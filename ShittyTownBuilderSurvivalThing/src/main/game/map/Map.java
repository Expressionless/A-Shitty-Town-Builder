package main.game.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.Graphics;

import main.Engine;
import main.GameConstants;
import main.game.Entity;
import main.game.controller.View;
import main.game.entities.mobs.Player;
import main.game.entities.mobs.neutral.Pig;

public class Map {

	public static final ArrayList<Entity> ENTITIES = new ArrayList<Entity>();

	private Chunk[][] chunkMap;

	private Player player;

	private int width, height;

	private View view;

	public Map(int width, int height) {
		System.out.println("creating map");
		this.width = width;
		this.height = height;

		// Create the chunkMap array
		float chunk_width_px = GameConstants.TILE_WIDTH * MapConstants.CHUNK_WIDTH;
		float chunk_height_px = GameConstants.TILE_HEIGHT * MapConstants.CHUNK_HEIGHT;

		chunkMap = new Chunk[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Chunk chunk = new Chunk(this, j * chunk_width_px, i * chunk_height_px, "A chunky boi");

				chunkMap[i][j] = chunk;
			}
		}
		new Pig(this, 32, 48);

		player = new Player(this, (float) (width * chunk_width_px) / 2, (float) (height * chunk_height_px) / 2);
		view = new View(this, player.getPos().getX() - GameConstants.WIDTH / 2,
				player.getPos().getY() - GameConstants.HEIGHT / 2, GameConstants.WIDTH, GameConstants.HEIGHT, player);
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

		// Sort entities by depth
		Collections.sort(ENTITIES,
				(Comparator<Entity>) (Entity e1, Entity e2) -> (int) (e1.getDepth() - e2.getDepth()));

		// Render the entities if they're within the view
		for (Entity entity : ENTITIES) {
			if (view.rectInView(entity.getBounds()))
				entity.render(g);
		}
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

	public Player getPlayer() {
		return player;
	}

}
