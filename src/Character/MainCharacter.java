package Character;

import java.io.IOException;

import javax.imageio.ImageIO;

import AbstractClass.Characters;

public class MainCharacter extends Characters {

  int SCREEN_HEIGHT = 900;
  int SCREEN_WIDTH = 900;

  public MainCharacter(int health, int positionX, int positionY) {
    super(health, positionX, positionY);

  }

  @Override
  public void move(char direction) {
    switch (direction) {
      case 'U':
        if (positionY - 10 > 0) {
          positionY -= 10;
          break;
        }
      case 'D':
        if (positionY + 10 < SCREEN_HEIGHT) {
          positionY += 10;
          break;
        }
      case 'L':
        if (positionX - 10 > 0) {
          positionX -= 10;
          break;
        }
      case 'R':
        if (positionX + 10 < SCREEN_WIDTH) {
          positionX += 10;
          break;
        }
    }

  }

}
