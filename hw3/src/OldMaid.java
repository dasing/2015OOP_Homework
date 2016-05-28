import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class OldMaid{

    ArrayList<User> alive_player = new ArrayList<User>();
    ArrayList<Integer> winner = new ArrayList<Integer>();
    Computer computer;

    public OldMaid(){
        computer = new Computer(1, 0);
    }

    public OldMaid( int mode ){
        if( mode == 1 )
            computer = new Computer(1, 1);
        else
            computer = new Computer(1, 0);
    }
         
    void basic_game( int basic_flag ){
        
        int i;
        int player_number = alive_player.size();

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
                    alive_player.get(i).draw_card( alive_player.get(((i+1)%player_number)), id2 );
                }else{
                    computer.draw_card( alive_player.get(i) , alive_player.get(((i+1)%player_number)),  id1, id2 );
                }
                
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
                }
                
            }

        }
        
    }
    
    
    void check_player_status( User player ){
        
        //System.out.printf("player %d, size=%d\n", player.id, player.hand_cards.size() );
        if( player.hand_cards.size() == 0 ){
            //no card
            player.state = 0;
                        
            if( alive_player.contains(player) )
                alive_player.remove(player);
                        
            winner.add(player.id);

        }else{
            //still have card
            if( !alive_player.contains(player) )
                alive_player.add(player);
        }
        
    }
    
    void output(){
        
        int id = 0;
        int id2 = 0;
        if( winner.size() == 1 ){
            id = winner.get(0);
            if( id == 3 )
                System.out.println("You wins");
            else
                System.out.println("Player"+id+" wins");

            winner.clear();
        }else{

            id = winner.get(0);
            id2 = winner.get(1);

            if( id == 3 ){
                System.out.println("You and Player"+id2+" win");
            }else if( id2 == 3 ){
                System.out.println("You and Player"+id+" win");
            }else{
                if( id > id2 )
                    System.out.println("Player"+id2+" and Player"+id+" win");
                else
                    System.out.println("Player"+id+" and Player"+id2+" win");
            }
            
            winner.clear();
        }
        
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