package main.game.entities.mobs;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

import main.Engine;
import main.GameConstants;
import main.game.entities.Mob;
import main.game.map.Map;
import main.game.map.MapConstants;
import main.util.ResourceLoader;
import main.util.Utils;

enum State {
	IDLE,
	MOVING,
	ATTACKING
}

public class Player extends Mob {

	private Animation legs;
	
	public Player(Map m, float x, float y) {
		super(m, x, y, ResourceLoader.SPRITE_SHEETS.get("player"), MapConstants.HUMAN_DEPTH);
		this.current_animation = new Animation(sprite, (int)((1000 / GameConstants.FPS)));
		legs = new Animation(ResourceLoader.SPRITE_SHEETS.get("player_legs"), 83);
	}
	
	public void initAnimations() {
		
	}
	
	@Override
	public void step() {
		
		float mouse_x = Mouse.getX();
		float mouse_y = GameConstants.HEIGHT - Mouse.getY();
		
		Point game_mouse = map.getView().viewToGame(new Point(mouse_x, mouse_y));
		look_direction = Utils.getPointDirection(this.getCentrePoint(), game_mouse);
	}
	
	@Override
	public void prerender(Graphics g) {
		if(speed != 0) {
			g.drawAnimation(legs, bounds.getX(), bounds.getY(), new Color(255, 255, 255, 0));
			Image cur_image = legs.getCurrentFrame().copy();
			cur_image.setRotation(direction);
			g.drawImage(cur_image, bounds.getX(), bounds.getY());
		}
	}

	@Override
	public void draw(Graphics g) {
	}

	// Input Code
	public Point pollInput(Input input) {
		int dir = 0;
		float xDir = 0, yDir = 0;
		float mspeed = 3;

		if (input.isKeyDown(GameConstants.PLAYER_RIGHT))
			dir |= 1;
		if (input.isKeyDown(GameConstants.PLAYER_LEFT))
			dir |= 2;
		if (input.isKeyDown(GameConstants.PLAYER_UP))
			dir |= 4;
		if (input.isKeyDown(GameConstants.PLAYER_DOWN))
			dir |= 8;

		if ((dir & 1) != 0)
			xDir = mspeed;
		else if ((dir & 2) != 0)
			xDir = -mspeed;
		if ((dir & 4) != 0)
			yDir = -mspeed;
		else if ((dir & 8) != 0)
			yDir = mspeed;

		return new Point(xDir, yDir);
	}

	@Override
	public void handleStates() {
		Point move_vec = pollInput(Engine.getInput());
		move(move_vec);
		speed = Utils.getDistTo(new Point(0,0), move_vec);
		direction = Utils.getPointDirection(new Point(0, 0), move_vec);
	}
}
