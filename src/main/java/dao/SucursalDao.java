package dao;

import java.util.List;

import clases.Sucursal;

public interface SucursalDao {
	public void crearSucursal(Sucursal sucursal);
	public List<Sucursal> buscarSucursales();
	public Integer getUltimoIdSucursal();
	public void eliminar(Sucursal s);
	public void modificar(Sucursal s);
}
