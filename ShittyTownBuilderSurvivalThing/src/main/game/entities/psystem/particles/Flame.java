package main.game.entities.psystem.particles;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import main.game.entities.Particle;
import main.game.entities.psystem.Emitter;
import main.game.entities.psystem.ParticleType;

public class Flame extends Particle {
	
	public static final SpriteSheet sprite_set = SPRITES.get(ParticleType.FLAME);
	
	private int initLife;
	private float initSize;
	
	public Flame(Emitter em, float x, float y, float direction, float acceleration, int life, float size) {
		super(em, sprite_set.getSubImage(new Random().nextInt(sprite_set.getHorizontalCount()), 0), x, y, direction, acceleration, em.getDepth(), life, size);
		
		this.initLife = life;
	}

	@Override
	public void updateParticle() {
		// TODO Auto-generated method stub
		size = (timer / initLife) * initSize;
		
	}

	@Override
	public void drawParticle(Graphics g) {
		float alpha = (float)timer / (float)initLife;

		g.drawImage(sprite, pos.getX(), pos.getY(), new Color(1.0f, 1.0f, 1.0f, alpha));
		drawImageScaled(g, sprite, pos.getX(), pos.getY(), size, new Color(1.0f, 1.0f, 1.0f, alpha));
	}
}
