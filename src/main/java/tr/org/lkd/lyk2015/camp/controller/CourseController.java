package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.service.CourseService;

/*
*cengizhan - Aug 16, 2015
*/


@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateCourse(@ModelAttribute Course course){
		
		return "courses/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String postCreateCourse(@ModelAttribute Course course){
		
		courseService.create(course);
		return "redirect:/courses";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getCourseList(Model model){
		
		model.addAttribute("courses", courseService.getAll());
		return "courses/courseList";
	}
	
	
}
