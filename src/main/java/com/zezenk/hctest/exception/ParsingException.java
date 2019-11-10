package com.zezenk.hctest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jt on 7/13/17.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParsingException extends RuntimeException {

    public ParsingException() {
        super();
    }

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
