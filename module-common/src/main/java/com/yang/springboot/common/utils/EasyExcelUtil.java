package com.yang.springboot.common.utils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
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

            out = response.getOutputStream();
            excelWriter = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            String fileName = new String((new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(), "UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            Sheet sheet = new Sheet(1, 0, clazz);
            sheet.setSheetName("sheet名字");
            excelWriter.write(list, sheet);
            out.flush();
            excelWriter.finish();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
