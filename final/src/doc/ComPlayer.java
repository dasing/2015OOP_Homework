import java.util.*;
import java.awt.Point;

public class ComPlayer extends Player{
	double[] evaluation;
	double avgValue;
	Card [] mine;
	int myid;
	Boolean mineFound;
	Point minepos;
	int d;
	public int checktoup(Card temp_card){
		if(temp_card.getFeature() == Command.ID_PATH_START || temp_card.getFeature() == Command.ID_PATH_REALGOAL || temp_card.getFeature() == Command.ID_PATH_FAKEGOAL1 || temp_card.getFeature() == Command.ID_PATH_FAKEGOAL2)
			return 1;
		if(temp_card.isRotated == false){
			if(temp_card.getFeature() == Command.ID_PATH_PASS_1010 || temp_card.getFeature() == Command.ID_PATH_PASS_1110 || temp_card.getFeature() == Command.ID_PATH_PASS_1111 || temp_card.getFeature() == Command.ID_PATH_PASS_1101){
				return 1;
			}else{
				return 0;
			}
		}else{
			if(temp_card.getFeature() == Command.ID_PATH_PASS_1010 || temp_card.getFeature() == Command.ID_PATH_PASS_1110 || temp_card.getFeature() == Command.ID_PATH_PASS_1111 || temp_card.getFeature() == Command.ID_PATH_PASS_0110){
				return 1;
			}else{
				return 0;
			}
		}
	}
	public int checktodown(Card temp_card){
		if(temp_card.getFeature() == Command.ID_PATH_START || temp_card.getFeature() == Command.ID_PATH_REALGOAL || temp_card.getFeature() == Command.ID_PATH_FAKEGOAL1 || temp_card.getFeature() == Command.ID_PATH_FAKEGOAL2)
			return 1;
		if(temp_card.isRotated == false){
			if(temp_card.getFeature() == Command.ID_PATH_PASS_1010 || temp_card.getFeature() == Command.ID_PATH_PASS_1110 || temp_card.getFeature() == Command.ID_PATH_PASS_1111 || temp_card.getFeature() == Command.ID_PATH_PASS_0110 || temp_card.getFeature() == Command.ID_PATH_PASS_0011){
				return 1;
			}else{
				return 0;
			}
		}else{
			if(temp_card.getFeature() == Command.ID_PATH_PASS_1010 || temp_card.getFeature() == Command.ID_PATH_PASS_1110 || temp_card.getFeature() == Command.ID_PATH_PASS_1111 || temp_card.getFeature() == Command.ID_PATH_PASS_1101){
				return 1;
			}else{
				return 0;
			}
		}
	}
	public int checktoleft(Card temp_card){
		if(temp_card.getFeature() == Command.ID_PATH_START || temp_card.getFeature() == Command.ID_PATH_REALGOAL || temp_card.getFeature() == Command.ID_PATH_FAKEGOAL1 || temp_card.getFeature() == Command.ID_PATH_FAKEGOAL2)
			return 1;
		if(temp_card.isRotated == false){
			if(temp_card.getFeature() == Command.ID_PATH_PASS_1111 || temp_card.getFeature() == Command.ID_PATH_PASS_0011 || temp_card.getFeature() == Command.ID_PATH_PASS_1101 || temp_card.getFeature() == Command.ID_PATH_PASS_0101){
				return 1;
			}else{
				return 0;
			}
		}else{
			if(temp_card.getFeature() == Command.ID_PATH_PASS_1110 || temp_card.getFeature() == Command.ID_PATH_PASS_1111 || temp_card.getFeature() == Command.ID_PATH_PASS_0110 || temp_card.getFeature() == Command.ID_PATH_PASS_1101 || temp_card.getFeature() == Command.ID_PATH_PASS_0101){
				return 1;
			}else{
				return 0;
			}
		}
	}

	public int checktoright(Card temp_card){
		if(temp_card.getFeature() == Command.ID_PATH_START || temp_card.getFeature() == Command.ID_PATH_REALGOAL || temp_card.getFeature() == Command.ID_PATH_FAKEGOAL1 || temp_card.getFeature() == Command.ID_PATH_FAKEGOAL2) 
			return 1;
		if(temp_card.isRotated == false){
			if(temp_card.getFeature() == Command.ID_PATH_PASS_1110 || temp_card.getFeature() == Command.ID_PATH_PASS_1111 || temp_card.getFeature() == Command.ID_PATH_PASS_0110 || temp_card.getFeature() == Command.ID_PATH_PASS_1101 || temp_card.getFeature() == Command.ID_PATH_PASS_0101){
				return 1;
			}else{
				return 0;
			}
		}else{
			if(temp_card.getFeature() == Command.ID_PATH_PASS_1111 || temp_card.getFeature() == Command.ID_PATH_PASS_0011 || temp_card.getFeature() == Command.ID_PATH_PASS_1101 || temp_card.getFeature() == Command.ID_PATH_PASS_0101){
				return 1;
			}else{
				return 0;
			}
		}
	}
	public void updateboardstatus(Boolean[][] boardstatus,int x,int y,Card[][]board){
		if(x < 0 || x > 8 || y < 0 || y > 4){
			return ;
		}//can go up
//		System.out.println(x+" " +y);
		if(checktoup(board[x][y]) == 1){
			if(y - 1 >= 0 && boardstatus[x][y-1] ==false &&board[x][y-1].getType()!= -1 && checktodown(board[x][y-1]) == 1){
				boardstatus[x][y-1] = true;
				updateboardstatus(boardstatus,x,y-1,board);
			}
		}
		//can go down
		if(checktodown(board[x][y]) == 1){
			if(y+1 <= 4 && boardstatus[x][y+1] == false &&board[x][y+1].getType() != -1 && checktoup(board[x][y+1]) == 1){
				boardstatus[x][y+1] = true;
				updateboardstatus(boardstatus,x,y+1,board);
			}
		}
		//right
		if(checktoright(board[x][y]) == 1){
			if(x + 1 <= 8 && boardstatus[x+1][y] == false && board[x+1][y].getType() != -1 && checktoleft(board[x+1][y]) == 1){
				boardstatus[x+1][y] = true;
				updateboardstatus(boardstatus,x+1,y,board);
			}
		}
		//to the left????????
		if(checktoleft(board[x][y]) == 1){
			if(x - 1 >= 0 && boardstatus[x-1][y] == false &&board[x-1][y].getType()!= -1 && checktoright(board[x-1][y]) == 1){
				boardstatus[x-1][y] =true;
				updateboardstatus(boardstatus,x-1,y,board);
			}
		}
	}

	public Decide decide(ArrayList<Decision> lastRound,Card [][]board,ArrayList<Status> s){
		Iterator<Card> it = this.handCard.iterator();
		Random ran = new Random();
		int index = 0;
		int mood = 0;
		Card temp_card;
		int rotate_not_L,rotate_not_R,rotate_not_U,rotate_not_D;
		int rotate_yes_L,rotate_yes_R,rotate_yes_U,rotate_yes_D;	
/*		for( int i=0; i<5; i++ ){
			for( int j=0; j<9; j++ ){
				System.out.printf("%d ", board[j][i].getFeature());
			}
			System.out.printf("\n");
		}
		for( int i=0; i<5; i++ ){
			for( int j=0; j<9; j++ ){
				if(board[j][i].isRotated == true)
					System.out.printf("1 ");
				else
					System.out.printf("0 ");
			}
			System.out.printf("\n");
		}*/
		if(mineFound == false){//if we have map card
			it = this.handCard.iterator();
			while(it.hasNext()){
				temp_card = it.next();
				if(temp_card.getType() == Command.CARDTYPE_ACTION && temp_card.getFeature() == Command.ID_ACTION_GPS){
					if(this.mine[0] == null){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,-1,-1,new Point(8,0));
					}else if(this.mine[1] == null){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,-1,-1,new Point(8,2));
					}else if(this.mine[2] == null){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,-1,-1,new Point(8,4));
					}
				}
			}
		}
		Decision temp_decision;
		Iterator<Decision> it_des;
		Boolean[][] boardstatus = new Boolean[9][5];
		for(int i = 0;i < 9;i++)
			for(int j = 0;j < 5;j++){
				boardstatus[i][j] = false;
			}
		boardstatus[0][2] = true;
		d++;
		updateboardstatus(boardstatus,0,2,board);
	/*	System.out.println("d:" + d);
		for(int j = 0;j < 5;j++){
			for(int i = 0;i < 9;i++){
				if(boardstatus[i][j] == true){
					System.out.print("1 ");
				}else{
					System.out.print("0 ");
				}
			}
			System.out.println("");
		}*/
		if(this.getidentity() != Command.ID_ROLE_SABOTEUR){
			//update evaluation
			it_des = lastRound.iterator();
//			if(lastRound == null)
//				System.out.println("null....................................");
//			else if(lastRound.size() <= 0)
//				System.out.println("size000000000000000000000000");
//			System.out.println("????????????????????????????????????????");
			while(it_des.hasNext()){
				temp_decision = it_des.next();
//				System.out.println("start");
//				System.out.println("card type "+temp_decision.card.getType() + " cardfeature" + temp_decision.card.getFeature());
//				System.out.println("playerid " + temp_decision.playerid + " receiverId " + temp_decision.receiverId);	
				if(temp_decision.card.getType() == Command.CARDTYPE_ACTION){
				//decide if action is to hit other or save other
					if(temp_decision.card.getFeature() == Command.ID_ACTION_TOWED || temp_decision.card.getFeature() == Command.ID_ACTION_MACBROKEN || temp_decision.card.getFeature() == Command.ID_ACTION_HSUANTIEN){
						if(this.evaluation[temp_decision.playerid] < this.evaluation[temp_decision.receiverId]){
							this.evaluation[temp_decision.playerid]--;
						}else if(this.evaluation[temp_decision.playerid] > this.evaluation[temp_decision.receiverId]){
							this.evaluation[temp_decision.receiverId]--;
						}else if(this.evaluation[temp_decision.playerid] == this.evaluation[temp_decision.receiverId]){
							this.evaluation[temp_decision.playerid]--;
						}
					}else if(temp_decision.card.getFeature() == Command.ID_ACTION_UNTOWED || temp_decision.card.getFeature() == Command.ID_ACTION_MACREPAIRED || temp_decision.card.getFeature() == Command.ID_ACTION_HAHA || temp_decision.card.getFeature() == Command.ID_ACTION_MACREPAIRED_HAHA || temp_decision.card.getFeature() == Command.ID_ACTION_UNTOWED_HAHA || temp_decision.card.getFeature() == Command.ID_ACTION_UNTOWED_MACREPAIRED){
						if(this.evaluation[temp_decision.receiverId] < avgValue){
							this.evaluation[temp_decision.playerid]--;//87 save the bad guy
						}else if(this.evaluation[temp_decision.playerid] < avgValue){
							this.evaluation[temp_decision.playerid]++;
						}
					}else if(temp_decision.card.getFeature() == Command.ID_ACTION_GPS){
						//do nothing
					}else if(temp_decision.card.getFeature() == Command.ID_ACTION_STUPIDBIRD){
						if(temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_0010 || temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_1011 || temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_1111 || temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_0110 || temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_0011 || temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_1010 || temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_1101 || temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_0101 || temp_decision.card.getFeature() == Command.ID_PATH_BLOCK_0001){
							this.evaluation[temp_decision.playerid]++;
						}else{
							this.evaluation[temp_decision.playerid]--;
						}
					}
				}else if(temp_decision.card.getType() == Command.CARDTYPE_PATH){
					//judge this path is good to result or bad
					if(mineFound == true){
						if(temp_decision.card.getFeature() <= Command.ID_PATH_BLOCK_0001 && temp_decision.card.getFeature() >= Command.ID_PATH_BLOCK_0010){
							if(temp_decision.pos.getY() - minepos.getY() <= 1 && temp_decision.pos.getY() - minepos.getY() >= -1){
								this.evaluation[temp_decision.playerid]--;
							}else{
								this.evaluation[temp_decision.playerid]++;
							}
						}else{
							if(temp_decision.pos.getY() - minepos.getY() <= 1 && temp_decision.pos.getY() - minepos.getY() >= -1){
								this.evaluation[temp_decision.playerid]++;
							}else{
								this.evaluation[temp_decision.playerid]--;
							}
						}
					}else{
						if(mineFound == false){
							if(temp_decision.card.getFeature() <= Command.ID_PATH_BLOCK_0001 && temp_decision.card.getFeature() >= Command.ID_PATH_BLOCK_0010){
								this.evaluation[temp_decision.playerid]--;
							}else{
								this.evaluation[temp_decision.playerid]++;
							}
						}
					}
				}
			}
			System.out.println("0:" + this.evaluation[0] + "\n1:" + this.evaluation[1] + " \n2:" + this.evaluation[2] + " \n3:" + this.evaluation[3]);
			avgValue = (this.evaluation[0] + this.evaluation[1] + this.evaluation[2] + this.evaluation[3]) / 4;
			//after update
			it = this.handCard.iterator();
			while(it.hasNext()){
				temp_card = it.next();
				//help somebody
				if(temp_card.getType() != Command.CARDTYPE_ACTION)
					continue;
				if(temp_card.getFeature() >= Command.ID_ACTION_UNTOWED && temp_card.getFeature() <= Command.ID_ACTION_MACREPAIRED_HAHA){
					
					if(this.isTowed == true && (temp_card.getFeature() == Command.ID_ACTION_UNTOWED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_HAHA)){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,this.myid,0);
					}else if(this.isMacBroken == true && (temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED_HAHA)){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,this.myid,1);
					}else if(this.isHSUANTIEN == true && (temp_card.getFeature() == Command.ID_ACTION_HAHA || temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED_HAHA || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_HAHA)){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,this.myid,2);
					}
					
					for(int i = 3;i >= 0;i--){
						Status temp_status = s.get(i);
						if(i == this.myid)
							continue;
						else{							
							if(temp_status.s.get(0) == true && (  temp_card.getFeature() == Command.ID_ACTION_UNTOWED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_HAHA  ) ){
								return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,0);
							}else if(temp_status.s.get(1) == true && (temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED_HAHA)){
								return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,1);
							}else if(temp_status.s.get(2) == true && (temp_card.getFeature() == Command.ID_ACTION_HAHA || temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED_HAHA || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_HAHA)){
								return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,2);
							}

						}
					}
				}
			}
//			System.out.println("after save");
			//hit somebody
			it = this.handCard.iterator();
			while(it.hasNext()){
				temp_card = it.next();
				if(temp_card.getType() != Command.CARDTYPE_ACTION)
					continue;
				if(temp_card.getFeature() >= Command.ID_ACTION_TOWED && temp_card.getFeature() <= Command.ID_ACTION_HSUANTIEN){		
					for(int i = 3;i >= 0;i--){
						if(i == this.myid)
							continue;
						else{
							Status temp_status = s.get(i);
							if(avgValue > this.evaluation[i]){
								if(temp_card.getFeature() == Command.ID_ACTION_TOWED && temp_status.s.get(0) == false){
									return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,0);
								}else if(temp_card.getFeature() == Command.ID_ACTION_MACBROKEN && temp_status.s.get(1) == false){
									return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,1);
								}else if(temp_card.getFeature() == Command.ID_ACTION_HSUANTIEN && temp_status.s.get(2) == false){
									return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,2);
								}
							}
						}
					}
				}
			}
			mood = ran.nextInt(50);
			if(this.isTowed == false && this.isHSUANTIEN == false && this.isMacBroken == false && mood != 13){
				int x,y;
				for(int i = 8;i >= 0;i--){
					for(int j = 4;j >= 0;j--){
						if(boardstatus[i][j] == true){
//							System.out.printf("i:%d j:%d\n",i,j);
							//go from left to right and test i + 1 can place??
							if(checktoright(board[i][j]) == 1 && i + 1 < 9&&board[i+1][j].getType() == -1 ){
								it = this.handCard.iterator();
								while(it.hasNext()){
									temp_card = it.next();
									if(temp_card.getType() != Command.CARDTYPE_PATH)
										continue;
//									System.out.println("card type" + temp_card.getType() + " cardfeature" + temp_card.getFeature());
									// not rotate
									rotate_not_U = 0;
									rotate_not_R = 0;
									rotate_not_L = 0;
									rotate_not_D = 0;
									rotate_yes_U = 0;
									rotate_yes_R = 0;
									rotate_yes_L = 0;
									rotate_yes_D = 0;
									if(checktoleft(temp_card) == 1){
										rotate_not_L = 1;
									}else{
										rotate_not_L = 0;
									}
									if(rotate_not_L == 1 && i + 1 <= 8 && j - 1 >= 0 && board[i+1][j-1].getType() != -1){
										if(checktodown(board[i+1][j-1]) == checktoup(temp_card)){
											rotate_not_U = 1;
										}else{
											rotate_not_U = 0;
										}
									}else{
										rotate_not_U = 1;
									}
									// down ok
									if(rotate_not_L == 1 && rotate_not_U == 1 && i+1 <= 8 && j + 1 <= 4 && board[i+1][j+1].getType() != -1){
										if(checktoup(board[i+1][j+1]) == checktodown(temp_card)){
											rotate_not_D = 1;
										}else{
											rotate_not_D = 0;
										}
									}else{
										rotate_not_D = 1;
									}
									//right ok
									if(rotate_not_L == 1 && rotate_not_D == 1 && rotate_not_U == 1 && i+2 <=8 && board[i+2][j].getType() != -1){
										if(checktoleft(board[i+2][j]) == checktoright(temp_card)){
											rotate_not_R = 1;
										}else{
											rotate_not_R = 0;
										}
									}else{
										rotate_not_R = 1;
									}
									if(rotate_not_U == 1 && rotate_not_R == 1 && rotate_not_L == 1 && rotate_not_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i+1,j));
									// rotate
									temp_card.isRotated = true;
									if(checktoleft(temp_card) == 1){
										rotate_yes_L = 1;
									}else{
										rotate_yes_L = 0;
									}
									if(rotate_yes_L == 1 && i + 1 <= 8 && j - 1 >= 0 && board[i+1][j-1].getType() != -1){
										if(checktodown(board[i+1][j-1]) == checktoup(temp_card)){
											rotate_yes_U = 1;
										}else{
											rotate_yes_U = 0;
										}
									}else{
										rotate_yes_U = 1;
									}
									// down ok
									if(rotate_yes_L == 1 && rotate_yes_U == 1 && i+1 <= 8 && j + 1 <= 4 && board[i+1][j+1].getType() != -1){
										if(checktoup(board[i+1][j+1]) == checktodown(temp_card)){
											rotate_yes_D = 1;
										}else{
											rotate_yes_D = 0;
										}
									}else{
										rotate_yes_D = 1;
									}
									//right ok
									if(rotate_yes_L == 1 && rotate_yes_D == 1 && rotate_yes_U == 1 && i+2 <=8 && board[i+2][j].getType() != -1){
										if(checktoleft(board[i+2][j]) == checktoright(temp_card)){
											rotate_yes_R = 1;
										}else{
											rotate_yes_R = 0;
										}
									}else{
										rotate_yes_R = 1;
									}
									if(rotate_yes_U == 1 && rotate_yes_R == 1 && rotate_yes_L == 1 && rotate_yes_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i+1,j));
									temp_card.isRotated = false;
								}
							}
							//go to up
							if(checktoup(board[i][j]) == 1 && j - 1 >= 0 && board[i][j-1].getType() == -1){
								it = this.handCard.iterator();
								while(it.hasNext()){
									temp_card = it.next();
									if(temp_card.getType() != Command.CARDTYPE_PATH)
										continue;
//									System.out.println("card type"+temp_card.getType() + " cardfeature" + temp_card.getFeature());
									rotate_not_U = 0;
									rotate_not_R = 0;
									rotate_not_L = 0;
									rotate_not_D = 0;
									rotate_yes_U = 0;
									rotate_yes_R = 0;
									rotate_yes_L = 0;
									rotate_yes_D = 0;
									temp_card.isRotated = false;
									if(checktodown(temp_card) == 1){
										rotate_not_D = 1;
									}else{
										rotate_not_D = 0;
									}
									//check up
									if(rotate_not_D == 1 && i <= 8 && j - 2 >= 0 && board[i][j-2].getType() != -1){
										if(checktodown(board[i][j-2]) == checktoup(temp_card)){
											rotate_not_U = 1;
										}else{
											rotate_not_U = 0;
										}
									}else{
										rotate_not_U = 1;
									}
									//check left
									if(rotate_not_D == 1 && rotate_not_U == 1 && i-1 >= 0 && j -1 >= 0 && board[i-1][j-1].getType() != -1){
										if(checktoright(board[i-1][j-1]) == checktoleft(temp_card)){
											rotate_not_L = 1;
										}else{
											rotate_not_L = 0;
										}
									}else{
										rotate_not_L = 1;
									}
									//right ok
									if(rotate_not_L == 1 && rotate_not_D == 1 && rotate_not_U == 1 && i+1 <=8 && j - 1 >= 0 && board[i+1][j - 1].getType() != -1){
										if(checktoleft(board[i+1][j-1]) == checktoright(temp_card)){
											rotate_not_R = 1;
										}else{
											rotate_not_R = 0;
										}
									}else{
										rotate_not_R = 1;
									}
									if(rotate_not_U == 1 && rotate_not_R == 1 && rotate_not_L == 1 && rotate_not_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i,j-1));
									// rotate
									temp_card.isRotated = true;
									if(checktodown(temp_card) == 1){
										rotate_yes_D = 1;
									}else{
										rotate_yes_D = 0;
									}
									if(rotate_yes_D == 1 && i <= 8 && j - 2 >= 0 && board[i][j-2].getType() != -1){
										if(checktodown(board[i][j-2]) == checktoup(temp_card)){
											rotate_yes_U = 1;
										}else{
											rotate_yes_U = 0;
										}
									}else{
										rotate_yes_U = 1;
									}
									//check left
									if(rotate_yes_D == 1 && rotate_yes_U == 1 && i - 1 >= 0 && j - 1 >= 0 && board[i-1][j-1].getType() != -1){
										if(checktoright(board[i-1][j-1]) == checktoleft(temp_card)){
											rotate_yes_L = 1;
										}else{
											rotate_yes_L = 0;
										}
									}else{
										rotate_yes_L = 1;
									}
									//right ok
									if(rotate_yes_L == 1 && rotate_yes_D == 1 && rotate_yes_U == 1 && i + 1 <=8 && j - 1 >= 0 && board[i+1][j-1].getType() != -1){
										if(checktoleft(board[i+1][j-1]) == checktoright(temp_card)){
											rotate_yes_R = 1;
										}else{
											rotate_yes_R = 0;
										}
									}else{
										rotate_yes_R = 1;
									}
									if(rotate_yes_U == 1 && rotate_yes_R == 1 && rotate_yes_L == 1 && rotate_yes_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i,j-1));
									temp_card.isRotated = false;
								}	
							}
							//go to down
							if(checktodown(board[i][j]) == 1 && j + 1 < 5 && board[i][j+1].getType() == -1){
								it = this.handCard.iterator();
								while(it.hasNext()){
									temp_card = it.next();
									if(temp_card.getType() != Command.CARDTYPE_PATH)
										continue;
	//								System.out.println("card type"+temp_card.getType() + " cardfeature" + temp_card.getFeature());
									rotate_not_U = 0;
									rotate_not_R = 0;
									rotate_not_L = 0;
									rotate_not_D = 0;
									rotate_yes_U = 0;
									rotate_yes_R = 0;
									rotate_yes_L = 0;
									rotate_yes_D = 0;
									temp_card.isRotated = false;
									if(checktoup(temp_card) == 1){
										rotate_not_U = 1;
									}else{
										rotate_not_U = 0;
									}
									//check down
									if(rotate_not_U == 1 && i <= 8 && j + 2 <= 4 && board[i][j+2].getType() != -1){
										if(checktoup(board[i][j+2]) == checktodown(temp_card)){
											rotate_not_D = 1;
										}else{
											rotate_not_D = 0;
										}
									}else{
										rotate_not_D = 1;
									}
									//check left
									if(rotate_not_D == 1 && rotate_not_U == 1 && i-1 >= 0 && j + 1 >= 0 && board[i-1][j+1].getType() != -1){
										if(checktoright(board[i-1][j+1]) == checktoleft(temp_card)){
											rotate_not_L = 1;
										}else{
											rotate_not_L = 0;
										}
									}else{
										rotate_not_L = 1;
									}
									//right ok
									if(rotate_not_L == 1 && rotate_not_D == 1 && rotate_not_U == 1 && i+1 <=8 && j + 1 <= 4 && board[i+1][j+1].getType() != -1){
										if(checktoleft(board[i+1][j+1]) == checktoright(temp_card)){
											rotate_not_R = 1;
										}else{
											rotate_not_R = 0;
										}
									}else{
										rotate_not_R = 1;
									}
									if(rotate_not_U == 1 && rotate_not_R == 1 && rotate_not_L == 1 && rotate_not_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i,j+1));
									// rotate
									temp_card.isRotated = true;
									if(checktoup(temp_card) == 1){
										rotate_yes_U = 1;
									}else{
										rotate_yes_U = 0;
									}
									if(rotate_yes_U == 1 && i <= 8 && j + 2 <= 4 && board[i][j+2].getType() != -1){
										if(checktoup(board[i][j+2]) == checktodown(temp_card)){
											rotate_yes_D = 1;
										}else{
											rotate_yes_D = 0;
										}
									}else{
										rotate_yes_D = 1;
									}
									//check left
									if(rotate_yes_D == 1 && rotate_yes_U == 1 && i - 1 >= 0 && j + 1 <= 4 && board[i-1][j+1].getType() != -1){
										if(checktoright(board[i-1][j+1]) == checktoleft(temp_card)){
											rotate_yes_L = 1;
										}else{
											rotate_yes_L = 0;
										}
									}else{
										rotate_yes_L = 1;
									}
									//right ok
									if(rotate_yes_L == 1 && rotate_yes_D == 1 && rotate_yes_U == 1 && i + 1 <=8 && j + 1 <= 4 && board[i+1][j+1].getType() != -1){
										if(checktoleft(board[i+1][j+1]) == checktoright(temp_card)){
											rotate_yes_R = 1;
										}else{
											rotate_yes_R = 0;
										}
									}else{
										rotate_yes_R = 1;
									}
									if(rotate_yes_U == 1 && rotate_yes_R == 1 && rotate_yes_L == 1 && rotate_yes_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i,j+1));
									temp_card.isRotated = false;
								}	
							}
						}
					}
				}
			}//bad guy
		}else{

			//hit
			it = this.handCard.iterator();
			while(it.hasNext()){
				temp_card = it.next();
				if(temp_card.getType() != Command.CARDTYPE_ACTION)
					continue;
				if(temp_card.getFeature() >= Command.ID_ACTION_TOWED && temp_card.getFeature() <= Command.ID_ACTION_HSUANTIEN){
					for(int i = 0;i < 4;i++){
						if(i == this.myid)
							continue;
						else{
							Status temp_status = s.get(i);
							if(true){
								if(temp_card.getFeature() == Command.ID_ACTION_TOWED && temp_status.s.get(0) == false){
									return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,0);
								}else if(temp_card.getFeature() == Command.ID_ACTION_MACBROKEN && temp_status.s.get(1) == false){
									return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,1);
								}else if(temp_card.getFeature() == Command.ID_ACTION_HSUANTIEN && temp_status.s.get(2) == false){
									return new Decide(true,Command.CARDTYPE_ACTION,temp_card,i,2);
								}
							}
						}
					}
				}
			}
			while(it.hasNext()){
				temp_card = it.next();
				if(temp_card.getType() != Command.CARDTYPE_ACTION)
					continue;
				//help myself
				if(temp_card.getFeature() >= Command.ID_ACTION_UNTOWED && temp_card.getFeature() <= Command.ID_ACTION_MACREPAIRED_HAHA){
					
					if(this.isTowed == true && (temp_card.getFeature() == Command.ID_ACTION_UNTOWED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_HAHA)){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,this.myid,0);
					}else if(this.isMacBroken == true && (temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_MACREPAIRED || temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED_HAHA)){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,this.myid,1);
					}else if(this.isHSUANTIEN == true && (temp_card.getFeature() == Command.ID_ACTION_HAHA || temp_card.getFeature() == Command.ID_ACTION_MACREPAIRED_HAHA || temp_card.getFeature() == Command.ID_ACTION_UNTOWED_HAHA)){
						return new Decide(true,Command.CARDTYPE_ACTION,temp_card,this.myid,2);
					}
				}
			}
			mood = ran.nextInt(50);
			if(this.isTowed == false && this.isHSUANTIEN == false && this.isMacBroken == false && mood != 13){
				int x,y;
	//			System.out.println("path maybe");
				for(int i = 8;i >= 0;i--){
					for(int j = 4;j >= 0;j--){
						if(boardstatus[i][j] == true){
			//				System.out.printf("i:%d j:%d\n",i,j);
							//go from left to right and test i + 1 can place??
							if(checktoright(board[i][j]) == 1 && i + 1 < 9 && board[i+1][j].getType() == -1){
								it = this.handCard.iterator();
								while(it.hasNext()){
									temp_card = it.next();
									if(temp_card.getType() != Command.CARDTYPE_PATH)
										continue;
				//					System.out.println("card type" + temp_card.getType() + " cardfeature" + temp_card.getFeature());
									// not rotate
									rotate_not_U = 0;
									rotate_not_R = 0;
									rotate_not_L = 0;
									rotate_not_D = 0;
									rotate_yes_U = 0;
									rotate_yes_R = 0;
									rotate_yes_L = 0;
									rotate_yes_D = 0;
									if(checktoleft(temp_card) == 1){
										rotate_not_L = 1;
									}else{
										rotate_not_L = 0;
									}
									if(rotate_not_L == 1 && i + 1 <= 8 && j - 1 >= 0 && board[i+1][j-1].getType() != -1){
										if(checktodown(board[i+1][j-1]) == checktoup(temp_card)){
											rotate_not_U = 1;
										}else{
											rotate_not_U = 0;
										}
									}else{
										rotate_not_U = 1;
									}
									// down ok
									if(rotate_not_L == 1 && rotate_not_U == 1 && i+1 <= 8 && j + 1 <= 4 && board[i+1][j+1].getType() != -1){
										if(checktoup(board[i+1][j+1]) == checktodown(temp_card)){
											rotate_not_D = 1;
										}else{
											rotate_not_D = 0;
										}
									}else{
										rotate_not_D = 1;
									}
									//right ok
									if(rotate_not_L == 1 && rotate_not_D == 1 && rotate_not_U == 1 && i+2 <=8 && board[i+2][j].getType() != -1){
										if(checktoleft(board[i+2][j]) == checktoright(temp_card)){
											rotate_not_R = 1;
										}else{
											rotate_not_R = 0;
										}
									}else{
										rotate_not_R = 1;
									}
									if(rotate_not_U == 1 && rotate_not_R == 1 && rotate_not_L == 1 && rotate_not_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i+1,j));
									// rotate
									temp_card.isRotated = true;
									if(checktoleft(temp_card) == 1){
										rotate_yes_L = 1;
									}else{
										rotate_yes_L = 0;
									}
									if(rotate_yes_L == 1 && i + 1 <= 8 && j - 1 >= 0 && board[i+1][j-1].getType() != -1){
										if(checktodown(board[i+1][j-1]) == checktoup(temp_card)){
											rotate_yes_U = 1;
										}else{
											rotate_yes_U = 0;
										}
									}else{
										rotate_yes_U = 1;
									}
									// down ok
									if(rotate_yes_L == 1 && rotate_yes_U == 1 && i+1 <= 8 && j + 1 <= 4 && board[i+1][j+1].getType() != -1){
										if(checktoup(board[i+1][j+1]) == checktodown(temp_card)){
											rotate_yes_D = 1;
										}else{
											rotate_yes_D = 0;
										}
									}else{
										rotate_yes_D = 1;
									}
									//right ok
									if(rotate_yes_L == 1 && rotate_yes_D == 1 && rotate_yes_U == 1 && i+2 <=8 && board[i+2][j].getType() != -1){
										if(checktoleft(board[i+2][j]) == checktoright(temp_card)){
											rotate_yes_R = 1;
										}else{
											rotate_yes_R = 0;
										}
									}else{
										rotate_yes_R = 1;
									}
									if(rotate_yes_U == 1 && rotate_yes_R == 1 && rotate_yes_L == 1 && rotate_yes_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i+1,j));
									temp_card.isRotated = false;
								}
							}
							//go to up
							if(checktoup(board[i][j])==1 && j-1>=0 && board[i][j-1].getType()==-1){
								it = this.handCard.iterator();
								while(it.hasNext()){
									temp_card = it.next();
									if(temp_card.getType() != Command.CARDTYPE_PATH)
										continue;
			//						System.out.println("card type"+temp_card.getType() + " cardfeature" + temp_card.getFeature());
									rotate_not_U = 0;
									rotate_not_R = 0;
									rotate_not_L = 0;
									rotate_not_D = 0;
									rotate_yes_U = 0;
									rotate_yes_R = 0;
									rotate_yes_L = 0;
									rotate_yes_D = 0;
									temp_card.isRotated = false;
									if(checktodown(temp_card) == 1){
										rotate_not_D = 1;
									}else{
										rotate_not_D = 0;
									}
									//check up
									if(rotate_not_D == 1 && i <= 8 && j - 2 >= 0 && board[i][j-2].getType() != -1){
										if(checktodown(board[i][j-2]) == checktoup(temp_card)){
											rotate_not_U = 1;
										}else{
											rotate_not_U = 0;
										}
									}else{
										rotate_not_U = 1;
									}
									//check left
									if(rotate_not_D == 1 && rotate_not_U == 1 && i-1 >= 0 && j -1 >= 0 && board[i-1][j-1].getType() != -1){
										if(checktoright(board[i-1][j-1]) == checktoleft(temp_card)){
											rotate_not_L = 1;
										}else{
											rotate_not_L = 0;
										}
									}else{
										rotate_not_L = 1;
									}
									//right ok
									if(rotate_not_L == 1 && rotate_not_D == 1 && rotate_not_U == 1 && i+1 <=8 && j - 1 >= 0 && board[i+1][j - 1].getType() != -1){
										if(checktoleft(board[i+1][j-1]) == checktoright(temp_card)){
											rotate_not_R = 1;
										}else{
											rotate_not_R = 0;
										}
									}else{
										rotate_not_R = 1;
									}
									if(rotate_not_U == 1 && rotate_not_R == 1 && rotate_not_L == 1 && rotate_not_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i,j-1));
									// rotate
									temp_card.isRotated = true;
									if(checktodown(temp_card) == 1){
										rotate_yes_D = 1;
									}else{
										rotate_yes_D = 0;
									}
									if(rotate_yes_D == 1 && i <= 8 && j - 2 >= 0 && board[i][j-2].getType() != -1){
										if(checktodown(board[i][j-2]) == checktoup(temp_card)){
											rotate_yes_U = 1;
										}else{
											rotate_yes_U = 0;
										}
									}else{
										rotate_yes_U = 1;
									}
									//check left
									if(rotate_yes_D == 1 && rotate_yes_U == 1 && i - 1 >= 0 && j - 1 >= 0 && board[i-1][j-1].getType() != -1){
										if(checktoright(board[i-1][j-1]) == checktoleft(temp_card)){
											rotate_yes_L = 1;
										}else{
											rotate_yes_L = 0;
										}
									}else{
										rotate_yes_L = 1;
									}
									//right ok
									if(rotate_yes_L == 1 && rotate_yes_D == 1 && rotate_yes_U == 1 && i + 1 <=8 && j - 1 >= 0 && board[i+1][j-1].getType() != -1){
										if(checktoleft(board[i+1][j-1]) == checktoright(temp_card)){
											rotate_yes_R = 1;
										}else{
											rotate_yes_R = 0;
										}
									}else{
										rotate_yes_R = 1;
									}
									if(rotate_yes_U == 1 && rotate_yes_R == 1 && rotate_yes_L == 1 && rotate_yes_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i,j-1));
									temp_card.isRotated = false;
								}	
							}
							//go to down
							if(checktodown(board[i][j]) == 1 && j+1 <5 && board[i][j+1].getType()== -1){
								it = this.handCard.iterator();
								while(it.hasNext()){
									temp_card = it.next();
									if(temp_card.getType() != Command.CARDTYPE_PATH)
										continue;
			//						System.out.println("card type"+temp_card.getType() + " cardfeature" + temp_card.getFeature());
									rotate_not_U = 0;
									rotate_not_R = 0;
									rotate_not_L = 0;
									rotate_not_D = 0;
									rotate_yes_U = 0;
									rotate_yes_R = 0;
									rotate_yes_L = 0;
									rotate_yes_D = 0;
									temp_card.isRotated = false;
									if(checktoup(temp_card) == 1){
										rotate_not_U = 1;
									}else{
										rotate_not_U = 0;
									}
									//check down
									if(rotate_not_U == 1 && i <= 8 && j + 2 <= 4 && board[i][j+2].getType() != -1){
										if(checktoup(board[i][j+2]) == checktodown(temp_card)){
											rotate_not_D = 1;
										}else{
											rotate_not_D = 0;
										}
									}else{
										rotate_not_D = 1;
									}
									//check left
									if(rotate_not_D == 1 && rotate_not_U == 1 && i-1 >= 0 && j + 1 >= 0 && board[i-1][j+1].getType() != -1){
										if(checktoright(board[i-1][j+1]) == checktoleft(temp_card)){
											rotate_not_L = 1;
										}else{
											rotate_not_L = 0;
										}
									}else{
										rotate_not_L = 1;
									}
									//right ok
									if(rotate_not_L == 1 && rotate_not_D == 1 && rotate_not_U == 1 && i+1 <=8 && j + 1 <= 4 && board[i+1][j+1].getType() != -1){
										if(checktoleft(board[i+1][j+1]) == checktoright(temp_card)){
											rotate_not_R = 1;
										}else{
											rotate_not_R = 0;
										}
									}else{
										rotate_not_R = 1;
									}
									if(rotate_not_U == 1 && rotate_not_R == 1 && rotate_not_L == 1 && rotate_not_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i,j+1));
									// rotate
									temp_card.isRotated = true;
									if(checktoup(temp_card) == 1){
										rotate_yes_U = 1;
									}else{
										rotate_yes_U = 0;
									}
									if(rotate_yes_U == 1 && i <= 8 && j + 2 <= 4 && board[i][j+2].getType() != -1){
										if(checktoup(board[i][j+2]) == checktodown(temp_card)){
											rotate_yes_D = 1;
										}else{
											rotate_yes_D = 0;
										}
									}else{
										rotate_yes_D = 1;
									}
									//check left
									if(rotate_yes_D == 1 && rotate_yes_U == 1 && i - 1 >= 0 && j + 1 <= 4 && board[i-1][j+1].getType() != -1){
										if(checktoright(board[i-1][j+1]) == checktoleft(temp_card)){
											rotate_yes_L = 1;
										}else{
											rotate_yes_L = 0;
										}
									}else{
										rotate_yes_L = 1;
									}
									//right ok
									if(rotate_yes_L == 1 && rotate_yes_D == 1 && rotate_yes_U == 1 && i + 1 <=8 && j + 1 <= 4 && board[i+1][j+1].getType() != -1){
										if(checktoleft(board[i+1][j+1]) == checktoright(temp_card)){
											rotate_yes_R = 1;
										}else{
											rotate_yes_R = 0;
										}
									}else{
										rotate_yes_R = 1;
									}
									if(rotate_yes_U == 1 && rotate_yes_R == 1 && rotate_yes_L == 1 && rotate_yes_D == 1)
										return new Decide(true,Command.CARDTYPE_PATH,temp_card,-1,-1,new Point(i,j+1));
									temp_card.isRotated = false;
								}	
							}
						}
					}
				}
			}
		}
			//card need to see other direction
			temp_card = this.handCard.get(0);
		//	System.out.println("discard from complayer");
			return new Decide(false,temp_card.getType(),-1,-1,temp_card);
	}

	public int UseMap(int pos,int feature){
		if(feature == Command.ID_PATH_REALGOAL){
			this.mineFound = true;
			if(pos == 0){
				this.minepos = new Point(8,0);
			}else if(pos == 1){
				this.minepos = new Point(8,2);
			}else{
				this.minepos = new Point(8,4);
			}
			this.mine[pos] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_REALGOAL);
		}else if(feature == Command.ID_PATH_FAKEGOAL1){
			this.mine[pos] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_FAKEGOAL1);
		}else{
			this.mine[pos] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_FAKEGOAL2);
		}
		if(mineFound == false){
			if(pos == 0){
				if(mine[1] != null){
					mine[2] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_REALGOAL);
					this.minepos = new Point(8,4);
					mineFound = true;
				}else if(mine[2] != null){
					mine[1] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_REALGOAL);
					this.minepos = new Point(8,2);
					mineFound = true;
				}
			}else if(pos == 1){
				if(mine[0] != null){
					mine[2] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_REALGOAL);
					this.minepos = new Point(8,4);
					mineFound = true;
				}else if(mine[2] != null){
					mine[0] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_REALGOAL);
					this.minepos = new Point(8,0);
					mineFound = true;
				}
			}else if(pos == 2){
				if(mine[0] != null){
					mine[1] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_REALGOAL);
					this.minepos = new Point(8,2);
					mineFound = true;
				}else if(mine[1] != null){
					mine[0] = new Card(Command.CARDTYPE_PATH,Command.ID_PATH_REALGOAL);
					this.minepos = new Point(8,0);
					mineFound = true;
				}
			}
		}
		return 0;
	}

	ComPlayer(int myid){
		super();
		this.d = 0;
		this.evaluation = new double[4];
		this.evaluation[0] = 0;
		this.evaluation[1] = 0;
		this.evaluation[2] = 0;
		this.evaluation[3] = 0;
		this.mine = new Card [3];
		this.myid = myid;
		this.mine[0] = null;
		this.mine[1] = null;
		this.mine[2] = null;
		this.mineFound = false;
		this.avgValue = 0;
	}
}
