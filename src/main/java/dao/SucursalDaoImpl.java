package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import clases.Sucursal;
import conexion.Conexion;
import enums.EstadoSucursal;

public class SucursalDaoImpl implements SucursalDao{
	/*
	public Sucursal crearSucursal(Sucursal sucursal) {
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		Statement st = null; //para hacer las consultas SQL
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			st = cn.createStatement();
			st.executeQuery("INSERT INTO sucursal (idSucursal, nombre, horarioApertura, horarioCierre, estado) " +
                    "VALUES (?, ?, ?, ?, ?)");
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	*/
	
	//Para poder probar el sucursal necesito la interfaz
	public void crearSucursal(Sucursal sucursal) {
		String consulta = 	"INSERT INTO tpdied.sucursal "
				+ 	"VALUES (?, ?, ?, ?);";
		
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null;//para hacer las consultas SQL
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			//st.setInt(1, this.getUltimoIdEstacion()+1);
			st.setString(2, sucursal.getNombre());
			st.setTime(3, Time.valueOf(sucursal.getHorarioApertura()));
			st.setTime(4, Time.valueOf(sucursal.getHorarioCierre()));	
			st.setString(5, sucursal.getEstado().toString());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//Para liberar recursos
			try {
				if(rs != null) {
					rs.close();
				}
				if(st != null) {
					st.close();
				}
				if(cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Sucursal> buscarSucursales(){
		List<Sucursal> sucursales = new ArrayList<>();
		
		String consulta = "SELECT * "
				+ "FROM tpdied.sucursal "
				+ "ORDER BY 1"	;
		
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null; //para hacer las consultas SQL
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			rs = st.executeQuery();
			
			//En rs esta la tabla de sucursal y ahora hay que recorrerla fila por fila
			while(rs.next()) {
				Integer id = rs.getInt(1);
				String nombre = rs.getString(2);
				LocalTime horarioApertura = rs.getTime(3).toLocalTime();
				LocalTime horarioCierre = rs.getTime(4).toLocalTime();
				String estado = rs.getString(5);
				
				if (estado.equals("OPERATIVA")) sucursales.add(new Sucursal(id, nombre, horarioApertura, horarioCierre, EstadoSucursal.OPERATIVA ));
				else sucursales.add(new Sucursal(id, nombre, horarioApertura, horarioCierre, EstadoSucursal.NO_OPERATIVA));
				
				System.out.println(id + " " + nombre + " " + horarioApertura + " " + horarioCierre + " " + estado );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//Para liberar recursos
			try {
				if(rs != null) {
					rs.close();
				}
				if(st != null) {
					st.close();
				}
				if(cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return sucursales;
	}
	
	
	public static void main(String[] args) {
		
		
	}
	

}