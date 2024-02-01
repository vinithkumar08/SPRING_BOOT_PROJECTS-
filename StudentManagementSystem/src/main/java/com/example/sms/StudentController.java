package com.example.sms;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;


@Controller
public class StudentController {

	@Autowired
	StudentRepo repo;
	
	@RequestMapping("input")
	public String home() {
		return "input";
	}
	
	@RequestMapping("store")
	public String store(Student stu) {
		repo.save(stu);
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public ModelAndView view () {
	    ModelAndView mv = new ModelAndView("index");
	    List<Student> list = (List<Student>) repo.findAll();
	    mv.addObject("l",list);
		return mv;
	}
	
	@RequestMapping("showFormForUpdate/{id}")
	public ModelAndView update(@PathVariable (value = "id") int id) {
			Optional<Student> optional = repo.findById(id);
			Student stu;
			if (optional.isPresent()) {
				 stu = optional.get();
			} else {
				throw new RuntimeException(" Employee not found for id :: " + id);
			}
		    ModelAndView mv = new ModelAndView("update");
		    mv.addObject("s",stu);
		return mv;
	}
	
	@RequestMapping("deleteStudent/{id}")
	public String deleteStudentById(@PathVariable (value = "id") int id) {
		repo.deleteById(id);
		return "redirect:/home";
	}

}
