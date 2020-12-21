package com.rentalcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalcar.entities.Utente;
import com.rentalcar.service.UtentiService;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private UtentiService utentiService;
	
	
	@RequestMapping(value = "index")
	public String home(Model model) {
		Utente ut = new Utente();
		model.addAttribute("titolo", "Login");
		model.addAttribute("avviso", "");
		model.addAttribute("utente", ut);
		
		return "index";
	}
	
	
	@RequestMapping
	public String home2(Model model) {
		Utente ut = new Utente();
		model.addAttribute("titolo", "Login");
		model.addAttribute("avviso", "");
		model.addAttribute("utente", ut);
		
		return "index";	
	}

}
