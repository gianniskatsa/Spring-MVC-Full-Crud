package org.pcedu.controllers;

import java.util.List;

import org.pcedu.entities.Trainer;
import org.pcedu.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AppController {

	@Autowired
	private TrainerService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Trainer> listTrainers = service.listAll();
		model.addAttribute("listTrainers", listTrainers);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewTrainerPage(Model model) {
		Trainer trainer = new Trainer();
		model.addAttribute("trainer", trainer);
		
		return "new_trainer";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTrainer(@Valid@ModelAttribute("trainer") Trainer trainer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			return "new_trainer";
		}else{
			service.save(trainer);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditTrainerPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_trainer");
		Trainer trainer = service.get(id);
		mav.addObject("trainer", trainer);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteTrainer(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
