import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import javax.imageio.*;

public class Dilate {
  private static final int ALPHA = 0;
  private static final int RED = 1;
  private static final int GREEN = 2;
  private static final int BLUE = 3;

  public static void main(String args[]) {
    if(args[0] == null)
    {
      System.err.println("Usage: java dilate <image path>");
      System.exit(1);
    }
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(args[0]));
    } catch (IOException e){
      System.err.println("The path to the file must be wrong: reading failed");
      System.exit(2);
    }

    int width = img.getWidth();
    int height = img.getHeight();
    int[][] src = new int[width][height];
    int[][] dst = new int[width][height];
    int[] pixel = new int[4];
    int lastrow = height-1;
    int lastcol = width-1;
    int i, j, colour;

    BufferedImage result = new BufferedImage(width, height,
        BufferedImage.TYPE_BYTE_GRAY);

    for (i = 0; i < width; i++) {
      for (j = 0; j < height; j++) {
        // The BufferedImage is transformed into a binary matrix
        colour = img.getRGB(i, j);
        //pixel[ALPHA] = (colour & 0xff000000) >>> 24;
        pixel[RED] = (colour & 0xff0000) >>> 16;
        pixel[GREEN] = (colour & 0xff00) >>> 8;
        pixel[BLUE] = colour & 0xff;
        src[i][j] = ((int) (0.299*pixel[RED] + 0.587*pixel[GREEN] + 0.114*pixel[BLUE]) > 128) ? 255 : 0;
        System.out.print(src[i][j]+" ");
      }
      System.out.println();
    }

    for (i = 0; i < width; i++) {
      for (j = 0; j < height; j++) {
        dst[i][j] = 0;
        // Performing dilation
        try {
          if(src[i][j] == 255) {
            if(i != 0) {
              if(j != 0)
              dst[i-1][j-1] = 255;
              dst[i-1][j] = 255;
              if(j != lastcol)
              dst[i-1][j+1] = 255;
            }
            if(j != 0)
            dst[i][j-1] = 255;
            dst[i][j] = 255;
            if(j != lastcol)
            dst[i][j+1] = 255;

            if(i != lastrow) {
              if(j != 0)
              dst[i+1][j-1] = 255;
              dst[i+1][j] = 255;
              if(j != lastcol)
              dst[i+1][j+1] = 255;
            }
          }
        } catch (IndexOutOfBoundsException ex) {
          //There is no need to take action, we just reached a border
        }
      }
    }

    for (i = 0; i < width; i++) {
      for (j = 0; j < height; j++) {
        colour = dst[i][j];
        Color rgb_colour = new Color(colour, colour, colour);
        result.setRGB(i,j,rgb_colour.getRGB());
      }
    }
    try {
      File output = new File("Result.jpg");
      ImageIO.write(result, "jpg", output);
    } catch (IOException exe) {
      System.err.println("Writing result on file failed");
      System.exit(2);
    }
  }
}
