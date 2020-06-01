package main.game.entities.mobs.neutral;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.game.entities.Mob;
import main.game.entities.MobState;
import main.game.map.Map;
import main.game.map.MapConstants;
import main.util.ResourceLoader;
import main.util.Utils;

public class Pig extends Mob {

	public static final SpriteSheet ss = ResourceLoader.SPRITE_SHEETS.get("pig");
	
	public static final int MIN_WAIT_TIME = 180; // 3 Seconds
	public static final int MAX_WAIT_TIME = 300; // 5 Seconds
	
	public Pig(Map m, float x, float y) {
		super(m, x, y, ss, MapConstants.MEDIUM_ANIMAL_DEPTH);
		this.current_animation = new Animation(sprite, (int)((4.5f / (float)GameConstants.FPS) * 1000));
		origin = new Point(current_animation.getWidth() / 2, current_animation.getHeight() / 2);
		bounds = new Rectangle(x - origin.getX() / 2, y - origin.getY() / 2, current_animation.getWidth(), current_animation.getHeight());
		
		// Init stats
		speed = 0;
		max_speed = 1;
		acceleration = 0.1f;
		
		current_animation.setLooping(true);
	}

	@Override
	public void initAnimations() {
		
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics g) {
	}
	public void handleStates() {
		switch (state) {
		case IDLE:
			speed = 0;
			if (alarm[0] == -1) {
				// Set an alarm between 0.5 and 1.5 seconds, or 30 and 90 frames
				Random r = new Random();
				setAlarm(0, r.nextInt(MAX_WAIT_TIME - MIN_WAIT_TIME) + MIN_WAIT_TIME);

				// Set a new point
				float new_dir = r.nextFloat() * 360;

				float move_radius = MapConstants.TILE_WIDTH * (3 + r.nextInt(2)); // Move 3 - 5 tiles around

				float lengthdir_x = (float) Math.cos(new_dir) * move_radius;
				float lengthdir_y = (float) Math.sin(new_dir) * move_radius;

				des.setX(pos.getX() + lengthdir_x);
				des.setY(pos.getY() + lengthdir_y);

				state = MobState.MOVING;
			}
			break;
		case MOVING:
			float distToPos = Utils.getDistTo(pos, des);

			// Set a new alarm if at destination
			if (distToPos < bounds.getWidth() / 2) {
				setState(MobState.IDLE);
			} else {
				moveTo(des);
			}
			break;
		default:
			System.err.println("Illegal State: " + state);
			state = MobState.IDLE;
		}
	}

}
