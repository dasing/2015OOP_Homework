import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class PlayGame{
    
    static int player0_flag = 0;
    static int player1_flag = 0;
    static int player2_flag = 0;
    static int player3_flag = 0;
    
    static void sort_card( int [] a ){
        
//        System.out.println("Before");
//        for( int k=0; k<14; k++ ){
//            System.out.print(" "+a[k]);
//        }
//        System.out.print("\n");
        
        int tmp;
        for( int i=13; i>=0; i-- ){
            for( int j=0; j<i; j++ ){
                if( a[j] < 0 || a[j+1] < 0 ){
                    if( a[j+1] < a[j] ){
                        //swap
                        tmp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = tmp;
                    }
                }else{
                    if( a[j] == 100 ){
                        //swap
                        tmp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = tmp;
                        continue;
                    }else if( a[j+1] == 100 )
                        continue;
                    else{
                        int num1 = a[j]/13;
                        int res1 = a[j]%13;
                        int num2 = a[j+1]/13;
                        int res2 = a[j+1]%13;
                        if( res1 != res2 ){
                           if( res1 == 0 || ( res1 > res2 && res2 != 0 ) ){
                                //swap
                               tmp = a[j];
                               a[j] = a[j+1];
                               a[j+1] = tmp;
                            }
                        }else{
                            if( num1 > num2 ){
                                //swap
                                tmp = a[j];
                                a[j] = a[j+1];
                                a[j+1] = tmp;
                            }
                        }
                    }
                }
            }
        }
        
//        System.out.println("after");
//        for( int k=0; k<14; k++ ){
//            System.out.print(" "+a[k]);
//        }
//        System.out.print("\n");
    }
    
    
    //void Deal_cards( int [] player0, int [] player1, int [] player2, int [] player3 ){
    void Deal_cards( User player0, User player1, User player2, User player3 ){

        Random rand = new Random();
        List<Integer> all_cards = new ArrayList<>();
        for( int i=1; i<=54; i++ ){
            all_cards.add(i);
        }
        int cnt=0;
        
        while( all_cards.size() >0 ){
            int get_card = all_cards.remove(rand.nextInt(all_cards.size()));
            
            if( get_card == 53 )
                get_card = -1; //B0
            else if( get_card == 54 )
                get_card = -2; //R0
            //System.out.println("choose"+get_card);
            
            if( cnt < 14 ){
                player0.ori_card[cnt] = get_card;
                
            }else if( cnt >= 14 && cnt < 28 ){
                player1.ori_card[cnt-14] = get_card;
                
            }else if( cnt >= 28 && cnt < 41 ){
                player2.ori_card[cnt-28] = get_card;
                
            }else if( cnt >= 41 ){
                player3.ori_card[cnt-41] = get_card;
            }
            cnt++;
        }
        
        player2.ori_card[13] = 100;
        player3.ori_card[13] = 100;
        
        //sort array
        sort_card(player0.ori_card);
        sort_card(player1.ori_card);
        sort_card(player2.ori_card);
        sort_card(player3.ori_card);

    }
    
    static int check_index( char a ){
        if( a == 'N' )
            return 4;
        else if( a == 'C' )
            return 3;
        else if( a == 'D' )
            return 2;
        else if( a == 'H' )
            return 1;
        else if( a == 'S' )
            return 0;
        
        return 4;
    }
    
    //show fixed card
    static void Show_cards( User player ){
        
        int i=0;
       
        if( player.card[14] == 'Y' ){
            System.out.print(" R0");
        }
        if( player.card[13] == 'Y' ){
            System.out.print(" B0");
        }
        
        for( i=0; i<13; i++ ){
            if( player.card[i] == 'N' )
                continue;
            else{
                if( i == 10 )
                    System.out.print(" "+player.card[i]+"J");
                else if( i == 11 )
                    System.out.print(" "+player.card[i]+"Q");
                else if( i == 12 )
                    System.out.print(" "+player.card[i]+"K");
                else
                    System.out.print(" "+player.card[i]+(i+1) );
            }
        }
        
        System.out.print("\n");
    }
    
    void separate_cards( User player ){
        
        char suit[] = { 'S', 'H', 'D', 'C'};
        for( int i=13; i>=0; i-- ){
            if( player.ori_card[i] == 100 )
                continue;
            
            if( player.ori_card[i] == -1 ){
                player.card[13] = 'Y';
                continue;
            }else if( player.ori_card[i] == -2 ){
                player.card[14] = 'Y';
                continue;
            }

            int num = player.ori_card[i]/13;
            int res = player.ori_card[i]%13;
            
            if( res == 0 ){
                int ori_idx = check_index( player.card[12] );
                //int idx = check_index(suit[num-1]);
                
                if( ori_idx == 4 )
                    player.card[12] = suit[num-1];
                else
                    player.card[12] = 'N';
            }else{
                int ori_idx = check_index( player.card[res-1] );
               // int idx = check_index(suit[num]);
                
                if( ori_idx == 4 )
                    player.card[res-1] = suit[num];
                else
                    player.card[res-1] = 'N';
            }
            
        }
    }
    
    //show ori_player's cards
    static void Display_cards( User player ){
        
        char suit[] = { 'S', 'H', 'D', 'C' };
        for( int i=0; i<14; i++ ){
            
            if( player.ori_card[i] == 100 )
                break;
            if( player.ori_card[i] == -1 ){
                System.out.print(" B0");
                continue;
            }
            if( player.ori_card[i] == -2 ){
                System.out.print(" R0");
                continue;
            }
            int num = player.ori_card[i]/13;
            int res = player.ori_card[i]%13;
            
            if( res == 0 ){
                System.out.print(" "+suit[num-1]+"K");
                continue;
            }else{
                if( res == 11 )
                    System.out.print(" "+suit[num]+"J");
                else if( res == 12 )
                    System.out.print(" "+suit[num]+"Q");
                else
                    System.out.print(" "+suit[num]+res);
                
            }
            
        }
        System.out.print("\n");
        
    }
    
    void draw_card( char [] chooser, char [] be_choose, int a, int b ){
        Random rand = new Random();
        int choose = rand.nextInt(15);
        //choose card
        while( be_choose[choose] == 'N' ){
            choose = rand.nextInt(15);
        }
        
        if( choose < 10 )
            System.out.println("Player"+a+" draws a card from Player"+b+" "+be_choose[choose]+(choose+1));
        else if( choose == 10 ){
            System.out.println("Player"+a+" draws a card from Player"+b+" "+be_choose[choose]+"J");
        }else if( choose == 11 )
            System.out.println("Player"+a+" draws a card from Player"+b+" "+be_choose[choose]+"Q");
        else if( choose == 12 )
            System.out.println("Player"+a+" draws a card from Player"+b+" "+be_choose[choose]+"K");
        else if( choose == 13 )
            System.out.println("Player"+a+" draws a card from Player"+b+" B0");
        else if( choose == 14 )
            System.out.println("Player"+a+" draws a card from Player"+b+" R0");
        
        if( chooser[choose] == 'N' )
            chooser[choose] = be_choose[choose];
        else
            chooser[choose] = 'N';
        
        be_choose[choose] = 'N';
        
    }
    
    static int check_card_num( char [] card ){
        for( int i=0; i<15; i++ ){
            if( card[i] != 'N' )
                return 1;
        }
        
        return -1;
    }
    
    void basic_game( ArrayList<User> alive_player, ArrayList<Integer> winner, int basic_flag ){
        
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
                
                
                draw_card( alive_player.get(i).card , alive_player.get(((i+1)%player_number)).card,   alive_player.get(i).id, alive_player.get(((i+1)%player_number)).id );
                
                System.out.print("Player"+alive_player.get(i).id+":");
                Show_cards( alive_player.get(i) );
                System.out.print("Player"+alive_player.get(((i+1)%player_number)).id+":");
                Show_cards( alive_player.get( ((i+1)%player_number)) );
                
                //System.out.println("i="+i+",player_number="+player_number+" ,i+1 % player_number="+((i+1)%player_number));
                
                int idx = alive_player.indexOf(alive_player.get(i));
                int idx2 = alive_player.indexOf(alive_player.get((i+1)%player_number));
                
                if( idx > idx2 ){
                    check_player_status( alive_player, winner, alive_player.get(i) );
                    check_player_status( alive_player, winner, alive_player.get(((i+1)%player_number)) );
                }else{
                    check_player_status( alive_player, winner, alive_player.get(((i+1)%player_number)) );
                    check_player_status( alive_player, winner, alive_player.get(i) );
                }
               
                
                if( !winner.isEmpty() ){
                    if( basic_flag == 0 ){
                        output(winner);
                        System.out.println("Basic game over");
                        System.out.println("Continue");
                        basic_flag = 1;
                        player_number = alive_player.size();
                    }else{
                        output(winner);
                        player_number = alive_player.size();
                    }
                }
                
            }

        }
        
    }
    
    
    static void check_player_status( ArrayList<User> alive_player, ArrayList<Integer> winner, User player ){
        
        int result = 0;
        result = check_card_num( player.card);
        if( result < 0 ){
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
    
    static void output( ArrayList<Integer> winner ){
        
        int id = 0;
        int id2 = 0;
        if( winner.size() == 1 ){
            id = winner.get(0);
            System.out.println("Player"+id+" wins");
            winner.clear();
        }else{
            id = winner.get(0);
            id2 = winner.get(1);
            
            if( id > id2 )
                System.out.println("Player"+id2+" and Player"+id+" win");
            else
                System.out.println("Player"+id+" and Player"+id2+" win");
            
            winner.clear();
        }
        
        
        
    }
    
    public static void main(String[] argv){
        
        //use array to store the cards of players, 1 means S1, 14 means H1.....,53 means B0, 54 means R0
        
        int basic_game_flag = 0;
        PlayGame basic = new PlayGame();
        ArrayList<User> alive_player = new ArrayList<User>();
        ArrayList<Integer> winner = new ArrayList<Integer>();
        
        User Player0 = new User(0);
        User Player1 = new User(1);
        User Player2 = new User(2);
        User Player3 = new User(3);
        
        
        System.out.println("Deal cards");
        basic.Deal_cards( Player0, Player1, Player2, Player3 );
        
        System.out.print("Player0:");
        basic.Display_cards( Player0 );

        System.out.print("Player1:");
        basic.Display_cards( Player1 );
        
        System.out.print("Player2:");
        basic.Display_cards( Player2 );
        
        System.out.print("Player3:");
        basic.Display_cards( Player3 );
        
        System.out.println("Drop Cards");
        
        System.out.print("Player0:");
        basic.separate_cards( Player0 );
        basic.Show_cards( Player0 );
        
        System.out.print("Player1:");
        basic.separate_cards( Player1 );
        basic.Show_cards( Player1 );
        
        System.out.print("Player2:");
        basic.separate_cards( Player2 );
        basic.Show_cards( Player2 );
        
        System.out.print("Player3:");
        basic.separate_cards( Player3 );
        basic.Show_cards( Player3 );
        
        System.out.println("Game start");
        check_player_status( alive_player, winner, Player0 );
        check_player_status( alive_player, winner, Player1 );
        check_player_status( alive_player, winner, Player2 );
        check_player_status( alive_player, winner, Player3 );
        
        //check if there is any winner
        if( !winner.isEmpty() ){
            output(winner);
            System.out.println("Basic game over");
            basic_game_flag = 1;
        }
        
        if( basic_game_flag == 0 )
            basic.basic_game( alive_player, winner, 0 );
        else{
            System.out.println("Continue");
            basic.basic_game( alive_player, winner, 1 );
        }


    }
}
