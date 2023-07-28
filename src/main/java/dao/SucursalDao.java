package dao;

import java.util.List;

import clases.Sucursal;

public interface SucursalDao {
	void crearSucursal(Sucursal sucursal);
	List<Sucursal> buscarSucursales();
	
}
