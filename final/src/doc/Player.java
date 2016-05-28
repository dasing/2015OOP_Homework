import java.util.ArrayList;

public class Player {

	private int identity;
	public Boolean isTowed;
	public Boolean isMacBroken;
	public Boolean isHSUANTIEN;
	public ArrayList<Card> handCard;
	private ArrayList<Card> rewardsCard;

	public void setidentity(int id){
		this.identity = id;
	}

	public ArrayList<Card> getRewardCardList(){
		return rewardsCard;
	}

	public void addRewardCard( Card c ){
		rewardsCard.add(c);
	}

	public void dropCard( Card c ){
		handCard.remove(c);
	}

	public void dropCard( ArrayList<Integer> pos ){

		for( int i=pos.size()-1; i>=0; i-- ){
			System.out.printf("drop index %d card\n", pos.get(i));
			handCard.remove( (int)pos.get(i) );
		}

	}

	public int getidentity(){
		return this.identity;
	}

	public Boolean isMovable(){

		if( isTowed || isMacBroken || isHSUANTIEN )
			return false;
		else
			return true;
		
	}

	Player(){

		this.identity = -1;
		this.handCard = new ArrayList<Card>();
		this.rewardsCard = new ArrayList<Card>();
		this.isTowed = false;
		this.isMacBroken = false;
		this.isHSUANTIEN = false;

	}
}