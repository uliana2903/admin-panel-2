package com.example.demo.exceptions;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>(); //тут хранятся ошибки по ключу название и значению сообщение об ошибке
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult(); //получаем отчет об ошибках валидации, какие поля не рпошли проверку и тд
        List<FieldError> errorList = bindingResult.getFieldErrors(); //из отчета об ошибках берем информацию о конкретном поле и кладем в список

        for (FieldError fieldError : errorList) {
            String fieldName = fieldError.getField(); //ключ название ошибки
            String message = fieldError.getDefaultMessage(); //значение сообщение об ошибке
            errors.put(fieldName, message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<Response> handlePlayerNotFound(PlayerNotFoundException playerNotFoundException) {
        return getResponse(playerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Response> getResponse(String message, HttpStatus httpStatus) {
       Response response = new Response();
       response.setMessage(message);
       return new ResponseEntity<>(response, httpStatus);
    }
}
