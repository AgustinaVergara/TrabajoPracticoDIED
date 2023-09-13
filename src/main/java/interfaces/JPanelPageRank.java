package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelPageRank extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelPageRank() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PAGE RANK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(223, 10, 132, 13);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("Principal");
			}
		});
		btnNewButton.setBounds(257, 289, 85, 21);
		add(btnNewButton);

	}

}
