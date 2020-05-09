package main.game.map;

public class MapConstants {

	// Chunk Size in tiles
	public static final int CHUNK_WIDTH = 16;
	public static final int CHUNK_HEIGHT = 16;
	
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
	public static final int TREE_DENSITY = 8;
	
	public static final int BIOME_PLAINS = 0;
	public static final int BIOME_FOREST = 1;
	public static final int BIOME_DEAD_FOREST = 2;
	
	public static final int BIOME_VARIATION = 3;
}
