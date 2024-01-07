package edu.it.errores;

public class BadRequestException extends HttpException {
    public BadRequestException(String mensaje) {
    	status = 400;
        this.mensaje = mensaje;
    }
}
