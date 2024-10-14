package com.aneeshreddykota.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aneeshreddykota.modal.Skill;
import com.aneeshreddykota.repository.SkillRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {
	
	
	private SkillRepository skillRepository;
	
	public SkillController() {
		
	}
	
	@Autowired
	public SkillController(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}
	
	@GetMapping("/skills")
	public @ResponseBody List<Skill> getAllSkills(){
		List<Skill> skillsList = skillRepository.findAll();
		return skillsList;
	}

	@PostMapping("/skill")
	public @ResponseBody Skill addSkill(@RequestBody Skill skill) {
		Skill skillAdded = skillRepository.save(skill);
		return skillAdded;
	}
	
	@DeleteMapping("/skill/{id}")
	public @ResponseBody void removeSkillById(@PathVariable int id) {
		skillRepository.deleteById(id);
	}
}

