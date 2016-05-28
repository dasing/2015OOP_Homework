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

public class RoleDeckPanel extends BasePanel {

	public CardPanel roleCard;

	public RoleDeckPanel() {
    	super(Fieldname.getDeckPanelWidth(), Fieldname.getDeckPanelHeight(), Fieldname.ID_ROTATE_PLAYER);
    	setBackground(Fieldname.C_TRANSPARENT);

    	roleCard = new CardPanel(Command.CARDTYPE_ROLE, Command.ID_ROLE_BACK, rotationID);
    	createLayout();
	}

	public void createLayout() {
		int x = Fieldname.getCardFaceUpPanelPosition(0, Command.NUM_DECK_PANEL_CARDS, rotationID).x;
		int y = Fieldname.getCardFaceUpPanelPosition(0, Command.NUM_DECK_PANEL_CARDS, rotationID).y;
		roleCard.setLocation(x, y);
    	roleCard.showPanel();
    	add(roleCard);

    	showPanel();
	}
}