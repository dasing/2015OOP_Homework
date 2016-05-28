import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

public class core{

	static GUIController GUI;
	static ArrayList<Player> Players; //store all players
	static Shuffler shuffler;
	static Shuffler rewardShuffler;
	static Shuffler roleShuffler;
	static Shuffler boardShuffler;
	static Boolean SUCCESS;
	static int nRound;
	static Boolean drawCard; //if the deck still has card to draw
	static Card[][] board;
	static Boolean[][] cardTable;
	static ArrayList<Decision> lastRoundDecision;
 
	static Boolean checkPathValid(Card c,Point pos){


		System.out.println("in checkPathValid\n");
		System.out.printf("type = %d, feature = %d\n", c.type, c.feature );
		System.out.printf("Posx = %d, Posy = %d\n", pos.x, pos.y );
		if(c.isRotated == true)
			System.out.printf("rotate? = yes\n");
		else
			System.out.printf("not rotate\n");

		Point left = new Point( pos.x-1, pos.y );
		Point right = new Point( pos.x+1, pos.y );
		Point top = new Point( pos.x, pos.y-1 );
		Point bottom = new Point( pos.x, pos.y+1 );
		int connectedFlag = 0;
		Boolean leftF = cardTable[c.feature][3];
		Boolean bottomF = cardTable[c.feature][2];
		Boolean rightF = cardTable[c.feature][1];
		Boolean topF = cardTable[c.feature][0];

		if( c.type != Command.CARDTYPE_PATH )
			return false;
		else if( board[pos.x][pos.y].type != -1 )
			return false;

		//Error: should check all the neighbor card
		if( left.x >= 0 ){
			if( board[left.x][left.y].type != -1 ){
				if( Connected( board[left.x][left.y], c, 2 ) == false ){
					System.out.println("left didn't fit");
					return false;
				}else
					connectedFlag = 1;
			}
		}

		if( top.y >= 0 ){
			if( board[top.x][top.y].type != -1 ){
				if( Connected( board[top.x][top.y], c, 3 ) == false ){
					System.out.println("top didn't fit");
					return false;
				}else
					connectedFlag = 1;
			}
		}

		if( right.x <= 8 ){
			if( board[right.x][right.y].type != -1 ){
				if( Connected( board[right.x][right.y], c, 0 ) == false ){
					System.out.println("right didn't fit");
					return false;
				}else
					connectedFlag = 1;
			}
		}

		if( bottom.y <= 4 ){
			if( board[bottom.x][bottom.y].type != -1 ){
				if( Connected( board[bottom.x][bottom.y], c, 1 ) == false ){
					System.out.println("bottom didn't fit");
					return false;
				}else
					connectedFlag = 1;
			}
		}

		if( connectedFlag == 1 )
			return true;
		else{
			System.out.println("didn't near anything");
			return false;
		}	
	}

	static void ConstructCardTable(){

		cardTable = new Boolean[20][4];

		//[ top right bottom light ]

		for( int i=0; i<4; i++ ){
			cardTable[Command.ID_PATH_START][i] = true;
			cardTable[Command.ID_PATH_REALGOAL][i] = true;
			cardTable[Command.ID_PATH_FAKEGOAL1][i] = true;
			cardTable[Command.ID_PATH_FAKEGOAL2][i] = true;
		}

		
		//1010
		cardTable[Command.ID_PATH_PASS_1010][0] = true;
		cardTable[Command.ID_PATH_PASS_1010][1] = false;
		cardTable[Command.ID_PATH_PASS_1010][2] = true;
		cardTable[Command.ID_PATH_PASS_1010][3] = false;

		//1110
		cardTable[Command.ID_PATH_PASS_1110][0] = true;
		cardTable[Command.ID_PATH_PASS_1110][1] = true;
		cardTable[Command.ID_PATH_PASS_1110][2] = true;
		cardTable[Command.ID_PATH_PASS_1110][3] = false;

		//1111
		cardTable[Command.ID_PATH_PASS_1111][0] = true;
		cardTable[Command.ID_PATH_PASS_1111][1] = true;
		cardTable[Command.ID_PATH_PASS_1111][2] = true;
		cardTable[Command.ID_PATH_PASS_1111][3] = true;

		//0110
		cardTable[Command.ID_PATH_PASS_0110][0] = false;
		cardTable[Command.ID_PATH_PASS_0110][1] = true;
		cardTable[Command.ID_PATH_PASS_0110][2] = true;
		cardTable[Command.ID_PATH_PASS_0110][3] = false;

		//0011
		cardTable[Command.ID_PATH_PASS_0011][0] = false;
		cardTable[Command.ID_PATH_PASS_0011][1] = false;
		cardTable[Command.ID_PATH_PASS_0011][2] = true;
		cardTable[Command.ID_PATH_PASS_0011][3] = true;

		//1101
		cardTable[Command.ID_PATH_PASS_1101][0] = true;
		cardTable[Command.ID_PATH_PASS_1101][1] = true;
		cardTable[Command.ID_PATH_PASS_1101][2] = false;
		cardTable[Command.ID_PATH_PASS_1101][3] = true;

		//0101
		cardTable[Command.ID_PATH_PASS_0101][0] = false;
		cardTable[Command.ID_PATH_PASS_0101][1] = true;
		cardTable[Command.ID_PATH_PASS_0101][2] = false;
		cardTable[Command.ID_PATH_PASS_0101][3] = true;

		//0010
		cardTable[Command.ID_PATH_BLOCK_0010][0] = false;
		cardTable[Command.ID_PATH_BLOCK_0010][1] = false;
		cardTable[Command.ID_PATH_BLOCK_0010][2] = true;
		cardTable[Command.ID_PATH_BLOCK_0010][3] = false;

		//1011
		cardTable[Command.ID_PATH_BLOCK_1011][0] = true;
		cardTable[Command.ID_PATH_BLOCK_1011][1] = false;
		cardTable[Command.ID_PATH_BLOCK_1011][2] = true;
		cardTable[Command.ID_PATH_BLOCK_1011][3] = true;

		//1111
		cardTable[Command.ID_PATH_BLOCK_1111][0] = true;
		cardTable[Command.ID_PATH_BLOCK_1111][1] = true;
		cardTable[Command.ID_PATH_BLOCK_1111][2] = true;
		cardTable[Command.ID_PATH_BLOCK_1111][3] = true;

		//0110
		cardTable[Command.ID_PATH_BLOCK_0110][0] = false;
		cardTable[Command.ID_PATH_BLOCK_0110][1] = true;
		cardTable[Command.ID_PATH_BLOCK_0110][2] = true;
		cardTable[Command.ID_PATH_BLOCK_0110][3] = false;

		//0011
		cardTable[Command.ID_PATH_BLOCK_0011][0] = false;
		cardTable[Command.ID_PATH_BLOCK_0011][1] = false;
		cardTable[Command.ID_PATH_BLOCK_0011][2] = true;
		cardTable[Command.ID_PATH_BLOCK_0011][3] = true;

		//1010
		cardTable[Command.ID_PATH_BLOCK_1010][0] = true;
		cardTable[Command.ID_PATH_BLOCK_1010][1] = false;
		cardTable[Command.ID_PATH_BLOCK_1010][2] = true;
		cardTable[Command.ID_PATH_BLOCK_1010][3] = false;

		//1101
		cardTable[Command.ID_PATH_BLOCK_1101][0] = true;
		cardTable[Command.ID_PATH_BLOCK_1101][1] = true;
		cardTable[Command.ID_PATH_BLOCK_1101][2] = false;
		cardTable[Command.ID_PATH_BLOCK_1101][3] = true;

		//0101
		cardTable[Command.ID_PATH_BLOCK_0101][0] = false;
		cardTable[Command.ID_PATH_BLOCK_0101][1] = true;
		cardTable[Command.ID_PATH_BLOCK_0101][2] = false;
		cardTable[Command.ID_PATH_BLOCK_0101][3] = true;

		//0001
		cardTable[Command.ID_PATH_BLOCK_0001][0] = false;
		cardTable[Command.ID_PATH_BLOCK_0001][1] = false;
		cardTable[Command.ID_PATH_BLOCK_0001][2] = false;
		cardTable[Command.ID_PATH_BLOCK_0001][3] = true;

	}

	static Boolean checkFunctionValid( Card c, int receiver, int recvPos, Point pos ){
		//if receiver = 0, means no receiver. Ex: map card or SupidBird card

		 //System.out.printf("in checkFunctional, card type= %d, card feature = %d, receiver = %d, card pos = %d, %d, card recv pos = %d, \n", c.type, c.feature, receiver,pos.x, pos.y, recvPos );
		int feature = c.feature;
		if( c.type != Command.CARDTYPE_ACTION )
			return false;

		if( feature == Command.ID_ACTION_GPS  ){
			if( recvPos == -1 )
				return false;
			else
				return true;
		}else if( feature == Command.ID_ACTION_STUPIDBIRD ){

			if( pos.x == 0 ){
				if( pos.y == 2 ) //start point
					return false;
			}else if( pos.x == 8 ){
				if( pos.y == 0 || pos.y == 2 || pos.y == 4 ) //end point
					return false;
			}

			if( pos.x > 8 || pos.x < 0 )
				return false;

			if( pos.y > 4 || pos.y < 0 )
				return false;

			if( board[pos.x][pos.y].type == -1 ) //don't have card
				return false;

			if( pos.x == -1 && pos.y == -1 )
				return false;
			else
				return true;
		}

		if( feature >= 3 ){
			//help
			if( recvPos == 0 ){
				//towed
				if( ( feature == Command.ID_ACTION_UNTOWED ) ||
						( feature == Command.ID_ACTION_UNTOWED_MACREPAIRED ) ||
							( feature == Command.ID_ACTION_UNTOWED_HAHA ) ){
					if( Players.get(receiver).isTowed == true )
						return true;
					else{
						System.out.println("error 1");
						return false;
					}
				}else
					return false;
			}

			if( recvPos == 1 ){
				if( ( feature ==  Command.ID_ACTION_MACREPAIRED ) ||
							( feature == Command.ID_ACTION_UNTOWED_MACREPAIRED )||
							( feature == Command.ID_ACTION_MACREPAIRED_HAHA ) ){
					if( Players.get(receiver).isMacBroken == true )
						return true;
					else{
						System.out.println("error 2");
						return false;
					}
				}else
					return false;

			}
			
			if( recvPos == 2 ){

				if( feature == ( Command.ID_ACTION_HAHA  ) ||
						( feature == Command.ID_ACTION_UNTOWED_HAHA  ) ||
						 ( feature == Command.ID_ACTION_MACREPAIRED_HAHA ) ){
					if( Players.get(receiver).isHSUANTIEN == true )
						return true;
					else{
						System.out.println("error 3");
						return false;
					}
				}else
					return false;
			}
			
		}else{
			//do bad thing
			if( feature == Command.ID_ACTION_TOWED ){
				if( recvPos != 0 )
					return false;

				if( Players.get(receiver).isTowed == true )
					return false;
				else
					return true;
			}else if( feature == Command.ID_ACTION_MACBROKEN ){
				if( recvPos != 1 )
					return false;

				if( Players.get(receiver).isMacBroken == true )
					return false;
				else
					return true;
			}else if( feature == Command.ID_ACTION_HSUANTIEN ){
				if( recvPos != 2 )
					return false;

				if( Players.get(receiver).isHSUANTIEN == true )
					return false;
				else
					return true;
			}
		}

		System.out.println("Error");
		return false;

	}

	static void ConstructBoard(){

		board = new Card[9][5];
		for( int i=0 ;i<9; i++ ){
			for( int j=0; j<5; j++ ){
				board[i][j] = new Card();
			}
		}

		//Construct Start Card
		Card StartCard = new Card( Command.CARDTYPE_PATH, Command.ID_PATH_START  ); //Pass 1111
		
		board[0][2] = StartCard;
		boardShuffler = new Shuffler(3);
		boardShuffler.shuffle();
		for( int i=0; i<3; i++ ){
			board[8][2*i] = boardShuffler.getNext();
		}

	}

	static void initializeGame(){

		GUI = new GUIController();
		rewardShuffler = new Shuffler(2);
		nRound = 0;
		Players = new ArrayList<Player>();

		//initialzie Players
		int i=0 ;
		for( i=0; i<3; i++ ){
			Players.add( new ComPlayer(i) );
		} 
		Players.add( new Player() );
		
		ConstructCardTable();
	}

	static ArrayList<Card> DealCard( ArrayList<Card> handCard, int num, ArrayList<Integer> index ){

		if( num != index.size() )
			System.out.println("Error");

		for( int i=0; i<index.size(); i++ ){
			System.out.printf("index %d = %d\n", i, index.get(i) );
		}

		//Get num Cards
		for( int i=0, j=0; i<num && j<index.size(); i++, j++ ){

			Card newCard = shuffler.getNext();
			if( newCard != null ){
				handCard.add( index.get(j), newCard );
				System.out.printf("get card, type = %d, feature = %d\n", newCard.type, newCard.feature );
			}else{
				drawCard = false;
				break;
			}
		}

		return handCard;
	}

	static ArrayList<Card> DealCard( ArrayList<Card> handCard, int num ){

		//Get num Cards
		for( int i=0; i<num; i++ ){

			Card newCard = shuffler.getNext();
			//System.out.printf("get Card %d %d\n", newCard.type, newCard.feature );
			if( newCard != null )
				handCard.add( newCard );
			else{
				drawCard = false;
				break;
			}
		}

		return handCard;
	}

	static void FirstshulffleCard(){

		//each Player will get 6 cards first
		for( int i=0; i<4; i++ ){
			if( Players.get(i).handCard == null )
				System.out.printf("Error, handCard is null\n");
			Players.get(i).handCard = DealCard( Players.get(i).handCard, 6 );
		}


	}

	static void ChooseIdentity(){

		ArrayList<Integer> RoleCard = new ArrayList<Integer>();
		Random ran = new Random();

		//shuffle RoleCard
		roleShuffler = new Shuffler(0);
		roleShuffler.shuffle();
		for( int i=0; i<5; i++ ){
			Card curr = roleShuffler.getNext();
			RoleCard.add( curr.feature );
		}

		//check RoleCard
		for( int i=0; i<5; i++ ){
			System.out.printf("Role %d\n", RoleCard.get(i));
		}
		//Real user
		int choose = GUI.getPlayerRole( RoleCard );
		Players.get(Command.ID_PLAYER).setidentity( RoleCard.get(choose) );

		//Computer
		RoleCard.remove(choose);
		for( int i=0; i<3; i++ ){
			int num = ran.nextInt( RoleCard.size() );
			Players.get(i).setidentity( RoleCard.get(num) );
			RoleCard.remove(num);
		}

	}

	static Boolean checkEnd(){

		int idxRealGoal = -1;
		for( int i=0 ;i<3; i++ ){
			if( board[8][2*i].isOpen == false ){

				if( CheckIfCanOpen( 2*i ) == true ){
					board[8][2*i].isOpen = true;
					System.out.printf("open Card %d, feature is %d\n", i, board[8][2*i].feature );
					GUI.openGoal( i ,board[8][2*i].feature );
					if( board[8][2*i].feature == Command.ID_PATH_REALGOAL ){
						idxRealGoal = i;
					}
				}
			}
		}

		if( idxRealGoal != -1 ){

			if(  board[8][2*idxRealGoal].isOpen ){
				System.out.println("success\n");
				return true;
			}else
				return false;
		}else
			return false;
		
		

	}

	static Boolean CheckIfCanOpen( int index ){

		Card c = board[8][index];
		Point destination = new Point( 8, index );
		ArrayList<Point >path = new ArrayList<Point>();
		if ( Reachable( destination, path )  == true )
			return true;
		else
			return false;
		
	}

	static Boolean Reachable( Point pos, ArrayList<Point> path ){

		Card c = board[pos.x][pos.y];

		//avoid stackoverflow
		if( path.contains(pos) )
			return false;
		else
			path.add(pos);

		//debug
		for( int i=0; i<path.size(); i++ ){
			System.out.printf("Pos: %d %d\n", path.get(i).x, path.get(i).y );
		}

		//check is the card is a block
		if( c.feature > 10 )
			return false;

		if( c.feature == Command.ID_PATH_START )
			return true;

		if( pos.x > 0 ){
			Point left = new Point( pos.x-1, pos.y ) ;
			Card leftCard = board[left.x][left.y];
			if( leftCard.type != -1 ){
				if( Connected( c, leftCard, 0 )  == true ){
					if( Reachable(left, path) == true )
						return true;
				}
			}
			
			
		}

		if( pos.y > 0 ){
			Point top = new Point( pos.x, pos.y-1 );
			Card topCard = board[top.x][top.y];
			if( topCard.type != -1 ){
				if( Connected( c, topCard, 1 ) == true ){
					if( Reachable(top, path) == true )
						return true;
				}
			}
			
		}

		if( pos.x < 8 ){
			Point right = new Point( pos.x+1, pos.y );
			Card rightCard = board[right.x][right.y];
			if( rightCard.type != -1 ){
				if( Connected( c, rightCard, 2) == true ){
					if( Reachable(right, path) == true )
						return true;
				}	
			}
			
		}

		if( pos.y < 4 ){
			Point bottom = new Point( pos.x, pos.y+1 );
			Card bottomCard = board[bottom.x][bottom.y];
			if( bottomCard.type != -1 ){
				if( Connected( c, bottomCard, 3 ) == true ){
					if( Reachable(bottom, path) == true )
						return true;
				}
			}
			
		}
		return false;
			
		
	}

	static Boolean Connected( Card oriCard, Card newCard, int relativePos ){

		// relativePos: newCard relative to oriCard
		// left-> 0, top -> 1, right -> 2, down -> 3
		if( oriCard == null )
			System.out.printf("oriCard is null\n");

		if( newCard == null )
			System.out.println("newCard is null\n");

		int oriFeature = oriCard.feature;
		int newFeature = newCard.feature;
		Boolean oriRotated = oriCard.isRotated;
		Boolean newRotated = newCard.isRotated;

		System.out.printf("oriFeature=%d, newFeature=%d\n", oriFeature, newFeature );

		if( relativePos == 0 ){
			//left
			if( oriRotated && newRotated ){
				if( (cardTable[oriFeature][1] && cardTable[newFeature][3]) || (!cardTable[oriFeature][1] && !cardTable[newFeature][3]) )
					return true;
				else
					return false;
			}else if( !oriRotated && newRotated ){
				if( (cardTable[oriFeature][3] && cardTable[newFeature][3]) || (!cardTable[oriFeature][3] && !cardTable[newFeature][3]) )
					return true;
				else
					return false;
			}else if( oriRotated && !newRotated ){
				if( (cardTable[oriFeature][1] && cardTable[newFeature][1]) || (!cardTable[oriFeature][1] && !cardTable[newFeature][1]) )
					return true;
				else
					return false;
			}else{
				if( (cardTable[oriFeature][3] && cardTable[newFeature][1]) || (!cardTable[oriFeature][3] && !cardTable[newFeature][1]) )
					return true;
				else
					return false;
			}

		}else if( relativePos == 1 ){
			//top
			if( oriRotated && newRotated ){
				if( (cardTable[oriFeature][2] && cardTable[newFeature][0]) || (!cardTable[oriFeature][2] && !cardTable[newFeature][0]) )
					return true;
				else
					return false;
			}else if( oriRotated && !newRotated ){
				if( (cardTable[oriFeature][2] && cardTable[newFeature][2]) || (!cardTable[oriFeature][2] && !cardTable[newFeature][2]) )
					return true;
				else
					return false;
			}else if( !oriRotated && newRotated ){
				if( (cardTable[oriFeature][0] && cardTable[newFeature][0]) || (!cardTable[oriFeature][0] && !cardTable[newFeature][0]) )
					return true;
				else
					return false;
			}else{
				if( (cardTable[oriFeature][0] && cardTable[newFeature][2]) || (!cardTable[oriFeature][0] && !cardTable[newFeature][2]) )
					return true;
				else
					return false;
			}


		}else if( relativePos == 2 ){
			//right
			if( oriRotated && newRotated ){
				if( (cardTable[oriFeature][3] && cardTable[newFeature][1]) || (!cardTable[oriFeature][3] && !cardTable[newFeature][1]) )
					return true;
				else
					return false;
			}else if( oriRotated && !newRotated ){
				if( (cardTable[oriFeature][3] && cardTable[newFeature][3]) ||  (!cardTable[oriFeature][3] && !cardTable[newFeature][3]))
					return true;
				else
					return false;
			}else if( !oriRotated && newRotated ){
				if( (cardTable[oriFeature][1] && cardTable[newFeature][1]) || (!cardTable[oriFeature][1] && !cardTable[newFeature][1]) )
					return true;
				else
					return false;
			}else{
				if( (cardTable[oriFeature][1] && cardTable[newFeature][3]) || (!cardTable[oriFeature][1] && !cardTable[newFeature][3]) )
					return true;
				else
					return false;
			}

		}else{
			//down
			if( oriRotated && newRotated ){
				if( (cardTable[oriFeature][0] && cardTable[newFeature][2]) || (!cardTable[oriFeature][0] && !cardTable[newFeature][2]) )
					return true;
				else
					return false;
			}else if( oriRotated && !newRotated ){
				if( (cardTable[oriFeature][0] && cardTable[newFeature][0]) || (!cardTable[oriFeature][0] && !cardTable[newFeature][0]) )
					return true;
				else
					return false;
			}else if( !oriRotated && newRotated ){
				if( (cardTable[oriFeature][2] && cardTable[newFeature][2])  || (!cardTable[oriFeature][2] && !cardTable[newFeature][2])) 
					return true;
				else
					return false;
			}else{
				if( (cardTable[oriFeature][2] && cardTable[newFeature][0]) || (!cardTable[oriFeature][2] && !cardTable[newFeature][0]) )
					return true;
				else
					return false;
			}

		}
	}

	static void ChangePlayerStatus( Card c, int receiver, int reciPos ){

		System.out.printf("in ChangePlayerStatus, card type = %d, card feature = %d, receiver = %d, recvPos = %d\n", c.type, c.feature, receiver, reciPos );
		int feature = c.feature;

		if( feature >= 3 ){
			//help
			if( reciPos == 0 )
				Players.get(receiver).isTowed = false;
			else if( reciPos == 1 )
				Players.get(receiver).isMacBroken = false;
			else if( reciPos == 2 )
				Players.get(receiver).isHSUANTIEN = false;

		}else{
			//do bad thing
			if( reciPos == 0 ){
				Players.get(receiver).isTowed = true;

			}else if( reciPos == 1 ){
				Players.get(receiver).isMacBroken = true;

			}else if( reciPos == 2 ){
				Players.get(receiver).isHSUANTIEN = true; 
			}
		}
	} 

	static GUIUseCardType ChangeHandCardType( ArrayList<Card> handCard ){

		int size = handCard.size();
		GUIUseCardType GUIHandCard = new GUIUseCardType();
		ArrayList<Integer> type = new ArrayList<Integer>();
		ArrayList<Integer> feature = new ArrayList<Integer>();

		for( int i=0; i<size; i++ ){
			Card curr = handCard.get(i);
			type.add(curr.type);
			feature.add(curr.feature);
		}

		GUIHandCard.type = type;
		GUIHandCard.feature = feature;

		return GUIHandCard;

	}

	static void SeperateReward( int winID ){
 
 		Random  ran = new Random();
 		int badManID = -1;
 		ArrayList<Card> Rewards = new ArrayList<Card>();
 		ArrayList<Integer> PlayersRole = new ArrayList<Integer>();
 		ArrayList<Integer> winners = new ArrayList<Integer>();
 		System.out.printf("winID = %d\n", winID );
 		rewardShuffler.shuffle();
 		for( int i=0; i<4; i++ ){
 			Rewards.add(rewardShuffler.getNext());
 		}

 		for( int i=0; i<4; i++ ){
 			int identity = Players.get(i).getidentity();
 			if( identity == Command.ID_ROLE_SABOTEUR ){
 				badManID = i;
 			}
 		}

 		for( int i=0; i<4; i++ ){
 			int identity = Players.get(i).getidentity();
 			if( identity == Command.ID_ROLE_SABOTEUR ){
 				if( !SUCCESS )
 					winners.add(i);
 				else if( SUCCESS && winID == i )
 					winners.add(i);
 			}else{
 				if( SUCCESS && (winID != badManID ) )
 					winners.add(i);
 			}
 			PlayersRole.add( identity );
 		}
 
 		GUI.openRoleCards( PlayersRole );
 		GUIUseCardType guiCard = ChangeHandCardType(Rewards);

 		if( winners.size() == 0 ){
 			System.out.printf("no Winner\n");
 			GUI.dealRewards( -1, null, null );
 		}else{
 			System.out.printf("Winner:\n");
 			for( int j=0; j<winners.size(); j++ ){
 				System.out.printf("%d win\n", winners.get(j) );
 			}
 			GUI.dealRewards(winID, PlayersRole, guiCard.feature);
 			int idx = winners.indexOf(winID);
 			for( int i=0; i<Rewards.size(); i++ ){
 				int ptr = winners.get(idx%winners.size());
 				Players.get(ptr).addRewardCard( Rewards.get(i) );
 				idx++;
 			}
 		}

  	}

	static Point FunctionalAction( Card c, Point pos, int recvPos, int playerId ){

		if( c == null )
			System.out.println("card is null\n");
		if( pos == null )
			System.out.println("pos is null\n");
		System.out.printf("in FunctionalAction, card type= %d, card feature = %d, card pos = %d, %d, card recv pos = %d, playerId = %d\n", c.type, c.feature, pos.x, pos.y, recvPos, playerId );

		if( c.feature == Command.ID_ACTION_GPS ){

			ArrayList<Card> DestCards = boardShuffler.deck.deck;
			recvPos = pos.y/2;

			if( playerId == Command.ID_PLAYER ){
				System.out.println("haha");
				GUI.showMap( DestCards.get(recvPos).feature );

			}else{
				((ComPlayer)Players.get(playerId)).UseMap( recvPos ,DestCards.get(recvPos).feature );
				return new Point( recvPos, -1 );
			}

		}else if( c.feature == Command. ID_ACTION_STUPIDBIRD ){

			board[pos.x][pos.y] = new Card();
			return pos;
		}else{
			System.out.println("Bug!!");
			
		}

		return new Point(-1, -1);

	}

	static Boolean checkLose(){

		System.out.println("in checkLose\n");
		for( int i=0; i<4; i++ ){
			if( Players.get(i).handCard.size() != 0 )
				return false;
		}

		return true;
	}

	static ArrayList<Status> ConstructPlayerStatus(){

		ArrayList<Status> PlayerStatus = new ArrayList<Status>();
		for( int i=0; i<4; i++ ){

			Status curr = new Status();
			curr.playerID = i;
			ArrayList<Boolean> s = curr.s;

			if( Players.get(i).isTowed )
				s.add(true);
			else
				s.add(false);

			if( Players.get(i).isMacBroken )
				s.add(true);
			else
				s.add(false);

			if( Players.get(i).isHSUANTIEN )
				s.add(true);
			else
				s.add(false);

			PlayerStatus.add(curr);
		}

		return PlayerStatus;

	}

	static public void PrintBoard(){

		for( int i=0; i<5; i++ ){
			for( int j=0; j<9; j++ ){
				System.out.printf("%d ", board[j][i].type);
			}
			System.out.printf("\n");
		}

	}

	static public void initialzeRound(){

		GUI.clearCards();
		for( int i=0; i<4; i++ ){
			Players.get(i).isTowed = false;
			Players.get(i).isMacBroken = false;
			Players.get(i).isHSUANTIEN = false;
		}
		//shuffle card
		shuffler = new Shuffler(1);
		shuffler.shuffle();
		drawCard = true;

	}

	public static void main(String[] args){

		initializeGame();

		while( nRound != 1 ){

			initialzeRound();
			
			int winID = 0;
			boolean isEnd = false;
			lastRoundDecision = new ArrayList<Decision>();

			ChooseIdentity();
			ConstructBoard();
			FirstshulffleCard();
			
			GUIUseCardType guiCard = ChangeHandCardType(Players.get(Command.ID_PLAYER).handCard );
			GUI.dealCards(  guiCard.type, guiCard.feature );
			GUI.startRound();
			
			while( isEnd != true ){

				ArrayList<Status> playerStatus;
				ArrayList<Decision> playersDecision = new ArrayList<Decision>();
				for( int i=0; i<4; i++ ){

					Decision playerDecision = new Decision(); 
					playerStatus = ConstructPlayerStatus();

					//check if the player still have card
					if( Players.get(i).handCard.size() == 0 )
						continue;

					//Is player i's turn.
					if( i == 3 ){
						boolean finish = false;
						
						while( finish != true ){

							PlayerAction curr = new PlayerAction();
							curr = GUI.getPlayerAction(); 

							if( curr.isDrop == false ){
								ArrayList<Integer> givenCards = curr.discardIndices;
								Players.get(i).dropCard( givenCards );

								if( drawCard == true ){
									DealCard( Players.get(i).handCard, givenCards.size(), givenCards );
								}

								finish = true;

								playerDecision.isDrop = false;
								playerDecision.playerid = i;
								playerDecision.receiverId = -1;
								playersDecision.add(playerDecision);
								

							}else{

								//Change GUICard to Card type
								Card newCard = new Card( curr.cardType, curr.cardFeature, curr.isRotated );
								System.out.printf("DropCard, card Type = %d, cardFeature = %d\n", curr.cardType, curr.cardFeature);

								if( curr.cardType == Command.CARDTYPE_PATH ){

									if( Players.get(i).isMovable() == false ){
										System.out.println("unmovable\n");
										continue;
									}
									
									//player use path card
									if( checkPathValid( newCard, curr.pos ) ){
										//Update board
										// System.out.printf("before\n");
										// PrintBoard();

										board[curr.pos.x][curr.pos.y] = newCard;
										finish = true;

										// System.out.printf("after\n");
										// PrintBoard();

										playerDecision.isDrop = true;
										playerDecision.type = Command.CARDTYPE_PATH;
										playerDecision.pos = curr.pos;
										playerDecision.card = newCard;
										playerDecision.playerid = i;
										playerDecision.receiverId = -1;
										playersDecision.add(playerDecision);
									

										System.out.println("Player drop path card success");
										
									}else
										continue;

								}else if( curr.cardType == Command.CARDTYPE_ACTION ){
									//player use function card
									if( checkFunctionValid( newCard, curr.recvID, curr.recvPos, curr.pos ) == true ){

										if( curr.recvID == -1 ){

											playerDecision.isDrop = true;
											playerDecision.type = Command.CARDTYPE_ACTION;
											if( newCard.feature == Command.ID_ACTION_STUPIDBIRD )
												playerDecision.card = newCard;
											else
												playerDecision.recvPos = curr.recvPos;
										
											playersDecision.add(playerDecision);
											

											FunctionalAction( newCard, curr.pos, curr.recvPos, i );
											finish = true;
											
										}else{
											ChangePlayerStatus( newCard, curr.recvID, curr.recvPos );
											finish = true;

											playerDecision.isDrop = true;
											playerDecision.type = Command.CARDTYPE_ACTION;
											playerDecision.playerid = i;
											playerDecision.receiverId = curr.recvID;
											playerDecision.recvPos = curr.recvPos;
											playerDecision.card = newCard;
											playersDecision.add(playerDecision);
											
										}

									}else{
										System.out.println("Action inValid");
										continue;
									}
								}

								//Deal Card
								ArrayList<Integer> dropIndices = new ArrayList<Integer>();
								dropIndices.add(curr.dropIndex);
								Players.get(i).dropCard(dropIndices);
								if( drawCard == true )
									DealCard( Players.get(i).handCard, 1, dropIndices );
								// for( int j=0; j<Players.get(i).handCard.size();j++ ){
								// 	System.out.printf("%d %d\n", Players.get(i).handCard.get(j).type, Players.get(i).handCard.get(j).feature );
								// }

							}
						}
						
						guiCard = ChangeHandCardType( Players.get(Command.ID_PLAYER).handCard );
						GUI.updatePlayer( guiCard.type, guiCard.feature );

						// System.out.println("print playersDecision");
						// for( int k=0; k<playersDecision.size(); k++  ){
						// 	System.out.printf("Card Type = %d, Feature = %d\n", playersDecision.get(k).card.type, playersDecision.get(k).card.feature );
						// }
						// System.out.printf("\n");

						lastRoundDecision = new ArrayList<Decision>(playersDecision);
						playersDecision.clear();

					}else{

							boolean finish = false;
							while( finish != true ){

								//print for debug
								// System.out.println("print lastRoundDecision");
								// for( int k=0; k<lastRoundDecision.size(); k++  ){
								// 	System.out.printf("Card Type = %d, Feature = %d\n", lastRoundDecision.get(k).card.type, lastRoundDecision.get(k).card.feature );
								// }

								Decide decision = ((ComPlayer)Players.get(i)).decide( lastRoundDecision ,board, playerStatus );

								System.out.println("Print Player Status");
								for( int j=0; j<4; j++ ){
									if( Players.get(j).isTowed )
										System.out.printf("1 ");
									else
										System.out.printf("0 ");
									if( Players.get(j).isMacBroken )
										System.out.printf("1 ");
									else
										System.out.printf("0 ");
									if( Players.get(j).isHSUANTIEN )
										System.out.printf("1\n");
									else
										System.out.printf("0\n");
								}


								if( decision.isDrop == false ){
									
									ArrayList<Card> givenCards = decision.discardCards;
									int remain_size = Players.get(i).handCard.size();
									System.out.printf("remain CardSize = %d\n", remain_size );
									System.out.printf("Player Discard %d Card(s)\n", givenCards.size() );
									// if( givenCards == null )
									// 	System.out.println("givenCards is null");
									for( int j=0; j<givenCards.size(); j++ ){
										Players.get(i).dropCard( givenCards.get(j) );
									}

									PlayerAction Curr = new PlayerAction();
									Curr.isDrop = false;
									Curr.discardSize = givenCards.size();
									GUI.setAIAction(Curr, i );

									if( drawCard == true ){
										DealCard( Players.get(i).handCard, givenCards.size() );
									}

									finish = true;
									playerDecision.isDrop = false;
									playerDecision.playerid = i;
									playerDecision.receiverId = -1;
									playersDecision.add(playerDecision);
									


								}else{

									System.out.printf("dropCard %d %d\n", decision.card.getType(), decision.card.getFeature() );
									System.out.printf("decision type = %d\n", decision.type );
									if( decision.type == Command.CARDTYPE_PATH ){

										if( Players.get(i).isMovable() == false )
											continue;

										//player use path card
										if( checkPathValid( decision.card, decision.pos ) ){
											//Update board
											board[decision.pos.x][decision.pos.y] = decision.card;
											finish = true;

											playerDecision.isDrop = true;
											playerDecision.type = Command.CARDTYPE_PATH;
											playerDecision.pos = decision.pos;
											playerDecision.card = decision.card;
											playerDecision.playerid = i;
											playerDecision.receiverId = -1;
											playersDecision.add(playerDecision);
											
										}else
											continue;

										PlayerAction Curr = new PlayerAction();
										Curr.isDrop = true;
										Curr.cardType = decision.card.type;
										Curr.cardFeature = decision.card.feature;
										Curr.pos = decision.pos;
										Curr.isRotated = decision.card.isRotated;
										System.out.println("drop path card success\n");
										GUI.setAIAction( Curr , i );

									}else if( decision.type == Command.CARDTYPE_ACTION ){
										//player use function card
										Point recvPos = new Point( -1, -1 );
										if( decision.receiverId == -1 ){
											//handle playerDecision
											playerDecision.isDrop = true;
											playerDecision.type = Command.CARDTYPE_ACTION;
											if( decision.card.feature == Command.ID_ACTION_STUPIDBIRD )
												playerDecision.card = board[decision.pos.x][decision.pos.y];

											recvPos = FunctionalAction( decision.card, decision.pos, decision.recvPos, i );

											if( decision.card.feature == Command.ID_ACTION_GPS )
												playerDecision.recvPos = recvPos.x;

											playerDecision.playerid = i;
											playerDecision.receiverId = -1;
											playersDecision.add(playerDecision);
												

										}else{

											if( checkFunctionValid( decision.card, decision.receiverId, decision.recvPos, decision.pos  ) == true ){
												ChangePlayerStatus( decision.card, decision.receiverId, decision.recvPos );

												
												recvPos.x = decision.recvPos;

												playerDecision.isDrop = true;
												playerDecision.type = Command.CARDTYPE_ACTION;
												playerDecision.receiverId = decision.receiverId;
												playerDecision.recvPos = decision.recvPos;
												playerDecision.card = decision.card;
												playerDecision.playerid = i;
												playersDecision.add(playerDecision);
												
											
											}else
												continue;
										}

										PlayerAction Curr = new PlayerAction();
										Curr.isDrop = true;
										Curr.cardType = decision.card.type;
										Curr.cardFeature = decision.card.feature;
										Curr.recvID = decision.receiverId;

										if( recvPos.y == -1 )
											Curr.recvPos = recvPos.x;
										else
											Curr.pos = recvPos;

										System.out.println("drop action card success");
										GUI.setAIAction( Curr, i );

									}

									finish = true;
									Players.get(i).dropCard( decision.card );
									if( drawCard == true )
										DealCard( Players.get(i).handCard, 1 );
								}
								
							}

					}
					
					if( checkLose() == true ){
						//find bad people
						SUCCESS = false;
						for( int j=0; j<4; j++ ){
							if( Players.get(j).getidentity() == Command.ID_ROLE_SABOTEUR ){
								winID = j;
								break;
							}
						}
						isEnd = true;
						System.out.println("FAILURE");
						break;
					}

					if( checkEnd() == true ){
						SUCCESS = true;
						isEnd = true;
						winID = i;
						System.out.printf("winID = %d\n", winID );
						break;
					}

				}

			}

			SeperateReward( winID );
			nRound++;
		}


		System.out.println("The End");
		GUIUseCardType gui1 = ChangeHandCardType(Players.get(0).getRewardCardList());
		GUIUseCardType gui2 = ChangeHandCardType(Players.get(1).getRewardCardList());
		GUIUseCardType gui3 = ChangeHandCardType(Players.get(2).getRewardCardList());
		GUIUseCardType gui4 = ChangeHandCardType(Players.get(3).getRewardCardList());

		GUI.showRank( gui1.feature, gui2.feature, gui3.feature, gui4.feature );

	}

}