package interfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

import clases.Camino;
import clases.Sucursal;
import enums.EstadoSucursal;
import gestores.GestorCamino;

import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaCamino extends JFrame {

	private JPanel contentPane;
	private JTextField idRutaTxt;
	private JTextField sucursalOtxt;
	private JTextField sucursalDtxt;
	private JTextField capacidadKgTxt;
	private JTextField tiemTransitoTxt;
	
	public GestorCamino gestorCamino= GestorCamino.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCamino frame = new VentanaCamino();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCamino() {
		JLabel tituloVentana = new JLabel("ALTA CAMINO");
		JLabel idCaminoLabel = new JLabel("Codigo de ruta (*)");
		JLabel SucursalOlabel = new JLabel("Sucursal Origen (*)");
		JLabel sucursalDLabel = new JLabel("Sucursal Destino (*)");
		JLabel tiempTransitoLabel = new JLabel("Tiempo de transito en min (*)");
		JLabel capacidadLabel = new JLabel("Capacidad maxima en kg (*)");
		JLabel estadoLabel = new JLabel("Estado (*)");
		JLabel labelErrorSucursalO = new JLabel("");
		JLabel labelErrorSD = new JLabel("");
		JLabel labelErrorEstado = new JLabel("");
		JLabel labelErrorTiempo = new JLabel("");
		JLabel labelErrorCapacidad = new JLabel("");
		idRutaTxt = new JTextField();
		sucursalOtxt = new JTextField();
		sucursalDtxt = new JTextField();
		tiemTransitoTxt = new JTextField();
		capacidadKgTxt = new JTextField();
		JButton botonCancelar = new JButton("Cancelar");
		JButton botonGuardar = new JButton("Guardar");
		JComboBox estadoComBox = new JComboBox();
		estadoComBox.setModel(new DefaultComboBoxModel(new String[] {"-SELECCIONE-", "OPERATIVA", "NO OPERATIVA"}));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Label camino ID
		idCaminoLabel.setBounds(25, 43, 107, 14);
		contentPane.add(idCaminoLabel);
		
		// TXT id Camino
		idRutaTxt.setBounds(204, 40, 116, 20);
		contentPane.add(idRutaTxt);
		idRutaTxt.setColumns(10);
		
		// titulo de la ventana 
		tituloVentana.setBackground(Color.BLACK);
		tituloVentana.setToolTipText("");
		tituloVentana.setBounds(176, 11, 88, 14);
		contentPane.add(tituloVentana);
		
		// Label Sucursal
		SucursalOlabel.setBounds(25, 74, 128, 14);
		contentPane.add(SucursalOlabel);
		
		// TXT Sucursal Origen
		sucursalOtxt.setBounds(204, 71, 116, 20);
		contentPane.add(sucursalOtxt);
		sucursalOtxt.setColumns(10);
		
		// Label Sucursal Destino
		sucursalDLabel.setBounds(25, 105, 128, 14);
		contentPane.add(sucursalDLabel);
		
		// TXT Sucursal Destino
		sucursalDtxt.setBounds(204, 102, 116, 20);
		contentPane.add(sucursalDtxt);
		sucursalDtxt.setColumns(10);
		
		// Label estado de camino
		estadoLabel.setBounds(25, 137, 56, 14);
		contentPane.add(estadoLabel);
		
		// Label capacidad de camino en kg
		capacidadLabel.setBounds(25, 165, 177, 14);
		contentPane.add(capacidadLabel);
		
		// TXT capacidad de ruta en kilogramos
		capacidadKgTxt.setBounds(204, 162, 116, 20);
		contentPane.add(capacidadKgTxt);
		capacidadKgTxt.setColumns(10);
		
		// Label Tiempo de transito en min
		tiempTransitoLabel.setBounds(25, 201, 177, 14);
		contentPane.add(tiempTransitoLabel);
		
		// TXT tiempo de transito en minutos
		tiemTransitoTxt.setBounds(204, 198, 116, 20);
		contentPane.add(tiemTransitoTxt);
		tiemTransitoTxt.setColumns(10);
		
		//Combo box de Estado Ruta
		estadoComBox.setToolTipText("Operativa\r\nNo operativa\r\n");
		estadoComBox.setEditable(true);
		estadoComBox.setBounds(204, 133, 116, 22);
		contentPane.add(estadoComBox);
				
				
		//label Error Sucursal Origen
		labelErrorSucursalO.setBounds(176, 88, 164, 14);;
		labelErrorSucursalO.setForeground(Color.RED);
		contentPane.add(labelErrorSucursalO);
			
		//labelError Sucursal Destino
		labelErrorSD.setBounds(176, 122, 164, 14);
		labelErrorSD.setForeground(Color.RED);
		contentPane.add(labelErrorSD);
				
		//Label Error Estado
		labelErrorEstado.setBounds(233, 152, 46, 14);
		labelErrorEstado.setForeground(Color.RED);
		contentPane.add(labelErrorEstado);
				
		//Label Error Tiempo
		labelErrorTiempo.setBounds(176, 215, 144, 14);
		labelErrorTiempo.setForeground(Color.RED);
		contentPane.add(labelErrorTiempo);
				
		//label Error Capacidad
		labelErrorCapacidad.setBounds(179, 176, 46, 14);
		labelErrorCapacidad.setForeground(Color.RED);
		contentPane.add(labelErrorCapacidad);
		

		//Boton Guardar
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sucursalO, sucursalD, capMaxS, estadoS;
				int tiempo;
				Sucursal so, sd;
				EstadoSucursal estado;
				double capacidad;
				/*idS= (idRutaTxt.getText());
				id= Integer.parseInt(idS);*/
				
				//VALIDACION DE DATOS
				if (tiemTransitoTxt.getText().isEmpty()) {
					labelErrorTiempo.setText("Por favor ingrese un tiempo para el camino");
				}
				tiempo= Integer.parseInt(tiemTransitoTxt.getText());
				if(sucursalOtxt.getText().isEmpty()) {
					labelErrorSucursalO.setText("Por favor, ingrese una sucursal de Origen");}
				sucursalO= sucursalOtxt.getText();
				so= Camino.buscarSucursal(sucursalO);
				if(so == null) {
					labelErrorSucursalO.setText("Por favor, ingrese una sucursal Existente");
				}
				
				if(sucursalDtxt.getText().isEmpty()) {
					labelErrorSD.setText("Por favor, ingrese una sucursal de Destino");
				}
				sucursalD= sucursalDtxt.getText();
				sd= Camino.buscarSucursal(sucursalD);
				if(sd == null) {
					labelErrorSD.setText("Por favor, ingrese una sucursal Existente");
				}
				if(capacidadKgTxt.getText().isEmpty()) {
					labelErrorCapacidad.setText("Por favor ingrese una capacidad en kg para el camino");
				}
				capMaxS= capacidadKgTxt.getText();
				capacidad= Double.parseDouble(capMaxS);
				estadoS= estadoComBox.getToolTipText();
				if(estadoS == "-Seleccione-") {
					labelErrorEstado.setText("Por favor ingrese un estado para el camino");
				}
				if(estadoS == "Operativa") estado = EstadoSucursal.OPERATIVA;
					else estado = EstadoSucursal.NO_OPERATIVA;

				Camino nuevoCamino = gestorCamino.crearCaminoGestor(so,sd,capacidad, estado, tiempo);
				gestorCamino.agregarCamino(nuevoCamino);
				
				}
			});
		botonGuardar.setBounds(361, 236, 81, 23);
		contentPane.add(botonGuardar);

		//Boton Cancelar
		botonCancelar.setBounds(263, 236, 88, 23);
		contentPane.add(botonCancelar);
		
		
		
	}
	

}
