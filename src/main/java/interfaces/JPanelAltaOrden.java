package interfaces;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;

import clases.Producto;
import clases.Sucursal;
import gestores.GestorProducto;
import gestores.GestorSucursal;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class JPanelAltaOrden extends JPanel {
	
	private JTextField txtHora;
	private JTextField txtMinuto;
	private JTable tableProductos;
	private MyTableModel model;

	private List<Producto> listaProductos;
	private List<Sucursal> listaSucursales;
	private GestorProducto gestorProducto = GestorProducto.getInstance();
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	/**
	 * Create the panel.
	 */
	public JPanelAltaOrden() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREAR NUEVA ORDEN DE PROVISIÓN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(193, 22, 218, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha  (*)");
		lblNewLabel_1.setBounds(44, 48, 70, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sucursal destino (*)");
		lblNewLabel_2.setBounds(44, 80, 140, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tiempo máximo en hs (*)");
		lblNewLabel_3.setBounds(288, 80, 163, 13);
		add(lblNewLabel_3);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(313, 289, 98, 21);
		add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null,
		                "¿Está seguro de que desea cancelar y volver?", 
		                "Confirmar Cancelar", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

		        if (respuesta == JOptionPane.YES_OPTION) {
		        	vaciarCampos();
		        	MenuPrincipal.mostrarPanel("GestionarOrden");
		        }
			}
		});
		btnCancelar.setBounds(193, 289, 98, 21);
		add(btnCancelar);
		
		 // Crear un formato de fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		JFormattedTextField fechaTextField = new JFormattedTextField(formatoFecha);
		
		fechaTextField.setBounds(161, 45, 117, 19);
		add(fechaTextField);
		
		JComboBox<String> comboBoxSD = new JComboBox();
		comboBoxSD.setBounds(161, 72, 117, 21);
				
		listaSucursales = gestorSucursal.getSucursales();
		DefaultComboBoxModel<String> modeloSD;
        modeloSD = new DefaultComboBoxModel<>();
        modeloSD.addElement("Seleccione");
        for(Sucursal s : listaSucursales) {
        	modeloSD.addElement(s.getNombre());
        }
        comboBoxSD.setModel(modeloSD);
        add(comboBoxSD);
        
		txtHora = new JTextField();
		txtHora.setBounds(440, 77, 56, 19);
		add(txtHora);
		txtHora.setColumns(10);
		
		txtMinuto = new JTextField();
		txtMinuto.setColumns(10);
		txtMinuto.setBounds(511, 77, 56, 19);
		add(txtMinuto);
		
		JLabel lblNewLabel_4 = new JLabel(":");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(494, 80, 20, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Lista de productos");
		lblNewLabel_5.setBounds(44, 113, 127, 21);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Producto");
		lblNewLabel_6.setBounds(64, 147, 70, 13);
		add(lblNewLabel_6);
		
		listaProductos = gestorProducto.getProductos();
		JComboBox<String> comboBoxProducto = new JComboBox();
		comboBoxProducto.setBounds(144, 143, 117, 21);
		DefaultComboBoxModel<String> modeloProducto;
        modeloProducto = new DefaultComboBoxModel<>();
        modeloProducto.addElement("Seleccione");
        for(Producto p : listaProductos) {
        	modeloProducto.addElement(p.getNombre());
        }
        comboBoxProducto.setModel(modeloProducto);
		add(comboBoxProducto);
		
		JLabel lblNewLabel_7 = new JLabel("Cantidad");
		lblNewLabel_7.setBounds(280, 147, 62, 13);
		add(lblNewLabel_7);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinner.setBounds(352, 144, 48, 20);
		add(spinner);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.setBounds(463, 143, 85, 21);
		add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(161, 124, 406, 2);
		add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 181, 523, 93);
		add(scrollPane);
		
		tableProductos = new JTable();
		scrollPane.setRowHeaderView(tableProductos);
		tableProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tableProductos.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY()/tableProductos.getRowHeight();
				
				if(row < tableProductos.getRowCount() && row >= 0 && column < tableProductos.getColumnCount() && column >=0) {
					Object value = tableProductos.getValueAt(row, column);
					if(value instanceof JButton) {
						((JButton)value).doClick();
						JButton boton = (JButton) value;
						if(boton.getName().equals("Eliminar")) {
							Producto p = gestorProducto.getProductos().get(row);
							 // Mostrar ventana de confirmación
						    int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar la sucursal?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
						    
						    if (option == JOptionPane.YES_OPTION) {
						        // El usuario confirmó eliminar la sucursal
						        // Armar codigo para sacar ese producto de la lista que se muestra pero no de la bd						    
						    }
						}	
					}
				}
			}
		});
		
		//Defino mi propio default table model para cargar los datos en la tabla
		model = new MyTableModel();
		tableProductos.setDefaultRenderer(Object.class, new RenderTabla()); //renderizar tabla
		tableProductos.setModel(model);
		
		model.addColumn("Id");
		model.addColumn("Nombre");
		model.addColumn("Precio unitario");
		model.addColumn("Peso en Kg");
		model.addColumn("Eliminar");
		
		tableProductos.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableProductos.getColumnModel().getColumn(1).setMinWidth(95);
		tableProductos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableProductos.getColumnModel().getColumn(2).setMinWidth(80);
		scrollPane.setViewportView(tableProductos);

	}
	
	public void vaciarCampos () {
		
	}
}
