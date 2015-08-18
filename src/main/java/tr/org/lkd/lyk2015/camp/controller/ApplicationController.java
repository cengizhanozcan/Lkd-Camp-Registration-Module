package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.controller.validation.ApplicationFormValidator;
import tr.org.lkd.lyk2015.camp.model.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.service.CourseService;

/*
*cengizhan - Aug 17, 2015
*/

@Controller
@RequestMapping("/applicationForm")
public class ApplicationController {

	@Autowired
	CourseService courseService;

	@Autowired
	ApplicationFormValidator applicationFormValidator;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(this.applicationFormValidator);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getApplicationForm(@ModelAttribute("form") ApplicationFormDto applicationFormDto, Model model) {

		model.addAttribute("courses", this.courseService.getAll());

		return "applicationForm";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String postApplicationForm(@ModelAttribute("form") @Valid ApplicationFormDto applicationFormDto,
			BindingResult bindingResult, Model model) {

		return "applicationForm";
	}
}
