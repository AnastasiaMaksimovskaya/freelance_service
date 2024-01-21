package com.free.freelance_service.exeption;

import com.free.freelance_service.dto.Message;
import com.free.freelance_service.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandlerResolver {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> handleException(Exception ex) {
        Message message = new Message();
        message.setStatus("error");
        message.setMessage(ExceptionEnum.globalException.toString());
        message.setCode(500);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
