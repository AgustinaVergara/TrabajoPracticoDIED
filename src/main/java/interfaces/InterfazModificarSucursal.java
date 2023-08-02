package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InterfazModificarSucursal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtHoraApertura;
	private JTextField txtMinutoApertura;
	private JTextField txtHoraCierre;
	private JTextField txtMinutoCierre;


	/**
	 * Create the frame.
	 */
	public InterfazModificarSucursal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloModificarSucursal = new JLabel("MODIFICAR SUCURSAL");
		tituloModificarSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		tituloModificarSucursal.setBounds(132, 24, 190, 13);
		contentPane.add(tituloModificarSucursal);
		
		JLabel nombreSucursal = new JLabel("Nombre ");
		nombreSucursal.setBounds(57, 51, 96, 13);
		contentPane.add(nombreSucursal);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(182, 48, 160, 19);
		contentPane.add(txtNombre);
		
		JLabel horarioApertura = new JLabel("Hora apertura");
		horarioApertura.setBounds(58, 92, 114, 13);
		contentPane.add(horarioApertura);
		
		txtHoraApertura = new JTextField();
		txtHoraApertura.setColumns(10);
		txtHoraApertura.setBounds(182, 89, 45, 19);
		contentPane.add(txtHoraApertura);
		
		JLabel separador_1 = new JLabel(":");
		separador_1.setBounds(231, 92, 27, 13);
		contentPane.add(separador_1);
		
		txtMinutoApertura = new JTextField();
		txtMinutoApertura.setColumns(10);
		txtMinutoApertura.setBounds(237, 89, 45, 19);
		contentPane.add(txtMinutoApertura);
		
		JLabel horarioCierre = new JLabel("Hora cierre");
		horarioCierre.setBounds(57, 130, 80, 13);
		contentPane.add(horarioCierre);
		
		txtHoraCierre = new JTextField();
		txtHoraCierre.setColumns(10);
		txtHoraCierre.setBounds(182, 124, 45, 19);
		contentPane.add(txtHoraCierre);
		
		JLabel separador_2 = new JLabel(":");
		separador_2.setBounds(231, 127, 27, 13);
		contentPane.add(separador_2);
		
		txtMinutoCierre = new JTextField();
		txtMinutoCierre.setColumns(10);
		txtMinutoCierre.setBounds(237, 124, 45, 19);
		contentPane.add(txtMinutoCierre);
		
		JLabel EstadoSucursal = new JLabel("Estado");
		EstadoSucursal.setBounds(58, 171, 45, 13);
		contentPane.add(EstadoSucursal);
		
		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setBounds(182, 167, 160, 21);
		contentPane.add(comboBoxEstado);
		
		JButton btnGuardarSucursal = new JButton("Guardar");
		btnGuardarSucursal.setBounds(214, 215, 85, 21);
		contentPane.add(btnGuardarSucursal);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(119, 215, 85, 21);
		contentPane.add(btnCancelar);
	}
}
