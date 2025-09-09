/*
   Kaleb Duchesneau 08/09/2025
   CS455 Computer Graphics
   Professor: Roger Kraft
*/

import framebuffer.FrameBuffer;

import java.awt.Color;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Outline of CS 45500 Assignment 1.
 */
public class Hw1 {
   public static void main(String[] args) {
      // Use a properties file to find out
      // which PPM files to use as assets.
      final Properties properties = new Properties();
      try {
         properties.load(
               new FileInputStream(
                     new File("assets.properties")));
      } catch (IOException e) {
         e.printStackTrace(System.err);
         System.exit(-1);
      }
      final String file_1 = properties.getProperty("file1"); // 1st ppm file
      final String file_2 = properties.getProperty("file2"); // 2nd ppm file

      // This framebuffer holds the image from the first ppm file.

      /******************************************/

      // Your code goes here.
      // 1. Create a 1100-by-700 framebuffer with the darker background color.
      final int fbHeight = 700;
      final int fbWidth = 1100;
      final Color backgroundColor = new Color(192, 56, 14);
      final FrameBuffer fb = new FrameBuffer(fbWidth, fbHeight, backgroundColor);

      // 2. Create a 1000-by-600 viewport filled with the lighter background color.
      final int borderSize = 50;
      final int vpHeight = 600;
      final int vpWidth = 1000;
      final Color vpBackgroundColor = new Color(255, 189, 96);
      final FrameBuffer.Viewport vp = fb.new Viewport(borderSize, borderSize, vpWidth, vpHeight, vpBackgroundColor);
      vp.clearVP();

      // 3. Draw the horizontal and vertical grid lines (each is 4 pixels wide).
      for (int y = 0; y < vpHeight; y += 100) {
         for (int x = 0; x < vpWidth; ++x) {
            vp.setPixelVP(x, y, backgroundColor); // top square border
            vp.setPixelVP(x, y + 1, backgroundColor);

            vp.setPixelVP(x, y + 98, backgroundColor); // bottom square border
            vp.setPixelVP(x, y + 99, backgroundColor);
         }
      }

      for (int x = 0; x < vpWidth; x += 100) {
         for (int y = 0; y < vpHeight; ++y) {
            vp.setPixelVP(x, y, backgroundColor); // left square border
            vp.setPixelVP(x + 1, y, backgroundColor);

            vp.setPixelVP(x + 98, y, backgroundColor); // right square border
            vp.setPixelVP(x + 99, y, backgroundColor);
         }
      }

      // 4. Put diagonal lines at the corners of the framebuffer (each is 3 pixel
      // wide).
      for (int i = 0; i < borderSize + 3; ++i) {
         final int height = fbHeight - i - 1;
         final int width = fbWidth - i - 1;

         fb.setPixelFB(i, i, vpBackgroundColor); // upper left
         fb.setPixelFB(i + 1, i, vpBackgroundColor);
         fb.setPixelFB(i, i + 1, vpBackgroundColor);

         fb.setPixelFB(width, height, vpBackgroundColor); // lower right
         fb.setPixelFB(width - 1, height, vpBackgroundColor);
         fb.setPixelFB(width, height - 1, vpBackgroundColor);

         fb.setPixelFB(i, height, vpBackgroundColor); // lower left
         fb.setPixelFB(i + 1, height, vpBackgroundColor);
         fb.setPixelFB(i, height - 1, vpBackgroundColor);

         fb.setPixelFB(width, i, vpBackgroundColor); // upper right
         fb.setPixelFB(width - 1, i, vpBackgroundColor);
         fb.setPixelFB(width, i + 1, vpBackgroundColor);
      }

      // 5. Create a viewport and draw into it the checkered pattern.
      final Color patternColors[] = { new Color(241, 91, 16), // red
            new Color(152, 203, 74), // green
            new Color(84, 129, 230), // blue
      };
      final int patternSize = 30;

      final int ulCheckerBoardX = 610;
      final int ulCheckerBoardY = 420;
      final int vp1Width = 270;
      final int vp1Height = 180;
      final FrameBuffer.Viewport vp1 = fb.new Viewport(ulCheckerBoardX, ulCheckerBoardY, vp1Width, vp1Height);
      vp1.clearVP();

      for (int x = 0; x < vp1Width; ++x) {
         for (int y = 0; y < vp1Height; ++y) {
            vp1.setPixelVP(x, y, patternColors[((x / patternSize) + (y / patternSize)) % patternColors.length]);
         }
      }

      // 6. Create a viewport and draw into it the striped disk pattern.
      final int ulDiskPatternX = 150;
      final int ulDIskPatternY = 350;
      final int vp2Width = 300;
      final int vp2Height = 300;
      final int diskCenterX = 150;
      final int diskCenterY = 150;
      final FrameBuffer.Viewport vp2 = fb.new Viewport(
            ulDiskPatternX,
            ulDIskPatternY,
            vp2Width,
            vp2Height);

      for (int x = 0; x < vp2Width; ++x) {
         for (int y = 0; y < vp2Height; ++y) {
            final int d = (int) (Math.sqrt(
                  Math.pow((x - diskCenterX), 2) +
                        Math.pow((y - diskCenterY), 2)));

            if (d >= 60 && d < 90) {
               vp2.setPixelVP(x, y, patternColors[1]);
            } else if (d >= 90 && d < 120) {
               vp2.setPixelVP(x, y, patternColors[0]);
            } else if (d >= 120 && d < 150) {
               vp2.setPixelVP(x, y, patternColors[2]);
            } else {
               continue;
            }
         }
      }

      // 7. Create a viewport and copy into it a flipped copy of the first ppm file.
      final FrameBuffer fb1 = new FrameBuffer(file_1);

      final int ulTroop1X = 75;
      final int ulTroop1Y = 125;
      final int troopLen = 256; // square image
      final FrameBuffer.Viewport vp3 = fb.new Viewport(ulTroop1X, ulTroop1Y, troopLen, troopLen); // upper-left-hand-corner,

      for (int x = troopLen - 1; x > 0; --x) {
         for (int y = troopLen - 1; y > 0; --y) {
            final Color c = fb1.getPixelFB(troopLen - x, troopLen - y);
            // 750 is to catch any color that is really close to rgb(255, 255, 255)
            if (c.getRed() + c.getGreen() + c.getBlue() > 750) {
               continue;
            }
            vp3.setPixelVP(troopLen - 1 - x, y, c);
         }
      }

      // 8. Create another viewport copy into it another flipped copy of the first ppm
      // file.
      final int ulTroop2X = ulTroop1X + troopLen;
      final int ulTroop2Y = ulTroop1Y;
      final FrameBuffer.Viewport vp4 = fb.new Viewport(ulTroop2X, ulTroop2Y, troopLen, troopLen);

      for (int x = 0; x < troopLen; ++x) {
         for (int y = 0; y < troopLen; ++y) {
            final Color c = fb1.getPixelFB(x, y);
            // 750 is to catch any color that is really close to rgb(255, 255, 255)
            if (c.getRed() + c.getGreen() + c.getBlue() > 750) {
               continue;
            }
            vp4.setPixelVP(troopLen - 1 - x, y, c);
         }
      }

      // 9. Create a viewport that covers the 6 grid squares that need to be copied.
      final int ulSixSqrsX = 250;
      final int ulSixSqrsY = 150;
      final int sixSqrsWidth = 200;
      final int sixSqrsHeight = 300;
      final FrameBuffer.Viewport vp5 = fb.new Viewport(ulSixSqrsX, ulSixSqrsY, sixSqrsWidth, sixSqrsHeight);

      // 10. Create another viewport to hold a "framed" copy of the previous viewport.
      // Give this viewport a grayish background color.
      final int ulSixSqrsBorderX = 775;
      final int ulSixSqrsBorderY = 50;
      final int sixSqrsBorderWidth = 250;
      final int sixSqrsBorderHeight = 350;
      final Color sixSqrsBorderColor = new Color(192, 192, 192);
      final FrameBuffer.Viewport vp6 = fb.new Viewport(ulSixSqrsBorderX,
            ulSixSqrsBorderY,
            sixSqrsBorderWidth,
            sixSqrsBorderHeight,
            sixSqrsBorderColor);
      vp6.clearVP();

      // 11. Create another viewport within the last, gray viewport, and initialize
      // it to hold a copy of the viewport from step 9.
      final int ulSixSqrsHomeX = 800;
      final int ulSixSqrsHomeY = 75;
      final FrameBuffer.Viewport vp7 = fb.new Viewport(ulSixSqrsHomeX, ulSixSqrsHomeY, vp5);

      // 12. Load Dumbledore (the second ppm file) into another FrameBuffer.
      final FrameBuffer fb2 = new FrameBuffer(file_2);

      // 13. Create a viewport to hold Dumbledore's ghost.
      final int ulDumbledoreX = 400;
      final int ulDumbledoreY = 100;
      final int dumbledoreSize = 500;
      final FrameBuffer.Viewport vp8 = fb.new Viewport(ulDumbledoreX, ulDumbledoreY, dumbledoreSize, dumbledoreSize);

      // 14. Blend Dumbledore from its framebuffer into the viewport from step 13.
      for (int x = 0; x < dumbledoreSize; ++x) {
         for (int y = 0; y < dumbledoreSize; ++y) {
            final Color c1 = fb2.getPixelFB(x, y);
            final Color c2 = vp8.getPixelVP(x, y);
            // 750 is to catch any color that is really close to rgb(255, 255, 255)
            if (c1.getRed() + c1.getGreen() + c1.getBlue() > 750) {
               continue;
            }

            final double newRed = 0.6 * c1.getRed() + 0.4 * c2.getRed();
            final double newGreen = 0.6 * c1.getGreen() + 0.4 * c2.getGreen();
            final double newBlue = 0.6 * c1.getBlue() + 0.4 * c2.getBlue();
            final Color newColor = new Color((int) newRed, (int) newGreen, (int) newBlue);
            vp8.setPixelVP(x, y, newColor);
         }
      }

      /******************************************/
      // Save the resulting image in a file.
      final String savedFileName = "Hw1.ppm";
      fb.dumpFB2File(savedFileName);
      System.err.println("Saved " + savedFileName);
   }
}
