package com.example.luckybank.Exception;

import com.example.luckybank.Exception.InsufficientFundsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    public ModelAndView handleInsufficientFundsException(InsufficientFundsException ex) {
        ModelAndView modelAndView = new ModelAndView("error-page");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }
}
