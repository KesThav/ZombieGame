package AbstractClass;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Weapons {

  protected int damage = 1;
  public int posX;
  public int posY;
  public int targetPosX; // mouse position X;
  public int targetPosY; // mouse position Y;
  public BufferedImage image;
  public int x;
  public int y;
  public int w;
  public int h;

  public Weapons(int damage, int posX, int posY, int targetPosX, int targetPosY) {
    this.damage = damage;
    this.posX = posX;
    this.posY = posY;
    this.targetPosX = targetPosX;
    this.targetPosY = targetPosY;
    try {
      this.image = ImageIO.read(Characters.class.getResource("../img/bombe.png"));
      this.w = image.getWidth();
      this.h = image.getHeight();
    } catch (IOException ex) {
      System.out.print("no img !");
    }

  }
}
