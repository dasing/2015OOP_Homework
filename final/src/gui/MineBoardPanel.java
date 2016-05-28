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

public class MineBoardPanel extends BasePanel {

	CardPanel pathCards[][];

	public MineBoardPanel(int cardsType[][], int cardsID[][]) {
    	super(Fieldname.getMineBoardPanelWidth(), Fieldname.getMineBoardPanelHeight(), Fieldname.ID_ROTATE_PLAYER);
    	setBackground(Fieldname.C_TRANSPARENT);

    	pathCards = new CardPanel[Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH][Command.NUM_MINEBOARD_PANEL_CARDS_HEIGHT];
    	for (int w = 0; w < Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH; w++)
    		for (int h = 0; h < Command.NUM_MINEBOARD_PANEL_CARDS_HEIGHT; h++)
		    	pathCards[w][h] = new CardPanel(cardsType[w][h], cardsID[w][h], rotationID);
    	createLayout();
	}

	public void createLayout() {
		for (int w = 0; w < Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH; w++) {
			for (int h = 0; h < Command.NUM_MINEBOARD_PANEL_CARDS_HEIGHT; h++) {
				int x = Fieldname.getMineBoardPanelCardPosition(w, h).x;
				int y = Fieldname.getMineBoardPanelCardPosition(w, h).y;
				pathCards[w][h].setLocation(x, y);
				pathCards[w][h].showPanel();
				add(pathCards[w][h]);
			}
		}

    	showPanel();
	}

	public void updateLayout(int w, int h, int cardType, int cardID, boolean isRotated) {
		pathCards[w][h].rotationID = isRotated ? Fieldname.ID_ROTATE_TOP_COMPUTER : Fieldname.ID_ROTATE_PLAYER;
		pathCards[w][h].updateImage(cardType, cardID);
		pathCards[w][h].repaint();
		repaint();
	}
}