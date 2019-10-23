package com.kevinpina.springboot.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {	// Segundo metodo en ejecutarse, aca se valida si se va a validar o no el filtro
		log.info("----------------------> Pre - shouldFilter()");
		return true;	// si devuelve true ejecuta el metodo run();
	}

	@Override
	public Object run() throws ZuulException { 	// Tercer metodo en ejecutarse, aca se resuelve la logica de nuestro filtro
		log.info("----------------------> Pre - run()");
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		return null;
	}

	@Override
	public String filterType() {	// Primero metodo en ejecutarse, se define el tipo: "pre, post, route"
		log.info("----------------------> Pre - filterType()");
		return "pre";
	}

	@Override
	public int filterOrder() {		// Cuarto metodo en ejecutarse
		log.info("----------------------> Pre - filterOrder()");
		return 1;
	}

}
