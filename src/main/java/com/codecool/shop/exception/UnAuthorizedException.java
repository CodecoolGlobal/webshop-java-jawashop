package com.codecool.shop.exception;

public class UnAuthorizedException extends HttpException {

    public UnAuthorizedException() {
        super("You do not have permission to access this resource! ", 401);
    }
}
