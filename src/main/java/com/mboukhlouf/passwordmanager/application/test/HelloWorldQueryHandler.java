package com.mboukhlouf.passwordmanager.application.test;

import com.mboukhlouf.passwordmanager.application.jmediatr.RequestHandler;

public class HelloWorldQueryHandler implements RequestHandler<HelloWorldQuery, String> {

    @Override
    public String Handle(HelloWorldQuery request) {
        return "Hello World!";
    }
}
