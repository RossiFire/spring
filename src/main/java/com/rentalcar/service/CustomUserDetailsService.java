package com.rentalcar.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rentalcar.dao.UtentiDao;
import com.rentalcar.entities.Utente;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UtentiDao utentiRepo;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utente u = utentiRepo.selByUserDetails(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(u.getTipoutente().getTipo());
		UserDetails userDetails = (UserDetails)new User(u.getNome(),
				u.getPassword(), Arrays.asList(authority));
		return userDetails;
	}

}
