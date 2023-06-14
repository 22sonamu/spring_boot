package com.in28minutes.rest.webservices.restfulwebservices.exception;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

//클래스를 모든 컨트롤러에 적용한다.
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    //ResponseEntityExceptionHandler class 의 handleException 함수가 Spring MVC 의 모든 예외 처리를 담당한다.
    // handleException을 Override하여 excetion을 커스텀 하려 한다.

    @ExceptionHandler(Exception.class) //모든 Exception을 대상으로 한다.
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class) //UserNotFoundException을 대상으로 한다.
    public final ResponseEntity<ErrorDetails> handleUserNotFondException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Nullable
    @Override //Valid 에러 걸린 경우
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Total Errors : " + ex.getErrorCount() + " / message :" + ex.getFieldError().getDefaultMessage(), request.getDescription(false));
        //ex.getFieldError().getDefaultMessage() -> user 객체에서 설정한 error message가 나온다.
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
