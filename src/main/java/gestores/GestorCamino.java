package gestores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import clases.*;
import conexion.Conexion;
import dao.*;
//import gestores.GestorSucursal;
import enums.*;

public class GestorCamino {
	private List<Camino> caminos;
	private static GestorCamino gestor;
	private CaminoDao caminoDAO;
	private static Integer siguienteIdCamino; 
	
	//CONSTRUCTOR
	public GestorCamino(){
		caminoDAO = new CaminoSQLimplementacion();
		this.caminos=new ArrayList<Camino>();
		//caminos = new  ArrayList<>(caminoDAO.buscarCaminos());
		
	}
	
	
	//INSTANCIA CONSTRUCTOR
	public static GestorCamino getInstance() {
		if (gestor == null) {
			gestor = new GestorCamino();
		}
		
		return gestor;
	}
	
	public List<Camino> getCaminos() {
		return caminos;
	}
	
	//BUSCAMOS SUCURSAL POR NOMBRE
	public final Sucursal buscarSucursal(String nombre){
		Sucursal s= new Sucursal();
		GestorSucursal buscar = new GestorSucursal();
		s= buscar.buscarSucursalxNombre(nombre);
		System.out.println("nombre sucursal: "+ s.getNombre());
		return s;
	}
	
	public Camino crearCaminoGestor(Sucursal sucursalO, Sucursal sucursalD, double capacidad, EstadoSucursal estado, int t) {
		
		//CREAMOS UNA INSTANCIA DE CAMINO Y CARGAMOS VALORES INSERTADOS EN LA VENTANA ALTA CAMINO
	/*	CaminoSQLimplementacion caminoSQLimplementacion = new CaminoSQLimplementacion();
		caminoSQLimplementacion.crearCamino(camino);*/
		siguienteIdCamino = caminoDAO.getUltimoIdCamino() + 1;
		
		return new Camino(siguienteIdCamino, sucursalO, sucursalD, t, estado, capacidad);
		
	}
	public void agregarCamino(Camino camino) {
		caminos.add(camino);
		caminoDAO.crearCamino(camino);
	}

	
	
}
	

