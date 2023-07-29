package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import clases.Camino;
import conexion.Conexion;


public interface CaminoDao extends Dao {
	public List<Camino> buscar();
	public void eliminar();
	public void insertar(List<Camino> camino);
	public void modificarCaminos(List<Camino> listaCaminosAEditar);
	public Integer getSiguienteIdCamino();
}
