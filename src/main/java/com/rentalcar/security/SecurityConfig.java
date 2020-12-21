package com.rentalcar.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rentalcar.service.CustomUserDetailsService;
import com.rentalcar.service.UtentiService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService UserDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	  @Bean
	  public UserDetailsService userDetailsService() {
	    return new CustomUserDetailsService();
	  };
	
	
	
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	
	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception{
// auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider atp = new DaoAuthenticationProvider();
		atp.setUserDetailsService(UserDetailsService);
		atp.setPasswordEncoder(passwordEncoder());
		
		return atp;
	}
	
	private final static String[] ADMIN_UTENTI_MATCHER = {
			"/utenti/aggiungi/**",
			"/utenti/modifica/**",
			"/utenti/elimina/**",
			"/utenti/controlla/**",
	};
			
	
	
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/login/**").permitAll()
		.antMatchers("/").hasAnyRole("ANONYMOUS", "CUSTOMER")
		.antMatchers(ADMIN_UTENTI_MATCHER).access("hasRole('ADMIN')")
		.antMatchers("/utenti/**").hasAnyRole("CUSTOMER")
		.and()
		.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.formLogin()
			.loginPage("/login/form")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/utenti", true)
			.failureUrl("/login/form?error")
			.usernameParameter("username")
			.passwordParameter("password")
		.and()
			.exceptionHandling()
			.accessDeniedPage("/login/form?forbidden")
		.and()
			.logout()
			.logoutUrl("/login/form?logout");
//		//.and().csrf().disable();
	}
 
	public AuthenticationFilter authenticationFilter()throws Exception{
		
		AuthenticationFilter filter = new AuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(failureHandler());
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		
		return filter;
	}
	
	
	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/login/form?error");
	}
	
	
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		
		return auth;
	}
	
	
	
	
	
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserBuilder users = User.builder();
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		
//		manager.createUser(users
//				.username("Daniele")
//				.password(new BCryptPasswordEncoder().encode("admin"))
//				.roles("ADMIN", "CUSTOMER").build()
//				);		
//		manager.createUser(users
//				.username("Net")
//				.password(new BCryptPasswordEncoder().encode("info"))
//				.roles("CUSTOMER").build()
//				);
//		return manager;
//	}
	
	
}
