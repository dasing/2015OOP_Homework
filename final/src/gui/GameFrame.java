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

public class GameFrame extends JFrame {

	MineBoardPanel mineBoard;

	PlayerPanel player;
	ComputerPanel leftComputer;
	ComputerPanel topComputer;
	ComputerPanel rightComputer;

	RoleDeckPanel roleDeck;
	RewardDeckPanel rewardDeck;
	DrawDeckPanel drawDeck;
	DiscardFaceUpDeckPanel disCardFaceUpDeck;
	DiscardFaceDownDeckPanel disCardFaceDownDeck;
	
	ButtonPanel discardButton, okButton, cancelButton;

	BackgroundPanel background;
	AnimationPanel animate;

	public GameFrame(MineBoardPanel mb, PlayerPanel p,	
		 			 ComputerPanel lc, ComputerPanel tc, ComputerPanel rc,
					 RoleDeckPanel role, RewardDeckPanel reward, DrawDeckPanel draw, 
					 DiscardFaceUpDeckPanel dup, DiscardFaceDownDeckPanel ddown,
					 BackgroundPanel bg, AnimationPanel an) {

		Dimension size = new Dimension(Fieldname.getGameFrameWidth(), Fieldname.getGameFrameHeight()+30);
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
    	setLayout(null);
    	// setBackground(Fieldname.C_TRANSPARENT);

    	mineBoard = mb;

    	player = p;
    	leftComputer = lc;
    	topComputer = tc;
    	rightComputer = rc;

    	roleDeck = role;
    	rewardDeck = reward;
    	drawDeck = draw;
    	disCardFaceUpDeck = dup;
    	disCardFaceDownDeck = ddown;

    	background = bg;
    	animate = an;

    	createLayout();
	}

	public void createLayout() {

		int x, y;

		//add mineBoard
		x = Fieldname.getMineBoardPanelPosition().x;
		y = Fieldname.getMineBoardPanelPosition().y;
		mineBoard.setLocation(x, y);
    	mineBoard.showPanel();
    	add(mineBoard);

		//add player
		x = Fieldname.getPlayerPanelPosition().x;
		y = Fieldname.getPlayerPanelPosition().y;
		player.setLocation(x, y);
    	player.showPanel();
    	add(player);

		//add leftComputer
		x = Fieldname.getLeftComputerPanelPosition().x;
		y = Fieldname.getLeftComputerPanelPosition().y;
		leftComputer.setLocation(x, y);
    	leftComputer.showPanel();
    	add(leftComputer);

		//add topComputer
		x = Fieldname.getTopComputerPanelPosition().x;
		y = Fieldname.getTopComputerPanelPosition().y;
		topComputer.setLocation(x, y);
    	topComputer.showPanel();
    	add(topComputer);

		//add rightComputer
		x = Fieldname.getRightComputerPanelPosition().x;
		y = Fieldname.getRightComputerPanelPosition().y;
		rightComputer.setLocation(x, y);
    	rightComputer.showPanel();
    	add(rightComputer);

		//add roleDeck
		x = Fieldname.getRoleDeckPanelPosition().x;
		y = Fieldname.getRoleDeckPanelPosition().y;
		roleDeck.setLocation(x, y);
    	roleDeck.showPanel();
    	add(roleDeck);

		//add rewardDeck
		x = Fieldname.getRewardDeckPanelPosition().x;
		y = Fieldname.getRewardDeckPanelPosition().y;
		rewardDeck.setLocation(x, y);
    	rewardDeck.showPanel();
    	add(rewardDeck);

		//add drawDeck
		x = Fieldname.getDrawDeckPanelPosition().x;
		y = Fieldname.getDrawDeckPanelPosition().y;
		drawDeck.setLocation(x, y);
    	drawDeck.showPanel();
    	add(drawDeck);

		//add disCardFaceUpDeck
		x = Fieldname.getDiscardFaceUpDeckPanelPosition().x;
		y = Fieldname.getDiscardFaceUpDeckPanelPosition().y;
		disCardFaceUpDeck.setLocation(x, y);
    	disCardFaceUpDeck.showPanel();
    	add(disCardFaceUpDeck);

		//add disCardFaceDownDeck
		x = Fieldname.getDiscardFaceDownDeckPanelPosition().x;
		y = Fieldname.getDiscardFaceDownDeckPanelPosition().y;
		disCardFaceDownDeck.setLocation(x, y);
    	disCardFaceDownDeck.showPanel();
    	add(disCardFaceDownDeck);

		//add background
		background.setLocation(0, 0);
    	background.showPanel();
    	add(background);

    	setVisible(true);
	}

	public void addAnimationPanel() {
		animate.setLocation(0, 0);
    	animate.showPanel();
    	add(animate);
    	repaint();
	}

	public void removeAnimationPanel() {
    	remove(animate);
    	repaint();
	}
}