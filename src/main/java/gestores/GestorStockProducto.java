package gestores;


import java.util.ArrayList;
import java.util.List;

import clases.StockProducto;
import dao.StockProductoDao;
import dao.StockProductoDaoImpl;


public class GestorStockProducto {
	private StockProductoDao dao;
	private static GestorStockProducto gestor;
	private static Integer siguienteIdStockProducto;
	private List<StockProducto> listaStockProducto;
	
	public GestorStockProducto(){
		dao = new StockProductoDaoImpl();
		listaStockProducto = new  ArrayList<>(dao.buscarStockProducto());
	}
	
	public static GestorStockProducto getInstance() {
		if (gestor == null) {
			gestor = new GestorStockProducto();
	}
		
		return gestor;
	}
	/*
	public StockProducto registrarStockProductoGestor(Integer idProducto, Integer idSucursal, Integer cantidad ) {
		siguienteIdStockProducto = dao.getUltimoIdStockProducto() + 1;
		return new StockProducto(siguienteIdStockProducto, idProducto, idSucursal, cantidad);
	}*/
	public void registrarStockProductoGestor(Integer idProducto, Integer idSucursal, Integer cantidad ) {
		siguienteIdStockProducto = dao.getUltimoIdStockProducto() + 1;
		StockProducto nuevoStockDeProducto = new StockProducto(siguienteIdStockProducto, idProducto, idSucursal, cantidad);
		listaStockProducto.add(nuevoStockDeProducto);
		dao.registrarStock(nuevoStockDeProducto);
	}
	
	public List<StockProducto> getListaStockProducto(){
		return listaStockProducto;
	}
	

}
