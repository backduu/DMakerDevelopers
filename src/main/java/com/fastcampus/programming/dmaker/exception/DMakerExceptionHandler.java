package com.fastcampus.programming.dmaker.exception;

import com.fastcampus.programming.dmaker.dto.DMakerErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.fastcampus.programming.dmaker.exception.DMakerErrorCode.INTERNAL_SERVER_ERROR;
import static com.fastcampus.programming.dmaker.exception.DMakerErrorCode.INVALID_REQUEST;

@RestControllerAdvice
@Slf4j
public class DMakerExceptionHandler {
    @ResponseStatus(value= HttpStatus.CONFLICT)
    @ExceptionHandler(DMakerException.class)
    public DMakerErrorResponse handleException(
            DMakerException exception
            , HttpServletRequest request
    ) {
        log.error("[DMakerResponse] error: {}, url: {}, message: {}"
                , exception.getDMakerErrorCode(), request.getRequestURI(), exception.getDetailMessage()
        );

        return DMakerErrorResponse.builder()
                .errorCode(exception.getDMakerErrorCode())
                .errorMessage(exception.getDetailMessage())
                .build();
    }

    // POST로 "~~/create-developer" 를 보내지 않을 때
    // HttpRequestMethodNotSupportedException가 생기는데 그때의 예외처리.
//    @ExceptionHandler(value = {
//            HttpRequestMethodNotSupportedException.class,
//            MethodArgumentNotValidException.class
//    })
    // Exception의 종류가 너무 많으니 그냥 이렇게 하위 처럼 해도 됨.
    @ExceptionHandler(Exception.class)
    public DMakerErrorResponse handleBadRequest
    (
            Exception exception
            , HttpServletRequest request
    ) {
        log.error("[DMakerErrorResponse] url: {}, message: {}",
                request.getRequestURI(), exception.getMessage()
        );

//        return DMakerErrorResponse.builder()
//                .errorCode(INVALID_REQUEST)
//                .errorMessage(INVALID_REQUEST.getMessage())
//                .build();
        // @ExceptionHandler(Exception.class)로 바꾼다면 모르는 에러니까
        // INTERNAL_SERVER_ERROR가 낫다.
        return DMakerErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
