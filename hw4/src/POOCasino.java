//package foop;

import foop.*;
import java.util.Vector;
import java.util.ArrayList;
import java.lang.Integer;

class PlayerStatus{
	boolean IsAalive;
	int id;
	int AliveId;
	int DeadRound;

	public PlayerStatus( int i ){
		IsAalive = true;
		id = i;
		AliveId = i;
		DeadRound = 0;
	}
}

class Player_inform{

	int bet;
	int id;
	boolean isSplit;
	boolean insurance;
	boolean surrender;
	boolean stand;
	boolean isBusted;
	boolean isBlackJack;
	boolean isDouble;
	Hand handCards;

	public Player_inform(int i ){

		id = i;
		bet = 0;
		insurance = false;
		surrender = false;
		stand = false;
		isBusted = false;
		isBlackJack = false;
		isDouble = false;

	}
}

public class POOCasino{

	public static final String MyPlayer = "PlayerB01902135";
	public static final String OtherPlayer1 = "PlayerB01902058"; 
	public static final String OtherPlayer2 = "PlayerB01902080"; 
	static int nRound = 0;
	static int nChip = 0;
	static int nPlayer = 0;
	static ArrayList<Player> Players;
	static ArrayList<PlayerStatus> PlayersStatus;
	static ArrayList<Player_inform> PlayersInform;
	static ArrayList<Hand> CurrTable;	
	static ArrayList<Integer> BankRupt;
	static ArrayList<Hand> LastTable;
	static Card[] deck;
	static Shuffler shuffler;
	static Dealer dealer;
	static int OriPlayer;

	static void ReadCommandLine( String[] args ){

		nRound = Integer.parseInt(args[0]);
		nChip = Integer.parseInt(args[1]);

		for( int i=2; i < args.length; i++ ){

			OriPlayer++;

			try{

				Player newPlayer = (Player)Class.forName(args[i]).getConstructor(Integer.TYPE).newInstance(nChip);
				Players.add(newPlayer);

			}catch( Exception e ){
				
				continue;
			}
			

			// if( args[i].equals(MyPlayer) ){
			// 	Players.add( new PlayerB01902135(nChip) );	
			// }else if(args[i].equals(OtherPlayer1) ){
			// 	Players.add( new PlayerB01902058(nChip) );
			// }
			

		}
	}

	static void InitializeDeck(){

        for( int i=1; i<=52; i++ ){

        	int value = i%13;
        	Card new_card;

        	if( value == 0 ) value = 13;
        	        	
        	if( i/13 == 0 )	 		new_card = new Card( Card.SPADE , (byte)value );           	
            else if( i/13 == 1 )	new_card = new Card( Card.HEART , (byte)value );
            else if( i/13 == 2 )	new_card = new Card( Card.DIAMOND , (byte)value );
            else					new_card = new Card( Card.CLUB , (byte)value );
            	
            deck[i-1] = new_card;
        }
	}

	static public void PlayersMakeBet(){

		//System.out.printf("inPlayerMakeBet\n");
		int nPlayer = Players.size();

		//make bet
		for( int i=0; i<nPlayer; i++ ){
			
			int bet = Players.get(i).make_bet( LastTable, nPlayer, i );
			System.out.printf("Player %d make bet = %d\n", i, bet );

			while ( DECREASE( i, bet ) == -1 ){
				System.out.printf("You don't have enough money ! Make Bet again ! \n");
				bet = Players.get(i).make_bet( LastTable, nPlayer, i );
				//System.out.printf("bet = %d\n", bet );
			}
			
			PlayersInform.get(i).bet = bet;
		}
	}

	static public ArrayList<Card> AssignCard( int number, ArrayList<Card> Curr ){

		for( int i=0; i<number; i++ ){
			Curr.add( shuffler.getNext(deck) );
		}
		return Curr;
	}

	static public void AssignFirstTwoCards(){

		int size = PlayersInform.size();

		for( int i=0; i<size; i++ ){

			PlayersInform.get(i).id = i;
			ArrayList<Card> temp = new ArrayList<Card>();
			temp = AssignCard( 2, temp );
			PlayersInform.get(i).handCards = new Hand(temp);

		}

		ArrayList<Card> temp = new ArrayList<Card>();
		temp = AssignCard( 2, temp );
		dealer.handCards = new Hand(temp);

	}

	static public boolean IF_CAN_BUY_INSURANCE(){

		/*
			If the dealer’s face-up card is ACE, ask each player whether to buy an insurance of 1/2Bi or not
		*/

		if(  dealer.handCards.getCards().get(0).getValue() == 1 ){
			//System.out.printf("CAN BUY INSURANCE\n");
			return true;
		}else
			return false;

	}

	static public boolean IF_CAN_SURRENDER(){

		/*
			Check dealer’s face-down card (hole card), and if the dealer does not get a Blackjack, ask each player
			whether to surrender or not.
		*/

		if( dealer.countValue() == 21 ){
			System.out.printf("Dealer Get a Blackjack!\n");
			dealer.isBlackJack = true;
			return false;
		}else
			return true;
	}

	static public void ConstructCurrTable( int i ){

		CurrTable.clear();
		int size = PlayersInform.size();

		for( int j=0; j<size; j++ ){
			if( j == i )
				continue;
			else{
				CurrTable.add( PlayersInform.get(i).handCards );
			}
		}

	}

	static public boolean DoDouble( int i, int j ){


		ConstructCurrTable( j );
		if( Players.get(i).do_double( PlayersInform.get(j).handCards, dealer.handCards.getCards().get(0), CurrTable )  ){

			if( DECREASE(i, (double)PlayersInform.get(j).bet ) > 0 ){
				System.out.printf("Player %d do double\n", i );
				PlayersInform.get(j).bet*=2;
				ArrayList<Card> tmp = AssignCard( 1, PlayersInform.get(j).handCards.getCards() );
				PlayersInform.get(j).handCards = new Hand( tmp );
				PlayersInform.get(j).stand = true;
				PlayersInform.get(j).isDouble = true;
				return true;
			}else{
				System.out.printf("You don't have enough money, can't do double\n");
				INCREASE(i, (double)PlayersInform.get(j).bet );
				return false;
			}

		}else{			
			return false;
		}
				
	}

	static void ShowPlayerStatus( int i ){

		if( i == -1 ){
			System.out.println("Dealer's Card: ");
			ArrayList<Card> card = dealer.handCards.getCards();
			for( int j=0; j<card.size() ; j++ ){
				System.out.printf("   %d %d\n", card.get(j).getSuit(), card.get(j).getValue() );
			}
			System.out.printf("value = %d\n", dealer.total );
		}else{
			System.out.printf("Player %d\n", i );
			int size = PlayersInform.size();
			for( int j=0; j<size; j++ ){
				if( PlayersInform.get(j).id == i ){
					ArrayList<Card> curr = PlayersInform.get(j).handCards.getCards();

					for( int k=0; k<curr.size(); k++ ){
						System.out.printf("   %d %d\n", curr.get(k).getSuit(), curr.get(k).getValue() );
					}
					System.out.printf("value = %d\n", countValue(curr) );
					System.out.println("---------------------------------------");
				}
			}


		}
		System.out.println("******************************************");
	}

	static public void KeepAssignCard( int i, int j ){

		//System.out.printf("in KeepAssignCard\n");

		if( i == -1 && j == -1 ){

			dealer.makeDecisions();
			while( dealer.stand == false ){
				ArrayList<Card> tmp = AssignCard(  1, dealer.handCards.getCards() );
				dealer.handCards = new Hand(tmp);
				dealer.makeDecisions();
			}

			return;
		}
		
		ConstructCurrTable( j );
		while( PlayersInform.get(j).stand == false ){
			
			if( Players.get(i).hit_me( PlayersInform.get(j).handCards, dealer.handCards.getCards().get(0), CurrTable ) == true ){
				ArrayList<Card> tmp =  AssignCard( 1, PlayersInform.get(j).handCards.getCards() );
				PlayersInform.get(j).handCards = new Hand(tmp);
			}else{
				PlayersInform.get(j).stand = true;
			}
		}

	}

	static public int countValue( ArrayList<Card> Curr_Cards ){

		int size = Curr_Cards.size();
		int a_flag = 0;
		int value = 0;

		for( int i=0; i < size; i++ ){
			int curr_value = Curr_Cards.get(i).getValue();
			if( curr_value > 10 ) curr_value = 10;
			if( curr_value == 1 ) { a_flag = 1; curr_value = 11; }
			value += curr_value;
		}

		if( value > 21 && a_flag == 1 ) value-=10;
		if( value == 21 ){
			if( a_flag == 1 && size == 2 )
				return -1;
		}

		return value;
	}

	static public void CheckBankRupt(){

		//System.out.printf("CheckBankRupt\n");

		//chexk if go banrupt, in function DECREASE, if type is 2, if you don't have enough money, 
		int nPlayer = Players.size();
		for( int i=0; i<nPlayer; i++ ){

			if ( DECREASE( i, 1 )  == -1 ){
				BankRupt.add(i);
			}else
				INCREASE( i, 1 );
		}

		return;
	}

	static public void computeResult(){

		/*Debug*/
		ShowPlayerStatus( -1 );
		for( int i=0; i<Players.size(); i++ ){
			ShowPlayerStatus(i);
		}

		int dealerValue = dealer.total;
		int nPlayer = PlayersInform.size();

		if( dealerValue > 21 ) dealer.isBusted = true;

		for( int i=0; i<nPlayer; i++ ){

			int PlayerValue = countValue( PlayersInform.get(i).handCards.getCards() );
			int PlayerIdx = PlayersInform.get(i).id;

			if( PlayersInform.get(i).surrender == true ){

				System.out.printf("Player %d Surrender\n", PlayersInform.get(i).id );
				// - 1/2Bi + Bi( original bet ) = +1/2 Bi
				INCREASE( PlayerIdx, (double)PlayersInform.get(i).bet/2  );  
				continue;

			}else if( PlayerValue > 21 ){
				//busted
				System.out.printf("Player %d Busted\n", PlayersInform.get(i).id );

				if( dealer.isBlackJack == true ){

					if( PlayersInform.get(i).insurance == true ){
						
						if( PlayersInform.get(i).isDouble == true )
							INCREASE( PlayerIdx, (double)PlayersInform.get(i).bet/2 );
						else
							INCREASE( PlayerIdx, (double)PlayersInform.get(i).bet );

						System.out.printf("Dealer get BJ, Player %d Get INSURANCE Back\n", PlayersInform.get(i).id );
						continue;
					}
				}

				continue;

			}else if( PlayerValue == -1 ){
				//get BlackJack
				PlayersInform.get(i).isBlackJack = true;

				System.out.printf("Player %d get BlackJack\n", PlayersInform.get(i).id );
				if( dealer.isBlackJack == false )
					INCREASE( PlayerIdx, (double)(PlayersInform.get(i).bet*5)/2 ); // 3/2+1 = 5/2
				continue;

			}else{

				if( dealer.isBusted == true ){
					System.out.printf("Dealer is busted\n");
					INCREASE( PlayerIdx, (double)PlayersInform.get(i).bet*2  );
					continue;
				}

				if( dealer.isBlackJack == true ){

					if( PlayersInform.get(i).insurance == false ){
						//Lose Bi to Cosino
						continue;
					}else{
						System.out.printf("Player %d Get INSURANCE Back\n", PlayersInform.get(i).id );
						if( PlayersInform.get(i).isDouble == true )
							INCREASE( PlayerIdx, (double)PlayersInform.get(i).bet/2 );
						else
							INCREASE( PlayerIdx, (double)PlayersInform.get(i).bet );
						continue;
					}
				}

				if( dealerValue > PlayerValue ){
					//Lose Bi to Cosino
					System.out.printf("Player %d lose Dealer\n", PlayersInform.get(i).id );
					continue;
				}else if( dealerValue < PlayerValue ){
					System.out.printf("Player %d win Dealer\n", PlayersInform.get(i).id );
					INCREASE( PlayerIdx, (double)PlayersInform.get(i).bet*2 );
					continue;
				}else{
					System.out.printf("Push, get bet back! \n");
					INCREASE( PlayerIdx, (double)PlayersInform.get(i).bet );
					continue;
				}
			}
		}
	}

	static public int DECREASE( int id, double value ){

		//System.out.printf("Player %d Decrease %f\n", id, value );
		try{
			Players.get(id).decrease_chips( value );
			//System.out.printf("Player %d remain %s\n", id, Players.get(id).toString() );
			return 1;
		}
		catch( Player.NegativeException ex ){
			System.out.println("Error! Diff is Negative! ");
			return -2;
		}
		catch( Player.BrokeException ex2 ){
			System.out.println("Error! You don't have enough money! ");
			return -1;
		}
		

	}

	static public void INCREASE( int id, double value ){

		//System.out.printf("Player %d Increase %f\n", id, value );

		try{
			Players.get(id).increase_chips( value );
		}
		catch( Player.NegativeException ex ){
			System.out.println("Error! Diff is Negative! ");
			return;
		}
		//System.out.printf("Player %d remain %s\n", id, Players.get(id).toString() );
		
	}

	static public void ConstructPlayersStatus( int nPlayer ){

		for( int i=0; i<nPlayer; i++ ){
			PlayerStatus temp = new PlayerStatus(i);
			PlayersStatus.add(temp);
		}

	}

	public static void ShowCurrPlayerStatus(){

		for( int n=0; n<OriPlayer; n++ ){
			System.out.printf("Player %d ", PlayersStatus.get(n).id );
			if( PlayersStatus.get(n).IsAalive == true ){
				System.out.printf("remain %s\n", Players.get(PlayersStatus.get(n).AliveId).toString() );
			}else{
				System.out.printf("dead at Round %d\n", PlayersStatus.get(n).DeadRound );
			}
		}
	}

	static public void UpdatePlayerStatus(){

		int count = 0;
		for( int n=0; n<OriPlayer; n++ ){
			if( PlayersStatus.get(n).IsAalive == true ){
				PlayersStatus.get(n).AliveId = count;
				Player_inform NewPlayersInform = new Player_inform(count);
				PlayersInform.add(NewPlayersInform);
				count++;
			}
		}

		ShowCurrPlayerStatus();

		if( count != Players.size() ){
			System.out.println("Error!");
		}else{
			System.out.printf("%d Players this Round\n", count );
		}

	}

	public static void ShowCurrAllCard(){

		System.out.printf("Dealer: \n");
		ArrayList<Card> DealerCard = dealer.handCards.getCards();
		for( int j=0; j<DealerCard.size() ;j++ ){
			System.out.printf("    %d %d \n", (int)DealerCard.get(j).getSuit(), (int)DealerCard.get(j).getValue() );
		}

		for( int i=0; i<Players.size(); i++ ){
			System.out.printf("Player %d \n", PlayersStatus.get(i).id );
			ArrayList<Card> Cards = PlayersInform.get(i).handCards.getCards();
			for( int j=0; j<Cards.size(); j++ ){
				System.out.printf("    %d %d \n", (int)Cards.get(j).getSuit(), (int)Cards.get(j).getValue() );
			}
		}
	}

	public static void ShowDeck(){

		for( int i=0; i<52; i++ ){
			System.out.printf("%d %d \n", deck[i].getSuit(), deck[i].getValue() );
		}

	}

	public static void ConstructLastTable(){

		LastTable.clear();
		int size = PlayersInform.size();
		for( int i=0; i<size; i++ ){
			LastTable.add( PlayersInform.get(i).handCards );
		}
	}

	

	public static void main( String[] args ){

		//initialize
		Players = new ArrayList<Player>();
		shuffler = new Shuffler();
		deck = new Card[52];
		PlayersStatus = new  ArrayList<PlayerStatus>();
		CurrTable = new ArrayList<Hand>();
		LastTable = new ArrayList<Hand>();

		ReadCommandLine( args );
		ConstructPlayersStatus( Players.size() );
		InitializeDeck();
				
		for( int R =0; R < nRound; R++ ){

			//initialize
			PlayersInform = new ArrayList<Player_inform>();
			BankRupt = new ArrayList<Integer>();
			dealer = new Dealer();
			nPlayer = Players.size();
			PlayersInform.clear();

			UpdatePlayerStatus();

			shuffler.Shuffle_Card(deck);
			//ShowDeck();

			PlayersMakeBet();
			ShowCurrPlayerStatus();
			AssignFirstTwoCards();
			ShowCurrAllCard();
			
			//System.out.printf("HANDLE INSURANCE\n");
			if( IF_CAN_BUY_INSURANCE() ){
				//buy insurance
				for( int i=0; i<nPlayer; i++ ){

					ConstructCurrTable( i );

					if( Players.get(i).buy_insurance( PlayersInform.get(i).handCards.getCards().get(0), dealer.handCards.getCards().get(0), CurrTable ) ){
						System.out.printf("Player %d buy insurance\n", i );
						if ( DECREASE( i, (double)PlayersInform.get(i).bet/2 ) > 0  ){
							PlayersInform.get(i).insurance = true;
						}else{
							INCREASE( i, (double)PlayersInform.get(i).bet/2 );
							System.out.printf("Player %d Don't have enough money to buy insurance\n", i );
						}	
					}
				}
			}

			//System.out.printf("HANDLE SURRENDER\n");
			if( IF_CAN_SURRENDER() ){
				for( int i=0; i<nPlayer; i++ ){
					ConstructCurrTable(i);
					if ( Players.get(i).do_surrender( PlayersInform.get(i).handCards.getCards().get(0), dealer.handCards.getCards().get(0), CurrTable ) ){
						PlayersInform.get(i).surrender = true;
						System.out.printf("Player %d surrender\n", i );
					}
				}
			}

			for( int i=0, j=0; i < nPlayer ; i++, j++ ){

				//System.out.printf("handle Player %d, PlayerInform %d\n", i, j );

				if( PlayersInform.get(j).surrender == true )
					continue;

				boolean isSplit = false;

				ConstructCurrTable( i );

				//split
				//System.out.printf("HANDLE SPLIT\n");
				if (  Players.get(i).do_split( PlayersInform.get(j).handCards.getCards() , dealer.handCards.getCards().get(0), CurrTable ) == true ){

					if( DECREASE( i, (double)PlayersInform.get(j).bet ) > 0 ){

						isSplit = true;

						//Create new Player_inform
						Player_inform newInform = new Player_inform(PlayersInform.get(j).id); 
						newInform.bet = PlayersInform.get(j).bet;
						newInform.insurance = PlayersInform.get(j).insurance;

						//Add card to the new HandCards
						ArrayList<Card> tmp = new ArrayList<Card>();
						tmp.add(PlayersInform.get(j).handCards.getCards().get(1));
						newInform.handCards = new Hand(tmp);

						//Remove the Card of Original
						tmp = PlayersInform.get(j).handCards.getCards();
						tmp.remove(1);
						PlayersInform.get(j).handCards = new Hand(tmp);

						//Assign Card to Original and the New
						tmp = AssignCard( 1, newInform.handCards.getCards() );
						newInform.handCards = new Hand(tmp);
						tmp = AssignCard( 1, PlayersInform.get(j).handCards.getCards() );
						PlayersInform.get(j).handCards = new Hand(tmp);

						//Add new Player_inform to PlayersInform
						PlayersInform.add( j+1, newInform );

					}else{

						INCREASE( i, (double)PlayersInform.get(j).bet );
						System.out.printf("Player %d don't have enough money, can't split\n", i );
					}

				}

				//do double
				if( isSplit == true ){

					//System.out.printf("SPLIT\n");
					if ( DoDouble( i, j )  == false ) 
						KeepAssignCard( i, j );

					if( DoDouble( i, j+1 ) == false )
						KeepAssignCard( i, j+1 );

					j++;

				}else{
					//System.out.printf("Didn't SPLIT\n");
					if( !DoDouble( i, j ) )
						KeepAssignCard( i, j );
				}
			}

			//It is dealer's turn
			KeepAssignCard( -1, -1 );
			computeResult();
			CheckBankRupt();

			//Update PlayersStatus
			int BankRuptSize = BankRupt.size();
			for( int i = BankRuptSize-1; i >= 0 ; i-- ){
				
				for( int  n=0; n<OriPlayer; n++ ){
					if( PlayersStatus.get(n).AliveId == BankRupt.get(i) ){
						PlayersStatus.get(n).IsAalive = false;
						PlayersStatus.get(n).DeadRound = R;
						PlayersStatus.get(n).AliveId = -1;
						System.out.printf("Player %d Dead\n", PlayersStatus.get(n).id );
						break;
					}
				}
				
				//remove the dead player from alive player list
				Players.remove( (int)BankRupt.get(i) );
			}

			ConstructLastTable();
			

			System.out.printf("Finish Round %d\n", R );

			if( Players.size() == 0 ){
				System.out.printf("All Players BankRupt, Game Over!");
				break;
			}else{
				System.out.printf("Remain %d Players\n", Players.size() );
			}

		}

		UpdatePlayerStatus();
		ShowCurrPlayerStatus();

	}
}