package main.game.entities;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.GameConstants;
import main.game.Entity;
import main.game.map.Map;
import main.util.Utils;

public abstract class Mob extends Entity {

	protected float health, health_max;

	// Pathing stuff
	protected Point des;

	protected int state;

	public Mob(Map m, float x, float y, Image sprite, float depth) {
		super(m, x, y, sprite, depth);
		init();
	}

	public void init() {
		des = new Point(pos.getX(), pos.getY());
		state = GameConstants.STATE_IDLE;
	}
	
	public abstract void handleStates();
	
	public void tick() {
		super.tick();
		handleStates();
	}

	public void render(Graphics g) {
		super.render(g);
	}


	public void move(Point move_vec) {
		pos.setX(pos.getX() + move_vec.getX());
		pos.setY(pos.getY() + move_vec.getY());
	}

	public void move(float spd, float angle) {
		float sin = (float) Math.sin(Math.toRadians(angle));
		float cos = (float) Math.cos(Math.toRadians(angle));
		move(new Point(spd * cos, spd * sin));
	}

	public void moveTo(Point target) {
		if (Utils.getDistTo(pos, target) >= (bounds.getWidth() / 2)) {
			direction = Utils.getPointDirection(pos, target);
			if (speed < max_speed)
				speed += acceleration;
			move(speed, direction);
		} else
			speed = 0;
	}

	// Getters and Setters

	public Point getDes() {
		return des;
	}

	public int getState() {
		return state;
	}

	public void setState(int index) {
		state = index;
	}

}
