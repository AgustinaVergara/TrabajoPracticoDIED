package interfaces;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Camino;
import clases.Grafo;
import gestores.GestorCamino;
import gestores.GestorSucursal;
import dao.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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
	public InterfazGestionarCaminos(MenuPrincipal ventanaMenu) {
		caminoDAO = new CaminoSQLimplementacion();
		listacaminos = caminoDAO.buscarCaminos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Tamaño deseado para el JFrame
        int width = 600;
        int height = 400;
        
        // Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        // Calculamos las coordenadas (x, y) para centrar el JFrame
        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;
        
        // Establecemos las coordenadas y el tamaño
        setBounds(x, y, width, height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaMenu.setVisible(true);
				dispose();
				//ventanaGestionarCaminos.setVisible(true);
			}
		});
		btnVolver.setBounds(254, 270, 85, 21);
		contentPane.add(btnVolver);
		
		final InterfazListadoCamino ventanaListadoCamino = new InterfazListadoCamino(this);
		JButton btnListadoDeCaminos = new JButton("Listado de caminos");
		btnListadoDeCaminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListadoCamino.llenarTabla(caminoDAO.buscarCaminos());
				ventanaListadoCamino.setVisible(true);
				setVisible(false);
			}
		});
		btnListadoDeCaminos.setBounds(166, 143, 248, 21);
		contentPane.add(btnListadoDeCaminos);
		
		final VentanaCamino ventanaAltaCaminos = new VentanaCamino(this);
		JButton btnNuevoCamino = new JButton("+ Nuevo Camino");
		btnNuevoCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaAltaCaminos.setVisible(true);
				setVisible(false);
			}
		});
		btnNuevoCamino.setBounds(166, 78, 248, 23);
		contentPane.add(btnNuevoCamino);	
		
		JLabel lblNewLabel = new JLabel("GESTIÓN DE CAMINOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(166, 38, 248, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnVerGrafos = new JButton("Ver Grafo");
		btnVerGrafos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grafo g = new Grafo();
				//JPanelGrafo jPanelGrafo = new JPanelGrafo();
				//jPanelGrafo.setVisible(true);
		        SwingUtilities.invokeLater(() -> {
		        	JPanelGrafo graphDrawing = new JPanelGrafo();
		            graphDrawing.setVisible(true);
		        });
				//JPanelGrafo jPanelGrafo;// = new JPanelGrafo(g);
			//	jPanelGrafo.setVisible(true);;
				//setVisible(false);
			}
		});
		btnVerGrafos.setBounds(166, 213, 248, 21);
		contentPane.add(btnVerGrafos);
		
	}
}
