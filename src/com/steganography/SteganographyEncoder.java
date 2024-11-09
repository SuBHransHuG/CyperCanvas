package com.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SteganographyEncoder {
    public static void encodeMessage(String imagePath, String outputPath, String message) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        int messageLength = message.length();
        int messageIndex = 0;
        int charBitIndex = 0;

        outerLoop:
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);

                // Encode each character using 8 bits
                if (messageIndex < messageLength) {
                    char currentChar = message.charAt(messageIndex);
                    int bit = (currentChar >> (7 - charBitIndex)) & 1; // Get each bit of the character

                    // Modify the pixel’s blue component to store the bit
                    int blue = (pixel & 0xFE) | bit; // Set LSB of blue component
                    pixel = (pixel & 0xFFFF00) | blue;

                    // Update the image with the modified pixel
                    image.setRGB(x, y, pixel);

                    charBitIndex++;
                    if (charBitIndex == 8) {
                        charBitIndex = 0;
                        messageIndex++;
                    }
                } else {
                    break outerLoop;
                }
            }
        }

        File outputFile = new File(outputPath);
        ImageIO.write(image, "png", outputFile);
        System.out.println("Message encoded and saved as: " + outputFile.getAbsolutePath());
    }
}
