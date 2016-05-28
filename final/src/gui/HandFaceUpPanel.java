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

public class HandFaceUpPanel extends BasePanel {

	public ArrayList<CardPanel> handCards;

	public HandFaceUpPanel(ArrayList<Integer> cardsType, ArrayList<Integer> cardsID, int rotateID) {
    	super(Fieldname.getHandFaceUpPanelWidth(), Fieldname.getHandFaceUpPanelHeight(), rotateID);
    	setBackground(Fieldname.C_TRANSPARENT);

    	handCards = new ArrayList<CardPanel>();
		for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++) 
	    	handCards.add(new CardPanel(cardsType.get(i), cardsID.get(i), rotateID));
    	createLayout();
	}

	public void createLayout() {
		for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++) {
			int x = Fieldname.getCardFaceUpPanelPosition(i, Command.NUM_HAND_PANEL_CARDS, rotationID).x;
			int y = Fieldname.getCardFaceUpPanelPosition(i, Command.NUM_HAND_PANEL_CARDS, rotationID).y;
			handCards.get(i).setLocation(x, y);
	    	handCards.get(i).showPanel();
    		add(handCards.get(i));
		}

    	showPanel();
	}

	public void updateLayout(int pos, int cardType, int cardID) {
		handCards.get(pos).updateImage(cardType, cardID);
		handCards.get(pos).repaint();
		repaint();
	}
}