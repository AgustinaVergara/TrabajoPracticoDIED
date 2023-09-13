package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Camino;
import clases.Sucursal;
import dao.CaminoDao;
import dao.CaminoSQLimplementacion;
import dao.SucursalDao;
import dao.SucursalDaoImpl;
import enums.EstadoSucursal;
import gestores.GestorCamino;
import gestores.GestorSucursal;

import javax.swing.SwingConstants;

public class JPanelAltaCamino extends JPanel {

	private JTextField idRutaTxt;
	private JComboBox<String> comboBoxSO;
	private JComboBox<String> comboBoxSD;
	private JComboBox<String> estadoComBox;
	private JTextField capacidadKgTxt;
	private JTextField tiemTransitoTxt;
	private List<Sucursal> sucursales;
	private DefaultComboBoxModel modelo;
	private DefaultComboBoxModel modelo2;
	private String eleccion;

	public GestorCamino gestorCamino = GestorCamino.getInstance();
	public GestorSucursal gestorSucursal = GestorSucursal.getInstance();

	/**
	 * Create the panel.
	 */
	public JPanelAltaCamino() {
		setLayout(null);

		JLabel tituloVentana = new JLabel("ALTA CAMINO");
		tituloVentana.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel idCaminoLabel = new JLabel("ID (*)");
		JLabel SucursalOlabel = new JLabel("Sucursal Origen (*)");
		JLabel sucursalDLabel = new JLabel("Sucursal Destino (*)");
		JLabel tiempTransitoLabel = new JLabel("Tiempo de transito en min (*)");
		JLabel capacidadLabel = new JLabel("Capacidad maxima en kg (*)");
		JLabel estadoLabel = new JLabel("Estado (*)");
		JLabel labelErrorSucursalO = new JLabel("");
		JLabel labelErrorSD = new JLabel("");
		JLabel labelErrorEstado = new JLabel("");
		JLabel labelErrorCapacidad = new JLabel("");
		JLabel labelErrorTiempo = new JLabel("");
		idRutaTxt = new JTextField();
		idRutaTxt.setEditable(false);
		tiemTransitoTxt = new JTextField();
		capacidadKgTxt = new JTextField();
		JButton botonCancelar = new JButton("Cancelar");

		JButton botonGuardar = new JButton("Guardar");
		estadoComBox = new JComboBox();
		estadoComBox.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "OPERATIVA", "NO OPERTATIVA" }));
		comboBoxSO = new JComboBox();
		comboBoxSO.setEditable(true);
		comboBoxSD = new JComboBox();
		// CREO UN MODELO A SEGUIR PARA EL COMBO BOX DE SUCURSALES

		sucursales = new ArrayList<Sucursal>();
		// SucursalDao dao = new SucursalDaoImpl();
		gestorSucursal.refreshSucursales();
		sucursales = gestorSucursal.getSucursales();

		// sucursales = dao.buscarSucursales();
		CaminoDao daocamino = new CaminoSQLimplementacion();

		// Label camino ID
		idCaminoLabel.setBounds(25, 43, 107, 14);
		add(idCaminoLabel);

		// TXT id Camino
		String id;
		int idInt = daocamino.getUltimoIdCamino() + 1;
		id = Integer.toString(idInt);
		idRutaTxt.setText(id);
		idRutaTxt.setBounds(188, 37, 116, 20);
		add(idRutaTxt);
		idRutaTxt.setColumns(10);

		// titulo de la ventana
		tituloVentana.setBackground(Color.BLACK);
		tituloVentana.setToolTipText("");
		tituloVentana.setBounds(261, 10, 122, 14);
		add(tituloVentana);

		// Label Sucursal
		SucursalOlabel.setBounds(25, 74, 128, 14);
		add(SucursalOlabel);

		// ARMANDO COMBOBOX Sucursal Origen
		modelo = new DefaultComboBoxModel();
		// COMENZAMOS CON MODELO DEL COMBOBOX
		String seleccionar = "Seleccione";
		modelo.addElement(seleccionar);
		// CARGAMOS LOS NOMBRES DE LAS SUCURSALES
		for (Sucursal s : sucursales) {
			System.out.println(s.getNombre());
			Object nombre = new Object();
			nombre = s.getNombre();
			modelo.addElement(nombre);
		}
		comboBoxSO.setModel(modelo);
		eleccion = comboBoxSO.getToolTipText();
		comboBoxSO.setBounds(188, 67, 116, 22);
		add(comboBoxSO);

		// Label Sucursal Destino
		sucursalDLabel.setBounds(25, 115, 128, 14);
		add(sucursalDLabel);

		// COMBOBOX Sucursal Destino

		// ARMANDO COMBOBOX Sucursal Destino
		modelo2 = new DefaultComboBoxModel();
		// COMENZAMOS CON MODELO DEL COMBOBOX
		String seleccionar2 = "Seleccione";
		modelo2.addElement(seleccionar2);
		// CARGAMOS LOS NOMBRES DE LAS SUCURSALES
		for (Sucursal s2 : sucursales) {
			Object nombre2 = new Object();
			if (eleccion != s2.getNombre()) {
				nombre2 = s2.getNombre();
				modelo2.addElement(nombre2);
			}
		}
		comboBoxSD.setModel(modelo2);
		comboBoxSD.setEditable(true);
		comboBoxSD.setBounds(188, 108, 116, 22);
		add(comboBoxSD);

		// Label estado de camino
		estadoLabel.setBounds(25, 159, 56, 14);
		add(estadoLabel);

		// Label capacidad de camino en kg
		capacidadLabel.setBounds(25, 195, 177, 14);
		add(capacidadLabel);

		// TXT capacidad de ruta en kilogramos
		capacidadKgTxt.setBounds(188, 191, 116, 20);
		add(capacidadKgTxt);
		capacidadKgTxt.setColumns(10);

		// Label Tiempo de transito en min
		tiempTransitoLabel.setBounds(25, 235, 165, 14);
		add(tiempTransitoLabel);

		// TXT tiempo de transito en minutos
		tiemTransitoTxt.setBounds(188, 233, 116, 20);
		add(tiemTransitoTxt);
		tiemTransitoTxt.setColumns(10);

		// Combo box de Estado Ruta

		estadoComBox.setEditable(true);
		estadoComBox.setBounds(188, 152, 116, 22);
		add(estadoComBox);

		// label Error Sucursal Origen
		labelErrorSucursalO.setBounds(145, 93, 257, 14);
		;
		labelErrorSucursalO.setForeground(Color.RED);
		add(labelErrorSucursalO);

		// labelError Sucursal Destino
		labelErrorSD.setBounds(145, 132, 257, 14);
		labelErrorSD.setForeground(Color.RED);
		add(labelErrorSD);

		// Label Error Estado
		labelErrorEstado.setBounds(233, 152, 46, 14);
		labelErrorEstado.setForeground(Color.RED);
		add(labelErrorEstado);

		// Label Error Tiempo
		labelErrorTiempo.setBounds(126, 268, 316, 14);
		labelErrorTiempo.setForeground(Color.RED);
		add(labelErrorTiempo);

		// label Error Capacidad
		labelErrorCapacidad.setBounds(25, 219, 391, 14);
		labelErrorCapacidad.setForeground(Color.RED);
		add(labelErrorCapacidad);

		// Boton Guardar
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sucursalO, sucursalD, capMaxS, estadoS;
				int tiempo;
				Sucursal so, sd;
				EstadoSucursal estado = EstadoSucursal.OPERATIVA;
				double capacidad;

				// COMENZAMOS VALIDACION DE DATOS

				// SUCURSAL ORIGEN
				sucursalO = comboBoxSO.getSelectedItem().toString();

				// SUCURSAL NO SELECCIONADA
				if (sucursalO == "Seleccione") {
					labelErrorSucursalO.setText("Por favor, ingrese una sucursal de Origen");
				}

				// SUCURSAL DESTINO
				sucursalD = comboBoxSD.getSelectedItem().toString();

				// SUCURSAL NO SELECCIONADA
				if (sucursalD == "Seleccione") {
					labelErrorSD.setText("Por favor, ingrese una sucursal de Destino");
				}

				// CAPACIDAD DE LA RUTA EN KG VACIA
				if (capacidadKgTxt.getText().isEmpty()) {
					labelErrorCapacidad.setText("Por favor ingrese una capacidad maxima en kg para el camino");
				}

				// TIEMPO TRANSITO VACIO
				if (tiemTransitoTxt.getText().isEmpty()) {
					labelErrorTiempo.setText("Por favor ingrese el tiempo de transito para el camino");
				}
				so = Camino.buscarSucursal(sucursalO);
				sd = Camino.buscarSucursal(sucursalD);
				capMaxS = capacidadKgTxt.getText();
				capacidad = Double.parseDouble(capMaxS);
				estadoS = estadoComBox.getSelectedItem().toString();
				if (estadoS == "OPERATIVA")
					estado = EstadoSucursal.OPERATIVA;
				else if (estadoS == "NO OPERATIVA")
					estado = EstadoSucursal.NO_OPERATIVA;
				else if (estadoS == "Seleccione")
					labelErrorEstado.setText("Por favor ingrese un estado para el camino");
				tiempo = Integer.parseInt(tiemTransitoTxt.getText());
				Camino nuevoCamino = gestorCamino.crearCaminoGestor(idInt, so, sd, capacidad, estado, tiempo);
				gestorCamino.agregarCamino(nuevoCamino);
				JOptionPane.showMessageDialog(null, "Se guardaron correctamente los datos");
				vaciarCampos();
				MenuPrincipal.mostrarPanel("GestionarCamino");

			}
		});
		botonGuardar.setBounds(326, 285, 116, 23);
		add(botonGuardar);

		// Boton Cancelar
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar y volver?",
						"Confirmar Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (respuesta == JOptionPane.YES_OPTION) {
					vaciarCampos();
					MenuPrincipal.mostrarPanel("GestionarCamino");
				}
			}
		});
		botonCancelar.setBounds(188, 285, 116, 23);
		add(botonCancelar);

	}

	public void vaciarCampos() {
		comboBoxSO.setSelectedItem(null);
		comboBoxSO.setSelectedItem("Seleccione");
		comboBoxSD.setSelectedItem(null);
		comboBoxSD.setSelectedItem("Seleccione");
		estadoComBox.setSelectedItem(null);
		estadoComBox.setSelectedItem("Seleccione");
		capacidadKgTxt.setText("");
		tiemTransitoTxt.setText("");
	}

	public void actualizarSucursales() {
		gestorSucursal.refreshSucursales();
		sucursales = gestorSucursal.getSucursales();

		// ARMANDO COMBOBOX Sucursal Origen
		modelo = new DefaultComboBoxModel();
		// COMENZAMOS CON MODELO DEL COMBOBOX
		String seleccionar = "Seleccione";
		modelo.addElement(seleccionar);
		// CARGAMOS LOS NOMBRES DE LAS SUCURSALES
		for (Sucursal s : sucursales) {
			System.out.println(s.getNombre());
			Object nombre = new Object();
			nombre = s.getNombre();
			modelo.addElement(nombre);
		}
		comboBoxSO.setModel(modelo);

		// COMBOBOX Sucursal Destino

		// ARMANDO COMBOBOX Sucursal Destino
		modelo2 = new DefaultComboBoxModel();
		// COMENZAMOS CON MODELO DEL COMBOBOX
		String seleccionar2 = "Seleccione";
		modelo2.addElement(seleccionar2);
		// CARGAMOS LOS NOMBRES DE LAS SUCURSALES
		for (Sucursal s2 : sucursales) {
			Object nombre2 = new Object();
			if (eleccion != s2.getNombre()) {
				nombre2 = s2.getNombre();
				modelo2.addElement(nombre2);
			}
		}
		comboBoxSD.setModel(modelo2);
	}

}
