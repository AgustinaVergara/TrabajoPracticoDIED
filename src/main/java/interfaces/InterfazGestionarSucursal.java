package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestores.GestorSucursal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazGestionarSucursal extends JFrame {

	private JPanel contentPane;
	
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();

	/**
	 * Create the frame.
	 */
	public InterfazGestionarSucursal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final InterfazAltaSucursal ventanaAltaSucursal = new InterfazAltaSucursal();
		JButton btnAltaSucursal = new JButton("+ Nueva sucursal");
		btnAltaSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaAltaSucursal.setVisible(true);
				setVisible(false);
			}
		});
		btnAltaSucursal.setBounds(100, 50, 248, 21);
		contentPane.add(btnAltaSucursal);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setBounds(181, 203, 85, 21);
		contentPane.add(btnNewButton);
		
		final InterfazListadoSucursal ventanaListadoSucursal = new InterfazListadoSucursal(this);
		JButton btnListadoDeSucursales = new JButton("Listado de sucursales");
		btnListadoDeSucursales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListadoSucursal.llenarTabla(gestorSucursal.getSucursales());
				ventanaListadoSucursal.setVisible(true);
				setVisible(false);
			}
		});
		btnListadoDeSucursales.setBounds(100, 91, 248, 21);
		contentPane.add(btnListadoDeSucursales);
	}
}
