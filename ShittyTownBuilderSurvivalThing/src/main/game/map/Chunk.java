package main.game.map;

import static main.GameConstants.TILE_HEIGHT;
import static main.GameConstants.TILE_WIDTH;
import static main.game.map.MapConstants.CHUNK_HEIGHT;
import static main.game.map.MapConstants.CHUNK_WIDTH;
import static main.game.map.MapConstants.TREE_DENSITY;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.game.entities.props.Tree;
import main.util.ResourceLoader;

public class Chunk {

	private Map map;
	private Rectangle bounds;
	
	private Tile[][] tileMap;
	
	public Chunk(Map m, float x, float y, String type) {
		this.map = m;
		
		// Chunk width and height are measured in tiles
		bounds = new Rectangle(x, y, CHUNK_WIDTH * TILE_WIDTH, CHUNK_HEIGHT * TILE_HEIGHT);
		
		// Initialize all the tiles in the chunk
		tileMap = createChunk();
		addTrees(TREE_DENSITY);
	}
	
	public Tile[][] createChunk() {
		Tile[][] t_map = new Tile[CHUNK_WIDTH][CHUNK_HEIGHT];
		
		for(int i = 0; i < CHUNK_WIDTH; i++) {
			for(int j = 0; j < CHUNK_HEIGHT; j++) {
				Random r = new Random();
				SpriteSheet sprite_sheet = ResourceLoader.TILE_SHEETS.get("grass");
				int index = r.nextInt(sprite_sheet.getHorizontalCount() - 1);
				
				Image image = sprite_sheet.getSubImage(index, 0);
				
				float t_x = bounds.getX() + i * GameConstants.TILE_WIDTH;
				float t_y = bounds.getY() + j * GameConstants.TILE_HEIGHT;
				
				t_map[i][j] = new Tile(t_x, t_y, image);
			}
		}
		return t_map;
	}
	
	public void addTrees(int density) {
		Random r = new Random();
		SpriteSheet ss = ResourceLoader.SPRITE_SHEETS.get("trees");
		float width = ss.getSubImage(0, 0).getWidth();
		float height = ss.getSubImage(0, 0).getHeight();
		// Populate chunk with trees according to tree density
		for(int i = 0; i < density; i++) {
			float x = bounds.getX() + r.nextInt(CHUNK_WIDTH) * TILE_WIDTH + width / 4;
			float y = bounds.getY() + r.nextInt(CHUNK_HEIGHT) * TILE_HEIGHT + height / 4;
			
			new Tree(map, x, y);
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(Tile[] tileLine : tileMap) {
			for(Tile tile : tileLine) {
				tile.render(g);
			}
		}
	}
	// Getters and Setters
	
	public Rectangle getBounds() {
		return bounds;
	}
}
