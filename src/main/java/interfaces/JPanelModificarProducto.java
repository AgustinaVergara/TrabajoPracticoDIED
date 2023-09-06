package interfaces;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Producto;
import clases.Sucursal;
import excepciones.CampoVacioException;
import gestores.GestorProducto;

public class JPanelModificarProducto extends JPanel {


	private JPanelListadoProducto panelListadoProducto;

	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtPeso;
	private Producto productoSeleccionado;

	private GestorProducto gestorProducto = GestorProducto.getInstance();
	/**
	 * Create the panel.
	 */
	public JPanelModificarProducto() {
		
setLayout(null);
		
		JLabel tituloModificarSucursal = new JLabel("MODIFICAR PRODUCTO");
		tituloModificarSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		tituloModificarSucursal.setBounds(132, 24, 190, 13);
		add(tituloModificarSucursal);
		
		JLabel nombreSucursal = new JLabel("Nombre ");
		nombreSucursal.setBounds(57, 51, 96, 13);
		add(nombreSucursal);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(200, 47, 160, 19);
		add(txtNombre);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setBounds(58, 92, 114, 13);
		add(descripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(200, 88, 160, 38);
		add(txtDescripcion);
		
		JLabel Precio = new JLabel("Precio");
		Precio.setBounds(57, 151, 80, 13);
		add(Precio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(200, 147, 160, 19);
		add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel Peso = new JLabel("Peso KG");
		Peso.setBounds(57, 190, 80, 13);
		add(Peso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(200, 186, 160, 19);
		add(txtPeso);
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
					    	gestorProducto.modificarProducto(productoSeleccionado.getidP(), txtNombre.getText(),
					    			txtDescripcion.getText(), Double.parseDouble(txtPrecio.getText()),
					    			Double.parseDouble(txtPeso.getText()));
				    	int result = JOptionPane.showConfirmDialog(null, "Producto modificado con éxito", "Confirmación", JOptionPane.DEFAULT_OPTION);
				    	
				    	
				        if (result == JOptionPane.OK_OPTION) {
				        	panelListadoProducto.llenarTabla(gestorProducto.getProductos());				        
				        	MenuPrincipal.mostrarPanel("ListadoProducto");
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
		add(btnGuardarProducto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar y volver?", 
		                "Confirmar Cancelar", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

		        if (respuesta == JOptionPane.YES_OPTION) {
					MenuPrincipal.mostrarPanel("ListadoProducto");
		        }
			}
		});
		btnCancelar.setBounds(119, 232, 85, 21);
		add(btnCancelar);

	}

	public void setProductoSeleccionado(Producto p) {
		this.productoSeleccionado = p;
	}

	public void setPanelListadoProducto(JPanelListadoProducto panelListadoProducto) {
		this.panelListadoProducto = panelListadoProducto;
	}

	public void setCamposAModificar() {
		txtNombre.setText(productoSeleccionado.getNombre());
		txtDescripcion.setText(productoSeleccionado.getDescripcion());
		txtPrecio.setText(productoSeleccionado.getPrecioUnitario().toString());
		txtPeso.setText(productoSeleccionado.getPrecioUnitario().toString());
	}
	
	public void campoVacio() throws CampoVacioException {
		String error = "";
		boolean algunoVacio = false;

		if (txtNombre.getText().isEmpty()) {
			error += "- Nombre del producto\n";
			algunoVacio = true;
		}

		if (txtDescripcion.getText().isEmpty()) {
			error += "- Descripcion del producto\n";
			algunoVacio = true;
		}

		if (txtPrecio.getText().isEmpty()) {
			error += "- Precio unitario\n";
			algunoVacio = true;
		}
		if (txtPeso.getText().isEmpty()) {
			error += "- Peso en kg\n";
			algunoVacio = true;
		}

		if (algunoVacio) {

			throw new CampoVacioException(error);
		}

	}
}
