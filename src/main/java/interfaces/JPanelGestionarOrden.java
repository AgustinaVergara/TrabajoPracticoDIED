package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelGestionarOrden extends JPanel {

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
				MenuPrincipal.mostrarPanel("AltaOrden");
			}
		});
		btnNuevaOrden.setBounds(169, 83, 248, 21);
		add(btnNuevaOrden);
		
		JButton btnListadoDeOrdenes = new JButton("Listado de ordenes");
		btnListadoDeOrdenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
}
