
package dao;
import clases.Sucursal;

import clases.Camino;

import java.util.List;

import clases.Grafo;
public interface GrafoDao {
	//public void conectar(Arista arista);
	public List<Grafo> buscarGrafos();
	public void crearGrafo(Grafo grafo);
}
