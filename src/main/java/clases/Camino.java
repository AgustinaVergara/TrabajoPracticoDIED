package clases;

import java.util.ArrayList;

//import java.util.ArrayList;

import enums.EstadoSucursal;



public class Camino  {
	int id;
	Sucursal sucursalOrigen;
	Sucursal sucursalDestino;
	ArrayList<Sucursal> listaSucursales; // LO HICE PARA VER A LAS SUCURSALES QUE SE 
									//ASOCIAN PQ NECESITO AL MOMENTO DE GUARDAR CDO DOY DE ALTA 
	int tiempoTransito; //tiempo del transito en minutos
	EstadoSucursal estado; // Estado de la ruta
	double capacidadMax; // en kilos

	public Camino(){
		this.id=0;
		this.sucursalOrigen= null ;
		this.sucursalDestino= null;
		this.listaSucursales= new ArrayList<Sucursal>();
		this.tiempoTransito=0;
		this.estado=EstadoSucursal.NO_OPERATIVA;
		this.capacidadMax=0.0;
	
	}
	public Camino(int id, Sucursal sO, Sucursal sD, int t, EstadoSucursal estado, double c){
		this.id=id;
		this.sucursalOrigen=(sD);
		this.sucursalDestino=(sD);
		this.listaSucursales.add(sD);
		this.listaSucursales.add(sO);
		this.tiempoTransito=t;
		this.estado=estado;
		this.capacidadMax=c;
	
	}
	// en realidad deberia sacar el nombre de la sucursal de la bd pero para que ande lo hice asi
	
	public final Sucursal buscarSucursal(String nombre){
		Sucursal s= new Sucursal();
		for (int i=0; i<this.listaSucursales.size();i++) {
			if(this.listaSucursales.get(i).getNombre()==nombre) return this.listaSucursales.get(i);
		}
		
		return s;
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
	public boolean esActivo() {
		if (this.estado== EstadoSucursal.OPERATIVA) return true;
		else return false;
	}
	
	
}
