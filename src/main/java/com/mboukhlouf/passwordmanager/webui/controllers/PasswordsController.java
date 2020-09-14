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
        return "passwords/index";
    }

    @RequestMapping(value = {"add"})
    public String add(Model model) throws Exception {
        if(!currentUserService.isAuthenticated()) {
            return "redirect:/authentication/login";
        }

        AddPasswordCommand command = new AddPasswordCommand("label", "account", "password", "url", "tags");
        Integer passwordId = mediator.send(command);
        model.addAttribute("username", currentUserService.getUsername());
        return "passwords/add";
    }

    @RequestMapping(value = {"delete"})
    @ResponseBody
    public String deletePost() throws Exception {
        if(!currentUserService.isAuthenticated()) {
            return "redirect:/authentication/login";
        }

        DeletePasswordCommand command = new DeletePasswordCommand(8);
        Integer passwordId = mediator.send(command);
        return "passwordId";
    }
}
