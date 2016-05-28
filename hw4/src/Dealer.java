//package foop;

import foop.*;
import java.lang.Object;
import java.util.ArrayList;

public class Dealer{

	int total;
	boolean isBlackJack;
	boolean isBusted;
	boolean stand;
	Hand handCards;

	public Dealer(){
		
		total = 0;
		isBlackJack = false;
		isBusted = false;
		stand = false;
	}

	public int countValue(){

		ArrayList<Card> Curr_Cards = handCards.getCards();
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
				isBlackJack = true;
		}



		return value;
	}

	public boolean CheckSoft17(){

		ArrayList<Card> Curr_Cards = handCards.getCards();
		int size = Curr_Cards.size();
		int a_flag = 0;
		int value = 0;

		for( int i=0; i<size; i++ ){
			int curr_value = Curr_Cards.get(i).getValue();
			if( curr_value == 1 ) { a_flag = 1; continue; }
			if( curr_value > 10 ) curr_value = 10;
			value += curr_value;
		}

		if( value == 6 && a_flag == 1 )
			return true;
		else
			return false;

	}

	// not finished
	public void  makeDecisions(){

		/*Debug*/
		// ArrayList<Card> Curr_Cards = handCards.getCards();
		// System.out.printf("Dealer's Current Card:\n");
		// for( int i=0; i< Curr_Cards.size(); i++ ){
		// 	System.out.printf("%d %d\n", Curr_Cards.get(i).getSuit(), Curr_Cards.get(i).getValue() );
		// }

		int value = countValue();

		if( value == 17 ){
			if( CheckSoft17()  == true ){
				//System.out.printf("Soft-17, hit \n");
				return;
			}
		}

		if( value <= 16 ){
			//System.out.printf("value < 16, hit \n");
			return; //hit
		}else{
			//System.out.printf("stand\n");
			total = value;
			stand = true;
			return; //stand
		}
	}


}