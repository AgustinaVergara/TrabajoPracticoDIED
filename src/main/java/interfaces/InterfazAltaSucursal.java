package interfaces;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import clases.Sucursal;
import excepciones.CampoInvalidoException;
import excepciones.CampoVacioException;
import excepciones.NombreSucursalExistenteException;
import gestores.GestorSucursal;
import enums.EstadoSucursal;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class InterfazAltaSucursal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtHoraApertura;
	private JTextField txtMinutoApertura;
	private JTextField txtHoraCierre;
	private JTextField txtMinutoCierre;
	
	private GestorSucursal gestorSucursal = GestorSucursal.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazAltaSucursal frame = new InterfazAltaSucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazAltaSucursal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloAltaSucursal = new JLabel("CREAR NUEVA SUCURSAL");
		tituloAltaSucursal.setBounds(161, 23, 190, 13);
		contentPane.add(tituloAltaSucursal);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(161, 46, 160, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel nombreSucursal = new JLabel("Nombre ");
		nombreSucursal.setBounds(36, 49, 96, 13);
		contentPane.add(nombreSucursal);

		JLabel horarioApertura = new JLabel("Hora apertura");
		horarioApertura.setBounds(37, 90, 114, 13);
		contentPane.add(horarioApertura);
		
		txtHoraApertura = new JTextField();
		txtHoraApertura.setBounds(161, 87, 45, 19);
		contentPane.add(txtHoraApertura);
		txtHoraApertura.setColumns(10);
		
		txtMinutoApertura = new JTextField();
		txtMinutoApertura.setColumns(10);
		txtMinutoApertura.setBounds(216, 87, 45, 19);
		contentPane.add(txtMinutoApertura);
		
		JLabel horarioCierre = new JLabel("Hora cierre");
		horarioCierre.setBounds(36, 128, 80, 13);
		contentPane.add(horarioCierre);
		
		txtHoraCierre = new JTextField();
		txtHoraCierre.setColumns(10);
		txtHoraCierre.setBounds(161, 122, 45, 19);
		contentPane.add(txtHoraCierre);
		
		txtMinutoCierre = new JTextField();
		txtMinutoCierre.setColumns(10);
		txtMinutoCierre.setBounds(216, 122, 45, 19);
		contentPane.add(txtMinutoCierre);
		
		JLabel separador_1 = new JLabel(":");
		separador_1.setBounds(210, 90, 27, 13);
		contentPane.add(separador_1);
		
		JLabel separador_2 = new JLabel(":");
		separador_2.setBounds(210, 125, 27, 13);
		contentPane.add(separador_2);
		
		JLabel EstadoSucursal = new JLabel("Estado");
		EstadoSucursal.setBounds(37, 169, 45, 13);
		contentPane.add(EstadoSucursal);
		
		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setBounds(161, 165, 160, 21);
		contentPane.add(comboBoxEstado);
		comboBoxEstado.addItem("OPERATIVA");
		comboBoxEstado.addItem("NO_OPERATIVA");
		
		JButton btnGuardarSucursal = new JButton("Guardar");
		btnGuardarSucursal.setBounds(193, 213, 85, 21);
		contentPane.add(btnGuardarSucursal);
		
		
		btnGuardarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				try {
					campoVacio();
					campoValido();
					nombreSucursalExistente();
	
					
					Sucursal nuevaSucursal = gestorSucursal.crearSucursalGestor(txtNombre.getText(),
							LocalTime.of(Integer.parseInt(txtHoraApertura.getText()), Integer.parseInt(txtMinutoApertura.getText())),
							LocalTime.of(Integer.parseInt(txtHoraCierre.getText()), Integer.parseInt(txtMinutoCierre.getText())),
							enums.EstadoSucursal.valueOf(comboBoxEstado.getSelectedItem().toString()));
					
					gestorSucursal.agregarSucursal(nuevaSucursal);
					
					
				}catch(CampoVacioException e1) {
					JOptionPane.showMessageDialog(frame,
							"Faltan completar los siguientes campos:\n\n"+e1.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}catch (CampoInvalidoException e2) {
					
					JOptionPane.showMessageDialog(frame,
							e2.getMessage()+"- El nombre puede tener como m�ximo 30 caracteres de longitud. \n"+
									  "- La hora debe encontrarse en el intervalo [0, 23]. \n"+
									  "- Los minutos deben encontrarse en el intervalo [0,59].\n"+
									  "- La hora de cierre debe ser mayor a la hora de inicio.\n",	
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}catch (NombreSucursalExistenteException e3) {
					
					JOptionPane.showMessageDialog(frame,
							e3.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
			
	}
	
	public void campoVacio() throws CampoVacioException{
		String error = "";
		boolean algunoVacio = false;
		
		if(txtNombre.getText().isEmpty()) {
			error += "- Nombre\n";
			algunoVacio = true;
		}
		
		if(txtHoraApertura.getText().isEmpty() || txtMinutoApertura.getText().isEmpty()) {
			error += "- Hora de apertura\n";
			algunoVacio = true;
		}
		
		if(txtHoraCierre.getText().isEmpty() || txtMinutoCierre.getText().isEmpty()) {
			error += "- Hora de cierre\n";
			algunoVacio = true;
		}
		
		if(algunoVacio) {
			
			throw new CampoVacioException(error);
		}
				
	}
	
	public void campoValido() throws CampoInvalidoException{
		
		if(!validarHora(txtHoraApertura) || !validarMinuto(txtMinutoApertura) ||
		   !validarHora(txtHoraCierre)   || !validarMinuto(txtMinutoCierre)   || !validarNombre(txtNombre)
		   || !horasValidas(txtHoraApertura, txtMinutoApertura, txtHoraCierre, txtMinutoCierre))

				throw new CampoInvalidoException();
	}
	
	public void nombreSucursalExistente() throws NombreSucursalExistenteException{ 
		
		for(Sucursal e : gestorSucursal.getSucursales()) {
			
			if(e.getNombre().equals(txtNombre.getText()))
				throw new NombreSucursalExistenteException();
		}
	}
	
	public boolean validarHora(JTextField field) { //Retorna false si no es integer o si no se encuentra en el rango [0, 23]
		
		try {
			Integer hora = Integer.parseInt(field.getText());
			
			if(hora > -1 && hora < 24) {
				
				return true; 
			}
			else {
				
				return false;
			}
			
		} catch(NumberFormatException e) {
			
			return false;
		}
	}
	
	public boolean validarMinuto(JTextField field) { //Retorna false si no es integer o si no se encuentra en el rango [0, 59]
		
		try {
			
			Integer minuto = Integer.parseInt(field.getText());
			
			if(minuto > -1 && minuto < 60) {
				
				return true; 
			}
			else {
				
				return false;
			}
			
		} catch(NumberFormatException e) {
			
			return false;
		}
	}
	
	public boolean validarNombre(JTextField field) { //Retorna false si la longitud del string es mayor a 30
		
		if(field.getText().length() > 30)
			return false;
		
		return true;
	}
	
	public boolean horasValidas(JTextField horaApertura, JTextField minutoApertura, JTextField horaCierre, JTextField minutoCierre) {
		
		Integer horaA = Integer.parseInt(horaApertura.getText());
		Integer horaC = Integer.parseInt(horaCierre.getText());
		Integer minutoA = Integer.parseInt(minutoApertura.getText());
		Integer minutoC = Integer.parseInt(minutoCierre.getText());
		
		if(horaC > horaA) {	//Si la hora de cierre es mayor, los minutos no importan (horas validas)
			return true;
		}
		else if(horaC == horaA){	//Si las horas son iguales, debo comparar minutos
			
			if(minutoC > minutoA) { //Minuto de cierre mayor (horas validas)
				return true;
			}
			else {
				return false;	//Minuto de apertura mayor (horas invalidas)
			}
		}
		else {	//Si la hora de apertura es mayor a la de cierre, los minutos no importan (horas invalidas)
			return false;
		}
	}
}
