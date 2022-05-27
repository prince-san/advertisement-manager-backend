package com.princesan.advertisementmanagerbackend.configuration;

import com.princesan.advertisementmanagerbackend.exceptions.request.RequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<Object> handleSqlIntegrityConstraintViolation(SQLIntegrityConstraintViolationException ex,
                                                                           WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = RequestException.class)
    protected ResponseEntity<Object> handleNoNewMatchingBanners(RequestException ex,
                                                                WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
                                                                WebRequest request) {
        return handleExceptionInternal(ex, ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .reduce((v1, v2) -> v1 + ", " + v2), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
