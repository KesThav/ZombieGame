package Character;

import AbstractClass.Characters;

public class MainCharacter extends Characters {

  int SCREEN_HEIGHT = 900;
  int SCREEN_WIDTH = 900;
  public int press = 0;

  public MainCharacter(int health, int positionX, int positionY) {
    super(health, positionX, positionY);

  }

  @Override
  public void move(char direction) {
    switch (direction) {
      case 'U':
        if (positionY - 30 > 0) {
          press++;
          positionY -= 30;
          break;
        }
      case 'D':
        if (positionY + 30 < SCREEN_HEIGHT) {
          press++;
          positionY += 30;
          break;
        }
      case 'L':
        if (positionX - 30 > 0) {
          press++;
          positionX -= 30;
          break;
        }
      case 'R':
        if (positionX + 30 < SCREEN_WIDTH) {
          press++;
          positionX += 30;
          break;
        }
    }

  }

}
