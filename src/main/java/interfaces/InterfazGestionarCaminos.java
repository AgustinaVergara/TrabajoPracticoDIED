package interfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Camino;
import gestores.GestorCamino;
import gestores.GestorSucursal;
import dao.*;
public class InterfazGestionarCaminos extends JFrame {

	private JPanel contentPane;
	//private GestorCamino gestorCamino = GestorCamino.getInstance();
	private CaminoDao caminoDAO;//= new CaminoSQLimplementacion();
	//private Camino caminos;
	private List<Camino> listacaminos;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public InterfazGestionarCaminos() {
		caminoDAO = new CaminoSQLimplementacion();
		listacaminos = caminoDAO.buscarCaminos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setBounds(181, 203, 85, 21);
		contentPane.add(btnNewButton);
		
		final InterfazListadoCamino ventanaListadoCamino = new InterfazListadoCamino(this);
		JButton btnListadoDeCaminos = new JButton("Listado de caminos");
		btnListadoDeCaminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListadoCamino.llenarTabla(caminoDAO.buscarCaminos());
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
