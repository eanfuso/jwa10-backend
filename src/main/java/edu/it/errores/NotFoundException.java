package edu.it.errores;

public class NotFoundException extends HttpException {
    public NotFoundException(String mensaje) {
    	status = 404;
        this.mensaje = mensaje;
    }
}
