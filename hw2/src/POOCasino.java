import java.util.Scanner;
import java.util.ArrayList;

public class POOCasino{
    
    public static void main( String[] argv ){
        
        Computer computer= new Computer();
        
        System.out.println("POOCasino Jacks or better, written by b01902135 Man-Hsin Kao");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        
        Player player = new Player(1000, scanner.nextLine() );

        System.out.printf("Welcome, %s.\n", player.name );
        System.out.printf("You have %d P-dollars now.\n", player.money );

        int mode = computer.test_mode(player.name);

        while( true ){

            computer.start_game(player.hand_cards);
            System.out.printf("Please enter your P-dollar bet for round %d (1-5 or 0 for quitting the game): ", player.count+1);
            
            while( true ){
                String input = scanner.nextLine();
                if( input.equals("change") ){
                    System.out.printf("Which name do you want to change? ");
                    player.name = scanner.nextLine();
                    mode = computer.test_mode(player.name);
                     System.out.printf("Okay! Welcome, %s. Please enter your P-dollar bet for round %d (1-5 or 0 for quitting the game): ", player.name, player.count+1);
                     continue;
                }
                try{
                    //valid input
                     int curr = Integer.parseInt(input);
                     if( curr >= 0 && curr < 6 ){
                        player.bet = curr;
                        break;
                    }else{
                        System.out.printf("Wrong input! Please enter your P-dollar bet for round %d (1-5 or 0 for quitting the game) again: ", player.count+1);
                        continue;
                    }
                }catch(NumberFormatException e){
                    //invalid input
                    System.out.printf("Wrong input! Please enter your P-dollar bet for round %d (1-5 or 0 for quitting the game) again: ", player.count+1);

                }
            }
        
            //end game
            if( player.bet == 0 ){
                System.out.printf("Good bye, %s. You played for %d round and have %d P-dollars now.\n", player.name, player.count ,player.money);
                break;
            }else
                 player.count++;

            computer.deal(  player.hand_cards, 5, mode );
            computer.show_ori_card( player.hand_cards );

            System.out.print("Which cards do you want to keep? ");
            String decision = scanner.nextLine();

            while( computer.check_input(decision) == false ){
                System.out.print("Wrong input ! You can only type character a-e or none for no card. Which cards do you want to keep? ");
                decision = scanner.nextLine();
            }

            int new_card_number = computer.discard( player.hand_cards, decision );
            computer.deal( player.hand_cards, new_card_number, 0 );
            computer.show_card( player.hand_cards );
            int payoff = computer.computing( player.hand_cards, player.bet );
            player.money += computer.compute_money( payoff, player.bet );

            System.out.printf("You have %d P-dollars now.\n", player.money );
        
        }
        
        
    }

}
