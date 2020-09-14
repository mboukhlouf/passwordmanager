package com.mboukhlouf.passwordmanager.application.jmediatr;

import java.util.Map;
import java.util.HashMap;

public class Mediator {
    private static final Map<Class<?>, RequestHandler<?,?>> requestHandlers = new HashMap<Class<?>, RequestHandler<?,?>>();

    public static void AddRequest(Class<?> requestType, RequestHandler<?, ?> handler) {
        if(!Request.class.isAssignableFrom(requestType)) {
            throw new IllegalArgumentException("The request class should implement the Request interface.");
        }
        requestHandlers.put(requestType, handler);
    }

    public <TResponse> TResponse send(Request<TResponse> request) throws Exception
    {
        if(request == null) {
            throw new IllegalArgumentException();
        }

        Class<?> requestClass = request.getClass();
        if(!requestHandlers.containsKey(requestClass)) {
            throw new IllegalArgumentException("The request is not recognized.");
        }

        @SuppressWarnings("unchecked")
        RequestHandler<Request<TResponse>, TResponse> handler = (RequestHandler<Request<TResponse>, TResponse>)requestHandlers.get(requestClass);
        return handler.Handle(request);
    }
}