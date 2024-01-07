package edu.it.errores;

public class HttpException extends RuntimeException {
	public Integer status;
	public String mensaje;
}
