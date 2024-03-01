import java.util.List;
import java.util.ArrayList;

public class Deck {
    
    private List<Card> listOfCards;

    public Deck(){
        listOfCards = new ArrayList<Card>();
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                Card c = new Card(rank, suit);
                this.listOfCards.add(c);
            }
        }
    }

    public String toString(){
        String result = "";
        for(Card c : listOfCards){
            result += c;
            result += "\n";
        }
        return result;
    }

    public Card deal(){
        Card topCard = listOfCards.remove(0);
        return topCard;
    }

    public int getCardsLeft(){
        return this.listOfCards.size();
    }

    public void shuffle(){
        for (int index = 0; index < 2000; index++) {
            int random = (int)(Math.random() * this.listOfCards.size());
            this.listOfCards.add(this.listOfCards.remove(random));
        }
    }

    public void returnToDeck(List<Card> cards){
        this.listOfCards.addAll(cards);
    }
}
