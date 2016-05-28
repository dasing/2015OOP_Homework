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

public class DiscardFaceDownDeckPanel extends BasePanel {

	public CardPanel faceDownCard;

	public DiscardFaceDownDeckPanel() {
    	super(Fieldname.getDeckPanelWidth(), Fieldname.getDeckPanelHeight(), Fieldname.ID_ROTATE_PLAYER);
    	setBackground(Fieldname.C_TRANSPARENT);

    	faceDownCard = new CardPanel(Command.CARDTYPE_PATH, Command.ID_PATH_BACK, rotationID);
    	createLayout();
	}

	public void createLayout() {
		int x = Fieldname.getCardFaceUpPanelPosition(0, Command.NUM_DECK_PANEL_CARDS, rotationID).x;
		int y = Fieldname.getCardFaceUpPanelPosition(0, Command.NUM_DECK_PANEL_CARDS, rotationID).y;
		faceDownCard.setLocation(x, y);
    	faceDownCard.showPanel();
    	add(faceDownCard);

    	showPanel();
	}

	public void updateLayout(int cardID) {
		faceDownCard.updateImage(Command.CARDTYPE_PATH, cardID);
		faceDownCard.repaint();
		repaint();
	}
}