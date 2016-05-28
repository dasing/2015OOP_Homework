import java.util.ArrayList;
import java.util.*;
import java.util.Random;

public class Computer{

	int bet = 0;
    Card[] deck;
    Shuffler shuffler;
    char[] order = { 'a', 'b', 'c' , 'd' , 'e' };
    char[] suit = { 'S', 'H', 'D', 'C' };
    char[] number = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', '0','J', 'Q', 'K' };

    /*built-in function*/
    private void initialize_a_deck(){
        
        for( int i=0; i<52; i++ ){
            Card new_card = new Card(i+1);
            deck[i] = new_card;
        }
    }

    private void sort_card( ArrayList<Card> hand_cards ){
        Collections.sort(hand_cards);
    }


    /*Constructor*/
    public Computer(){
        deck = new Card[52];
        shuffler = new Shuffler();      
        initialize_a_deck();
    }

    public void start_game( ArrayList<Card> hand_cards ){
        shuffler.Shuffle_Card( deck );
        hand_cards.clear();
    }
 
    /*Show the first card you get before you discard*/
    public void show_ori_card( ArrayList<Card> hand_cards ){
        System.out.printf("Your cards are ");
        for( int i=0; i<5; i++ ){

            // System.out.printf("suit = %d, number = %d\n", hand_cards.get(i).suit, hand_cards.get(i).number );
            char curr_suit = suit[hand_cards.get(i).suit];
            char curr_number = number[hand_cards.get(i).number-1];

            // System.out.printf("curr_suit = %c, curr_number = %c\n", curr_suit, curr_number );
            if( curr_number == '0' )
                System.out.printf("(%c) %c10 ", order[i], curr_suit );
            else
                System.out.printf("(%c) %c%c ", order[i], curr_suit, curr_number );
        }

        System.out.printf("\n");
    }

    /*Show the card after you discard card*/
    public void show_card( ArrayList<Card> hand_cards ){

        System.out.printf("Your new cards are ");
        for( int i=0; i<5; i++ ){

            // System.out.printf("suit = %d, number = %d\n", hand_cards.get(i).suit, hand_cards.get(i).number );
            char curr_suit = suit[hand_cards.get(i).suit];
            char curr_number = number[hand_cards.get(i).number-1];

            // System.out.printf("curr_suit = %c, curr_number = %c\n", curr_suit, curr_number );
            if( curr_number == '0' )
                System.out.printf("%c10 ", curr_suit );
            else
                System.out.printf("%c%c ", curr_suit, curr_number );
        }

        System.out.printf("\n");
    }


    /*deal card*/
    public void deal( ArrayList<Card> hand_cards, int number, int mode ){

        if( mode == 0 ){
            int i;
            for( i=0; i<number; i++ ){
                Card pick = new Card();
                pick = shuffler.getNext(deck);
                //System.out.printf("size=%d\n", hand_cards.size() );
                hand_cards.add(pick);
            }
            sort_card(hand_cards);
        }else if( mode == 1 )
            test_royal_flush(hand_cards);
        else if( mode == 2 )
            test_straight_flush(hand_cards);
        else if( mode == 3 )
            test_four_of_a_kind(hand_cards);
        else if( mode == 4 )
            test_full_house(hand_cards);
        else if( mode == 5 )
            test_flush(hand_cards);
        else if( mode == 6 )
            test_straight(hand_cards);
        else if( mode == 7 )
            test_three_of_a_kind(hand_cards);
        else if( mode == 8 )
            test_two_pair(hand_cards);
        else if( mode == 9 )
            test_Jacks(hand_cards);
    	
    }

    /*check the card type*/
    private int check_type( ArrayList<Card> hand_cards ){

        int[] _card = new int[17];
        int[] _order = new int[4];
        int _three = 0;
        int _two = 0;
        int _one = 0;
        int suit_flag = 0;
        int jack_flag = 0;
        

        for( int i=0; i<17; i++ ) 
            _card[i] = 0;
        for( int i=0; i<4; i++ )
            _order[i] = 0;

        for( int i=0 ; i<5; i++ ){
            int number = hand_cards.get(i).number;
            _order[hand_cards.get(i).suit]++;
            _card[number-1]++;

            if( number <=4 )
                _card[number+12] = 1;
        }

        for( int i=0 ; i<17; i++ ){
            //System.out.printf("i=%d, _one=%d\n",i, _one);
            if( _card[i] == 4 )
                return 1; /*four of a kind*/
            else if( _card[i] == 3 ){
                _three++;
                _one = 0;
            }else if( _card[i] == 2 ){
                _two++;
                _one = 0;
                if( i == 0 || i >= 10 )
                    jack_flag = 1;
            }else if( _card[i] == 1 )
                _one++;
            else if( _card[i] == 0 && _one != 5 ){
                _one = 0;
            }

        }

        for( int i=0 ; i<4; i++ ){
            if( _order[i] == 5 ){
                suit_flag = 1;
                break;
            }
        }

       /*Print for debug*/
        // System.out.printf("For Cards:\n");
        // for( int i=0; i<17; i++ )
        //     System.out.printf("%d = %d\n", i, _card[i]);
        // System.out.printf("For orders:\n");
        // for( int i=0; i<4; i++ ){
        //     System.out.printf(" %d = %d\n", i, _order[i]);
        // }
        // System.out.printf("_one = %d, suit_flag = %d\n", _one, suit_flag);

        if( _three == 1 && _two == 1 )
            return 2; /*full house*/
        else if( _three == 1 )
            return 3; /*three of a kind*/
        else if( suit_flag == 1  && _one < 5 )
            return 4; /*flush*/
        else if( _two == 2 )
            return 5; /*tow pair*/
        else if( jack_flag == 1 )
            return 6; /*Jack or better*/
        else if( _one >= 5 && suit_flag  == 1 ){
            if( _card[0] == 1 && _card[9] == 1 )
                return 7; /*royal flush*/
            else
                return 8; /*straight flush*/
        }else if( _one >= 5 ){
            return 9; /*straight*/
        }

        return 0;

    }

    public int compute_money( int payoff, int bet ){
        return(payoff-bet);
    }

    /*compute the bet*/
    public int computing( ArrayList<Card> hand_cards, int bet ){

        System.out.printf("You get ");
        int payoff;

        int type = check_type(hand_cards);
        if( type == 0 ){
            System.out.printf("nothing. The payoff is 0.\n");
        }else if( type == 1 ){
            /*four of kind*/
            payoff = 25*bet;
            System.out.printf("a four of a kind hand. The payoff is %d.\n", payoff );
            return payoff;

        }else if( type == 2 ){
            /*full house*/
            payoff = 9*bet;
            System.out.printf("a full House hand. The payoff is %d.\n", payoff );
            return payoff;
        }else if( type == 3 ){
            /*three of a kind*/
            payoff = 3*bet;
            System.out.printf("a three of a kind hand. The payoff is %d.\n", payoff );
            return payoff;

        }else if( type == 4 ){
            /*flush*/
            payoff = 6*bet;
            System.out.printf("a flush hand. The payoff is %d.\n", payoff );
            return payoff;
        }else if( type == 5 ){
            /*two pair*/
            payoff = 2*bet;
            System.out.printf("a two pair hand. The payoff is %d.\n", payoff );
            return payoff;
        }else if( type == 6 ){
            /*Jack*/
            payoff = bet;
            System.out.printf("a Jacks or better hand. The payoff is %d.\n", payoff );
            return payoff;
        }else if( type == 7 ){
            /*royal flush*/
            if( bet < 5 ){
                payoff = 250*bet;
            }else
                payoff = 4000;

            System.out.printf("a royal flush hand. The payoff is %d.\n", payoff );
            return payoff;

        }else if( type == 8 ){
            /*straight flush*/
            payoff = 50*bet;
            System.out.printf("a straight flush hand. The payoff is %d.\n", payoff );
            return payoff;

        }else if( type == 9 ){
            /*straight*/
            payoff = 4*bet;
            System.out.printf("a straight hand. The payoff is %d.\n", payoff );
            return payoff;
        }

        return 0;
        
    }


    public int discard( ArrayList<Card> hand_cards, String decision ){

        int j=0, i=0, k=0; /*i is the index of hand_card, j is the index of order, k is the index of decision*/
        int count = 0;
        int discard_flag = 0;
        String a = "none";

        if( decision.equals(a) ){
            System.out.printf("Okay. I will discard ");

            for( int l=0; l<5; l++ ){

                char curr_suit = suit[hand_cards.get(l).suit];
                char curr_number = number[hand_cards.get(l).number-1];
                if( curr_number == '0' )
                    System.out.printf("(%c) %c10 ", order[l], curr_suit );
                else
                    System.out.printf("(%c) %c%c ", order[l], curr_suit, curr_number );
            }

            hand_cards.clear();
            System.out.printf(".\n");
            return 5;
        }else{
            
            System.out.printf("Okay. I will discard ");

            while( k < decision.length() ){
                //System.out.printf("k=%d %c, j=%d %c\n", k, decision.charAt(k), j, order[j] );
                if( decision.charAt(k) == ' ' ){
                    k++;
                    continue;
                }else if( decision.charAt(k) == order[j] ){
                    i++;
                    j++;
                    k++;
                }else{
                    discard_flag = 1;
                    count++;
                    char curr_suit = suit[hand_cards.get(i).suit];
                    char curr_number = number[hand_cards.get(i).number-1];

                    if( curr_number == '0' )
                        System.out.printf("(%c) %c10 ", order[j], curr_suit );
                    else
                        System.out.printf("(%c) %c%c ", order[j], curr_suit, curr_number );

                    hand_cards.remove(i);

                    if( j < 5 )
                        j++;

                }
            }
            if( discard_flag == 0 && j == 5 ){
                System.out.printf("nothing.\n");
                return 0;
            }

        }

        while( j < 5 ){

            char curr_suit = suit[hand_cards.get(i).suit];
            char curr_number = number[hand_cards.get(i).number-1];
            if( curr_number == '0' )
                System.out.printf("(%c) %c10 ", order[j], curr_suit );
            else
                System.out.printf("(%c) %c%c ", order[j], curr_suit, curr_number );

            hand_cards.remove( i );
            j++;
            count++;

        }
        System.out.printf(".\n");
        return count;

    }

    /*check if input is valid*/
     public boolean check_input( String decision ){
        int[] charcter = new int[5];

        if( decision.equals("none") )
            return true;

        for( int i=0 ; i<5; i++ ){
            charcter[i] = 0;
        }

        int length_size = decision.length();
        for( int i=0; i<length_size; i++ ){
            char curr = decision.charAt(i);
            if( curr == 'a' && charcter[0] == 0 ) charcter[0] = 1;
            else if( curr == 'b' && charcter[1] == 0 ) charcter[1] = 1;
            else if( curr == 'c' && charcter[2] == 0 ) charcter[2] = 1;
            else if( curr == 'd' && charcter[3] == 0 ) charcter[3] = 1;
            else if( curr == 'e' && charcter[4] == 0 ) charcter[4] = 1;
            else if( curr == ' ') continue;
            else
                return false;
        }

        return true;

    }

    /*Test function*/

    public int test_mode(String input){

        if( input.equals("royalflush") )
            return 1;
        else if( input.equals("straightflush") )
            return 2;
        else if( input.equals("four") )
            return 3;
        else if( input.equals("full") )
            return 4;
        else if( input.equals("flush") )
            return 5;
        else if( input.equals("straight") )
            return 6;
        else if( input.equals("three") )
            return 7;
        else if( input.equals("two") )
            return 8;
        else if( input.equals("jack") )
            return 9;

        return 0;
    }

    private void test_royal_flush( ArrayList<Card> hand_cards ){

        Card first = new Card(1);
        hand_cards.add(first);

        for( int i=10 ; i<=13; i++){
            Card curr = new Card(i);
            hand_cards.add(curr);
        }
    }

    private void test_straight_flush( ArrayList<Card> hand_cards ){
       
        for( int i=2; i<=6; i++ ){
            Card curr = new Card();
            curr.suit =  1;
            curr.number = i;
            hand_cards.add(curr);
        }
    }

    private void test_four_of_a_kind( ArrayList<Card> hand_cards ){
        for( int i=0; i<5; i++ ){
            Card curr = new Card();
            if( i != 4 ){
                curr.suit = i;
                curr.number = 1;
            }else{
                curr.suit = 2;
                curr.number = 2;
            }
            hand_cards.add(curr);
        }
    }

    private void test_full_house( ArrayList<Card> hand_cards ){
        for( int i=0; i<5; i++ ){
            Card curr = new Card();
            if( i < 3 ){
                curr.suit = i;
                curr.number = 2;
            }else if( i == 3 ){
                curr.suit = i;
                curr.number = 3;
            }else if( i == 4 ){
                curr.suit = i-2;
                curr.number = 3;
            }
            hand_cards.add(curr);
        }

    }

    private void test_flush( ArrayList<Card> hand_cards ){
        for( int i=1; i<=5; i++ ){
            Card curr = new Card();
            curr.suit = 0;
            curr.number = i*2;
            hand_cards.add(curr);
        }
    }

    private void test_straight( ArrayList<Card> hand_cards ){
        Random ran = new Random();
        for( int i=1; i<=5; i++ ){
            Card curr = new Card();
            curr.suit =  ran.nextInt(4);
            curr.number = i;
            hand_cards.add(curr);
        }
    }

    private void test_three_of_a_kind( ArrayList<Card> hand_cards ){
        for( int i=0; i<5; i++ ){
            Card curr = new Card();
            if( i < 3 ){
                curr.suit = i;
                curr.number = 2;
            }else if( i == 3 ){
                curr.suit = i;
                curr.number = 3;
            }else if( i == 4 ){
                curr.suit = i-2;
                curr.number = 8;
            }
            hand_cards.add(curr);
        }

    }

    private void test_two_pair( ArrayList<Card> hand_cards ){
        for( int i=0; i<5; i++ ){
            Card curr = new Card();
            if( i == 0 || i == 1 ){
                curr.suit = i;
                curr.number = 1;
            }else if( i == 2 || i == 3 ){
                curr.suit = i;
                curr.number = 2;
            }else{  
                curr.suit = 1;
                curr.number = 3;
            }
            hand_cards.add(curr);
        }

    }

    private void test_Jacks( ArrayList<Card> hand_cards ){
        for( int i=1; i<=5; i++ ){
            Card curr = new Card();
            if( i == 1 || i == 2 ){
                curr.suit = i;
                curr.number = 1;
            }else{
                curr.suit = 1;
                curr.number = i;
            }
            hand_cards.add(curr);
        }

    }



    
}
