public class Card implements Comparable<Card>{
    int number;
    int suit; /*0 means Spades, 1 means Hearts, 2 means Diamonds, 3 means Clubs*/

    @Override
    public int compareTo(Card card) {

        if( this.number == card.number ){
            if(this.suit > card.suit )
                return 1;
            else
                return -1;
        }else{

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
        number = index%13;
        suit = index/13;
        
        if( number == 0 ){
            number = 13;
            suit-=1;
        }
    }
}
