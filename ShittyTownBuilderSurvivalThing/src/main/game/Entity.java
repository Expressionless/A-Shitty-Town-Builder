package main.game;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.game.entities.Particle;
import main.game.entities.mobs.Player;
import main.game.map.Map;

public abstract class Entity {

	protected int[] alarm = { -1, -1, -1, -1, -1 };

	protected Rectangle bounds;
	protected Point pos;
	protected Point origin;
	protected Map map;

	// Graphical stuff
	protected SpriteSheet sprite;
	protected Animation current_animation;

	protected final HashMap<Anim, Animation> animations = new HashMap<Anim, Animation>();

	protected float depth;

	// Physics stuff
	protected float speed = 0;
	protected float max_speed;
	protected float direction, look_direction;
	protected float acceleration;

	public Entity(Map m, float x, float y, SpriteSheet sprite, float depth) {
		// Set up basic collision masks
		this.map = m;
		this.sprite = sprite;

		if(sprite == null)
			System.out.println(this);
		this.current_animation = new Animation(sprite, (int)((1f / (float)GameConstants.FPS) * 1000));
		
		this.depth = depth;
		
		if (this.sprite != null) {
			origin = new Point(sprite.getWidth() / 2, sprite.getHeight() / 2);
			bounds = new Rectangle(x - origin.getX(), y - origin.getY(), sprite.getWidth(), sprite.getHeight());
		} else {
			origin = null;
			bounds = null;
		}
		pos = new Point(x, y);
		
		initAnimations();
		
		// Particle render and tick is handled by emitter
		if(!(this instanceof Particle))
			map.addEntity(this);
	}

	public void tick() {
		
		bounds.setX(pos.getX() - bounds.getWidth() / 2);
		bounds.setY(pos.getY() - bounds.getHeight() / 2);
		updateAlarms();
		step();
	}

	public abstract void prerender(Graphics g);
	public abstract void initAnimations();
	
	public void render(Graphics g) {
		render(g, 1.0f, 1.0f);
	}
	
	public void render(Graphics g, float climate, float lighting) {
		prerender(g);
		
		Color renderCol = new Color(lighting, lighting, lighting);
		
		if (current_animation != null) {
			if(speed != 0) {
				current_animation.start();
				
				g.drawAnimation(current_animation, bounds.getX(), bounds.getY(), new Color(1.0f, 1.0f, 1.0f, 0f));
				Image current_image = current_animation.getCurrentFrame().copy();
				current_image.setRotation(direction);
				if(this instanceof Player)
					current_image.setRotation(look_direction);
				g.drawImage(current_image, bounds.getX(), bounds.getY(), renderCol);
			} else {
				Image current_image = current_animation.getImage(0);
				current_image.setRotation(direction);
				if(this instanceof Player)
					current_image.setRotation(look_direction);
				g.drawImage(current_image, bounds.getX(), bounds.getY(), renderCol);
			}
		}
		draw(g);
	}

	public void drawImageScaled(Graphics g, Image image, float x, float y, float scale, Color c) {
		float x1 = x;
		float y1 = y;
		float x2 = x + image.getWidth() * scale;
		float y2 = y + image.getHeight() * scale;
		
		float srcx1 = 0;
		float srcy1 = 0;
		float srcx2 = image.getWidth();
		float srcy2 = image.getHeight();
		
		g.drawImage(image, x1, y1, x2, y2, srcx1, srcy1, srcx2, srcy2, c);
	}
	
	public void drawImageScaled(Graphics g, Image image, float x, float y, float scale) {
		drawImageScaled(g, image, x, y, scale, new Color(255, 255, 255, 255));
	}

	public abstract void step();

	public abstract void draw(Graphics g);

	// Alarm management
	public void updateAlarms() {
		for (int i = 0; i < alarm.length; i++) {
			if (alarm[i] > -1) {
				alarm[i] -= 1;
			}
		}
	}

	// Add animation
	public void addAnimation(Anim name, Animation a) {
		animations.put(name, a);
	}
	
	/********************* Getters and Setters ***********************/

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
