package weapon;

import AbstractClass.Weapons;

public class Pistol extends Weapons {
  public Pistol(int damage, int posX, int posY, int targetPosX, int targetPosY) {
    super(damage, posX, posY, targetPosX, targetPosY);
  }

  int speed = 20;

  public void shoot(int posX, int posY, int mouseX, int mouseY) {
    if (this.posX < 2000 || this.posY < 2000) {
      float deltaX = mouseX - this.posX;
      float deltaY = mouseY - this.posY;
      float angle = (float) Math.atan2(deltaY, deltaX);

      this.posX += (int) (speed * Math.cos(angle));
      this.posY += (int) (speed * Math.sin(angle));

    }
  }

}