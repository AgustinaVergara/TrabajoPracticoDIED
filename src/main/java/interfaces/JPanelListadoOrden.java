package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import clases.Camino;
import clases.ItemProducto;
import clases.OrdenDeProvision;
import clases.StockProducto;
import clases.Sucursal;
import enums.EstadoOrden;
import gestores.GestorGrafo;
import gestores.GestorItemProducto;
import gestores.GestorOrden;
import gestores.GestorStockProducto;
import gestores.GestorSucursal;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class JPanelListadoOrden extends JPanel {

	private JTable tableOrdenes;
	private JTable tableCaminos;
	private MyTableModel modelTableOrdenes;
	private MyTableModel modelTableCaminos;

	private GestorOrden gestorOrden = GestorOrden.getInstance();
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	private GestorGrafo gestorGrafo = GestorGrafo.getInstance();
	private GestorItemProducto gestorItemProducto = GestorItemProducto.getInstance();
	private GestorStockProducto gestorStockProducto = GestorStockProducto.getInstance();
	
	private List<Sucursal> sucursalesOrigen;
	private List<Sucursal> sucursales;
	private OrdenDeProvision o;
	


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
		lblNewLabel_1_1.setBounds(46, 174, 126, 20);
		add(lblNewLabel_1_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(162, 184, 428, 2);
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
							o = gestorOrden.getOrdenes().get(row);
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
						if(boton.getName().equals("Asignar")) {
							o = gestorOrden.getOrdenes().get(row);
							sucursalesOrigen = obtenerSucursalesCandidatas(o);
							for(Sucursal s : sucursalesOrigen) {
								llenarTablaCaminosPosibles(s, gestorSucursal.getSucursalPorId(o.getSucursalDestinoId()));
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
		scrollPaneCaminosPosibles.setBounds(20, 204, 570, 97);
		add(scrollPaneCaminosPosibles);

		tableCaminos = new JTable();
		scrollPaneCaminosPosibles.setRowHeaderView(tableCaminos);
//		
		tableCaminos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component frame = null;
				int column = tableCaminos.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / tableCaminos.getRowHeight();

				if (row < tableCaminos.getRowCount() && row >= 0 && column < tableOrdenes.getColumnCount()
						&& column >= 0) {
					Object value = tableCaminos.getValueAt(row, column);
					if (value instanceof JButton) {
						((JButton) value).doClick();
						JButton boton = (JButton) value;
						if (boton.getName().equals("Ver")) {
							// aca tengo que  llamar al metodo en gestor grafo con sucursal origen y destino que tengo aparecen en la tabla
							gestorGrafo.graficarGrafoConCamino(tableCaminos.getValueAt(row, 0).toString(),tableCaminos.getValueAt(row, 1).toString());
						}else 
							if(boton.getName().equals("SeleccionarCamino")) {
							int option = JOptionPane.showConfirmDialog(null,
									"¿Estás seguro de que quieres asignar este camino para la orden de provisión?",
									"Confirmar elección", JOptionPane.YES_NO_OPTION);

							if (option == JOptionPane.YES_OPTION) {
								gestorOrden.setSucursalOrigen(gestorSucursal.buscarSucursalxNombre(tableCaminos.getValueAt(row, 0).toString()), o.getIdOrdenProvision());
								gestorOrden.refreshOrdenes();
								llenarTablaOrdenes(gestorOrden.getOrdenes());
								JOptionPane.showMessageDialog(frame, "Camino asignado con éxito. Su orden esta EN PROCESO", "Éxito", JOptionPane.INFORMATION_MESSAGE);
								modelTableCaminos.setRowCount(0);
					
							}
						}

					}
				}
			}
		});

		// Defino mi propio default table model para cargar los datos en la tabla
		modelTableCaminos = new MyTableModel();
		tableCaminos.setDefaultRenderer(Object.class, new RenderTabla()); // renderizar tabla
		tableCaminos.setModel(modelTableCaminos);

		
		modelTableCaminos.addColumn("Sucursal origen");
		modelTableCaminos.addColumn("Sucursal destino");
		modelTableCaminos.addColumn("Tiempo máximo");
		modelTableCaminos.addColumn("Ver grafo");
		modelTableCaminos.addColumn("Seleccionar");
		

		scrollPaneCaminosPosibles.setViewportView(tableCaminos);


		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal.mostrarPanel("GestionarOrden");
				modelTableCaminos.setRowCount(0);
			}
		});
		btnVolver.setBounds(261, 311, 85, 21);
		add(btnVolver);
		
		JButton btnVerGrafo = new JButton("Ver grafo");
		btnVerGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorGrafo.graficarGrafo();
			}
		});
		btnVerGrafo.setBounds(249, 155, 100, 21);
		add(btnVerGrafo);

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
			
			  // Verifica si el estado es "EN_PROCESO" y deshabilita los botones en consecuencia
	        if ((o.getEstado()).equals(EstadoOrden.EN_PROCESO)) {
	            btnEliminar.setEnabled(false);
	            btnAsignarCamino.setEnabled(false);
	        }

			fila[4] = btnEliminar;
			fila[5] = btnAsignarCamino;

			modelTableOrdenes.addRow(fila);
		}
	}

	public void llenarTablaCaminosPosibles(Sucursal SO,Sucursal SD) {		
		
		Object[] fila = new Object[5];
		fila[0] = SO.getNombre();
		fila[1] = SD.getNombre();
		
		fila[2] = gestorGrafo.calcularTiempoEntreSucursales(SO.getNombre(), SD.getNombre(),gestorGrafo.crearGrafo());
		JButton btnVerGrafo= new JButton("Ver");
		btnVerGrafo.setName("Ver");
		fila[3] = btnVerGrafo;
		
		JButton btnSeleccionarCamino = new JButton("SeleccionarCamino");
		btnSeleccionarCamino.setName("SeleccionarCamino");
		fila[4] = btnSeleccionarCamino;
		
		modelTableCaminos.addRow(fila);
		
	}
	
	public List<Sucursal> obtenerSucursalesCandidatas(OrdenDeProvision orden){
		sucursales = gestorSucursal.getSucursales();
		List<ItemProducto> itemsProductoOrden = gestorItemProducto.filtrarItemProductoXOrden(orden.getIdOrdenProvision());
		List<StockProducto> listaStockProducto = gestorStockProducto.getListaStockProducto();
		
		List<Sucursal> sucursalesConStockSuficiente = sucursales.stream()
			    .filter(sucursal -> listaStockProducto.stream()
			        .filter(stock -> stock.getIdSucursal() == sucursal.getId())
			        .filter(stock -> {
			            // Buscamos el ItemProducto correspondiente
			            Optional<ItemProducto> item = itemsProductoOrden.stream()
			                .filter(itemProducto -> itemProducto.getIdProducto() == stock.getIdProducto())
			                .findFirst();

			            // Comparamos el stock con la cantidad en ItemProducto
			            return item.isPresent() && stock.getStock() >= item.get().getCantidad();
			        })
			        .findAny()
			        .isPresent())
			    .collect(Collectors.toList());
		
		List<Sucursal> sucursalOrigenCandidata = new ArrayList<>();
		for(Sucursal s : sucursalesConStockSuficiente) {
			int tiempoEntreSuc = gestorGrafo.calcularTiempoEntreSucursales(s.getNombre(), gestorSucursal.getSucursalPorId(orden.getSucursalDestinoId()).getNombre(),gestorGrafo.crearGrafo());
			
			if(tiempoEntreSuc <= orden.getTiempoMax()) {
				sucursalOrigenCandidata.add(s);
			}
		}
		
		
		return sucursalOrigenCandidata;
	}
}
