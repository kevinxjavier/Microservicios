package com.kevinpina.springboot.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {	// Segundo metodo en ejecutarse, aca se valida si se va a validar o no el filtro
		log.info("----------------------> Post - shouldFilter()");
		return true;	// si devuelve true ejecuta el metodo run();
	}

	@Override
	public Object run() throws ZuulException { 	// Tercer metodo en ejecutarse, aca se resuelve la logica de nuestro filtro
		log.info("----------------------> Post - run()");
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos %s seg", tiempoTranscurrido.doubleValue() / 1000.00));
		log.info(String.format("Tiempo transcurrido en milisegundos %s ms", tiempoTranscurrido));
		return null;
	}

	@Override
	public String filterType() {	// Primero metodo en ejecutarse, se define el tipo: "pre, post, route, errors"
		log.info("----------------------> Post - filterType()");
		return "post";
	}

	@Override
	public int filterOrder() {		// Cuarto metodo en ejecutarse
		log.info("----------------------> Post - filterOrder()");
		return 1;
	}

}
