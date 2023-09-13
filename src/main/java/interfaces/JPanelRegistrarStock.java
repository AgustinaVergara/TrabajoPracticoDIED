package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import clases.Producto;
import clases.Sucursal;
import gestores.GestorProducto;
import gestores.GestorSucursal;
import gestores.GestorStockProducto;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class JPanelRegistrarStock extends JPanel {
	
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	private GestorProducto gestorProducto = GestorProducto.getInstance();
	private GestorStockProducto gestorStockProducto = GestorStockProducto.getInstance();
	
	private JComboBox<String> comboBoxSucursal;
	private DefaultComboBoxModel<String> modeloSucursal;
	private JComboBox<String> comboBoxProducto;
	private DefaultComboBoxModel<String> modeloProducto;
	private JSpinner spinner;
	
	private List<Sucursal> listaSucursales;
	private List<Producto> listaProductos;
	
	private Integer idProducto;
	private Integer idSucursal;
	private Integer cantidad;

	/**
	 * Create the panel.
	 */
	public JPanelRegistrarStock() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRAR STOCK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(107, 21, 356, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione una sucursal");
		lblNewLabel_1.setBounds(117, 67, 144, 13);
		add(lblNewLabel_1);
		
		// COMBO BOX SUCURSAL
		comboBoxSucursal = new JComboBox<String>();
		comboBoxSucursal.setBounds(271, 63, 117, 21);
				
		listaSucursales = gestorSucursal.getSucursales();
        modeloSucursal = new DefaultComboBoxModel<>();
        modeloSucursal.addElement("Seleccione");
        for(Sucursal s : listaSucursales) {
        	modeloSucursal.addElement(s.getNombre());
        }
        comboBoxSucursal.setModel(modeloSucursal);
        add(comboBoxSucursal);
		
        JLabel lblNewLabel_2 = new JLabel("Seleccione producto");
		lblNewLabel_2.setBounds(46, 116, 131, 13);
		add(lblNewLabel_2);
		
		// COMBO BOX PRODUCTO
		comboBoxProducto = new JComboBox<String>();
		comboBoxProducto.setBounds(165, 112, 117, 21);
				
		listaProductos = gestorProducto.getProductos();
        modeloProducto = new DefaultComboBoxModel<>();
        modeloProducto.addElement("Seleccione");
        for(Producto p : listaProductos) {
        	modeloProducto.addElement(p.getNombre());
        }
        comboBoxProducto.setModel(modeloProducto);
        add(comboBoxProducto);
        
		spinner = new JSpinner();
		spinner.setBounds(462, 113, 53, 20);
		add(spinner);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad");
		lblNewLabel_3.setBounds(363, 115, 110, 13);
		add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Actualizar stock");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				idProducto = comboBoxProducto.getSelectedIndex();
				idSucursal = gestorSucursal.buscarSucursalxNombre((String)comboBoxSucursal.getSelectedItem()).getId();
				cantidad = (Integer) spinner.getValue();
				gestorStockProducto.registrarStockProductoGestor(idProducto, idSucursal, cantidad);

				JOptionPane.showMessageDialog(frame, "Se ha actualizado el stock.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				vaciarCampos();
			}
		});
		btnNewButton.setBounds(241, 205, 124, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("GestionarSucursal");
			}
		});
		btnNewButton_1.setBounds(260, 269, 85, 21);
		add(btnNewButton_1);

	}
	
	public void vaciarCampos () {
		
		modeloProducto.setSelectedItem("Seleccione");
		spinner.setValue(1);
		
	}
}
