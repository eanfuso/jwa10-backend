package edu.it.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.it.components.Utiles;
import edu.it.errores.BadRequestException;
import edu.it.model.Alumno;

public class AlumnosController extends HttpServlet {
	private void getGenerico(HttpServletResponse response) {
		response.setStatus(200);
	}
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {    		
            Utiles.manejarRespuesta(request, response, () -> {
            	return Utiles.leerTodosLosRegistros();
            });
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
			Utiles.manejarRespuesta(request, response, () -> {
				Alumno a = Utiles.deserializarInputStream(request, Alumno.class);
				Utiles.persistirObjeto(a);
				return "";
			}, 201);
    }
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    
    	Utiles.manejarRespuesta(request, response, () -> {
    		Utiles.validarPathInfoNotNull(request.getPathInfo());
    		var id = request.getPathInfo().replace("/", "");
    		Utiles.validarPathInfo(id);
    		Alumno a = Utiles.deserializarInputStream(request, Alumno.class);
    		if (a.id.equals(id)==false) {
    			throw new BadRequestException("Los ids difieren");
    		}
    		Utiles.persistirObjeto(a);
        	return "";
        });
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
    	Utiles.manejarRespuesta(request, response, () -> {
    		var id = request.getPathInfo().replace("/", "");
    		Utiles.validarPathInfo(id);
        	Utiles.borrarObjeto(id);
        	return "";
        });   
    }   
}
