import java.util.*;

public class Command {
	//static commands
	static final int ID_LEFTCOMPUTER  = 0,
					 ID_TOPCOMPUTER   = 1,
					 ID_RIGHTCOMPUTER = 2,
					 ID_PLAYER		  = 3;


	static final int CARDTYPE_EMPTY  = -1,
					 CARDTYPE_ROLE   = 0,
					 CARDTYPE_ACTION = 1,
					 CARDTYPE_PATH   = 2,
					 CARDTYPE_REWARD = 3;

	static final int ID_ROLE_EMPTY    = -1,
					 ID_ROLE_MINER1   = 0,
					 ID_ROLE_MINER2   = 1,
					 ID_ROLE_MINER3   = 2,
					 ID_ROLE_MINER4   = 3,
					 ID_ROLE_SABOTEUR = 4,
					 ID_ROLE_BACK     = 5,

					 ID_ACTION_EMPTY               = -1,
					 ID_ACTION_TOWED               = 0,
					 ID_ACTION_MACBROKEN           = 1,
					 ID_ACTION_HSUANTIEN           = 2,

					 ID_ACTION_UNTOWED             = 3,
					 ID_ACTION_MACREPAIRED         = 4,
					 ID_ACTION_HAHA                = 5,
					 ID_ACTION_UNTOWED_MACREPAIRED = 6,
					 ID_ACTION_UNTOWED_HAHA        = 7,
					 ID_ACTION_MACREPAIRED_HAHA    = 8,

					 ID_ACTION_GPS                 = 9,
					 ID_ACTION_STUPIDBIRD          = 10,
					 ID_ACTION_BACK      	       = 11,

					 //[top, right, bottom, left]
					 ID_PATH_EMPTY     = -1,
					 ID_PATH_START     = 0,
					 ID_PATH_REALGOAL  = 1,
					 ID_PATH_FAKEGOAL1 = 2,
					 ID_PATH_FAKEGOAL2 = 3,

					 ID_PATH_PASS_1010 = 4,
					 ID_PATH_PASS_1110 = 5,
					 ID_PATH_PASS_1111 = 6,
					 ID_PATH_PASS_0110 = 7,
					 ID_PATH_PASS_0011 = 8,
					 ID_PATH_PASS_1101 = 9,
					 ID_PATH_PASS_0101 = 10,

					 ID_PATH_BLOCK_0010 = 11,
					 ID_PATH_BLOCK_1011 = 12,
					 ID_PATH_BLOCK_1111 = 13,
					 ID_PATH_BLOCK_0110 = 14,
					 ID_PATH_BLOCK_0011 = 15,
					 ID_PATH_BLOCK_1010 = 16,
					 ID_PATH_BLOCK_1101 = 17,
					 ID_PATH_BLOCK_0101 = 18,
					 ID_PATH_BLOCK_0001 = 19,

					 ID_PATH_BACK       = 20,
					 ID_PATH_GOAL_BACK  = 21,

					 ID_REWARD_EMPTY = -1,
					 ID_REWARD_PASS  = 0,
					 ID_REWARD_APLUS = 1,
					 ID_REWARD_RANK1 = 2,
					 ID_REWARD_BACK  = 3;

	static final int NUM_ROLE_MINER    = 4,
					 NUM_ROLE_SABOTEUR = 1,

					 NUM_ACTION_TOWED               = 3,
					 NUM_ACTION_MACBROKEN           = 3,
					 NUM_ACTION_HSUANTIEN           = 3,

					 NUM_ACTION_UNTOWED             = 2,
					 NUM_ACTION_MACREPAIRED         = 2,
					 NUM_ACTION_HAHA                = 2,
					 NUM_ACTION_UNTOWED_MACREPAIRED = 1,
					 NUM_ACTION_UNTOWED_HAHA        = 1,
					 NUM_ACTION_MACREPAIRED_HAHA    = 1,

					 NUM_ACTION_GPS                 = 6,
					 NUM_ACTION_STUPIDBIRD          = 3,

					 //[top, right, bottom, left]
					 NUM_PATH_START     = 1,
					 NUM_PATH_REALGOAL  = 1,
					 NUM_PATH_FAKEGOAL1 = 1,
					 NUM_PATH_FAKEGOAL2 = 1,

					 NUM_PATH_PASS_1010 = 4,
					 NUM_PATH_PASS_1110 = 5,
					 NUM_PATH_PASS_1111 = 5,
					 NUM_PATH_PASS_0110 = 4,
					 NUM_PATH_PASS_0011 = 5,
					 NUM_PATH_PASS_1101 = 5,
					 NUM_PATH_PASS_0101 = 3,

					 NUM_PATH_BLOCK_0010 = 1,
					 NUM_PATH_BLOCK_1011 = 1,
					 NUM_PATH_BLOCK_1111 = 1,
					 NUM_PATH_BLOCK_0110 = 1,
					 NUM_PATH_BLOCK_0011 = 1,
					 NUM_PATH_BLOCK_1010 = 1,
					 NUM_PATH_BLOCK_1101 = 1,
					 NUM_PATH_BLOCK_0101 = 1,
					 NUM_PATH_BLOCK_0001 = 1,

					 NUM_REWARD_PASS  = 16,
					 NUM_REWARD_APLUS = 8,
					 NUM_REWARD_RANK1 = 4;

	static final int NUM_DRAW_ROLE   = 5,
					 NUM_DRAW_HAND   = 6,
					 NUM_DRAW_ENDS   = 4,
					 NUM_DRAW_TOTAL  = 67,
					 NUM_DISCARD_MAX = 3,
					 NUM_DRAW_REWARD = 4,
					 NUM_PLAYER		 = 4;
					 
	static final int NUM_ROLE_PANEL_CARDS             = 1,
					 NUM_ACTIONS_PANEL_CARDS          = 3,
					 NUM_HAND_PANEL_CARDS             = 6,
					 NUM_MINEBOARD_PANEL_CARDS_WIDTH  = 9,
					 NUM_MINEBOARD_PANEL_CARDS_HEIGHT = 5,
					 NUM_DECK_PANEL_CARDS             = 1;

	static final int PLAYER_LOSE = -1;

}