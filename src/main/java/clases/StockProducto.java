package clases;

public class StockProducto {
	private Producto idProducto;
	private Integer idSucursal;
	private Integer stock;
	
	
	public StockProducto(Producto idProducto, Integer idSucursal, Integer stock) {
		super();
		this.idProducto = idProducto;
		this.idSucursal = idSucursal;
		this.stock = stock;
	}


	public Producto getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(Producto idProducto) {
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
