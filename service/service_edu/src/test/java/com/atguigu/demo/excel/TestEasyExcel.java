package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-10-10 22:28
 */
public class TestEasyExcel {

    @Test
    public  void test1() {
        // 实现excel写的操作，
        // 设置写入的excel的地址和名称

        String filename = "/Users/sean/Downloads/test_demo.xlsx";


        // 调用easyExcel的写入方法将数据写入到excel中
        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());

    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i+1);
            data.setSname("学生" + i);
            list.add(data);
        }
        return list;
    }

    @Test
    public void test2(){
        // 读取excel的数据

        String filename = "/Users/sean/Downloads/test_demo.xlsx";

        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }
}
