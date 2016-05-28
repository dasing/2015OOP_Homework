import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GUIController {

	private int playerRID;
	private int leftComputerRID;
	private int topComputerRID;
	private int rightComputerRID;

	private PlayerPanel player;

	private ComputerPanel leftComputer;
	private ComputerPanel topComputer;
	private ComputerPanel rightComputer;

	private MineBoardPanel mineBoard;

	private RoleDeckPanel roleDeck;
	private RewardDeckPanel rewardDeck;
	private DrawDeckPanel drawDeck;
	private DiscardFaceUpDeckPanel discardFaceUpDeck;
	private DiscardFaceDownDeckPanel discardFaceDownDeck;

	private BackgroundPanel background;
	private AnimationPanel animate;

	private GameFrame gframe;

	private boolean isDiscardMode = false;
	private int numDrawTotal;
	public PlayerAction pa;

	private Thread lthread;

	public GUIController() {

    	playerRID        = Fieldname.ID_ROTATE_PLAYER;
    	leftComputerRID  = Fieldname.ID_ROTATE_LEFT_COMPUTER;
    	topComputerRID   = Fieldname.ID_ROTATE_TOP_COMPUTER;
    	rightComputerRID = Fieldname.ID_ROTATE_RIGHT_COMPUTER;

    	//init player
    	int playerRole = Command.ID_ROLE_EMPTY;

    	ArrayList<Integer> status = new ArrayList<Integer>();
    	status.add(Command.ID_ACTION_EMPTY);
    	status.add(Command.ID_ACTION_EMPTY);
    	status.add(Command.ID_ACTION_EMPTY);

    	ArrayList<Integer> handFaceUpType = new ArrayList<Integer>();
    	handFaceUpType.add(Command.CARDTYPE_PATH);
    	handFaceUpType.add(Command.CARDTYPE_PATH);
    	handFaceUpType.add(Command.CARDTYPE_PATH);
    	handFaceUpType.add(Command.CARDTYPE_PATH);
    	handFaceUpType.add(Command.CARDTYPE_PATH);
    	handFaceUpType.add(Command.CARDTYPE_PATH);

    	ArrayList<Integer> handFaceUpID = new ArrayList<Integer>();
    	handFaceUpID.add(Command.ID_PATH_EMPTY);
    	handFaceUpID.add(Command.ID_PATH_EMPTY);
    	handFaceUpID.add(Command.ID_PATH_EMPTY);
    	handFaceUpID.add(Command.ID_PATH_EMPTY);
    	handFaceUpID.add(Command.ID_PATH_EMPTY);
    	handFaceUpID.add(Command.ID_PATH_EMPTY);

		player = new PlayerPanel(playerRole, status, handFaceUpType, handFaceUpID, playerRID);

		//init computers
    	int computerRole = Command.ID_ROLE_EMPTY;

		leftComputer  = new ComputerPanel(computerRole, status, leftComputerRID);
		topComputer   = new ComputerPanel(computerRole, status, topComputerRID);
		rightComputer = new ComputerPanel(computerRole, status, rightComputerRID);

		//init mineboard
		int cardsType[][] = new int[Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH][Command.NUM_MINEBOARD_PANEL_CARDS_HEIGHT];
		int cardsID[][] = new int[Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH][Command.NUM_MINEBOARD_PANEL_CARDS_HEIGHT];

		for (int w = 0; w < Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH; w++) {
			for (int h = 0; h < Command.NUM_MINEBOARD_PANEL_CARDS_HEIGHT; h++) {
				cardsType[w][h] = Command.CARDTYPE_PATH;
				cardsID[w][h] = Command.ID_PATH_EMPTY;
			}
		}

		mineBoard = new MineBoardPanel(cardsType, cardsID);

		//init decks
		roleDeck = new RoleDeckPanel();
		rewardDeck = new RewardDeckPanel();
		drawDeck = new DrawDeckPanel();
		discardFaceUpDeck = new DiscardFaceUpDeckPanel(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY);
		discardFaceDownDeck = new DiscardFaceDownDeckPanel();

		//init background
		background = new BackgroundPanel();

		//init animation
		animate = new AnimationPanel();

        gframe = new GameFrame(mineBoard, player,	
		 			 		   leftComputer, topComputer, rightComputer,
					 		   roleDeck, rewardDeck, drawDeck,
					 		   discardFaceUpDeck, discardFaceDownDeck,
					 		   background, animate);

        pa = new PlayerAction();

//test-----------------------------------------------
        // System.out.println(player.getWidth());
        // System.out.println(player.getHeight());
        // System.out.println(leftComputer.getWidth());
        // System.out.println(leftComputer.getHeight());
        // System.out.println(mineBoard.getWidth());
        // System.out.println(mineBoard.getHeight());
        // System.out.println(roleDeck.getWidth());
        // System.out.println(roleDeck.getHeight());
        // System.out.println(Fieldname.getGameFrameWidth());
        // System.out.println(Fieldname.getGameFrameHeight());
	}

	public void clearCards() {
		//clear player
		player.role.updateLayout(Command.ID_ROLE_EMPTY);
		for (int i = 0; i < Command.NUM_ACTIONS_PANEL_CARDS; i++)
			player.status.updateLayout(i, Command.ID_ACTION_EMPTY);
		for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++)
			player.hand.updateLayout(i, Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY);

		//clear computers
		leftComputer.role.updateLayout(Command.ID_ROLE_EMPTY);
		topComputer.role.updateLayout(Command.ID_ROLE_EMPTY);
		rightComputer.role.updateLayout(Command.ID_ROLE_EMPTY);
		for (int i = 0; i < Command.NUM_ACTIONS_PANEL_CARDS; i++) {
			leftComputer.status.updateLayout(i, Command.ID_ACTION_EMPTY);
			topComputer.status.updateLayout(i, Command.ID_ACTION_EMPTY);
			rightComputer.status.updateLayout(i, Command.ID_ACTION_EMPTY);
		}
		for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++) {
			leftComputer.hand.updateLayout(i, Command.ID_PATH_EMPTY);
			topComputer.hand.updateLayout(i, Command.ID_PATH_EMPTY);
			rightComputer.hand.updateLayout(i, Command.ID_PATH_EMPTY);
		}

		//clear mineboard
		for (int w = 0; w < Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH; w++)
			for (int h = 0; h < Command.NUM_MINEBOARD_PANEL_CARDS_HEIGHT; h++)
				mineBoard.updateLayout(w, h, Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, false);

		//init draw deck
		drawDeck.updateLayout(Command.ID_PATH_BACK);

		//clear discard decks
		discardFaceUpDeck.updateLayout(Command.CARDTYPE_EMPTY, Command.ID_PATH_EMPTY);
		discardFaceDownDeck.updateLayout(Command.ID_PATH_EMPTY);

		numDrawTotal = Command.NUM_DRAW_TOTAL - Command.NUM_HAND_PANEL_CARDS * 4;
	}

	public int getPlayerRole(ArrayList<Integer> cardsID) {
		int playerPos = -1;

		//get fake backgorund image to simulate UI
        animate.bg = getFrameImage();

        //add animation panel
       	gframe.addAnimationPanel();

       	//display 5 role cards 
        animate.setRoleCardsPos();

		//add click listener
		lthread =Thread.currentThread();
        MouseAdapter ma = new MouseAdapter() {
	    	@Override
	    	public void mousePressed(MouseEvent e) {
        		animate.setClickedRoleCard(e.getX(), e.getY());
	        	if (animate.playerClicked != -1) {
			  		synchronized(lthread) {
		        		lthread.notify();
		      		}
        		}
	     	}
	  	};

        gframe.addMouseListener(ma);
        synchronized(lthread) {
            try {
                lthread.wait();
            } catch (InterruptedException e) {
	    		System.out.println("error");	
            }
        }
        gframe.removeMouseListener(ma);

        //deal clicked card to player and random cards to computer
        animate.dealRoleCards();
        gframe.removeAnimationPanel();

        //change player & computer role card
		player.role.updateLayout(cardsID.get(animate.playerClicked));
		leftComputer.role.updateLayout(Command.ID_ROLE_BACK);
		topComputer.role.updateLayout(Command.ID_ROLE_BACK);
		rightComputer.role.updateLayout(Command.ID_ROLE_BACK);
		animate.delay(1000);

		return animate.playerClicked;
	}

	//deal hands to players
	public void dealCards(ArrayList<Integer> playerCardsType, ArrayList<Integer> playerCardsID) {
		//get fake backgorund image to simulate UI
        animate.bg = getFrameImage();

        //add animation panel
       	gframe.addAnimationPanel();


		for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++) {
			leftComputer.hand.updateLayout(i, Command.ID_PATH_EMPTY);
			topComputer.hand.updateLayout(i, Command.ID_PATH_EMPTY);
			rightComputer.hand.updateLayout(i, Command.ID_PATH_EMPTY);
		}

        //deal hand
        for (int i = 0; i < Command.NUM_PLAYER; i++) {
	        animate.dealHandCards(i);
			switch(i) {
				case Command.ID_LEFTCOMPUTER:
					for (int j = 0; j < Command.NUM_HAND_PANEL_CARDS; j++)
						leftComputer.hand.updateLayout(j, Command.ID_PATH_BACK);
					break;
				case Command.ID_TOPCOMPUTER:
					for (int j = 0; j < Command.NUM_HAND_PANEL_CARDS; j++)
						topComputer.hand.updateLayout(j, Command.ID_PATH_BACK);
					break;
				case Command.ID_RIGHTCOMPUTER:
					for (int j = 0; j < Command.NUM_HAND_PANEL_CARDS; j++)
						rightComputer.hand.updateLayout(j, Command.ID_PATH_BACK);
					break;
				default:
					for (int j = 0; j < Command.NUM_HAND_PANEL_CARDS; j++)
						player.hand.updateLayout(j, playerCardsType.get(j), playerCardsID.get(j));
			}
			animate.bg = getFrameImage();
        }
        gframe.removeAnimationPanel();
	}

	//place start and goals to mineBoard
	public void startRound() {
		//get fake backgorund image to simulate UI
        animate.bg = getFrameImage();

        //add animation panel
       	gframe.addAnimationPanel();

       	//display deal cards animation
       	animate.dealMineBoardEndCards();
        gframe.removeAnimationPanel();

        //put cards on board
		mineBoard.updateLayout(Fieldname.INDEX_START_X, Fieldname.INDEX_START_Y, Command.CARDTYPE_PATH, Command.ID_PATH_START    , false);
		mineBoard.updateLayout(Fieldname.INDEX_GOAL1_X, Fieldname.INDEX_GOAL1_Y, Command.CARDTYPE_PATH, Command.ID_PATH_GOAL_BACK, false);
		mineBoard.updateLayout(Fieldname.INDEX_GOAL2_X, Fieldname.INDEX_GOAL2_Y, Command.CARDTYPE_PATH, Command.ID_PATH_GOAL_BACK, false);
		mineBoard.updateLayout(Fieldname.INDEX_GOAL3_X, Fieldname.INDEX_GOAL3_Y, Command.CARDTYPE_PATH, Command.ID_PATH_GOAL_BACK, false);
	}

	public void setAIAction(PlayerAction curAI, int userID) {
// System.out.println(curAI.isDrop);
// System.out.println(curAI.cardType);
// System.out.println(curAI.cardFeature);
// System.out.println(curAI.dropIndex);
// System.out.println(curAI.pos);
// System.out.println(curAI.recvID);
// System.out.println(curAI.recvPos);
// System.out.println(curAI.discardSize);
        //add animation panel
       	gframe.addAnimationPanel();

		//computer drop a path/action card
		if (curAI.isDrop) {
			//drop a card from hand
			switch(userID) {
				case Command.ID_LEFTCOMPUTER:
					leftComputer.hand.updateLayout(leftComputer.getLastCardIndex(), Command.ID_PATH_EMPTY);
					break;
				case Command.ID_TOPCOMPUTER:
					topComputer.hand.updateLayout(topComputer.getLastCardIndex(), Command.ID_PATH_EMPTY);
					break;
				default:
					rightComputer.hand.updateLayout(rightComputer.getLastCardIndex(), Command.ID_PATH_EMPTY);
			}
			//get fake backgorund image to simulate UI
	        animate.bg = getFrameImage();

			if (curAI.cardType == Command.CARDTYPE_PATH) {
				//flip path card
		       	animate.flipCard(userID, Command.CARDTYPE_PATH, curAI.cardFeature, curAI.isRotated);
		       	//do place animation
				animate.placePathCard(curAI.pos.x, curAI.pos.y);
		        //put path card on board
				mineBoard.updateLayout(curAI.pos.x, curAI.pos.y, Command.CARDTYPE_PATH, curAI.cardFeature, curAI.isRotated);
			} else if (curAI.cardFeature == Command.ID_ACTION_GPS) {
				//flip GPS card
		       	animate.flipCard(userID, Command.CARDTYPE_ACTION, Command.ID_ACTION_GPS, false);
		       	//do place animation
				animate.placeGPSCard(curAI.recvPos);
				//discard GPS card
				animate.discardDroppedCard(Command.CARDTYPE_ACTION, Command.ID_ACTION_GPS);
				//update discard face up deck
				discardFaceUpDeck.updateLayout(Command.CARDTYPE_ACTION, Command.ID_ACTION_GPS);
			} else if (curAI.cardFeature == Command.ID_ACTION_STUPIDBIRD) {
				//flip stupid bird card
		       	animate.flipCard(userID, Command.CARDTYPE_ACTION, Command.ID_ACTION_STUPIDBIRD, false);
		       	//do place animation
				animate.placeStupidBirdCard(curAI.pos.x, curAI.pos.y);
		        //clear path card on board
				mineBoard.updateLayout(curAI.pos.x, curAI.pos.y, Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, false);
				//get fake backgorund image to simulate UI
		        animate.bg = getFrameImage();
				//discard GPS card
				animate.discardDroppedCard(Command.CARDTYPE_ACTION, Command.ID_ACTION_STUPIDBIRD);
				//update discard face up deck
				discardFaceUpDeck.updateLayout(Command.CARDTYPE_ACTION, Command.ID_ACTION_STUPIDBIRD);
			} else {
				//flip action card
		       	animate.flipCard(userID, Command.CARDTYPE_ACTION, curAI.cardFeature, false);
		       	//do place animation
				animate.placeActionCard(curAI.recvID, curAI.recvPos);
				//update status
				int newCardID = curAI.cardFeature < Command.ID_ACTION_UNTOWED ? curAI.cardFeature : Command.ID_ACTION_EMPTY;
				switch(curAI.recvID) {
					case Command.ID_LEFTCOMPUTER:
						leftComputer.status.updateLayout(curAI.recvPos, newCardID);
						break;
					case Command.ID_TOPCOMPUTER:
						topComputer.status.updateLayout(curAI.recvPos, newCardID);
						break;
					case Command.ID_RIGHTCOMPUTER:
						rightComputer.status.updateLayout(curAI.recvPos, newCardID);
						break;
					default:
						player.status.updateLayout(curAI.recvPos, newCardID);
				}
				if (newCardID == Command.ID_ACTION_EMPTY) {	
					//get fake backgorund image to simulate UI
			        animate.bg = getFrameImage();
					//discard action card
					animate.discardDroppedCard(Command.CARDTYPE_ACTION, curAI.cardFeature);
					//update discard face up deck
					discardFaceUpDeck.updateLayout(Command.CARDTYPE_ACTION, curAI.cardFeature);
				}
			}
			if (numDrawTotal > 0) {
				//get fake backgorund image to simulate UI
		        animate.bg = getFrameImage();
		        //draw a card to user
				animate.drawCards(userID, 1);
				//draw a card to hand
				switch(userID) {
					case Command.ID_LEFTCOMPUTER:
						leftComputer.hand.updateLayout(leftComputer.getLastCardIndex()+1, Command.ID_PATH_BACK);
						break;
					case Command.ID_TOPCOMPUTER:
						topComputer.hand.updateLayout(topComputer.getLastCardIndex()+1, Command.ID_PATH_BACK);
						break;
					default:
						rightComputer.hand.updateLayout(rightComputer.getLastCardIndex()+1, Command.ID_PATH_BACK);
				}
				numDrawTotal--;
			}
		} else {
			//drop cards from hand
			System.out.println("discard size = " + curAI.discardSize);
			System.out.println("AI card index = " + leftComputer.getLastCardIndex());
			for (int i = 0; i < curAI.discardSize; i++) {
				switch(userID) {
					case Command.ID_LEFTCOMPUTER:
						leftComputer.hand.updateLayout(leftComputer.getLastCardIndex(), Command.ID_PATH_EMPTY);
						break;
					case Command.ID_TOPCOMPUTER:
						topComputer.hand.updateLayout(topComputer.getLastCardIndex(), Command.ID_PATH_EMPTY);
						break;
					default:
						rightComputer.hand.updateLayout(rightComputer.getLastCardIndex(), Command.ID_PATH_EMPTY);
				}	
			}
			//get fake backgorund image to simulate UI
	        animate.bg = getFrameImage();
	        //flip and discard cards
			animate.discardAICards(userID, curAI.discardSize);
			//update discard face down deck
			discardFaceDownDeck.updateLayout(Command.ID_PATH_BACK);

			int drawNum = numDrawTotal > curAI.discardSize ? curAI.discardSize : numDrawTotal;
			if (drawNum > 0) {
				//get fake backgorund image to simulate UI
		        animate.bg = getFrameImage();
		        //draw a card to user
				animate.drawCards(userID, drawNum);
				//drop cards from hand
				for (int i = 0; i < drawNum; i++) {
					switch(userID) {
						case Command.ID_LEFTCOMPUTER:
							leftComputer.hand.updateLayout(leftComputer.getLastCardIndex()+1, Command.ID_PATH_BACK);
							break;
						case Command.ID_TOPCOMPUTER:
							topComputer.hand.updateLayout(topComputer.getLastCardIndex()+1, Command.ID_PATH_BACK);
							break;
						default:
							rightComputer.hand.updateLayout(rightComputer.getLastCardIndex()+1, Command.ID_PATH_BACK);
					}
				}
				numDrawTotal -= drawNum;
			}
		}

		if (numDrawTotal == 0)
			drawDeck.updateLayout(Command.ID_PATH_EMPTY);

        gframe.removeAnimationPanel();
	}

	public PlayerAction getPlayerAction() {
		//hide player hand
		player.hand.hidePanel();
		gframe.repaint();

		//get fake backgorund image to simulate UI
        animate.bg = getFrameImage();

        //add animation panel
       	gframe.addAnimationPanel();


		if (!isDiscardMode) {
	       	animate.showDiscardButton();
			animate.hideOKButton();
			animate.hideCancelButton();
	    } else {
	       	animate.hideDiscardButton();
			animate.showOKButton();
			animate.showCancelButton();
	    }

	    for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++) {
	    	animate.copyCard(i, player.hand.handCards.get(i).cardType, player.hand.handCards.get(i).cardID, player.hand.handCards.get(i).rotationID);
	    	if (animate.isSelected[i])
		    	animate.selectCard(i);
	    }

		//add click listener
		lthread =Thread.currentThread();
        MouseAdapter ma = new MouseAdapter() {
	    	@Override
	    	public void mousePressed(MouseEvent e) {
    			int mouseX = e.getX();
    			int mouseY = e.getY();

	    		if (!isDiscardMode) {
	    			//player click discard
	    			if (animate.isDiscardClicked(mouseX, mouseY)) {
	    				isDiscardMode = true;

       					animate.hideDiscardButton();
       					animate.showOKButton();
       					animate.showCancelButton();
	    			} else if (animate.getSelectedNum() == 1) {
	    				if (animate.isStatusClicked(mouseX, mouseY) && animate.getSelectedCard().cardType == Command.CARDTYPE_ACTION) {
	    					int clickPos = (Fieldname.getGameFrameInfo(mouseX, mouseY) - 1) % 3;
	    					//card is stop action card
	    					if (animate.getSelectedCard().cardID <= Command.ID_ACTION_HSUANTIEN)
	    						clickPos = animate.getSelectedCard().cardID;
					        pa.isDrop = true;
					        pa.dropIndex = animate.getSelectedIndex();
					        pa.cardType = animate.getSelectedCard().cardType;
					        pa.cardFeature = animate.getSelectedCard().cardID;
					        pa.recvID = (Fieldname.getGameFrameInfo(mouseX, mouseY) - 1) / 3;
					        pa.recvPos = clickPos;
					        synchronized(lthread) {
					    		lthread.notify();
					  		}
	    				} else if (animate.isMineBoardClicked(mouseX, mouseY)) {
	    					int mineBoardIndex = Fieldname.getGameFrameInfo(mouseX, mouseY) - Fieldname.INFO_MINEBOARD;
					        pa.isDrop = true;
					        pa.dropIndex = animate.getSelectedIndex();
					        pa.cardType = animate.getSelectedCard().cardType;
					        pa.cardFeature = animate.getSelectedCard().cardID;
        					pa.pos = new Point(mineBoardIndex % 9, (int)(mineBoardIndex / 9));
					        pa.recvID = -1;
					        pa.recvPos = mineBoardIndex / 18;			        
        					pa.isRotated = animate.getSelectedCard().rotationID == Fieldname.ID_ROTATE_TOP_COMPUTER;
					        synchronized(lthread) {
					    		lthread.notify();
	    					}
	    				}
	    			}
	    			if (animate.isPlayerHandClicked(mouseX, mouseY)) {
	    				int handPos = Fieldname.getGameFrameInfo(mouseX, mouseY) - Fieldname.INFO_PLAYER_HAND1;
	    				if (animate.isSelected[handPos] && animate.playerHandCards.get(handPos).cardType == Command.CARDTYPE_PATH) {
		    				player.hand.handCards.get(handPos).rotationID = (player.hand.handCards.get(handPos).rotationID + 2) % 4;
		    				animate.playerHandCards.get(handPos).rotationID = (player.hand.handCards.get(handPos).rotationID + 2) % 4;
		    				animate.repaint();
	    				} else {
		    				for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++)
		    					animate.unselectCard(i);
		    				animate.selectCard(handPos);
	    				}
	    			}
	    		} else {
	    			if (animate.isOKClicked(mouseX, mouseY)) {
	    				isDiscardMode = false;

	    				//discard cards
				        pa.isDrop = false;
					    pa.dropIndex = animate.getSelectedIndex();
        				pa.discardSize = animate.getSelectedNum();
        				pa.discardIndices.clear();
				        for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++)
				        	if (animate.isSelected[i])
				        		pa.discardIndices.add(i);
				        synchronized(lthread) {
				    		lthread.notify();
    					}
	    			} else if (animate.isCancelClicked(e.getX(), e.getY())) {
	    				isDiscardMode = false;

       					animate.showDiscardButton();
       					animate.hideOKButton();
       					animate.hideCancelButton();

	    				//select or unselect hand card
	    				for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++)
	    					animate.unselectCard(i);
	    			} else if (animate.isPlayerHandClicked(mouseX, mouseY)) {
	    				//select or unselect hand card
	    				int handPos = Fieldname.getGameFrameInfo(mouseX, mouseY) - Fieldname.INFO_PLAYER_HAND1;
	    				if (animate.isSelected[handPos])
	    					animate.unselectCard(handPos);
	    				else if (!animate.isSelected[handPos] && animate.getSelectedNum() < 3)
	    					animate.selectCard(handPos);
	    			}
	    		}
	     	}
	  	};

        gframe.addMouseListener(ma);
        synchronized(lthread) {
            try {
                lthread.wait();
            } catch (InterruptedException e) {
	    		System.out.println("error");	
            }
        }
        gframe.removeMouseListener(ma);

        gframe.removeAnimationPanel();
		return pa;
	}

	public void updatePlayer(ArrayList<Integer> cardsType, ArrayList<Integer> cardsID) {
		//hide buttons
       	animate.hideDiscardButton();
		animate.hideOKButton();
		animate.hideCancelButton();
System.out.println("player gets " + cardsType.size());
		//find what and which one to update
		for (int i = cardsType.size(); i < Command.NUM_HAND_PANEL_CARDS; i++) {
			cardsType.add(Command.CARDTYPE_PATH);
			cardsID.add(Command.ID_PATH_EMPTY);
		}

        //add animation panel
       	gframe.addAnimationPanel();

		//computer drop a path/action card
		if (pa.isDrop) {
			if (pa.cardType == Command.CARDTYPE_PATH) {
				animate.setDroppedCard();
		       	//do place animation
				animate.placePathCard(pa.pos.x, pa.pos.y);
		        //put path card on board
				mineBoard.updateLayout(pa.pos.x, pa.pos.y, Command.CARDTYPE_PATH, pa.cardFeature, pa.isRotated);
			} else if (pa.cardFeature == Command.ID_ACTION_GPS) {
				animate.setDroppedCard();
		       	//do place animation
				animate.placeGPSCard(pa.recvPos);
				//discard GPS card
				animate.discardDroppedCard(Command.CARDTYPE_ACTION, Command.ID_ACTION_GPS);
				//update discard face up deck
				discardFaceUpDeck.updateLayout(Command.CARDTYPE_ACTION, Command.ID_ACTION_GPS);
			} else if (pa.cardFeature == Command.ID_ACTION_STUPIDBIRD) {
				animate.setDroppedCard();
		       	//do place animation
				animate.placeStupidBirdCard(pa.pos.x, pa.pos.y);
		        //clear path card on board
				mineBoard.updateLayout(pa.pos.x, pa.pos.y, Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, false);
				//get fake backgorund image to simulate UI
		        animate.bg = getFrameImage();
				//discard GPS card
				animate.discardDroppedCard(Command.CARDTYPE_ACTION, Command.ID_ACTION_STUPIDBIRD);
				//update discard face up deck
				discardFaceUpDeck.updateLayout(Command.CARDTYPE_ACTION, Command.ID_ACTION_STUPIDBIRD);
			} else {
				animate.setDroppedCard();
		       	//do place animation
				animate.placeActionCard(pa.recvID, pa.recvPos);
				//update status
				int newCardID = pa.cardFeature < Command.ID_ACTION_UNTOWED ? pa.cardFeature : Command.ID_ACTION_EMPTY;
				switch(pa.recvID) {
					case Command.ID_LEFTCOMPUTER:
						leftComputer.status.updateLayout(pa.recvPos, newCardID);
						break;
					case Command.ID_TOPCOMPUTER:
						topComputer.status.updateLayout(pa.recvPos, newCardID);
						break;
					case Command.ID_RIGHTCOMPUTER:
						rightComputer.status.updateLayout(pa.recvPos, newCardID);
						break;
					default:
						player.status.updateLayout(pa.recvPos, newCardID);
				}
				if (newCardID == Command.ID_ACTION_EMPTY) {	
					//get fake backgorund image to simulate UI
			        animate.bg = getFrameImage();
					//discard action card
					animate.discardDroppedCard(Command.CARDTYPE_ACTION, pa.cardFeature);
					//update discard face up deck
					discardFaceUpDeck.updateLayout(Command.CARDTYPE_ACTION, pa.cardFeature);
				}
			}
			if (numDrawTotal > 0) {
				//get fake backgorund image to simulate UI
		        animate.bg = getFrameImage();
		        //draw a card to user
				animate.drawCards(Command.ID_PLAYER, 1);	
				numDrawTotal--;
			}
		} else {
	        //flip and discard cards
			animate.discardSelectedCards();
			//update discard face down deck
			discardFaceDownDeck.updateLayout(Command.ID_PATH_BACK);
			//get fake backgorund image to simulate UI
	        animate.bg = getFrameImage();
	        //draw a card to user
	        int drawNum = numDrawTotal > pa.discardSize ? pa.discardSize : numDrawTotal; 
			animate.drawCards(Command.ID_PLAYER, drawNum);
			numDrawTotal -= drawNum;
		}

		//draw cards to hand
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
			player.hand.handCards.get(i).rotationID = Fieldname.ID_ROTATE_PLAYER;
			player.hand.updateLayout(i, cardsType.get(i), cardsID.get(i));
		}

		if (numDrawTotal == 0)
			drawDeck.updateLayout(Command.ID_PATH_EMPTY);

        gframe.removeAnimationPanel();

		//hide player hand
		player.hand.showPanel();
		gframe.repaint();

		isDiscardMode = false;
		animate.hidePlayerHand();
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			animate.unselectCard(i);
	}

	public void showMap(int cardID) {
		System.out.println("in show map");
		mineBoard.updateLayout(8, pa.recvPos * 2, Command.CARDTYPE_PATH, cardID, false);
		gframe.repaint();

		animate.delay(1000);
		mineBoard.updateLayout(8, pa.recvPos * 2, Command.CARDTYPE_PATH, Command.ID_PATH_GOAL_BACK, false);
	}

	public void openGoal(int cardPos, int cardID) {
		mineBoard.updateLayout(8, cardPos * 2, Command.CARDTYPE_PATH, cardID, false);
		gframe.repaint();
	}

	public void openRoleCards(ArrayList<Integer> cardsID) {
		leftComputer.role.updateLayout(cardsID.get(0));
		topComputer.role.updateLayout(cardsID.get(1));
		rightComputer.role.updateLayout(cardsID.get(2));
	}

	public void dealRewards(int winner, ArrayList<Integer> roleCardsID, ArrayList<Integer> rewardCardsID) {
		System.out.println("winner is " + winner);
		//get fake backgorund image to simulate UI
        animate.bg = getFrameImage();

        //add animation panel
       	gframe.addAnimationPanel();

       	//display 4 reward cards 
        animate.setRewardCardsPos();

        ArrayList<Integer> matcher = new ArrayList<Integer>();
		//no winner
		if (winner == -1) {
			//set matcher
			for (int i = 0; i < Command.NUM_DRAW_REWARD; i++)
				matcher.add(-1);
			animate.dealRewards(matcher);
		}

		//bad miner wins
		else if (roleCardsID.get(winner) == Command.ID_ROLE_SABOTEUR) {
			//set matcher
			for (int i = 0; i < Command.NUM_DRAW_REWARD; i++)
				matcher.add(winner);
			animate.showRewards(rewardCardsID);
			animate.dealRewards(matcher);
		}

		//good miner wins
		else {
			//set matcher
			int index = winner;
			for (int i = 0; i < Command.NUM_DRAW_REWARD; i++) {
				if (roleCardsID.get(index) == Command.ID_ROLE_SABOTEUR)
					index++;
				matcher.add(index % Command.NUM_DRAW_REWARD);
				index = (index + 1) % Command.NUM_DRAW_REWARD;
			}
			animate.showRewards(rewardCardsID);
			animate.dealRewards(matcher);
		}

	    gframe.removeAnimationPanel();
	}

	public void showRank(ArrayList<Integer> lcCards, ArrayList<Integer> tcCards, ArrayList<Integer> rcCards, ArrayList<Integer> players) {
		//get fake backgorund image to simulate UI
        animate.bg = getFrameImage();
        clearCards();
        //add animation panel
       	gframe.addAnimationPanel();
       	
		animate.showRewards(lcCards, tcCards, rcCards, players);
	}

	private BufferedImage getFrameImage() {
		gframe.repaint();
		animate.delay(100);

        Component comp = gframe.getContentPane();
        BufferedImage img = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
    	BufferedImage result = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
    	comp.paint(img.getGraphics());

    	try {
			ImageIO.write(img, "PNG", new File(Fieldname.IMAGEPATH_ANIMATE_BG));
			animate.delay(100);
			result = ImageIO.read(new File(Fieldname.IMAGEPATH_ANIMATE_BG));
    	} catch (IOException ex) {
			System.out.println("Failed to save image...");	
        }
		return result;
	}
}


