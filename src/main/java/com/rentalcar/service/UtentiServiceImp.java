package com.rentalcar.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalcar.dao.UtentiDao;
import com.rentalcar.entities.Utente;

@Service("utentiService")
@Transactional
public class UtentiServiceImp implements UtentiService{

	@Autowired
	private UtentiDao utentiRepo;

	@Override
	public Utente selById(int id) {
		return utentiRepo.selById(id);
	}

	@Override
	public List<Utente> selTutti() {
		return utentiRepo.selTutti();
	}

	@Override
	public void Aggiungi(Utente utente) {
		utentiRepo.Aggiungi(utente);
		
	}

	@Override
	public void Aggiorna(Utente utente) {
		utentiRepo.Aggiorna(utente);
		
	}

	@Override
	public void Elimina(Utente utente) {
		utentiRepo.Elimina(utente);
		
	}

	@Override
	public String ControllaUtente(String nome, String password) {
		Utente u = utentiRepo.ControllaUtente(nome, password);

		if (u.getNome().equals("-1")) {
			return "NO";
		} else {
			if (u.getTipoutente().getTipo().equals("ADMIN")) {
				return "ADMIN";
			} else {
				return "CUSTOMER";
			}
		}
	}

	@Override
	public int selByCredenziali(String nome, String password) {
		return utentiRepo.selByCredenziali(nome, password);
	}

	@Override
	public Utente selByUserDetails(String nome) {
		return utentiRepo.selByUserDetails(nome);
	}

}
