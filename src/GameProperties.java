
public class GameProperties {
final public static int FROG_STEP = 50; //equivalent to dimension of frog square
final public static int BOARD_HEIGHT_WITH_TOP_BAR = 10*FROG_STEP + 20; //470, currently
final public static int BOARD_HEIGHT = 10*FROG_STEP;
final public static int BOARD_WIDTH = 600;

//there will be 8 tracks, incidentally, currently the same size as STEP
final public static int TRACK = BOARD_HEIGHT/10;

//define Track constants -- this constitutes my layout of one column and 8 rows
final public static int TRACK_10_BASE = BOARD_HEIGHT/10; //50 px
final public static int TRACK_9_BASE = (BOARD_HEIGHT/10)*2; //100 px

final public static int TRACK_8_BASE = (BOARD_HEIGHT/10)*3; //150 px
final public static int TRACK_7_BASE = (BOARD_HEIGHT/10)*4; //200 px
final public static int TRACK_6_BASE = (BOARD_HEIGHT/10)*5;
final public static int TRACK_5_BASE = (BOARD_HEIGHT/10)*6;
final public static int TRACK_4_BASE = (BOARD_HEIGHT/10)*7;
final public static int TRACK_3_BASE = (BOARD_HEIGHT/10)*8;
final public static int TRACK_2_BASE = (BOARD_HEIGHT/10)*9;
final public static int TRACK_1_BASE = (BOARD_HEIGHT/10)*10;


}
