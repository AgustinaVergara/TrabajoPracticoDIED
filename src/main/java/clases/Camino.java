package clases;

//import java.util.ArrayList;

import enums.EstadoSucursal;



public class Camino  {
	int id;
	Sucursal sucursalOrigen;
	Sucursal sucursalDestino;
	int tiempoTransito; //tiempo del transito en minutos
	EstadoSucursal estado; // Estado de la ruta
	double capacidadMax; // en kilos

	Camino(){
		this.id=0;
		this.sucursalOrigen= null ;
		this.sucursalDestino= null;
		this.tiempoTransito=0;
		this.estado=EstadoSucursal.NO_OPERATIVA;
		this.capacidadMax=0.0;
	
	}
	public Camino(int id, Sucursal sO, Sucursal sD, int t, EstadoSucursal estado, double c){
		this.id=id;
		this.sucursalOrigen=(sD);
		this.sucursalDestino=(sD);
		this.tiempoTransito=t;
		this.estado=estado;
		this.capacidadMax=c;
	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sucursal getSO() {
		return sucursalOrigen;
	}
	public void setSucursalOrigen(Sucursal sO) {
		this.sucursalOrigen=(sO) ;
	}
	public Sucursal getSD() {
		return sucursalDestino;
	}
	public void setSucursalDestino(Sucursal sD) {
		this.sucursalDestino=(sD);
	}
	public int getTiempoTransito() {
		return tiempoTransito;
	}
	public void setTiempoTransito(int tiempoTransito) {
		this.tiempoTransito = tiempoTransito;
	}
	public EstadoSucursal getEsOperativa() {
		return this.estado;
	}
	public void setEsOperativa(EstadoSucursal estado) {
		this.estado = estado;
	}
	public double getCapacidadMax() {
		return capacidadMax;
	}
	public void setCapacidadMax(double capacidadMax) {
		this.capacidadMax = capacidadMax;
	}
	
	
}
