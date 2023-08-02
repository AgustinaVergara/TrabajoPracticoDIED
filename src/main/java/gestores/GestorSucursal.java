package gestores;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

	public List<Sucursal> getSucursales() {
		return sucursales;
	}
	
	public final Sucursal buscarSucursalxNombre(String nombre){
		Sucursal s= new Sucursal();
		
		//que pasa si no existe esa sucursal? deberia hacer un try catch?
		//List<Sucursal> sucursalesA = this.dao.buscarSucursales();
		System.out.println("parametro nombre: " + nombre);
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
		//System.out.println("SIGUIENTE SUCURSAL " +siguienteIdSucursal);
		return new Sucursal(siguienteIdSucursal, nombre, horarioApertura, horarioCierre, estado);
	}
	
	public void eliminarSucursal(Sucursal s) {
		dao.eliminar(s);
		sucursales.remove(s);

	}
	


}
