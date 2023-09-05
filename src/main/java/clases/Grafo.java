package clases;

import java.util.Objects;

public class Grafo {
	String nombreOrigen;
	String nombreDestino;
	int tiempo;
	public Grafo(String nO,String nD, int t) {
		this.nombreOrigen=nO;
		this.nombreDestino=nD;
		this.tiempo=t;
	}
	public Grafo() {
		
	}
	public String getNombreOrigen() {
		return nombreOrigen;
	}
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}
	public String getNombreDestino() {
		return nombreDestino;
	}
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombreDestino, nombreOrigen, tiempo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grafo other = (Grafo) obj;
		return Objects.equals(nombreDestino, other.nombreDestino) && Objects.equals(nombreOrigen, other.nombreOrigen)
				&& tiempo == other.tiempo;
	}
	
}
