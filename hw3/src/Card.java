public class Card implements Comparable<Card>{

    int number;
    int suit; /*0 means Spades, 1 means Hearts, 2 means Diamonds, 3 means Clubs*/
    char[] arr_suit = { 'S', 'H', 'D', 'C', 'B', 'R' };
    char[] arr_number = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', '0','J', 'Q', 'K', 'G' };

    @Override
    public int compareTo(Card card) {

        if( this.number == card.number ){

            //ghost card
            if( this.number == 14 ){
                 if( this.suit == 5  ) 
                    return -1;
                 else 
                    return 1;
            }
           

            if( this.suit > card.suit )
                return 1;
            else
                return -1;
        }else{

            //check ghost card
            if( this.number == 14 ) return -1;
            else if( card.number == 14 ) return 1;

            if( this.number == 1 )
                return -1;
            else if( card.number == 1 )
                return 1;
            else if( this.number > card.number )
                return -1;
            else
                return 1;
        }

    }

    public Card(){
        number = 0;
        suit = 0;
    }

    public Card( Card card ){
        number = card.number;
        suit = card.suit;
    }

    public Card( int index ){
        // System.out.printf("here\n");

        if( index == 53 ){
            //B0
            number = 14;
            suit = 4;
            return;
        }else if( index == 54 ){
            //R0, R0>B0
            number = 14;
            suit = 5;
            return;
        }


        number = index%13;
        suit = index/13;
        
        if( number == 0 ){
            number = 13;
            suit-=1;
        }
    }

    /*Change a card to a number*/
    public int get_index(){
        if( number == 13 ){
            number = 0;
            suit+=1;
        }

        int index = suit*13+number;
        return index;
    }

    /*To check that if a pair of cards can be dropped*/
    public boolean equal_number( Card b ){

        //when number == 14, it is ghost card
        if( this.number == b.number && this.number != 14 )
            return true;
        else
            return false;
    }

    /*Show a single card*/
    public void show_card(){

        System.out.printf("The Card is ");
        char curr_number = arr_number[number-1];

         if( curr_number == '0' )
            System.out.printf("%c10", arr_suit[suit] );
        else if( curr_number == 'G' )
                System.out.printf("%c0 ", arr_suit[suit] );
        else
            System.out.printf("%c%c", arr_suit[suit], curr_number );

        System.out.printf(". ");
    }
}
