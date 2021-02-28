package com.company.aspect;


import com.company.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionLogAdvice {

    private static final Logger LOG=Logger.getLogger(ExceptionLogAdvice.class.getName());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ResponseBody
    public ResponseEntity<ResponseDTO> handleValidationException(final Exception exception){

        LOG.log(Level.SEVERE,null,exception);

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).
                body(ResponseDTO.of(exception.getMessage(), 5, "Bad GATEWAY error happened"));


    }
}
