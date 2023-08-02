
package interfaces;

import java.awt.EventQueue;
import java.util.ArrayList;
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
import clases.Sucursal;
import dao.CaminoDao;
import dao.CaminoSQLimplementacion;
import dao.SucursalDao;
import dao.SucursalDaoImpl;
import gestores.GestorCamino;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaModificarCamino extends JFrame {


	private JPanel contentPane;
	private DefaultTableModel model;
	
	private InterfazGestionarCaminos ventanaModificarCaminos;
	
	private JTable table;
	
	GestorCamino gestorCamino = new GestorCamino();

	


	/**
	 * Create the frame.
	 */
	
	
	public VentanaModificarCamino(InterfazGestionarCaminos ventanaGestionarCaminos) {
		JTextField textId;
		JLabel labelSucursalDestino = new JLabel("Sucursal de destino:");
		JLabel sucursalO = new JLabel("Sucursal de origen:");

		JComboBox comboBoxSDestino = new JComboBox();
	
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		SucursalDao dao = new SucursalDaoImpl();
		CaminoDao daocamino = new CaminoSQLimplementacion();
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTADO DE CAMINOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(184, 11, 196, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 557, 140);
		contentPane.add(scrollPane);
		
		table = new JTable();
		llenarTablaCamino(gestorCamino.getCaminos());
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Sucursal Origen", "Sucursal Destino", "Estado", "Capacidad Maxima[kg]", "Tiempo de transito"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		table.getColumnModel().getColumn(2).setPreferredWidth(98);
		table.getColumnModel().getColumn(3).setPreferredWidth(94);
		table.getColumnModel().getColumn(4).setPreferredWidth(124);
		table.getColumnModel().getColumn(5).setPreferredWidth(106);
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
				ventanaGestionarCaminos.setVisible(true);
				
			}
		});
		btnVolver.setBounds(225, 324, 85, 21);
		contentPane.add(btnVolver);
		
		//FILTRADO
		JLabel labelFiltro = new JLabel("Filtrar por:");
		labelFiltro.setBounds(21, 38, 79, 13);
		contentPane.add(labelFiltro);
		
		// ID
		JLabel labelId = new JLabel("ID:");
		labelId.setBounds(36, 70, 64, 13);
		contentPane.add(labelId);
		
		textId = new JTextField();
		textId.setBounds(91, 62, 108, 20);
		contentPane.add(textId);
		textId.setColumns(10);

		//ESTADO
		JLabel labelEstado = new JLabel("Estado:");
		labelEstado.setBounds(36, 105, 45, 13);
		contentPane.add(labelEstado);
		
		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setEditable(true);
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"-SELECCIONE-", "OPERATIVA", "NO OPERATIVA"}));
		comboBoxEstado.setBounds(91, 101, 108, 21);
		contentPane.add(comboBoxEstado);
		
		// BUSCAMOS SUCURSALES PARA CARGAR
		sucursales = dao.buscarSucursales();
		
		// SUCURSAL ORIGEN
		JComboBox comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setEditable(true);
		comboBoxOrigen.setBounds(379, 65, 130, 22);
	
		sucursalO.setBounds(236, 70, 155, 13);
		contentPane.add(sucursalO);
		
		String seleccionar = "-SELECCIONE-";
	
		comboBoxOrigen.setEditable(true);
		DefaultComboBoxModel modeloOrigen;
		modeloOrigen =new DefaultComboBoxModel();
		modeloOrigen.addElement(seleccionar);
		for(Sucursal s: sucursales) {
			Object nombre = new Object();
			nombre = s.getNombre();
			modeloOrigen.addElement(nombre);
 		}
		comboBoxOrigen.setModel(modeloOrigen);
		contentPane.add(comboBoxOrigen);
		String eleccion = modeloOrigen.getSelectedItem().toString();
		
		
		// SUCURSAL DESTINO
		labelSucursalDestino.setBounds(236, 105, 155, 13);
		contentPane.add(labelSucursalDestino);
		
		comboBoxSDestino.setEditable(true);
		comboBoxSDestino.setBounds(379, 100, 130, 22);
		
		DefaultComboBoxModel modeloDestino;
		modeloDestino =new DefaultComboBoxModel();
		modeloDestino.addElement(seleccionar);
		//CARGAMOS LOS NOMBRES DE LAS SUCURSALES
				for(Sucursal s: sucursales) {
					Object nombre = new Object();
					nombre = s.getNombre();
					if(nombre!= eleccion)modeloDestino.addElement(nombre);
		 		}
		comboBoxSDestino.setModel(modeloDestino);
		contentPane.add(comboBoxSDestino);


		// BOTON FILTRO
		JButton btnAplicarFiltros = new JButton("Aplicar  filtros");
		btnAplicarFiltros.setBounds(206, 142, 113, 21);
		contentPane.add(btnAplicarFiltros);
		
		
		
	}
	
	public void llenarTablaCamino(List<Camino> listaCaminos) {
		
		for(Camino c : listaCaminos) {
			Object[] fila = new Object[6];//columnas
			
			fila[0] = c.getId();
			fila[1] = c.getSO();
			fila[2] = c.getSD();
			fila[3] = c.getEsOperativa();
			fila[4] = c.getCapacidadMax();
			fila[5] = c.getTiempoTransito();
		//	Se debe renderizar  los botones
			//JButton btnEliminar = new JButton("Eliminar");
			//JButton btnModificar = new JButton("Modificar");
			
			//fila[4] = btnModificar; 
			//fila[5] = btnEliminar;
			
			model.addRow(fila);
		}
	}
}







