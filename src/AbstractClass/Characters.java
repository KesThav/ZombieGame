package AbstractClass;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.shape.Rectangle;

public class Characters {

  public int health;
  public int positionX;
  public int positionY;
  public BufferedImage image, up1, up2, up3, up4;
  public char direction;
  public int x;
  public int y;
  public int w;
  public int h;

  private Rectangle CharacterBounds;

  protected Characters(int health, int positionX, int positionY) {
    this.health = health;
    this.positionX = positionX;
    this.positionY = positionY;
    try {
      this.image = ImageIO.read(Characters.class.getResource("../img/char.png"));
      this.w = image.getWidth();
      this.h = image.getHeight();
    } catch (IOException ex) {
      System.out.print("no img !");
    }
  }

  public void move(char direction) {

  }

  public void checkCollision() {

  }

  public void takeDamage() {

  }
}
