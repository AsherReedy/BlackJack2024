import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;
import javax.swing.JPanel;
public class BlackjackPanel extends JPanel implements KeyListener{
// put class & instance variables to control animation here

BlackjackPlayer one; 
BlackjackPlayer two; 
BlackjackPlayer dealer;
char userInput = 0;
String statusMsg;
String state; 
int winsCt = 0;
int lossCt = 0;
int ties = 0;
double edge;
boolean playerWin;
boolean dealerWin;


BufferedImage background;

Deck d;
int fps = 10;

public BlackjackPanel(int w, int h) {
    one = new BlackjackPlayer("P1");
    two = new BlackjackPlayer("P2 (Not here)");
    dealer = new BlackjackPlayer("Franklin");
    state = "READY";

    String background = String.format("images/asmong.jpg");
        try{
            this.background = ImageIO.read(new File(background));
        } catch (IOException e){
            this.background = null;
            System.err.println(e + " file: " + background);
        }

    d = new Deck();
    setPreferredSize(new Dimension(w,h));
    Color color = new Color(1,50,32);
    setBackground(color);
    d.shuffle();

    addKeyListener(this);
}

public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.drawImage(background, 0, 0, 600, 400, null);

    for (int i = 0; i < one.getSize(); i++) {
        BufferedImage imageObject = one.getHand().get(i).getFace();
        g.drawImage(imageObject, 100 + (i*20), 270 + (i*4), 75, 100, null);
    }
    
    if (state == "PLAYERONE"){
        BufferedImage back = dealer.getHand().get(0).getBack();
        BufferedImage imageObjecta = dealer.getHand().get(1).getFace();

        g.drawImage(back, 250, 40, 75,100,null);
        g.drawImage(imageObjecta, 270, 44, 75, 100, null);
        
    } else {for (int i = 0; i < dealer.getSize(); i++) {
        BufferedImage imageObject = dealer.getHand().get(i).getFace();
        g.drawImage(imageObject, 250 + (20 * i), 40 + (4 * i), 75, 100, null);
    }}

    Color f = new Color(200,200,200);  
    g.setColor(f);
    g.drawRect(200, 10, 180, 20);
    g.drawRect(100, 240, 80, 20);
    g.drawRect(400, 240, 80, 20);

    Color u = new Color(100,100,100);
    g.setColor(u);
    g.fillRect(200, 10, 180, 20);
    g.fillRect(100, 240, 80, 20);
    g.fillRect(400, 240, 80, 20);

    Color c = new Color(255, 255, 255);
    g.setColor(c);
    g.drawString("Dealer:  " + dealer.getName(), 240 ,23);
    g.drawString(one.getName(), 105, 253);
    g.drawString(two.getName(), 405, 253);

    if (winsCt != 0 || lossCt != 0){   //fix this
        edge = (winsCt - lossCt) / (winsCt + lossCt);
    }
    g.drawString("WINS: " + winsCt, 25, 200);
    g.drawString("LOSSES: " +lossCt, 25, 180);
    g.drawString("Edge (WIP): " + edge, 25, 160);
    g.drawString("TIES: " +ties, 25, 220);

    g.setColor(Color.RED);
    Font messageFont = new Font("Arial", Font.BOLD, 24);
    g.setFont(messageFont);
    g.drawString(statusMsg, 120, 200);   
    
}
public void run() {
    while (true) {
        update(); 
        repaint();
        delay(1000 / fps);
    }
}
public void delay(int n) {
    try {
        Thread.sleep(n);
} catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
}

public void update(){
    
    if (state.equals("READY")){
        statusMsg = "PRESS s TO PLAY";
        if (userInput == 's'){
            state = "DEAL";
            userInput = 0;
        }
    } else if (state.equals("DEAL")){
        for (int i = 1; i < 5; i++) {  
            Card c = d.deal();
      
            if (i % 2 == 0){
                one.take(c);
            }
            if (i % 2 != 0){
                dealer.take(c);
            }
        }        
        state = "PLAYERONE";
    } else if (state.equals("PLAYERONE")){
        statusMsg = "PRESS d TO HIT... PRESS n TO STAND";
        if (one.getScore() > 21){   
            state = "SHOW";
        }

        if (userInput == 'd'){   
            Card c = d.deal();
            one.take(c);
            userInput = 0;
            state = "PLAYERONE";
        }

        if (userInput == 'n'){   
            userInput = 0;
            state = "DEALER";
        }

    } else if (state.equals("DEALER")){
        if (dealer.getScore() > 21){
            state = "SHOW";
        }
        if (dealer.getScore() < 17){
            Card c = d.deal();
            dealer.take(c);
        } 
        state = "SHOW";

    }else if (state.equals("SHOW")){
        if (one.getScore() <= 21 && dealer.getScore() <= 21){
            if (one.getScore() > dealer.getScore()){
                statusMsg = "YOU WIN :DDD";
                playerWin = true;
            } else if (one.getScore() == dealer.getScore()){
                statusMsg = "YOU TIE :|";
            }
            else if (dealer.getScore() > one.getScore()){
                statusMsg = "YOU SUCK HAHAHAHA"; 
                dealerWin = true;
            }
        }
        if (one.getScore() > 21){
            statusMsg = "YOU BUSTED"; 
            dealerWin = true;
        }
        if (dealer.getScore() > 21){
            statusMsg = "DEALER  B U S T E D";
            playerWin = true;
        }

        if (userInput == 'r'){
            state = "FOLD";
            userInput = 0;

            if (dealerWin){
                lossCt++;
            } else if (playerWin){
                winsCt++;
            } else {
                ties++;
            }
            playerWin = false;
            dealerWin = false;
        }

    }else if (state.equals("FOLD")){
        d.returnToDeck(one.getHand());
        d.returnToDeck(dealer.getHand());
        one.clearHand();
        dealer.clearHand();
        d.shuffle();
        state = "READY";
    } else {
        throw new RuntimeException("Undefined state: " + state);
    }

}

@Override
public void keyTyped(KeyEvent e) {
    userInput = e.getKeyChar();
}
@Override
public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
}
@Override
public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
}
}
