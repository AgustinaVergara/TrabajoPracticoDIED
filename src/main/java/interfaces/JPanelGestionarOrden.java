package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import clases.OrdenDeProvision;
import clases.Sucursal;
import gestores.GestorOrden;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class JPanelGestionarOrden extends JPanel {
	
	private JPanelListadoOrden panelListadoOrden;
	private JPanelAltaOrden panelAltaOrden;
	
	private GestorOrden gestorOrden = GestorOrden.getInstance();

	/**
	 * Create the panel.
	 */
	public JPanelGestionarOrden() {
		setLayout(null);
		
		JLabel lblGestinDerdenes = new JLabel("GESTIÓN DE ÓRDENES DE PROVISIÓN");
		lblGestinDerdenes.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestinDerdenes.setBounds(165, 49, 248, 13);
		add(lblGestinDerdenes);
		
		JButton btnNuevaOrden = new JButton("+ Nueva orden de provisión");
		btnNuevaOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAltaOrden.actualizarSucursales();
				MenuPrincipal.mostrarPanel("AltaOrden");
			}
		});
		btnNuevaOrden.setBounds(169, 83, 248, 21);
		add(btnNuevaOrden);
		
		JButton btnListadoDeOrdenes = new JButton("Listado de ordenes");
		btnListadoDeOrdenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<OrdenDeProvision> listaOrdenes = gestorOrden.getOrdenes();
				panelListadoOrden.llenarTablaOrdenes(listaOrdenes);
				MenuPrincipal.mostrarPanel("ListadoOrden");
			}
		});
		btnListadoDeOrdenes.setBounds(169, 121, 248, 21);
		add(btnListadoDeOrdenes);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("Principal");
			}
		});
		btnVolver.setBounds(238, 207, 123, 21);
		add(btnVolver);

	}
	
	public void setPanelListadoOrden(JPanelListadoOrden panelListadoOrden) {
	    this.panelListadoOrden = panelListadoOrden;
	}
	
	public void setPanelAltaOrden(JPanelAltaOrden panelAltaOrden) {
	    this.panelAltaOrden = panelAltaOrden;
	}
}
