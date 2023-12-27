package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-10-10 23:26
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {


    // 逐行读取数据
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("*******" + demoData);

    }

    // 读取完成后可以做的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context){
        System.out.println("表头" + headMap);
    }
}
