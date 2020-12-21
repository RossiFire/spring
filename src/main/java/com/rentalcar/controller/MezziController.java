package com.rentalcar.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalcar.entities.Mezzo;
import com.rentalcar.entities.TipoMezzo;
import com.rentalcar.service.MezziService;
import com.rentalcar.service.TipoMezzoService;

@Controller
@RequestMapping("/mezzi")
public class MezziController {
	
	@Autowired
	private MezziService mezziService;
	
	@Autowired
	private TipoMezzoService tipoMezzoService;
	
	
	
	@GetMapping
	public String tuttiMezzi(Model model, HttpServletRequest request) {
		
		List<Mezzo> recordMezzi  = mezziService.selTutti();
		recordMezzi = recordMezzi.stream().limit(20).skip(0).collect(Collectors.toList());
		
		
		model.addAttribute("titolo", "Lista Mezzi");
		model.addAttribute("mezzi", recordMezzi);
		
		return "adminMezzi";
	}
	
	
// AGGIUNGI MEZZO
	@GetMapping(value ="/aggiungi")
	public String formAggiungi(Model model) {
		Mezzo m = new Mezzo();
		List <TipoMezzo> tipiMezzi = tipoMezzoService.selTutti();
		
		model.addAttribute("azione", "aggiungi");
		model.addAttribute("titolo", "Aggiungi Mezzo");
		model.addAttribute("tipiMezzi", tipiMezzi);
		model.addAttribute("newMezzo", m);
		
		return "insMezzo";
	}
	
	
	@PostMapping(value = "/aggiungi")
	public String aggiungiMezzo(@ModelAttribute("newMezzo") Mezzo mezzo) {
			mezziService.Aggiungi(mezzo);
			
			return "redirect:/mezzi";
	}
	
	

//MODIFICA 
	@GetMapping(value ="/modifica/{id}")
	public String loadForm(@PathVariable("id") int idMezzo, Model model) {
		Mezzo m = mezziService.selById(idMezzo);
		List <TipoMezzo> tipiMezzi = tipoMezzoService.selTutti();
		
		model.addAttribute("azione", "/RentalCarApp/mezzi/modifica/" + m.getId());
		model.addAttribute("titolo", "Modifica Mezzo");
		model.addAttribute("tipiMezzi", tipiMezzi);
		model.addAttribute("newMezzo", m);
		return "insMezzo";
	}
	
	
	@PostMapping(value = "/modifica/{id}")
	public String aggiornaMezzo(@ModelAttribute("newMezzo")Mezzo mezzo, Model model, HttpServletRequest request) {
		Mezzo m = mezziService.selById(mezzo.getId());
		TipoMezzo tp = new TipoMezzo();
		tp.setId(mezzo.getTipomezzo().getId());
		m.setCasaCostr(mezzo.getCasaCostr());
		m.setModello(mezzo.getModello());
		m.setTarga(mezzo.getTarga());
		m.setTipomezzo(tp);
		mezziService.Aggiorna(m);
		return "redirect:/mezzi";
	}
	
	
// ELIMINA MEZZO
	@GetMapping(value ="/elimina/{id}")
	public String eliminaMezzo(@PathVariable("id") int idMezzo, Model model) {
		Mezzo m = mezziService.selById(idMezzo);
		mezziService.Elimina(m);
		
		return "redirect:/mezzi";
	}
}
