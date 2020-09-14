package com.mboukhlouf.passwordmanager.webui.services;

import javax.servlet.http.HttpServletRequest;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.ICurrentUserService;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CurrentUserService implements ICurrentUserService {

    @Override
    public boolean isAuthenticated() {
        HttpServletRequest request = getCurrentRequest();
        return request.getSession().getAttribute("id") != null;
    }

    @Override
    public Integer getId() {
        HttpServletRequest request = getCurrentRequest();
        return (Integer)request.getSession().getAttribute("id");
    }

    @Override
    public String getUsername() {        
        HttpServletRequest request = getCurrentRequest();
        return (String)request.getSession().getAttribute("username");
    }

    @Override
    public String getPassword() {
        HttpServletRequest request = getCurrentRequest();
        return (String)request.getSession().getAttribute("password");
    }
 
    private static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
        Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
        return servletRequest;
    }
}
