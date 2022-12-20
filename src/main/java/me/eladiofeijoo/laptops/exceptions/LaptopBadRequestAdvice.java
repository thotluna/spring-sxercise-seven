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
public class LaptopBadRequestAdvice {
    @ResponseBody
    @ExceptionHandler(LaptopBadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDto laptopBadRequestHandler(HttpServletRequest req, LaptopBadRequest ex) {

        return new ErrorDto(LocalDate.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                req.getServletPath());
    }
}
