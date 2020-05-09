package main.game.map;

import static main.game.map.MapConstants.TILE_HEIGHT;
import static main.game.map.MapConstants.TILE_WIDTH;
import static main.game.map.MapConstants.CHUNK_HEIGHT;
import static main.game.map.MapConstants.CHUNK_WIDTH;
import static main.game.map.MapConstants.TREE_DENSITY;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import main.game.entities.mobs.neutral.Pig;
import main.game.entities.props.Tree;
import main.util.ResourceLoader;

public class Chunk {

	private Map map;
	private Rectangle bounds;
	
	private Tile[][] tileMap;
	
	private Biome biome;
	
	public Chunk(Biome b, float x, float y, int type) {
		bounds = new Rectangle(x, y, CHUNK_WIDTH * TILE_WIDTH, CHUNK_HEIGHT * TILE_HEIGHT);
		biome = b;
		map = biome.getMap();
		// Chunk width and height are measured in tiles
		
		// Initialize all the tiles in the chunk
		tileMap = createChunk();
		switch(type) {
		case MapConstants.BIOME_FOREST:
			addTrees(TREE_DENSITY, .125f);
			break;
		case MapConstants.BIOME_DEAD_FOREST:
			addTrees(TREE_DENSITY / 2, 1f);
			break;
		case MapConstants.BIOME_PLAINS:
			addPigs(3);
			break;
		}
	}
	
	public Tile[][] createChunk() {
		Tile[][] t_map = new Tile[CHUNK_WIDTH][CHUNK_HEIGHT];
		
		for(int i = 0; i < CHUNK_WIDTH; i++) {
			for(int j = 0; j < CHUNK_HEIGHT; j++) {
				Random r = new Random();
				SpriteSheet sprite_sheet = ResourceLoader.TILE_SHEETS.get("grass");
				int index = r.nextInt(sprite_sheet.getHorizontalCount() - 1);
				
				Image image = sprite_sheet.getSubImage(index, 0);
				
				float t_x = bounds.getX() + i * TILE_WIDTH;
				float t_y = bounds.getY() + j * TILE_HEIGHT;
				
				t_map[i][j] = new Tile(t_x, t_y, image);
			}
		}
		return t_map;
	}
	
	public void addTrees(int density, float proportion) {
		Random r = new Random();
		SpriteSheet ss = ResourceLoader.SPRITE_SHEETS.get("trees");
		
		float width = ss.getSubImage(0, 0).getWidth();
		float height = ss.getSubImage(0, 0).getHeight();
		
		// Populate chunk with trees according to tree density
		for(int i = 0; i < density; i++) {
			float x = bounds.getX() + r.nextInt(CHUNK_WIDTH) * TILE_WIDTH + width / 4;
			float y = bounds.getY() + r.nextInt(CHUNK_HEIGHT) * TILE_HEIGHT + height / 4;
			int decider = r.nextInt(100);
			
			int tree_type;
			
			// Make some dead trees
			if(decider < proportion * 100)
				tree_type = r.nextInt(2) + 2;
			else
				tree_type = r.nextInt(2);
			new Tree(map, x, y, tree_type);
		}
	}
	
	public void addTrees(int density) {
		//just splatter dead trees around the place, (1/8 proportion)
		addTrees(density, 0.125f);
	}
	
	public void addPigs(int density) {
		Random r = new Random();

		float width = Pig.ss.getSubImage(0, 0).getWidth();
		float height = Pig.ss.getSubImage(0, 0).getHeight();
		
		for(int i = 0; i < density; i++) {
			float x = bounds.getX() + r.nextInt(CHUNK_WIDTH) * TILE_WIDTH + width / 4;
			float y = bounds.getY() + r.nextInt(CHUNK_HEIGHT) * TILE_HEIGHT + height / 4;
			new Pig(map, x, y);
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int climate) {
		for(Tile[] tileLine : tileMap) {
			for(Tile tile : tileLine) {
				tile.render(g, climate);
			}
		}
	}
	
	public void render(Graphics g) {
		render(g, 255);
	}
	
	// Getters and Setters
	
	public Rectangle getBounds() {
		return bounds;
	}
}
