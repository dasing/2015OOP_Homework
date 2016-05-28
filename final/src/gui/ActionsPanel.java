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

public class ActionsPanel extends BasePanel {

	public ArrayList<CardPanel> statusCards;

	public ActionsPanel(ArrayList<Integer> statusIDs, int rotateID) {
    	super(Fieldname.getActionsPanelWidth(), Fieldname.getActionsPanelHeight(), rotateID);
    	setBackground(Fieldname.C_TRANSPARENT);

    	statusCards = new ArrayList<CardPanel>();
    	for (int i = 0; i < Command.NUM_ACTIONS_PANEL_CARDS; i++)
    		statusCards.add(new CardPanel(Command.CARDTYPE_ACTION, statusIDs.get(i), rotateID));
    	createLayout();
	}

	public void createLayout() {
		for (int i = 0; i < Command.NUM_ACTIONS_PANEL_CARDS; i++) {
			int x = Fieldname.getCardFaceUpPanelPosition(i, Command.NUM_ACTIONS_PANEL_CARDS, rotationID).x;
			int y = Fieldname.getCardFaceUpPanelPosition(i, Command.NUM_ACTIONS_PANEL_CARDS, rotationID).y;
			statusCards.get(i).setLocation(x, y);
	    	statusCards.get(i).showPanel();
    		add(statusCards.get(i));
		}

    	showPanel();
	}

	public void updateLayout(int pos, int newStatusID) {
		statusCards.get(pos).updateImage(Command.CARDTYPE_ACTION, newStatusID);
		statusCards.get(pos).repaint();
	}
}