package com.springboot.demo.util;

/**
 * @author yanghao
 * @date 2019-04-29 11:17
 */
public class HexStringUtils {

    private static final char[] DIGITS_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    protected static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_HEX[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_HEX[0x0F & data[i]];
        }
        return out;
    }

    protected static byte[] decodeHex(char[] data) {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("字符个数应该为偶数");
        }
        byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f |= toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public static String toHexString(byte[] bs) {
        return new String(encodeHex(bs));
    }

    public static String hexString2Bytes(String hex) {
        return new String(decodeHex(hex.toCharArray()));
    }

    public static byte[] chars2Bytes(char[] bs) {
        return decodeHex(bs);
    }

    public static void main(String[] args) {
        String s = "02 00 00 70 00 00 72 02 08 93 00 4c 00 02 01 00 20 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 18 10 15 17 24 44 e2 12 04 60 00 91 82 5b 30 34 91 82 5b 32 2d 91 82 30 63 2c f1 04 00 e4 01 9e f2 12 fd fc f6 ff 77 02 bb fd 78 00 00 00 00 09 00 50 02 52 f3 02 08 00 12 0b 01 89 86 02 b9 19 17 50 76 18 92 e8 08 11 01 00 03 00 02 00 06 e9 09 02 00 00 00 78 00 00 02 58 9b";
        String hex = toHexString(s.getBytes());
        String decode = hexString2Bytes(hex);
        System.out.println("原字符串:" + s);
        System.out.println("十六进制字符串:" + hex);
        System.out.println("还原:" + decode);
    }
}
