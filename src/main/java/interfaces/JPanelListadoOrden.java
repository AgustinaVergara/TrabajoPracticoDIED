package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelListadoOrden extends JPanel {
	private JTable tableOrdenes;
	private JTable tableCaminos;

	/**
	 * Create the panel.
	 */
	public JPanelListadoOrden() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTADO DE ORDENES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(162, 10, 266, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Listado de órdenes");
		lblNewLabel_1.setBounds(46, 33, 126, 20);
		add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(164, 44, 399, 2);
		add(separator);
		
		JLabel lblNewLabel_1_1 = new JLabel("Caminos posibles");
		lblNewLabel_1_1.setBounds(46, 155, 126, 20);
		add(lblNewLabel_1_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(162, 165, 399, 2);
		add(separator_1);
		
		JScrollPane scrollPaneOrdenes = new JScrollPane();
		scrollPaneOrdenes.setBounds(20, 56, 570, 89);
		add(scrollPaneOrdenes);
		
		tableOrdenes = new JTable();
		scrollPaneOrdenes.setRowHeaderView(tableOrdenes);
		
		JScrollPane scrollPaneCaminosPosibles = new JScrollPane();
		scrollPaneCaminosPosibles.setBounds(20, 185, 570, 97);
		add(scrollPaneCaminosPosibles);
		
		tableCaminos = new JTable();
		scrollPaneCaminosPosibles.setRowHeaderView(tableCaminos);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("GestionarOrden");
			}
		});
		btnVolver.setBounds(262, 289, 85, 21);
		add(btnVolver);

	}

}
