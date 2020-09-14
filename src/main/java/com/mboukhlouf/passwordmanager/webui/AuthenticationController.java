package com.mboukhlouf.passwordmanager.webui;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.mboukhlouf.passwordmanager.application.authentication.commands.authenticateuser.AuthenticateUserCommand;
import com.mboukhlouf.passwordmanager.application.authentication.commands.registeruser.RegisterUserCommand;
import com.mboukhlouf.passwordmanager.application.common.abstractions.services.ICurrentUserService;
import com.mboukhlouf.passwordmanager.application.common.dtos.UserDto;
import com.mboukhlouf.passwordmanager.application.jmediatr.Mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "authentication")
public class AuthenticationController {
    private final ICurrentUserService currentUserService;
	private final Mediator mediator;
	
	@Autowired
	public AuthenticationController(ICurrentUserService currentUserService,
        Mediator mediator) {
        this.currentUserService = currentUserService;
        this.mediator = mediator;
    }
    
    @RequestMapping(value = {"login", ""}, method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        if(currentUserService.isAuthenticated()) {
            return "redirect:/passwords";
        }
        return "authentication/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String loginPost(@RequestParam String username, @RequestParam String password, 
    HttpServletRequest request) throws Exception {
        if(currentUserService.isAuthenticated()) {
            return "redirect:/passwords";
        }

        UserDto user = mediator.Send(new AuthenticateUserCommand(username, password));
        if(user == null) {
            return "redirect:authentication/login";
        }
        request.getSession().setAttribute("id", user.getId());
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("password", password);
        return user.getUsername() + " " + user.getId();
    }

    @RequestMapping(value = "register")
    public String register(Model model, HttpServletRequest request) {
        if(currentUserService.isAuthenticated()) {
            return "redirect:/passwords";
        }
        return "authentication/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public String regsiterPost(@RequestParam String username, @RequestParam String password, 
    @RequestParam String confirmPassword, 
    HttpServletRequest request) throws Exception {
        if(currentUserService.isAuthenticated()) {
            return "redirect:/passwords";
        }
        mediator.Send(new RegisterUserCommand(username, password));
        return "Registered successfully";   
    }
}
