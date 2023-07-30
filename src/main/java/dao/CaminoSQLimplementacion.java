package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import clases.Camino;
import conexion.Conexion;
import enums.EstadoSucursal;

public class CaminoSQLimplementacion {
	

	public static void crearCamino(Camino camino) {
		// CREO UN CAMINO ES DECIR ALTA CAMINO
		
		String consulta = "insert into tpdied.camino (id_camino,"+ "sucursal_origen," + "sucursal_destino,"+ "estado," + "capacidad_max," + "tiempo_transito) values (?,?,?,?,?,?);"; 
		Conexion conexion = new Conexion();
		Connection cn = null; //para conectar a la bd
		PreparedStatement cs = null;//para hacer las consultas SQL
		ResultSet rs = null;
		try {
			
		//	CallableStatement cs= conexion.conectar().prepareCall(consulta); 
			cn = conexion.conectar();
			cs = cn.prepareStatement(consulta);
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
			cs.executeUpdate();
			//mensaje de confirmacion
			JOptionPane.showMessageDialog(null, "Se insertaron correctamente los datos");
				
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No se insertaron correctamente los datos, error "+ e.toString());
		}finally {
			//Para liberar recursos
			try {
				if(rs != null) {
					rs.close();
				}
				if(cs != null) {
					cs.close();
				}
				if(cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
}
