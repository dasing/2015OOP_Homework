import java.util.*;
import java.lang.Math;
import java.awt.Color;
import java.awt.Point;

public class Fieldname {

	static final String IMAGEPATH_ANIMATE_BG = "animate_bg.png";

	static final String IMAGEPATH_BACKGROUND_GAMEWINDOW    = "src/img/backgrounds/map.png",
						IMAGEPATH_BACKGROUND_MINEBOARD     = "src/img/backgrounds/",
						IMAGEPATH_BACKGROUND_PLAYER_REGION = "src/img/backgrounds/",
						IMAGEPATH_BACKGROUND_HAND          = "src/img/backgrounds/",
						IMAGEPATH_BACKGROUND_ACTIONS       = "src/img/backgrounds/",
						IMAGEPATH_BACKGROUND_ROLE          = "src/img/backgrounds/";

	static final String[] IMAGEPATH_ROLE_CARDS = {
		"src/img/roles/good1.png",
		"src/img/roles/good2.png",
		"src/img/roles/good3.png",
		"src/img/roles/good4.png",
		"src/img/roles/bad.png",
		"src/img/roles/people_back.png"
	};

	static final String[] IMAGEPATH_ACTION_CARDS = {
		"src/img/actions/bicycle.png",
		"src/img/actions/mac.png",
		"src/img/actions/teacher.png",
		"src/img/actions/un_bicycle.png",
		"src/img/actions/un_mac.png",
		"src/img/actions/un_teacher.png",
		"src/img/actions/un_mac_bicycle.png",
		"src/img/actions/un_bicycle_teacher.png",
		"src/img/actions/un_mac_and_teacher.png",
		"src/img/actions/gps.png",
		"src/img/actions/bird.png",
		"src/img/actions/obj_back.png"
	};

	static final String[] IMAGEPATH_PATH_CARDS = {
		"src/img/paths/start.png",
		"src/img/paths/goal_2.png",
		"src/img/paths/goal_1.png",
		"src/img/paths/goal_1.png",

		"src/img/paths/pass1010.png",
		"src/img/paths/pass1110.png",
		"src/img/paths/pass1111.png",
		"src/img/paths/pass0110.png",
		"src/img/paths/pass0011.png",
		"src/img/paths/pass1101.png",
		"src/img/paths/pass0101.png",
		
		"src/img/paths/block0010.png",
		"src/img/paths/block1011.png",
		"src/img/paths/block1111.png",
		"src/img/paths/block0110.png",
		"src/img/paths/block0011.png",
		"src/img/paths/block1010.png",
		"src/img/paths/block1101.png",
		"src/img/paths/block0101.png",
		"src/img/paths/block0001.png",
		
		"src/img/paths/obj_back.png",
		"src/img/paths/goal_back.png"
	};

	static final String[] IMAGEPATH_REWARD_CARDS = {
		"src/img/rewards/reward1.png",
		"src/img/rewards/reward2.png",
		"src/img/rewards/reward3.png",
		"src/img/rewards/gold_back.png"
	};

	static final String[] IMAGEPATH_BUTTONS = {
		"src/img/buttons/button_discard.png",
		"src/img/buttons/button_ok.png",
		"src/img/buttons/button_cancel.png"
	};

	static final int BUTTON_DISCARD = 0,
					 BUTTON_OK		= 1,
					 BUTTON_CANCEL  = 2;

	static final int D_CARD_WIDTH_REAL                  = 90,
					 D_CARD_HEIGHT_REAL                 = 110,
				     D_CARD_WIDTH                  = 90,
					 D_CARD_HEIGHT                 = 110,
					 D_CARD_PANEL_PADDING          = 3,
					 D_LARGE_PANEL_PADDING         = 5,
					 D_MINEBOARD_PADDING_X         = 100,
					 D_MINEBOARD_PADDING_Y         = 18,
					 D_GAMEFRAME_PADDING           = 15,
					 D_HAND_FACEDOWN_OVERLAPPING   = 70,
					 D_REWARD_OVERLAPPING		   = 10,
					 D_BUTTON_WIDTH				   = 90,
					 D_BUTTON_HEIGHT			   = 50;

	static final int ID_ROTATE_LEFT_COMPUTER  = 0,
					 ID_ROTATE_TOP_COMPUTER   = 1,
					 ID_ROTATE_RIGHT_COMPUTER = 2,
					 ID_ROTATE_PLAYER		  = 3;

	static final double[] ROTATIONS = {Math.PI/2, Math.PI, -Math.PI/2, 0.0};

	static final int INDEX_START_X = 0,
					 INDEX_START_Y = 2,
					 INDEX_GOAL1_X = 8,
					 INDEX_GOAL1_Y = 0,
					 INDEX_GOAL2_X = 8,
					 INDEX_GOAL2_Y = 2,
					 INDEX_GOAL3_X = 8,
					 INDEX_GOAL3_Y = 4;


	static final Color C_TRANSPARENT             = new Color(0, 0, 0, 0),
					   C_MASK                    = new Color(0, 0, 0, 70),
					   C_ROLE_PANEL                  = Color.BLUE,
					   C_ACTIONS_PANEL               = Color.CYAN,
					   C_HAND_PANEL                  = Color.DARK_GRAY,
					   C_PLAYER_PANEL                = Color.GRAY,
					   C_COMPUTER_PANEL              = Color.GREEN,
					   C_DRAWDECK_PANEL              = Color.LIGHT_GRAY,
					   C_DISCARD_FACEUP_DECK_PANEL   = Color.MAGENTA,
					   C_DISCARD_FACEDOWN_DECK_PANEL = Color.ORANGE,
					   C_ROLE_DECK_PANEL             = Color.PINK,
					   C_REWARD_DECK_PANEL           = Color.RED,
					   C_MINEBOARD_PANEL             = Color.YELLOW,
					   C_GAMEFRAME_PANEL             = Color.WHITE;

	static final int INFO_NONE                  = 0,

					 INFO_LEFTCOMPUTER_STATUS1  = 1,
					 INFO_LEFTCOMPUTER_STATUS2  = 2,
					 INFO_LEFTCOMPUTER_STATUS3  = 3,
					 INFO_TOPCOMPUTER_STATUS1   = 4,
					 INFO_TOPCOMPUTER_STATUS2   = 5,
					 INFO_TOPCOMPUTER_STATUS3   = 6,
					 INFO_RIGHTCOMPUTER_STATUS1 = 7,
					 INFO_RIGHTCOMPUTER_STATUS2 = 8,
					 INFO_RIGHTCOMPUTER_STATUS3 = 9,
					 INFO_PLAYER_STATUS1        = 10,
					 INFO_PLAYER_STATUS2        = 11,
					 INFO_PLAYER_STATUS3        = 12,

					 INFO_PLAYER_HAND1          = 13,
					 INFO_PLAYER_HAND2          = 14,
					 INFO_PLAYER_HAND3          = 15,
					 INFO_PLAYER_HAND4          = 16,
					 INFO_PLAYER_HAND5          = 17,
					 INFO_PLAYER_HAND6          = 18,

					 INFO_MINEBOARD				= 19;

	public static int getCardPanelWidth() {
		return D_CARD_WIDTH;
	}

	public static int getCardPanelHeight() {
		return D_CARD_HEIGHT;
	}

	public static Point getCardFaceUpPanelPosition(int handPos, int maxPos, int rotateID) {
		int x, y;
		switch(rotateID) {
			case ID_ROTATE_LEFT_COMPUTER:
				x = D_CARD_PANEL_PADDING;
				y = (getCardPanelWidth() + D_CARD_PANEL_PADDING) * handPos + D_CARD_PANEL_PADDING;
				break;
			case ID_ROTATE_TOP_COMPUTER:
				x = (getCardPanelWidth() + D_CARD_PANEL_PADDING) * (maxPos - handPos - 1) + D_CARD_PANEL_PADDING;
				y = D_CARD_PANEL_PADDING;
				break;
			case ID_ROTATE_RIGHT_COMPUTER:
				x = D_CARD_PANEL_PADDING;
				y = (getCardPanelWidth() + D_CARD_PANEL_PADDING) * (maxPos - handPos - 1) + D_CARD_PANEL_PADDING;
				break;
			default:
				x = (getCardPanelWidth() + D_CARD_PANEL_PADDING) * handPos + D_CARD_PANEL_PADDING;
				y = D_CARD_PANEL_PADDING;
		}
		return new Point(x, y);
	}

	public static Point getCardFaceDownPanelPosition(int handPos, int maxPos, int rotateID) {
		int x, y;
		switch(rotateID) {
			case ID_ROTATE_LEFT_COMPUTER:
				x = D_CARD_PANEL_PADDING;
				y = D_CARD_PANEL_PADDING + (getCardPanelWidth() - D_HAND_FACEDOWN_OVERLAPPING) * handPos;
				break;
			case ID_ROTATE_TOP_COMPUTER:
				x = D_CARD_PANEL_PADDING + (getCardPanelWidth() - D_HAND_FACEDOWN_OVERLAPPING) * (maxPos - handPos - 1);
				y = D_CARD_PANEL_PADDING;
				break;
			default:
				x = D_CARD_PANEL_PADDING;
				y = D_CARD_PANEL_PADDING + (getCardPanelWidth() - D_HAND_FACEDOWN_OVERLAPPING) * (maxPos - handPos - 1);
		}
		return new Point(x, y);
	}

	public static int getRolePanelWidth() {
		return D_CARD_WIDTH + D_CARD_PANEL_PADDING * 2;
	}

	public static int getRolePanelHeight() {
		return D_CARD_HEIGHT + D_CARD_PANEL_PADDING * 2;
	}

	public static Point getRolePanelPosition(int rotateID) {
		int x, y;
		switch(rotateID) {
			case ID_ROTATE_LEFT_COMPUTER:
				x = D_LARGE_PANEL_PADDING;
				y = D_LARGE_PANEL_PADDING;
				break;
			case ID_ROTATE_TOP_COMPUTER:
				x = D_LARGE_PANEL_PADDING + getHandFaceDownPanelWidth() + getActionsPanelWidth() + D_LARGE_PANEL_PADDING * 2;
				y = D_LARGE_PANEL_PADDING;
				break;
			case ID_ROTATE_RIGHT_COMPUTER:
				x = D_LARGE_PANEL_PADDING;
				y = D_LARGE_PANEL_PADDING + getHandFaceDownPanelWidth() + getActionsPanelWidth() + D_LARGE_PANEL_PADDING * 2;
				break;
			default:
				x = D_LARGE_PANEL_PADDING;
				y = D_LARGE_PANEL_PADDING;
		}
		return new Point(x, y);
	}

	public static int getActionsPanelWidth() {
		return (D_CARD_WIDTH + D_CARD_PANEL_PADDING) * Command.NUM_ACTIONS_PANEL_CARDS + D_CARD_PANEL_PADDING;
	}

	public static int getActionsPanelHeight() {
		return D_CARD_HEIGHT + D_CARD_PANEL_PADDING * 2;
	}

	public static Point getActionsPanelPosition(int rotateID) {
		int x, y;
		switch(rotateID) {
			case ID_ROTATE_LEFT_COMPUTER:
				x = D_LARGE_PANEL_PADDING;
				y = getRolePanelWidth() + D_LARGE_PANEL_PADDING * 2;
				break;
			case ID_ROTATE_TOP_COMPUTER:
				x = getHandFaceDownPanelWidth() + D_LARGE_PANEL_PADDING * 2;
				y = D_LARGE_PANEL_PADDING;
				break;
			case ID_ROTATE_RIGHT_COMPUTER:
				x = D_LARGE_PANEL_PADDING;
				y = getHandFaceDownPanelWidth() + D_LARGE_PANEL_PADDING * 2;
				break;
			default:
				x = getRolePanelWidth() + D_LARGE_PANEL_PADDING * 2;
				y = D_LARGE_PANEL_PADDING;
		}
		return new Point(x, y);
	}
	
	public static int getHandFaceUpPanelWidth() {
		return (D_CARD_WIDTH + D_CARD_PANEL_PADDING) * Command.NUM_HAND_PANEL_CARDS + D_CARD_PANEL_PADDING;
	}

	public static int getHandFaceUpPanelHeight() {
		return D_CARD_HEIGHT + D_CARD_PANEL_PADDING * 2;
	}
	
	public static int getHandFaceDownPanelWidth() {
		return (D_CARD_WIDTH - D_HAND_FACEDOWN_OVERLAPPING) * Command.NUM_HAND_PANEL_CARDS + D_HAND_FACEDOWN_OVERLAPPING + D_CARD_PANEL_PADDING * 2;
	}

	public static int getHandFaceDownPanelHeight() {
		return D_CARD_HEIGHT + D_CARD_PANEL_PADDING * 2;
	}

	public static Point getHandPanelPosition(int rotateID) {
		int x, y;
		switch(rotateID) {
			case ID_ROTATE_LEFT_COMPUTER:
				x = D_LARGE_PANEL_PADDING;
				y = getRolePanelWidth() + getActionsPanelWidth() + D_LARGE_PANEL_PADDING * 3;
				break;
			case ID_ROTATE_TOP_COMPUTER:
				x = D_LARGE_PANEL_PADDING;
				y = D_LARGE_PANEL_PADDING;
				break;
			case ID_ROTATE_RIGHT_COMPUTER:
				x = D_LARGE_PANEL_PADDING;
				y = D_LARGE_PANEL_PADDING;
				break;
			default:
				x = getRolePanelWidth() + getActionsPanelWidth() + D_LARGE_PANEL_PADDING * 3;
				y = D_LARGE_PANEL_PADDING;
		}
		return new Point(x, y);
	}
	
	public static int getDeckPanelWidth() {
		return D_CARD_WIDTH + D_CARD_PANEL_PADDING * 2;
	}

	public static int getDeckPanelHeight() {
		return D_CARD_HEIGHT + D_CARD_PANEL_PADDING * 2;
	}

	public static Point getRoleDeckPanelPosition() {
		int x = D_GAMEFRAME_PADDING;
		int y = D_GAMEFRAME_PADDING;
		return new Point(x, y);
	}

	public static Point getRewardDeckPanelPosition() {
		int x = getRoleDeckPanelPosition().x + getDeckPanelWidth() + D_LARGE_PANEL_PADDING;
		int y = D_GAMEFRAME_PADDING;
		return new Point(x, y);
	}

	public static Point getDrawDeckPanelPosition() {
		int x = getGameFrameWidth() - D_GAMEFRAME_PADDING - getDeckPanelWidth();
		int y = D_GAMEFRAME_PADDING;
		return new Point(x, y);
	}

	public static Point getDiscardFaceUpDeckPanelPosition() {
		int x = getDrawDeckPanelPosition().x - D_LARGE_PANEL_PADDING - getDeckPanelWidth();
		int y = D_GAMEFRAME_PADDING;
		return new Point(x, y);
	}

	public static Point getDiscardFaceDownDeckPanelPosition() {
		int x = getDiscardFaceUpDeckPanelPosition().x - D_LARGE_PANEL_PADDING - getDeckPanelWidth();
		int y = D_GAMEFRAME_PADDING;
		return new Point(x, y);
	}
	
	public static int getComputerPanelWidth() {
		return getRolePanelWidth() + getActionsPanelWidth() + getHandFaceDownPanelWidth() + D_LARGE_PANEL_PADDING * 4;
	}

	public static int getComputerPanelHeight() {
		return getRolePanelHeight() + D_LARGE_PANEL_PADDING * 2;
	}

	public static Point getLeftComputerPanelPosition() {
		int x = D_GAMEFRAME_PADDING;
		int y = (getGameFrameHeight() - getComputerPanelWidth()) / 2;
		return new Point(x, y);
	}

	public static Point getTopComputerPanelPosition() {
		int x = (getGameFrameWidth() - getComputerPanelWidth()) / 2;
		int y = D_GAMEFRAME_PADDING;
		return new Point(x, y);
	}

	public static Point getRightComputerPanelPosition() {
		int x = D_GAMEFRAME_PADDING + getComputerPanelHeight() + D_MINEBOARD_PADDING_X * 2 + getMineBoardPanelWidth();
		int y = (getGameFrameHeight() - getComputerPanelWidth()) / 2;
		return new Point(x, y);
	}

	public static int getPlayerPanelWidth() {
		return getRolePanelWidth() + getActionsPanelWidth() + getHandFaceUpPanelWidth() + D_LARGE_PANEL_PADDING * 4;
	}

	public static int getPlayerPanelHeight() {
		return getRolePanelHeight() + D_LARGE_PANEL_PADDING * 2;
	}

	public static Point getPlayerPanelPosition() {
		int x = (getGameFrameWidth() - getPlayerPanelWidth()) / 2;
		int y = D_GAMEFRAME_PADDING + getComputerPanelHeight() + D_MINEBOARD_PADDING_Y * 2 + getMineBoardPanelHeight();
		return new Point(x, y);
	}
	
	public static int getMineBoardPanelWidth() {
		return (D_CARD_WIDTH + D_CARD_PANEL_PADDING) * Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH + D_CARD_PANEL_PADDING;
	}

	public static int getMineBoardPanelHeight() {
		return (D_CARD_HEIGHT + D_CARD_PANEL_PADDING) * Command.NUM_MINEBOARD_PANEL_CARDS_HEIGHT + D_CARD_PANEL_PADDING;
	}

	public static Point getMineBoardPanelPosition() {
		int x = getLeftComputerPanelPosition().x + getComputerPanelHeight() + D_MINEBOARD_PADDING_X;
		int y = getTopComputerPanelPosition().y + getComputerPanelHeight() + D_MINEBOARD_PADDING_Y;
		return new Point(x, y);
	}

	public static Point getMineBoardPanelCardPosition(int w, int h) {
		int x = (D_CARD_PANEL_PADDING + getCardPanelWidth()) * w + D_CARD_PANEL_PADDING;
		int y = (D_CARD_PANEL_PADDING + getCardPanelHeight()) * h + D_CARD_PANEL_PADDING;
		return new Point(x, y);
	}

	public static Point getAnimateCardsPosition(int cardPos, int maxPos) {
		int x = (getCardPanelWidth() + D_MINEBOARD_PADDING_X) * cardPos + D_MINEBOARD_PADDING_X;
		int y = D_CARD_PANEL_PADDING;
		return new Point(x, y);
	}

	public static int getRoleCardsPanelWidth() {
		return (D_CARD_WIDTH + D_MINEBOARD_PADDING_X) * Command.NUM_DRAW_ROLE + D_MINEBOARD_PADDING_X;
	}

	public static int getRoleCardsPanelHeight() {
		return D_CARD_HEIGHT + D_CARD_PANEL_PADDING * 2;
	}

	public static int getRewardCardsPanelWidth() {
		return (D_CARD_WIDTH + D_MINEBOARD_PADDING_X) * Command.NUM_DRAW_REWARD + D_MINEBOARD_PADDING_X;
	}

	public static int getRewardCardsPanelHeight() {
		return D_CARD_HEIGHT + D_CARD_PANEL_PADDING * 2;
	}

	public static int getGameFrameWidth() {
		return getComputerPanelHeight() * 2 + getMineBoardPanelWidth() + D_MINEBOARD_PADDING_X * 2 + D_GAMEFRAME_PADDING * 2;
	}

	public static int getGameFrameHeight() {
		return getComputerPanelHeight() + getMineBoardPanelHeight() + getPlayerPanelHeight() + D_MINEBOARD_PADDING_Y * 2 + D_GAMEFRAME_PADDING * 2;
	}

	public static Point getGameFramePosition() {
		return new Point(0, 0);
	}

	public static Point getDiscardButtonPosition() {
		int x = getPlayerPanelPosition().x + getPlayerPanelWidth() + D_GAMEFRAME_PADDING;
		int y = getPlayerPanelPosition().y + (getPlayerPanelHeight() - D_BUTTON_HEIGHT) / 2;
		return new Point(x, y);
	}

	public static Point getOKButtonPosition() {
		int x = getPlayerPanelPosition().x + getPlayerPanelWidth() + D_GAMEFRAME_PADDING;
		int y = getGameFrameHeight() - D_GAMEFRAME_PADDING * 2 - D_BUTTON_HEIGHT * 2;
		return new Point(x, y);
	}

	public static Point getCancelButtonPosition() {
		int x = getPlayerPanelPosition().x + getPlayerPanelWidth() + D_GAMEFRAME_PADDING;
		int y = getGameFrameHeight() - D_GAMEFRAME_PADDING - D_BUTTON_HEIGHT;
		return new Point(x, y);
	}

	public static int getGameFrameInfo(int w, int h) {
		int lb, rb, tb, bb;
		int cardWidth = D_CARD_WIDTH + D_CARD_PANEL_PADDING;
		int cardHeight = D_CARD_HEIGHT + D_CARD_PANEL_PADDING;

		//check left computer status panel
		lb = getLeftComputerPanelPosition().x + getActionsPanelPosition(ID_ROTATE_LEFT_COMPUTER).x;
		rb = lb + getActionsPanelHeight();
		tb = getLeftComputerPanelPosition().y + getActionsPanelPosition(ID_ROTATE_LEFT_COMPUTER).y;
		bb = tb + getActionsPanelWidth();
		if (in(w, h, lb, rb, tb, bb))
			return INFO_LEFTCOMPUTER_STATUS1 + (int)((h - tb) / cardWidth);

		//check top computer status panel
		lb = getTopComputerPanelPosition().x + getActionsPanelPosition(ID_ROTATE_TOP_COMPUTER).x;
		rb = lb + getActionsPanelWidth();
		tb = getTopComputerPanelPosition().y + getActionsPanelPosition(ID_ROTATE_TOP_COMPUTER).y;
		bb = tb + getActionsPanelHeight();
		if (in(w, h, lb, rb, tb, bb))
			return INFO_TOPCOMPUTER_STATUS1 + (int)(2 - (w - lb) / cardWidth);

		//check right computer status panel
		lb = getRightComputerPanelPosition().x + getActionsPanelPosition(ID_ROTATE_RIGHT_COMPUTER).x;
		rb = lb + getActionsPanelHeight();
		tb = getRightComputerPanelPosition().y + getActionsPanelPosition(ID_ROTATE_RIGHT_COMPUTER).y;
		bb = tb + getActionsPanelWidth();
		if (in(w, h, lb, rb, tb, bb))
			return INFO_RIGHTCOMPUTER_STATUS1 + (int)(2 - (h - tb) / cardWidth);

		//check player status panel
		lb = getPlayerPanelPosition().x + getActionsPanelPosition(ID_ROTATE_PLAYER).x;
		rb = lb + getActionsPanelWidth();
		tb = getPlayerPanelPosition().y + getActionsPanelPosition(ID_ROTATE_PLAYER).y;
		bb = tb + getActionsPanelHeight();
		if (in(w, h, lb, rb, tb, bb))
			return INFO_PLAYER_STATUS1 + (int)((w - lb) / cardWidth);

		//check player hand
		lb = getPlayerPanelPosition().x + getHandPanelPosition(ID_ROTATE_PLAYER).x;
		rb = lb + getHandFaceUpPanelWidth();
		tb = getPlayerPanelPosition().y + getHandPanelPosition(ID_ROTATE_PLAYER).y;
		bb = tb + getHandFaceUpPanelHeight();
		if (in(w, h, lb, rb, tb, bb))
			return INFO_PLAYER_HAND1 + (int)((w - lb) / cardWidth);

		//check board position
		lb = getMineBoardPanelPosition().x;
		rb = lb + getMineBoardPanelWidth();
		tb = getMineBoardPanelPosition().y;
		bb = tb + getMineBoardPanelHeight();
		if (in(w, h, lb, rb, tb, bb))
			return INFO_MINEBOARD + (int)((w - lb) / cardWidth) + Command.NUM_MINEBOARD_PANEL_CARDS_WIDTH * (int)((h - tb) / cardHeight);

		return INFO_NONE;
	}

	public static boolean in(int w, int h, int lb, int rb, int tb, int bb) {
		return w >= lb && w <= rb && h >= tb && h <= bb;
	}

	public static Point getRewardCardPosition(int handPos, int maxPos, int rotateID) {
		int x, y;
		switch(rotateID) {
			case ID_ROTATE_LEFT_COMPUTER:
				x = getLeftComputerPanelPosition().x + D_LARGE_PANEL_PADDING;
				y = getLeftComputerPanelPosition().y + D_LARGE_PANEL_PADDING + (getCardPanelWidth() - D_REWARD_OVERLAPPING) * handPos;
				break;
			case ID_ROTATE_TOP_COMPUTER:
				x = getTopComputerPanelPosition().x + D_LARGE_PANEL_PADDING + (getCardPanelWidth() - D_REWARD_OVERLAPPING) * (maxPos - handPos - 1);
				y = getTopComputerPanelPosition().y + D_LARGE_PANEL_PADDING;
				break;
			case ID_ROTATE_RIGHT_COMPUTER:
				x = getRightComputerPanelPosition().x + D_LARGE_PANEL_PADDING;
				y = getRightComputerPanelPosition().y + D_LARGE_PANEL_PADDING + (getCardPanelWidth() - D_REWARD_OVERLAPPING) * (maxPos - handPos - 1);
				break;
			default:
				x = getPlayerPanelPosition().x + D_LARGE_PANEL_PADDING + (getCardPanelWidth() - D_REWARD_OVERLAPPING) * handPos;
				y = getPlayerPanelPosition().y + D_LARGE_PANEL_PADDING;
		}
		return new Point(x, y);
	}
}
