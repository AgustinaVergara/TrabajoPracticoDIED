package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import clases.Producto;
import clases.Sucursal;
import gestores.GestorProducto;

public class JPanelGestionarProducto extends JPanel {
	
	
	private JPanelListadoProducto panelListadoProducto;
	
	private GestorProducto gestorProducto = GestorProducto.getInstance();

	/**
	 * Create the panel.
	 */
	public JPanelGestionarProducto() {
		
		setLayout(null);
		
		JButton btnAltaProducto = new JButton("+ Nuevo Producto");
		btnAltaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("AltaProducto");
			}
		});
		btnAltaProducto.setBounds(164, 96, 248, 21);
		add(btnAltaProducto);
	
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("Principal");
			}
		});
		botonVolver.setBounds(252, 204, 85, 21);
		add(botonVolver);
		
		JButton btnListarProducto = new JButton("Listado de productos");
		btnListarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Producto> listaProductos = gestorProducto.getProductos();
				panelListadoProducto.llenarTabla(listaProductos);
				MenuPrincipal.mostrarPanel("ListadoProducto");
				
			}
		});
		btnListarProducto.setBounds(164, 137, 248, 21);
		add(btnListarProducto);
		
		JLabel lblNewLabel = new JLabel("GESTIÓN DE PRODUCTOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(164, 54, 239, 13);
		add(lblNewLabel);

	}
	
	public void setPanelListadoProducto(JPanelListadoProducto panelListadoProducto) {
	    this.panelListadoProducto = panelListadoProducto;
	}

}
