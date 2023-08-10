package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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

public class InterfazModificarCamino extends JFrame {

	private JPanel contentPane;
	private JTextField idRutaTxt;
	private JTextField sucursalOtxt;
	private JTextField sucursalDtxt;
	private JTextField capacidadKgTxt;
	private JTextField tiemTransitoTxt;
	
	public GestorCamino gestorCamino= GestorCamino.getInstance();

	/**
	 * Create the frame.
	 */
	public InterfazModificarCamino(Camino caminoAModificar) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Tamaño deseado para el JFrame
        int width = 600;
        int height = 400;
        
        // Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        // Calculamos las coordenadas (x, y) para centrar el JFrame
        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;
        
        // Establecemos las coordenadas y el tamaño
        setBounds(x, y, width, height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel tituloVentana = new JLabel("MODIFICAR CAMINO");
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
		JButton botonCancelar = new JButton("Cancelar cambios");
		JButton botonGuardar = new JButton("Guardar cambios");
		JComboBox estadoComBox = new JComboBox();
		estadoComBox.setModel(new DefaultComboBoxModel(new String[] {"-SELECCIONAR-","OPERATIVA", "NO OPERTATIVA"}));
		JComboBox comboBoxSO = new JComboBox();
		comboBoxSO.setEditable(true);
		JComboBox comboBoxSD = new JComboBox();
		DefaultComboBoxModel modelo;// modelos para los combobox
		DefaultComboBoxModel modelo2;
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		SucursalDao dao = new SucursalDaoImpl();;
		sucursales = dao.buscarSucursales();
		CaminoDao daocamino = new CaminoSQLimplementacion();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Label camino ID
		idCaminoLabel.setBounds(25, 43, 107, 14);
		contentPane.add(idCaminoLabel);
		
		// TXT id Camino
		String id= Integer.toString(caminoAModificar.getId());
		idRutaTxt.setText(id);
		idRutaTxt.setBounds(204, 40, 116, 20);
		contentPane.add(idRutaTxt);
		idRutaTxt.setColumns(10);
		
		// titulo de la ventana 
		tituloVentana.setBackground(Color.BLACK);
		tituloVentana.setToolTipText("");
		tituloVentana.setBounds(176, 11, 122, 14);
		contentPane.add(tituloVentana);
		
		// Label Sucursal
		SucursalOlabel.setBounds(25, 74, 128, 14);
		contentPane.add(SucursalOlabel);
		
		// ARMANDO COMBOBOX Sucursal Origen
		
		modelo =new DefaultComboBoxModel();
		String nombreSO = caminoAModificar.getSO().getNombre();
		modelo.addElement(nombreSO);
		comboBoxSO.setEditable(false);
		comboBoxSO.setModel(modelo);
		comboBoxSO.setBounds(204, 70, 116, 22);
		contentPane.add(comboBoxSO);
		
		// Label Sucursal Destino
		sucursalDLabel.setBounds(25, 115, 128, 14);
		contentPane.add(sucursalDLabel);
		
		// ARMANDO COMBOBOX Sucursal Destino
		modelo2 =new DefaultComboBoxModel();
		
		String nombreSD = caminoAModificar.getSD().getNombre();
		modelo2.addElement(nombreSD);
		comboBoxSD.setModel(modelo2);
		comboBoxSD.setEditable(false);
		comboBoxSD.setBounds(204, 111, 116, 22);
		contentPane.add(comboBoxSD);
		
		// Label estado de camino
		estadoLabel.setBounds(25, 159, 56, 14);
		contentPane.add(estadoLabel);
		
		// Label capacidad de camino en kg
		capacidadLabel.setBounds(25, 195, 177, 14);
		contentPane.add(capacidadLabel);
		
		// TXT capacidad de ruta en kilogramos
		capacidadKgTxt.setBounds(204, 194, 116, 20);
		contentPane.add(capacidadKgTxt);
		capacidadKgTxt.setColumns(10);
		
		// Label Tiempo de transito en min
		tiempTransitoLabel.setBounds(25, 245, 177, 14);
		contentPane.add(tiempTransitoLabel);
		
		// TXT tiempo de transito en minutos
		tiemTransitoTxt.setBounds(204, 242, 116, 20);
		contentPane.add(tiemTransitoTxt);
		tiemTransitoTxt.setColumns(10);
		
		//Combo box de Estado Ruta
		
		estadoComBox.setEditable(true);
		estadoComBox.setBounds(204, 155, 116, 22);
		contentPane.add(estadoComBox);
				
				
		//label Error Sucursal Origen
		labelErrorSucursalO.setBounds(145, 93, 257, 14);;
		labelErrorSucursalO.setForeground(Color.RED);
		contentPane.add(labelErrorSucursalO);
			
		//labelError Sucursal Destino
		labelErrorSD.setBounds(145, 132, 257, 14);
		labelErrorSD.setForeground(Color.RED);
		contentPane.add(labelErrorSD);
				
		//Label Error Estado
		labelErrorEstado.setBounds(233, 152, 46, 14);
		labelErrorEstado.setForeground(Color.RED);
		contentPane.add(labelErrorEstado);
				
		//Label Error Tiempo
		labelErrorTiempo.setBounds(126, 268, 316, 14);
		labelErrorTiempo.setForeground(Color.RED);
		contentPane.add(labelErrorTiempo);
				
		//label Error Capacidad
		labelErrorCapacidad.setBounds(81, 220, 391, 14);
		labelErrorCapacidad.setForeground(Color.RED);
		contentPane.add(labelErrorCapacidad);
		

		//Boton Guardar
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String capMaxS, estadoS;//sucursalO, sucursalD, 
						
				int tiempo;
				//Sucursal so, sd;
				EstadoSucursal estado = null;
				double capacidad;
				
				// COMENZAMOS VALIDACION DE DATOS
				
				//SUCURSAL ORIGEN
				/*sucursalO= comboBoxSO.getSelectedItem().toString();
				
				// SUCURSAL NO SELECCIONADA
				if(sucursalO == "-SELECCIONE-") {
					labelErrorSucursalO.setText("Por favor, ingrese una sucursal de Origen");}
				
				// SUCURSAL DESTINO
				 sucursalD= comboBoxSD.getSelectedItem().toString();
				 
				//SUCURSAL NO SELECCIONADA
				if(sucursalD == "-SELECCIONE-") {
					labelErrorSD.setText("Por favor, ingrese una sucursal de Destino");
				}*/
				
				// CAPACIDAD DE LA RUTA EN KG VACIA
				
				if(capacidadKgTxt.getText().isEmpty()) {
					labelErrorCapacidad.setText("Por favor ingrese una capacidad maxima en kg para el camino");
				}
				
				// TIEMPO TRANSITO VACIO
				if (tiemTransitoTxt.getText().isEmpty()) {
					labelErrorTiempo.setText("Por favor ingrese el tiempo de transito para el camino");
				}
				/*so= Camino.buscarSucursal(sucursalO);
				sd= Camino.buscarSucursal(sucursalD);*/
				capMaxS= capacidadKgTxt.getText();
				capacidad= Double.parseDouble(capMaxS);
				estadoS= estadoComBox.getSelectedItem().toString();
				if(estadoS == "OPERATIVA") 
					estado = EstadoSucursal.OPERATIVA;
					else if(estadoS== "NO OPERATIVA")
							estado = EstadoSucursal.NO_OPERATIVA;
						else  if(estadoS == "-SELECCIONAR-")
							labelErrorEstado.setText("Por favor ingrese un estado para el camino");
				tiempo= Integer.parseInt(tiemTransitoTxt.getText());
				
				gestorCamino.modificarCamino(caminoAModificar.getId(), capacidad, tiempo, estado);
				
				
				}
			});
		botonGuardar.setBounds(285,293,157,23);
		contentPane.add(botonGuardar);

		// EN BOTON CANCELAR SOLO LIMPIAMOS LOS DATOS INGRESADOS 
		
		 botonCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cerrar();
			}
		 
		 });
		//Boton Cancelar
		botonCancelar.setBounds(113,293,162,23);
		contentPane.add(botonCancelar);
		
		

		
	}
	
	// METODO PARA CONFIRMAR EL CIERRE DEL JFRAME CDO SELECCIONAMOS BOTON CANCELAR
	
	public void cerrar() {
		String [] botones = {"Si", "Cancelar"};
		int i= JOptionPane.showOptionDialog(this, "¿Estas seguro de cancelar la operacion?", "Muchas respuestas",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);
		/*int i= JOptionPane.showConfirmDialog(this, "¿Estas seguro de cancelar la operacion?");*/
		if(i==0) 
			System.exit((WIDTH));
		
	}
}
