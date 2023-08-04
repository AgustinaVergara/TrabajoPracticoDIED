package interfaces;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloMenuPrincipal = new JLabel("SISTEMA DE GESTIÓN LOGÍSTICO");
		tituloMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER); 
		tituloMenuPrincipal.setBounds(137, 26, 247, 13);
		contentPane.add(tituloMenuPrincipal);
		
		final InterfazGestionarSucursal ventanaGestionarSucursal = new InterfazGestionarSucursal(); //crea la proxima 
		JButton btnGestionarSucursal = new JButton("Gestionar sucursales");
		btnGestionarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaGestionarSucursal.setVisible(true);
				dispose(); //cierra la ventana actual
			}
		});
		btnGestionarSucursal.setBounds(39, 64, 215, 37);
		contentPane.add(btnGestionarSucursal);
		
		// GESTIONANDO CAMINOS
		final InterfazGestionarCaminos ventanaGestionarCaminos = new InterfazGestionarCaminos(this); //crea la proxima
		JButton btnGestionarCamino = new JButton("Gestionar caminos");
		btnGestionarCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaGestionarCaminos.setVisible(true);
				dispose(); //cierra la ventana actual
			}
		});
		btnGestionarCamino.setBounds(264, 64, 215, 37);
		contentPane.add(btnGestionarCamino);
		
		//final InterfazGestionarProducto ventanaGestionarProducto = new InterfazGestionarProducto();
		JButton btnGestionarProducto = new JButton("Gestionar productos");
		btnGestionarProducto.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		//	ventanaGestionarProducto.setVisible(true);
			dispose(); //cierra la ventana actual
		}
	});
		btnGestionarProducto.setBounds(39, 118, 215, 37);
		contentPane.add(btnGestionarProducto);
		
		JButton btnRegistrarStock = new JButton("Control de stock");
		btnRegistrarStock.setBounds(264, 118, 215, 37);
		contentPane.add(btnRegistrarStock);
		
		JButton btnFlujoMaximo = new JButton("Flujo máximo");
		btnFlujoMaximo.setBounds(39, 176, 215, 37);
		contentPane.add(btnFlujoMaximo);
		
		JButton btnPageRank = new JButton("PageRank");
		btnPageRank.setBounds(264, 176, 215, 37);
		contentPane.add(btnPageRank);
	}
}
