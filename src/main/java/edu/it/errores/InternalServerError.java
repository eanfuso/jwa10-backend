package edu.it.errores;

public class InternalServerError extends HttpException {
	public InternalServerError(String mensaje) {
    	status = 500;
        this.mensaje = mensaje;
	}
}
