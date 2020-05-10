package main.game.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.Engine;
import main.GameConstants;
import main.game.Entity;
import main.game.controller.View;
import main.game.entities.mobs.Player;
import main.game.entities.props.Campfire;
import main.util.Utils;

public class Map {

	public static final ArrayList<Entity> ENTITIES = new ArrayList<Entity>();

	private Biome[][] biomes;
	private Chunk[][] chunks;

	private Player player;

	private int width, height;

	private View view;

	public Map(int width, int height) {
		System.out.println("creating map");
		this.width = width;
		this.height = height;

		// Calculate biome_width in px
		int biome_width_px = MapConstants.MAX_BIOME_WIDTH * MapConstants.CHUNK_WIDTH_PX;
		int biome_height_px = MapConstants.MAX_BIOME_HEIGHT * MapConstants.CHUNK_HEIGHT_PX;

		int biome_count_x = (int) Math.ceil(width / biome_width_px);
		int biome_count_y = (int) Math.ceil(height / biome_height_px);

		int chunk_count_x = biome_count_x * MapConstants.MAX_BIOME_WIDTH;
		int chunk_count_y = biome_count_y * MapConstants.MAX_BIOME_HEIGHT;

		// Create the biome array
		biomes = new Biome[biome_count_y][biome_count_x];
		generateBiomes(biome_count_x, biome_count_y);

		// Create the chunk array
		chunks = new Chunk[chunk_count_y][chunk_count_x];
		generateChunks(chunk_count_x, chunk_count_y);

		float chunk_width_px = MapConstants.TILE_WIDTH * MapConstants.CHUNK_WIDTH;
		float chunk_height_px = MapConstants.TILE_HEIGHT * MapConstants.CHUNK_HEIGHT;

		player = new Player(this, (float) (width) / 2, (float) (height) / 2);
		while (view == null)
			view = new View(this, player.getPos().getX() - GameConstants.WIDTH / 2,
					player.getPos().getY() - GameConstants.HEIGHT / 2, GameConstants.WIDTH, GameConstants.HEIGHT,
					player);

		new Campfire(this, player.getPos().getX(), player.getPos().getY());
	}

	public void tick() {
		// Update the view
		view.tick(Engine.getInput());

		// Update biomes
		for (Biome[] biome_line : biomes) {
			for (Biome biome : biome_line) {
				biome.tick();
			}
		}
		// Chunks
		for (Chunk[] chunk_line : chunks) {
			for (Chunk chunk : chunk_line) {
				chunk.tick();
			}
		}
		// Update the entities
		for (Entity entity : ENTITIES) {
			entity.tick();
		}
	}

	public void render(Graphics g) {

		// Sort entities by depth
		Collections.sort(ENTITIES,
				(Comparator<Entity>) (Entity e1, Entity e2) -> (int) (e1.getDepth() - e2.getDepth()));

		// Chunks
		for (Chunk[] chunk_line : chunks) {
			for (Chunk chunk : chunk_line) {
				chunk.render(g);
			}
		}

		// Render biomes
		for (Biome[] biome_line : biomes) {
			for (Biome biome : biome_line) {
				biome.render(g);
			}
		}

		float climate = 1.0f;
		float lighting = 1.0f;

		// Render the entities if they're within the view
		for (Entity entity : ENTITIES) {
			if (view.rectInView(entity.getBounds()))
				entity.render(g, climate, lighting);
		}
	}

	public void generateChunks(int x_count, int y_count) {
		float width_px = MapConstants.CHUNK_WIDTH_PX;
		float height_px = MapConstants.CHUNK_HEIGHT_PX;

		for (int y = 0; y < y_count; y++) {
			for (int x = 0; x < x_count; x++) {
				chunks[x][y] = createChunk(x * width_px, y * height_px);
			}
		}
	}

	public void generateBiomes(int x_count, int y_count) {
		for (int y = 0; y < y_count; y++) {
			for (int x = 0; x < x_count; x++) {

				// Get the biome size in chunks
				int biome_width = Utils.getRandInRange(MapConstants.MIN_BIOME_WIDTH, MapConstants.MAX_BIOME_WIDTH);
				int biome_height = Utils.getRandInRange(MapConstants.MIN_BIOME_HEIGHT, MapConstants.MAX_BIOME_HEIGHT);

				float biome_width_px = biome_width * MapConstants.CHUNK_WIDTH_PX;
				float biome_height_px = biome_height * MapConstants.CHUNK_HEIGHT_PX;

				// Get biome spacing
				int biome_space_x = Utils.getRandInRange(MapConstants.MIN_BIOME_SPACING_X,
						MapConstants.MAX_BIOME_SPACING_X);
				int biome_space_y = Utils.getRandInRange(MapConstants.MIN_BIOME_SPACING_Y,
						MapConstants.MAX_BIOME_SPACING_Y);

				float space_x_px = biome_space_x * MapConstants.CHUNK_WIDTH_PX;
				float space_y_px = biome_space_y * MapConstants.CHUNK_HEIGHT_PX;

				// Get previous biome x & y
				float prev_biome_x, prev_biome_y;

				if (y != 0) {
					prev_biome_y = biomes[x][y - 1].getCentrePos().getY();
				} else {
					prev_biome_y = -biome_height_px / 2;
				}

				if (x != 0) {
					prev_biome_x = biomes[x - 1][y].getCentrePos().getX();
				} else {
					prev_biome_x = -biome_width_px / 2;
				}

				float biome_x = prev_biome_x + space_x_px;
				float biome_y = prev_biome_y + space_y_px;
				try {
					biomes[x][y] = createBiome(biome_x, biome_y, biome_width, biome_height);
				} catch (Exception e) {
					System.err.println(x_count + " " + y_count + " : " + x + " " + y);
				}
			}
		}
	}

	/**
	 * 
	 * @param x      x-coordinate of the centre of the biome
	 * @param y      y-coordinate of the centre of the biome
	 * @param width  width at the widest point of the biome (ellipse)
	 * @param height height at the widest point of the biome (ellipse)
	 * @return A biome of random type at the given coordinates with the given width
	 *         and height
	 */
	public Biome createBiome(float x, float y, float width, float height) {
		Biome biome;
		Random r = new Random();
		int type = r.nextInt(BType.values().length);
		biome = new Biome(this, BType.values()[type], x, y, width, height);
		return biome;
	}

	public Biome getBiome(Point p) {
		Biome current_biome = biomes[0][0];
		for (Biome[] biome_line : biomes) {
			for (Biome biome : biome_line) {
				if (biome.getRadius().contains(p)) {
					current_biome = biome;
					return current_biome;
				}
			}
		}

		return current_biome;
	}

	public Chunk createChunk(float x, float y) {
		Biome biome = getBiome(new Point(x, y));
		Chunk chunk = new Chunk(biome, x, y, biome.getType());
		return chunk;
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

	public Biome[][] getBiomes() {
		return biomes;
	}

	public Biome getBiome(int x, int y) {
		try {
			return biomes[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Failed to retrieve biome at: (" + x + " " + y + ")");
			if (x < 0)
				System.err.println("x cannot be negative");
			else if (x > biomes.length)
				System.err.println("x is bigger than map's width");
			if (y < 0)
				System.err.println("y cannot be negative");
			else if (y > biomes[0].length)
				System.err.println("y is bigger than map's height");
			System.err.println("---------------------------------------");
			e.printStackTrace();
			return null;
		}
	}

	public Chunk[][] getChunks() {
		return chunks;
	}

	public Chunk getChunk(int x, int y) {
		try {
			return chunks[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Failed to retrieve chunk at: (" + x + " " + y + ")");
			if (x < 0)
				System.err.println("x cannot be negative");
			else if (x > chunks.length)
				System.err.println("x is bigger than chunkMap width");
			if (y < 0)
				System.err.println("y cannot be negative");
			else if (y > chunks[0].length)
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

class SortBySize implements Comparator<Biome> {
	public int compare(Biome b1, Biome b2) {
		// Area is pi * a * b
		float area1 = (float)Math.PI * b1.getRadius().getRadius1() * b1.getRadius().getRadius2();
		float area2 = (float)Math.PI * b2.getRadius().getRadius1() * b2.getRadius().getRadius2();
		
		return (int)(area2 - area1);
	}
}
