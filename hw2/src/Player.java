import java.util.ArrayList;


public class Player{
    
    String name;
    int money;
    int bet;
    int count;
    public ArrayList<Card> hand_cards;

    //constructor
    public Player( int initialize_money, String input ){
        money = initialize_money;
        name = input;
        bet = 0;
        count = 0;
        hand_cards = new ArrayList<Card>();
    }
    
}
