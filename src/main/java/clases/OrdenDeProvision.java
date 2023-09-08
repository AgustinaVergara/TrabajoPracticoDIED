package clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import enums.EstadoOrden;

public class OrdenDeProvision {
	private Integer idOrdenProvision;
	private LocalDate fechaOrden;
	private Integer sucursalOrigenId;
	private Integer sucursalDestinoId;
	private LocalTime tiempoMax;
	private List<ItemProducto> productosDeOrden;
	private EstadoOrden estado;
	
	
	public Integer getIdOrdenProvision() {
		return idOrdenProvision;
	}

	public void setIdOrdenProvision(Integer idOrdenProvision) {
		this.idOrdenProvision = idOrdenProvision;
	}

	public LocalDate getFechaOrden() {
		return fechaOrden;
	}
	
	public void setFechaOrden(LocalDate fechaOrden) {
		this.fechaOrden = fechaOrden;
	}
	
	public LocalTime getTiempoMax() {
		return tiempoMax;
	}

	public void setTiempoMax(LocalTime tiempoMax) {
		this.tiempoMax = tiempoMax;
	}
	
	public List<ItemProducto> getProductosDeOrden() {
		return productosDeOrden;
	}

	public void setProductosDeOrden(List<ItemProducto> productosDeOrden) {
		this.productosDeOrden = productosDeOrden;
	}
	
	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}

	public Integer getSucursalOrigenId() {
		return sucursalOrigenId;
	}

	public void setSucursalOrigenId(Integer sucursalOrigenId) {
		this.sucursalOrigenId = sucursalOrigenId;
	}

	public Integer getSucursalDestinoId() {
		return sucursalDestinoId;
	}

	public void setSucursalDestinoId(Integer sucursalDestinoId) {
		this.sucursalDestinoId = sucursalDestinoId;
	}

	public OrdenDeProvision(Integer idOrdenProvision, LocalDate fechaOrden, 
			Integer sucursalDestinoId, LocalTime tiempoMax, List<ItemProducto> productosDeOrden, EstadoOrden estado) {
		super();
		this.idOrdenProvision = idOrdenProvision;
		this.fechaOrden = fechaOrden;
		this.sucursalDestinoId = sucursalDestinoId;
		this.tiempoMax = tiempoMax;
		this.productosDeOrden = productosDeOrden;
		this.estado = estado;
	}
	
	//ver porque este no tiene los productos
	public OrdenDeProvision(Integer idOrdenProvision, LocalDate fechaOrden, 
			Integer sucursalDestinoId, LocalTime tiempoMax,  EstadoOrden estado) {
		super();
		this.idOrdenProvision = idOrdenProvision;
		this.fechaOrden = fechaOrden;
		this.sucursalDestinoId = sucursalDestinoId;
		this.tiempoMax = tiempoMax;
		this.estado = estado;
	}
}

