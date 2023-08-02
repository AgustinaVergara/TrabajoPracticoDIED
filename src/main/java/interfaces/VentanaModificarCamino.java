
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

import clases.Camino;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaModificarCamino extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	
	private InterfazGestionarSucursal ventanaModificarCaminos;
	private JTextField txtid;
	private JTextField txtSO;
	private JTextField txtSucursalDestino;
	private JTable table;


	/**
	 * Create the frame.
	 */
	public VentanaModificarCamino(VentanaModificarCamino ventanaModificarCaminos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTADO DE CAMINOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(157, 23, 196, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 519, 127);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//Defino mi propio default table model para cargar los datos en la tabla
		model = new DefaultTableModel();
		
		model.addColumn("ID");
		model.addColumn("Sucursal de origen");
		model.addColumn("Sucursal de destino");
		model.addColumn("Estado");
		model.addColumn("Capacidad Maxima [kg]");
		model.addColumn("Tiempo de transito");
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				model.setRowCount(0); //Limpiar tabla
				ventanaModificarCaminos.setVisible(true);
			}
		});
		btnVolver.setBounds(225, 324, 85, 21);
		contentPane.add(btnVolver);
		
		//FILTRADO
		JLabel labelFiltro = new JLabel("Filtrar por:");
		labelFiltro.setBounds(57, 46, 79, 13);
		contentPane.add(labelFiltro);
		
		// ID
		JLabel labelId = new JLabel("ID:");
		labelId.setBounds(36, 70, 64, 13);
		contentPane.add(labelId);
		
		txtid= new JTextField();
		txtid.setBounds(91, 66, 108, 19);
		contentPane.add(txtid);
		txtid.setColumns(10);

		//ESTADO
		JLabel labelEstado = new JLabel("Estado:");
		labelEstado.setBounds(36, 105, 45, 13);
		contentPane.add(labelEstado);
		
		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setEditable(true);
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"-SELECCIONE-", "OPERATIVA", "NO OPERATIVA"}));
		comboBoxEstado.setBounds(91, 101, 108, 21);
		contentPane.add(comboBoxEstado);
		
		// SUCURSAL ORIGEN
		JLabel sucursalO = new JLabel("Sucursal de origen:");
		sucursalO.setBounds(225, 69, 136, 13);
		contentPane.add(sucursalO);
		
		txtSO = new JTextField();
		txtSO.setColumns(10);
		txtSO.setBounds(340, 65, 113, 19);
		contentPane.add(txtSO);

		// SUCURSAL DESTINO
		JLabel labelSucursalDestino = new JLabel("Sucursal de destino:");
		labelSucursalDestino.setBounds(225, 102, 136, 13);
		contentPane.add(labelSucursalDestino);
		
		txtSucursalDestino = new JTextField();
		txtSucursalDestino.setColumns(10);
		txtSucursalDestino.setBounds(340, 99, 113, 19);
		contentPane.add(txtSucursalDestino);
		
		JButton btnAplicarFiltros = new JButton("Aplicar  filtros");
		btnAplicarFiltros.setBounds(206, 142, 113, 21);
		contentPane.add(btnAplicarFiltros);
	}
	
	public void llenarTabla(List<Camino> listaCaminos) {
		
		for(Camino c : listaCaminos) {
			Object[] fila = new Object[6];//columnas
			
			fila[0] = c.getId();
			fila[1] = c.getSO();
			fila[2] = c.getSD();
			fila[3] = c.getEsOperativa();
			/*n Se debe renderizar  los botones
			JButton btnEliminar = new JButton("Eliminar");
			JButton btnModificar = new JButton("Modificar");
			
			fila[4] = btnModificar; 
			fila[5] = btnEliminar;*/
			
			model.addRow(fila);
		}
	}
}







