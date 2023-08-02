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
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BusquedaProducto extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaProducto frame = new BusquedaProducto();
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
	public BusquedaProducto() {
		setTitle("BUSQUEDA DE PRODUCTO ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtalta = new JLabel("BUSQUEDA PRODUCTO");
		txtalta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtalta.setForeground(new Color(0, 0, 0));
		txtalta.setVerticalAlignment(SwingConstants.TOP);
		txtalta.setBounds(91, 22, 210, 28);
		contentPane.add(txtalta);
		
		JButton botonbuscar = new JButton("Buscar");
		botonbuscar.setBounds(337, 274, 85, 21);
		contentPane.add(botonbuscar);
	}
}
