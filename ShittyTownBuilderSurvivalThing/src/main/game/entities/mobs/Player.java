package main.game.entities.mobs;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

import main.Engine;
import main.GameConstants;
import main.game.entities.Mob;
import main.game.map.Map;
import main.util.ResourceLoader;
import main.util.Utils;

public class Player extends Mob {

	public Player(Map m, float x, float y) {
		super(m, x, y, ResourceLoader.SPRITES.get("player"), GameConstants.HUMAN_DEPTH);
	}
	
	@Override
	public void step() {
		
		float mouse_x = Mouse.getX();
		float mouse_y = GameConstants.HEIGHT - Mouse.getY();
		
		Point game_mouse = map.getView().viewToGame(new Point(mouse_x, mouse_y));
		direction = Utils.getPointDirection(getCentrePoint(), game_mouse);
		sprite.setRotation(direction);
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
	}
}
