package com.code.user.service.exceptions;

import org.springframework.web.bind.annotation.RequestMapping;

public class ResourcsNotFoundException extends  RuntimeException{

    public ResourcsNotFoundException ()
    {
        super("Resource Not found on server !!");
    }
    public ResourcsNotFoundException (String message)
    {
        super(message);
    }


}
