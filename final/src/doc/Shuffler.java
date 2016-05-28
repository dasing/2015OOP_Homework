import java.util.Random;
import java.util.Collections;
class Shuffler{

	Deck deck;
	private int index;

	Shuffler(int purpose){
		this.deck = new Deck();
		if(purpose == 0){
			this.deck.addType(0);
		}else if(purpose == 1){
			this.deck.addType(1);
			this.deck.addType(2);
		}else if(purpose == 2){
			this.deck.addType(3);
		}else if( purpose == 3 ){
			this.deck.addType(4);
		}
		this.index = 0;
		System.out.printf("Type: %d, deck size = %d\n", purpose, deck.deck.size() );
	}

	public Card getNext(){
		if(this.deck.deck.size() <= index){
			System.out.println("running out of card");
			return null;
		}else{
			this.index++;
			return this.deck.deck.get(this.index-1);
		}
	}

	void shuffle(){
		Collections.shuffle(deck.deck);	
	}

}