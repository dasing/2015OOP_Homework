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

abstract public class BasePanel extends JPanel {

	//panel attributes
	public int width;
	public int height;
    public int rotationID;

	//priority for the panel to display in different layer
	public int priority;
	
	public BasePanel(int width, int height, int rotateID) {
		Dimension size;
		if (rotateID == Fieldname.ID_ROTATE_TOP_COMPUTER || rotateID == Fieldname.ID_ROTATE_PLAYER)
	    	size = new Dimension(width, height);
	    else
	    	size = new Dimension(height, width);
	    
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);

	    setLayout(null);
        rotationID = rotateID;
	}

	public void setPriority(int prio) {
		priority = prio;
	}

	public void showPanel() {
		setVisible(true);
	}

	public void hidePanel() {
		setVisible(false);
	}

	abstract public void createLayout();

}