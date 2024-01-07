package edu.it.dtos;

public class ResultadoError {
	public final String status;
	public final String motivoError;
	
	public ResultadoError(String motivoError) {
		this.status = "ERROR";
		this.motivoError = motivoError;
	}
}
