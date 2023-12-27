package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-08-03 01:01
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    // 指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class) // 所有异常均捕获
    @ResponseBody  // 为了返回数据
    public R globalError(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    // 特定异常处理
    @ExceptionHandler(ArithmeticException.class) // 所有异常均捕获
    @ResponseBody  // 为了返回数据
    public R globalError(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException自己写的异常处理");
    }

    // 自定义异常处理
    @ExceptionHandler(GuliExecption.class) // 所有异常均捕获
    @ResponseBody  // 为了返回数据
    public R globalError(GuliExecption e) {
        log.error(e.getMessage()); // 将报错信息写入到日志文件中

        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
