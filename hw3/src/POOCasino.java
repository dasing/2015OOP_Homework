import java.util.Scanner;
import java.util.ArrayList;

public class POOCasino{
    
    public static void main( String[] argv ){
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        
        Player player = new Player( scanner.nextLine() );

        System.out.printf("Welcome, %s.\n", player.name );
       

        while( true ){

            System.out.println("Do you want to open debug mode? (y/n)");
            char test_mode = scanner.next().charAt(0);
            
            System.out.println("Which game do you want to play? (a)Original (b)Variant1 (c)Variant2 (e)Exit");
            String game_type = scanner.next();

            if( game_type.equals("a") ){

                if( test_mode == 'y' ){
                    OldMaid oldmaid = new OldMaid(1);
                    oldmaid.game();
                }else{
                    OldMaid oldmaid = new OldMaid(0);
                     oldmaid.game();
                }

               

            }else if( game_type.equals("b") ){
                if( test_mode == 'y' ){
                    VariantOne  variant1 = new VariantOne(1);
                    variant1.game();
                }else{
                    VariantOne  variant1 = new VariantOne(0);
                    variant1.game();
                }

                

            }else if( game_type.equals("c") ){
                if( test_mode == 'y' ){
                    VariantTwo  variant2 = new VariantTwo(1);
                    variant2.game();
                 }else{
                    VariantTwo  variant2 = new VariantTwo(0);
                    variant2.game();
                }
                

            }else if( game_type.equals("e") ){
                System.out.printf("Good bye, %s.\n", player.name );
                break;
            }else{
                System.out.println("Wrong input");
                continue;
            }
        }
       
    }

}
