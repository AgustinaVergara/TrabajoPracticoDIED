package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Producto;
import conexion.Conexion;
import gestores.GestorProducto;

public class ProductoDaoImpl implements ProductoDao {
	
		public void crearProducto(Producto p) {
			
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
					Integer id = rs.getInt(1);
					String nombre = rs.getString(2);
					String descripcion = rs.getString(3);
					Double precioUnitario = rs.getDouble(4);
					Double peso = rs.getDouble(5);
					
					productos.add(new Producto(id, nombre, descripcion, precioUnitario, peso));
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
		
		@Override
		public void eliminarP(Producto p) {
			String consulta = "DELETE FROM tpdied.producto WHERE idProducto = (?);";
			
			Conexion conexion = new Conexion();
			
			Connection cn = null; //para conectar a la bd
			PreparedStatement st = null; //para hacer las consultas SQL
			
			
			try {
				cn = conexion.conectar();
				cn.setAutoCommit(false);
				
				st = cn.prepareStatement(consulta);
		
				st.setInt(1, p.getidP());


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
		public void modificarP(Producto p) {
			String consulta = "UPDATE tpdied.producto "
					+ "SET nombre = ?, "
					+ "    descripcion = ?, "
					+ "    precioUniario = ?, "
					+ "    pesokg = ? "
					+ "WHERE idProducto = ?;";
			
			Conexion conexion = new Conexion();
			
			Connection cn = null;
			PreparedStatement st = null;
			
			try {
				cn = conexion.conectar();
			
				
				st = cn.prepareStatement(consulta);
				
				st.setInt(1, p.getidP());
				st.setString(2, p.getNombre());
				st.setString(3, p.getDescripcion());
				st.setDouble(4, p.getPrecioUnitario());	
				st.setDouble(5, p.getPesoKg());
				
				st.executeUpdate();
				
				
			}  catch(SQLException e) {
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
		public List<Producto> buscarProductoNombre(String nombre){
			List<Producto> listaProductos = new ArrayList<>();
			GestorProducto gestorProducto = GestorProducto.getInstance();
			String consulta = "select * from tpdied.producto where nombre= ?";
			
			Conexion conexion = new Conexion();
			
			Connection cn = null; //para conectar a la bd
			PreparedStatement st = null; //para hacer las consultas SQL
			ResultSet rs = null;
			try {
				cn = conexion.conectar();
				st = cn.prepareStatement(consulta);
				st.setString(1, nombre);
				rs = st.executeQuery();
				
				//En rs esta la tabla y ahora hay que recorrerla fila por fila
				while(rs.next()) {
					Integer idp = rs.getInt(1);
					//String nom = rs.getString(2);
					String descripcion = rs.getString(3);
					Double precio = rs.getDouble(4);
					Double peso = rs.getDouble(5);
					
					Producto p = gestorProducto.buscarPN(nombre);
					listaProductos.add(p);
					
					System.out.println(idp + " " + p + " " + descripcion + " " + precio + " " + peso);
					
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
			
			return listaProductos;
			
		}
		public List<Producto> buscarProductoPrecio(double precio){
			List<Producto> listaProductosPrecio = new ArrayList<>();
			GestorProducto gestorProducto = GestorProducto.getInstance();
			String consulta = "select * from tpdied.producto where precioUnitario= ?";
			
			Conexion conexion = new Conexion();
			
			Connection cn = null; //para conectar a la bd
			PreparedStatement st = null; //para hacer las consultas SQL
			ResultSet rs = null;
			try {
				cn = conexion.conectar();
				st = cn.prepareStatement(consulta);
				st.setDouble(1, precio);
				rs = st.executeQuery();
				
				//En rs esta la tabla y ahora hay que recorrerla fila por fila
				while(rs.next()) {
					Integer idp = rs.getInt(1);
					String nom = rs.getString(2);
					String descripcion = rs.getString(3);
					//Double precio = rs.getDouble(4);
					Double peso = rs.getDouble(5);
					
					Producto p = gestorProducto.buscarPPr(precio);
					listaProductosPrecio.add(p);
					
					System.out.println(idp + " " + nom + " " + descripcion + " " + p + " " + peso);
					
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
			
			return listaProductosPrecio;
		}
		public List<Producto> buscarProductoPeso(double peso){
			List<Producto> listaProductosPeso = new ArrayList<>();
			GestorProducto gestorProducto = GestorProducto.getInstance();
			String consulta = "select * from tpdied.prodcuto where pesokg= ?";
			
			Conexion conexion = new Conexion();
			
			Connection cn = null; //para conectar a la bd
			PreparedStatement st = null; //para hacer las consultas SQL
			ResultSet rs = null;
			try {
				cn = conexion.conectar();
				st = cn.prepareStatement(consulta);
				st.setDouble(1, peso);
				rs = st.executeQuery();
				
				//En rs esta la tabla y ahora hay que recorrerla fila por fila
				while(rs.next()) {
					Integer idp = rs.getInt(1);
					String nom = rs.getString(2);
					String descripcion = rs.getString(3);
					Double precio = rs.getDouble(4);
					//Double peso = rs.getDouble(5);
					
					Producto p = gestorProducto.buscarPp(peso);
					listaProductosPeso.add(p);
					
					System.out.println(idp + " " + nom + " " + descripcion + " " + precio + " " + p);
					
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
			
			return listaProductosPeso;
		}
}
