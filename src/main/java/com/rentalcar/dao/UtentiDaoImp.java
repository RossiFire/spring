package com.rentalcar.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import com.rentalcar.entities.Utente;

@Repository
public class UtentiDaoImp extends AbstractDao<Utente, Integer> implements UtentiDao{

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Utente> selTutti(){
		String jpql = "SELECT a FROM Utente a";
		List<Utente> utenti = entityManager.createQuery(jpql).getResultList();	
		return utenti;
	}


	@Override
	public void Aggiungi(Utente utente) {
		super.Aggiungi(utente);

	}

	@Override
	public void Aggiorna(Utente utente) {
		super.Aggiorna(utente);
		
	}

	@Override
	public void Elimina(Utente utente) {
		String jqpl = "DELETE FROM Utente a WHERE a.id = :uid";
		entityManager.createQuery(jqpl).setParameter("uid", utente.getId()).executeUpdate();
	}




	@Override
	public Utente selById(int id) {
		String jpql = "SELECT a FROM Utente a WHERE a.id = :uid";
		Utente u = (Utente) entityManager.createQuery(jpql).setParameter("uid", id).getSingleResult();
	return u;
	}


	@Override
	public Utente ControllaUtente(String nome, String password) {
		Utente nonEs = new Utente();
		String jpql = "SELECT a FROM Utente a WHERE a.nome = :name AND a.password = :pwd";
		List<Utente> u =entityManager.createQuery(jpql).setParameter("name", nome)
				.setParameter("pwd", password).getResultList();
		if(u.isEmpty()) {
			nonEs.setNome("-1");
			return nonEs;
		}else {
			return u.get(0);
		}
		
	}


	@Override
	public int selByCredenziali(String nome, String password) {
		String jpql = "SELECT a FROM Utente a WHERE a.nome = :name AND a.password = :pwd";
		Utente u = (Utente) entityManager.createQuery(jpql).setParameter("name", nome)
				.setParameter("pwd", password).getSingleResult();
		if(u.getNome().equals(null)) {
			return -1;
		}else {
			return u.getId();
		}
	}


	@Override
	public Utente selByUserDetails(String nome) {
		String jpql = "SELECT a FROM Utente a WHERE a.nome = :name";
		List<Utente> utenti = entityManager.createQuery(jpql).setParameter("name", nome).getResultList();
		if(utenti.isEmpty()) {
			throw new NoResultException("NESSUN UTENTE TROVATO");
		}else {
			return utenti.get(0);
		}
	}



	

}
