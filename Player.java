import java.util.List;
import java.util.ArrayList;

public class Player {
    List<Card> hand;
    String playerName;   // add a getter later to ask name
    private int wins = 0;  
    private int losses = 0;

    public Player(){
        hand = new ArrayList<Card>();

    }

    public void take(Card c){
        this.hand.add(c);
    }

    public List<Card> fold(){ 
        List<Card> foldedHand = new ArrayList<Card>(); 
        foldedHand = hand;
        List<Card> newHand = new ArrayList<Card>();
        hand = newHand;

        return foldedHand;
    }

    public int getScore(){

        return 0;
    }

    public String toString(){   //improve this later to include player name
        String result = "";
        for(Card c : hand){
            result += c;
            result += "\n";
        }
        return result;
    }

    public String getName(){
        return this.playerName;
    }

    public int getSize(){
        return this.hand.size();
    }

    public void clearHand(){
        this.hand = new ArrayList<Card>();
    }
}


