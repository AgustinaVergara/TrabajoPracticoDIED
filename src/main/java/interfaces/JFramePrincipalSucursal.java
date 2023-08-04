package interfaces;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class JFramePrincipalSucursal extends JFrame {

	private static JPanel contentPane;
	private CardLayout cardLayout;
	private JPanelGestionarSucursal panelGestionarSucursal;
	private JPanelAltaSucursal panelAltaSucursal;
	private JPanelListadoSucursal panelListadoSucursal;
	private JPanelModificarSucursal panelModificarSucursal;

	/**
	 * Create the frame.
	 */
	public JFramePrincipalSucursal() {

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

	
		//Crear paneles
		panelGestionarSucursal = new  JPanelGestionarSucursal();
		panelAltaSucursal = new JPanelAltaSucursal();
		panelListadoSucursal = new JPanelListadoSucursal();
		panelModificarSucursal = new JPanelModificarSucursal();
		
		panelGestionarSucursal.setPanelListadoSucursal(panelListadoSucursal); //establecer referencia 
		panelListadoSucursal.setPanelModificarSucursal(panelModificarSucursal);
		panelModificarSucursal.setPanelListadoSucursal(panelListadoSucursal);
		
		//Configura el layout del contenido de la ventana
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        contentPane.add(panelGestionarSucursal, "GestionarSucursal");
        contentPane.add(panelAltaSucursal, "AltaSucursal");
        contentPane.add(panelListadoSucursal, "ListadoSucursal");
        contentPane.add(panelModificarSucursal,"ModificarSucursal");
        
        getContentPane().add(contentPane);
		
	}
	
	public static void mostrarPanel(String nombrePanel) {
		CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, nombrePanel);
	}
	
	public static void cerrar() {
		final MenuPrincipal ventanaMenu = new MenuPrincipal();
		ventanaMenu.setVisible(true);
		for (java.awt.Window window : java.awt.Window.getWindows()) {
            if (window instanceof JFramePrincipalSucursal) {
                window.dispose(); // Cierra el JFrame actual
            }
        }
	}

}
