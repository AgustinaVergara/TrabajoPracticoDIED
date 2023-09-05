package gestores;
import java.util.ArrayList;
import java.util.List;

import clases.Camino;
import clases.Grafo;
import clases.Sucursal;
import dao.GrafoDao;
import dao.GrafoSQLimplementacion;
public class GestorGrafo {
	private GrafoDao grafoDAO;

	private List<Grafo> grafos;
	//CONSTRUCTOR
	public GestorGrafo(){
		grafoDAO = new GrafoSQLimplementacion();
		this.grafos=new ArrayList<Grafo>();
		//grafos = new  ArrayList<>(grafoDAO.buscarGrafos());
		
	}
	private static GestorGrafo gestor;
	public static GestorGrafo getInstance() {
		if (gestor == null) {
			gestor = new GestorGrafo();
		}
		
		return gestor;
	}
	public Grafo crearGrafo(Camino c,Sucursal so, Sucursal sd) {
		return new Grafo(so.getNombre(),sd.getNombre(),c.getTiempoTransito());
	
	}
	public void agregarGrafo(Grafo g) {
		grafoDAO.crearGrafo(g);
		this.grafos.add(g);
	}
}
