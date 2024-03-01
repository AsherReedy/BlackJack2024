import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class CardPanel extends JPanel{
  static final long serialVersionUID = 1;

    BlackjackPlayer one = new BlackjackPlayer("Asher");
    BlackjackPlayer two = new BlackjackPlayer("Alex");

    Deck d = new Deck();

  public CardPanel(){
    setPreferredSize(new Dimension(600,400));
    Color c = new Color(1,50,32);
    setBackground(c);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    d.shuffle();

    for (int i = 0; i < 4; i++) {
      Card c = d.deal();

      if (i % 2 == 0){
          one.take(c);
          g.drawImage(c.getFace(), 100 + (i * 10), 300, 60, 80, null);
      }
      if (i % 2 != 0){
          two.take(c);
          g.drawImage(c.getFace(), 400 + (i * 10), 300, 60, 80, null);
      }


    //g.drawImage(card1.getFace(), x, y, width, height, null);

    }
  }
}