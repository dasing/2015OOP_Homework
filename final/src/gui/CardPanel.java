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

public class CardPanel extends BasePanel {

    //card image
    public BufferedImage image;
    private String filepath;

    //card information
    public int cardType;
    public int cardID;

    public CardPanel(int type, int id, int rotateID) {
    	super(Fieldname.getCardPanelWidth(), Fieldname.getCardPanelHeight(), rotateID);
	    setBackground(Fieldname.C_TRANSPARENT);

        cardType = type;
        cardID = id;
        createLayout();
    }

    public void createLayout() {
        if (cardType == Command.CARDTYPE_EMPTY || cardID == Command.CARDTYPE_EMPTY) return;
    	try {
// System.out.println("Read image...");
            switch(cardType) {
                case Command.CARDTYPE_ROLE:
                    filepath = Fieldname.IMAGEPATH_ROLE_CARDS[cardID];
                    break;
                case Command.CARDTYPE_ACTION:
                    filepath = Fieldname.IMAGEPATH_ACTION_CARDS[cardID];
                    break;
                case Command.CARDTYPE_PATH:
                    filepath = Fieldname.IMAGEPATH_PATH_CARDS[cardID];
                    break;
                case Command.CARDTYPE_REWARD:
                    filepath = Fieldname.IMAGEPATH_REWARD_CARDS[cardID];
                    break;
                default:
                    throw new EmptyStackException();
            }
        	image = ImageIO.read(new File(filepath));
       } catch (IOException ex) {
			System.out.println("Failed to read image...");	
            System.out.println(filepath);  
       } catch (EmptyStackException ex) {
            System.out.println("Cannot find card type.");  
       }
    }

    public void updateImage(int type, int id) {
        cardType = type;
        cardID = id;
        createLayout();
    }

    @Override
    public void paintComponent(Graphics g) {
// System.out.println("draw image......");
        super.paintComponent(g);

        if (cardType == Command.CARDTYPE_EMPTY || cardID == Command.CARDTYPE_EMPTY) return;

        if (rotationID == Fieldname.ID_ROTATE_PLAYER) {
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.drawImage(image, 0, 0, this);
            g2d.dispose();
        } else {
            AffineTransform at = new AffineTransform();

            if (rotationID == Fieldname.ID_ROTATE_TOP_COMPUTER)
                at.translate(image.getWidth()/2, image.getHeight()/2);
            else
                at.translate(image.getHeight()/2, image.getWidth()/2);
            
            at.rotate(Fieldname.ROTATIONS[rotationID]);
            at.translate(-image.getWidth()/2, -image.getHeight()/2);

            Graphics2D g2d = (Graphics2D)g.create();
            g2d.drawImage(image, at, null);
            g2d.dispose();
        }
    }
}