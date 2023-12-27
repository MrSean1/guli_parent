package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-08-03 23:13
 */
@Data
@AllArgsConstructor // 全参数构造器
@NoArgsConstructor // 无参数构造器
public class GuliExecption extends RuntimeException{

    private Integer code; // 异常状态码

    private String msg; // 异常信息
}
