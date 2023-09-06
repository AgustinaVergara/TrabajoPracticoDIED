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

import clases.Sucursal;
import excepciones.CampoInvalidoException;
import excepciones.CampoVacioException;
import excepciones.NombreSucursalExistenteException;
import gestores.GestorSucursal;
import javax.swing.SwingConstants;

public class JPanelAltaSucursal extends JPanel {

	
	private JTextField txtNombre;
	private JTextField txtHoraApertura;
	private JTextField txtMinutoApertura;
	private JTextField txtHoraCierre;
	private JTextField txtMinutoCierre;
	
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	
	public JPanelAltaSucursal() {
		setLayout(null);

		JLabel tituloAltaSucursal = new JLabel("CREAR NUEVA SUCURSAL");
		tituloAltaSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		tituloAltaSucursal.setBounds(188, 37, 230, 13);
		add(tituloAltaSucursal);
		txtNombre = new JTextField();
		txtNombre.setBounds(276, 71, 142, 19);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel nombreSucursal = new JLabel("Nombre (*)");
		nombreSucursal.setBounds(151, 74, 87, 13);
		add(nombreSucursal);

		JLabel horarioApertura = new JLabel("Hora apertura (*)");
		horarioApertura.setBounds(151, 110, 116, 13);
		add(horarioApertura);
		
		txtHoraApertura = new JTextField();
		txtHoraApertura.setBounds(276, 107, 48, 19);
		add(txtHoraApertura);
		txtHoraApertura.setColumns(10);
		
		txtMinutoApertura = new JTextField();
		txtMinutoApertura.setColumns(10);
		txtMinutoApertura.setBounds(344, 107, 48, 19);
		add(txtMinutoApertura);
		
		JLabel horarioCierre = new JLabel("Hora cierre (*)");
		horarioCierre.setBounds(151, 148, 96, 13);
		add(horarioCierre);
		
		txtHoraCierre = new JTextField();
		txtHoraCierre.setColumns(10);
		txtHoraCierre.setBounds(276, 145, 48, 19);
		add(txtHoraCierre);
		
		txtMinutoCierre = new JTextField();
		txtMinutoCierre.setColumns(10);
		txtMinutoCierre.setBounds(344, 145, 48, 19);
		add(txtMinutoCierre);
		
		JLabel separador_1 = new JLabel(":");
		separador_1.setBounds(334, 110, 30, 13);
		add(separador_1);
		
		JLabel separador_2 = new JLabel(":");
		separador_2.setBounds(334, 148, 13, 13);
		add(separador_2);
		
		JLabel EstadoSucursal = new JLabel("Estado (*)");
		EstadoSucursal.setBounds(151, 185, 96, 13);
		add(EstadoSucursal);
		
		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setBounds(276, 181, 142, 21);
		add(comboBoxEstado);
		comboBoxEstado.addItem("OPERATIVA");
		comboBoxEstado.addItem("NO_OPERATIVA");
		
		JButton btnCancelarSucursal = new JButton("Cancelar");
		btnCancelarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null,
		                "¿Está seguro de que desea cancelar y volver?", 
		                "Confirmar Cancelar", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

		        if (respuesta == JOptionPane.YES_OPTION) {
		        	vaciarCampos();
		        	MenuPrincipal.mostrarPanel("GestionarSucursal");
		        }
				
			}
		});
		btnCancelarSucursal.setBounds(211, 248, 96, 21);
		add(btnCancelarSucursal);
		
		JButton btnGuardarSucursal = new JButton("Guardar");
		btnGuardarSucursal.setBounds(312, 248, 87, 21);
		add(btnGuardarSucursal);
		
		
		btnGuardarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				try {
					campoVacio();
					campoValido();
					nombreSucursalExistente();
	
					
					Sucursal nuevaSucursal = gestorSucursal.crearSucursalGestor(txtNombre.getText(),
							LocalTime.of(Integer.parseInt(txtHoraApertura.getText()), Integer.parseInt(txtMinutoApertura.getText())),
							LocalTime.of(Integer.parseInt(txtHoraCierre.getText()), Integer.parseInt(txtMinutoCierre.getText())),
							enums.EstadoSucursal.valueOf(comboBoxEstado.getSelectedItem().toString()));
					
					gestorSucursal.agregarSucursal(nuevaSucursal);
					
					JOptionPane.showMessageDialog(frame, "Sucursal creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					vaciarCampos();
					MenuPrincipal.mostrarPanel("GestionarSucursal");
					
				}catch(CampoVacioException e1) {
					JOptionPane.showMessageDialog(frame,
							"Faltan completar los siguientes campos:\n\n"+e1.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}catch (CampoInvalidoException e2) {
					
					JOptionPane.showMessageDialog(frame,
							e2.getMessage()+"- El nombre puede tener como m�ximo 30 caracteres de longitud. \n"+
									  "- La hora debe encontrarse en el intervalo [0, 23]. \n"+
									  "- Los minutos deben encontrarse en el intervalo [0,59].\n"+
									  "- La hora de cierre debe ser mayor a la hora de inicio.\n",	
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}catch (NombreSucursalExistenteException e3) {
					
					JOptionPane.showMessageDialog(frame,
							e3.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	public void campoVacio() throws CampoVacioException{
		String error = "";
		boolean algunoVacio = false;
		
		if(txtNombre.getText().isEmpty()) {
			error += "- Nombre\n";
			algunoVacio = true;
		}
		
		if(txtHoraApertura.getText().isEmpty() || txtMinutoApertura.getText().isEmpty()) {
			error += "- Hora de apertura\n";
			algunoVacio = true;
		}
		
		if(txtHoraCierre.getText().isEmpty() || txtMinutoCierre.getText().isEmpty()) {
			error += "- Hora de cierre\n";
			algunoVacio = true;
		}
		
		if(algunoVacio) {
			
			throw new CampoVacioException(error);
		}
				
	}
	
	public void campoValido() throws CampoInvalidoException{
		
		if(!validarHora(txtHoraApertura) || !validarMinuto(txtMinutoApertura) ||
		   !validarHora(txtHoraCierre)   || !validarMinuto(txtMinutoCierre)   || !validarNombre(txtNombre)
		   || !horasValidas(txtHoraApertura, txtMinutoApertura, txtHoraCierre, txtMinutoCierre))

				throw new CampoInvalidoException();
	}
	
	public void nombreSucursalExistente() throws NombreSucursalExistenteException{ 
		
		for(Sucursal e : gestorSucursal.getSucursales()) {
			
			if(e.getNombre().equals(txtNombre.getText()))
				throw new NombreSucursalExistenteException();
		}
	}
	
	public boolean validarHora(JTextField field) { //Retorna false si no es integer o si no se encuentra en el rango [0, 23]
		
		try {
			Integer hora = Integer.parseInt(field.getText());
			
			if(hora > -1 && hora < 24) {
				
				return true; 
			}
			else {
				
				return false;
			}
			
		} catch(NumberFormatException e) {
			
			return false;
		}
	}
	
	public boolean validarMinuto(JTextField field) { //Retorna false si no es integer o si no se encuentra en el rango [0, 59]
		
		try {
			
			Integer minuto = Integer.parseInt(field.getText());
			
			if(minuto > -1 && minuto < 60) {
				
				return true; 
			}
			else {
				
				return false;
			}
			
		} catch(NumberFormatException e) {
			
			return false;
		}
	}
	
	public boolean validarNombre(JTextField field) { //Retorna false si la longitud del string es mayor a 30
		
		if(field.getText().length() > 30)
			return false;
		
		return true;
	}
	
	public boolean horasValidas(JTextField horaApertura, JTextField minutoApertura, JTextField horaCierre, JTextField minutoCierre) {
		
		Integer horaA = Integer.parseInt(horaApertura.getText());
		Integer horaC = Integer.parseInt(horaCierre.getText());
		Integer minutoA = Integer.parseInt(minutoApertura.getText());
		Integer minutoC = Integer.parseInt(minutoCierre.getText());
		
		if(horaC > horaA) {	//Si la hora de cierre es mayor, los minutos no importan (horas validas)
			return true;
		}
		else if(horaC == horaA){	//Si las horas son iguales, debo comparar minutos
			
			if(minutoC > minutoA) { //Minuto de cierre mayor (horas validas)
				return true;
			}
			else {
				return false;	//Minuto de apertura mayor (horas invalidas)
			}
		}
		else {	//Si la hora de apertura es mayor a la de cierre, los minutos no importan (horas invalidas)
			return false;
		}
	}
	
	public void vaciarCampos() {
		txtNombre.setText("");
		txtHoraApertura.setText("");
		txtMinutoApertura.setText("");
		txtHoraCierre.setText("");
		txtMinutoCierre.setText("");
	}
	

}
