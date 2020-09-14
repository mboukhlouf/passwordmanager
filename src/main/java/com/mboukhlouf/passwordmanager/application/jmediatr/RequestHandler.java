package com.mboukhlouf.passwordmanager.application.jmediatr;

public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {
    TResponse Handle(TRequest request) throws Exception;
}
