package com.steganography;

import java.io.IOException;
import java.lang.runtime.SwitchBootstraps;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String imagePath = "D:\\Projects\\ImageSteganography\\Pictures\\originalImage.png";
        String outputPath = "D:\\Projects\\ImageSteganography\\Pictures\\encodedImage1.png";
        String message = "This is Harsh";
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("What you want:");
            System.out.println("1.Encode\n2.Decode");
            System.out.println("Enter your Choice");
            int n = sc.nextInt();
            switch (n){
                case 1:
                    // Encode message
                    SteganographyEncoder.encodeMessage(imagePath, outputPath, message);
                    break;
                case 2:
                    // Decode message
                    String decodedMessage = SteganographyDecoder.decodeMessage(outputPath, message.length());
                    System.out.println("Decoded Message: " + decodedMessage);
                    break;
                default:
                    System.out.println("Invalid");
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
