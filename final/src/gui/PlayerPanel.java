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

public class PlayerPanel extends BasePanel {

	public RolePanel role;
	public ActionsPanel status;
	public HandFaceUpPanel hand;

	public PlayerPanel(int roleID, ArrayList<Integer> statusIDs, ArrayList<Integer> cardsType, ArrayList<Integer> cardsID, int rotateID) {
    	super(Fieldname.getPlayerPanelWidth(), Fieldname.getPlayerPanelHeight(), rotateID);
    	setBackground(Fieldname.C_TRANSPARENT);

		role = new RolePanel(roleID, rotateID);
		status = new ActionsPanel(statusIDs, rotateID);
		hand = new HandFaceUpPanel(cardsType, cardsID, rotateID);
    	createLayout();
	}

	public void createLayout() {
		int x, y;
		
		//add role panel
		x = Fieldname.getRolePanelPosition(rotationID).x;
		y = Fieldname.getRolePanelPosition(rotationID).y;
		role.setLocation(x, y);
    	role.showPanel();
		add(role);

		//add status panel
		x = Fieldname.getActionsPanelPosition(rotationID).x;
		y = Fieldname.getActionsPanelPosition(rotationID).y;
		status.setLocation(x, y);
    	status.showPanel();
		add(status);

		//add hand panel
		x = Fieldname.getHandPanelPosition(rotationID).x;
		y = Fieldname.getHandPanelPosition(rotationID).y;
		hand.setLocation(x, y);
    	hand.showPanel();
		add(hand);

    	showPanel();
	}
}