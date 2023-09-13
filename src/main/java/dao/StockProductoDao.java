package dao;

import java.util.List;

import clases.StockProducto;

public interface StockProductoDao {
	
	public Integer getUltimoIdStockProducto();
	public void registrarStock(StockProducto st);
	public List<StockProducto> buscarStockProducto();

}
