package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Grafo;
import clases.Sucursal;

import conexion.Conexion;
import enums.EstadoSucursal;
import gestores.GestorCamino;
import gestores.GestorGrafo;
import clases.Camino;

public class GrafoSQLimplementacion implements GrafoDao{
	public void crearGrafo(Grafo grafo) {
		String consulta = "INSERT INTO tpdied.grafos "
				+ "VALUES (?,?,?,?,?);"; 
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement cs = null;//para hacer las consultas SQL
		ResultSet rs = null;
		try {
			
			//	CallableStatement cs= conexion.conectar().prepareCall(consulta); 
				cn = conexion.conectar();
				cs = cn.prepareStatement(consulta);
				
				//INCORPORAMOS PARAMETROS DE ARRIBA (los values)
				cs.setInt(1, grafo.getTiempo());
				cs.setInt(2, grafo.getNombreOrigen().hashCode());
				cs.setInt(3, grafo.getNombreDestino().hashCode());
				cs.setString(4, grafo.getNombreOrigen());
				cs.setString(5, grafo.getNombreDestino());

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
			}finally {try {
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
			}}
	}
	public List<Grafo> buscarGrafos(){
		List<Grafo> grafos = new ArrayList<>();
		String consulta = "SELECT * "
				+ "FROM tpdied.camino "
				+ "ORDER BY 1"	;
		
		Conexion conexion = new Conexion();
		
		Connection cn = null; //para conectar a la bd
		PreparedStatement st = null; //para hacer las consultas SQL
		ResultSet rs = null;
		GestorGrafo gestor = GestorGrafo.getInstance();
		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			rs = st.executeQuery();
			
			//En rs esta la tabla de sucursal y ahora hay que recorrerla fila por fila
			
			while(rs.next()) {
				Integer arista = rs.getInt(1);
				Integer hashCodeSO = rs.getInt(2);
				Integer hashCodeSD = rs.getInt(3);
				String nombreSO = rs.getString(4);
				String nombreSD= rs.getString(5);
				grafos.add(new Grafo(nombreSO,nombreSD,arista));
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
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//Para liberar recursos

			if (rs != null) {try {rs.close();} catch (Exception e) {e.printStackTrace();}}
			if (st != null) {try {st.close();} catch (Exception e) {e.printStackTrace();}}
			if (cn != null) {try {cn.close();} catch (Exception e) {e.printStackTrace();}}
		}
		return grafos;
	}
	
}
