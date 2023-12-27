package com.atguigu.serviceedu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-08-02 23:13
 */

@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2熟悉讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin; // 这里使用字符串数据接收时间数据，前端传递过来的数据无需进行格式转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-01-01 10:10:10")
    private String end;


}
