import java.awt.image.BufferedImage;
import java.io;
import javax.imageio;

public class Dilate {
  public static void main(String args[]) {
    if(args[0] == null)
    {
      System.out.println("Usage: java dilate <image filename>");
      System.exit(1);
    }
    BufferedImage img=ImageIO.read(new File(args[0]));
    int width = img.getWidth(null);
    int height = img.getHeight(null);
    int[][] src = new int[width][height];
    for (int i = 0; i < width; i++)
      for (int j = 0; j < height; j++)
        src[i][j] = img.getRGB(i, j);

  }
}
