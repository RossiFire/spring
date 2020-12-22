package com.rentalcar.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rentalcar.entities.Mezzo;
import com.rentalcar.entities.Prenotazione;
import com.rentalcar.entities.Utente;
import com.rentalcar.service.MezziService;
import com.rentalcar.service.PrenotazioniService;
import com.rentalcar.service.UtentiService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

	@Autowired
	private PrenotazioniService prenotazioniService;
	@Autowired
	private UtentiService utentiService;
	@Autowired
	private MezziService mezziService;
	
	private String tipoUtente;
	private int idCorrente;
	HttpSession session;
	
	
	@GetMapping
	public String getPrenotazioni(Model model, HttpServletRequest request) {
		session = request.getSession();
		tipoUtente = session.getAttribute("tipoUtente").toString();
		List <Prenotazione> prenotazioni;
		if(tipoUtente.equals("CUSTOMER")) {
			int idCorrente = utentiService.selByUserDetails(session.getAttribute("username").toString()).getId();
			prenotazioni = prenotazioniService.selByUserId(idCorrente);
		}else {
			prenotazioni= prenotazioniService.selTutti();			
		}
		session.setAttribute("tipoUtente", tipoUtente);
		session.setAttribute("idCorrente", idCorrente);
		model.addAttribute("titolo", "Prenotazioni");
		model.addAttribute("prenotazioni", prenotazioni);
		
		return "prenotazioni";
		
	}
	
	
	
	//AGGIUNGI
	
	@GetMapping(value = "/aggiungi")
	public String compilaAggiungi(Model model) {
		List <Utente> utenti = utentiService.selTutti(); 
		List <Mezzo> mezzi = mezziService.selTutti();
		
		Prenotazione p = new Prenotazione();
		
		model.addAttribute("titolo", "Nuova Prenotazione");
		model.addAttribute("newPrenotazione", p);
		model.addAttribute("utenti", utenti);
		model.addAttribute("mezzi", mezzi);
		
		return "insPrenotazione";
	}
	
	@PostMapping(value = "/aggiungi")
	public String aggiungiP(@ModelAttribute("newPrenotazione") Prenotazione p, Model model) {
		prenotazioniService.Aggiungi(p);	
		return "redirect:/prenotazioni";
	}
	
	
	// APPROVAZIONE
	@GetMapping(value = "/approva/{id}")
	public String approva(@PathVariable("id") int id, Model model) {
			Prenotazione p = prenotazioniService.selById(id);
			p.setApprovata(true);
			prenotazioniService.Aggiorna(p);
			
			return "redirect:/prenotazioni";
	}
	
	// MODIFICA
	
	@GetMapping(value = "/modifica/{id}")
	public String compilaModifica(@PathVariable("id") int id, Model model) {
		List <Utente> utenti = utentiService.selTutti(); 
		List <Mezzo> mezzi = mezziService.selTutti();
		
		Prenotazione p = prenotazioniService.selById(id);
		
		model.addAttribute("titolo", "Modifica Prenotazione");
		model.addAttribute("newPrenotazione", p);
		model.addAttribute("utenti", utenti);
		model.addAttribute("mezzi", mezzi);
		
		return "insPrenotazione";
	}
	
	@PostMapping(value = "/modifica/{id}")
	public String modifica(@ModelAttribute("newPrenotazione") Prenotazione p) {
			Utente utemp = new Utente();
			Mezzo mtemp = new Mezzo();
			utemp.setId(p.getUtentePrenotato().getId());
			mtemp.setId(p.getMezzoPrenotato().getId());
			Prenotazione pr = prenotazioniService.selById(p.getId());
			pr.setApprovata(p.isApprovata());
			pr.setUtentePrenotato(utemp);
			pr.setMezzoPrenotato(mtemp);
			pr.setDataInizio(p.getDataInizio());
			pr.setDataFine(p.getDataFine());
			prenotazioniService.Aggiorna(pr);
			return "redirect:/prenotazioni";
	}
	
	
	// ELIMINA
	@RequestMapping(value = "/elimina/{id}", method = RequestMethod.GET)
	public String elimina(@PathVariable("id") int id, Model model) {
			
			Prenotazione pr = prenotazioniService.selById(id);
			
			prenotazioniService.Elimina(pr);
			return "redirect:/prenotazioni";
	}
	
	
}
