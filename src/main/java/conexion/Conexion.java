package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL =  "jdbc:mysql://localhost:3306/tpdied";
	private static final String USUARIO = "root";
	private static final String CLAVE =  "died2023";
	
	/*El metodo conectar se debe llamar que necesitemos hacer algo en la bd por eso es mejor cargar el controlador en 
	en un bloque static aparte, de este modo se carga una unica vez cuando se ejecuta la aplicacion independientemente
	de que llamemos el metodo conectar. Es  mas eficiente*/
	
	static {
		try {
			//Cargar controlador
			//Class.forName("com.mysql.jdbc.Driver"); deprecated si funciona el otro borrar esto
			Class.forName(CONTROLADOR);
		}catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		try {
			//Hacer la conexion
			conexion = DriverManager.getConnection(URL,USUARIO,CLAVE);
			System.out.println("Conexion a la bd exitosa");
		}  catch(SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		
		return conexion;
	}

}
