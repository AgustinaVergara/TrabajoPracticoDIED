package interfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.*;

import javax.swing.*;

import clases.ItemProducto;
import clases.OrdenDeProvision;
import clases.Producto;
import clases.Sucursal;
import gestores.GestorOrden;
import gestores.GestorProducto;
import gestores.GestorSucursal;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class JPanelAltaOrden extends JPanel {
	
	private JFormattedTextField fechaTextField;
	private JTextField txtHora;
	private JTextField txtMinuto;
	private JComboBox<String> comboBoxSD;
	private DefaultComboBoxModel<String> modeloSD;
	private JComboBox<String> comboBoxProducto;
	private DefaultComboBoxModel<String> modeloProducto;
	private JSpinner spinner;
	private JTable tableProductos;
	private MyTableModel model;

	private List<Producto> listaProductos;
	private List<Sucursal> listaSucursales;
	private List<ItemProducto> productosDeOrden;
	private GestorProducto gestorProducto = GestorProducto.getInstance();
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	private GestorOrden gestorOrden = GestorOrden.getInstance();
	/**
	 * Create the panel.
	 */
	public JPanelAltaOrden() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREAR NUEVA ORDEN DE PROVISIÓN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(193, 22, 258, 13);
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
		
		
		 // Crear un formato de fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		fechaTextField = new JFormattedTextField(formatoFecha);
		
		fechaTextField.setBounds(161, 45, 117, 19);
		add(fechaTextField);
		
		comboBoxSD = new JComboBox<String>();
		comboBoxSD.setBounds(161, 72, 117, 21);
				
		listaSucursales = gestorSucursal.getSucursales();
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
		comboBoxProducto = new JComboBox<String>();
		comboBoxProducto.setBounds(144, 143, 117, 21);
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
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinner.setBounds(352, 144, 48, 20);
		add(spinner);
		
		productosDeOrden = new ArrayList<>();
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto p = gestorProducto.getProdId(comboBoxProducto.getSelectedIndex());
				productosDeOrden.add(new ItemProducto (p,(Integer) spinner.getValue()));
				agregarProductoATabla(p,(Integer) spinner.getValue());
			}
		});
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
							// Iterar sobre la lista productosDeOrden y buscar el elemento que coincide con el nombre del producto p
							for (ItemProducto i : productosDeOrden) {
							    if (i.getProducto().getNombre().equals(p.getNombre())) {
							        productosDeOrden.remove(i); // Eliminar el elemento de la lista
							        break;
							    }
							}
							actualizarTablaProducto();
						    				    
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
		model.addColumn("Cantidad");
		model.addColumn("Eliminar");
		
		tableProductos.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableProductos.getColumnModel().getColumn(1).setMinWidth(95);
		tableProductos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableProductos.getColumnModel().getColumn(2).setMinWidth(80);
		scrollPane.setViewportView(tableProductos);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				//Falta agregar un try catch paralas validaciones de la orden
				if(productosDeOrden.size() == 0) {
					JOptionPane.showMessageDialog(frame, "Agregue al menos un producto a la orden", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}else {
					try {
					    // Parsea la cadena a un objeto Date utilizando el formato
					    Date fechaParseada = formatoFecha.parse(fechaTextField.getText());

					    // Convierte el objeto Date a un objeto LocalDate (a partir de Java 8)
					    LocalDate fechaLocalDate = fechaParseada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					    
					    gestorOrden.crearOrdenGestor(
								fechaLocalDate,
								(gestorSucursal.buscarSucursalxNombre((String) comboBoxSD.getSelectedItem())).getId(),
								LocalTime.of(Integer.parseInt(txtHora.getText()), Integer.parseInt(txtMinuto.getText())),
								productosDeOrden,
								enums.EstadoOrden.PENDIENTE);
						
						//gestorOrden.agregarOrden(nuevaOrden);
						JOptionPane.showMessageDialog(frame, "Orden creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						vaciarCampos();
						productosDeOrden.clear();
						MenuPrincipal.mostrarPanel("GestionarOrden");
						
					} catch (ParseException e1) {
					    // Manejar una excepción si la entrada no coincide con el formato esperado
					    e1.printStackTrace();
					}
					
				}
				
			}
		});
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
		        	model.setRowCount(0);
		        	productosDeOrden.clear();
		        	MenuPrincipal.mostrarPanel("GestionarOrden");
		        }
			}
		});
		btnCancelar.setBounds(193, 289, 98, 21);
		add(btnCancelar);

	}
	
	public void vaciarCampos () {
		
		fechaTextField.setText("");
		txtHora.setText("");
		txtMinuto.setText("");
		modeloSD.setSelectedItem("Seleccione");
		modeloProducto.setSelectedItem("Seleccione");
		spinner.setValue(1);
		
	}
	
	public void agregarProductoATabla(Producto p, Integer cantidad) {
	
		
			Object[] fila = new Object[6];
			
			fila[0] = p.getidP();
			fila[1] = p.getNombre();
			fila[2] = p.getPrecioUnitario();
			fila[3] = p.getPesoKg();
			fila[4] = cantidad;
			
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setName("Eliminar");
		
			
			fila[5] = btnEliminar;
			
			model.addRow(fila);	
	}
	
	public void actualizarTablaProducto() {
		model.setRowCount(0);
		
		for(ItemProducto p : productosDeOrden) {
			Object[] fila = new Object[6];
			
			fila[0] = p.getProducto().getidP();
			fila[1] = p.getProducto().getNombre();
			fila[2] = p.getProducto().getPrecioUnitario();
			fila[3] = p.getProducto().getPesoKg();
			fila[4] = p.getCantidad();
			
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setName("Eliminar");
		
			
			fila[5] = btnEliminar;
			
			model.addRow(fila);	
		}

	}
}
