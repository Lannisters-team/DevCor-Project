package ua.lannisters.devcor.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
	public String aboutPage(Model model) {
		return "about";
	}
	@RequestMapping(value = { "/templates" }, method = RequestMethod.GET)
	public String about2Page(Model model) {
		return "templates";
	}
	
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		return "main";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		model.addAttribute("title", "Admin");
		model.addAttribute("message", "Admin Page - This is protected page!");
		return "adminPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String loginPage(Model model, Principal principal) {
		model.addAttribute("title", "User Info");

		String userName = principal.getName();

		model.addAttribute("message",
				"User Info - This is protected page!. Hello " + userName);

		return "userInfoPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {
		model.addAttribute("title", "Access Denied!");

		if (principal != null) {
			model.addAttribute("message", "Hi " + principal.getName()
					+ "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg",
					"You do not have permission to access this page!");
		}
		return "403Page";
	}
}