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
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.service.AdminService;

/*
*cengizhan - Aug 16, 2015
*/

@Controller
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateAdmin(@ModelAttribute Admin admin) {

		return "admins/createAdmin";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String postCreateAdmin(@ModelAttribute @Valid Admin admin, BindingResult bindingResult,
			@RequestParam("passwordAgain") String passwordAgain) {

		System.out.println("burda");

		this.passwordEquals(admin, passwordAgain);

		return "redirect:/admins";
	}

	private void passwordEquals(Admin admin, String passwordAgain) {
		if (passwordAgain.equals(admin.getPassword())) {

			this.adminService.create(admin);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getAdmins(Model model) {

		model.addAttribute("adminList", this.adminService.getAdmins());
		return "admins/adminList";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getUpdate(@PathVariable("id") Long id, Model model) {

		Admin admin = this.adminService.getById(id);
		model.addAttribute("admin", admin);

		return "admins/updateAdmin";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postUpdate(@ModelAttribute Admin admin) {

		this.adminService.update(admin);
		return "redirect:/admins";
	}

}
