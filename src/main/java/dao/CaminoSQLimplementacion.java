package dao;

import java.sql.CallableStatement;

import javax.swing.JOptionPane;

import clases.Camino;
import conexion.Conexion;
import enums.EstadoSucursal;

public class CaminoSQLimplementacion {
	

	public static void crearCamino(Camino camino) {
		// CREO UN CAMINO ES DECIR ALTA CAMINO
		Conexion conexion = new Conexion();
		String consulta = "insert into camino (id_camino," //1
				+ "sucursal_origen," //2
				+ "sucursal_destino," //3
				+ "estado," //4
				+ "capacidad_max," //5
				+ "tiempo_transito) values (?,?,?,?,?,?);"; //6
		
		try {
			// utulizamos la conexion de la clase CONEXION del paquete CONEXION 
			CallableStatement cs= conexion.conectar().prepareCall(consulta); 
			//INCORPORAMOS PARAMETROS DE ARRIBA (los values)
			cs.setInt(1, camino.getId());
			cs.setString(2, camino.getSO().getNombre());
			cs.setString(3, camino.getSD().getNombre());
				if(camino.getEsOperativa() == EstadoSucursal.NO_OPERATIVA) {
					String estado = "NO OPERATIVA";
					cs.setString(4, estado);
				} else {
					String estado = "OPERATIVA";
					cs.setString(4, estado);}
			cs.setDouble(5, camino.getCapacidadMax());
			cs.setInt(6, camino.getTiempoTransito());
			//EJECUTAMOS
			cs.execute();
			//mensaje de confirmacion
			JOptionPane.showMessageDialog(null, "Se insertaron correctamente los datos");
				
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No se insertaron correctamente los datos, error "+ e.toString());
		}
	}
	
	
}
