import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import Character.MainCharacter;
import Character.Zombie;
import javafx.scene.shape.Rectangle;
import weapon.Pistol;

public class GamePanel extends JPanel implements ActionListener {

  static final int SCREEN_WIDTH = 900;
  static final int SCREEN_HEIGHT = 900;
  Timer timer;
  MainCharacter m = createMainCharacter();
  boolean running = false;
  static final int DELAY = 35;
  ArrayList<Zombie> myZombieList = createZombieArray();
  ArrayList<Pistol> PistolList = new ArrayList<Pistol>();

  GamePanel() {
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.white);
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
    if (m.image != null) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.drawImage(m.image, m.positionX, m.positionY, this);

      for (int i = 0; i < myZombieList.size(); i++) {
        g2d.drawImage(myZombieList.get(i).image, myZombieList.get(i).positionX,
            myZombieList.get(i).positionY,
            this);
        g2d.drawRect(myZombieList.get(i).positionX, myZombieList.get(i).positionY,
            myZombieList.get(i).w,
            myZombieList.get(i).h);

      }

      g.setColor(Color.red);
      g.setFont(new Font("Arial", Font.BOLD, 15));
      FontMetrics metrics1 = getFontMetrics(g.getFont());
      FontMetrics metrics2 = getFontMetrics(g.getFont());
      g.drawString("Zombie restants: " + myZombieList.size(),
          (SCREEN_WIDTH - metrics1.stringWidth("Zombie restants: " + myZombieList.size())) / 4,
          g.getFont().getSize());
      g.drawString("Vie: " + m.health,
          (SCREEN_WIDTH - metrics2.stringWidth("Vie: " + m.health)) / 2,
          g.getFont().getSize());
    }

  }

  public void draw2(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    for (int i = 0; i < PistolList.size(); i++) {
      g2d.drawImage(PistolList.get(i).image, PistolList.get(i).posX, PistolList.get(i).posY, this);
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
    for (int i = 0; i < 10; i++) {
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

      Rectangle r = new Rectangle(PistolList.get(j).posX, PistolList.get(j).posY, PistolList.get(j).w,
          PistolList.get(j).h);
      if (r.contains(PistolList.get(j).targetPosX, PistolList.get(j).targetPosY)) {
        PistolList.remove(j);
        break;
      }

    }
    checkCollision();
    checkCollisionWithZombie();
    repaint();
  }

  public void checkCollisionWithZombie() {
    boolean breaked = false;
    for (int j = 0; j < PistolList.size(); j++) {
      for (int i = 0; i < myZombieList.size(); i++) {
        if (myZombieList.get(i).positionX < PistolList.get(j).posX + PistolList.get(j).w &&
            myZombieList.get(i).positionX + myZombieList.get(i).w > PistolList.get(j).posX &&
            myZombieList.get(i).positionY < PistolList.get(j).posY + PistolList.get(j).h &&
            myZombieList.get(i).positionY + myZombieList.get(i).h > PistolList.get(j).posY) {
          myZombieList.remove(i);
          PistolList.remove(j);
          breaked = true;
          break;
        }
      }
      if (breaked) {
        break;
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
        case java.awt.event.KeyEvent.VK_A:
          m.move('L');
          if (m.press % 2 == 0) {
            m.image = m.left_2;
          } else {
            m.image = m.left_1;
          }
          repaint();
          break;
        case java.awt.event.KeyEvent.VK_D:
          m.move('R');
          if (m.press % 2 == 0) {
            m.image = m.right_2;
          } else {
            m.image = m.right_1;
          }
          repaint();
          break;
        case java.awt.event.KeyEvent.VK_W:
          m.move('U');
          if (m.press % 2 == 0) {
            m.image = m.up_2;
          } else {
            m.image = m.up_1;
          }
          repaint();
          break;
        case java.awt.event.KeyEvent.VK_S:
          m.move('D');
          if (m.press % 2 == 0) {
            m.image = m.down_2;
          } else {
            m.image = m.down_1;
          }

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
