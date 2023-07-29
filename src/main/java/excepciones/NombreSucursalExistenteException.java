package excepciones;

public class NombreSucursalExistenteException extends Exception {
	
	public NombreSucursalExistenteException() {
		super("El nombre ingresado es igual al de otra estaci�n. Por favor,\n ingrese otro nombre que no est� repetido.");
	}

}
