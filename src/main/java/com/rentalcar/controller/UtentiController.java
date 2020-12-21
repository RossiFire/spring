package com.rentalcar.controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rentalcar.entities.TipoUtente;
import com.rentalcar.entities.Utente;
import com.rentalcar.service.UtentiService;
import com.rentalcar.service.UtentiServiceImp;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

@Controller
@RequestMapping("/utenti")
public class UtentiController {
	
	@Autowired
	private UtentiService utentiService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	HttpSession session;
	List<Utente> MainRecordSet;
	
	private int LimitePag = 20;
	private String OrderType = "DESC";
	private int OrderBy = 0;
	private int PageNum = 1;
	
	private String tipoUtente;
	
	public void getAllUtenti() {
		MainRecordSet = utentiService.selTutti();
	}
	
	
	@PostMapping(value = "/controlla")
	public String getHome(Model model, HttpServletRequest request) {
		List<Utente> recordset = null;
		long NumRecords = 0;
		
		getAllUtenti();
		
		if(MainRecordSet!= null) {
			
			//record trovati nella tabella
			NumRecords = MainRecordSet.size();
			
			recordset = MainRecordSet.stream().filter(u -> u.getId() != -1).skip(0)
					.limit(LimitePag).collect(Collectors.toList());
		}
	//	tipoUtente = session.getAttribute("tipoUtente").toString();
		model.addAttribute("titolo","Utenti Customer");
		model.addAttribute("NumRecords", NumRecords);
		model.addAttribute("utenti", recordset);
		model.addAttribute("LimitePag", LimitePag);
		model.addAttribute("orderBy", OrderBy);
		model.addAttribute("orderType", OrderType);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("tipoUtente", "ADMIN");
		return "customer";
	}
	
	
	
	@GetMapping
	public String getUtenti(Model model, HttpServletRequest request) {
		
		List<Utente> recordset = null;
		long NumRecords = 0;
		
		getAllUtenti();
		
		if(MainRecordSet!= null) {
			
			//record trovati nella tabella
			NumRecords = MainRecordSet.size();
			
			recordset = MainRecordSet.stream().filter(u -> u.getId() != -1).skip(0)
					.limit(LimitePag).collect(Collectors.toList());
		}
	//	tipoUtente = session.getAttribute("tipoUtente").toString();
		model.addAttribute("titolo","Utenti Customer");
		model.addAttribute("NumRecords", NumRecords);
		model.addAttribute("utenti", recordset);
		model.addAttribute("LimitePag", LimitePag);
		model.addAttribute("orderBy", OrderBy);
		model.addAttribute("orderType", OrderType);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("tipoUtente", "ADMIN");
		return "customer";
	}
	
//AGGIUNGI UTENTE
	
	@GetMapping(value="/aggiungi")
	public String formUtente(Model model) {
		
		Utente ut = new Utente();
		model.addAttribute("titolo", "Aggiungi utente");
		model.addAttribute("insUtente", ut);
		
		return "insUtente";
	}
	
	@PostMapping(value = "/aggiungi")
	public String inserisciUtente(@ModelAttribute("insUtente") Utente user) throws ParseException {
		

		TipoUtente tp = new TipoUtente(); 
		tp.setId(2);
		user.setTipoutente(tp);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		utentiService.Aggiungi(user);
		
		return "redirect:/utenti";
	}
	
// ELIMINA UTENTE
	
	@RequestMapping(value = "/elimina/{id}", method = RequestMethod.GET)
	public String eliminaUtente(@PathVariable("id") int idUtente, Model model) {
		
		Utente utente = utentiService.selById(idUtente);
		utentiService.Elimina(utente);
		
		return "redirect:/utenti";
	}
	
	
// MODIFICA UTENTE
	
	@GetMapping(value = "/modifica/{id}")
	public String compilaFormUtente(@PathVariable("id") int idUtente, Model model) {
		Utente u = utentiService.selById(idUtente);
		
		model.addAttribute("titolo", "Modifica Utente");
		model.addAttribute("modUtente", u);
		
		return "modUtente";
	}
	
	
	@PostMapping(value = "/modifica/{id}")
	public String updUtente(@ModelAttribute("modUtente") Utente u, Model model,HttpServletRequest request) {
		Utente user = utentiService.selById(u.getId());
		
		String data = u.getNascita().toString();
		
		user.setPassword(passwordEncoder.encode(u.getPassword()));
		utentiService.Aggiorna(user);
		
		return "redirect:/utenti";
	}
	
	
	
	
	
	
	
}
