package gestores;

import java.util.ArrayList;
import java.util.List;

import clases.Producto;
import clases.Sucursal;
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
	
	public List<Producto> eliminarProducto(Producto p) {
		List<Producto> eliminarP = new ArrayList<>() ; 
		
		if(!(p.getNombre().isEmpty())) {
			for(Producto i : productos) {
				if(p.getNombre() == i.getNombre()) eliminarP.add(i);
			}
		}
		else if(p.getPrecioUnitario() > 0) {
			for(Producto i : productos) {
				if(p.getPrecioUnitario() == i.getPrecioUnitario()) eliminarP.add(i);
			}
		}
		else if(p.getPesoKg() > 0) {
			for(Producto i : productos) {
				if(p.getPesoKg() == i.getPesoKg()) eliminarP.add(i);
			}
		}
		return eliminarP;
	}
	
	public List<Producto> buscarProducto(Producto p){
		List <Producto> listaBusqueda = new ArrayList<>();
		if(!(p.getNombre().isEmpty())) {
			for(Producto i : productos) {
				if(p.getNombre() == i.getNombre()) listaBusqueda.add(i);
			}
		}
		else if(p.getPrecioUnitario() > 0) {
			for(Producto i : productos) {
				if(p.getPrecioUnitario() == i.getPrecioUnitario()) listaBusqueda.add(i);
			}
		}
		else if(p.getPesoKg() > 0) {
			for(Producto i : productos) {
				if(p.getPesoKg() == i.getPesoKg()) listaBusqueda.add(i);
			}
		}
		
		return listaBusqueda;
	}
}
