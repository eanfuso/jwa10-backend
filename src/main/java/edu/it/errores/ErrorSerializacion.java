package edu.it.errores;

public class ErrorSerializacion extends HttpException {
    public ErrorSerializacion(String mensaje) {
    	status = 400;
        this.mensaje = mensaje;
    }
}
