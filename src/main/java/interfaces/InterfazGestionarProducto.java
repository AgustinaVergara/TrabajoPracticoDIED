package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestores.GestorProducto;

public class InterfazGestionarProducto extends JFrame {

	private JPanel contentPane;
	private MenuPrincipal ventanaMenu;
	private GestorProducto gestorProducto = GestorProducto.getInstance();

	/**
	 * Create the frame.
	 */
	public InterfazGestionarProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final AltaProducto ventanaAltaProducto = new AltaProducto();
		JButton btnAltaProducto = new JButton("+ Nueva Producto");
		btnAltaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaAltaProducto.setVisible(true);
				setVisible(false);
			}
		});
		btnAltaProducto.setBounds(100, 50, 248, 21);
		contentPane.add(btnAltaProducto);
	
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ventanaMenu.setVisible(true);
			}
		});
		botonVolver.setBounds(181, 203, 85, 21);
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
		btnListarProducto.setBounds(100, 91, 248, 21);
		contentPane.add(btnListarProducto);
	}
}