package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-10-10 22:22
 */

@Data  // 此注解是为了生成get和set方法
public class DemoData {

    @ExcelProperty(value = "学生编号", index = 0)  // 设置excel的表头
    private Integer sno;

    @ExcelProperty(value = "学生姓名", index = 1)  // 设置excel的表头
    private String sname;
}
