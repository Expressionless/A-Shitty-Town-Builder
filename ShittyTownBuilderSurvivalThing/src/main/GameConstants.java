package main;

import java.io.File;

import org.newdawn.slick.Input;

public class GameConstants {

	public static final String ABS_PATH = new File("").getAbsolutePath();

	public static final int FPS = 60;
	
	public static final String TITLE = "SHITTY TOWN BUILDER";
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public static final int CAMERA_DOWN = Input.KEY_DOWN;
	public static final int CAMERA_UP = Input.KEY_UP;
	public static final int CAMERA_LEFT = Input.KEY_LEFT;
	public static final int CAMERA_RIGHT = Input.KEY_RIGHT;
	public static final int CAMERA_SPEED = 15;

	public static final int PLAYER_UP = Input.KEY_W;
	public static final int PLAYER_LEFT = Input.KEY_A;
	public static final int PLAYER_DOWN = Input.KEY_S;
	public static final int PLAYER_RIGHT = Input.KEY_D;
	
	
}
