import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class Dilate {
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
    int width = img.getWidth(null);
    int height = img.getHeight(null);
    int[][] src = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        src[i][j] = img.getRGB(i, j);
      }
    }
  }
}
