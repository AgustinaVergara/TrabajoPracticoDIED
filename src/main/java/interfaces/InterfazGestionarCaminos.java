package interfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestores.GestorCamino;
import gestores.GestorSucursal;

public class InterfazGestionarCaminos extends JFrame {

	private JPanel contentPane;
	private GestorCamino gestorCamino = GestorCamino.getInstance();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazGestionarCaminos frame = new InterfazGestionarCaminos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazGestionarCaminos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setBounds(181, 203, 85, 21);
		contentPane.add(btnNewButton);
		
		final VentanaModificarCamino ventanaListadoCamino = new VentanaModificarCamino(this);
		JButton btnListadoDeCaminos = new JButton("Listado de caminos");
		btnListadoDeCaminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ventanaListadoCamino.llenarTabla(gestorCamino.getCaminos());
				ventanaListadoCamino.setVisible(true);
				setVisible(false);
			}
		});
		btnListadoDeCaminos.setBounds(96, 129, 248, 21);
		contentPane.add(btnListadoDeCaminos);
		
		final VentanaCamino ventanaAltaCaminos = new VentanaCamino();
		JButton btnNuevoCamino = new JButton("+ Nuevo Camino");
		btnNuevoCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaAltaCaminos.setVisible(true);
				setVisible(false);
			}
		});
		btnNuevoCamino.setBounds(96, 64, 248, 23);
		contentPane.add(btnNuevoCamino);
		
		
		
		
	}
}
