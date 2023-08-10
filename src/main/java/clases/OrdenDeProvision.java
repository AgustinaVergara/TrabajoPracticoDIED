package clases;

import java.time.LocalTime;
import java.util.Map;

import enums.EstadoOrden;

public class OrdenDeProvision {
	private Integer idOrdenProvision;
	private LocalTime fechaOrden;
	private Sucursal sucursalDestino;
	private Double tiempoMaxEnHs;
	private Map<Producto, Integer> productosCantidad;
	private EstadoOrden estado;
	
	
	public Integer getIdOrdenProvision() {
		return idOrdenProvision;
	}

	public void setIdOrdenProvision(Integer idOrdenProvision) {
		this.idOrdenProvision = idOrdenProvision;
	}

	public LocalTime getFechaOrden() {
		return fechaOrden;
	}
	
	public void setFechaOrden(LocalTime fechaOrden) {
		this.fechaOrden = fechaOrden;
	}
	
	public Sucursal getSucursalDestino() {
		return sucursalDestino;
	}
	
	public void setSucursalDestino(Sucursal sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}
	
	public Double getTiempoMaxEnHs() {
		return tiempoMaxEnHs;
	}
	
	public void setTiempoMaxEnHs(Double tiempoMaxEnHs) {
		this.tiempoMaxEnHs = tiempoMaxEnHs;
	}
	
	public Map<Producto, Integer> getProductosCantidad() {
		return productosCantidad;
	}
	public void setProductosCantidad(Map<Producto, Integer> productosCantidad) {
		this.productosCantidad = productosCantidad;
	}
	
	//tener  en cuenta que este metodo agregar deberia ir en el dao
	public void agregarProducto(Producto producto, Integer cantidad) {
	     productosCantidad.put(producto, cantidad);
	}

	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}
	
	
}

