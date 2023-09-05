package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Sucursal;
import enums.EstadoSucursal;
import excepciones.CampoInvalidoException;
import excepciones.NombreSucursalExistenteException;
import gestores.GestorSucursal;

public class JPanelListadoSucursal extends JPanel {
	
	private JPanel contentPane;
	private JTable tableSucursales;
	private MyTableModel model;
	
	
	private JTextField txtNombre;
	private JTextField txtHoraApertura;
	private JTextField txtMinutoApertura;
	private JTextField txtHoraCierre;
	private JTextField txtMinutoCierre;
	private JComboBox<String> comboBoxEstado;
	
	
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();
	
	private JPanelModificarSucursal panelModificarSucursal;

	
	public JPanelListadoSucursal() {
		setLayout(null);
		JLabel lblNewLabel = new JLabel("LISTADO DE SUCURSALES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(207, 24, 189, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setBounds(48, 47, 113, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(48, 90, 77, 13);
		add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(124, 87, 138, 19);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Estado:");
		lblNewLabel_3.setBounds(48, 126, 77, 13);
		add(lblNewLabel_3);
		
		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setBounds(124, 122, 138, 21);
		comboBoxEstado.setSelectedItem(null);
		comboBoxEstado.addItem("Seleccione");
		comboBoxEstado.addItem("OPERATIVA");
		comboBoxEstado.addItem("NO_OPERATIVA");
		add(comboBoxEstado);
		
		
		JLabel horarioApertura = new JLabel("Hora apertura:");
		horarioApertura.setBounds(327, 91, 85, 13);
		add(horarioApertura);
		
		txtHoraApertura = new JTextField();
		txtHoraApertura.setColumns(10);
		txtHoraApertura.setBounds(420, 90, 45, 19);
		add(txtHoraApertura);
		
		JLabel separador_1 = new JLabel(":");
		separador_1.setBounds(471, 93, 27, 13);
		add(separador_1);
		

		txtMinutoApertura = new JTextField();
		txtMinutoApertura.setColumns(10);
		txtMinutoApertura.setBounds(481, 90, 45, 19);
		add(txtMinutoApertura);
		
		JLabel lblHoraCierre = new JLabel("Hora cierre:");
		lblHoraCierre.setBounds(327, 127, 114, 13);
		add(lblHoraCierre);
		
		txtHoraCierre = new JTextField();
		txtHoraCierre.setColumns(10);
		txtHoraCierre.setBounds(420, 126, 45, 19);
		add(txtHoraCierre);
		
		JLabel separador_1_1 = new JLabel(":");
		separador_1_1.setBounds(471, 129, 27, 13);
		add(separador_1_1);
		
		txtMinutoCierre = new JTextField();
		txtMinutoCierre.setColumns(10);
		txtMinutoCierre.setBounds(481, 126, 45, 19);
		add(txtMinutoCierre);
		
		JButton btnAplicarFiltros = new JButton("Aplicar  filtros");
		btnAplicarFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				List<Sucursal> totalSucursales = gestorSucursal.getSucursales();
				llenarTabla(filtrar(totalSucursales));
			}
		});
		btnAplicarFiltros.setBounds(239, 164, 113, 21);
		add(btnAplicarFiltros);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 195, 580, 112);
		add(scrollPane);
		
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
							Sucursal s = gestorSucursal.getSucursales().get(row); 
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
							//obtener sucursal seleccionada
							List<Sucursal> sucursalesEnTabla = filtrar(gestorSucursal.getSucursales());
							Sucursal s = sucursalesEnTabla.get(row);
					        panelModificarSucursal.setSucursalSeleccionada(s);
					        panelModificarSucursal.setCamposAModificar();					  
					        MenuPrincipal.mostrarPanel("ModificarSucursal");
							
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
				model.setRowCount(0);
				limpiarFiltros();
				MenuPrincipal.mostrarPanel("GestionarSucursal");
			}
		});
		btnVolver.setBounds(239, 317, 113, 21);
		add(btnVolver);
	}
	
	public void llenarTabla(List<Sucursal> listaSucursales) {
		model.setRowCount(0);
		
		for(Sucursal s : listaSucursales) {
			Object[] fila = new Object[6];
			
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
	
	public void setPanelModificarSucursal(JPanelModificarSucursal panelModificarSucursal) {
	    this.panelModificarSucursal = panelModificarSucursal;
	}
	
	public List<Sucursal> filtrar(List<Sucursal> totalSucursales){
		List<Sucursal> sucursalesFiltradas;
		
	 		String nombre = txtNombre.getText();
	 	    LocalTime apertura;
	 	    LocalTime cierre;
	 	    EstadoSucursal estado;
	 	   
	 	    if(!txtHoraApertura.getText().isEmpty() && !txtMinutoApertura.getText().isEmpty()) {
	 	    	apertura = LocalTime.of(Integer.parseInt(txtHoraApertura.getText()), Integer.parseInt(txtMinutoApertura.getText()));
	 	    } else {
	 	    	apertura = null;
	 	    }
	 	    
	 	   if(!txtHoraCierre.getText().isEmpty() && !txtMinutoCierre.getText().isEmpty()) {
	 	    	cierre = LocalTime.of(Integer.parseInt(txtHoraCierre.getText()), Integer.parseInt(txtMinutoCierre.getText()));
	 	    }else {
	 	    	cierre = null;
	 	    }
	 	    
	 		if (comboBoxEstado.getSelectedItem() != "Seleccione") {
	 	        estado = EstadoSucursal.valueOf(comboBoxEstado.getSelectedItem().toString());
	 	    }else {
	 	    	estado = null;
	 	    }
	 		 
	        sucursalesFiltradas = totalSucursales.stream()
	                .filter(sucursal -> nombre.isEmpty()|| sucursal.getNombre().toLowerCase().contains(nombre.toLowerCase()))
	                .filter(sucursal -> apertura == null || sucursal.getHorarioApertura().equals(apertura))
	                .filter(sucursal -> cierre == null || sucursal.getHorarioCierre().equals(cierre))
	                .filter(sucursal -> estado == null || sucursal.getEstado().equals(estado))
	                .collect(Collectors.toList());
	        
	 	
        return sucursalesFiltradas;
	}
	
	public void limpiarFiltros () {
		txtNombre.setText("");
		txtHoraApertura.setText("");
		txtMinutoApertura.setText("");
		txtHoraCierre.setText("");
		txtMinutoCierre.setText("");		
		comboBoxEstado.setSelectedItem(null);
		comboBoxEstado.setSelectedItem("Seleccione");
		
	}
	
}
