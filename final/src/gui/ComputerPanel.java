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

public class ComputerPanel extends BasePanel {

	public RolePanel role;
	public ActionsPanel status;
	public HandFaceDownPanel hand;

	public ComputerPanel(int roleID, ArrayList<Integer> statusIDs, int rotateID) {
    	super(Fieldname.getComputerPanelWidth(), Fieldname.getComputerPanelHeight(), rotateID);
    	setBackground(Fieldname.C_TRANSPARENT);

    	role = new RolePanel(roleID, rotateID);
    	status = new ActionsPanel(statusIDs, rotateID);
    	hand = new HandFaceDownPanel(rotateID);
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

	public int getLastCardIndex() {
		for (int i = Command.NUM_HAND_PANEL_CARDS - 1; i >= 0; i--)
			if (hand.handCards.get(i).cardID == Command.ID_PATH_BACK)
				return i;
		return 0;
	}
}