package com.rentalcar.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
		Utente u = utentiService.selByUserDetails(username);
		builder.username(u.getNome());
		builder.password(u.getPassword());
		builder.roles(u.getTipoutente().getTipo());
		
		return builder.build();
	}

}
