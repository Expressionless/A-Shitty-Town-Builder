package main.game.states;

import org.newdawn.slick.Graphics;

public abstract class State {

	public State() {
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
}
