package main.game.entities;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import main.game.Entity;
import main.game.entities.psystem.Emitter;
import main.game.entities.psystem.ParticleType;
import main.util.ResourceLoader;

public abstract class Particle extends Entity {

	protected Emitter emitter;

	protected float size;

	// Timer stuffs
	protected int timer;
	protected boolean destroy = false;

	public static final HashMap<ParticleType, SpriteSheet> SPRITES = new HashMap<ParticleType, SpriteSheet>();

	// Load the sprites
	public static void loadSprites() {
		SPRITES.put(ParticleType.FLAME, ResourceLoader.loadSpriteSheet("\\res\\sprites\\particles\\flame", 12, 12));
	}

	public Particle(Emitter em, Image sprite, float x, float y, float direction, float acceleration, int depth,
			int life, float size) {
		super(em.getMap(), x, y, sprite, em.getDepth());
		this.emitter = em;
		this.pos = new Point(x, y);
		this.direction = direction;
		this.acceleration = acceleration;
		this.speed = 0;
		this.timer = life;
		this.size = size;

		this.depth = depth;
	}

	@Override
	public void step() {
		if (timer == -1) {
			int ind = emitter.getParticles().indexOf(this);
			emitter.getParticles().set(ind, null);
		} else if (timer > -1)
			timer -= 1;
		speed += acceleration;
		
		float newx = (float) (pos.getX() + speed * Math.cos(Math.toRadians(direction)));
		float newy = (float) (pos.getY() - speed * Math.sin(Math.toRadians(direction)));
		pos.setX(newx);
		pos.setY(newy);
		//System.out.println(pos.getY());
		updateParticle();
	}

	@Override
	public void draw(Graphics g) {
	}

	public void render(Graphics g) {
		drawParticle(g);
	}

	public abstract void updateParticle();

	public abstract void drawParticle(Graphics g);

	public boolean needsToDestroy() {
		return destroy;
	}
	
	// Getters and Setters
	public Emitter getEmitter() {
		return emitter;
	}

}
