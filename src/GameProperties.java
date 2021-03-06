
public class GameProperties {
final public static int FROG_STEP = 50; //equivalent to dimension of frog square
final public static int BOARD_HEIGHT_WITH_TOP_BAR = 8*FROG_STEP + 20; //470, currently
final public static int BOARD_HEIGHT = 8*FROG_STEP;
final public static int BOARD_WIDTH = 500;

//there will be 8 tracks, incidentally currently of the same size as STEP
final public static int TRACK = BOARD_HEIGHT/8;

//implement Track constants -- this constitutes my layout of one column and 8 rows
final public static int TRACK_EIGHT_HEIGHT = BOARD_HEIGHT/8; //50 px
final public static int TRACK_SEVEN_HEIGHT = (BOARD_HEIGHT/8)*2; //100 px

final public static int TRACK_SIX_HEIGHT = (BOARD_HEIGHT/8)*3; //150 px
final public static int TRACK_FIVE_HEIGHT = (BOARD_HEIGHT/8)*4;
final public static int TRACK_FOUR_HEIGHT = (BOARD_HEIGHT/8)*5;
final public static int TRACK_THREE_HEIGHT = (BOARD_HEIGHT/8)*6;
final public static int TRACK_TWO_HEIGHT = (BOARD_HEIGHT/8)*7;
final public static int TRACK_ONE_HEIGHT = (BOARD_HEIGHT/8)*8;
}
