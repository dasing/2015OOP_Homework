import java.util.*;
import java.awt.Point;
import java.util.Arrays;
import java.util.ArrayList;

public class PlayerAction {
	
	//true if player drops a path/action card
	//false if player discards 1 ~ 3 cards
	public boolean isDrop;

	//action or path card
	public int cardType;
	public int cardFeature;
	public int dropIndex;
	public Point pos;
	public int recvID;
	public int recvPos;
	public int discardSize;
	public boolean isRotated; 

	public ArrayList<Integer> discardIndices;

	PlayerAction(){
		isDrop = true;
		cardType = -1;
		cardFeature = -1;
		dropIndex = -1;
		pos = new Point( -1, -1 );
		recvID = -1;
		recvPos = 0;
		isRotated = false;
		discardSize = 0;
		discardIndices = new ArrayList<Integer>();
	}

}