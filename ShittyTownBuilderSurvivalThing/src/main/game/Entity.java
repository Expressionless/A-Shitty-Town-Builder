package main.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.map.Map;

public abstract class Entity {
	
	protected Rectangle bounds;

	protected Point origin;

	protected Image sprite;

	protected Map map;
	
	public Entity(Map m, float x, float y, Image sprite) {
		// Set up basic collision masks
		this.map = m;
		this.sprite = sprite;
		origin = new Point(sprite.getWidth() / 2, sprite.getHeight() / 2);
		bounds = new Rectangle(x - origin.getX(), y - origin.getY(), sprite.getWidth(), sprite.getHeight());
		map.addEntity(this);
	}

	public void tick() {
		step();
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, 
				bounds.getX(), bounds.getY());
		draw(g);
	}
	
	public abstract void step();
	public abstract void draw(Graphics g);
	
	// Getters and Setters
	
	public Rectangle getBounds() {
		return bounds;
	}

	public Point getCentrePoint() {
		return new Point(bounds.getX() + origin.getX(), bounds.getY() + origin.getY());
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	// Return the centre of the entity
	public Point getPos() {
		return new Point(bounds.getX() + origin.getX(), bounds.getY() + origin.getY());
	}

}
