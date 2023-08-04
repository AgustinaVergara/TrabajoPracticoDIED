package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import clases.Camino;
import conexion.Conexion;


public interface CaminoDao   {
	public List<Camino> buscarCaminos();
	public void eliminar(Camino camino);
	
	public void modificarCamino(Camino caminoEditar);
	public void crearCamino(Camino camino);
	public Integer getUltimoIdCamino();
	public List<Camino> buscarxId(int id) ;
	public List<Camino> buscarxNombreSucursalOrigen(String nombreSucursal) ;
	
	
	
	
	
}
