package excepciones;

public class CampoInvalidoException extends Exception {
	public CampoInvalidoException() {
		super("Se han ingresado uno o más datos inválidos. Por favor,\n" +
			  "revise y vuelva a intentarlo. \nRecuerde que: \n\n");
	}

}
