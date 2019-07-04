package com.springboot.demo.util;

/**
 * @author yanghao
 * @date 2019-07-04 15:14
 */
public class ByteUtil {


    private static String hexStr = "0123456789ABCDEF";
    private static String[] binaryArray =
            {
                    "0000", "0001", "0010", "0011",
                    "0100", "0101", "0110", "0111",
                    "1000", "1001", "1010", "1011",
                    "1100", "1101", "1110", "1111"
            };


    /**
     * 字节数组转换为二进制字符串
     *
     * @param bArray
     * @return
     */
    public static String bytes2BinaryStr(byte[] bArray) {

        String outStr = "";
        int pos = 0;
        for (byte b : bArray) {
            //高四位
            pos = (b & 0xF0) >> 4;
            outStr += binaryArray[pos];
            //低四位
            pos = b & 0x0F;
            outStr += binaryArray[pos];
        }
        return outStr;

    }




}
