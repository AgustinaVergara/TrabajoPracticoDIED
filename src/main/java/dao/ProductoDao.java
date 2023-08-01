package dao;

import java.util.List;

import clases.Producto;

public interface ProductoDao {
	public void crearProducto(Producto p);
	public List<Producto> buscarProducto();
	public List<Producto> eliminarProducto();
	public void modificarProducto(Producto p);
}
