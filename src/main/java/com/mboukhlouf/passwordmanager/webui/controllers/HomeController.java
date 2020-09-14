package com.mboukhlouf.passwordmanager.webui.controllers;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.ICurrentUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "home", "" })
public class HomeController {
    private final ICurrentUserService currentUserService;
	
	@Autowired
	public HomeController(ICurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @RequestMapping(value = { "", "index"})
    public String index() {
        if(!currentUserService.isAuthenticated()) {
            return "redirect:/authentication/login";
        }
        else {
            return "redirect:/passwords/index";
        }
    }
}
