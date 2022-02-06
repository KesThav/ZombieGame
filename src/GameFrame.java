import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame {

  GameFrame() {
    JLabel background;
    this.add(new GamePanel());
    this.setTitle("ZombieGame");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon img = new ImageIcon("img/TilesetHole.png");
    background = new JLabel("", img, JLabel.CENTER);
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }

}
