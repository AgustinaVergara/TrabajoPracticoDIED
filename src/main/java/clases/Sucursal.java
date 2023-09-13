package clases;

import java.time.LocalTime;
import java.util.Objects;

import enums.EstadoSucursal;

public class Sucursal {
	
	private Integer id;
	private String nombre;
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private EstadoSucursal estado;
	int posicionx;
	int posiciony;
	
	public void setPosicionX(int px) {
		this.posicionx=px;
	}
	public void setPosicionY(int py) {
		this.posiciony= py;
	}
	public int getPosicionX() {
		return this.posicionx;
	}
	public int getPosicionY() {
		return this.posiciony;
	}
	
	//Getters y Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}
	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}
	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}
	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}
	public EstadoSucursal getEstado() {
		return estado;
	}
	public void setEstado(EstadoSucursal estado) {
		this.estado = estado;
	}
	
	//Constructors
	public Sucursal() {
		
	};
	public Sucursal(Integer id, String nombre, LocalTime horarioApertura, LocalTime horarioCierre,
			EstadoSucursal estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
	}
	
	
	public void modificarse(String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoSucursal estado) {
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sucursal other = (Sucursal) obj;
		return Objects.equals(id, other.id);
	}
	
	

	

}
