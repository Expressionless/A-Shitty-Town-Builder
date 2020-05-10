package main.game.map;

public class MapConstants {
	
	// Depth values
	public static final int TREE_DEPTH = 48;
	public static final int CAMPFIRE_DEPTH = 40;
	public static final int HUMAN_DEPTH = 32;
	public static final int MEDIUM_ANIMAL_DEPTH = 24;

	// Chunk Size in tiles
	public static final int CHUNK_WIDTH = 8;
	public static final int CHUNK_HEIGHT = 8;
	
	// Tile Size in px
	public static final int TILE_WIDTH = 24;
	public static final int TILE_HEIGHT = 24;
	
	public static final int CHUNK_WIDTH_PX = CHUNK_WIDTH * TILE_WIDTH;
	public static final int CHUNK_HEIGHT_PX = CHUNK_HEIGHT * TILE_HEIGHT;
	
	// Size in chunks
	public static final int MIN_BIOME_SPACING_X = 2;
	public static final int MAX_BIOME_SPACING_X = 3;
	public static final int MIN_BIOME_SPACING_Y = 2;
	public static final int MAX_BIOME_SPACING_Y = 3;
	
	public static final int MIN_BIOME_WIDTH = 1;
	public static final int MAX_BIOME_WIDTH = 3;
	public static final int MIN_BIOME_HEIGHT = 1;
	public static final int MAX_BIOME_HEIGHT = 3;
		
	// No. of Trees per chunk
	public static final int TREE_DENSITY = 3;
}
