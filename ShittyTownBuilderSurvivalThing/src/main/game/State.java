package main.game;

import org.newdawn.slick.Graphics;

public abstract class State {

	protected boolean loaded = false;
	
	public State() {
		
	}
	
	public abstract void init();
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public boolean isLoaded() {
		return loaded;
	}
}
