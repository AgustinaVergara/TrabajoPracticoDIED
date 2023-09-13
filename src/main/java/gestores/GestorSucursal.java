package gestores;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import clases.Sucursal;
import dao.SucursalDao;
import dao.SucursalDaoImpl;
import enums.EstadoSucursal;

public class GestorSucursal {
	private static GestorSucursal gestor;
	private SucursalDao dao;
	private List<Sucursal> sucursales;
	private static Integer siguienteIdSucursal;
	
	public GestorSucursal(){
		dao = new SucursalDaoImpl();
		sucursales = new  ArrayList<>(dao.buscarSucursales());
	}
	
	public static GestorSucursal getInstance() {
		if (gestor == null) {
			gestor = new GestorSucursal();
	}
		
		return gestor;
	}
	
	public void refreshSucursales() {
	    sucursales = new ArrayList<>(dao.buscarSucursales());
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}
	
	public final Sucursal buscarSucursalxNombre(String nombre){
		Sucursal s= new Sucursal();
		
		//que pasa si no existe esa sucursal? deberia hacer un try catch?
		//List<Sucursal> sucursalesA = this.dao.buscarSucursales();
		//System.out.println("parametro nombre: " + nombre);
		for (int i=0; i<this.sucursales.size();i++) {
			//System.out.println("esta es la sucursal: "+ this.sucursales.get(i).getNombre());
			
			if(this.sucursales.get(i).getNombre().equals(nombre)) return this.sucursales.get(i);
		
		}
		
		
		return s;
	}

	public void agregarSucursal(Sucursal s) {
		sucursales.add(s);
		dao.crearSucursal(s);
	}
	
	public Sucursal crearSucursalGestor(String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoSucursal estado ) {
		siguienteIdSucursal = dao.getUltimoIdSucursal() + 1;
		return new Sucursal(siguienteIdSucursal, nombre, horarioApertura, horarioCierre, estado);
	}
	
	public void eliminarSucursal(Sucursal s) {
		dao.eliminar(s);
		sucursales.remove(s);

	}
	
	public void modificarSucursal(Integer id,String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoSucursal estado ) {
		Sucursal s = this.getSucursalPorId(id);
		s.modificarse(nombre, horarioApertura, horarioCierre, estado);
		
		dao.modificar(s);
	}
	
	public Sucursal getSucursalPorId(Integer id) {
		return (sucursales.stream().filter(e -> e.getId() == id).findFirst()).get();
	}


	public List<Sucursal> getSucursalesOperativas() {
		// TODO Auto-generated method stub
		
		return (sucursales.stream().filter(e  -> e.getEstado() == EstadoSucursal.OPERATIVA)).collect(Collectors.toList());
	}


	public static Integer getSiguienteIdSucursal() {
		return siguienteIdSucursal;
	}

	
	



}
