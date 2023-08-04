package interfaces;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestores.GestorProducto;
import clases.Producto;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class InterfazGestionarProducto extends JFrame {

	private JPanel contentPane;
	private MenuPrincipal ventanaMenu;
	private GestorProducto gestorProducto = GestorProducto.getInstance();

	/**
	 * Create the frame.
	 */
	public InterfazGestionarProducto(MenuPrincipal ventanaMenu) {
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
		
		final AltaProducto ventanaAltaProducto = new AltaProducto(this);
		JButton btnAltaProducto = new JButton("+ Nueva Producto");
		btnAltaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaAltaProducto.setVisible(true);
				setVisible(false);
			}
		});
		btnAltaProducto.setBounds(164, 96, 248, 21);
		contentPane.add(btnAltaProducto);
	
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaMenu.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(252, 204, 85, 21);
		contentPane.add(botonVolver);
		
		final ListarProducto ventanaListadoProducto = new ListarProducto(this);
		JButton btnListarProducto = new JButton("Listado de productos");
		btnListarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListadoProducto.llenarTabla(gestorProducto.getProductos());
				ventanaListadoProducto.setVisible(true);
				setVisible(false);
			}
		});
		btnListarProducto.setBounds(164, 137, 248, 21);
		contentPane.add(btnListarProducto);
		
		JLabel lblNewLabel = new JLabel("GESTIÓN DE PRODUCTOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(164, 54, 239, 13);
		contentPane.add(lblNewLabel);
	}
}