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
	
	public void modificarProducto(Integer id,String nombre, String descrip, double precio, double peso) {
		Producto p = this.getProdId(id);
		p.modificarProd(nombre, descrip, precio, peso);
		dao.modificarP(p);
	}
	
	public Producto getProdId(Integer id) {
		return (productos.stream().filter(e -> e.getidP() == id).findFirst()).get();
	}
	
	public final Producto buscarPN(String nombre){
		Producto p = new Producto(null, nombre, null, null, null);
		GestorProducto buscarP = new GestorProducto();
		p = (Producto) buscarP.buscarProductoNombre(nombre);
		return p;
	}
	
	public final Producto buscarPPr(double precio){
		Producto p = new Producto(null, null, null, precio, null);
		GestorProducto buscarPr = new GestorProducto();
		p = (Producto) buscarPr.buscarProductoPrecio(precio);
		return p;
	}
	
	public final Producto buscarPp(double peso){
		Producto p = new Producto(null, null, null, null, peso);
		GestorProducto buscarPp = new GestorProducto();
		p = (Producto) buscarPp.buscarProductoPeso(peso);
		return p;
	}
	
	public List<Producto> buscarProductoNombre(String nombre){
		List<Producto> prod = new ArrayList<>();
		prod = dao.buscarProductoNombre(nombre);
		return prod;
		
	}
	public List<Producto> buscarProductoPrecio(double precio){
		List<Producto> listap = new ArrayList<>();
		
		for(Producto p: productos) {
			if(p.getPrecioUnitario()== precio) listap.add(p);
		}
		return listap;
	}
	public List<Producto> buscarProductoPeso(double peso){
		List<Producto> lista = new ArrayList<>();
		for(Producto p: productos) {
			if(p.getPesoKg() == peso) lista.add(p);
		}
		return lista;
	}
}
