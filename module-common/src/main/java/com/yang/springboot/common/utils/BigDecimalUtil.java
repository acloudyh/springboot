package com.yang.springboot.common.utils;

import java.math.BigDecimal;

/**
 * 提供加减乘除以及四舍五入
 *
 * @author yanghao
 * @date 2019-05-06 11:38
 */
public class BigDecimalUtil {

    public BigDecimalUtil() {
    }

    /**
     * 保留两位小数转化
     *
     * @param d     需要转换的数字
     * @param scale 精确度
     * @return
     */
    public static double transform(double d, int scale) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 加法运算（例：1+2，1是被加数，2是加数）
     *
     * @param v1 被加数
     * @param v2 加数
     * @return
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal add = b1.add(b2);
        return add.doubleValue();
    }

    /**
     * 减法运算（例：1-2,1是被减数，2是减数）
     *
     * @param v1 被减数
     * @param v2 减数
     * @return
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal subtract = b1.subtract(b2);
        return subtract.doubleValue();
    }

    /**
     * 乘法运算（例：1*2,1是被乘数，2是乘数）
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal multiply = b1.multiply(b2);
        return multiply.doubleValue();
    }

    /**
     * 除法运算 四舍五入（例：1÷2，1是被除数，2是除数）
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精确度
     * @return
     */
    public static double div(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal divide = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
        return divide.doubleValue();
    }

    /**
     * 加法运算 四舍五入（例：1+2，1是被加数，2是加数）
     *
     * @param v1    被加数
     * @param v2    加数
     * @param scale 精确度
     * @return
     */
    public static double add(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal add = b1.add(b2);
        return add.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 减法运算 四舍五入（例：1-2,1是被减数，2是减数）
     *
     * @param v1    被减数
     * @param v2    减数
     * @param scale 精确度
     * @return
     */
    public static double sub(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal subtract = b1.subtract(b2);
        return subtract.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 乘法运算 四舍五入（例：1*2,1是被乘数，2是乘数）
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 精确度
     * @return
     */
    public static double mul(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal multiply = b1.multiply(b2);
        return multiply.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
