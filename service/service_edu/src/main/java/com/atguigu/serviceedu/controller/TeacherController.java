package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.Teacher;
import com.atguigu.serviceedu.entity.TeacherQuery;
import com.atguigu.serviceedu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-07-19
 */
@Api("讲师模块")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class TeacherController {
    // 把service 注入
    @Autowired
    public TeacherService teacherService;

    // 1. 查询讲师表中的所有数据。
    @ApiOperation("所有讲师列表")
    @GetMapping("/findAll")
    public R findAllTeacher() {
        List<Teacher> teacherList = teacherService.list(null);
//        return teacherList;
        return R.ok().data("items", teacherList);
    }

    // 2. 删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        }
        return R.error();
    }

    // 3。 分页查询

    /**
     * @param current 当前页
     * @param limit   每页记录数
     * @return 分页数据
     */
    @ApiOperation("分页查询讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current, @PathVariable long limit) {

        // 创建page对象
        Page<Teacher> pageTeacher = new Page<>(current, limit);

        // 调用方法实现分页
        // 调用方法的时候， 底层封装，把分页所有数据封装到pageTeacher对象中
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal(); // 总记录数

        List<Teacher> records = pageTeacher.getRecords(); // 数据list集合

//        return R.ok().data("total", total).data("rows", records);
        HashMap hashMap = new HashMap();

        hashMap.put("total", total);
        hashMap.put("rows", records);

        return R.ok().data(hashMap);
    }

    // 4. 条件查询带分页方法

    /**
     * @param current      当前页
     * @param limit        每页的记录数
     * @param teacherQuery 分页条件
     * @return 数据明细及数据总量
     */
    @ApiOperation("条件分页查询讲师列表")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        // 创建一个page对象
        Page<Teacher> pageTeacher = new Page<>(current, limit);

        // 构造条件
        QueryWrapper<Teacher> wapper = new QueryWrapper<>();
        // 多条件组合查询  动态sql
        // 判断条件值是否为空，不为空添加条件
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        Integer level = teacherQuery.getLevel();
        String name = teacherQuery.getName();

        if (!StringUtils.isEmpty(name)) {
            wapper.like("name", name);
        }
        if (!StringUtils.isEmpty(end)) {
            wapper.le("gmt_create", end);
        }
        if (!StringUtils.isEmpty(level)) {
            wapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wapper.ge("gmt_create", begin);
        }

        // 排序，对展示的数据进行排序
        wapper.orderByDesc("gmt_create");

        // 调用方法实现条件查询分页
        teacherService.page(pageTeacher, wapper);

        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();

        return R.ok().data("total", total).data("items", records);
    }

    // 5。 添加讲师的接口
    @ApiOperation("讲师添加接口")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Teacher teacher) {
//        try {
//            int a = 10/0;
//        }catch (Exception e){
//            throw new GuliExecption(501, "程序bug");
//        }


        boolean save = teacherService.save(teacher);
        return save ? R.ok() : R.error();
    }

    // 6. 根据讲师id查询讲师信息
    @ApiOperation("根据讲师id查询讲师信息")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if (teacher != null){
            return R.ok().data("items", teacher);
        }else {
            return R.error().message("数据不存在");
        }
    }

    // 7. 修改讲师信息
    @ApiOperation("修改讲师接口")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean isUpdateSuccess = teacherService.updateById(teacher);

        return isUpdateSuccess? R.ok():R.error().message("数据不存在，更新失败");

    }
}

