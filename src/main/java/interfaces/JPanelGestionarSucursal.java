package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import clases.Sucursal;
import gestores.GestorSucursal;

public class JPanelGestionarSucursal extends JPanel {
	
	private JPanelListadoSucursal panelListadoSucursal;

	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	
	/**
	 * Create the panel.
	 */
	public JPanelGestionarSucursal() {
		
		JButton btnAltaSucursal = new JButton("+ Nueva sucursal");
		btnAltaSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("AltaSucursal");
			}
		});
		setLayout(null);
		btnAltaSucursal.setBounds(180, 89, 248, 21);
		add(btnAltaSucursal);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("Principal");
			}
		});
		btnVolver.setBounds(238, 213, 123, 21);
		add(btnVolver);
				
		JButton btnListadoDeSucursales = new JButton("Listado de sucursales");
		btnListadoDeSucursales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Sucursal> listaSucursales = gestorSucursal.getSucursales();
				panelListadoSucursal.llenarTabla(listaSucursales);
				MenuPrincipal.mostrarPanel("ListadoSucursal");
			}
		});
		btnListadoDeSucursales.setBounds(180, 127, 248, 21);
		add(btnListadoDeSucursales);
		
		JLabel lblNewLabel = new JLabel("GESTIÓN DE SUCURSALES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(176, 55, 248, 13);
		add(lblNewLabel);

	}
	
	public void setPanelListadoSucursal(JPanelListadoSucursal panelListadoSucursal) {
	    this.panelListadoSucursal = panelListadoSucursal;
	}
}
