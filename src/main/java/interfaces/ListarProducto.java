package interfaces;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import gestores.GestorProducto;

public class ListarProducto extends JFrame {

	private JPanel contentPane;
	private JTable tableProductos;
	private MyTableModel model;
	
	private InterfazGestionarProducto ventanaGestionarProducto;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtPeso;
	private List<Producto> listaProductos;
	
	private GestorProducto gestorProducto = GestorProducto.getInstance();

	/**
	 * Create the frame.
	 */
	public ListarProducto(InterfazGestionarProducto ventanaGestionarProducto) {
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
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTADO DE PRODUCTOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(197, 23, 196, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 196, 571, 124);
		contentPane.add(scrollPane);
		
		tableProductos = new JTable();
		scrollPane.add(tableProductos);
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
							Producto p = listaProductos.get(row); 
							
						    int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
						    
						    if (option == JOptionPane.YES_OPTION) {
						        gestorProducto.eliminarProducto(p);
						        model.setRowCount(0);
						        System.out.println(gestorProducto.getProductos());
						        llenarTabla(gestorProducto.getProductos());						    
						    }
						}
						if(boton.getName().equals("Modificar")) {
							final ModificarProducto ventanaModificarProducto = new ModificarProducto();
							ventanaModificarProducto.setVisible(true); 
							setVisible(false);
							System.out.println("modificar");
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
				dispose();
				model.setRowCount(0); //Limpiar tabla
				ventanaGestionarProducto.setVisible(true);
			}
		});
		btnVolver.setBounds(259, 330, 85, 21);
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setBounds(97, 46, 79, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(97, 69, 64, 13);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(171, 69, 138, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel precioUnitario = new JLabel("Precio Unitario:");
		precioUnitario.setBounds(97, 106, 70, 13);
		contentPane.add(precioUnitario);
		
		JLabel pesoKG = new JLabel("Peso KG:");
		pesoKG.setBounds(97, 136, 51, 13);
		contentPane.add(pesoKG);
		
		JButton btnAplicarFiltros = new JButton("Aplicar  filtros");
		btnAplicarFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//hacer esto
			}
		});
		btnAplicarFiltros.setBounds(247, 165, 113, 21);
		contentPane.add(btnAplicarFiltros);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(171, 103, 96, 19);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(171, 133, 96, 19);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);
	}
	
	public void llenarTabla(List<Producto> listaProductos) {
		
		this.listaProductos = listaProductos;
		
		for(Producto p : listaProductos) {
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
