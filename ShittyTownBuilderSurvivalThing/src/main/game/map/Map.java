package main.game.map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.Engine;
import main.GameConstants;
import main.game.Entity;
import main.game.controller.Cursor;
import main.game.controller.View;

public class Map {

	public static final ArrayList<Entity> ENTITIES = new ArrayList<Entity>();

	private Chunk[][] chunkMap;
	
	private int width, height;

	private View view;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		
		// Create the chunkMap array
		float chunk_width_px = GameConstants.TILE_WIDTH * MapConstants.CHUNK_WIDTH;
		float chunk_height_px = GameConstants.TILE_HEIGHT * MapConstants.CHUNK_HEIGHT;

		int chunk_xcount = (int) Math.ceil(width / chunk_width_px);
		int chunk_ycount = (int) Math.ceil(height / chunk_height_px);

		chunkMap = new Chunk[chunk_xcount][chunk_ycount];

		for (int i = 0; i < chunk_xcount; i++) {
			for (int j = 0; j < chunk_ycount; j++) {
				Chunk chunk = new Chunk(this, i * chunk_width_px, j * chunk_height_px, "A chunky boi");

				chunkMap[i][j] = chunk;
			}
		}

		view = new View(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
	}

	public void tick() {
		// Update the view
		view.tick(Engine.getInput());

		// Update the entities
		for (Entity entity : ENTITIES) {
			entity.tick();
		}
	}

	public void render(Graphics g) {

		// Render the tiles if they're within the view
		for (Chunk[] chunkLine : chunkMap) {
			for (Chunk chunk : chunkLine) {
				if (view.rectInView(chunk.getBounds()))
					chunk.render(g);
			}
		}

		// Render the entities if they're within the view
		for (Entity entity : ENTITIES) {
			if (view.rectInView(entity.getBounds()))
				entity.render(g);
		}

		// Render the view stuff
		g.setColor(Color.red);
		g.draw(view.getViewBounds());
		g.setColor(Color.blue);
		g.draw(view.getRenderBounds());
	}

	public boolean addEntity(Entity e) {

		// Kick out by lazy evaluation if the entity is null, don't add it
		if (e == null)
			return false;
		else
			ENTITIES.add(e);
		return true;
	}

	// Getters and Setters

	public Chunk[][] getChunks() {
		return chunkMap;
	}

	public Chunk getChunk(int x, int y) {
		try {
			return chunkMap[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Failed to retrieve chunk at: (" + x + " " + y + ")");
			if (x < 0)
				System.err.println("x cannot be negative");
			else if (x > chunkMap.length)
				System.err.println("x is bigger than chunkMap width");
			if (y < 0)
				System.err.println("y cannot be negative");
			else if (y > chunkMap[0].length)
				System.err.println("y is bigger than chunkMap height");
			System.err.println("---------------------------------------");
			e.printStackTrace();
			return null;
		}
	}

	public View getView() {
		return view;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
