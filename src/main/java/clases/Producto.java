package clases;

public class Producto {
	public String nombre;
	public String descripcion; 
	public Double precioUnitario;
	public Double pesoKg;
	public Integer idP;
	
	public Integer getidP() {
		return idP; 
	}
	public void setIdP(Integer id) {
		this.idP = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Double getPesoKg() {
		return pesoKg;
	}
	public void setPesoKg(Double pesoKg) {
		this.pesoKg = pesoKg;
	} 
	
	public Producto(Integer id, String nom, String descrip, Double precioU, Double peso){
		this.idP = id;
		this.nombre = nom; 
		this.descripcion = descrip;
		this.precioUnitario = precioU;
		this.pesoKg = peso; 
	}
}
