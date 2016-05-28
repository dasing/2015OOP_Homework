import java.util.*;
class Deck{
	ArrayList<Card> deck;
	/*
	type 0 for character
	type 1,2 for functional and path
	type 3 for reward
	*/
	void addType(int type){
		int i;
		if(type == 0){

			this.deck.add(new Card( Command.CARDTYPE_ROLE, Command.ID_ROLE_MINER1 ) );
			this.deck.add(new Card( Command.CARDTYPE_ROLE, Command.ID_ROLE_MINER2 ) );
			this.deck.add(new Card( Command.CARDTYPE_ROLE, Command.ID_ROLE_MINER3 ) );
			this.deck.add(new Card( Command.CARDTYPE_ROLE, Command.ID_ROLE_MINER4 ) );
			this.deck.add(new Card( Command.CARDTYPE_ROLE, Command.ID_ROLE_SABOTEUR ) );

		}else if(type == 1){
			for(i = 0;i < 6;i++){
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_GPS));
			}
			for(i = 0;i < 3;i++){
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_TOWED));
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_HSUANTIEN));
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_STUPIDBIRD));
				this.deck.add(new Card(Command.CARDTYPE_ACTION ,Command.ID_ACTION_MACBROKEN));
			}
			for(i = 0;i < 2;i++){
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_UNTOWED));
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_MACREPAIRED));
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_HAHA));
			}
			for(i = 0;i < 1;i++){
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_UNTOWED_MACREPAIRED));
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_UNTOWED_HAHA));
				this.deck.add(new Card(Command.CARDTYPE_ACTION,Command.ID_ACTION_MACREPAIRED_HAHA));
			}
		}else if(type == 2){
			for(i = 0;i < 5;i++){
				this.deck.add(new Card(Command.CARDTYPE_PATH ,5));
				this.deck.add(new Card(Command.CARDTYPE_PATH,6));
				this.deck.add(new Card(Command.CARDTYPE_PATH,8));
				this.deck.add(new Card(Command.CARDTYPE_PATH,9));
			}
			for(i = 0;i < 4;i++){
				this.deck.add(new Card(Command.CARDTYPE_PATH,4));
				this.deck.add(new Card(Command.CARDTYPE_PATH,7));
			}
			for(i = 0;i < 3;i++){
				this.deck.add(new Card(Command.CARDTYPE_PATH,10));
			}
			this.deck.add(new Card(Command.CARDTYPE_PATH,11));
			this.deck.add(new Card(Command.CARDTYPE_PATH,12));
			this.deck.add(new Card(Command.CARDTYPE_PATH,13));
			this.deck.add(new Card(Command.CARDTYPE_PATH,14));
			this.deck.add(new Card(Command.CARDTYPE_PATH,19));
			this.deck.add(new Card(Command.CARDTYPE_PATH,16));
			this.deck.add(new Card(Command.CARDTYPE_PATH,17));
			this.deck.add(new Card(Command.CARDTYPE_PATH,18));
			this.deck.add(new Card(Command.CARDTYPE_PATH,15));
		}else if(type == 3){
			for(i = 0;i < 16;i++){
				this.deck.add(new Card( Command.CARDTYPE_REWARD,Command.ID_REWARD_PASS));
			}
			for(i = 0;i < 8;i++){
				this.deck.add(new Card( Command.CARDTYPE_REWARD,Command.ID_REWARD_APLUS));
			}
			for(i = 0;i < 4;i++){
				this.deck.add(new Card(Command.CARDTYPE_REWARD,Command.ID_REWARD_RANK1));
			}
		}else if( type == 4 ){
			this.deck.add( new Card( Command.CARDTYPE_PATH, Command.ID_PATH_REALGOAL ) );
			this.deck.add( new Card( Command.CARDTYPE_PATH, Command.ID_PATH_FAKEGOAL1 ) );
			this.deck.add( new Card( Command.CARDTYPE_PATH, Command.ID_PATH_FAKEGOAL2 ) );
		}
	}
	Deck(){
		this.deck = new ArrayList<Card>();
	}
}