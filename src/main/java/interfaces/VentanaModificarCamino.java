
package interfaces;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaModificarCamino extends JFrame {


	private JPanel contentPane;
	private MyTableModel modeloTablaCamino;
	private InterfazGestionarCaminos ventanaModificarCaminos;	
	private JTable table;	
	GestorCamino gestorCamino;
	private CaminoDao caminoDAO;//= new CaminoSQLimplementacion();
	private List<Camino> listaCaminos; //= caminoDAO.buscarCaminos();

	/**
	 * Create the frame.
	 */

	public VentanaModificarCamino(InterfazGestionarCaminos ventanaGestionarCaminos) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//gestorCamino.getCaminos();
		gestorCamino = GestorCamino.getInstance();
		//listaCaminos= gestorCamino.getCaminos();
		//caminoDAO = new CaminoSQLimplementacion();
		caminoDAO= new CaminoSQLimplementacion();
		this.listaCaminos = caminoDAO.buscarCaminos();
		JTextField textId;
		JLabel labelSucursalDestino = new JLabel("Sucursal de destino:");
		JLabel sucursalO = new JLabel("Sucursal de origen:");

		JComboBox comboBoxSDestino = new JComboBox();
		
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		SucursalDao dao = new SucursalDaoImpl();
		
		
		JLabel lblNewLabel = new JLabel("LISTADO DE CAMINOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(184, 11, 196, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 571, 140);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				
				int row = e.getY()/table.getRowHeight();
				
				if(row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >=0) {
					Object value = table.getValueAt(row, column);
					if(value instanceof JButton) {
						((JButton)value).doClick();
						JButton boton = (JButton) value;
						if(boton.getName().equals("Eliminar")) {
							Camino camino = listaCaminos.get(row); 
							 
							eliminarCaminoElegido(camino);
						    
						}
						if(boton.getName().equals("Modificar")) {
							final InterfazModificarSucursal ventanaModificarSucursal = new InterfazModificarSucursal();
							ventanaModificarSucursal.setVisible(true); 
							setVisible(false);
							System.out.println("modificar");
						}
						
					}
				}
			}
		});
		
		modeloTablaCamino = new MyTableModel();
		table.setDefaultRenderer(Object.class, new RenderTabla()); //renderizar tabla
		table.setModel(modeloTablaCamino);
		
		// DEFINO MI MODELO DE TABLA CAMINO PARA HACERLO MANUAL 
		
		modeloTablaCamino.addColumn("Origen");
		modeloTablaCamino.addColumn("Destino");
		modeloTablaCamino.addColumn("Estado");
		modeloTablaCamino.addColumn("Capacidad");
		modeloTablaCamino.addColumn("Modificar");
		modeloTablaCamino.addColumn("Eliminar");
		
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setMinWidth(95);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		
		// PONGO DENTRO DEL SCROLL PANE MI TABLA DE CAMINOS		
		scrollPane.setViewportView(table);
		// LLENO MI TABLA
		//llenarTablaCamino(gestorCamino.getCaminos());
		
		// BOTON VOLVER
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				modeloTablaCamino.setRowCount(0); //Limpiar tabla
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
		this.listaCaminos = listaCaminos;
		for(Camino c : listaCaminos) {
			Object[] fila = new Object[6];
			//columnas
			fila[0] = c.getSO().getNombre();
			fila[1] = c.getSD().getNombre();
			fila[2] = c.getEsOperativa();
			fila[3] = c.getCapacidadMax();
			//fila[4] = c.getCapacidadMax();
			//fila[5] = c.getTiempoTransito();
		//	Se debe renderizar  los botones
			JButton btnModificar = new JButton("Modificar");
			JButton btnEliminar = new JButton("Eliminar");
		
			btnEliminar.setEnabled(true);
			
			fila[4] = btnModificar; 
			fila[5] = btnEliminar;
			
			modeloTablaCamino.addRow(fila);
		}
	}
	public void eliminarCaminoElegido(Camino camino) {
		String [] botones = {"Si", "Cancelar"};
		int i= JOptionPane.showOptionDialog(this, "¿Estas seguro de eliminar el camino seleccionado?", "Muchas respuestas",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);
		/*int i= JOptionPane.showConfirmDialog(this, "¿Estas seguro de cancelar la operacion?");*/
		if(i==0) {
			gestorCamino.eliminarCamino(camino);
	        modeloTablaCamino.setRowCount(0);
	        llenarTablaCamino(caminoDAO.buscarCaminos());	
		}
			//System.exit((WIDTH));
		
	}
}







