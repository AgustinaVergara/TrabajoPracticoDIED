package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import clases.Producto;
import conexion.Conexion;

public class ProductoDaoImpl implements ProductoDao {
	
		public void crearProducto(Producto p) {
			List<Producto> productos = new ArrayList<>();
			
			String consulta = "INSERT INTO tpdied.producto "
					+ 	"VALUES (?,?,?, ?, ?);";
			
			Conexion conexion = new Conexion();
			
			Connection cn = null; //para conectar a la bd
			PreparedStatement st = null; //para hacer las consultas SQL
			
			try {
				cn = conexion.conectar();
				st = cn.prepareStatement(consulta);
				st.setInt(1, this.getUltimoIdP()+1);
				st.setString(2, p.getNombre());
				st.setString(3, p.getDescripcion());
				st.setDouble(4, p.getPrecioUnitario());
				st.setDouble(5, p.getPesoKg());	
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				//Para liberar recursos
				try {
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
		public Integer getUltimoIdP() {
			String consultaObtenerID = "SELECT MAX(idProducto) "
							+  "FROM tpdied.producto";
			
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
		public List<Producto> buscarProducto(){
			List<Producto> productos = new ArrayList<>();
			
			String consulta = "SELECT * "
					+ "FROM tpdied.producto "
					+ "ORDER BY 1"	;
			
			Conexion conexion = new Conexion();
			
			Connection cn = null; //para conectar a la bd
			PreparedStatement st = null; //para hacer las consultas SQL
			ResultSet rs = null;
			
			try {
				cn = conexion.conectar();
				st = cn.prepareStatement(consulta);
				rs = st.executeQuery();
				
				while(rs.next()) {
					String nombre = rs.getString(1);
					String descripcion = rs.getString(2);
					Double precioUnitario = rs.getDouble(3);
					Double peso = rs.getDouble(4);
					
					
					
					System.out.println(nombre + " " + descripcion + " " + precioUnitario + " " + peso);
					
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
			
			return productos;
		}
		public List<Producto> eliminarProducto(){
			return null;
		}
		
		public void modificarProducto(Producto p) {
			
		}
}
