package dao;

import java.util.List;

import clases.OrdenDeProvision;


public interface OrdenDao {
	public void crearOrden(OrdenDeProvision orden);
	public List<OrdenDeProvision> buscarOrdenes();
	public Integer getUltimoIdOrden();
	public void crearItemProducto(Integer idOrdenProvision, Integer productoId, Integer cantidad);
	public void eliminar(OrdenDeProvision o);

}
