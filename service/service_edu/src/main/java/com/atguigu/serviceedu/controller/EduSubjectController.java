package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-10-10
 */
@RestController
@RequestMapping("/serviceedu/subject")
@CrossOrigin
@Api(tags = "课程科目管理")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    // 添加课程分类
    // 获取上传文件，把文件内容读取出来

    @ApiOperation("上传课程分类")
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){

        // 上传过来的文件
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }
}

