package main.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {

	protected Rectangle bounds;

	protected Point origin;

	protected Image sprite;

	public Entity(float x, float y, Image sprite) {
		// Set up basic collision masks
		origin = new Point(sprite.getWidth() / 2, sprite.getHeight() / 2);
		bounds = new Rectangle(x - origin.getX(), y - origin.getY(), sprite.getWidth(), sprite.getHeight());
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	
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

}
