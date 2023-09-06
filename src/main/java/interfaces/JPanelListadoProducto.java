package interfaces;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Producto;
import clases.Sucursal;
import gestores.GestorProducto;

public class JPanelListadoProducto extends JPanel {
	
	private JTable tableProductos;
	private MyTableModel model;
	
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtPeso;
	private List<Producto> listaProductos;
	
	private GestorProducto gestorProducto = GestorProducto.getInstance();
	
	private JPanelModificarProducto panelModificarProducto;

	/**
	 * Create the panel.
	 */
	public JPanelListadoProducto() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTADO DE PRODUCTOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(199, 22, 198, 13);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 580, 100);
		add(scrollPane);
		
		tableProductos = new JTable();
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
							Producto p =  gestorProducto.getProductos().get(row); 
							
						    int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
						    
						    if (option == JOptionPane.YES_OPTION) {
						        gestorProducto.eliminarProducto(p);
						        model.setRowCount(0);
						        System.out.println(gestorProducto.getProductos());
						        llenarTabla(gestorProducto.getProductos());						    
						    }
						}
						if(boton.getName().equals("Modificar")) {
							List<Producto> productosEnTabla = gestorProducto.getProductos(); //deberia agregar un filtrado
							Producto p = productosEnTabla.get(row);
							panelModificarProducto.setProductoSeleccionado(p);
							panelModificarProducto.setCamposAModificar();
							MenuPrincipal.mostrarPanel("ModificarProducto");
						}
						
					}
				}
			}
		});	
		
		model = new MyTableModel();
		tableProductos.setDefaultRenderer(Object.class, new RenderTabla()); 
		tableProductos.setModel(model);
		
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		model.addColumn("Precio Unitario");
		model.addColumn("Peso KG");
		model.addColumn("Modificar");
		model.addColumn("Eliminar");
		
		tableProductos.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableProductos.getColumnModel().getColumn(1).setMinWidth(95);
		tableProductos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableProductos.getColumnModel().getColumn(2).setMinWidth(80);
		scrollPane.setViewportView(tableProductos);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0); 
				MenuPrincipal.mostrarPanel("GestionarProducto");
			}
		});
		btnVolver.setBounds(248, 269, 86, 21);
		add(btnVolver);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setBounds(51, 49, 95, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(51, 75, 86, 13);
		add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(148, 72, 96, 19);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel precioUnitario = new JLabel("Precio Unitario:");
		precioUnitario.setBounds(51, 104, 95, 13);
		add(precioUnitario);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(148, 101, 96, 19);
		add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel pesoKG = new JLabel("Peso KG:");
		pesoKG.setBounds(337, 72, 71, 13);
		add(pesoKG);
		
		
		txtPeso = new JTextField();
		txtPeso.setBounds(413, 69, 96, 19);
		add(txtPeso);
		txtPeso.setColumns(10);
		
		JButton btnAplicarFiltros = new JButton("Aplicar  filtros");
		btnAplicarFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Producto> listaFiltrarProducto = new ArrayList<>();
				
				if (!(txtNombre.getText().isEmpty())) {
					String nom = txtNombre.getText();
					listaFiltrarProducto = gestorProducto.buscarProductoNombre(nom);
				}
				if (!(txtPrecio.getText().isEmpty())) {
					double precio = Double.parseDouble(txtPrecio.getText());
					listaFiltrarProducto = gestorProducto.buscarProductoPrecio(precio);
				}
				if (!(txtPeso.getText().isEmpty())) {
					double peso = Double.parseDouble(txtPeso.getText());
					listaFiltrarProducto = gestorProducto.buscarProductoPeso(peso);
				}
				model.setRowCount(0);
				llenarTabla(listaFiltrarProducto);
			}
		});
		btnAplicarFiltros.setBounds(214, 128, 142, 21);
		add(btnAplicarFiltros);

	}
	
	public void setPanelModificarProducto(JPanelModificarProducto panelModificarProducto) {
	    this.panelModificarProducto = panelModificarProducto;
	}
	
	public void llenarTabla(List<Producto> listaP) {
		model.setRowCount(0);

		
		for(Producto p : listaP) {
			Object[] fila = new Object[6];//columnas
			
			fila[0] = p.getNombre();
			fila[1] = p.getDescripcion();
			fila[2] = p.getPrecioUnitario();
			fila[3] = p.getPesoKg();
			
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setName("Eliminar");
			JButton btnModificar = new JButton("Modificar");
			btnModificar.setName("Modificar");
			
			fila[4] = btnModificar; 
			fila[5] = btnEliminar;
			
			model.addRow(fila);
		}
	}

}
