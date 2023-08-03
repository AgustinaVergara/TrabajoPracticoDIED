package gestores;

import java.util.ArrayList;
import java.util.List;

import clases.Producto;
import dao.ProductoDao;
import dao.ProductoDaoImpl;

public class GestorProducto {
	private static GestorProducto gestorP;
	private ProductoDao dao;
	private List<Producto> productos;
	private static Integer siguienteIdP;
	
	private GestorProducto(){
		dao = new ProductoDaoImpl();
		productos = new  ArrayList<>(dao.buscarProducto());
	}
	
	public static GestorProducto getInstance() {
		if (gestorP == null) {
			gestorP = new GestorProducto();
		}
		return gestorP;
	}

	public List<Producto> getProductos() {
		return productos;
	}
	
	public void agregarProducto(Producto p) {
		productos.add(p);
		dao.crearProducto(p);
	}
	
	public Producto crearProductoGestor(String nom, String descrip, Double precio, Double peso) {
		siguienteIdP = dao.getUltimoIdP() + 1;
		System.out.println("SIGUIENTE PRODUCTO " +siguienteIdP);
		return new Producto(siguienteIdP, nom, descrip, precio, peso);
	}
	
	public void eliminarProducto(Producto p) {
		dao.eliminarP(p);
		productos.remove(p);

	}
}
