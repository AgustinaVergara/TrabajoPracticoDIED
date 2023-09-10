package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import clases.OrdenDeProvision;
import clases.Sucursal;
import gestores.GestorOrden;
import gestores.GestorSucursal;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class JPanelListadoOrden extends JPanel {

	private JTable tableOrdenes;
	private JTable tableCaminos;
	private MyTableModel modelTableOrdenes;
	private MyTableModel modelTableCaminos;

	private GestorOrden gestorOrden = GestorOrden.getInstance();
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();

	/**
	 * Create the panel.
	 */
	public JPanelListadoOrden() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("LISTADO DE ORDENES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(162, 10, 266, 13);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Listado de órdenes");
		lblNewLabel_1.setBounds(46, 33, 126, 20);
		add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(164, 44, 426, 2);
		add(separator);

		JLabel lblNewLabel_1_1 = new JLabel("Caminos posibles");
		lblNewLabel_1_1.setBounds(46, 155, 126, 20);
		add(lblNewLabel_1_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(162, 165, 428, 2);
		add(separator_1);

		JScrollPane scrollPaneOrdenes = new JScrollPane();
		scrollPaneOrdenes.setBounds(20, 56, 570, 89);
		add(scrollPaneOrdenes);

		tableOrdenes = new JTable();
		scrollPaneOrdenes.setRowHeaderView(tableOrdenes);
		tableOrdenes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tableOrdenes.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / tableOrdenes.getRowHeight();

				if (row < tableOrdenes.getRowCount() && row >= 0 && column < tableOrdenes.getColumnCount()
						&& column >= 0) {
					Object value = tableOrdenes.getValueAt(row, column);
					if (value instanceof JButton) {
						((JButton) value).doClick();
						JButton boton = (JButton) value;
						if (boton.getName().equals("Eliminar")) {
							OrdenDeProvision o = gestorOrden.getOrdenes().get(row);
							// Mostrar ventana de confirmación
							int option = JOptionPane.showConfirmDialog(null,
									"¿Estás seguro de que quieres eliminar la orden de provisión?",
									"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

							if (option == JOptionPane.YES_OPTION) {
								// El usuario confirmó eliminar la sucursal
								gestorOrden.eliminarOrden(o);
								modelTableOrdenes.setRowCount(0);
								llenarTablaOrdenes(gestorOrden.getOrdenes());
							}
						}

					}
				}
			}
		});

		// Defino mi propio default table model para cargar los datos en la tabla
		modelTableOrdenes = new MyTableModel();
		tableOrdenes.setDefaultRenderer(Object.class, new RenderTabla()); // renderizar tabla
		tableOrdenes.setModel(modelTableOrdenes);

		modelTableOrdenes.addColumn("Fecha");
		modelTableOrdenes.addColumn("Sucursal origen");
		modelTableOrdenes.addColumn("Sucursal  destino");
		modelTableOrdenes.addColumn("Estado");
		modelTableOrdenes.addColumn("Eliminar");
		modelTableOrdenes.addColumn("Asignar");

		tableOrdenes.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableOrdenes.getColumnModel().getColumn(1).setMinWidth(95);
		tableOrdenes.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableOrdenes.getColumnModel().getColumn(2).setMinWidth(80);
		scrollPaneOrdenes.setViewportView(tableOrdenes);

		// Tabla de caminos posibles

		JScrollPane scrollPaneCaminosPosibles = new JScrollPane();
		scrollPaneCaminosPosibles.setBounds(20, 185, 570, 97);
		add(scrollPaneCaminosPosibles);

		tableCaminos = new JTable();
		scrollPaneCaminosPosibles.setRowHeaderView(tableCaminos);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("GestionarOrden");
			}
		});
		btnVolver.setBounds(261, 299, 85, 21);
		add(btnVolver);

	}

	public void llenarTablaOrdenes(List<OrdenDeProvision> ordenes) {
		modelTableOrdenes.setRowCount(0);

		for (OrdenDeProvision o : ordenes) {
			Object[] fila = new Object[6];

			fila[0] = o.getFechaOrden();
			if(o.getSucursalOrigenId() != null) {
				fila[1] = gestorSucursal.getSucursalPorId(o.getSucursalOrigenId()).getNombre();
			}else {
				fila[1] = "";
			}
			
			fila[2] = gestorSucursal.getSucursalPorId(o.getSucursalDestinoId()).getNombre();
			fila[3] = o.getEstado();

			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setName("Eliminar");
			JButton btnAsignarCamino = new JButton("Asignar");
			btnAsignarCamino.setName("Asignar");
			

			fila[4] = btnEliminar;
			fila[5] = btnAsignarCamino;

			modelTableOrdenes.addRow(fila);
		}
	}

}
