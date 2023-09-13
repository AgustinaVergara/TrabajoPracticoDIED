package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clases.OrdenDeProvision;
import conexion.Conexion;
import enums.EstadoOrden;

public class OrdenDaoImpl implements OrdenDao {

	public void crearOrden(OrdenDeProvision orden) {

		String consulta = "INSERT INTO tpdied.orden " + "(idOrden, fecha, tiempoMax, sucursalDestinoId, estado) "
				+ "VALUES (?, ?, ?, ?, ?);";

		Conexion conexion = new Conexion();

		Connection cn = null; // para conectar a la bd
		PreparedStatement st = null;// para hacer las consultas SQL
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			st.setInt(1, this.getUltimoIdOrden() + 1);
			st.setDate(2, Date.valueOf(orden.getFechaOrden()));
			st.setInt(3, orden.getTiempoMax());
			st.setInt(4, orden.getSucursalDestinoId());
			st.setString(5, orden.getEstado().toString());

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

	};

	public List<OrdenDeProvision> buscarOrdenes() {
		List<OrdenDeProvision> ordenes = new ArrayList<>();

		String consulta = "SELECT * " + "FROM tpdied.orden " + "ORDER BY 1";

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
				Integer idOrden = rs.getInt(1);
				LocalDate fecha = rs.getDate(2).toLocalDate();
				Integer tiempoMax = rs.getInt(3);
				Integer sucursalDestinoId = rs.getInt(4);
				Integer sucursalOrigenId = rs.getInt(5);
				String estado = rs.getString(6);

				if (estado.equals("PENDIENTE"))
					ordenes.add(
							new OrdenDeProvision(idOrden, fecha, sucursalDestinoId, tiempoMax, EstadoOrden.PENDIENTE));
				else
					ordenes.add(
							new OrdenDeProvision(idOrden, fecha, sucursalDestinoId, sucursalOrigenId, tiempoMax, EstadoOrden.EN_PROCESO));

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

		return ordenes;
	};

	public Integer getUltimoIdOrden() {
		String consultaObtenerID = "SELECT MAX(idOrden) " + "FROM tpdied.orden";

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
	public void crearItemProducto(Integer idOrdenProvision, Integer productoId, Integer cantidad) {
		String consulta = "INSERT INTO tpdied.item_producto (ordenId, productoId, cantidad) VALUES (?, ?, ?);";

		Conexion conexion = new Conexion();

		Connection cn = null; // para conectar a la bd
		PreparedStatement st = null;// para hacer las consultas SQL

		try {
			cn = conexion.conectar();
			st = cn.prepareStatement(consulta);
			st.setInt(1, idOrdenProvision);
			st.setInt(2, productoId);
			st.setInt(3, cantidad);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Para liberar recursos
			try {
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
	public void eliminar(OrdenDeProvision o) {

		String consulta = "DELETE FROM tpdied.orden WHERE idOrden = (?);";

		Conexion conexion = new Conexion();

		Connection cn = null; // para conectar a la bd
		PreparedStatement st = null; // para hacer las consultas SQL

		try {
			cn = conexion.conectar();
			cn.setAutoCommit(false);

			st = cn.prepareStatement(consulta);

			/*
			 * Esto se utiliza para establecer los valores de los parámetros (?) en la
			 * declaración SQL usando el método setInt() del objeto st.
			 */
			st.setInt(1, o.getIdOrdenProvision());

			Integer nro = st.executeUpdate();
			cn.commit();

		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public void setOrigen(Integer idSO, Integer idOrdenSeleccionada) {
		// TODO Auto-generated method stub
		
		String consulta = "UPDATE tpdied.orden" 
                + " SET sucursalOrigenId = ?, "
                + " estado = ? "
                + " WHERE idOrden = ?;";
		Conexion conexion = new Conexion();

		Connection cn = null;
		PreparedStatement st = null;

		try {
			cn = conexion.conectar();

			st = cn.prepareStatement(consulta);

			st.setInt(1, idSO);
			st.setString(2, EstadoOrden.EN_PROCESO.toString());
			st.setInt(3, idOrdenSeleccionada);
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	};
}
