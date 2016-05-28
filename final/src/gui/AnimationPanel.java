import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Point;

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

public class AnimationPanel extends BasePanel {

	ArrayList<CardPanel> roleCards;
	ArrayList<CardPanel> handCards;
	ArrayList<CardPanel> playerHandCards;
	ArrayList<CardPanel> endCards;
	ArrayList<CardPanel> rewardCards;
	CardPanel playerRole;
	CardPanel playerReward;
	int playerClicked;

	CardPanel rotated;
	CardPanel droppedCard;
	CardPanel pathUpright;
	CardPanel pathReverse;
	double posX, posY;

	ArrayList<CardPanel> discardCards;
	double posesX[], posesY[];
	boolean isSelected[];

	ButtonPanel discardButton, okButton, cancelButton;

	int paddingX;
	int paddingY;

	public BufferedImage bg;

	public AnimationPanel() {
    	super(Fieldname.getGameFrameWidth(), Fieldname.getGameFrameHeight(), Fieldname.ID_ROTATE_PLAYER);
    	setBackground(Fieldname.C_TRANSPARENT);
    	roleCards = new ArrayList<CardPanel>();
    	handCards = new ArrayList<CardPanel>();
    	playerHandCards = new ArrayList<CardPanel>();
    	endCards = new ArrayList<CardPanel>();
    	rewardCards = new ArrayList<CardPanel>();

    	discardCards = new ArrayList<CardPanel>();
    	posesX = new double[Command.NUM_DRAW_HAND];
    	posesY = new double[Command.NUM_DRAW_HAND];
    	isSelected = new boolean[Command.NUM_DRAW_HAND];

    	for (int i = 0; i < Command.NUM_DRAW_ROLE; i++)
    		roleCards.add(new CardPanel(Command.CARDTYPE_ROLE, Command.ID_ROLE_BACK, Fieldname.ID_ROTATE_PLAYER));
    	for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
    		isSelected[i] = false;
    		handCards.add(new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_BACK, Fieldname.ID_ROTATE_PLAYER));
    		playerHandCards.add(new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, Fieldname.ID_ROTATE_PLAYER));
    	}
    	for (int i = 0; i < Command.NUM_DRAW_ENDS; i++)
    		endCards.add(new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_BACK, Fieldname.ID_ROTATE_PLAYER));
    	for (int i = 0; i < Command.NUM_DISCARD_MAX; i++)
    		discardCards.add(new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, Fieldname.ID_ROTATE_PLAYER));
    	for (int i = 0; i < Command.NUM_DRAW_REWARD; i++)
    		rewardCards.add(new CardPanel(Command.CARDTYPE_REWARD, Command.ID_REWARD_BACK, Fieldname.ID_ROTATE_PLAYER));

    	playerRole = new CardPanel(Command.CARDTYPE_ROLE, Command.ID_ROLE_EMPTY, Fieldname.ID_ROTATE_PLAYER);
    	playerReward = new CardPanel(Command.CARDTYPE_REWARD, Command.ID_REWARD_EMPTY, Fieldname.ID_ROTATE_PLAYER);

    	droppedCard = new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, Fieldname.ID_ROTATE_PLAYER);
    	rotated = new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, Fieldname.ID_ROTATE_PLAYER);

    	pathUpright = new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, Fieldname.ID_ROTATE_PLAYER);
    	pathReverse = new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY, Fieldname.ID_ROTATE_TOP_COMPUTER);

		discardButton = new ButtonPanel(Fieldname.BUTTON_DISCARD);
		okButton      = new ButtonPanel(Fieldname.BUTTON_OK);
		cancelButton  = new ButtonPanel(Fieldname.BUTTON_CANCEL);

    	createLayout();
	}

	public void createLayout() {
		paddingX = (Fieldname.getGameFrameWidth() - Fieldname.getRoleCardsPanelWidth()) / 2;
		paddingY = (Fieldname.getGameFrameHeight() - Fieldname.getRoleCardsPanelHeight()) / 2;

		for (int i = 0; i < Command.NUM_DRAW_ROLE; i++)
			add(roleCards.get(i));
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			add(handCards.get(i));
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			add(playerHandCards.get(i));
		for (int i = 0; i < Command.NUM_DRAW_ENDS; i++)
			add(endCards.get(i));
		for (int i = 0; i < Command.NUM_DISCARD_MAX; i++)
			add(discardCards.get(i));
		for (int i = 0; i < Command.NUM_DRAW_REWARD; i++)
			add(rewardCards.get(i));

		hideRoles();
		hideHand();
		hidePlayerHand();
		hideEnds();
		hideDiscards();
		hideRewards();

		add(playerRole);
		add(playerReward);
		add(droppedCard);
		add(rotated);
		add(pathUpright);
		add(pathReverse);

		playerRole.showPanel();
		playerReward.showPanel();
		droppedCard.showPanel();
		rotated.showPanel();
		pathUpright.showPanel();
		pathReverse.showPanel();

		//add buttons
		int x, y;

		x = Fieldname.getDiscardButtonPosition().x;
		y = Fieldname.getDiscardButtonPosition().y;
		discardButton.setLocation(x, y);
    	discardButton.hidePanel();
    	add(discardButton);

		x = Fieldname.getOKButtonPosition().x;
		y = Fieldname.getOKButtonPosition().y;
		okButton.setLocation(x, y);
    	okButton.hidePanel();
    	add(okButton);

		x = Fieldname.getCancelButtonPosition().x;
		y = Fieldname.getCancelButtonPosition().y;
		cancelButton.setLocation(x, y);
    	cancelButton.hidePanel();
    	add(cancelButton);

    	showPanel();
	}

	public void showRoles() {
		for (int i = 0; i < Command.NUM_DRAW_ROLE; i++)
			roleCards.get(i).showPanel();
	}

	public void hideRoles() {
		for (int i = 0; i < Command.NUM_DRAW_ROLE; i++)
			roleCards.get(i).hidePanel();
	}

	public void showHand() {
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			handCards.get(i).showPanel();
	}

	public void hideHand() {
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
			handCards.get(i).updateImage(Command.CARDTYPE_PATH, Command.ID_PATH_BACK);
			handCards.get(i).hidePanel();
		}
	}

	public void showPlayerHand() {
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			playerHandCards.get(i).showPanel();
	}

	public void hidePlayerHand() {
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
			playerHandCards.get(i).updateImage(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY);
			playerHandCards.get(i).hidePanel();
		}
	}

	public void showEnds() {
		for (int i = 0; i < Command.NUM_DRAW_ENDS; i++)
			endCards.get(i).showPanel();
	}

	public void hideEnds() {
		for (int i = 0; i < Command.NUM_DRAW_ENDS; i++)
			endCards.get(i).hidePanel();
	}

	public void showDiscards() {
		for (int i = 0; i < Command.NUM_DISCARD_MAX; i++)
			discardCards.get(i).showPanel();
	}

	public void hideDiscards() {
		for (int i = 0; i < Command.NUM_DISCARD_MAX; i++)
			discardCards.get(i).hidePanel();
	}

	public void showRewards() {
		for (int i = 0; i < Command.NUM_DRAW_ROLE; i++)
			rewardCards.get(i).showPanel();
	}

	public void hideRewards() {
		for (int i = 0; i < Command.NUM_DRAW_REWARD; i++) {
    		rewardCards.get(i).updateImage(Command.CARDTYPE_REWARD, Command.ID_REWARD_BACK);
			rewardCards.get(i).hidePanel();
		}
	}

	public void showPlayerRole(int pos, int cardID) {
		playerRole.updateImage(Command.CARDTYPE_ROLE, cardID);
		playerRole.repaint();
	}

	public void hidePlayerRole() {
		playerRole.updateImage(Command.CARDTYPE_ROLE, Command.ID_ROLE_EMPTY);
		playerRole.repaint();
	}

	public void showPlayerReward(int pos, int cardID) {
		playerReward.updateImage(Command.CARDTYPE_REWARD, cardID);
		playerReward.repaint();
	}

	public void hidePlayerReward() {
		playerReward.updateImage(Command.CARDTYPE_REWARD, Command.ID_REWARD_EMPTY);
		playerReward.repaint();
	}

	public void showCard(int cardType, int cardID, int cardRotation) {
		droppedCard.rotationID = cardRotation;
		droppedCard.updateImage(cardType, cardID);
		droppedCard.showPanel();
		droppedCard.repaint();
	}

	public void hideCard() {
		// droppedCard.updateImage(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY);
		// droppedCard.repaint();
		droppedCard.hidePanel();
		repaint();
	}

	public void showDiscardButton() {
       	discardButton.showPanel();
       	repaint();
	}

	public void showOKButton() {
       	okButton.showPanel();
       	repaint();
	}

	public void showCancelButton() {
       	cancelButton.showPanel();
       	repaint();
	}

	public void hideDiscardButton() {
       	discardButton.hidePanel();
       	repaint();
	}

	public void hideOKButton() {
       	okButton.hidePanel();
       	repaint();
	}

	public void hideCancelButton() {
       	cancelButton.hidePanel();
       	repaint();
	}

	public void setRoleCardsPos() {
		for (int i = 0; i < Command.NUM_DRAW_ROLE; i++) {
			double startX = (double)(Fieldname.getRoleDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
			double startY = (double)(Fieldname.getRoleDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);
			double targetX = (double)(paddingX + Fieldname.getAnimateCardsPosition(i, Command.NUM_DRAW_ROLE).x);
			double targetY = (double)(paddingY + Fieldname.getAnimateCardsPosition(i, Command.NUM_DRAW_ROLE).y);
			roleCards.get(i).showPanel();
			roleCards.get(i).setLocation((int)startX, (int)startY);

			int timeSlice = 100;
			double moveX = (targetX - startX) / (double)timeSlice;
			double moveY = (targetY - startY) / (double)timeSlice;
			while (startX < targetX && startY < targetY) {
				try {
					Thread.sleep(8);
					startX += moveX;
					startY += moveY;
					roleCards.get(i).setLocation((int)startX, (int)startY);
					repaint();
				} catch (InterruptedException ex) {}
			}

			roleCards.get(i).setLocation((int)targetX, (int)targetY);
		}
	}

	public void setClickedRoleCard(int mouseX, int mouseY) {
		int yTop = paddingY + Fieldname.getAnimateCardsPosition(0, Command.NUM_DRAW_ROLE).y;
		int yBottom = yTop + Fieldname.getCardPanelHeight();
		if (mouseY < yTop || mouseY > yBottom) {
			playerClicked = -1;
			return;
		}

		for (int i = 0; i < Command.NUM_DRAW_ROLE; i++) {
			int xLeft = paddingX + Fieldname.getAnimateCardsPosition(i, Command.NUM_DRAW_ROLE).x;
			int xRight = xLeft + Fieldname.getCardPanelWidth();
			if (mouseX >= xLeft && mouseX <= xRight) {
				playerClicked = i;
				return;
			}
		}
        playerClicked = -1;
	}

	public void dealRoleCards() {
		int matcher[] = new int[Command.NUM_PLAYER];
		for (int i = 0; i < Command.NUM_DRAW_ROLE - 1; i++)
			matcher[i] = (playerClicked + i + 2) % Command.NUM_DRAW_ROLE;

		for (int i = 0; i < Command.NUM_DRAW_ROLE - 1; i++) {
			double startX = (double)(paddingX + Fieldname.getAnimateCardsPosition(matcher[i], Command.NUM_DRAW_ROLE).x);
			double startY = (double)(paddingY + Fieldname.getAnimateCardsPosition(matcher[i], Command.NUM_DRAW_ROLE).y);
			double targetX, targetY;
			switch(i) {
				case Command.ID_LEFTCOMPUTER:
					targetX = Fieldname.getLeftComputerPanelPosition().x + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
					targetY = Fieldname.getLeftComputerPanelPosition().y + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
					break;
				case Command.ID_TOPCOMPUTER:
					targetX = Fieldname.getTopComputerPanelPosition().x + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
					targetY = Fieldname.getTopComputerPanelPosition().y + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
					break;
				case Command.ID_RIGHTCOMPUTER:
					targetX = Fieldname.getRightComputerPanelPosition().x + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
					targetY = Fieldname.getRightComputerPanelPosition().y + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
					break;
				default:
					targetX = Fieldname.getPlayerPanelPosition().x + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_PLAYER).x + Fieldname.D_CARD_PANEL_PADDING;
					targetY = Fieldname.getPlayerPanelPosition().y + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_PLAYER).y + Fieldname.D_CARD_PANEL_PADDING;
			}
			roleCards.get(matcher[i]).setLocation((int)startX, (int)startY);

			int timeSlice = 80;
			double moveX = (targetX - startX) / (double)timeSlice;
			double moveY = (targetY - startY) / (double)timeSlice;
			boolean reachTarget = false;
			while (!reachTarget) {
				try {
					Thread.sleep(8);
					startX += moveX;
					startY += moveY;
					roleCards.get(matcher[i]).setLocation((int)startX, (int)startY);
					repaint();
				} catch (InterruptedException ex) {}

				if ((moveX >= 0 && startX >= targetX) || (moveX < 0 && startX <= targetX))
					if ((moveY >= 0 && startY >= targetY) || (moveY < 0 && startY <= targetY))
						reachTarget = true;
			}

			roleCards.get(matcher[i]).setLocation((int)targetX, (int)targetY);
		}
		delay(100);
		hideRoles();
	}

	public void dealHandCards(int pos) {
		double startX = (double)(Fieldname.getDrawDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
		double startY = (double)(Fieldname.getDrawDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);

		int timeSlice;
		double targetX, targetY;
		switch(pos) {
			case Command.ID_LEFTCOMPUTER:
				timeSlice = 200;
				targetX = Fieldname.getLeftComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				targetY = Fieldname.getLeftComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			case Command.ID_TOPCOMPUTER:
				timeSlice = 120;
				targetX = Fieldname.getTopComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				targetY = Fieldname.getTopComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			case Command.ID_RIGHTCOMPUTER:
				timeSlice = 100;
				targetX = Fieldname.getRightComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				targetY = Fieldname.getRightComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			default:
				timeSlice = 180;
				targetX = Fieldname.getPlayerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).x + Fieldname.D_CARD_PANEL_PADDING;
				targetY = Fieldname.getPlayerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).y + Fieldname.D_CARD_PANEL_PADDING;
		}

		for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
			handCards.get(i).setLocation((int)startX, (int)startY);
			handCards.get(i).showPanel();
			handCards.get(i).repaint();
		}

		double moveX = (targetX - startX) / (double)timeSlice;
		double moveY = (targetY - startY) / (double)timeSlice;
		int curTime = 0, space = 10;
		while (++curTime < timeSlice + space * (Command.NUM_DRAW_HAND - 1)) {
			try {
				Thread.sleep(8);
				startX += moveX;
				startY += moveY;
				for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
					if (curTime >= i * space && curTime < timeSlice + i * space) {
						handCards.get(i).setLocation((int)(startX - i * space * moveX), 
													   (int)(startY - i * space * moveY));
					}
				}
				repaint();
			} catch (InterruptedException ex) {}
		}

		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			handCards.get(i).setLocation((int)targetX, (int)targetY);
		repaint();

		delay(200);
		hideHand();
	}

	public void dealMineBoardEndCards() {

		int timeSlice[] = {70, 100, 120, 140};
		double targetX[] = new double[Command.NUM_DRAW_ENDS];
		double targetY[] = new double[Command.NUM_DRAW_ENDS];

		targetX[0] = Fieldname.getMineBoardPanelPosition().x + Fieldname.getMineBoardPanelCardPosition(Fieldname.INDEX_START_X, Fieldname.INDEX_START_Y).x;
		targetY[0] = Fieldname.getMineBoardPanelPosition().y + Fieldname.getMineBoardPanelCardPosition(Fieldname.INDEX_START_X, Fieldname.INDEX_START_Y).y;
		targetX[1] = Fieldname.getMineBoardPanelPosition().x + Fieldname.getMineBoardPanelCardPosition(Fieldname.INDEX_GOAL1_X, Fieldname.INDEX_GOAL1_Y).x;
		targetY[1] = Fieldname.getMineBoardPanelPosition().y + Fieldname.getMineBoardPanelCardPosition(Fieldname.INDEX_GOAL1_X, Fieldname.INDEX_GOAL1_Y).y;
		targetX[2] = Fieldname.getMineBoardPanelPosition().x + Fieldname.getMineBoardPanelCardPosition(Fieldname.INDEX_GOAL2_X, Fieldname.INDEX_GOAL2_Y).x;
		targetY[2] = Fieldname.getMineBoardPanelPosition().y + Fieldname.getMineBoardPanelCardPosition(Fieldname.INDEX_GOAL2_X, Fieldname.INDEX_GOAL2_Y).y;
		targetX[3] = Fieldname.getMineBoardPanelPosition().x + Fieldname.getMineBoardPanelCardPosition(Fieldname.INDEX_GOAL3_X, Fieldname.INDEX_GOAL3_Y).x;
		targetY[3] = Fieldname.getMineBoardPanelPosition().y + Fieldname.getMineBoardPanelCardPosition(Fieldname.INDEX_GOAL3_X, Fieldname.INDEX_GOAL3_Y).y;

		for (int i = 0; i < Command.NUM_DRAW_ENDS; i++) {
			double startX = (double)(Fieldname.getDrawDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
			double startY = (double)(Fieldname.getDrawDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);

			endCards.get(i).setLocation((int)startX, (int)startY);
			endCards.get(i).showPanel();

			double moveX = (targetX[i] - startX) / (double)timeSlice[i];
			double moveY = (targetY[i] - startY) / (double)timeSlice[i];
			boolean reachTarget = false;
			while (!reachTarget) {
				try {
					Thread.sleep(8);
					startX += moveX;
					startY += moveY;
					endCards.get(i).setLocation((int)startX, (int)startY);
					repaint();
				} catch (InterruptedException ex) {}
				if ((moveX >= 0 && startX >= targetX[i]) || (moveX < 0 && startX <= targetX[i]))
					if ((moveY >= 0 && startY >= targetY[i]) || (moveY < 0 && startY <= targetY[i]))
						reachTarget = true;
			}
			endCards.get(i).setLocation((int)targetX[i], (int)targetY[i]);
		}
		delay(200);
		hideEnds();
	}

	public void flipCard(int userFrom, int cardType, int cardID, boolean isRotated) {
		int cardRotation = isRotated ? Fieldname.ID_ROTATE_TOP_COMPUTER : Fieldname.ID_ROTATE_PLAYER;

		switch(userFrom) {
			case Command.ID_LEFTCOMPUTER:
				posX = Fieldname.getLeftComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				posY = Fieldname.getLeftComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			case Command.ID_TOPCOMPUTER:
				posX = Fieldname.getTopComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				posY = Fieldname.getTopComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			default:
				posX = Fieldname.getRightComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				posY = Fieldname.getRightComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
		}
		droppedCard.setLocation((int)posX, (int)posY);
		showCard(cardType, cardID, cardRotation);
		repaint();
		delay(200);
	}

	public void copyCard(int handCardPos, int cardType, int cardID, int rotateID) {
		posesX[handCardPos] = Fieldname.getPlayerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).x + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_HAND_PANEL_CARDS, Fieldname.ID_ROTATE_PLAYER).x; 
		posesY[handCardPos] = Fieldname.getPlayerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).y + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_HAND_PANEL_CARDS, Fieldname.ID_ROTATE_PLAYER).y;

		playerHandCards.get(handCardPos).setLocation((int)posesX[handCardPos], (int)posesY[handCardPos]);
		playerHandCards.get(handCardPos).rotationID = rotateID;
		playerHandCards.get(handCardPos).updateImage(cardType, cardID);
		playerHandCards.get(handCardPos).showPanel();
		playerHandCards.get(handCardPos).repaint();

		repaint();
	}

	public void selectCard(int handCardPos) {
		posesX[handCardPos] = Fieldname.getPlayerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).x + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_HAND_PANEL_CARDS, Fieldname.ID_ROTATE_PLAYER).x; 
		posesY[handCardPos] = Fieldname.getPlayerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).y + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_HAND_PANEL_CARDS, Fieldname.ID_ROTATE_PLAYER).y - 20;

		playerHandCards.get(handCardPos).setLocation((int)posesX[handCardPos], (int)posesY[handCardPos]);
		playerHandCards.get(handCardPos).showPanel();
		playerHandCards.get(handCardPos).repaint();

		isSelected[handCardPos] = true;
		repaint();
	}

	public void unselectCard(int handCardPos) {
		posesX[handCardPos] = Fieldname.getPlayerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).x + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_HAND_PANEL_CARDS, Fieldname.ID_ROTATE_PLAYER).x; 
		posesY[handCardPos] = Fieldname.getPlayerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).y + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_HAND_PANEL_CARDS, Fieldname.ID_ROTATE_PLAYER).y;

		playerHandCards.get(handCardPos).setLocation((int)posesX[handCardPos], (int)posesY[handCardPos]);
		playerHandCards.get(handCardPos).showPanel();
		playerHandCards.get(handCardPos).repaint();
		
		isSelected[handCardPos] = false;
		repaint();
	}

	public boolean isDiscardClicked(int mouseX, int mouseY) {
		return (mouseX >= Fieldname.getDiscardButtonPosition().x && mouseX <= Fieldname.getDiscardButtonPosition().x + Fieldname.D_BUTTON_WIDTH)
		    && (mouseY >= Fieldname.getDiscardButtonPosition().y && mouseY <= Fieldname.getDiscardButtonPosition().y + Fieldname.D_BUTTON_HEIGHT);
	}

	public boolean isOKClicked(int mouseX, int mouseY) {
		return (mouseX >= Fieldname.getOKButtonPosition().x && mouseX <= Fieldname.getOKButtonPosition().x + Fieldname.D_BUTTON_WIDTH)
		    && (mouseY >= Fieldname.getOKButtonPosition().y && mouseY <= Fieldname.getOKButtonPosition().y + Fieldname.D_BUTTON_HEIGHT);
	}

	public boolean isCancelClicked(int mouseX, int mouseY) {
		return (mouseX >= Fieldname.getCancelButtonPosition().x && mouseX <= Fieldname.getCancelButtonPosition().x + Fieldname.D_BUTTON_WIDTH)
		    && (mouseY >= Fieldname.getCancelButtonPosition().y && mouseY <= Fieldname.getCancelButtonPosition().y + Fieldname.D_BUTTON_HEIGHT);
	}

	public int getSelectedNum() {
		int num = 0;
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			if (isSelected[i])
				num++;
		return num;
	}

	public int getSelectedIndex() {
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			if (isSelected[i])
				return i;
		return -1;
	}

	public CardPanel getSelectedCard() {
		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			if (isSelected[i])
				return playerHandCards.get(i);
		return null;
	}

	public boolean isStatusClicked(int mouseX, int mouseY) {
		int type = Fieldname.getGameFrameInfo(mouseX, mouseY);
		return type >= Fieldname.INFO_LEFTCOMPUTER_STATUS1 && type <= Fieldname.INFO_PLAYER_STATUS3;
	}

	public boolean isLeftComputerStatusClicked(int mouseX, int mouseY) {
		int type = Fieldname.getGameFrameInfo(mouseX, mouseY);
		return type >= Fieldname.INFO_LEFTCOMPUTER_STATUS1 && type <= Fieldname.INFO_LEFTCOMPUTER_STATUS3;
	}

	public boolean isTopComputerStatusClicked(int mouseX, int mouseY) {
		int type = Fieldname.getGameFrameInfo(mouseX, mouseY);
		return type >= Fieldname.INFO_TOPCOMPUTER_STATUS1 && type <= Fieldname.INFO_TOPCOMPUTER_STATUS3;
	}

	public boolean isRightComputerStatusClicked(int mouseX, int mouseY) {
		int type = Fieldname.getGameFrameInfo(mouseX, mouseY);
		return type >= Fieldname.INFO_RIGHTCOMPUTER_STATUS1 && type <= Fieldname.INFO_RIGHTCOMPUTER_STATUS3;
	}

	public boolean isPlayerStatusClicked(int mouseX, int mouseY) {
		int type = Fieldname.getGameFrameInfo(mouseX, mouseY);
		return type >= Fieldname.INFO_PLAYER_STATUS1 && type <= Fieldname.INFO_PLAYER_STATUS3;
	}

	public boolean isPlayerHandClicked(int mouseX, int mouseY) {
		int type = Fieldname.getGameFrameInfo(mouseX, mouseY);
		return type >= Fieldname.INFO_PLAYER_HAND1 && type <= Fieldname.INFO_PLAYER_HAND6;
	}

	public boolean isMineBoardClicked(int mouseX, int mouseY) {
		int type = Fieldname.getGameFrameInfo(mouseX, mouseY);
		return type >= Fieldname.INFO_MINEBOARD;
	}

	public void discardSelectedCards() {
		double targetX = (double)(Fieldname.getDiscardFaceDownDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
		double targetY = (double)(Fieldname.getDiscardFaceDownDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);

		int timeSlice = 100;
		double moveX[] = new double[Command.NUM_DRAW_HAND];
		double moveY[] = new double[Command.NUM_DRAW_HAND];

		for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
			if (isSelected[i]) {
				moveX[i] = (targetX - posesX[i]) / (double)timeSlice;
				moveY[i] = (targetY - posesY[i]) / (double)timeSlice;
			}
		}

		int curTime = 0;
		while (++curTime < timeSlice) {
			try {
				Thread.sleep(8);
				for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
					if (isSelected[i]) {
						posesX[i] += moveX[i];
						posesY[i] += moveY[i];
						playerHandCards.get(i).setLocation((int)posesX[i], (int)posesY[i]);
					}
				}
				repaint();
			} catch (InterruptedException ex) {}
		}

		for (int i = 0; i < Command.NUM_DRAW_HAND; i++)
			if (isSelected[i])
				playerHandCards.get(i).setLocation((int)targetX, (int)targetY);

		delay(200);

		for (int i = 0; i < Command.NUM_DRAW_HAND; i++) {
			if (isSelected[i])
				playerHandCards.get(i).updateImage(Command.CARDTYPE_PATH, Command.ID_PATH_EMPTY);
			isSelected[i] = false;
		}

		repaint();
	}

	public void discardDroppedCard(int cardType, int cardID) {
		posesX[0] = posX;
		posesY[0] = posY;

		handCards.get(0).setLocation((int)posX, (int)posY);
		handCards.get(0).updateImage(cardType, cardID);
		handCards.get(0).showPanel();
		handCards.get(0).repaint();

		isSelected[0] = true;

		double targetX = (double)(Fieldname.getDiscardFaceUpDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
		double targetY = (double)(Fieldname.getDiscardFaceUpDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);

		int timeSlice = 100;
		double moveX = (targetX - posX) / (double)timeSlice;
		double moveY = (targetY - posY) / (double)timeSlice;

		int curTime = 0;
		while (++curTime < timeSlice) {
			try {
				Thread.sleep(8);
				posX += moveX;
				posY += moveY;
				handCards.get(0).setLocation((int)posX, (int)posY);
				repaint();
			} catch (InterruptedException ex) {}
		}

		handCards.get(0).setLocation((int)targetX, (int)targetY);
		repaint();

		delay(200);
		hideHand();
		isSelected[0] = false;
	}

	public void discardAICards(int userFrom, int num) {
		int timeSlice;
		switch(userFrom) {
			case Command.ID_LEFTCOMPUTER:
				timeSlice = 100;
				posX = Fieldname.getLeftComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				posY = Fieldname.getLeftComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			case Command.ID_TOPCOMPUTER:
				timeSlice = 70;
				posX = Fieldname.getTopComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				posY = Fieldname.getTopComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			default:
				timeSlice = 50;
				posX = Fieldname.getRightComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				posY = Fieldname.getRightComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
		}

		double targetX = (double)(Fieldname.getDiscardFaceDownDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
		double targetY = (double)(Fieldname.getDiscardFaceDownDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);

		for (int i = 0; i < num; i++) {
			handCards.get(i).setLocation((int)posX, (int)posY);
			handCards.get(i).showPanel();
			handCards.get(i).repaint();
		}
		delay(200);

		double moveX = (targetX - posX) / (double)timeSlice;
		double moveY = (targetY - posY) / (double)timeSlice;
		int curTime = 0, space = 10;
		while (++curTime < timeSlice + space * (num - 1)) {
			try {
				Thread.sleep(8);
				posX += moveX;
				posY += moveY;
				for (int i = 0; i < num; i++) {
					if (curTime >= i * space && curTime < timeSlice + i * space) {
						handCards.get(i).setLocation((int)(posX - i * space * moveX), 
													 (int)(posY - i * space * moveY));
					}
				}
				repaint();
			} catch (InterruptedException ex) {}
		}

		for (int i = 0; i < num; i++)
			handCards.get(i).setLocation((int)targetX, (int)targetY);
		repaint();

		delay(200);
		hideHand();
	}

	public void setDroppedCard() {
		for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++) {
			if (isSelected[i]) {
				posX = posesX[i];
				posY = posesY[i];
				droppedCard.setLocation((int)posesX[i], (int)posesY[i]);
				showCard(playerHandCards.get(i).cardType, playerHandCards.get(i).cardID, playerHandCards.get(i).rotationID);
				playerHandCards.get(i).hidePanel();
			}
		}
		repaint();
	}

	public void placeMineBoardCard(int w, int h) {
		double targetX = Fieldname.getMineBoardPanelPosition().x + Fieldname.getMineBoardPanelCardPosition(w, h).x;
		double targetY = Fieldname.getMineBoardPanelPosition().y + Fieldname.getMineBoardPanelCardPosition(w, h).y;

		int timeSlice = 50;
		double moveX = (targetX - posX) / (double)timeSlice;
		double moveY = (targetY - posY) / (double)timeSlice;
		boolean reachTarget = false;
		while (!reachTarget) {
			try {
				Thread.sleep(8);
				posX += moveX;
				posY += moveY;
				droppedCard.setLocation((int)posX, (int)posY);
				repaint();
			} catch (InterruptedException ex) {}
			if ((moveX >= 0 && posX >= targetX) || (moveX < 0 && posX <= targetX))
				if ((moveY >= 0 && posY >= targetY) || (moveY < 0 && posY <= targetY))
					reachTarget = true;
		}
		droppedCard.setLocation((int)targetX, (int)targetY);
		delay(500);
		hideCard();
	}

	public void placePathCard(int w, int h) {
		placeMineBoardCard(w, h);
	}

	public void placeGPSCard(int index) {
		placeMineBoardCard(8, index * 2);
	}

	public void placeStupidBirdCard(int w, int h) {
		placeMineBoardCard(w, h);
	}

	public void placeActionCard(int userTo, int handCardPos) {
		double targetX, targetY;
		switch(userTo) {
			case Command.ID_LEFTCOMPUTER:
				targetX = Fieldname.getLeftComputerPanelPosition().x + Fieldname.getActionsPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).x + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_ACTIONS_PANEL_CARDS, Fieldname.ID_ROTATE_LEFT_COMPUTER).x;
				targetY = Fieldname.getLeftComputerPanelPosition().y + Fieldname.getActionsPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).y + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_ACTIONS_PANEL_CARDS, Fieldname.ID_ROTATE_LEFT_COMPUTER).y;
				break;
			case Command.ID_TOPCOMPUTER:
				targetX = Fieldname.getTopComputerPanelPosition().x + Fieldname.getActionsPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).x + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_ACTIONS_PANEL_CARDS, Fieldname.ID_ROTATE_TOP_COMPUTER).x;
				targetY = Fieldname.getTopComputerPanelPosition().y + Fieldname.getActionsPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).y + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_ACTIONS_PANEL_CARDS, Fieldname.ID_ROTATE_TOP_COMPUTER).y;
				break;
			case Command.ID_RIGHTCOMPUTER:
				targetX = Fieldname.getRightComputerPanelPosition().x + Fieldname.getActionsPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).x + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_ACTIONS_PANEL_CARDS, Fieldname.ID_ROTATE_RIGHT_COMPUTER).x;
				targetY = Fieldname.getRightComputerPanelPosition().y + Fieldname.getActionsPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).y + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_ACTIONS_PANEL_CARDS, Fieldname.ID_ROTATE_RIGHT_COMPUTER).y;
				break;
			default:
				targetX = Fieldname.getPlayerPanelPosition().x + Fieldname.getActionsPanelPosition(Fieldname.ID_ROTATE_PLAYER).x + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_ACTIONS_PANEL_CARDS, Fieldname.ID_ROTATE_PLAYER).x; 
				targetY = Fieldname.getPlayerPanelPosition().y + Fieldname.getActionsPanelPosition(Fieldname.ID_ROTATE_PLAYER).y + Fieldname.getCardFaceUpPanelPosition(handCardPos, Command.NUM_ACTIONS_PANEL_CARDS, Fieldname.ID_ROTATE_PLAYER).y; 
		}

		int timeSlice = 100;
		double moveX = (targetX - posX) / (double)timeSlice;
		double moveY = (targetY - posY) / (double)timeSlice;
		boolean reachTarget = false;
		while (!reachTarget) {
			try {
				Thread.sleep(8);
				posX += moveX;
				posY += moveY;
				droppedCard.setLocation((int)posX, (int)posY);
				repaint();
			} catch (InterruptedException ex) {}
			if ((moveX >= 0 && posX >= targetX) || (moveX < 0 && posX <= targetX))
				if ((moveY >= 0 && posY >= targetY) || (moveY < 0 && posY <= targetY))
					reachTarget = true;
		}
		droppedCard.setLocation((int)targetX, (int)targetY);
		delay(200);
		hideCard();
	}

	public void drawCards(int userTo, int num) {
		double startX = (double)(Fieldname.getDrawDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
		double startY = (double)(Fieldname.getDrawDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);

		int timeSlice;
		double targetX, targetY;
		switch(userTo) {
			case Command.ID_LEFTCOMPUTER:
				timeSlice = 200;
				targetX = Fieldname.getLeftComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				targetY = Fieldname.getLeftComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			case Command.ID_TOPCOMPUTER:
				timeSlice = 120;
				targetX = Fieldname.getTopComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				targetY = Fieldname.getTopComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			case Command.ID_RIGHTCOMPUTER:
				timeSlice = 100;
				targetX = Fieldname.getRightComputerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
				targetY = Fieldname.getRightComputerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
				break;
			default:
				timeSlice = 180;
				targetX = Fieldname.getPlayerPanelPosition().x + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).x + Fieldname.D_CARD_PANEL_PADDING;
				targetY = Fieldname.getPlayerPanelPosition().y + Fieldname.getHandPanelPosition(Fieldname.ID_ROTATE_PLAYER).y + Fieldname.D_CARD_PANEL_PADDING;
		}

		for (int i = 0; i < num; i++) {
			handCards.get(i).showPanel();
			handCards.get(i).setLocation((int)startX, (int)startY);
		}

		double moveX = (targetX - startX) / (double)timeSlice;
		double moveY = (targetY - startY) / (double)timeSlice;
		int curTime = 0, space = 10;
		while (++curTime < timeSlice + space * (Command.NUM_DRAW_HAND - 1)) {
			try {
				Thread.sleep(8);
				startX += moveX;
				startY += moveY;
				for (int i = 0; i < num; i++) {
					if (curTime >= i * space && curTime < timeSlice + i * space) {
						handCards.get(i).setLocation((int)(startX - i * space * moveX), 
													   (int)(startY - i * space * moveY));
					}
				}
				repaint();
			} catch (InterruptedException ex) {}
		}

		for (int i = 0; i < num; i++)
			handCards.get(i).setLocation((int)targetX, (int)targetY);
		repaint();

		delay(200);
		hideHand();

	}

	public void setRewardCardsPos() {
		for (int i = 0; i < Command.NUM_DRAW_REWARD; i++) {
			double startX = (double)(Fieldname.getRewardDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
			double startY = (double)(Fieldname.getRewardDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);
			double targetX = (double)(paddingX + Fieldname.getAnimateCardsPosition(i, Command.NUM_DRAW_REWARD).x);
			double targetY = (double)(paddingY + Fieldname.getAnimateCardsPosition(i, Command.NUM_DRAW_REWARD).y);
			rewardCards.get(i).showPanel();
			rewardCards.get(i).setLocation((int)startX, (int)startY);

			int timeSlice = 100;
			double moveX = (targetX - startX) / (double)timeSlice;
			double moveY = (targetY - startY) / (double)timeSlice;
			while (startX < targetX && startY < targetY) {
				try {
					Thread.sleep(8);
					startX += moveX;
					startY += moveY;
					rewardCards.get(i).setLocation((int)startX, (int)startY);
					repaint();
				} catch (InterruptedException ex) {}
			}

			rewardCards.get(i).setLocation((int)targetX, (int)targetY);
		}
	}

	public void setClickedRewardCard(int mouseX, int mouseY) {
		int yTop = paddingY + Fieldname.getAnimateCardsPosition(0, Command.NUM_DRAW_REWARD).y;
		int yBottom = yTop + Fieldname.getCardPanelHeight();
		if (mouseY < yTop || mouseY > yBottom) {
			playerClicked = -1;
			return;
		}

		for (int i = 0; i < Command.NUM_DRAW_REWARD; i++) {
			int xLeft = paddingX + Fieldname.getAnimateCardsPosition(i, Command.NUM_DRAW_REWARD).x;
			int xRight = xLeft + Fieldname.getCardPanelWidth();
			if (mouseX >= xLeft && mouseX <= xRight) {
				playerClicked = i;
				return;
			}
		}
        playerClicked = -1;
	}

	public void showRewards(ArrayList<Integer> rewardCardsID) {
		for (int i = 0; i < Command.NUM_DRAW_REWARD; i++)
			rewardCards.get(i).updateImage(Command.CARDTYPE_REWARD, rewardCardsID.get(i));
		repaint();
	}

	public void dealRewards(ArrayList<Integer> matcher) {
		for (int i = 0; i < Command.NUM_DRAW_REWARD; i++) {
			double startX = (double)(paddingX + Fieldname.getAnimateCardsPosition(i, Command.NUM_DRAW_REWARD).x);
			double startY = (double)(paddingY + Fieldname.getAnimateCardsPosition(i, Command.NUM_DRAW_REWARD).y);
			double targetX, targetY;
			switch(matcher.get(i)) {
				case Command.ID_LEFTCOMPUTER:
					targetX = Fieldname.getLeftComputerPanelPosition().x + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
					targetY = Fieldname.getLeftComputerPanelPosition().y + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_LEFT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
					break;
				case Command.ID_TOPCOMPUTER:
					targetX = Fieldname.getTopComputerPanelPosition().x + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
					targetY = Fieldname.getTopComputerPanelPosition().y + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_TOP_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
					break;
				case Command.ID_RIGHTCOMPUTER:
					targetX = Fieldname.getRightComputerPanelPosition().x + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).x + Fieldname.D_CARD_PANEL_PADDING;
					targetY = Fieldname.getRightComputerPanelPosition().y + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_RIGHT_COMPUTER).y + Fieldname.D_CARD_PANEL_PADDING;
					break;
				case Command.ID_PLAYER:
					targetX = Fieldname.getPlayerPanelPosition().x + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_PLAYER).x + Fieldname.D_CARD_PANEL_PADDING;
					targetY = Fieldname.getPlayerPanelPosition().y + Fieldname.getRolePanelPosition(Fieldname.ID_ROTATE_PLAYER).y + Fieldname.D_CARD_PANEL_PADDING;
					break;
				default:
					targetX = (double)(Fieldname.getRewardDeckPanelPosition().x + Fieldname.D_CARD_PANEL_PADDING);
					targetY = (double)(Fieldname.getRewardDeckPanelPosition().y + Fieldname.D_CARD_PANEL_PADDING);
			}
			rewardCards.get(i).setLocation((int)startX, (int)startY);

			int timeSlice = 80;
			double moveX = (targetX - startX) / (double)timeSlice;
			double moveY = (targetY - startY) / (double)timeSlice;
			boolean reachTarget = false;
			while (!reachTarget) {
				try {
					Thread.sleep(8);
					startX += moveX;
					startY += moveY;
					rewardCards.get(i).setLocation((int)startX, (int)startY);
					repaint();
				} catch (InterruptedException ex) {}

				if ((moveX >= 0 && startX >= targetX) || (moveX < 0 && startX <= targetX))
					if ((moveY >= 0 && startY >= targetY) || (moveY < 0 && startY <= targetY))
						reachTarget = true;
			}

			rewardCards.get(i).setLocation((int)targetX, (int)targetY);
		}
		delay(100);
		hideRewards();
	}

	public void showRewards(ArrayList<Integer> lcCards, ArrayList<Integer> tcCards, ArrayList<Integer> rcCards, ArrayList<Integer> players) {
		for (int i = 0; i < lcCards.size(); i++) {
			CardPanel card = new CardPanel(Command.CARDTYPE_REWARD, lcCards.get(i), Fieldname.ID_ROTATE_LEFT_COMPUTER);
			card.setLocation(Fieldname.getRewardCardPosition(i, lcCards.size(), Fieldname.ID_ROTATE_LEFT_COMPUTER));
			card.showPanel();
			add(card);
		}

		for (int i = 0; i < tcCards.size(); i++) {
			CardPanel card = new CardPanel(Command.CARDTYPE_REWARD, tcCards.get(i), Fieldname.ID_ROTATE_TOP_COMPUTER);
			card.setLocation(Fieldname.getRewardCardPosition(i, tcCards.size(), Fieldname.ID_ROTATE_TOP_COMPUTER));
			card.showPanel();
			add(card);
		}

		for (int i = 0; i < rcCards.size(); i++) {
			CardPanel card = new CardPanel(Command.CARDTYPE_REWARD, rcCards.get(i), Fieldname.ID_ROTATE_RIGHT_COMPUTER);
			card.setLocation(Fieldname.getRewardCardPosition(i, rcCards.size(), Fieldname.ID_ROTATE_RIGHT_COMPUTER));
			card.showPanel();
			add(card);
		}

		for (int i = 0; i < players.size(); i++) {
			CardPanel card = new CardPanel(Command.CARDTYPE_REWARD, players.get(i), Fieldname.ID_ROTATE_PLAYER);
			card.setLocation(Fieldname.getRewardCardPosition(i, players.size(), Fieldname.ID_ROTATE_PLAYER));
			card.showPanel();
			add(card);
		}

		repaint();
	}

	public void delay(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException ex) {}
	}

    @Override
    public void paintComponent(Graphics g) {
// System.out.println("draw animate...");
        super.paintComponent(g);
        if (bg != null) {
// System.out.println("draw background...");
	        g.drawImage(bg, 0, 0, this);
        }



	    // g.drawRect(0, 0, getWidth(), getHeight());
	    // g.setColor(Color.RED);  
	    // g.fillRect(0, 0, getWidth(), getHeight()); 
	    // g.setColor(new Color(0, 0, 0, 0));  
	    // g.fillRect(0, 0, getWidth(), getHeight()); 
    }
}