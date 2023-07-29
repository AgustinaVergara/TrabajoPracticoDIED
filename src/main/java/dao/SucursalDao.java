package dao;

import java.util.List;

import clases.Sucursal;

public interface SucursalDao {
	public void crearSucursal(Sucursal sucursal);
	public List<Sucursal> buscarSucursales();
	public Integer getUltimoIdSucursal();
	
}
