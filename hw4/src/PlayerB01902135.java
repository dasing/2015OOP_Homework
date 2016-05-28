//package foop;

import foop.*;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Object;
import java.util.Random;

public class PlayerB01902135 extends Player{

	private int[][] HardHitTable;
	private int[][] SoftHitTable;
	private boolean[][] SplitTable;

	private void ConstructDecisionTable(){

		// 1 means hit, 2 means double if allowed otherwise hit, 3 means double if allowed otherwise stand ,4 means stand
		HardHitTable = new int[18][10];
		SoftHitTable = new int[9][10];
		SplitTable = new boolean[10][10];

		//Construct HardHitTable
		for( int i=0; i<18; i++ ){

			for( int j=0; j<10; j++ ){

				if( i <=  3 )
					HardHitTable[i][j] = 1;
				else if( i == 4 ){

					if( j == 3 || j == 4 )
						HardHitTable[i][j] = 2;
					else
						HardHitTable[i][j] = 1;

				}else if( i == 5 ){

					if( j <= 4 )
						HardHitTable[i][j] = 2;
					else
						HardHitTable[i][j] = 1;

				}else if( i == 6 ){

					if( j <= 7 )
						HardHitTable[i][j] = 2;
					else
						HardHitTable[i][j] = 1;

				}else if( i == 7 ){

					HardHitTable[i][j] = 1;

				}else if( i == 8 ){

					if( j <= 1 || j >= 5 )
						HardHitTable[i][j] = 1;
					else
						HardHitTable[i][j] = 4;

				}else if( i >= 9 && i <= 12 ){
					if( j <= 4 )
						HardHitTable[i][j] = 4;
					else
						HardHitTable[i][j] = 1;
				}else if( i >= 13 ){
					HardHitTable[i][j] = 4;
				}

			}
		}


		//Construct SoftHitTable
		for( int i=0; i<9; i++ ){
			for( int j=0; j<10; j++ ){
				if( i <= 3 ){
					if( j <= 1 || j >= 5 ){
						SoftHitTable[i][j] = 1;
					}else
						SoftHitTable[i][j] = 2;
				}else if( i == 4 ){
					if( j <= 4 )
						SoftHitTable[i][j] = 2;
					else
						SoftHitTable[i][j] = 1;
				}else if( i == 5 ){
					if( j >= 1 || j <= 4 )
						SoftHitTable[i][j] = 3; //DS
					else if( j >= 7 )
						SoftHitTable[i][j] = 1; //Hit
					else
						SoftHitTable[i][j] = 4; //stand
				}else if( i == 6 ){
					if( j == 4 )
						SoftHitTable[i][j] = 3; //DS
					else
						SoftHitTable[i][j] = 4; //Stand
				}else
					SoftHitTable[i][j] = 4; //Stand
			}
		}

		//Construct SplitTable
		for( int i=0; i<10; i++ ){
			for( int j=0; j<10; j++ ){
				if( i == 0 ){
					if( j == 0 || j >= 6 )
						SplitTable[i][j] = false;
					else
						SplitTable[i][j] = true;
				}else if( i == 1 ){
					if( j >= 2 && j <= 5 )
						SplitTable[i][j] = true;
					else
						SplitTable[i][j] = false;
				}else if( i == 2 || i == 3 ){
					SplitTable[i][j] = false;
				}else if( i == 4 ){
					if( j <= 4 ){
						SplitTable[i][j] = true;
					}else
						SplitTable[i][j] = false;
				}else if( i == 5 ){
					if( j <= 5 ){
						SplitTable[i][j] = true;
					}else
						SplitTable[i][j] = false;
				}else if( i == 6 ){
					SplitTable[i][j] = true;
				}else if( i == 7 ){
					if( j == 5 || j>=8 ){
						SplitTable[i][j] = false;
					}else
						SplitTable[i][j] = true;
				}else if( i >= 8 )
					SplitTable[i][j] = true;
			}
		}

	}

	public PlayerB01902135(int nChips){

		super(nChips);
		ConstructDecisionTable();
	}

	private int CountValue( ArrayList<Card> Curr_Cards ){

		int size = Curr_Cards.size();
		int a_flag = 0;
		int value = 0;

		for( int i=0; i < size; i++ ){
			int curr_value = Curr_Cards.get(i).getValue();
			if( curr_value > 10 ) curr_value = 10;
			if( curr_value == 1 ) { a_flag = 1; curr_value = 11; }
			value += curr_value;
		}

		if( value <= 21 && a_flag == 1 ) { return (-1)*value; } //Soft Value

		if( value > 21 && a_flag == 1 ) { value-=10; } //Hard Value
		
		//Hard Value
		return value;
	}
	
	public boolean buy_insurance(Card my_open, Card dealer_open, ArrayList<Hand> current_table ){

		if( my_open.getValue() == 1 )
			return false;
		else
			return true;
	}


	public boolean do_double(Hand my_open, Card dealer_open,  ArrayList<Hand> current_table ){

		int value = CountValue( my_open.getCards() );
		int dealerValue = dealer_open.getValue();
		dealerValue = (dealerValue > 10) ? 10 : dealerValue;
		dealerValue = (dealerValue == 1) ? 11 : dealerValue;

		int HardFlag = 1;

		if( value < 0 ){
			value  *= (-1);
			HardFlag = 0;
		}

		//System.out.printf("Value = %d, dealerValue = %d, HardFlag = %d\n", value, dealerValue, HardFlag );
 
		if( HardFlag == 1 ){
			int res = HardHitTable[value -4][dealerValue-2];
			if(  res == 2 || res == 3 )
				return true;
			else
				return false;
		}else{
			int res = SoftHitTable[value -13][dealerValue-2];
			if( res == 2 || res == 3 )
				return true;
			else
				return false;
		}

	}

	public boolean	do_split( ArrayList<Card> my_open, Card dealer_open, ArrayList<Hand> current_table ){

		int CardOneValue = my_open.get(0).getValue();
		int CardTwoValue = my_open.get(1).getValue();
		int dealerValue = dealer_open.getValue();

		dealerValue = (dealerValue > 10) ? 10 : dealerValue;
		dealerValue = (dealerValue == 1) ? 11 : dealerValue;
		CardOneValue = ( CardOneValue > 10 ) ? 10 : CardOneValue;
		CardOneValue = ( CardOneValue == 1 ) ? 11 : CardOneValue;
		CardTwoValue = ( CardTwoValue > 10 ) ? 10 : CardTwoValue;
		CardTwoValue = (CardTwoValue == 1 ) ? 11 : CardTwoValue;

		if( CardOneValue == CardTwoValue ){
			boolean res = SplitTable[CardOneValue-2][dealerValue-2];
			return res;
		}else
			return false;

	}

	public boolean do_surrender(Card my_open, Card dealer_open, ArrayList<Hand> current_table ){

			return false;
	}

	public boolean hit_me( Hand my_open, Card dealer_open, ArrayList<Hand> current_table ){

		int value = CountValue( my_open.getCards() );
		int dealerValue = dealer_open.getValue();
		dealerValue = (dealerValue > 10) ? 10 : dealerValue;
		dealerValue = (dealerValue == 1) ? 11 : dealerValue;

		int HardFlag = 1;

		if( value  < 0 ){
			value  *= (-1);
			HardFlag = 0;
		}

		//System.out.printf("Value = %d, dealerValue = %d, HardFlag = %d\n", value, dealerValue, HardFlag );
		if( value >= 21 )
			return false;

		
		if( HardFlag == 1 ){
			int res = HardHitTable[value-4][dealerValue-2];
			//System.out.printf("HardHitTable[%d][%d] = %d\n", value-4, dealerValue-2, res );
			if(  res <= 2 )
				return true;
			else
				return false;
		}else{
			int res = SoftHitTable[value -13][dealerValue-2];
			//System.out.printf("SoftHitTable[%d][%d] = %d\n", value-13, dealerValue-2, res );
			if( res <= 2 )
				return true;
			else
				return false;
		}

	}


	public int make_bet( ArrayList<Hand> last_table, int total_player, int my_position ){

		Random ran = new Random();
		double Chips = get_chips();

		if( Chips >= 2 ){
			int res = ran.nextInt((int)Chips/2)+1;
			return res;
		}else
			return 1;
	}

	public String toString(){
		return new String(""+get_chips());
	}

}