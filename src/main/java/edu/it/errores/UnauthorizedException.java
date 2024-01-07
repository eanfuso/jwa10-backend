package edu.it.errores;

public class UnauthorizedException extends HttpException {
	public UnauthorizedException(String mensaje) {
    	status = 401;
        this.mensaje = mensaje;
    }
}
