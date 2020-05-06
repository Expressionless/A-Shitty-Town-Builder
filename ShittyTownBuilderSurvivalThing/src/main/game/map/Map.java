package main.game.map;

import main.GameConstants;

public class Map {

	private Tile[][] tileMap;
	
	private int width, height;
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		
		// Create the tileMap array
		int tile_xcount = (int)Math.ceil(width / GameConstants.TILE_WIDTH);
		int tile_ycount = (int)Math.ceil(height / GameConstants.TILE_HEIGHT);
	}
	
}
