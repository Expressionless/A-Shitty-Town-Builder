package main.game.entities.mobs.hostile.bandits;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import main.game.Anim;
import main.game.entities.mobs.hostile.Bandit;
import main.game.map.Map;
import main.util.Utils;

public class BanditArcher extends Bandit {

	public BanditArcher(Map m, float x, float y) {
		super(m, x, y, Utils.pickFromSpriteSheet(Bandit.SPRITES.get("archer")));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAnimations() {
		addAnimation(Anim.WALK, new Animation(SPRITES.get("legs"), 83));
		addAnimation(Anim.ATTACK, new Animation(SPRITES.get("draw_cycle"), 1000/3));
		addAnimation(Anim.IDLE, new Animation(SPRITES.get("idle_hands"), 1000/3));
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		switch(state) {
		case MOVING:
			g.drawAnimation(animations.get(Anim.WALK), pos.getX(), pos.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void handleStates() {
		// TODO Auto-generated method stub
		
	}

}
