import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class VariantOne extends OldMaid{

    public VariantOne(int mode){
        

        if( mode == 1 )
            computer = new Computer(2, 1);
        else
            computer = new Computer(2, 0);
    } 
         
    public void game(){
        
        //use array to store the cards of players, 1 means S1, 14 means H1.....,53 means B0, 54 means R0
        int basic_game_flag = 0;
        
        /*Construct User, "You" are Player3*/
        User Player0 = new User(0);
        User Player1 = new User(1);
        User Player2 = new User(2);
        User Player3 = new User(3);

        computer.start_game();
        
        computer.Deal_cards( Player0, Player1, Player2, Player3, 2 );
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
            basic_game( 0 );
        else{
            System.out.println("Continue");
            basic_game( 1 );
        }

         if( computer.debug == true ){
            if ( computer.debug_function() == true )
                System.out.println("test correct");
            else
                System.out.println("test wrong");
        }


    }
        
}