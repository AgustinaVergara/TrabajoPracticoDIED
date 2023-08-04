package interfaces;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import clases.Sucursal;
import gestores.GestorSucursal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfazListadoSucursal extends JFrame {

	private JPanel contentPane;
	private JTable tableSucursales;
	private MyTableModel model;
	
	private InterfazGestionarSucursal ventanaGestionarSucursal; 
	private JTextField txtNombre;
	private JTextField txtHoraApertura;
	private JTextField txtMinutoApertura;
	private JTextField txtHoraCierre;
	private JTextField txtMinutoCierre;
	private List<Sucursal> listaSucursales;
	
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();

	/**
	 * Create the frame.
	 */
	public InterfazListadoSucursal(InterfazGestionarSucursal ventanaGestionarSucursal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTADO DE SUCURSALES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(197, 23, 196, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 571, 127);
		contentPane.add(scrollPane);
		
		tableSucursales = new JTable();
		tableSucursales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tableSucursales.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY()/tableSucursales.getRowHeight();
				if(row < tableSucursales.getRowCount() && row >= 0 && column < tableSucursales.getColumnCount() && column >=0) {
					Object value = tableSucursales.getValueAt(row, column);
					if(value instanceof JButton) {
						((JButton)value).doClick();
						JButton boton = (JButton) value;
						if(boton.getName().equals("Eliminar")) {
							Sucursal s = listaSucursales.get(row); 
							 // Mostrar ventana de confirmación
						    int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar la sucursal?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
						    
						    if (option == JOptionPane.YES_OPTION) {
						        // El usuario confirmó eliminar la sucursal
						        gestorSucursal.eliminarSucursal(s);
						        model.setRowCount(0);
						        llenarTabla(gestorSucursal.getSucursales());						    
						    }
						}
						if(boton.getName().equals("Modificar")) {
							Sucursal s = listaSucursales.get(row); 

							final InterfazModificarSucursal ventanaModificarSucursal = new InterfazModificarSucursal(s);

							ventanaModificarSucursal.setVisible(true); 
							setVisible(false);
						}
						
					}
				}
			}
		});
		
		//Defino mi propio default table model para cargar los datos en la tabla
		model = new MyTableModel();
		tableSucursales.setDefaultRenderer(Object.class, new RenderTabla()); //renderizar tabla
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
		btnVolver.setBounds(255, 324, 85, 21);
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setBounds(97, 46, 79, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(97, 69, 64, 13);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(154, 66, 138, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Estado:");
		lblNewLabel_3.setBounds(97, 105, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setBounds(154, 101, 138, 21);
		contentPane.add(comboBoxEstado);
		
		JLabel horarioApertura = new JLabel("Hora apertura:");
		horarioApertura.setBounds(316, 69, 85, 13);
		contentPane.add(horarioApertura);
		
		txtHoraApertura = new JTextField();
		txtHoraApertura.setColumns(10);
		txtHoraApertura.setBounds(403, 66, 45, 19);
		contentPane.add(txtHoraApertura);
		
		JLabel separador_1 = new JLabel(":");
		separador_1.setBounds(452, 69, 27, 13);
		contentPane.add(separador_1);
		
		txtMinutoApertura = new JTextField();
		txtMinutoApertura.setColumns(10);
		txtMinutoApertura.setBounds(458, 66, 45, 19);
		contentPane.add(txtMinutoApertura);
		
		JLabel lblHoraCierre = new JLabel("Hora cierre:");
		lblHoraCierre.setBounds(316, 102, 85, 13);
		contentPane.add(lblHoraCierre);
		
		txtHoraCierre = new JTextField();
		txtHoraCierre.setColumns(10);
		txtHoraCierre.setBounds(403, 99, 45, 19);
		contentPane.add(txtHoraCierre);
		
		JLabel separador_1_1 = new JLabel(":");
		separador_1_1.setBounds(452, 102, 27, 13);
		contentPane.add(separador_1_1);
		
		txtMinutoCierre = new JTextField();
		txtMinutoCierre.setColumns(10);
		txtMinutoCierre.setBounds(458, 99, 45, 19);
		contentPane.add(txtMinutoCierre);
		
		JButton btnAplicarFiltros = new JButton("Aplicar  filtros");
		btnAplicarFiltros.setBounds(246, 142, 113, 21);
		contentPane.add(btnAplicarFiltros);
	}
	
	public void llenarTabla(List<Sucursal> listaSucursales) {
		
		this.listaSucursales = listaSucursales;
		
		for(Sucursal s : listaSucursales) {
			Object[] fila = new Object[6];//columnas
			
			fila[0] = s.getNombre();
			fila[1] = s.getHorarioApertura();
			fila[2] = s.getHorarioCierre();
			fila[3] = s.getEstado();
			
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setName("Eliminar");
			JButton btnModificar = new JButton("Modificar");
			btnModificar.setName("Modificar");
			
			fila[4] = btnModificar; 
			fila[5] = btnEliminar;
			
			model.addRow(fila);
		}
	}
}
