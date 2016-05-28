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
import java.awt.geom.AffineTransform;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundPanel extends BasePanel {

    //card image
    public BufferedImage image;

    public BackgroundPanel() {
    	super(Fieldname.getGameFrameWidth(), Fieldname.getGameFrameHeight(), Fieldname.ID_ROTATE_PLAYER);
	    setBackground(Fieldname.C_TRANSPARENT);

        createLayout();
    }

    public void createLayout() {
    	try {
        	image = ImageIO.read(new File(Fieldname.IMAGEPATH_BACKGROUND_GAMEWINDOW));
       } catch (IOException ex) {
			System.out.println("Failed to read background image...");
       } catch (EmptyStackException ex) {
            System.out.println("Cannot find card type.");  
       }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.drawImage(image, 0, 0, this);
        g2d.dispose();
    }
}