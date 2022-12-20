package me.eladiofeijoo.laptops.exceptions;

import me.eladiofeijoo.laptops.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice
public class LaptopNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(LaptopNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDto laptopBadRequestHandler(HttpServletRequest req, LaptopNotFoundException ex) {

        return new ErrorDto(LocalDate.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
                req.getServletPath());
    }
}
