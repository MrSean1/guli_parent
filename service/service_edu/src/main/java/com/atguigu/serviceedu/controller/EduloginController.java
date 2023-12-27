package com.atguigu.serviceedu.controller;

import com.atguigu.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-08-13 20:26
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨域问题
public class EduloginController {

    // login
    @PostMapping("login")
    @ApiOperation("登陆校验")
    public R login(){
        return R.ok().data("token", "admin");
    }

    // info
    @GetMapping("info")
    @ApiOperation("返回角色信息")
    public R info(){
        return R.ok().data("roles","[admin]").data("name", "admin").data("avatar", "https://");
    }
}
