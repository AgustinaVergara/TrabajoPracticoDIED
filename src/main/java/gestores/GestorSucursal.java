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
	
	private GestorSucursal(){
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

	public void agregarSucursal(Sucursal s) {
		sucursales.add(s);
		dao.crearSucursal(s);
	}
	
	public Sucursal crearSucursalGestor(String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoSucursal estado ) {
		siguienteIdSucursal = dao.getUltimoIdSucursal() + 1;
		System.out.println("SIGUIENTE SUCURSAL " +siguienteIdSucursal);
		return new Sucursal(siguienteIdSucursal, nombre, horarioApertura, horarioCierre, estado);
	}

	


}