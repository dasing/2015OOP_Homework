public class Card{

	public int type;
	public int feature;
	public Boolean isOpen;
	public Boolean isRotated;

	public int getFeature(){
		return this.feature;
	}

	public int getType(){

		return this.type;
	}

	Card(){
		this.type = -1;
		this.feature = -1;
		this.isOpen = true;
		this.isRotated = false;
	}

	Card( int type, int feature ){

		this.type = type;
		this.feature = feature;
		this.isRotated = false;

		if( (feature ==  Command.ID_PATH_FAKEGOAL1) ||  (feature == Command.ID_PATH_REALGOAL) || (feature ==	Command.ID_PATH_FAKEGOAL2) ) 
			this.isOpen = false;
		else
			this.isOpen = true;

	}

	Card( int type, int feature ,Boolean isRotated ){

		this.type = type;
		this.feature = feature;
		this.isRotated = isRotated;

		if( (feature ==  Command.ID_PATH_FAKEGOAL1) ||  (feature == Command.ID_PATH_REALGOAL) || (feature ==	Command.ID_PATH_FAKEGOAL2) ) 
			this.isOpen = false;
		else
			this.isOpen = true;

	}


}