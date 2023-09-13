package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import clases.Camino;
import clases.Sucursal;
import dao.CaminoDao;
import dao.CaminoSQLimplementacion;
import gestores.GestorCamino;


public class JPanelGestionarCamino extends JPanel {
	
	private CaminoDao caminoDAO;
	private List<Camino> listaCaminos;

	
	private GestorCamino gestorCamino = GestorCamino.getInstance();
	
	private JPanelAltaCamino panelAltaCamino;

	private JPanelListadoCamino panelListadoCamino;

	/**
	 * Create the panel.
	 */
	public JPanelGestionarCamino() {
		caminoDAO = new CaminoSQLimplementacion();
		//listaCaminos = caminoDAO.buscarCaminos();
		listaCaminos = gestorCamino.getCaminos();
		
		JLabel lblNewLabel = new JLabel("GESTIÓN DE CAMINOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(166, 38, 248, 13);
		add(lblNewLabel);
		
		setLayout(null);
		
		JButton btnNuevoCamino = new JButton("+ Nuevo Camino");
		btnNuevoCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAltaCamino.actualizarSucursales();
				MenuPrincipal.mostrarPanel("AltaCamino");
			}
		});
		btnNuevoCamino.setBounds(166, 78, 248, 23);
		add(btnNuevoCamino);
		
		JButton btnListadoDeCaminos = new JButton("Listado de caminos");
		btnListadoDeCaminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelListadoCamino.actualizarSucursales();
				panelListadoCamino.llenarTabla(listaCaminos);
				MenuPrincipal.mostrarPanel("ListadoCamino");
			}
		});
		btnListadoDeCaminos.setBounds(166, 143, 248, 21);
		add(btnListadoDeCaminos);
		

		JButton btnVerGrafos = new JButton("Ver Grafo");
		btnVerGrafos.setBounds(251, 300, 200, 21);
		add(btnVerGrafos);
		btnVerGrafos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// OBTENEMOS EL GRAFO A TRAVES DE LOS CAMINOS QUE TENEMOS EN LA BD
			MenuPrincipal.mostrarPanel("VerGrafo");
		      //  SwingUtilities.invokeLater(() -> {
		       // 	JPanelGrafo graphDrawing = new JPanelGrafo();
		        //    graphDrawing.setVisible(true);
		      //  });

			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("Principal");
			}
		});
		btnVolver.setBounds(251, 217, 85, 21);
		add(btnVolver);
	}
	
	public void setPanelListadoCamino(JPanelListadoCamino panelListadoCamino) {
	    this.panelListadoCamino = panelListadoCamino;
	}
	
	public void setPanelAltaCamino(JPanelAltaCamino panelAltaCamino) {
	    this.panelAltaCamino = panelAltaCamino;
	}

}
