package clases;

import java.util.ArrayList;

public class Camino {
	int id;
	ArrayList<Sucursal> sucursalOrigen =new ArrayList<Sucursal>();
	ArrayList<Sucursal> sucursalDestino=new ArrayList<Sucursal>();
	int tiempoTransito; //tiempo del transito en minutos
	boolean esOperativa; // Estado de la ruta
	double capacidadMax; // en kilos

	Camino(){
		this.id=0;
		this.sucursalOrigen= null ;
		this.sucursalDestino= null;
		this.tiempoTransito=0;
		this.esOperativa=false;
		this.capacidadMax=0.0;
	
	}
	Camino(int id, Sucursal sO, Sucursal sD, int t, boolean estado, double c){
		this.id=id;
		this.sucursalOrigen= sO;
		this.sucursalDestino= sD;
		this.tiempoTransito=t;
		this.esOperativa=estado;
		this.capacidadMax=c;
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Sucursal> getlistaSO() {
		return sucursalOrigen;
	}
	public void setSucursalOrigen(Sucursal sO) {
		this.sucursalOrigen.add(sO) ;
	}
	public ArrayList<Sucursal> getlistaSD() {
		return sucursalDestino;
	}
	public void setSucursalDestino(Sucursal sD) {
		this.sucursalDestino.add(sD);
	}
	public int getTiempoTransito() {
		return tiempoTransito;
	}
	public void setTiempoTransito(int tiempoTransito) {
		this.tiempoTransito = tiempoTransito;
	}
	public boolean isEsOperativa() {
		return esOperativa;
	}
	public void setEsOperativa(boolean esOperativa) {
		this.esOperativa = esOperativa;
	}
	public double getCapacidadMax() {
		return capacidadMax;
	}
	public void setCapacidadMax(double capacidadMax) {
		this.capacidadMax = capacidadMax;
	}
	
	
}
