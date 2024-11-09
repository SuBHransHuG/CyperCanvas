package com.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SteganographyDecoder {
    public static String decodeMessage(String imagePath, int messageLength) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        StringBuilder message = new StringBuilder();
        int charBitIndex = 0;
        char currentChar = 0;

        outerLoop:
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int blue = pixel & 1; // Extract the least significant bit of the blue channel

                // Assemble the character bit by bit
                currentChar = (char) ((currentChar << 1) | blue);
                charBitIndex++;

                if (charBitIndex == 8) {
                    message.append(currentChar);
                    charBitIndex = 0;
                    currentChar = 0;

                    if (message.length() == messageLength) {
                        break outerLoop;
                    }
                }
            }
        }

        return message.toString();
    }
}
