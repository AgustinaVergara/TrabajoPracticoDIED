package interfaces;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
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
		//setBounds(100, 100, 544, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		JLabel tituloMenuPrincipal = new JLabel("SISTEMA DE GESTIÓN LOGÍSTICO");
		tituloMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 14));
		tituloMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER); 
		tituloMenuPrincipal.setBounds(76, 38, 440, 13);
		contentPane.add(tituloMenuPrincipal);
		/*Esto lo uso  para pasar del menu a InterfazGestionarSucursal sin paneles
		 * 
		final InterfazGestionarSucursal ventanaGestionarSucursal = new InterfazGestionarSucursal(); //crea la proxima 
		JButton btnGestionarSucursal = new JButton("Gestionar sucursales");
		btnGestionarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaGestionarSucursal.setVisible(true);
				dispose(); //cierra la ventana actual
			}
		});
		btnGestionarSucursal.setBounds(39, 64, 215, 37);
		contentPane.add(btnGestionarSucursal);*/
		
		//Este boton ahora abre el gestionar sucursal que trabaja con paneles
		final JFramePrincipalSucursal ventanaGestionarSucursal = new JFramePrincipalSucursal();
		JButton btnGestionarSucursal = new JButton("Gestionar sucursales");
		btnGestionarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaGestionarSucursal.setVisible(true);
				setVisible(false);
				//dispose(); //cierra la ventana actual
			}
		});
		btnGestionarSucursal.setBounds(76, 89, 215, 37);
		contentPane.add(btnGestionarSucursal);
		
		JButton btnGestionarCamino = new JButton("Gestionar caminos");
		btnGestionarCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionarCamino.setBounds(301, 89, 215, 37);
		contentPane.add(btnGestionarCamino);
		
		JButton btnGestionarProducto = new JButton("Gestionar productos");
		btnGestionarProducto.setBounds(76, 143, 215, 37);
		contentPane.add(btnGestionarProducto);
		
		JButton btnRegistrarStock = new JButton("Control de stock");
		btnRegistrarStock.setBounds(301, 143, 215, 37);
		contentPane.add(btnRegistrarStock);
		
		JButton btnFlujoMaximo = new JButton("Flujo máximo");
		btnFlujoMaximo.setBounds(76, 201, 215, 37);
		contentPane.add(btnFlujoMaximo);
		
		JButton btnPageRank = new JButton("PageRank");
		btnPageRank.setBounds(301, 201, 215, 37);
		contentPane.add(btnPageRank);
		
		JLabel lblNewLabel = new JLabel("Trabajo Práctico Integrador realizado por:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(76, 288, 440, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("María Victoria Bertero, Valentina Ducasse y Agustina Vergara");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(86, 311, 430, 13);
		contentPane.add(lblNewLabel_1);
	}
}
