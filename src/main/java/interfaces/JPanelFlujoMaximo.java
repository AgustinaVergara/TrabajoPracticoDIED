package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gestores.GestorGrafo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelFlujoMaximo extends JPanel {
	
	private GestorGrafo gestorGrafo = GestorGrafo.getInstance();
	

	/**
	 * Create the panel.
	 */
	public JPanelFlujoMaximo() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FLUJO MÁXIMO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(220, 10, 142, 13);
		add(lblNewLabel);
		
		//gestorGrafo.flujoMaximo();
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("Principal");
			}
		});
		btnNewButton.setBounds(250, 289, 85, 21);
		add(btnNewButton);

	}
}
