package com.adeesha.typeBDigital.exception;

import com.adeesha.typeBDigital.response.ErrorResponse;
import com.adeesha.typeBDigital.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<MessageResponse> handleResponseStatusException(ResponseStatusException e){
        return ResponseEntity
                .status(e.getStatusCode())
                .body(new MessageResponse(e.getMessage()));
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e) {

        // Get the name of the parameter that failed
        String paramName = e.getName();

        // Get the required type
        String requiredType = null;
        if (e.getRequiredType() != null){
            requiredType = e.getRequiredType().getSimpleName();
        }

        String message = String.format("Parameter '%s' has an invalid format. It should be a '%s'.",
                paramName, requiredType);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(message));
    }
}
