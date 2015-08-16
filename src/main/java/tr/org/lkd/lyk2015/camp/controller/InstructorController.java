package tr.org.lkd.lyk2015.camp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.service.InstructorService;

/*
*cengizhan - Aug 16, 2015
*/

@Controller
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String getInstructorForm(@ModelAttribute Instructor instructor){
		
		return "instructors/create";
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public String postInstructorForm(@ModelAttribute Instructor instructor,@RequestParam("passwordAgain") String passwordAgain){
		
		passwordEquals(instructor, passwordAgain);
		
		return "redirect:/instructors";
	}

	private void passwordEquals(Instructor instructor, String passwordAgain) {
		
		if (passwordAgain.equals(instructor.getPassword())) {
			
			instructorService.create(instructor);
		}
	}
	
	@RequestMapping(value ="", method = RequestMethod.GET)
	public String getInstructors(Model model){
		
		model.addAttribute("instructors" , instructorService.getInstructors());
		 
		return "instructors/instructorList";
	}
		
	
}
