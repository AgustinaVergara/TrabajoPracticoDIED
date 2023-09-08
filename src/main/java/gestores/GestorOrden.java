package gestores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import clases.ItemProducto;
import clases.OrdenDeProvision;
import clases.Sucursal;
import dao.OrdenDao;
import dao.OrdenDaoImpl;
import enums.EstadoOrden;
import enums.EstadoSucursal;

public class GestorOrden {
	private static GestorOrden gestor;
	private OrdenDao dao;
	private List<OrdenDeProvision> ordenes;
	private static Integer siguienteIdOrden;

		
	public GestorOrden(){
		dao = new OrdenDaoImpl();
		ordenes = new  ArrayList<>(dao.buscarOrdenes());
	}
	
	public static GestorOrden getInstance() {
		if (gestor == null) {
			gestor = new GestorOrden();
		}
		return gestor;
	}
	
	public List<OrdenDeProvision> getOrdenes(){
		return ordenes;
	}
	/*
	public void agregarOrden(OrdenDeProvision o) {
		ordenes.add(o);
		dao.crearOrden(o);
		
	}*/
	
	public OrdenDeProvision crearOrdenGestor(LocalDate fecha, Integer SD, LocalTime tiempoMax, List<ItemProducto> productos, 
			EstadoOrden estado ) {
		siguienteIdOrden = dao.getUltimoIdOrden() + 1;
		
		OrdenDeProvision nuevaOrden = new OrdenDeProvision(siguienteIdOrden, fecha, SD, tiempoMax, productos, estado);
		ordenes.add(nuevaOrden);
		dao.crearOrden(nuevaOrden);
		
		// Crear los ítems de producto asociados a la orden en la base de datos
	    for (ItemProducto item : productos) {
	        dao.crearItemProducto(nuevaOrden.getIdOrdenProvision(), item.getProducto().getidP(), item.getCantidad());
	    }

	    return nuevaOrden;
	}
}
