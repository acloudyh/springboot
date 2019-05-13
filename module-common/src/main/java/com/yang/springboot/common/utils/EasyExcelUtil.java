package com.yang.springboot.common.utils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.*;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yanghao
 * @date 2019-05-13 14:29
 */
@Slf4j
public class EasyExcelUtil {


    /**
     * 导出文件带有注解的model
     *
     * @param list
     * @param response
     * @param clazz
     */
    public static void export(List<? extends BaseRowModel> list, HttpServletResponse response, Class<? extends BaseRowModel> clazz) {
        ServletOutputStream out = null;
        ExcelWriter excelWriter = null;
        try {
            out = getServletOutputStream(response);
            excelWriter = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);

            Sheet sheet = new Sheet(1, 0, clazz);
            sheet.setSheetName("sheet名字");
            sheet.setAutoWidth(Boolean.TRUE);
            excelWriter.write(list, sheet);
            out.flush();
            excelWriter.finish();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出文件表头自动生成，无model 注解
     *
     * @param list
     * @param response
     * @param clazz
     */
    public static void exportNoModel(List<? extends BaseRowModel> list, HttpServletResponse response, Class<? extends BaseRowModel> clazz) {
        ServletOutputStream out = null;
        ExcelWriter excelWriter = null;
        try {

            out = getServletOutputStream(response);
            excelWriter = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);

            List<List<String>> head = new ArrayList<>();
            List<String> headCoulumn1 = new ArrayList<>();
            List<String> headCoulumn2 = new ArrayList<>();
            List<String> headCoulumn3 = new ArrayList<>();
            List<String> headCoulumn4 = new ArrayList<>();
            headCoulumn1.add("运单号");
            headCoulumn2.add("创建时间");
            headCoulumn3.add("承运人姓名");
            headCoulumn4.add("承运人邮箱");
            head.add(headCoulumn1);
            head.add(headCoulumn2);
            head.add(headCoulumn3);
            head.add(headCoulumn4);

            Table table = new Table(1);
            table.setTableStyle(createTableStytle());
            table.setHead(head);
            Sheet sheet = new Sheet(1, 0);
            excelWriter.write(list, sheet, table);
            out.flush();
            excelWriter.finish();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义表头以及内容样式
     *
     * @return
     */
    public static TableStyle createTableStytle() {
        TableStyle tableStyle = new TableStyle();
        //设置表头样式
        Font headFond = new Font();
        //字体是都加粗
        headFond.setBold(true);
        //字体大小
        headFond.setFontHeightInPoints((short) 12);
        //字体
        headFond.setFontName("宋体");
        tableStyle.setTableHeadFont(headFond);

        //表头背景色
        tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);

        //设置表格主体样式
        Font contentFond = new Font();
        contentFond.setBold(true);
        contentFond.setFontName("楷体");
        contentFond.setFontHeightInPoints((short) 12);
        tableStyle.setTableContentFont(contentFond);
        tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);

        return tableStyle;
    }


    private static ServletOutputStream getServletOutputStream(HttpServletResponse response) throws IOException {
        ServletOutputStream out;
        out = response.getOutputStream();
        String fileName = new String((new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())).getBytes(), "UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "UTF-8"));
        return out;
    }
}
