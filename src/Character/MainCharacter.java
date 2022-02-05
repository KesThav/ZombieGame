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
        if (positionY - 30 > 0) {
          positionY -= 30;
          break;
        }
      case 'D':
        if (positionY + 30 < SCREEN_HEIGHT) {
          positionY += 30;
          break;
        }
      case 'L':
        if (positionX - 30 > 0) {
          positionX -= 30;
          break;
        }
      case 'R':
        if (positionX + 30 < SCREEN_WIDTH) {
          positionX += 30;
          break;
        }
    }

  }

}
