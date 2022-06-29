package com.liyi.reggie.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */

@Slf4j
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobleExceptionHandler {


    @ExceptionHandler
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException e){

        if(e.getMessage().contains("Duplicate entry")){
            String[] split = e.getMessage().split(" ");
            String userName = split[2];

            return Result.error("用户名" + userName + "已存在");
        }
        return Result.error("未知错误");
    }

}
