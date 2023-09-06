package interfaces;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Sucursal;
import excepciones.CampoInvalidoException;
import excepciones.CampoVacioException;
import excepciones.NombreSucursalExistenteException;
import gestores.GestorSucursal;

public class JPanelModificarSucursal extends JPanel {

	private JTextField txtNombre;
	private JTextField txtHoraApertura;
	private JTextField txtMinutoApertura;
	private JTextField txtHoraCierre;
	private JTextField txtMinutoCierre;
	private JComboBox<String> comboBoxEstado;

	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	private Sucursal sucursalSeleccionada;
	private JPanelListadoSucursal panelListadoSucursal;

	/**
	 * Create the panel.
	 */
	public JPanelModificarSucursal() {
		setLayout(null);

		JLabel tituloModificarSucursal = new JLabel("MODIFICAR SUCURSAL");
		tituloModificarSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		tituloModificarSucursal.setBounds(194, 39, 190, 13);
		add(tituloModificarSucursal);

		JLabel nombreSucursal = new JLabel("Nombre ");
		nombreSucursal.setBounds(119, 66, 96, 13);
		add(nombreSucursal);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(244, 63, 160, 19);
		// txtNombre.setText(sucursalSeleccionada.getNombre());
		add(txtNombre);

		JLabel horarioApertura = new JLabel("Hora apertura");
		horarioApertura.setBounds(119, 109, 114, 13);
		add(horarioApertura);

		txtHoraApertura = new JTextField();
		txtHoraApertura.setColumns(10);
		txtHoraApertura.setBounds(243, 106, 45, 19);
		// txtHoraApertura.setText((sucursalSeleccionada.getHorarioApertura().toString()).substring(0,2));
		add(txtHoraApertura);

		JLabel separador_1 = new JLabel(":");
		separador_1.setBounds(292, 109, 27, 13);
		add(separador_1);

		txtMinutoApertura = new JTextField();
		txtMinutoApertura.setColumns(10);
		txtMinutoApertura.setBounds(298, 106, 45, 19);
		// txtMinutoApertura.setText((sucursalSeleccionada.getHorarioApertura().toString()).substring(3,5));
		add(txtMinutoApertura);

		JLabel horarioCierre = new JLabel("Hora cierre");
		horarioCierre.setBounds(119, 147, 80, 13);
		add(horarioCierre);

		txtHoraCierre = new JTextField();
		txtHoraCierre.setColumns(10);
		txtHoraCierre.setBounds(244, 147, 45, 19);
		// txtHoraCierre.setText((sucursalSeleccionada.getHorarioCierre().toString()).substring(0,2));
		add(txtHoraCierre);

		JLabel separador_2 = new JLabel(":");
		separador_2.setBounds(293, 150, 27, 13);
		add(separador_2);

		txtMinutoCierre = new JTextField();
		txtMinutoCierre.setColumns(10);
		txtMinutoCierre.setBounds(299, 147, 45, 19);
		// txtMinutoCierre.setText((sucursalSeleccionada.getHorarioCierre().toString()).substring(3,5));
		add(txtMinutoCierre);

		JLabel EstadoSucursal = new JLabel("Estado");
		EstadoSucursal.setBounds(120, 183, 45, 13);
		add(EstadoSucursal);

		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setBounds(244, 179, 160, 21);
		// comboBoxEstado.setSelectedItem(sucursalSeleccionada.getEstado());
		comboBoxEstado.addItem("OPERATIVA");
		comboBoxEstado.addItem("NO_OPERATIVA");
		add(comboBoxEstado);

		JButton btnGuardarSucursal = new JButton("Guardar");
		btnGuardarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				try {
					campoVacio();
					campoValido();
					nombreSucursalExistente();

					int option = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres guardar los cambios de la sucursal?",
							"Confirmar modificación", JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {

						// El usuario confirma las modificacione de la sucursal
						gestorSucursal.modificarSucursal(sucursalSeleccionada.getId(), txtNombre.getText(),
								LocalTime.of(Integer.parseInt(txtHoraApertura.getText()),
										Integer.parseInt(txtMinutoApertura.getText())),
								LocalTime.of(Integer.parseInt(txtHoraCierre.getText()),
										Integer.parseInt(txtMinutoCierre.getText())),
								enums.EstadoSucursal.valueOf(comboBoxEstado.getSelectedItem().toString()));
						int result = JOptionPane.showConfirmDialog(null, "Sucursal modificada con éxito",
								"Confirmación", JOptionPane.DEFAULT_OPTION);

						if (result == JOptionPane.OK_OPTION) {

							panelListadoSucursal.llenarTabla(gestorSucursal.getSucursales());
							MenuPrincipal.mostrarPanel("ListadoSucursal");
						}
					}
				} catch (CampoVacioException e1) {
					JOptionPane.showMessageDialog(frame,
							"Faltan completar los siguientes campos:\n\n" + e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (CampoInvalidoException e2) {

					JOptionPane.showMessageDialog(frame,
							e2.getMessage() + "- El nombre puede tener como m�ximo 30 caracteres de longitud. \n"
									+ "- La hora debe encontrarse en el intervalo [0, 23]. \n"
									+ "- Los minutos deben encontrarse en el intervalo [0,59].\n"
									+ "- La hora de cierre debe ser mayor a la hora de inicio.\n",
							"Error", JOptionPane.ERROR_MESSAGE);
				} catch (NombreSucursalExistenteException e3) {

					JOptionPane.showMessageDialog(frame, e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		btnGuardarSucursal.setBounds(299, 249, 85, 21);
		add(btnGuardarSucursal);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar y volver?",
						"Confirmar Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (respuesta == JOptionPane.YES_OPTION) {
					MenuPrincipal.mostrarPanel("ListadoSucursal");
				}
			}
		});
		btnCancelar.setBounds(204, 249, 85, 21);
		add(btnCancelar);

	}

	public void campoVacio() throws CampoVacioException {
		String error = "";
		boolean algunoVacio = false;

		if (txtNombre.getText().isEmpty()) {
			error += "- Nombre\n";
			algunoVacio = true;
		}

		if (txtHoraApertura.getText().isEmpty() || txtMinutoApertura.getText().isEmpty()) {
			error += "- Hora de apertura\n";
			algunoVacio = true;
		}

		if (txtHoraCierre.getText().isEmpty() || txtMinutoCierre.getText().isEmpty()) {
			error += "- Hora de cierre\n";
			algunoVacio = true;
		}

		if (algunoVacio) {

			throw new CampoVacioException(error);
		}

	}

	public void campoValido() throws CampoInvalidoException {

		if (!validarHora(txtHoraApertura) || !validarMinuto(txtMinutoApertura) || !validarHora(txtHoraCierre)
				|| !validarMinuto(txtMinutoCierre) || !validarNombre(txtNombre)
				|| !horasValidas(txtHoraApertura, txtMinutoApertura, txtHoraCierre, txtMinutoCierre))

			throw new CampoInvalidoException();
	}

	public void nombreSucursalExistente() throws NombreSucursalExistenteException {

		for (Sucursal e : gestorSucursal.getSucursales()) {

			if (e.getNombre().equals(txtNombre.getText()))
				throw new NombreSucursalExistenteException();
		}
	}

	public boolean validarHora(JTextField field) { // Retorna false si no es integer o si no se encuentra en el rango
													// [0, 23]

		try {
			Integer hora = Integer.parseInt(field.getText());

			if (hora > -1 && hora < 24) {

				return true;
			} else {

				return false;
			}

		} catch (NumberFormatException e) {

			return false;
		}
	}

	public boolean validarMinuto(JTextField field) { // Retorna false si no es integer o si no se encuentra en el rango
														// [0, 59]

		try {

			Integer minuto = Integer.parseInt(field.getText());

			if (minuto > -1 && minuto < 60) {

				return true;
			} else {

				return false;
			}

		} catch (NumberFormatException e) {

			return false;
		}
	}

	public boolean validarNombre(JTextField field) { // Retorna false si la longitud del string es mayor a 30

		if (field.getText().length() > 30)
			return false;

		return true;
	}

	public boolean horasValidas(JTextField horaApertura, JTextField minutoApertura, JTextField horaCierre,
			JTextField minutoCierre) {

		Integer horaA = Integer.parseInt(horaApertura.getText());
		Integer horaC = Integer.parseInt(horaCierre.getText());
		Integer minutoA = Integer.parseInt(minutoApertura.getText());
		Integer minutoC = Integer.parseInt(minutoCierre.getText());

		if (horaC > horaA) { // Si la hora de cierre es mayor, los minutos no importan (horas validas)
			return true;
		} else if (horaC == horaA) { // Si las horas son iguales, debo comparar minutos

			if (minutoC > minutoA) { // Minuto de cierre mayor (horas validas)
				return true;
			} else {
				return false; // Minuto de apertura mayor (horas invalidas)
			}
		} else { // Si la hora de apertura es mayor a la de cierre, los minutos no importan
					// (horas invalidas)
			return false;
		}
	}

	public void setSucursalSeleccionada(Sucursal s) {
		this.sucursalSeleccionada = s;
	}

	public void setPanelListadoSucursal(JPanelListadoSucursal panelListadoSucursal) {
		this.panelListadoSucursal = panelListadoSucursal;
	}

	// Este metodo se encarga de setear los valores de la sucursal que se desea
	// modificar
	public void setCamposAModificar() {
		txtNombre.setText(sucursalSeleccionada.getNombre());
		txtHoraApertura.setText((sucursalSeleccionada.getHorarioApertura().toString()).substring(0, 2));
		txtMinutoApertura.setText((sucursalSeleccionada.getHorarioApertura().toString()).substring(3, 5));
		txtHoraCierre.setText((sucursalSeleccionada.getHorarioCierre().toString()).substring(0, 2));
		txtMinutoCierre.setText((sucursalSeleccionada.getHorarioCierre().toString()).substring(3, 5));
		comboBoxEstado.setSelectedItem(sucursalSeleccionada.getEstado());
	}

}
