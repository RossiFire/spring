package com.rentalcar.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rentalcar.entities.Utente;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UtentiService utentiService;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBuilder builder = null;
		builder = User.withUsername(username);
		Utente u = utentiService.selByUserDetails(username);
		builder.username(u.getNome());
		builder.password(u.getPassword());
		if(u.getTipoutente().getTipo().equals("ADMIN")) {
			builder.roles(u.getTipoutente().getTipo(), "CUSTOMER");
		}else {
			builder.roles(u.getTipoutente().getTipo());
		}
		
		System.out.println(u.getNome());
		System.out.println(u.getPassword());
		System.out.println(u.getNome());
		System.out.println(u.getPassword());
		System.out.println(u.getNome());
		System.out.println(u.getPassword());
		System.out.println(u.getNome());
		System.out.println(u.getPassword());
		System.out.println(u.getNome());
		System.out.println(u.getPassword());
		
		return builder.build();
	}

}
