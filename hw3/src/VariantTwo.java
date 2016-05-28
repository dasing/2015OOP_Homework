import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class VariantTwo extends OldMaid{

    char[] suit = { 'S', 'H', 'D', 'C', 'B', 'R' };
    char[] arr_number = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', '0','J', 'Q', 'K', 'G' };
  
    int basic_flag = 0;

    public VariantTwo(int mode){
        if( mode == 1 )
            computer = new Computer(1, 1);
        else
            computer = new Computer(1, 0);
    } 

    /*process after draw card, to check every player's status and if there is winner*/
    private int aft_process(int i, int player_number ){

        computer.sort_card(alive_player.get(i).hand_cards);
        computer._drop_card(alive_player.get(i));
        computer.show_card(alive_player.get(i));

        computer.sort_card(alive_player.get(((i+1)%player_number)).hand_cards);
        computer.show_card(alive_player.get(((i+1)%player_number)));
                
                //System.out.println("i="+i+",player_number="+player_number+" ,i+1 % player_number="+((i+1)%player_number));
                
        int idx = alive_player.indexOf(alive_player.get(i));
        int idx2 = alive_player.indexOf(alive_player.get((i+1)%player_number));
                
        if( idx > idx2 ){
            check_player_status( alive_player.get(i) );
            check_player_status( alive_player.get(((i+1)%player_number)) );
        }else{
            check_player_status( alive_player.get(((i+1)%player_number)) );
            check_player_status( alive_player.get(i) );
        }
               
                
        if( !winner.isEmpty() ){
            if( basic_flag == 0 ){
                output();
                System.out.println("Basic game over");
                System.out.println("Continue");
                basic_flag = 1;
                player_number = alive_player.size();
            }else{
                output();
                player_number = alive_player.size();
            }
            return 1;
        }else
            return 0;

    }

     void basic_game( ){
        
        int i;
        int player_number = alive_player.size();

        //System.out.printf("player_number=%d\n", player_number );

        while( true ){
            
            if( player_number <= 1 ){
                System.out.println("Bonus game over");
                return;
            }
            
            for( i=0; i<player_number; i++ ){
                
                //User chooser = new User(alive_player.get(i));
                //User be_choose = new User(alive_player.get((i+1)%player_number));

                int id1 = alive_player.get(i).id;
                int id2 = alive_player.get(((i+1)%player_number)).id;
                
                if( id1 == 3 ){

                    int result = 1, result2 = 0;
                    while( result == 1 && result2 == 0 ){
                        result = alive_player.get(i).draw_card1( alive_player.get(((i+1)%player_number)), id2 );
                        result2 = aft_process(i, player_number );
                    }

                    //update player_number
                    player_number = alive_player.size();

                }else{

                    int result = 1, result2 = 0;
                    while( result == 1 && result2 == 0 ){
                        result = computer.draw_card1( alive_player.get(i) , alive_player.get(((i+1)%player_number)),   id1, id2 );
                        result2 = aft_process(i, player_number );
                    }

                    //update player_number
                    player_number = alive_player.size();

                }
                    
                //aft_process(i, player_number, basic_flag );
                
            }

        }
        
    }
         
    public void game(){

       
        /*Construct User, "You" are Player3*/
        User Player0 = new User(0);
        User Player1 = new User(1);
        User Player2 = new User(2);
        User Player3 = new User(3);
        int basic_game_flag = 0;
        
        computer.start_game();
        
        computer.Deal_cards( Player0, Player1, Player2, Player3, 1 );
        computer.Drop_cards( Player0, Player1, Player2, Player3 );
        
        System.out.println("Game start");
        check_player_status( Player0 );
        check_player_status( Player1 );
        check_player_status( Player2 );
        check_player_status( Player3 );
        
        //check if there is any winner
        if( !winner.isEmpty() ){
            output();
            System.out.println("Basic game over");
            basic_game_flag = 1;
        }
        
        if( basic_game_flag == 0 )
            basic_game( );
        else{
            System.out.println("Continue");
            basic_game( );
        }

        if( computer.debug == true ){
            if ( computer.debug_function() == true )
                System.out.println("test correct");
            else
                System.out.println("test wrong");
        }
    }
        
}