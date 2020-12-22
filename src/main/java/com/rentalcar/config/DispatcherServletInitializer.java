package com.rentalcar.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		 return new Class[] { WebApplicationContextConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
				WebApplicationContextConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		
		// in questo modo qualsiasi richiesta arriverà al nostro webserver verrà gestita dal dispatcher servlet
		return new String[] { "/" };
	}

}
