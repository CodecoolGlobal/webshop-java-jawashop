package com.codecool.shop.exception;

public class InternalServerException extends HttpException {

    public static final String MESSAGE = "Sorry but while we processed your request we encountered a technical difficulty on our side. We will investigate the issue! Thank You for your patience!";

    public InternalServerException(Throwable e) {
        super(MESSAGE, 500, e);
    }
}
