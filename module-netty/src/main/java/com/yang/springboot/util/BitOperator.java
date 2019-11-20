package com.yang.springboot.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yang Hao
 * @date 2019/11/20 17:04
 */
public class BitOperator {

    /**
     * 合并字节数组
     *
     * @param first
     * @param rest
     * @return
     */
    public byte[] concatAll(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            if (array != null) {
                totalLength += array.length;
            }
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            if (array != null) {
                System.arraycopy(array, 0, result, offset, array.length);
                offset += array.length;
            }
        }
        return result;
    }

    /**
     * 合并字节数组
     *
     * @param rest
     * @return
     */
    public byte[] concatAll(List<byte[]> rest) {
        int totalLength = 0;
        for (byte[] array : rest) {
            if (array != null) {
                totalLength += array.length;
            }
        }
        byte[] result = new byte[totalLength];
        int offset = 0;
        for (byte[] array : rest) {
            if (array != null) {
                System.arraycopy(array, 0, result, offset, array.length);
                offset += array.length;
            }
        }
        return result;
    }
}
