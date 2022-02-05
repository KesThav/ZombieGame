package weapon;

import AbstractClass.Weapons;

public class Pistol extends Weapons {
  public Pistol(int damage, int posX, int posY, int targetPosX, int targetPosY) {
    super(damage, posX, posY, targetPosX, targetPosY);
  }

  int speed = 20;

  public void shoot(int charPositionX, int charPositionY, int mouseX, int mouseY) {
    if (posX < 2000 || posY < 2000) {
      float deltaX = mouseX - charPositionX;
      float deltaY = mouseY - charPositionY;
      float angle = (float) Math.atan2(deltaY, deltaX);

      posX += (int) (speed * Math.cos(angle));
      posY += (int) (speed * Math.sin(angle));

    }
  }

}