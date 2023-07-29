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
	
	
	public static void crearCamino(int id, String sucursalO, String sucursalD, double capacidad, EstadoSucursal estado) {
		// TODO Auto-generated method stub
		//CREAMOS UNA INSTANCIA DE CAMINO Y CARGAMOS VALORES INSERTADOS EN LA VENTANA ALTA CAMINO
		Camino camino= new Camino();
		camino.setId(id);
		camino.setSucursalOrigen(camino.buscarSucursal(sucursalO));
		camino.setSucursalDestino(camino.buscarSucursal(sucursalD));
		camino.setCapacidadMax(capacidad);
		camino.setEsOperativa(estado);
		CaminoSQLimplementacion.crearCamino(camino);
	}

	
	
}
	

