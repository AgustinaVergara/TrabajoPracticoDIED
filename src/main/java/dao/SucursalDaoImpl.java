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
	
	
	public void crearSucursal(Sucursal sucursal) {
		String consulta = 	"INSERT INTO tpdied.sucursal "
				+ 	"VALUES (?, ?, ?, ?, ?);";
		
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null;//para hacer las consultas SQL
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			st.setInt(1, this.getUltimoIdSucursal()+1);
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

	@Override
	public Integer getUltimoIdSucursal() {
		String consultaObtenerID = "SELECT MAX(idsucursal) "
						+  "FROM tpdied.sucursal";
		
		Integer id = null;
		

		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null; //para hacer las consultas SQL
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consultaObtenerID);
			rs = st.executeQuery();
			
			rs.next();
			id = rs.getInt(1);
			if (rs.wasNull()) {
				id = 0;
			}
			rs.close();
			st.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) {try {rs.close();} catch (Exception e) {e.printStackTrace();}}
			if (st != null) {try {st.close();} catch (Exception e) {e.printStackTrace();}}
			if (cn != null) {try {cn.close();} catch (Exception e) {e.printStackTrace();}}
		}
		
		return id;
	}


	@Override
	public void eliminar(Sucursal s) {
		//Modificar consulta una vez que conecte a los caminos. Borrar una tupla de la tabla implica 
		//borrar todas las instancias que la tengan como clave for
		String consulta = "DELETE FROM tpdied.sucursal WHERE idsucursal = (?);";
		
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null; //para hacer las consultas SQL
		
		
		try {
			cn = conexion.conectar();
			cn.setAutoCommit(false);
			
			st = cn.prepareStatement(consulta);
			

			/*Esto se utiliza para establecer los valores de los parámetros (?) en la declaración SQL usando el 
			 * método setInt() del objeto st. */
			st.setInt(1, s.getId());


			Integer nro = st.executeUpdate();
			cn.commit();
			
		}  catch(SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			if (st != null) {
				try {
					st.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (cn != null) {
				try {
					cn.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
		
	}

	@Override
	public void modificar(Sucursal s) {
		// TODO Auto-generated method stub
		
	}	


}
