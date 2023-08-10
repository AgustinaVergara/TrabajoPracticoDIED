package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class JPanelAltaOrden extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelAltaOrden() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREAR NUEVA ORDEN DE PROVISIÓN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 31, 218, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha  (*)");
		lblNewLabel_1.setBounds(85, 72, 70, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sucursal destino (*)");
		lblNewLabel_2.setBounds(85, 108, 117, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tiempo máximo en hs (*)");
		lblNewLabel_3.setBounds(85, 148, 125, 13);
		add(lblNewLabel_3);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(313, 243, 98, 21);
		add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(180, 243, 98, 21);
		add(btnCancelar);

	}
}
