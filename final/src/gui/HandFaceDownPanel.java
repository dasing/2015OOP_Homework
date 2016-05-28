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

public class HandFaceDownPanel extends BasePanel {

	public ArrayList<CardPanel> handCards;

	public HandFaceDownPanel(int rotateID) {
    	super(Fieldname.getHandFaceDownPanelWidth(), Fieldname.getHandFaceDownPanelHeight(), rotateID);
    	setBackground(Fieldname.C_TRANSPARENT);

    	handCards = new ArrayList<CardPanel>();
    	for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++)
    		handCards.add(new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_BACK, rotateID));
    	createLayout();
	}


	public void createLayout() {
		for (int i = 0; i < Command.NUM_HAND_PANEL_CARDS; i++) {
			int x = Fieldname.getCardFaceDownPanelPosition(i, Command.NUM_HAND_PANEL_CARDS, rotationID).x;
			int y = Fieldname.getCardFaceDownPanelPosition(i, Command.NUM_HAND_PANEL_CARDS, rotationID).y;
			handCards.get(i).setLocation(x, y);
	    	handCards.get(i).showPanel();
    		add(handCards.get(i));
		}

    	showPanel();
	}

	public void updateLayout(int pos, int cardID) {
		handCards.get(pos).updateImage(Command.CARDTYPE_PATH, cardID);
		handCards.get(pos).repaint();
		repaint();
	}
}