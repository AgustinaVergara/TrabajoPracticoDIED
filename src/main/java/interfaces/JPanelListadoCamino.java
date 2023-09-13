package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Camino;
import clases.Sucursal;
import dao.CaminoDao;
import dao.CaminoSQLimplementacion;
import dao.SucursalDao;
import dao.SucursalDaoImpl;
import enums.EstadoSucursal;
import gestores.GestorCamino;
import gestores.GestorSucursal;


public class JPanelListadoCamino extends JPanel {

	private JTable tableCaminos;

	// private DefaultTableModel model;
	private MyTableModel model;

	private JTextField textId;
	private List<Sucursal> sucursales;

	private List<Camino> listaCaminos;
	private CaminoDao caminoDAO;
	private SucursalDao sucursalDAO;
	private GestorCamino gestorCamino = GestorCamino.getInstance();
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();

	private DefaultComboBoxModel modeloOrigen;
	private DefaultComboBoxModel modeloDestino;
	private JComboBox comboBoxOrigen;
	private JComboBox comboBoxSDestino;
	private String seleccionar;

	private JPanelModificarCamino panelModificarCamino;

	/**
	 * Create the panel.
	 */
	public JPanelListadoCamino() {
		caminoDAO = new CaminoSQLimplementacion();
		sucursalDAO = new SucursalDaoImpl();
		setLayout(null);

		JLabel lblNewLabel = new JLabel("LISTADO DE CAMINOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(197, 23, 196, 13);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setBounds(36, 46, 79, 13);
		add(lblNewLabel_1);

		// ID
		JLabel labelId = new JLabel("ID:");
		labelId.setBounds(36, 70, 64, 13);
		add(labelId);

		textId = new JTextField();
		textId.setBounds(91, 62, 108, 20);
		add(textId);

		// ESTADO
		JLabel labelEstado = new JLabel("Estado:");
		labelEstado.setBounds(36, 105, 45, 13);
		add(labelEstado);

		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setEditable(true);
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "OPERATIVA", "NO OPERATIVA" }));
		comboBoxEstado.setBounds(91, 101, 108, 21);
		add(comboBoxEstado);

		// SUCURSAL ORIGEN
		JLabel sucursalOrigen = new JLabel("Sucursal Origen:");
		sucursalOrigen.setBounds(236, 70, 155, 13);
		add(sucursalOrigen);

		// BUSCAMOS SUCURSALES PARA CARGAR
		sucursales = sucursalDAO.buscarSucursales();

		// SUCURSAL ORIGEN COMBO BOX
		comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setEditable(true);
		comboBoxOrigen.setBounds(379, 65, 130, 22);

		String seleccionar = "Seleccione";
		comboBoxOrigen.setEditable(true);

		modeloOrigen = new DefaultComboBoxModel();
		modeloOrigen.addElement(seleccionar);
		for (Sucursal s : sucursales) {
			Object nombre = new Object();
			nombre = s.getNombre();
			modeloOrigen.addElement(nombre);
		}
		comboBoxOrigen.setModel(modeloOrigen);
		add(comboBoxOrigen);
		String eleccion = comboBoxOrigen.getSelectedItem().toString();

		// SUCURSAL DESTINO
		JLabel sucursalDestino = new JLabel("Sucursal Destino:");
		sucursalDestino.setBounds(236, 105, 155, 13);
		add(sucursalDestino);

		comboBoxSDestino = new JComboBox();
		comboBoxSDestino.setEditable(true);
		comboBoxSDestino.setBounds(379, 100, 130, 22);

		modeloDestino = new DefaultComboBoxModel();
		modeloDestino.addElement(seleccionar);
		// CARGAMOS LOS NOMBRES DE LAS SUCURSALES
		for (Sucursal s : sucursales) {
			Object nombre = new Object();
			nombre = s.getNombre();
			if (nombre != eleccion)
				modeloDestino.addElement(nombre);
		}
		comboBoxSDestino.setModel(modeloDestino);
		add(comboBoxSDestino);

		JButton btnAplicarFiltros = new JButton("Aplicar  filtros");
		btnAplicarFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				String sucursalOrigen, sucursalDestino;
				EstadoSucursal estado;
				// DEFINIMOS LOS FILTROS A UTILIZAR
				if (textId.getText().isEmpty())
					id = -1;
				else
					id = Integer.parseInt(textId.getText());
				if (comboBoxOrigen.getSelectedItem().toString().equals("Seleccione"))
					sucursalOrigen = null;
				else
					sucursalOrigen = comboBoxOrigen.getSelectedItem().toString();
				if (comboBoxSDestino.getSelectedItem().toString().equals("Seleccione"))
					sucursalDestino = null;
				else
					sucursalDestino = comboBoxSDestino.getSelectedItem().toString();
				if (comboBoxEstado.getSelectedItem().toString().equals("Seleccione"))
					estado = null;
				else
					estado = EstadoSucursal.valueOf(comboBoxEstado.getSelectedItem().toString());

				List<Camino> caminosList = new ArrayList<>();
				caminosList = caminoDAO.buscarCaminos();

				List<Camino> caminosFiltrados = (List<Camino>) caminosList.stream()
						.filter(camino -> id == -1 || camino.getId() == (id))
						.filter(camino -> sucursalOrigen == null || camino.getSO().getNombre().equals(sucursalOrigen))
						.filter(camino -> sucursalDestino == null || camino.getSD().getNombre().equals(sucursalDestino))
						.filter(camino -> estado == null || camino.getEsOperativa().equals(estado))
						.collect(Collectors.toList());

				model.setRowCount(0);
				llenarTabla(caminosFiltrados);

			}
		});
		btnAplicarFiltros.setBounds(246, 142, 113, 21);
		add(btnAplicarFiltros);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 571, 127);
		add(scrollPane);

		tableCaminos = new JTable();
		tableCaminos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tableCaminos.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / tableCaminos.getRowHeight();
				if (row < tableCaminos.getRowCount() && row >= 0 && column < tableCaminos.getColumnCount()
						&& column >= 0) {
					Object value = tableCaminos.getValueAt(row, column);
					if (value instanceof JButton) {
						((JButton) value).doClick();
						JButton boton = (JButton) value;

						if (boton.getName().equals("Eliminar")) {

							Camino c = listaCaminos.get(row);
							eliminarCaminoElegido(c);
							// llenarTabla(gestorCamino.getCaminos());

						}
						if (boton.getName().equals("Modificar")) {
							// Cambiar: el camino c debe obtenerse una lista caminosEnTabla (ya filtrados)
							// no de todos los caminos
							Camino c = listaCaminos.get(row);
							panelModificarCamino.setCaminoSeleccionado(c);
							panelModificarCamino.setCamposAModificar();

							llenarTabla(gestorCamino.getCaminos());
							MenuPrincipal.mostrarPanel("ModificarCamino");
						}

					}
				}
			}
		});

		// Defino mi propio default table model para cargar los datos en la tabla
		model = new MyTableModel();
		tableCaminos.setDefaultRenderer(Object.class, new RenderTabla()); // renderizar tabla
		tableCaminos.setModel(model);

		model.addColumn("ID");
		model.addColumn("Origen");
		model.addColumn("Destino");
		model.addColumn("Estado");
		model.addColumn("Modificar");
		model.addColumn("Eliminar");

		tableCaminos.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableCaminos.getColumnModel().getColumn(1).setMinWidth(95);
		tableCaminos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableCaminos.getColumnModel().getColumn(2).setMinWidth(80);
		scrollPane.setViewportView(tableCaminos);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				MenuPrincipal.mostrarPanel("GestionarCamino");
			}
		});
		btnVolver.setBounds(255, 324, 85, 21);
		add(btnVolver);

	}

	public void eliminarCaminoElegido(Camino camino) {

		String[] botones = { "Si", "Cancelar" };
		int i = JOptionPane.showOptionDialog(this, "¿Estas seguro de eliminar el camino seleccionado?",
				"Muchas respuestas", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones,
				botones[0]);
		/*
		 * int i= JOptionPane.showConfirmDialog(this,
		 * "¿Estas seguro de cancelar la operacion?");
		 */
		if (i == 0) {
			gestorCamino.eliminarCamino(camino);
			model.setRowCount(0);
			llenarTabla(caminoDAO.buscarCaminos());

		String [] botones = {"Si", "Cancelar"};
		int i= JOptionPane.showOptionDialog(this, "¿Estas seguro de eliminar el camino seleccionado?", "Muchas respuestas",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);
		/*int i= JOptionPane.showConfirmDialog(this, "¿Estas seguro de cancelar la operacion?");*/
		if(i==0) {
			gestorCamino.eliminarCamino(camino);	
	        model.setRowCount(0);
	        llenarTabla(caminoDAO.buscarCaminos());	

		}

	}

	public void llenarTabla(List<Camino> listaCaminos) {

		this.listaCaminos = listaCaminos;
		for (Camino c : listaCaminos) {
			Object[] fila = new Object[6];// columnas

			fila[0] = c.getId();
			fila[1] = c.getSO().getNombre();
			fila[2] = c.getSD().getNombre();
			fila[3] = c.getEsOperativa();

			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setName("Eliminar");
			JButton btnModificar = new JButton("Modificar");
			btnModificar.setName("Modificar");

			fila[4] = btnModificar;
			fila[5] = btnEliminar;

			model.addRow(fila);
		}
	}

	public void limpiarTabla() {
		for (int i = 0; i < 4; i++) {
			Object[] fila = new Object[6];// columnas

			fila[0] = null;
			fila[1] = null;
			fila[2] = null;
			fila[3] = null;

			/*
			 * JButton btnEliminar = new JButton("Eliminar");
			 * btnEliminar.setName("Eliminar"); JButton btnModificar = new
			 * JButton("Modificar"); btnModificar.setName("Modificar");
			 */

			fila[4] = null;
			fila[5] = null;

			model.addRow(fila);
		}
	}

	public void setPanelModificarCamino(JPanelModificarCamino panelModificarCamino) {
		this.panelModificarCamino = panelModificarCamino;
	}

	public void actualizarSucursales() {
		gestorSucursal.refreshSucursales();
		this.sucursales = gestorSucursal.getSucursales();

		// ComboBox sucursal origen
		comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setEditable(true);
		comboBoxOrigen.setBounds(379, 65, 130, 22);

		String seleccionar = "Seleccione";
		comboBoxOrigen.setEditable(true);

		modeloOrigen = new DefaultComboBoxModel();
		modeloOrigen.addElement(seleccionar);
		for (Sucursal s : sucursales) {
			Object nombre = new Object();
			nombre = s.getNombre();
			modeloOrigen.addElement(nombre);
		}
		comboBoxOrigen.setModel(modeloOrigen);

		// ComboBox sucursal destino
		String eleccion = comboBoxOrigen.getSelectedItem().toString();
		seleccionar = "Seleccione";
		modeloDestino = new DefaultComboBoxModel();
		modeloDestino.addElement(seleccionar);
		// CARGAMOS LOS NOMBRES DE LAS SUCURSALES
		for (Sucursal s : sucursales) {
			Object nombre = new Object();
			nombre = s.getNombre();
			if (nombre != eleccion)
				modeloDestino.addElement(nombre);
		}
		comboBoxSDestino.setModel(modeloDestino);
	}

}
