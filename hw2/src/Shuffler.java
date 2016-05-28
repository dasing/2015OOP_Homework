import java.util.Random;
import java.util.ArrayList;

public class Shuffler{
	int count = 0;
	public void Shuffle_Card( Card[] deck ){

		count = 0;
		Random rnd = new Random();
		int length = deck.length;

		for(int i=length-1;i>=0;i--){
	   		int j = rnd.nextInt(i+1);
	    	Card tmp = new Card(deck[j]);
	    	deck[j] = deck[i];
	    	deck[i] = tmp;
		}
	}

	public Card getNext( Card[] deck ){

		Card res = new Card();
		res = deck[count++];
		int length = deck.length;
		if (count == length){
	    	Shuffle_Card( deck );
	    	count = 0;
		}
		return res;
    }
}
