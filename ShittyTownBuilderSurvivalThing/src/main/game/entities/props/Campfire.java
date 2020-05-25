package main.game.entities.props;

import java.util.Random;

import org.newdawn.slick.Graphics;

import main.game.entities.Prop;
import main.game.entities.psystem.Emitter;
import main.game.entities.psystem.ParticleType;
import main.game.map.Map;
import main.game.map.MapConstants;
import main.util.ResourceLoader;

public class Campfire extends Prop {

	private Emitter emitter;
	
	public Campfire(Map m, float x, float y) {
		super(m, x, y, ResourceLoader.SPRITE_SHEETS.get("campfire"), MapConstants.CAMPFIRE_DEPTH - 1);
		emitter = new Emitter(m, x, y, ParticleType.FLAME, 0.5f, 3000);
		emitter.getSpread().setX(8);
		emitter.getSpread().setY(8);
		emitter.setAcceleration(0.0001f);
		emitter.setDuration(90);
		emitter.setMin_size(3f);
		emitter.setMax_size(5f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAnimations() {
		
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		emitter.tick();
		emitter.setDirection(new Random().nextFloat() * 360);
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		emitter.render(g, 1.0f, 1.0f);
	}
	
}
