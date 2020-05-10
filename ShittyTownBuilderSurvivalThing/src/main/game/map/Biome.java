package main.game.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Point;

import main.game.controller.View;

public class Biome {

	private BType type;
	private Point centrePos;
	private Ellipse radius;

	private Map map;

	// A value to dictate how 'warm' or 'cold' each chunk should look (0 - 255)
	private int climate;

	public Biome(Map map, BType biome_type, float x, float y, float chunk_radius_x, float chunk_radius_y) {
		System.err.println("new biome@" + x + " " + y);
		this.map = map;
		type = biome_type;
		centrePos = new Point(x, y);

		climate = 255;

		this.map = map;

		float rad_x = (float) chunk_radius_x * MapConstants.CHUNK_WIDTH_PX;
		float rad_y = (float) chunk_radius_y * MapConstants.CHUNK_HEIGHT_PX;

		radius = new Ellipse(centrePos.getX(), centrePos.getY(), rad_x, rad_y);
		
	}

	public void tick() {
	}

	public void render(Graphics g) {
		/*
		g.setColor(Color.blue);
		g.draw(radius);
		g.drawString(type.name(), centrePos.getX(), centrePos.getY());
		*/
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

	public Point getCentrePos() {
		return centrePos;
	}

	public Ellipse getRadius() {
		return radius;
	}
	
	public BType getType() {
		return type;
	}
}
