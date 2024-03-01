import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card{

    private final int rank;
    private final int suit;
    private BufferedImage face;
    private static BufferedImage back;
    
    static { 
        String filename = "images/back02.png";
        try{
            back = ImageIO.read(new File(filename));
        } catch (IOException e){
            back = null;
            System.err.println(e + " file: " + filename);
        }
    }

    public static final String[] RANKS = {
        null, "Ace", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "Jack", "Queen", "King"};
    public static final String[] SUITS = {
        "Clubs", "Diamonds", "Hearts", "Spades"};
    
    
    public Card(int rank, int suit) {
        if (rank < 1||rank > 13){
            throw new IllegalArgumentException("Invalid rank:" + rank);
        }
        if (suit < 0|| suit > 3){
            throw new IllegalArgumentException("Invalid suit:" + suit);
        }
        this.rank = rank;
        this.suit = suit;

        //TODO figure out how to format the correct filename for a face
    
        int cardNum = rank * 4 + suit - 3;
        //String filename = "images/card01.png";
        String filename = String.format("images/card%02d.png", cardNum);
        try{
            this.face = ImageIO.read(new File(filename));
        } catch (IOException e){
            this.face = null;
            System.err.println(e + " file: " + filename);
        }
    }

    public String toString() {
        String s = RANKS[this.rank] + " of " + SUITS[this.suit];
        return s;
    }

    public boolean equals(Card that) {
        return this.rank == that.rank
        && this.suit == that.suit;
    }
        
    public int compareTo(Card that) {
        if (this.suit < that.suit) {
        return -1;
        }
        if (this.suit > that.suit) {
        return 1;
        }
        if (this.rank < that.rank) {
        return -1;
        }
        if (this.rank > that.rank) {
        return 1;
        }
        return 0;
    }

    public static int compare(Card card1, Card card2){
        int thisNum = card1.rank * 4 + card1.suit;
        int otherNum = card2.rank * 4 + card2.suit;
        return thisNum - otherNum;
    }
          
    public int getRank() {
        return this.rank;
        }
        public int getSuit() {
        return this.suit;
    }

    public BufferedImage getFace(){
        return this.face;
    }

    public BufferedImage getBack(){
        return back;
    }
    
    public static void printDeck(Card[] cards) {
            for (Card card : cards) {
            System.out.println(card);
        }
    }
    

}