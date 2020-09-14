package com.mboukhlouf.passwordmanager.webui.controllers;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.ICurrentUserService;
import com.mboukhlouf.passwordmanager.application.jmediatr.Mediator;
import com.mboukhlouf.passwordmanager.application.passwords.commands.AddPassword.AddPasswordCommand;
import com.mboukhlouf.passwordmanager.application.passwords.commands.DeletePassword.DeletePasswordCommand;
import com.mboukhlouf.passwordmanager.application.passwords.queries.GetUserPasswords.GetUserPasswordsQuery;
import com.mboukhlouf.passwordmanager.application.passwords.queries.GetUserPasswords.UserPasswordDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "passwords")
public class PasswordsController {
    private final ICurrentUserService currentUserService;
	private final Mediator mediator;
	
	@Autowired
	public PasswordsController(ICurrentUserService currentUserService,
        Mediator mediator) {
        this.currentUserService = currentUserService;
        this.mediator = mediator;
    }

    @RequestMapping(value = { "", "index"})
    public String index(Model model) throws Exception {
        if(!currentUserService.isAuthenticated()) {
            return "redirect:/authentication/login";
        }
        Iterable<UserPasswordDto> passwords = mediator.send(new GetUserPasswordsQuery());
        model.addAttribute("username", currentUserService.getUsername());
        model.addAttribute("passwords", passwords);
        return "passwords/index";
    }

    @RequestMapping(value = {"add"})
    public String add(Model model) throws Exception {
        if(!currentUserService.isAuthenticated()) {
            return "redirect:/authentication/login";
        }
        model.addAttribute("username", currentUserService.getUsername());
        return "passwords/add";
    }

    @RequestMapping(value = {"add"}, method = RequestMethod.POST)
    public String addPost(@RequestParam String label, 
    @RequestParam String account,
    @RequestParam String password,
    @RequestParam String url,
    @RequestParam String tags) throws Exception {
        if(!currentUserService.isAuthenticated()) {
            return "redirect:/authentication/login";
        }
        
        AddPasswordCommand command = new AddPasswordCommand(label, account, password, url, tags);
        mediator.send(command);
        return "redirect:/passwords";
    }

    @RequestMapping(value = {"delete"})
    public String deletePost(@RequestParam Integer id) throws Exception {
        if(!currentUserService.isAuthenticated()) {
            return "redirect:/authentication/login";
        }

        DeletePasswordCommand command = new DeletePasswordCommand(id);
        mediator.send(command);
        return "redirect:/passwords/";
    }
}
