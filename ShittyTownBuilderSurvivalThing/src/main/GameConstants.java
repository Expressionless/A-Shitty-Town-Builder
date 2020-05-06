package main;

import java.io.File;

import org.newdawn.slick.Input;

public class GameConstants {

	public static final String ABS_PATH = new File("").getAbsolutePath();

	public static final String TITLE = "SHITTY TOWN BUILDER";
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public static final int TILE_WIDTH = 24;
	public static final int TILE_HEIGHT = 24;

	public static final int CAMERA_DOWN = Input.KEY_DOWN;
	public static final int CAMERA_UP = Input.KEY_UP;
	public static final int CAMERA_LEFT = Input.KEY_LEFT;
	public static final int CAMERA_RIGHT = Input.KEY_RIGHT;
}
