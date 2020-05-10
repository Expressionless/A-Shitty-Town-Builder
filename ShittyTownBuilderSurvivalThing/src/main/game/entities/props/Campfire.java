package main.game.entities.props;

import org.newdawn.slick.Graphics;

import main.game.Entity;
import main.game.entities.Particle;
import main.game.entities.psystem.Emitter;
import main.game.entities.psystem.ParticleType;
import main.game.map.Map;
import main.game.map.MapConstants;

public class Campfire extends Entity {

	private Emitter emitter;
	
	public Campfire(Map m, float x, float y) {
		super(m, x, y, Particle.SPRITES.get(ParticleType.FLAME), MapConstants.CAMPFIRE_DEPTH - 1);
		emitter = new Emitter(m, x, y, ParticleType.FLAME, 0.5f, 7);
		emitter.getSpread().setX(10);
		emitter.getSpread().setY(5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		emitter.tick();
	}

	@Override
	public void render(Graphics g) {
		draw(g);
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		emitter.render(g);
	}
	
}
