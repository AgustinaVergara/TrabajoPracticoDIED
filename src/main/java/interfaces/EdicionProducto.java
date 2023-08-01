package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import excepciones.CampoVacioException;

public class EdicionProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtnombre;
	private JTextField txtdescripcion;
	private JTextField txtprecio;
	private JTextField txtpeso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EdicionProducto frame = new EdicionProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EdicionProducto() {
		setTitle("EDICION DE PRODUCTO ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre (*):");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelNombre.setBounds(10, 58, 73, 28);
		contentPane.add(labelNombre);
		
		JLabel txtalta = new JLabel("EDICION PRODUCTO");
		txtalta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtalta.setForeground(new Color(0, 0, 0));
		txtalta.setVerticalAlignment(SwingConstants.TOP);
		txtalta.setBounds(146, 22, 189, 28);
		contentPane.add(txtalta);
		
		JLabel labelDescripcion = new JLabel("Descripción (*): ");
		labelDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelDescripcion.setBounds(10, 111, 86, 13);
		contentPane.add(labelDescripcion);
		
		JLabel labelPrecio = new JLabel("Precio Unitario (*):");
		labelPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelPrecio.setBounds(10, 177, 107, 22);
		contentPane.add(labelPrecio);
		
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
		
		JButton botonguardar = new JButton("Guardar");
		botonguardar.setBounds(324, 267, 108, 28);
		contentPane.add(botonguardar);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(120, 64, 96, 19);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtdescripcion = new JTextField();
		txtdescripcion.setBounds(120, 109, 302, 50);
		contentPane.add(txtdescripcion);
		txtdescripcion.setColumns(10);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(120, 180, 96, 19);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
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
