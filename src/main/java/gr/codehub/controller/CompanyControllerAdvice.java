package gr.codehub.controller;


import gr.codehub.exception.DepartmentNotAddedException;
import gr.codehub.exception.DepartmentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CompanyControllerAdvice extends ResponseEntityExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(AccountNotFoundException ex) {
        log.error("Requested account not found");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Account not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }


    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(DepartmentNotFoundException ex) {
        log.error("Requested department not found");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Department not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }



    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(DepartmentNotAddedException.class)
    public ResponseEntity<Object> handleBadRequest(DepartmentNotAddedException ex) {
        log.error("DepartmentNotAddedException");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "DepartmentNotAddedException");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);


    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    public void handleGeneralError(Exception ex) {
        log.error("An error occurred processing request" + ex);
    }

}
