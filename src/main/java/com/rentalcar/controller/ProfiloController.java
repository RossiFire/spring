package com.rentalcar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalcar.entities.Utente;
import com.rentalcar.service.UtentiService;

@Controller
@RequestMapping("/profilo")
public class ProfiloController {

	@Autowired
	private UtentiService utentiService;
	
	HttpSession session;
	
	@GetMapping
	public String getProfilo(Model model, HttpServletRequest request) {
		session = request.getSession();
		Utente u = utentiService.selById(Integer.parseInt(session.getAttribute("idCorrente").toString()));
		
		model.addAttribute("utente", u);
		model.addAttribute("titolo", "Informazioni del profilo");
		
		return "profilo";
		
	}
	
	
	
	
	
	
}
