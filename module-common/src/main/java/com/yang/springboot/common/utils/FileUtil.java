package com.yang.springboot.common.utils;

import sun.jvm.hotspot.utilities.BitMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Yang Hao
 * @date 2021-08-27 15:35
 */
public class FileUtil {
    public static BitMap readFile(String imagePath) {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream baos = null;
        byte[] currentData = new byte[240 * 320];
        try {
            fileInputStream = new FileInputStream(imagePath);
            baos = new ByteArrayOutputStream();
            byte[] by = new byte[1024];
            int line = -1;
            while ((line = fileInputStream.read(by)) != -1) {
                baos.write(by, 0, line);
            }
            byte[] buffer = baos.toByteArray();
            int index = -1;
            for (int i = 0; i < 320; i++) {
                for (int j = 0; j < 240; j++) {
                    currentData[++index] = buffer[i * 240 + j];
                }
            }
            try {
                ByteToFile(buffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        readFile("/Users/neo/Desktop/20210712_161715835_1.bin");
    }

    static void ByteToFile(byte[] bytes) throws Exception {
        int width = 240;
        int height = 320;

        int samplesPerPixel = 1;
        int[] bandOffsets = {0}; // BGRA order

        byte[] bgraPixelData = new byte[width * height * samplesPerPixel];

        DataBuffer buffer = new DataBufferByte(bytes, bytes.length);
        WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, samplesPerPixel * width, samplesPerPixel, bandOffsets, null);

        ColorModel colorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), false, false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);

        BufferedImage image = new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);

        System.out.println("image: " + image); // Should print: image: BufferedImage@<hash>: type = 0 ...

        ImageIO.write(image, "PNG", new File("/Users/neo/Desktop/hhaha.png"));
    }
}
