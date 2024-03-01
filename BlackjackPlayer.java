import java.util.List;

public class BlackjackPlayer extends Player{
    
    public BlackjackPlayer(String name) {
        
        this.playerName = name;
    }

    @Override
    public int getScore(){
        int rankCt = 0;
        boolean ace = false;

        for (int i = 0; i < this.hand.size(); i++) {
            Card c = this.hand.get(i);
            int rank = c.getRank();

            if (rank == 1){
                ace = true;
            }
            if (rank == 13 || rank == 12 || rank == 11){
                rank = 10;
            }

            rankCt += rank;
        }
        /*
         * turns aces from 1s into 11s if the tot score will be < 21... 
         * not exactly how the actual game works but idk what to do w/o user input
         */
        if (ace && rankCt <= 11){   
            rankCt = rankCt + 10;
        }

        return rankCt;

        //return -1; // avoid error for now
    }

    public List<Card> getHand(){
        return this.hand;
    }

}
