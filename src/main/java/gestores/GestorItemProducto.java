package gestores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import clases.ItemProducto;
import clases.OrdenDeProvision;
import dao.ItemProductoDao;
import dao.ItemProductoDaoImpl;

public class GestorItemProducto {
	
	private static GestorItemProducto gestor;
	private ItemProductoDao dao;
	private List<OrdenDeProvision> ordenes;
	private static Integer siguienteIdItemProducto;
	private List<ItemProducto> listaItemProducto;
	
	public GestorItemProducto(){
		dao = new ItemProductoDaoImpl();
		listaItemProducto = new  ArrayList<>(dao.buscarItemProducto());
	}
	
	public static GestorItemProducto getInstance() {
		if (gestor == null) {
			gestor = new GestorItemProducto();
		}
		return gestor;
	}
	
	
	public List<ItemProducto> filtrarItemProductoXOrden(Integer idOrden){
		// Obtiene los items producto que tiene la orden pasada como parametro
		return (listaItemProducto.stream().filter(i -> i.getIdOrden() == idOrden).collect(Collectors.toList()));
		
	}

}
