package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Producto;
import gestores.GestorProducto;
import excepciones.CampoVacioException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.awt.event.ActionEvent;

public class AltaProducto extends JFrame {
	
	private GestorProducto gestorProducto = GestorProducto.getInstance();
	private InterfazGestionarProducto ventana;
	private JPanel contentPane;
	private JTextField txtnombre;
	private JTextField txtdescripcion;
	private JTextField txtprecio;
	private JTextField txtpeso;

	/**
	 * Create the frame.
	 */
	public AltaProducto(InterfazGestionarProducto ventana) {
		setTitle("ALTA DE PRODUCTO ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre (*):");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 58, 73, 28);
		contentPane.add(lblNewLabel);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(120, 63, 102, 22);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel txtalta = new JLabel("ALTA PRODUCTO");
		txtalta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtalta.setForeground(new Color(0, 0, 0));
		txtalta.setVerticalAlignment(SwingConstants.TOP);
		txtalta.setBounds(146, 22, 155, 28);
		contentPane.add(txtalta);
		
		JLabel labelDescripcion = new JLabel("Descripción (*): ");
		labelDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelDescripcion.setBounds(10, 111, 86, 13);
		contentPane.add(labelDescripcion);
		
		txtdescripcion = new JTextField();
		txtdescripcion.setBounds(120, 111, 252, 51);
		contentPane.add(txtdescripcion);
		txtdescripcion.setColumns(10);
		
		JLabel labelPrecio = new JLabel("Precio Unitario (*):");
		labelPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelPrecio.setBounds(10, 177, 107, 22);
		contentPane.add(labelPrecio);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(120, 180, 96, 19);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
		
		JLabel labelPeso = new JLabel("Peso KG (*):");
		labelPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelPeso.setBounds(10, 226, 86, 27);
		contentPane.add(labelPeso);
		
		txtpeso = new JTextField();
		txtpeso.setBounds(120, 231, 96, 19);
		contentPane.add(txtpeso);
		txtpeso.setColumns(10);
		
		JButton botomcancelar = new JButton("Cancelar");
		botomcancelar.setForeground(new Color(0, 0, 0));
		botomcancelar.setBounds(202, 267, 112, 28);
		contentPane.add(botomcancelar);
		
		 botomcancelar.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					cerrar();
				}
			 
			 });
		
		JButton botonguardar = new JButton("Guardar");
		botonguardar.setBounds(324, 267, 108, 28);
		contentPane.add(botonguardar);
		
		botonguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				try {
					campoVacio();
					
					Producto nuevoProducto = gestorProducto.crearProductoGestor(txtnombre.getText(),
							txtdescripcion.getText(), Double.parseDouble(txtprecio.getText()),
							Double.parseDouble(txtpeso.getText()));
					
					gestorProducto.agregarProducto(nuevoProducto);
					
					
				}catch(CampoVacioException e1) {
					JOptionPane.showMessageDialog(frame,
							"Faltan completar los siguientes campos:\n\n"+e1.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				ventana.setVisible(true);
				dispose();
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
	
	 public void cerrar() {
			String [] botones = {"Si", "Cancelar"};
			int i= JOptionPane.showOptionDialog(this, "¿Estas seguro de cancelar la operacion?", "Muchas respuestas",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);
			if(i==0) 
				System.exit((WIDTH));
			
		}
}
