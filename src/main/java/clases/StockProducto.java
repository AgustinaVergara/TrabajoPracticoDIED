package clases;

public class StockProducto {
	private Integer idStockProducto;
	private Integer idProducto;
	private Integer idSucursal;
	private Integer stock;

	public StockProducto(Integer idStockProducto, Integer idProducto, Integer idSucursal, Integer stock) {
		super();
		this.idStockProducto = idStockProducto;
		this.idProducto = idProducto;
		this.idSucursal = idSucursal;
		this.stock = stock;
	}

	public Integer getIdStockProducto() {
		return idStockProducto;
	}

	public void setIdStockProducto(Integer idStockProducto) {
		this.idStockProducto = idStockProducto;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
