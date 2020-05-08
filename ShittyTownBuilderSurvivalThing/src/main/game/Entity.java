package main.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.map.Map;

public abstract class Entity {

	protected int[] alarm = {-1, -1, -1, -1, -1};
	
	protected Rectangle bounds;
	protected Point pos;

	protected Point origin;

	protected Image sprite;

	protected Map map;

	protected float depth;
	
	protected float speed = 0;
	protected float max_speed;
	protected float direction;
	protected float acc;
	
	protected int image_index = 0;
	
	public Entity(Map m, float x, float y, Image sprite, float depth) {
		// Set up basic collision masks
		this.map = m;
		this.sprite = sprite;
		
		this.depth = depth;
		
		if(this.sprite != null) {
			origin = new Point(sprite.getWidth() / 2, sprite.getHeight() / 2);
			bounds = new Rectangle(x - origin.getX(), y - origin.getY(), sprite.getWidth(), sprite.getHeight());
		} else {
			origin = null;
			bounds = null;
		}
		pos = new Point(x, y);
		map.addEntity(this);
	}

	public void tick() {
		bounds.setX(pos.getX() - bounds.getWidth() / 2);
		bounds.setY(pos.getY() - bounds.getHeight() / 2);
		updateAlarms();
		step();
	}

	public void render(Graphics g) {
		if(sprite != null)
			g.drawImage(sprite, bounds.getX(), bounds.getY());
		draw(g);
	}

	public abstract void step();
	public abstract void draw(Graphics g);
	
	// Alarm management
	public void updateAlarms() {
		for(int i = 0; i < alarm.length; i++) {
			if(alarm[i] > -1) {
				alarm[i] -= 1;
			}
		}
	}
	
	/*********************Getters and Setters***********************/

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

	public float getDepth() {
		return depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}
	
	public int[] getAlarms() {
		return alarm;
	}
	
	public int getAlarm(int index) {
		return alarm[index];
	}
	
	public void setAlarm(int index, int value) {
		alarm[index] = value;
	}

}
