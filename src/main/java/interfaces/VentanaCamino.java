package interfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.factories.DefaultComponentFactory;

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
		JLabel tituloVentana = DefaultComponentFactory.getInstance().createTitle("ALTA CAMINO");
		JLabel idCaminoLabel = new JLabel("Codigo de ruta (*)");
		JLabel SucursalOlabel = new JLabel("Sucursal Origen (*)");
		JLabel sucursalDLabel = new JLabel("Sucursal Destino (*)");
		JLabel tiempTransitoLabel = new JLabel("Tiempo de transito en min (*)");
		JLabel capacidadLabel = new JLabel("Capacidad maxima en kg (*)");
		JLabel estadoLabel = new JLabel("Estado (*)");
		idRutaTxt = new JTextField();
		sucursalOtxt = new JTextField();
		sucursalDtxt = new JTextField();
		tiemTransitoTxt = new JTextField();
		capacidadKgTxt = new JTextField();
		JButton botonCancelar = new JButton("Cancelar");
		JButton botonGuardar = new JButton("Guardar");
		JComboBox estadoComBox = new JComboBox();
		estadoComBox.setModel(new DefaultComboBoxModel(new String[] {"OPERATIVA", "NO OPERATIVA"}));
		
		
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
		
		//Boton Guardar
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tiempoS, sucursalO, sucursalD, capMaxS, idS, estadoS;
				int id, tiempo;
				Sucursal so;
				EstadoSucursal estado;
				double capacidad;
				//EstadoSucursal estado;
				idS= (idRutaTxt.getText());
				id= Integer.parseInt(idS);
				tiempoS=tiemTransitoTxt.getText();
				tiempo= Integer.parseInt(tiempoS);
				sucursalO= sucursalOtxt.getText();
				//so= Camino.buscarSucursal(sucursalO);
				sucursalD= sucursalDtxt.getText();
				capMaxS= capacidadKgTxt.getText();
				capacidad= Double.parseDouble(capMaxS);
				estadoS= estadoComBox.getToolTipText();
				if(estadoS == "Operativa") estado = EstadoSucursal.OPERATIVA;
					else estado = EstadoSucursal.NO_OPERATIVA;
				GestorCamino.crearCamino(id,sucursalO,sucursalD,capacidad, estado);
				}
			});
		botonGuardar.setBounds(361, 236, 81, 23);
		contentPane.add(botonGuardar);

		//Boton Cancelar
		botonCancelar.setBounds(263, 236, 88, 23);
		contentPane.add(botonCancelar);
		
	}
}
