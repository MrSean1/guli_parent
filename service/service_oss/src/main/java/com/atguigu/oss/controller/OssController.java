package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-08-16 01:05
 */

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin // 解决跨域问题
public class OssController {

    @Autowired
    private OssService ossService;

    // 上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        // 获取上传文件 MultipartFile

        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }
}
