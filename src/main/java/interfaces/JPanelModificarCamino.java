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

public class JPanelModificarCamino extends JPanel {
	
	private JTextField idRutaTxt;
	
	private JComboBox<String> comboBoxSO;
	private JComboBox<String> comboBoxSD;
	private JComboBox<String> estadoComBox;
	private JTextField capacidadKgTxt;
	private JTextField tiemTransitoTxt;
	
	private List<Sucursal> listaSucursales;
	
	public GestorCamino gestorCamino= GestorCamino.getInstance();
	public GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	
	private Camino caminoSeleccionado;
	private JPanelListadoCamino panelListadoCamino; 

	/**
	 * Create the panel.
	 */
	public JPanelModificarCamino() {
		 setLayout(null);
		
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
		JButton botonCancelar = new JButton("Cancelar");
		JButton botonGuardar = new JButton("Guardar");
		estadoComBox = new JComboBox();
		estadoComBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione","OPERATIVA", "NO OPERTATIVA"}));
		comboBoxSO = new JComboBox<String>();
		comboBoxSO.setEditable(true);
		//comboBoxSD = new JComboBox<String>();
		DefaultComboBoxModel modelo;// modelos para los combobox
		DefaultComboBoxModel modelo2;
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		SucursalDao dao = new SucursalDaoImpl();;
		sucursales = dao.buscarSucursales();
		CaminoDao daocamino = new CaminoSQLimplementacion();
		
		
		// Label camino ID
		idCaminoLabel.setBounds(25, 43, 107, 14);
		add(idCaminoLabel);
		
		// TXT id Camino
		String id;
		//id = Integer.toString(caminoSeleccionado.getId());
		//idRutaTxt.setText(id);
		idRutaTxt.setBounds(204, 40, 116, 20);
		add(idRutaTxt);
		idRutaTxt.setColumns(10);
		
		// titulo de la ventana 
		tituloVentana.setBackground(Color.BLACK);
		tituloVentana.setToolTipText("");
		tituloVentana.setBounds(176, 11, 122, 14);
		add(tituloVentana);
		
		// Label Sucursal
		SucursalOlabel.setBounds(25, 74, 128, 14);
		add(SucursalOlabel);
		
		// ARMANDO COMBOBOX Sucursal Origen
		
		comboBoxSO = new JComboBox<String>();
		comboBoxSO.setBounds(204, 71, 117, 21);
				
		listaSucursales = gestorSucursal.getSucursales();
		DefaultComboBoxModel<String> modeloSO;
        modeloSO = new DefaultComboBoxModel<>();
        modeloSO.addElement("Seleccione");
        for(Sucursal s : listaSucursales) {
        	modeloSO.addElement(s.getNombre());
        }
        comboBoxSO.setModel(modeloSO);
        add(comboBoxSO);
		
		
		// Label Sucursal Destino
		sucursalDLabel.setBounds(25, 115, 128, 14);
		add(sucursalDLabel);
		
		// ARMANDO COMBOBOX Sucursal Destino
		comboBoxSD = new JComboBox<String>();
		comboBoxSD.setBounds(203, 112, 117, 21);
				
		
		DefaultComboBoxModel<String> modeloSD;
        modeloSD = new DefaultComboBoxModel<>();
        modeloSD.addElement("Seleccione");
        for(Sucursal s : listaSucursales) {
        	modeloSD.addElement(s.getNombre());
        }
        comboBoxSD.setModel(modeloSD);
        add(comboBoxSD);
     
		// Label estado de camino
		estadoLabel.setBounds(25, 159, 56, 14);
		add(estadoLabel);
		
		// Label capacidad de camino en kg
		capacidadLabel.setBounds(25, 195, 177, 14);
		add(capacidadLabel);
		
		// TXT capacidad de ruta en kilogramos
		capacidadKgTxt.setBounds(204, 194, 116, 20);
		add(capacidadKgTxt);
		capacidadKgTxt.setColumns(10);
		
		// Label Tiempo de transito en min
		tiempTransitoLabel.setBounds(25, 245, 177, 14);
		add(tiempTransitoLabel);
		
		// TXT tiempo de transito en minutos
		tiemTransitoTxt.setBounds(204, 242, 116, 20);
		add(tiemTransitoTxt);
		tiemTransitoTxt.setColumns(10);
		
		//Combo box de Estado Ruta
		
		estadoComBox.setEditable(true);
		estadoComBox.setBounds(204, 155, 116, 22);
		add(estadoComBox);
				
		//label Error Sucursal Origen
		labelErrorSucursalO.setBounds(145, 93, 257, 14);;
		labelErrorSucursalO.setForeground(Color.RED);
		add(labelErrorSucursalO);
			
		//labelError Sucursal Destino
		labelErrorSD.setBounds(145, 132, 257, 14);
		labelErrorSD.setForeground(Color.RED);
		add(labelErrorSD);
				
		//Label Error Estado
		labelErrorEstado.setBounds(233, 152, 46, 14);
		labelErrorEstado.setForeground(Color.RED);
		add(labelErrorEstado);
				
		//Label Error Tiempo
		labelErrorTiempo.setBounds(126, 268, 316, 14);
		labelErrorTiempo.setForeground(Color.RED);
		add(labelErrorTiempo);
				
		//label Error Capacidad
		labelErrorCapacidad.setBounds(81, 220, 391, 14);
		labelErrorCapacidad.setForeground(Color.RED);
		add(labelErrorCapacidad);

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
						else  if(estadoS == "Seleccione")
							labelErrorEstado.setText("Por favor ingrese un estado para el camino");
				tiempo= Integer.parseInt(tiemTransitoTxt.getText());
				
				gestorCamino.modificarCamino(caminoSeleccionado.getId(), capacidad, tiempo, estado);
				
				
				}
			});
		botonGuardar.setBounds(285,293,157,23);
		add(botonGuardar);

		
		botonCancelar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null,
		                "¿Está seguro de que desea cancelar y volver?", 
		                "Confirmar Cancelar", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

		        if (respuesta == JOptionPane.YES_OPTION) {
		        	MenuPrincipal.mostrarPanel("ListadoCamino");
		        }
			}
		 
		 });
		//Boton Cancelar
		botonCancelar.setBounds(113,293,162,23);
		add(botonCancelar);
		
		
	}
	
	public void setCaminoSeleccionado(Camino c) {
		this.caminoSeleccionado = c;
	}
	
	 public void setPanelListadoCamino(JPanelListadoCamino panelListadoCamino) {
	    this.panelListadoCamino = panelListadoCamino;
	 }
	 
	 //Este metodo se encarga de setear los valores de la sucursal que se desea modificar
	 public void setCamposAModificar() {
		 idRutaTxt.setText(Integer.toString(caminoSeleccionado.getId()));
		 comboBoxSO.setSelectedItem(caminoSeleccionado.getSO().getNombre());
		 comboBoxSD.setSelectedItem(caminoSeleccionado.getSD());
		 estadoComBox.setSelectedItem(caminoSeleccionado.getEsOperativa());
		 capacidadKgTxt.setText(String.valueOf(caminoSeleccionado.getCapacidadMax()));
		 tiemTransitoTxt.setText(String.valueOf(caminoSeleccionado.getTiempoTransito()));
		 
		 
	 }


}
