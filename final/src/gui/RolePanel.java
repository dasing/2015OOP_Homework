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

public class RolePanel extends BasePanel {

	public CardPanel roleCard;

	public RolePanel(int roleID, int rotateID) {
    	super(Fieldname.getRolePanelWidth(), Fieldname.getRolePanelHeight(), rotateID);
    	setBackground(Fieldname.C_TRANSPARENT);

    	roleCard = new CardPanel(Command.CARDTYPE_ROLE, roleID, rotateID);
    	createLayout();
	}

	public void createLayout() {
		int x = Fieldname.getCardFaceUpPanelPosition(0, Command.NUM_ROLE_PANEL_CARDS, rotationID).x;
		int y = Fieldname.getCardFaceUpPanelPosition(0, Command.NUM_ROLE_PANEL_CARDS, rotationID).y;
		roleCard.setLocation(x, y);
    	roleCard.showPanel();
    	add(roleCard);

    	showPanel();
	}

	public void updateLayout(int roleID) {
		roleCard.updateImage(Command.CARDTYPE_ROLE, roleID);
		roleCard.repaint();
	}	
}