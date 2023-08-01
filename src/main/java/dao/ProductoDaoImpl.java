package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Producto;
import conexion.Conexion;

public class ProductoDaoImpl implements ProductoDao {
	
		public void crearProducto(Producto p) {
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
					
					productos.add(new Producto(nombre, descripcion, precioUnitario, peso));
					
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
