package interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import clases.Producto;
import excepciones.CampoVacioException;
import gestores.GestorProducto;

public class ModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtPeso;
	private Producto productoSelec;
	private ListarProducto ventanaListar; 
	private ListarProducto ListadoProd;
	private GestorProducto gestorProd = GestorProducto.getInstance();
	

	/**
	 * Create the frame.
	 */
	public ModificarProducto() {
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
		
		JLabel tituloModificarSucursal = new JLabel("MODIFICAR PRODUCTO");
		tituloModificarSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		tituloModificarSucursal.setBounds(132, 24, 190, 13);
		contentPane.add(tituloModificarSucursal);
		
		JLabel nombreSucursal = new JLabel("Nombre ");
		nombreSucursal.setBounds(57, 51, 96, 13);
		contentPane.add(nombreSucursal);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(182, 48, 160, 19);
		contentPane.add(txtNombre);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setBounds(58, 92, 114, 13);
		contentPane.add(descripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(182, 89, 160, 38);
		contentPane.add(txtDescripcion);
		
		JLabel Precio = new JLabel("Precio");
		Precio.setBounds(57, 151, 80, 13);
		contentPane.add(Precio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(182, 148, 160, 19);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel Peso = new JLabel("Peso KG");
		Peso.setBounds(57, 190, 45, 13);
		contentPane.add(Peso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(182, 187, 160, 19);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);
		
		JButton btnGuardarProducto = new JButton("Guardar");
		btnGuardarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
			    	try {
			    		campoVacio();
						int option = JOptionPane.showConfirmDialog(null, 
								"¿Estás seguro de que quieres guardar los cambios?", 
								"Confirmar modificación", JOptionPane.YES_NO_OPTION);
					    
					    if (option == JOptionPane.YES_OPTION) {
					    	gestorProd.modificarProducto(productoSelec.getidP(), txtNombre.getText(),
					    			txtDescripcion.getText(), Double.parseDouble(txtPrecio.getText()),
					    			Double.parseDouble(txtPeso.getText()));
				    	int result = JOptionPane.showConfirmDialog(null, "Producto modificada con éxito", "Confirmación", JOptionPane.DEFAULT_OPTION);
				    	
				    	
				        if (result == JOptionPane.OK_OPTION) {
				        	ListadoProd.llenarTabla(gestorProd.getProductos());
				        	ventanaListar.setVisible(true);
							dispose();
				        }
				        }
			    	}catch(CampoVacioException e1) {
						JOptionPane.showMessageDialog(frame,
								"Faltan completar los siguientes campos:\n\n"+e1.getMessage(),
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
			        
			    }
		});

		btnGuardarProducto.setBounds(214, 232, 85, 21);
		contentPane.add(btnGuardarProducto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar y volver?", 
		                "Confirmar Cancelar", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

		        if (respuesta == JOptionPane.YES_OPTION) {
					ventanaListar.setVisible(true);
					dispose();
		        }
			}
		});
		btnCancelar.setBounds(119, 232, 85, 21);
		contentPane.add(btnCancelar);
	}
	
	public void setProducto(Producto p) {
		this.productoSelec= p;
	}
	 
	public void setModificar() {
		 txtNombre.setText(productoSelec.getNombre());
		 txtDescripcion.setText(productoSelec.getDescripcion());
		 txtPrecio.setText(productoSelec.getPrecioUnitario().toString());
		 txtPeso.setText(productoSelec.getPrecioUnitario().toString());
	 }
	public void campoVacio() throws CampoVacioException{
		String error = "";
		boolean algunoVacio = false;
		
		if(txtNombre.getText().isEmpty()) {
			error += "- Nombre del producto\n";
			algunoVacio = true;
		}
		
		if(txtDescripcion.getText().isEmpty()) {
			error += "- Descripcion del producto\n";
			algunoVacio = true;
		}
		
		if(txtPrecio.getText().isEmpty()) {
			error += "- Precio unitario\n";
			algunoVacio = true;
		}
		if(txtPeso.getText().isEmpty()) {
			error += "- Peso en kg\n";
			algunoVacio = true;
		}
		
		if(algunoVacio) {
			
			throw new CampoVacioException(error);
		}
				
	}

}
