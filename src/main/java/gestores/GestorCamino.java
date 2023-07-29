package gestores;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import clases.*;
import dao.*;
import enums.*;

public class GestorCamino {
	private List<Camino> caminos;
	private static GestorCamino gestor;
	private CaminoDao caminoDAO;
	private static int siguienteIdCamino; 
	
	
	private GestorCamino() {
		caminoDAO = (CaminoDao) new CaminoSQLimplementacion();
		caminos = new ArrayList<>(caminoDAO.buscar());
		Thread t1 = new Thread(() -> {
			siguienteIdCamino = caminoDAO.getSiguienteIdCamino() + 1;
		});
		t1.run();
	}
	
	public static GestorCamino getInstance() {
		if (gestor == null) {
			gestor = new GestorCamino();
		}
		
		return gestor;
	}
	public List<Camino> getRutas() {
		return caminos;
	}
	public void agregarCamino(int id, Sucursal so, Sucursal sd, int tiempo, EstadoSucursal estado, double capacidad) {
		caminos.add(new Camino(id, so, sd, tiempo, estado, capacidad));
	}
	
	// Devuelve una lista con todas las lista de rutas que pueden recorrerse para ir desde origen a destino
		public List<List<Camino>> buscarCaminos(Sucursal origen, Sucursal destino) {
			List<List<Camino>> salida = new ArrayList<>();
			List<Sucursal> marcados = new ArrayList<>();
			marcados.add(origen);
			buscarCaminosAux(origen, destino, marcados, new ArrayList<>(), salida);
			return salida;
		}
		
		private void buscarCaminosAux(Sucursal s1, Sucursal s2, List<Sucursal> marcados, List<Camino> camino, List<List<Camino>> salida) {	
			if (s1.equals(s2)) salida.add(camino);
			else {
				List<Camino> salientes = this.getCaminoSaliente(s1);
				List<Sucursal> copiaMarcados = null;
				List<Camino> copiaCamino = null;
				
				marcados.add(s1);
				for(Camino r : salientes) {
					//tiene que ser distinto a operativa
					if (marcados.contains(r.getSD()) && r.getSD().getEstado()!= EstadoSucursal.OPERATIVA) { 
						copiaMarcados = marcados.stream().collect(Collectors.toList());
						copiaCamino = camino.stream().collect(Collectors.toList());
						copiaCamino.add(r);
						buscarCaminosAux(r.getSD(), s2, copiaMarcados, copiaCamino, salida);
					}				
				}
			}
		
		}

		public List<Camino> getCaminoSaliente(Sucursal s) {
			List<Camino> retorno = new ArrayList<>();
			
			for (Camino r : caminos) {
				if (r.activa() && r.getOrigen().equals(e)) retorno.add(r);
			}
			
			return retorno;
		}
}
