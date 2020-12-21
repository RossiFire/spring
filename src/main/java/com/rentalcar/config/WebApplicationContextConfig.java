package com.rentalcar.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.rentalcar.service.CustomUserDetailsService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.rentalcar")
public class WebApplicationContextConfig implements WebMvcConfigurer{

	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
	 // stiamo dicendo che deve considerare anche i tiles come pagine e non solo la pagine jsp
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver(); 
		registry.viewResolver(viewResolver);
	}
	
	
	// cercare le pagine jsp in quel path sottoindicato
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
			
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	// localizzazione delle pagine
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");

		registry.addInterceptor(localeChangeInterceptor);
	}
	
	
	@Bean
	public LocaleResolver localeResolver(){

		CookieLocaleResolver r = new CookieLocaleResolver();
		r.setCookieName("localeInfo");
		r.setCookieMaxAge(24 * 60 * 60);
		r.setDefaultLocale(new Locale("it"));

		return r;
	}
	
		
	@Bean
	public MessageSource messageSource()
	{
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("language");
		
		return resource;
	}
	
}
