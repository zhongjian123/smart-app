package com.smart.smartapp;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootTest
public class ImageSimilarity {

    public static double calculateMSE(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        long mse = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);
                int r1 = (rgb1 >> 16) & 0xFF;
                int g1 = (rgb1 >> 8) & 0xFF;
                int b1 = (rgb1) & 0xFF;
                int r2 = (rgb2 >> 16) & 0xFF;
                int g2 = (rgb2 >> 8) & 0xFF;
                int b2 = (rgb2) & 0xFF;
                mse += (r1 - r2) * (r1 - r2) + (g1 - g2) * (g1 - g2) + (b1 - b2) * (b1 - b2);
            }
        }
        return (double) mse / (width * height);
    }

    @Test
    public void testImageSimilarity() throws IOException {
        BufferedImage img1 = ImageIO.read(new File("D:\\Users\\zhizj\\Desktop\\temp\\distance\\setting.jpg"));
        BufferedImage img2 = ImageIO.read(new File("D:\\Users\\zhizj\\Desktop\\temp\\distance\\20240323093045.jpg"));

        double mse = calculateMSE(img1, img2);
        System.out.println("MSE: " + mse);
    }

}
