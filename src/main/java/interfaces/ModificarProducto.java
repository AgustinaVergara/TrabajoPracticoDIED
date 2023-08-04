package interfaces;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtPeso;
	

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
		btnGuardarProducto.setBounds(214, 232, 85, 21);
		contentPane.add(btnGuardarProducto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(119, 232, 85, 21);
		contentPane.add(btnCancelar);

		
	}

}
