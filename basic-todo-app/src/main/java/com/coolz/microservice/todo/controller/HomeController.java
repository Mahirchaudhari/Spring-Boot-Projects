package com.coolz.microservice.todo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Mahir
 *
 */
@Controller
@SessionAttributes("username")
public class HomeController {

	@GetMapping(value = "/")
	public String gotoHomePage(ModelMap modelMap) {
		modelMap.put("username", loggeedInUserName());
		return "welcome";
	}

	private String loggeedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
