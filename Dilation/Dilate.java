import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.util.Arrays;

public class Dilate {
  private static final int ALPHA = 0;
  private static final int RED = 1;
  private static final int GREEN = 2;
  private static final int BLUE = 3;

  public static void main(String args[]) {
    if(args.length < 1)
    {
      System.err.println("Usage: java dilate <image path> [number of iterations]");
      System.exit(1);
    }

    int max = 1;
    if(args.length != 2)
    {
      System.out.println("No number specified: dilation will be performed 1 time");
    } else {
      try {
        max = Integer.parseInt(args[1]);
      } catch (NumberFormatException nfe) {
        System.err.println("The number specified could not be parsed, continuing with iterations=1");
        max = 1;
      }
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
    int i, j, colour, iter;
    Color rgb_colour;

    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

    // Conversion of the image in a matrix
    for (i = 0; i < width; i++) {
      for (j = 0; j < height; j++) {
        //Preparation of the destination matrix
        dst[i][j] = 0;
        // The BufferedImage is transformed into a binary matrix
        colour = img.getRGB(i, j);
        //pixel[ALPHA] = (colour & 0xff000000) >>> 24;
        pixel[RED] = (colour & 0xff0000) >>> 16;
        pixel[GREEN] = (colour & 0xff00) >>> 8;
        pixel[BLUE] = colour & 0xff;
        src[i][j] = ((int) (0.299*pixel[RED] + 0.587*pixel[GREEN] + 0.114*pixel[BLUE]) > 128) ? 255 : 0;
        if(src[i][j] != 255 && src[i][j] != 0)
        System.out.print(src[i][j]+" ");
      }
      //System.out.println();
    }

    for(iter = 0; iter < max; iter++) {
      for (i = 0; i < width; i++) {
        for (j = 0; j < height; j++) {
          // Performing dilation 8-connectivity
          try {
            if(src[i][j] == 255) {
              dst[i-1][j-1] = 255;
              dst[i-1][j] = 255;
              dst[i-1][j+1] = 255;
              dst[i][j-1] = 255;
              dst[i][j] = 255;
              dst[i][j+1] = 255;
              dst[i+1][j-1] = 255;
              dst[i+1][j] = 255;
              dst[i+1][j+1] = 255;
            }
          } catch (IndexOutOfBoundsException ex) {
            //There is no need to take action, we just reached a border
          }
        }
      }
      src = Arrays.stream(dst).map(int[]::clone).toArray(int[][]::new);
    }

    // Copying values into a BufferedImage to save it on a file.
    for (i = 0; i < width; i++) {
      for (j = 0; j < height; j++) {
        if(dst[i][j] == 255)
          rgb_colour = Color.WHITE;
          else
          rgb_colour = Color.BLACK;
        result.setRGB(i,j,rgb_colour.getRGB());
      }
    }

    // Printing the result in a file
    try {
      File output = new File("Result.jpg");
      ImageIO.write(result, "jpg", output);
    } catch (IOException exe) {
      System.err.println("Writing result on file failed");
      System.exit(2);
    }
    System.out.println("Dilation completed");
  }
}
