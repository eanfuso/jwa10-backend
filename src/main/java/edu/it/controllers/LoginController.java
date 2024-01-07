package edu.it.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import edu.it.components.JWTUtil;
import edu.it.components.Utiles;
import edu.it.dtos.UsuPass;
import edu.it.errores.NotFoundException;
import edu.it.model.Usuario;

public class LoginController extends HttpServlet {
	private Logger logger = Logger.getLogger(getClass());
	
	/*
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
		Utiles.manejarRespuesta(request, response, () -> {
			logger.trace("Esto es un trace");
			logger.debug("Esto es un debug");
			logger.info("Esto es info");
			logger.warn("Esto es un warning");
			logger.error("Esto es un error");
			
			return "prueba log4j";
		});
	}
	*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
	
		/*
		 * 1) Recibir el objeto usuPass 
		 * 2) Ir a la base encontrar el usuario
		 * 3) junto con la password enviada ? + el salt de la base
		 *    lo voy a pasar por el alogritmo sha256Hex
		 * 4) Si coninciden las claves, la password es correcta
		 */
		
		Utiles.manejarRespuesta(request, response, () -> {
			UsuPass usuPass = Utiles.deserializarInputStream(request, UsuPass.class);
			Usuario usuario = Utiles.leerUnUsuario(usuPass.usuario);
			String passAEncriptar = String.join("__", usuPass.password, usuario.salt);
			DigestUtils.sha256Hex(passAEncriptar);
			String encriptada = DigestUtils.sha256Hex(passAEncriptar);
			
			System.out.println(usuario.passwordEnciptada);
			System.out.println(encriptada);
			
			if (!usuario.passwordEnciptada.equals(encriptada)) {
				throw new NotFoundException("");
			}
			
			return JWTUtil.crearJWT(usuPass.usuario);
		});	
		
	}
}
