package com.rentalcar.service;

import java.util.List;
import com.rentalcar.entities.Utente;

public interface UtentiService {

	
	Utente selById(int id);
	
	List<Utente> selTutti();
	
	void Aggiungi(Utente utente);
	
	void Aggiorna(Utente utente);
	
	void Elimina(Utente utente);
	
	String ControllaUtente(String nome, String password);
	
	int selByCredenziali(String nome,String password);
	
	Utente selByUserDetails(String nome);
}
