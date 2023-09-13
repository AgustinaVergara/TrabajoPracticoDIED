package interfaces;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Font;

//Probando para hacer que este sea el unico frame y luego cambien los paneles aca
public class MenuPrincipal extends JFrame {
	
	private static JPanel contentPane;
	private CardLayout cardLayout;
	private JPanelPrincipal panelPrincipal;
	
	private JPanelGestionarSucursal panelGestionarSucursal;
	private JPanelAltaSucursal panelAltaSucursal;
	private JPanelListadoSucursal panelListadoSucursal;
	private JPanelModificarSucursal panelModificarSucursal;
	private JPanelRegistrarStock panelRegistrarStock;
	
	private JPanelGestionarCamino panelGestionarCamino;
	private JPanelAltaCamino panelAltaCamino;
	private JPanelListadoCamino panelListadoCamino;
	private JPanelModificarCamino panelModificarCamino;
	
	private JPanelGestionarProducto panelGestionarProducto;
	private JPanelAltaProducto panelAltaProducto;
	private JPanelListadoProducto panelListadoProducto;
	private JPanelModificarProducto panelModificarProducto;
	
	private JPanelGestionarOrden panelGestionarOrden;
	private JPanelAltaOrden panelAltaOrden;
	private JPanelListadoOrden panelListadoOrden;
	
	


	
	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Tamaño deseado para el JFrame
        int width = 625;
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
  		panelPrincipal = new JPanelPrincipal();
  		
  		panelGestionarSucursal = new  JPanelGestionarSucursal();
  		panelAltaSucursal = new JPanelAltaSucursal();
  		panelListadoSucursal = new JPanelListadoSucursal();
  		panelModificarSucursal = new JPanelModificarSucursal();
  		panelRegistrarStock = new JPanelRegistrarStock();
  		
  		panelGestionarCamino = new  JPanelGestionarCamino();
  		panelAltaCamino = new JPanelAltaCamino();
  		panelListadoCamino = new JPanelListadoCamino();
  		panelModificarCamino = new JPanelModificarCamino();
  		
  		panelGestionarProducto = new  JPanelGestionarProducto();
  		panelAltaProducto = new JPanelAltaProducto();
  		panelListadoProducto = new JPanelListadoProducto();
  		panelModificarProducto = new JPanelModificarProducto();
  		
  		panelGestionarOrden = new  JPanelGestionarOrden();
  		panelAltaOrden = new JPanelAltaOrden();
  		panelListadoOrden = new JPanelListadoOrden();
  		
  		

  		
  		//Establecer referencias entre paneles
  		panelGestionarSucursal.setPanelListadoSucursal(panelListadoSucursal); 
  		panelListadoSucursal.setPanelModificarSucursal(panelModificarSucursal);
  		panelModificarSucursal.setPanelListadoSucursal(panelListadoSucursal);
  		
  		panelGestionarCamino.setPanelAltaCamino(panelAltaCamino);
  		panelGestionarCamino.setPanelListadoCamino(panelListadoCamino);
  		panelListadoCamino.setPanelModificarCamino(panelModificarCamino);
  		panelModificarCamino.setPanelListadoCamino(panelListadoCamino);
  	
  		
  		panelGestionarProducto.setPanelListadoProducto(panelListadoProducto);
  		panelListadoProducto.setPanelModificarProducto(panelModificarProducto);
  		panelModificarProducto.setPanelListadoProducto(panelListadoProducto);
  		
  		panelGestionarOrden.setPanelListadoOrden(panelListadoOrden);
      		
		//Configura el layout del contenido de la ventana
	    contentPane = new JPanel();
	    cardLayout = new CardLayout();
	    contentPane.setLayout(cardLayout);
	    contentPane.add(panelPrincipal, "Principal");
	    
	    contentPane.add(panelGestionarSucursal, "GestionarSucursal");
	    contentPane.add(panelAltaSucursal, "AltaSucursal");
	    contentPane.add(panelListadoSucursal, "ListadoSucursal");
	    contentPane.add(panelModificarSucursal,"ModificarSucursal");
	    contentPane.add(panelRegistrarStock, "RegistrarStock");
	    
	    contentPane.add(panelGestionarCamino,"GestionarCamino");
	    contentPane.add(panelAltaCamino,"AltaCamino");
	    contentPane.add(panelListadoCamino,"ListadoCamino");
	    contentPane.add(panelModificarCamino,"ModificarCamino");
	   
	    
	    contentPane.add(panelGestionarProducto,"GestionarProducto");
	    contentPane.add(panelAltaProducto,"AltaProducto");
	    contentPane.add(panelListadoProducto,"ListadoProducto");
	    contentPane.add(panelModificarProducto,"ModificarProducto");
	    
	    contentPane.add(panelGestionarOrden,"GestionarOrden");
	    contentPane.add(panelAltaOrden,"AltaOrden");
	    contentPane.add(panelListadoOrden,"ListadoOrden");
	    
	      
	    getContentPane().add(contentPane);

	}
	
	public static void mostrarPanel(String nombrePanel) {
		CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, nombrePanel);
	}

}


