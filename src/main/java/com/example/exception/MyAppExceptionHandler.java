package com.example.exception;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class MyAppExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex, Model model) {
        // 404エラーを処理
        log.error("NotFoundExceptionが発生しました。", ex);
        model.addAttribute("errorCode", HttpStatus.NOT_FOUND.value());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        // 400エラーを処理
        log.error("IllegalArgumentExceptionが発生しました。", ex);
        model.addAttribute("errorCode", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleMyException(MyException ex, Model model) {
        // 404エラーを処理
        log.error("MyExceptionが発生しました。", ex);
        model.addAttribute("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(Exception ex, Model model) {
        // 500エラーを処理
        log.error("NExceptionが発生しました。", ex);
        model.addAttribute("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }


}
