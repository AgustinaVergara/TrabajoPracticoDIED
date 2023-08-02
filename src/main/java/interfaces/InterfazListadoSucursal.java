package interfaces;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import clases.Sucursal;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class InterfazListadoSucursal extends JFrame {

	private JPanel contentPane;
	private JTable tableSucursales;
	private DefaultTableModel model;
	
	private InterfazGestionarSucursal ventanaGestionarSucursal;
	private JTextField txtNombre;
	private JTextField txtHoraApertura;
	private JTextField txtMinutoApertura;
	private JTextField txtHoraCierre;
	private JTextField txtMinutoCierre;


	/**
	 * Create the frame.
	 */
	public InterfazListadoSucursal(InterfazGestionarSucursal ventanaGestionarSucursal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTADO DE SUCURSALES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(157, 23, 196, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 519, 127);
		contentPane.add(scrollPane);
		
		tableSucursales = new JTable();
		
		//Defino mi propio default table model para cargar los datos en la tabla
		model = new DefaultTableModel();
		tableSucursales.setModel(model);
		
		model.addColumn("Nombre");
		model.addColumn("Hora de apertura");
		model.addColumn("Hora de cierre");
		model.addColumn("Estado");
		model.addColumn("Modificar");
		model.addColumn("Eliminar");
		
		tableSucursales.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableSucursales.getColumnModel().getColumn(1).setMinWidth(95);
		tableSucursales.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableSucursales.getColumnModel().getColumn(2).setMinWidth(80);
		scrollPane.setViewportView(tableSucursales);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				model.setRowCount(0); //Limpiar tabla
				ventanaGestionarSucursal.setVisible(true);
			}
		});
		btnVolver.setBounds(225, 324, 85, 21);
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setBounds(57, 46, 79, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(57, 69, 64, 13);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(114, 66, 138, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Estado:");
		lblNewLabel_3.setBounds(57, 105, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setBounds(114, 101, 138, 21);
		contentPane.add(comboBoxEstado);
		
		JLabel horarioApertura = new JLabel("Hora apertura:");
		horarioApertura.setBounds(276, 69, 85, 13);
		contentPane.add(horarioApertura);
		
		txtHoraApertura = new JTextField();
		txtHoraApertura.setColumns(10);
		txtHoraApertura.setBounds(363, 66, 45, 19);
		contentPane.add(txtHoraApertura);
		
		JLabel separador_1 = new JLabel(":");
		separador_1.setBounds(412, 69, 27, 13);
		contentPane.add(separador_1);
		
		txtMinutoApertura = new JTextField();
		txtMinutoApertura.setColumns(10);
		txtMinutoApertura.setBounds(418, 66, 45, 19);
		contentPane.add(txtMinutoApertura);
		
		JLabel lblHoraCierre = new JLabel("Hora cierre:");
		lblHoraCierre.setBounds(276, 102, 85, 13);
		contentPane.add(lblHoraCierre);
		
		txtHoraCierre = new JTextField();
		txtHoraCierre.setColumns(10);
		txtHoraCierre.setBounds(363, 99, 45, 19);
		contentPane.add(txtHoraCierre);
		
		JLabel separador_1_1 = new JLabel(":");
		separador_1_1.setBounds(412, 102, 27, 13);
		contentPane.add(separador_1_1);
		
		txtMinutoCierre = new JTextField();
		txtMinutoCierre.setColumns(10);
		txtMinutoCierre.setBounds(418, 99, 45, 19);
		contentPane.add(txtMinutoCierre);
		
		JButton btnAplicarFiltros = new JButton("Aplicar  filtros");
		btnAplicarFiltros.setBounds(206, 142, 113, 21);
		contentPane.add(btnAplicarFiltros);
	}
	
	public void llenarTabla(List<Sucursal> listaSucursales) {
		
		
		
		for(Sucursal s : listaSucursales) {
			Object[] fila = new Object[6];//columnas
			
			fila[0] = s.getNombre();
			fila[1] = s.getHorarioApertura();
			fila[2] = s.getHorarioCierre();
			fila[3] = s.getEstado();
			/*n Se debe renderizar  los botones
			JButton btnEliminar = new JButton("Eliminar");
			JButton btnModificar = new JButton("Modificar");
			
			fila[4] = btnModificar; 
			fila[5] = btnEliminar;*/
			
			model.addRow(fila);
		}
	}
}
