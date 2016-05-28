import java.util.ArrayList;
import java.util.*;
import java.util.Random;

public class Computer{

	int bet = 0;
    Card[] deck;
    int [] all_card;
    Shuffler shuffler;
    char[] suit = { 'S', 'H', 'D', 'C', 'B', 'R' };
    char[] number = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', '0','J', 'Q', 'K', 'G' };
    public boolean debug = false;

    /*built-in function*/
    private void initialize_a_deck( int type ){
        
        int i=0;
        for( i=0; i<52; i++ ){
            Card new_card = new Card(i+1);
            deck[i] = new_card;
        }

        Card ghost_card1 = new Card(53);
        deck[i] = ghost_card1;

        //VariantTwo and Original
        if( type == 1 ){
            Card ghost_card2 = new Card(54);
            deck[i+1] = ghost_card2; 
        }   
    }

    void sort_card( ArrayList<Card> hand_cards ){
        Collections.sort(hand_cards);
    }

    /*Constructor*/
    public Computer( int type, int de ){

        //VariantTwo
        if( type == 1 )
            deck = new Card[54];
        else
            deck = new Card[53];

        shuffler = new Shuffler();      
        initialize_a_deck( type );

        if( de == 1 )
            debug = true;
        else
            debug = false;

        if( debug == true ){
            System.out.printf("initialze all_card\n");
            all_card = new int[52];
            for( int i=0; i<52; i++ )
                all_card[i] = 0;
        }else{
            System.out.printf("Wrong\n");
        }
    }

    public void start_game(){
        shuffler.Shuffle_Card( deck );
    }
 
  
    /*Display a user's hand_cards*/
    public void show_card( User user ){

        ArrayList<Card> hand_cards = new ArrayList<Card>(user.hand_cards);
        int size = user.hand_cards.size();
        int id = user.id;

        if( id != 3 )
            System.out.printf("Player%d: ", id);
        else
            System.out.printf("You: ");

        for( int i=0; i<size; i++ ){

            // System.out.printf("suit = %d, number = %d\n", hand_cards.get(i).suit, hand_cards.get(i).number );
            char curr_suit = suit[hand_cards.get(i).suit];
            char curr_number = number[hand_cards.get(i).number-1];

            // System.out.printf("curr_suit = %c, curr_number = %c\n", curr_suit, curr_number );
            if( curr_number == '0' )
                System.out.printf("%c10 ", curr_suit );
            else if( curr_number == 'G' )
                System.out.printf("%c0 ", curr_suit );
            else
                System.out.printf("%c%c ", curr_suit, curr_number );
        }

        System.out.printf("\n");
    }


    /*deal card to all user*/
    void Deal_cards( User player0, User player1, User player2, User player3, int game_type ){

        System.out.println("Deal cards");

        if( game_type == 1 ){

            deal( 14, player0 );
            show_card(player0);

            deal( 14, player1 );
            show_card(player1);

            deal( 13, player2 );
            show_card(player2);

            deal( 13, player3 );
            show_card(player3);

        }else if( game_type == 2 ){

            //only one ghost card
            deal( 14, player0 );
            show_card(player0);

            deal( 13, player1 );
            show_card(player1);

            deal( 13, player2 );
            show_card(player2);

            deal( 13, player3 );
            show_card(player3);
        }
 
        

    }

    /*deal card */
    public void deal( int number, User user ){
            int i;
            for( i=0; i<number; i++ ){
                Card pick = new Card();
                pick = shuffler.getNext(deck);
                //System.out.printf("size=%d\n", hand_cards.size() );
                user.hand_cards.add(pick);
            }
            sort_card(user.hand_cards);
    }

    /*Check if two cards have equal number*/
    public boolean equal_number_card( Card a, Card b ){

       // System.out.printf("in equal_card, Card a= %d %d, Card b = %d %d\n", a.suit, a.number, b.suit, b.number );
        if( a.equal_number(b) ){
            //System.out.println("hahaha");
            return true;
        }else
            return false;
    }

    /*Drop all user's card*/
    public void Drop_cards( User player0, User player1, User player2, User player3 ){

        System.out.println("Drop cards");

        _drop_card( player0 );
        show_card( player0 );

        _drop_card( player1 );
        show_card( player1 );

        _drop_card( player2 );
        show_card( player2 );

        _drop_card( player3 );
        show_card( player3 );

    }

    /*Drop single user's card*/
    public void _drop_card( User user ){

        ArrayList<Card> hand_cards = user.hand_cards;
        int size = hand_cards.size();

        for( int i=size-1; i>0; i-- ){
            
            if( equal_number_card( hand_cards.get(i), hand_cards.get(i-1) ) ){
                
                if( debug == true ){
                    all_card[hand_cards.get(i).get_index()-1] = 1;
                    all_card[hand_cards.get(i-1).get_index()-1] = 1;
                }
                
                hand_cards.remove(i);
                hand_cards.remove(i-1);


                /*debug*/
                // for( int k=0; k<52; k++ )
                //     System.out.printf("%d ", all_card[k]);

                // System.out.printf("\n");

                //user.hand_cards.remove(i);
                //user.hand_cards.remove(i-1);
                i--;
            }
        }


        // System.out.println("Print for Debug");
        // for( int k=0; k<user.hand_cards.size(); k++ ){
        //     System.out.printf("%d %d ", user.hand_cards.get(k).suit, user.hand_cards.get(k).number );
        // }
        // System.out.printf("\n");
    }


    /*draw card function for VariantOne*/
    public void draw_card( User chooser, User be_choose, int a, int b ){

        int size = be_choose.hand_cards.size();
        Random rand = new Random();
        int choose = rand.nextInt(size);
       
        char curr_suit = suit[be_choose.hand_cards.get(choose).suit];
        char curr_number = number[be_choose.hand_cards.get(choose).number-1];

        if( b != 3 )
            System.out.printf("Player"+a+" draws a card from Player"+b+" ");
        else
            System.out.printf("Player"+a+" draws a card from You ");

        if( curr_number == '0' )
            System.out.printf("%c10 ", curr_suit );
        else if( curr_number == 'G' )
            System.out.printf("%c0 ", curr_suit );
        else
            System.out.printf("%c%c ", curr_suit, curr_number );

         System.out.printf("\n");

        Card be_draw = new Card(be_choose.hand_cards.get(choose));
        chooser.hand_cards.add(be_draw);
        be_choose.hand_cards.remove(choose);
       

    }

    /*draw card function for VariantTwo*/
    public int draw_card1( User chooser, User be_choose, int a, int b ){

            
                Random random = new Random();
                int choose_number = random.nextInt(52)+1;
                int decide = random.nextInt(2); /*0 means big, 1 means small*/
                Card rand = new Card(choose_number);
                rand.show_card();
                System.out.printf("\n");

                int size = be_choose.hand_cards.size();
                int choose = random.nextInt(size);

                if( choose == 0 )
                    System.out.printf("Player"+a+" guess Bigger!\n");
                else
                    System.out.printf("Player"+a+" guess Smaller!\n");

                int result = rand.compareTo(be_choose.hand_cards.get(choose));

                if(  (result < 0 && decide == 1 )|| ( result > 0 && decide == 0 ) ||  be_choose.hand_cards.get(choose).number == 14 ){
                   
                        /*guess right or ghost card*/
                        char curr_suit = suit[be_choose.hand_cards.get(choose).suit];
                        char curr_number = number[be_choose.hand_cards.get(choose).number-1];

                        if( b != 3 )
                            System.out.printf("Player"+a+" draws a card from Player"+b+" ");
                        else
                            System.out.printf("Player"+a+" draws a card from You ");

                        if( curr_number == '0' )
                            System.out.printf("%c10 ", curr_suit );
                        else if( curr_number == 'G' )
                            System.out.printf("%c0 ", curr_suit );
                        else
                            System.out.printf("%c%c ", curr_suit, curr_number );

                        System.out.printf("\n");

                        Card be_draw = new Card(be_choose.hand_cards.get(choose));
                        chooser.hand_cards.add(be_draw);
                        be_choose.hand_cards.remove(choose);

                        int if_continue = random.nextInt(2);
                        if( if_continue == 0 )
                            return 0;
                        else
                            return 1;
                    
                }else
                    return 0;
            
        }

        public boolean debug_function(){

            // System.out.println("print for debug");
            // for( int i=0; i<52; i++ )
            //     System.out.printf("%d ", all_card[i]);

            int flag = 0;
            for( int i=0; i<52; i++ ){
                if( all_card[i] == 0 ){
                    flag = 1;
                    Card curr = new Card(i+1);
                    curr.show_card();
                    System.out.printf(" didn't get back\n");
                }
            }
            if(flag == 0 )
                return true;
            else
                return false;
        }
    
}
