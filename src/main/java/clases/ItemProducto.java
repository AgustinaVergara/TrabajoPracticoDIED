package clases;

public class ItemProducto {
	private Integer idItemProducto;
	private Integer idOrden;
	private Integer idProducto;
	private Integer cantidad;
	private Producto producto;

	//Constructores
	public ItemProducto(Integer idItemProducto, Integer idOrden, Integer idProducto, Integer cantidad) {
		super();
		this.idItemProducto = idItemProducto;
		this.idOrden = idOrden;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	
	public ItemProducto(Integer cantidad, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
	}

	
	//Getters and Setters
	public Integer getIdItemProducto() {
		return idItemProducto;
	}

	public void setIdItemProducto(Integer idItemProducto) {
		this.idItemProducto = idItemProducto;
	}

	public Integer getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
}
