package edu.it.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class Filtro1 implements Filter {
	Logger logger = Logger.getLogger(getClass());

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		logger.info("Estoy en el filtro 1");
		logger.info("Pateando la pelota al siguiente filtro");
		chain.doFilter(request, response);
	}
}
