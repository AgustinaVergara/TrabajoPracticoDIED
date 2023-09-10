package dao;

//import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import clases.Camino;
import clases.Sucursal;
import conexion.Conexion;
import enums.EstadoSucursal;
import gestores.GestorCamino;
import gestores.GestorSucursal;

public class CaminoSQLimplementacion implements CaminoDao{
	

	public void crearCamino(Camino camino) {
		// CREO UN CAMINO ES DECIR ALTA CAMINO
		
		String consulta = "INSERT INTO tpdied.camino "
				+ "VALUES (?,?,?,?,?,?);"; 
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
			//System.out.println("antes del execute");
			cs.executeUpdate();
			//System.out.println("dsp del execute");
			
			//mensaje de confirmacion
			//JOptionPane.showMessageDialog(null, "Se insertaron correctamente los datos");
				
		//}catch(Exception e) {
			//JOptionPane.showMessageDialog(null, "No se insertaron correctamente los datos, error "+ e.toString());
		}catch (SQLException e) {
			e.printStackTrace();
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

	@Override
	public List<Camino> buscarCaminos(){
		List<Camino> caminos = new ArrayList<>();
		
		String consulta = "SELECT * "
				+ "FROM tpdied.camino "
				+ "ORDER BY 1"	;
		
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null; //para hacer las consultas SQL
		ResultSet rs = null;
		GestorSucursal gestorSucursal = GestorSucursal.getInstance();
		
		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			rs = st.executeQuery();
			
			//En rs esta la tabla de sucursal y ahora hay que recorrerla fila por fila
			
			while(rs.next()) {
				Integer id = rs.getInt(1);
				String nombreSo = rs.getString(2);
				String nombreSD = rs.getString(3);
				String estado = rs.getString(4);
				Double capacidad= rs.getDouble(5);
				Integer tiempo= rs.getInt(6);
				//Sucursal so = gestorCamino.buscarSucursal(nombreSo);
				//Sucursal sd = gestorCamino.buscarSucursal(nombreSD);
				
				if (estado.equals("OPERATIVA")) {
					caminos.add(new Camino(id, gestorSucursal.buscarSucursalxNombre(nombreSo), gestorSucursal.buscarSucursalxNombre(nombreSD), tiempo, EstadoSucursal.OPERATIVA, capacidad));
				}
				else caminos.add(new Camino(id,gestorSucursal.buscarSucursalxNombre(nombreSo), gestorSucursal.buscarSucursalxNombre(nombreSD), tiempo, EstadoSucursal.NO_OPERATIVA, capacidad));
		
				
			}
			
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(cn != null) {
				cn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//Para liberar recursos

			if (rs != null) {try {rs.close();} catch (Exception e) {e.printStackTrace();}}
			if (st != null) {try {st.close();} catch (Exception e) {e.printStackTrace();}}
			if (cn != null) {try {cn.close();} catch (Exception e) {e.printStackTrace();}}
		}
		
		return caminos;
	}
	

	@Override
	public void eliminar(Camino camino) {
		// TODO Auto-generated method stub
		String consulta = "DELETE FROM tpdied.camino WHERE id_camino = (?);";
		Conexion conexion = new Conexion();
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null; //para hacer las consultas SQL
		
		try {
			cn = conexion.conectar();
			cn.setAutoCommit(false);
			st = cn.prepareStatement(consulta);
			/* CARGAMOS VALORES/PARAMETROS (?) en la declaración SQL usando el 
			 * método setInt() del objeto st. */
			st.setInt(1, camino.getId());
			Integer nro = st.executeUpdate();
			cn.commit();	
		
		}catch(SQLException e) {
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
	public void modificarCamino(Camino caminoAEditar) {
		// TODO Auto-generated method stub
		// CONECTAMOS A LA BD
		String consulta = "UPDATE tpdied.camino "
				+ "SET estado = ?, "
				+ "    capacidad_max = ?, "
				+ "    tiempo_transito = ? " 
				+ " WHERE id_camino = ?;";
	
		
		Conexion conexion = new Conexion();
		
		Connection cn = null;
		PreparedStatement st = null;
		
		try {
			cn = conexion.conectar();
			
			st = cn.prepareStatement(consulta);
		
			st.setString(1, caminoAEditar.getEsOperativa().toString());	
			st.setDouble(2, caminoAEditar.getCapacidadMax());
			st.setInt(3, caminoAEditar.getTiempoTransito());
			st.setInt(4, caminoAEditar.getId());
			st.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se guardaron correctamente los datos");
			
		}  catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al modificar los datos"+ e.toString());
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
	public Integer getUltimoIdCamino() {
		String consultaObtenerID = "SELECT MAX(id_camino) "
						+  "FROM tpdied.camino";
		
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
	


	public List<Camino> buscarxNombreSucursalOrigen(String nombreSucursal) {
		List<Camino> listaCaminos= null;
		GestorCamino gestorCamino = GestorCamino.getInstance();
		String consulta = "select * from tpdied.camino where sucursal_origen= ?";
		
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null; //para hacer las consultas SQL
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			st.setString(1, nombreSucursal);
			rs = st.executeQuery();
			
			//En rs esta la tabla y ahora hay que recorrerla fila por fila
			while(rs.next()) {
				Integer idCamino = rs.getInt(1);
				//String nombreSo = rs.getString(2);
				String nombreSD = rs.getString(3);
				String estado = rs.getString(4);
				Double capacidad= rs.getDouble(5);
				Integer tiempo= rs.getInt(6);
				
				Sucursal so = gestorCamino.buscarSucursal(nombreSucursal);
				Sucursal sd = gestorCamino.buscarSucursal(nombreSD);
				
				if (estado.equals("OPERATIVA")) {
					listaCaminos.add(new Camino(idCamino, so, sd, tiempo, EstadoSucursal.OPERATIVA, capacidad));
				}
				else listaCaminos.add(new Camino(idCamino, so, sd, tiempo, EstadoSucursal.NO_OPERATIVA, capacidad));
				
				System.out.println(idCamino + " " + nombreSucursal + " " + sd + " " + tiempo + " " + estado+" "+capacidad );
				
			}
			
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(cn != null) {
				cn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//Para liberar recursos

			if (rs != null) {try {rs.close();} catch (Exception e) {e.printStackTrace();}}
			if (st != null) {try {st.close();} catch (Exception e) {e.printStackTrace();}}
			if (cn != null) {try {cn.close();} catch (Exception e) {e.printStackTrace();}}
		}

		return listaCaminos;
	}

	
	
}
