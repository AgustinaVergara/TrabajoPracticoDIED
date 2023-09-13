package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import clases.StockProducto;
import clases.Sucursal;
import conexion.Conexion;
import enums.EstadoSucursal;

public class StockProductoDaoImpl implements StockProductoDao {

	@Override
	public Integer getUltimoIdStockProducto() {
		// TODO Auto-generated method stub
		String consultaObtenerID = "SELECT MAX(id_stock) " + "FROM tpdied.stock_producto";

		Integer id = null;

		Conexion conexion = new Conexion();

		Connection cn = null; // para conectar a la bd
		PreparedStatement st = null; // para hacer las consultas SQL
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consultaObtenerID);
			rs = st.executeQuery();

			rs.next();
			id = rs.getInt(1);
			if (rs.wasNull()) {
				System.out.println("entra xq es null");
				id = 0;
			}
			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (cn != null) {
				try {
					cn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return id;
	}

	@Override
	public void registrarStock(StockProducto nuevoStockProducto) {
		// TODO Auto-generated method stub
		String consulta = "INSERT INTO tpdied.stock_producto " + "VALUES (?, ?, ?, ?);";

		Conexion conexion = new Conexion();

		Connection cn = null; // para conectar a la bd
		PreparedStatement st = null;// para hacer las consultas SQL
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			st.setInt(1, this.getUltimoIdStockProducto() + 1);
			st.setInt(2, nuevoStockProducto.getIdProducto());
			st.setInt(3, nuevoStockProducto.getIdSucursal());
			st.setInt(4, nuevoStockProducto.getStock());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Para liberar recursos
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	@Override
	public List<StockProducto> buscarStockProducto() {
		// TODO Auto-generated method stub
		List<StockProducto> listaStockProducto = new ArrayList<>();

		String consulta = "SELECT * " + "FROM tpdied.stock_producto " + "ORDER BY 1";

		Conexion conexion = new Conexion();

		Connection cn = null; // para conectar a la bd
		PreparedStatement st = null; // para hacer las consultas SQL
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			rs = st.executeQuery();

			// En rs esta la tabla de sucursal y ahora hay que recorrerla fila por fila
			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer idProducto = rs.getInt(2);
				Integer idSucursal = rs.getInt(3);
				Integer cantidad = rs.getInt(4);
				
				listaStockProducto.add(new StockProducto(id, idProducto, idSucursal, cantidad));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Para liberar recursos
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return listaStockProducto;
	}

}
