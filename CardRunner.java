import javax.swing.JFrame;

public class CardRunner {

  public static void main(String[] args) {
    JFrame f = new JFrame("Blackjack"); 
    CardPanel p = new CardPanel();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(p);
    f.pack();
    f.setVisible(true);
    p.setFocusable(true);
    p.requestFocusInWindow();
  }
}