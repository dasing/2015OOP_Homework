import java.awt.Point;

class Decision{
	int playerid;
	Boolean isDrop;
	int type; //type 1 -> using path card, type2 -> using function card

	//type 1
	Point pos;
	//type 2
	int receiverId;//no receiver return -1
	int recvPos;//0 towed 1 computer 2 huish

	Card card;
	Decision(){
		card = new Card();
	}

	//discard -> drop, player
	//path -> drop, type, pos, card
	//action -> drop, type, receiverId(Optional, StupidBird don't have), receiverPos(Optional, StupidBird don't have recvPos), Card(Optional, StupidBird only)

}