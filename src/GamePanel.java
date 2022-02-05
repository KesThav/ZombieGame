import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import Character.MainCharacter;
import Character.Zombie;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import weapon.Pistol;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements ActionListener {

  static final int SCREEN_WIDTH = 900;
  static final int SCREEN_HEIGHT = 900;
  Timer timer;
  MainCharacter m = createMainCharacter();
  boolean running = false;
  static final int DELAY = 75;
  ArrayList<Zombie> myZombieList = createZombieArray();
  ArrayList<Pistol> PistolList = new ArrayList<Pistol>();

  GamePanel() {
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    this.addMouseListener(new MyMouseAdapter());
    startGame();
  }

  private void startGame() {
    running = true;
    timer = new Timer(DELAY, this);
    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
    draw2(g);
  }

  public void draw(Graphics g) {
    Graphics g2 = (Graphics2D) g;
    if (m.image != null) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.drawImage(m.image, m.positionX, m.positionY, 50, 50, this);

      for (int i = 0; i < myZombieList.size(); i++) {
        g2d.drawImage(myZombieList.get(i).image, myZombieList.get(i).positionX,
            myZombieList.get(i).positionY, 50, 50,
            this);

      }

    }

  }

  public void draw2(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    for (int i = 0; i < PistolList.size(); i++) {
      g2d.drawImage(PistolList.get(i).image, PistolList.get(i).posX, PistolList.get(i).posY, 50, 50, this);
    }

  }

  public MainCharacter createMainCharacter() {
    return new MainCharacter(100, (int) (Math.random() * SCREEN_WIDTH - 50),
        (int) (Math.random() * SCREEN_HEIGHT - 50));
  }

  public Zombie createZombie() {
    return new Zombie(100, (int) (Math.random() * SCREEN_WIDTH - 50),
        (int) (Math.random() * SCREEN_HEIGHT - 50));
  }

  public ArrayList<Zombie> createZombieArray() {
    ArrayList<Zombie> myZombieList = new ArrayList<Zombie>();
    for (int i = 0; i < 2; i++) {
      myZombieList.add(createZombie());
    }
    return myZombieList;
  }

  public void checkCollision() {
    for (int i = 0; i < myZombieList.size(); i++) {

      if (myZombieList.get(i).positionX < m.positionX + m.w &&
          myZombieList.get(i).positionX + myZombieList.get(i).w > m.positionX &&
          myZombieList.get(i).positionY < m.positionY + m.h &&
          myZombieList.get(i).positionY + myZombieList.get(i).h > m.positionY) {
        m.health -= 1;
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    for (int i = 0; i < myZombieList.size(); i++) {
      myZombieList.get(i).move2(m.positionX, m.positionY);
    }

    for (int j = 0; j < PistolList.size(); j++) {
      PistolList.get(j).shoot(PistolList.get(j).posX, PistolList.get(j).posY, PistolList.get(j).targetPosX,
          PistolList.get(j).targetPosY);
      if (PistolList.get(j).posX >= PistolList.get(j).targetPosX
          && PistolList.get(j).posY >= PistolList.get(j).targetPosY) {
        PistolList.remove(j);
      }
    }
    checkCollision();
    checkCollisionWithZombie();
    repaint();
  }

  public void checkCollisionWithZombie() {
    for (int j = 0; j < PistolList.size(); j++) {
      for (int i = 0; i < myZombieList.size(); i++) {
        if (myZombieList.get(i).positionX < PistolList.get(j).posX + PistolList.get(j).w &&
            myZombieList.get(i).positionX + myZombieList.get(i).w > PistolList.get(j).posX &&
            myZombieList.get(i).positionY < PistolList.get(j).posY + PistolList.get(j).h &&
            myZombieList.get(i).positionY + myZombieList.get(i).h > PistolList.get(j).posY) {
          PistolList.remove(j);
          myZombieList.remove(i);

        }
      }
    }
  }

  public class MyKeyAdapter implements KeyListener {

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
      // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
      switch (e.getKeyCode()) {
        case java.awt.event.KeyEvent.VK_LEFT:
          m.move('L');
          repaint();
          break;
        case java.awt.event.KeyEvent.VK_RIGHT:
          m.move('R');
          repaint();
          break;
        case java.awt.event.KeyEvent.VK_UP:
          m.move('U');
          repaint();
          break;
        case java.awt.event.KeyEvent.VK_DOWN:
          m.move('D');
          repaint();
          break;
      }

    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
      // TODO Auto-generated method stub

    }
  }

  public class MyMouseAdapter implements MouseListener {

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
      int mouseX = (int) e.getX();
      int mouseY = (int) e.getY();
      PistolList.add(new Pistol(1, m.positionX, m.positionY, mouseX, mouseY));

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
      int mouseX = (int) e.getX();
      int mouseY = (int) e.getY();
      PistolList.add(new Pistol(1, m.positionX, m.positionY, mouseX, mouseY));

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
      // TODO Auto-generated method stub

    }

  }
}
