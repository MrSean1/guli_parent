package com.atguigu.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.servicebase.exceptionhandler.GuliExecption;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.excel.SubjectData;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-10-11 00:08
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // 因为SubjectExcelListener不能交给spring进行管理， 需要自己去new，不能注入其他对象
    // 不能实现数据库操作

    public EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectExcelListener() {
    }

    // 读取excel中的内容， 一行一行进行读取的
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if (subjectData == null){
            throw new GuliExecption(20001, "文件数据为空");
        }
        // 一行一行读取的数据， 每次读取有两个值，第一个值为一级分类，第二个值为二级分类


        EduSubject existsOneEduSubject = this.exisOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existsOneEduSubject == null){
            // 表示表中没有相同的一级分类， 需要将此一级分类添加进表中
            existsOneEduSubject = new EduSubject();
            existsOneEduSubject.setParentId("0");
            existsOneEduSubject.setTitle(subjectData.getOneSubjectName()); // 以及分类的名称
            subjectService.save(existsOneEduSubject);

        }

        String pid = existsOneEduSubject.getId();

        EduSubject existsTwoEduSubject = this.exisTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existsTwoEduSubject == null){
            // 表示表中没有相同的二级分类， 需要将此一级分类添加进表中
            existsTwoEduSubject = new EduSubject();
            existsTwoEduSubject.setParentId(pid);
            existsTwoEduSubject.setTitle(subjectData.getTwoSubjectName()); // 以及分类的名称
            subjectService.save(existsTwoEduSubject);

        }

    }

    // 判断一级分类不能重复添加
    public EduSubject exisOneSubject(EduSubjectService subjectService, String name){
        QueryWrapper<EduSubject> wapper = new QueryWrapper<>();

        wapper.eq("title", name);
        wapper.eq("parent_id", "0");

        EduSubject subjectServiceOne = subjectService.getOne(wapper);
        return subjectServiceOne;

    }


    // 判断二级分类不能重复添加
    public EduSubject exisTwoSubject(EduSubjectService subjectService, String name, String pid){
        QueryWrapper<EduSubject> wapper = new QueryWrapper<>();

        wapper.eq("title", name);
        wapper.eq("parent_id", pid);

        EduSubject subjectServiceTwo = subjectService.getOne(wapper);
        return subjectServiceTwo;

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
