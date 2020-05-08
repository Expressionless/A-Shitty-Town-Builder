package main.game.entities.mobs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

import main.Engine;
import main.GameConstants;
import main.game.entities.Mob;
import main.game.map.Map;
import main.util.ResourceLoader;

public class Player extends Mob {

	public Player(Map m, float x, float y) {
		super(m, x, y, ResourceLoader.SPRITES.get("player"), GameConstants.HUMAN_DEPTH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		Point move_vec = pollInput(Engine.getInput());
		move(move_vec);
	}
}
