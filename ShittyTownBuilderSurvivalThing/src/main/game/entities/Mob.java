package main.game.entities;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import main.GameConstants;
import main.game.Entity;
import main.game.map.Map;
import main.util.Utils;

public abstract class Mob extends Entity {

	protected float health, health_max;

	// Pathing stuff
	protected Point des;

	protected MobState state;

	public Mob(Map m, float x, float y, SpriteSheet sprite, float depth) {
		super(m, x, y, sprite, depth);
		init();
	}

	public void init() {
		des = new Point(pos.getX(), pos.getY());
		state = MobState.IDLE;
	}
	
	public abstract void handleStates();
	
	public void tick() {
		super.tick();
		handleStates();
	}

	public void prerender(Graphics g) {
		
	}
	
	public void render(Graphics g, float climate, float lighting) {
		super.render(g, climate, lighting);
	}


	public void move(Point move_vec) {
		pos.setX(pos.getX() + move_vec.getX());
		pos.setY(pos.getY() + move_vec.getY());
	}

	public void move(float spd, float angle) {
		float sin = (float) Math.sin(Math.toRadians(angle));
		float cos = (float) Math.cos(Math.toRadians(angle));
		direction = angle;
		move(new Point(spd * cos, spd * sin));
	}

	public void moveTo(Point target) {
		if (Utils.getDistTo(pos, target) >= (bounds.getWidth() / 2)) {
			float dir = Utils.getPointDirection(pos, target);
				
			if (speed < max_speed)
				speed += acceleration;
			move(speed, dir);
		} else
			speed = 0;
	}

	// Getters and Setters

	public Point getDes() {
		return des;
	}

	public MobState getState() {
		return state;
	}

	public void setState(MobState index) {
		state = index;
	}

}
