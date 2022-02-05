package Character;

import java.io.IOException;

import javax.imageio.ImageIO;

import AbstractClass.Characters;

public class Zombie extends Characters {

  double speed = 5;

  public Zombie(int health, int positionX, int positionY) {
    super(health, positionX, positionY);
    this.health = 5;
    try {
      this.image = ImageIO.read(Characters.class.getResource("../img/samurai.png"));
      this.w = image.getWidth();
      this.h = image.getHeight();
    } catch (IOException ex) {
      System.out.print("no img !");
    }
  }

  @Override
  public void move(char direction) {

  }

  public void move2(int characterPositionX, int characterPositionY) {
    if (characterPositionX != positionX && characterPositionY != positionY) {
      float deltaX = characterPositionX - positionX;
      float deltaY = characterPositionY - positionY;
      float angle = (float) Math.atan2(deltaY, deltaX);

      positionX += (int) (speed * Math.cos(angle));
      positionY += (int) (speed * Math.sin(angle));
    }
  }

}
