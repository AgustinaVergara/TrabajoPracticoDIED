package interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import clases.Sucursal;

import javax.swing.JButton;

public class JPanelPrincipal extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public JPanelPrincipal() {
        setLayout(null);
        
        JLabel tituloMenuPrincipal = new JLabel("SISTEMA DE GESTIÓN LOGÍSTICO");
		tituloMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 14));
		tituloMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER); 
		tituloMenuPrincipal.setBounds(188, 33, 246, 17);
		add(tituloMenuPrincipal);
		
		JLabel lblNewLabel = new JLabel("Trabajo Práctico Integrador realizado por:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(195, 239, 251, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("María Victoria Bertero, Valentina Ducasse y Agustina Vergara");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(131, 262, 359, 13);
		add(lblNewLabel_1);
		
		JButton btnGestionarSucursal = new JButton("Gestionar sucursal");
		btnGestionarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("GestionarSucursal");
			}
		});
		btnGestionarSucursal.setBounds(79, 85, 202, 33);
		add(btnGestionarSucursal);

		JButton btnGestionarCaminos = new JButton("Gestionar caminos");
		btnGestionarCaminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("GestionarCamino");
			}
		});
		btnGestionarCaminos.setBounds(311, 85, 202, 33);
		add(btnGestionarCaminos);
		
		JButton btnGestionarProductos = new JButton("Gestionar productos");
		btnGestionarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("GestionarProducto");
			}
		});
		btnGestionarProductos.setBounds(79, 134, 202, 33);
		add(btnGestionarProductos);
		
		JButton btnGestionarOrdenes = new JButton("Gestionar órdenes");
		btnGestionarOrdenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("GestionarOrden");
			}
		});
		btnGestionarOrdenes.setBounds(311, 134, 202, 33);
		add(btnGestionarOrdenes);
		
		JButton btnFlujoMaximo = new JButton("Flujo máximo");
		btnFlujoMaximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFlujoMaximo.setBounds(79, 181, 202, 33);
		add(btnFlujoMaximo);
		
		JButton btnPageRank = new JButton("Page rank");
		btnPageRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPageRank.setBounds(311, 181, 202, 33);
		add(btnPageRank);
	}
}
