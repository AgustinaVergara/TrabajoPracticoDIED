package interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Producto;
import excepciones.CampoVacioException;
import gestores.GestorProducto;

public class JPanelAltaProducto extends JPanel {
	
	private GestorProducto gestorProducto = GestorProducto.getInstance();
	
	private JTextField txtnombre;
	private JTextField txtdescripcion;
	private JTextField txtprecio;
	private JTextField txtpeso;


	/**
	 * Create the panel.
	 */
	public JPanelAltaProducto() {
		setLayout(null);
		
		JLabel txtalta = new JLabel("ALTA PRODUCTO");
		txtalta.setHorizontalAlignment(SwingConstants.CENTER);
		txtalta.setForeground(new Color(0, 0, 0));
		txtalta.setVerticalAlignment(SwingConstants.TOP);
		txtalta.setBounds(255, 22, 114, 13);
		add(txtalta);
		
		JLabel lblNewLabel = new JLabel("Nombre (*):");
		lblNewLabel.setBounds(98, 63, 84, 13);
		add(lblNewLabel);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(222, 60, 96, 19);
		add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel labelDescripcion = new JLabel("Descripción (*): ");
		labelDescripcion.setBounds(98, 93, 103, 13);
		add(labelDescripcion);
		
		txtdescripcion = new JTextField();
		txtdescripcion.setBounds(222, 90, 283, 55);
		add(txtdescripcion);
		txtdescripcion.setColumns(10);
		
		JLabel labelPrecio = new JLabel("Precio Unitario (*):");
		labelPrecio.setBounds(98, 169, 114, 13);
		add(labelPrecio);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(222, 166, 96, 19);
		add(txtprecio);
		txtprecio.setColumns(10);
		
		JLabel labelPeso = new JLabel("Peso KG (*):");
		//labelPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelPeso.setBounds(98, 204, 84, 13);
		add(labelPeso);
		
		txtpeso = new JTextField();
		txtpeso.setBounds(222, 201, 96, 19);
		add(txtpeso);
		txtpeso.setColumns(10);
		
		JButton botoncancelar = new JButton("Cancelar");
		botoncancelar.setForeground(new Color(0, 0, 0));
		botoncancelar.setBounds(183, 247, 107, 21);
		add(botoncancelar);
		
		 botoncancelar.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					int respuesta = JOptionPane.showConfirmDialog(null,
			                "¿Está seguro de que desea cancelar y volver?", 
			                "Confirmar Cancelar", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

			        if (respuesta == JOptionPane.YES_OPTION) {
						MenuPrincipal.mostrarPanel("GestionarProducto");
			        }

				}
			 
			 });
		
		JButton botonguardar = new JButton("Guardar");
		botonguardar.setBounds(300, 247, 90, 21);
		add(botonguardar);
		
		botonguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				try {
					campoVacio();
					
					Producto nuevoProducto = gestorProducto.crearProductoGestor(txtnombre.getText(),
							txtdescripcion.getText(), Double.parseDouble(txtprecio.getText()),
							Double.parseDouble(txtpeso.getText()));
					
					gestorProducto.agregarProducto(nuevoProducto);
					
					MenuPrincipal.mostrarPanel("GestionarProducto");
					
				}catch(CampoVacioException e1) {
					JOptionPane.showMessageDialog(frame,
							"Faltan completar los siguientes campos:\n\n"+e1.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	public void campoVacio() throws CampoVacioException{
		String error = "";
		boolean algunoVacio = false;
		
		if(txtnombre.getText().isEmpty()) {
			error += "- Nombre del producto\n";
			algunoVacio = true;
		}
		
		if(txtdescripcion.getText().isEmpty()) {
			error += "- Descripcion del producto\n";
			algunoVacio = true;
		}
		
		if(txtprecio.getText().isEmpty()) {
			error += "- Precio unitario\n";
			algunoVacio = true;
		}
		if(txtpeso.getText().isEmpty()) {
			error += "- Peso en kg\n";
			algunoVacio = true;
		}
		
		if(algunoVacio) {
			
			throw new CampoVacioException(error);
		}			
	}

}
