import java.awt.Point;
import java.util.ArrayList;

public class Decide{

	Boolean isDrop;
	int type; //type 1 -> using path card, type2 -> using function card

	//type 1
	Point pos;
	//type 2
	int receiverId;//no receiver return -1
	int recvPos;//0 towed 1 computer 2 huish

	Card card;
	ArrayList<Card> discardCards;
	Decide(Boolean drop,int type,Point pos,int receiverId,int recvPos,Card c){
		this.isDrop = drop;
		this.type = type;
		this.pos = pos;
		this.receiverId = receiverId;
		this.recvPos = recvPos;
		this.card = c;
	}
	Decide(Boolean isDrop,int type,Card c,int receiverId,int recvPos){
		this.isDrop = isDrop;
		this.type = type;
		this.receiverId = receiverId;
		this.recvPos = recvPos;
		this.card = c;
		this.pos = new Point(-1, -1);
	}
	Decide(Boolean isDrop,int type,int receiverId,int recvPos,Card c){
		this.isDrop = isDrop;
		this.type = type;
		this.receiverId = receiverId;
		this.recvPos = recvPos;
		this.card = null;
		this.discardCards = new ArrayList<Card>();
		this.discardCards.add(c);
		this.pos = new Point( -1, -1 );
	}
	Decide(Boolean isDrop,int type,Card c,int receiverId,int recvPos,Point pos){
		this.isDrop = isDrop;
		this.type = type;
		this.pos = pos;
		this.receiverId = receiverId;
		this.recvPos = recvPos;
		this.card = c;
	}
}