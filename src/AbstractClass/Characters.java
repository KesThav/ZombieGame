package AbstractClass;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.shape.Rectangle;

public class Characters {

  public int health;
  public int positionX;
  public int positionY;
  public BufferedImage image, down_1, down_2, up_1, up_2, left_1, left_2, right_1, right_2;
  public char direction;
  public int x;
  public int y;
  public int w;
  public int h;

  protected Characters(int health, int positionX, int positionY) {
    this.health = health;
    this.positionX = positionX;
    this.positionY = positionY;
    try {
      this.down_1 = ImageIO.read(Characters.class.getResource("../img/main/down_1.png"));
      this.image = this.down_1;
      this.down_2 = ImageIO.read(Characters.class.getResource("../img/main/down_2.png"));
      this.up_1 = ImageIO.read(Characters.class.getResource("../img/main/up_1.png"));
      this.up_2 = ImageIO.read(Characters.class.getResource("../img/main/up_2.png"));
      this.left_1 = ImageIO.read(Characters.class.getResource("../img/main/left_1.png"));
      this.left_2 = ImageIO.read(Characters.class.getResource("../img/main/left_2.png"));
      this.right_1 = ImageIO.read(Characters.class.getResource("../img/main/right_1.png"));
      this.right_2 = ImageIO.read(Characters.class.getResource("../img/main/right_2.png"));
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
