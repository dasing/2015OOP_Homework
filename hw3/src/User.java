import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class User{
    
    int id;
    int state;
    ArrayList<Card> hand_cards;
    char[] suit = { 'S', 'H', 'D', 'C', 'B', 'R' };
    char[] number = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', '0','J', 'Q', 'K', 'G' };
    
    public User( int player_id ){
        
        //assign player id
        id = player_id;
        state = 1; // 1 means alive, 0 means dead
        
        hand_cards = new ArrayList<Card>();
            
    }
    
    public User( User user ){
        id = user.id;
        state = user.state;
        int size = hand_cards.size();
        for( int i=0; i<size; i++ ){
            hand_cards.add(new Card(user.hand_cards.get(i)));
        }
    }

    //draw_card function for VariantOne
    public void draw_card( User be_choose, int id ){

        Scanner scanner = new Scanner(System.in);

        int size = be_choose.hand_cards.size();

        System.out.printf("Which card do you want to draw?(1-%d)\n",size);
        int choose = scanner.nextInt();

        while( choose <= 0 || choose > size ){
            System.out.printf("Wrong input, Which card do you want to draw?(1-%d)\n",size);
            choose = scanner.nextInt();
        }
        //get card number
        char curr_suit = suit[be_choose.hand_cards.get(choose-1).suit];
        char curr_number = number[be_choose.hand_cards.get(choose-1).number-1];
        //output
        System.out.print("You draws a card from Player"+id+" ");

        if( curr_number == '0' )
            System.out.printf("%c10 ", curr_suit );
        else if( curr_number == 'G' )
            System.out.printf("%c0 ", curr_suit );
        else
            System.out.printf("%c%c ", curr_suit, curr_number );

        System.out.printf("\n");

        Card be_draw = new Card(be_choose.hand_cards.get(choose-1));
        this.hand_cards.add(be_draw);
        be_choose.hand_cards.remove(choose-1);

    }

    //draw_card function for VariantTwo
    public int draw_card1( User be_choose, int id ){

                //System.out.println("Here!");
                Scanner scanner = new Scanner(System.in);
                Random random = new Random();
                int choose_number = random.nextInt(52)+1;

                Card ran_card = new Card(choose_number);
                ran_card.show_card();

                System.out.printf("Bigger or Smaller?( 0 for Bigger, 1 for Smaller ): ");
                int guess = scanner.nextInt();

                int size = be_choose.hand_cards.size();
                System.out.printf("Which card do you want to draw?(1-%d)", size );
                int choose = scanner.nextInt();

                 while( choose <= 0 || choose > size ){
                    System.out.printf("Wrong input, Which card do you want to draw?(1-%d)\n",size);
                    choose = scanner.nextInt();
                }

                int result = ran_card.compareTo(be_choose.hand_cards.get(choose-1));

                if( (result < 0 && guess == 1 ) || (result > 0 && guess == 0 ) || (be_choose.hand_cards.get(choose-1).number == 14) ){
                    /*guess right or ghost card*/
                        char curr_suit = suit[be_choose.hand_cards.get(choose-1).suit];
                        char curr_number = number[be_choose.hand_cards.get(choose-1).number-1];
                        //output
                        System.out.print("You draws a card from Player"+id+" ");

                        if( curr_number == '0' )
                            System.out.printf("%c10 ", curr_suit );
                        else if( curr_number == 'G' )
                            System.out.printf("%c0 ", curr_suit );
                        else
                            System.out.printf("%c%c ", curr_suit, curr_number );

                        System.out.printf("\n");

                        Card be_draw = new Card(be_choose.hand_cards.get(choose-1));
                        this.hand_cards.add(be_draw);
                        be_choose.hand_cards.remove(choose-1);

                        
                        while( true ){
                            System.out.printf("Do you want to continue?(y/n) ");
                            char if_continue = scanner.next().charAt(0);
                            if( if_continue == 'n' || if_continue == 'N' )
                                return 0;
                            else if( if_continue == 'y' || if_continue == 'Y' )
                                return 1;
                            else
                                continue;
                        }
                            
                }else
                    return 0;
 
    }
    
}
