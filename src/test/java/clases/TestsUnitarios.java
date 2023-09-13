package clases;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import enums.EstadoSucursal;
import gestores.GestorProducto;
import gestores.GestorSucursal;
import clases.Producto;
class TestsUnitarios {
	
	
	@Test
	public void test1CrearSucursal() {
		
		GestorSucursal gestorSucursal = new GestorSucursal().getInstance();
		// CREAMOS UNA SUCURSAL
		final Sucursal resultadoTest1 = gestorSucursal.crearSucursalGestor("Sucursal Santa Fe", LocalTime.of(6, 30), LocalTime.of(13, 00), EstadoSucursal.OPERATIVA);
		final int id= resultadoTest1.getId();
		Sucursal esperada=  new Sucursal(id,"Sucursal Santa Fe", LocalTime.of(6, 30), LocalTime.of(13, 00), EstadoSucursal.OPERATIVA);
		
		// AHORA VERIFICAMOS SI SE CREO  CORRECTAMENTE O NO
		Assertions.assertEquals(esperada, resultadoTest1);
		boolean activo=false;
		if (esperada.getEstado()==EstadoSucursal.OPERATIVA) activo= true;
		//LA SUCURSAL DEBE ESTAR ACTIVA. VERIFICAMOS
		assertTrue(activo);
		
	}
	


	@Test
	public void test2ActualizarProducto() {
		GestorProducto gestorProducto = new GestorProducto().getInstance();
		final Producto resultadoTest2 = gestorProducto.crearProductoGestor("Hamburguesa", "Hamburguesa de Carne. 300 gramos", 999.99, 300.50);
		String desc= resultadoTest2.modificarPrododucto("Hamburguesa", "Hamburguesa de Carne. 450 gramos", 1459.99, 449.50).getDescripcion();
		Double precio =resultadoTest2.modificarPrododucto("Hamburguesa", "Hamburguesa de Carne. 450 gramos", 1459.99, 449.50).getPrecioUnitario();
		Double peso =resultadoTest2.modificarPrododucto("Hamburguesa", "Hamburguesa de Carne. 450 gramos", 1459.99, 449.50).getPesoKg();
		// DEBE ACTUALIZARSE CORRECTAMENTE LAS MODIFICACIONES. VERIFICAMOS
		Assertions.assertEquals(desc, "Hamburguesa de Carne. 450 gramos");
		Assertions.assertEquals(precio, 2000.99);
		Assertions.assertEquals(peso, 449.50);
	}
	
		
	
	@Test
	public void test3Camino() {
		
		Sucursal sucursal1=  new Sucursal(45,"Sucursal Santa Fe", LocalTime.of(6, 30), LocalTime.of(13, 00), EstadoSucursal.OPERATIVA);
		Sucursal sucursal2=  new Sucursal(30,"Sucursal Parana", LocalTime.of(6, 30), LocalTime.of(18, 00), EstadoSucursal.NO_OPERATIVA);
		Sucursal sucursal3=  new Sucursal(25,"Sucursal Tucuman", LocalTime.of(6, 00), LocalTime.of(19, 00), EstadoSucursal.OPERATIVA);
		Sucursal sucursal4=  new Sucursal(5,"Sucursal Bariloche", LocalTime.of(7, 00), LocalTime.of(19, 00), EstadoSucursal.OPERATIVA);
		Sucursal sucursal5=  new Sucursal(25,"Sucursal Buenos Aires", LocalTime.of(7, 00), LocalTime.of(12, 30), EstadoSucursal.OPERATIVA);
		List<Sucursal> sucursalesDestinoSucursal1= new ArrayList<>();
		Camino camino50 = new Camino(50,sucursal1,sucursal3,10,EstadoSucursal.OPERATIVA,9599.99);
		Camino camino5 = new Camino(5,sucursal1,sucursal4,24,EstadoSucursal.OPERATIVA,9899.99);
		Camino camino31 = new Camino(31,sucursal1,sucursal5,7,EstadoSucursal.OPERATIVA,9000.00);
		sucursalesDestinoSucursal1.add(camino50.getSD());
		sucursalesDestinoSucursal1.add(camino5.getSD());
		sucursalesDestinoSucursal1.add(camino31.getSD());
		// NO QUEREMOS QUE UNO DE LOS DESTINOS SEA LA SUCURSAL2. VERIFICAMOS
		assertNotEquals(sucursal2, camino50.getSD());
		// LA LISTA NO DEBE SER VACIA. VERIFICAMOS
		assertFalse(sucursalesDestinoSucursal1.isEmpty());
		// TODAS LAS SUCURSALES DESTINOS DEBEN ESTAR ACTIVAS
		boolean activo= true;
		for(int i=0;i<sucursalesDestinoSucursal1.size();i++) {
			if(sucursalesDestinoSucursal1.get(i).getEstado()==EstadoSucursal.NO_OPERATIVA) activo=false;
		}
		assertTrue(activo);
		
		
	}

}
