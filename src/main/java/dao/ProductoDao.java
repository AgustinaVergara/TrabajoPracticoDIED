package dao;

import java.util.List;

import clases.Producto;

public interface ProductoDao {
	public void crearProducto(Producto p);
	public Integer getUltimoIdP();
	public List<Producto> buscarProducto();
	public void eliminarP(Producto p);
	public void modificarProducto(Producto p);
}
