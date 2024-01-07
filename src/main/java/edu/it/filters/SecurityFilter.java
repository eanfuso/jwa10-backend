package edu.it.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.it.components.JWTUtil;
import edu.it.components.Utiles;
import edu.it.errores.UnauthorizedException;

public class SecurityFilter implements Filter {
	Logger logger = Logger.getLogger(getClass());
	
	private void verificarToken(String token) {
		// Hay que verificar que el token sea TOKEN_20220219
		// si es ese ? no hago nada, return
		// de lo contrario
		if (token == null) {
			throw new UnauthorizedException("Es necesario contar con un token");
		}
		try {
			JWTUtil.validarToken(token);
		}
		catch (io.jsonwebtoken.MalformedJwtException ex) {
			logger.warn("io.jsonwebtoken.MalformedJwtException");
			throw new UnauthorizedException("No se recibio un token adecuado");
		}
		catch (io.jsonwebtoken.SignatureException ex) {
			logger.warn("io.jsonwebtoken.SignatureException");
			throw new UnauthorizedException("token invalido");
		}
		catch (io.jsonwebtoken.ExpiredJwtException ex) {
			logger.warn("io.jsonwebtoken.ExpiredJwtException");
			throw new UnauthorizedException("token vencido, debe volver a loguearse");
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		
		logger.info("Estoy en el filtro security filter");
		
		var reqHTTP = (HttpServletRequest)request;
		var resHTTP = (HttpServletResponse)response;
		
		logger.info(reqHTTP.getHeader("X-TOKEN"));
		
		var path = reqHTTP.getServletPath();
		logger.info(path);
		
		if (path.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		logger.info("Dado que NO va a /login tiene que tener credenciales X-TOKEN valido");
		var exito = Utiles.manejarPosibleError(reqHTTP, resHTTP, () -> {
			// voy a asumir que NO tiene credenciales validas
			verificarToken(reqHTTP.getHeader("X-TOKEN"));
			return null;
		});
		
		if (exito) {
			chain.doFilter(request, response);
		}
	}

}
