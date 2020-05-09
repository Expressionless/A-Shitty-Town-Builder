package main.game.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Point;

import main.game.controller.View;

public class Biome {

	private int type;
	private Point centrePos;
	private Ellipse radius;

	private Chunk[][] chunks;

	private Map map;

	// A value to dictate how 'warm' or 'cold' each chunk should look (0 - 255)
	private int climate;

	public Biome(Map map, int biome_type, float x, float y, float chunk_radius_x, float chunk_radius_y) {
		System.err.println("new biome@" + x + " " + y);
		this.map = map;
		type = biome_type;
		centrePos = new Point(x, y);

		climate = 255;

		this.map = map;

		float rad_x = (float) chunk_radius_x * MapConstants.CHUNK_WIDTH_PX;
		float rad_y = (float) chunk_radius_x * MapConstants.CHUNK_HEIGHT_PX;

		radius = new Ellipse(centrePos.getX(), centrePos.getY(), rad_x, rad_y);
		
		generateChunks();

	}

	public void tick() {
		for (Chunk[] chunk_line : chunks) {
			for (Chunk chunk : chunk_line)
				chunk.tick();
		}
	}

	public void render(Graphics g) {
		View view = map.getView();

		for (Chunk[] chunk_line : chunks) {
			for (Chunk chunk : chunk_line) {
				if (view.rectInView(chunk.getBounds()))
					chunk.render(g, climate);
			}
		}
		
		g.setColor(Color.red);
		g.draw(radius);
		g.drawString(Integer.toString(type), centrePos.getX(), centrePos.getY());
	}

	public void generateChunks() {
		/**
		 * generates the chunks for the biome
		 **/

		float top_left_x = centrePos.getX() - radius.getWidth() / 2;
		float top_left_y = centrePos.getY() - radius.getHeight() / 2;

		int chunk_count_x = (int) Math.ceil(radius.getWidth() / MapConstants.CHUNK_WIDTH_PX);
		int chunk_count_y = (int) Math.ceil(radius.getHeight() / MapConstants.CHUNK_HEIGHT_PX);

		chunks = new Chunk[chunk_count_y][chunk_count_x];

		// If the chunk will be in the radius of the biome, add it
		for (int y = 0; y < chunk_count_y; y++) {
			for (int x = 0; x < chunk_count_x; x++) {
				float chunk_x = top_left_x + MapConstants.CHUNK_WIDTH_PX * x;
				float chunk_y = top_left_y + MapConstants.CHUNK_HEIGHT_PX * y;

				Point chunk_pos = new Point(chunk_x, chunk_y);

				chunks[x][y] = createChunk(chunk_pos);

			}
		}
	}

	public Chunk createChunk(float x, float y) {
		Chunk c = new Chunk(this, x, y, type);
		return c;
	}

	public Chunk createChunk(Point p) {
		return createChunk(p.getX(), p.getY());
	}

	// Getters and Setters
	public Map getMap() {
		return map;
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

	public Point getCentrePos() {
		return centrePos;
	}
}
