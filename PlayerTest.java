public class PlayerTest {
    
    public static void main(String[] args){
        Deck d = new Deck();
        d.shuffle();

        BlackjackPlayer one = new BlackjackPlayer("");
        BlackjackPlayer two = new BlackjackPlayer("");

        for (int i = 0; i < 4; i++) {
            Card c = d.deal();

            if (i % 2 == 0){
                one.take(c);
            }
            if (i % 2 != 0){
                two.take(c);
            }
        }
        System.out.println(one.toString());
        System.out.println(one.getScore());



        //System.out.println("Player One: \n" + one.toString());
        //System.out.println("Player Two: \n" +  two.toString());
    }
}
