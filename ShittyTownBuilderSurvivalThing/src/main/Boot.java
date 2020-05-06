package main;

public class Boot {

	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", GameConstants.ABS_PATH);
		new Engine();
	}
	
}
