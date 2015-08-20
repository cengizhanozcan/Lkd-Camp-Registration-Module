package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String getCreateCourse(@ModelAttribute Course course) {

		return "courses/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String postCreateCourse(@ModelAttribute @Valid Course course, BindingResult bindingResult) {

		this.courseService.create(course);
		return "redirect:/courses";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getCourseList(Model model) {

		model.addAttribute("courses", this.courseService.getAll());
		return "courses/courseList";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getUpdate(@PathVariable("id") Long id, Model model) {

		Course course = this.courseService.getById(id);
		model.addAttribute("course", course);

		return "courses/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postUpdate(@ModelAttribute Course course) {

		this.courseService.update(course);

		return "redirect:/courses";
	}

}
