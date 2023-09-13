package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.ItemProducto;
import conexion.Conexion;

public class ItemProductoDaoImpl implements ItemProductoDao {

	@Override
	public List<ItemProducto> buscarItemProducto() {
		List<ItemProducto> listaItemProducto = new ArrayList<>();
		
		String consulta = "SELECT * "
				+ "FROM tpdied.item_producto "
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
				Integer idItemProducto = rs.getInt(1);
				Integer idOrden = rs.getInt(2);
				Integer idProducto = rs.getInt(3);
				Integer cantidad = rs.getInt(4);
				
				
				listaItemProducto.add(new ItemProducto(idItemProducto, idOrden, idProducto, cantidad));
				
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
		return listaItemProducto;
	}

}
