package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("")
	public String testMethod(Model model){
		
		model.addAttribute("message","Hello World Buradayıkkk :D");
		return "test";
	}
	
}
