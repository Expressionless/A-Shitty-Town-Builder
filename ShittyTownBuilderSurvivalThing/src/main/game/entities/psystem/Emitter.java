package main.game.entities.psystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.GameConstants;
import main.game.entities.Particle;
import main.game.entities.psystem.particles.Flame;
import main.game.map.Map;
import main.util.Utils;

public class Emitter {

	// No. of particles / second
	// private int density;

	// type of particle and info about particle
	private ParticleType particle_type;
	private int duration;
	private float min_size;
	private float max_size;

	// px per frame
	private float acceleration;
	private float direction;

	// Seconds per particle
	private float interval;
	private float timer = -1;

	private Point pos;
	private Map map;
	private int depth;

	protected Point spread;
	
	private final ArrayList<Particle> particles = new ArrayList<Particle>();

	public Emitter(Map m, float x, float y, ParticleType particle, float duration, int density, float max_speed,
			float direction, float min_size, float max_size) {
		init(m, x, y, particle, duration, density, max_speed, direction, min_size, max_size);
	}

	public Emitter(Map m, float x, float y, ParticleType particle) {
		init(m, x, y, particle);
	}

	public Emitter(Map m, float x, float y, ParticleType particle, float duration, int density) {
		init(m, x, y, particle, duration, density);
	}

	public void init(Map m, float x, float y, ParticleType particle, float duration, int density) {
		init(m, x, y, particle, duration, density, Constants.DEFAULT_ACCELERATION, Constants.DIR_UP, Constants.DEFAULT_MIN_SIZE,
				Constants.DEFAULT_MAX_SIZE);
	}

	public void init(Map m, float x, float y, ParticleType particle) {
		init(m, x, y, particle, GameConstants.FPS * 3f, 1);
	}

	// Initialize the object
	public void init(Map m, float x, float y, ParticleType particle, float duration, int density, float acceleration,
			float direction, float min_size, float max_size) {
		this.particle_type = particle;
		this.duration = (int) (duration * GameConstants.FPS);
		this.acceleration = acceleration;
		this.direction = direction;
		this.min_size = min_size;
		this.max_size = max_size;

		spread = new Point(0, 0);		
		
		interval = GameConstants.FPS / density;

		this.map = m;
		this.pos = new Point(x, y);
	}

	public void tick() {
		// Check if should spawn a particle
		if (timer == -1) {
			particles.add(createParticle());
			timer = interval;
		} else if (timer > -1)
			timer -= 1;

		//Collections.sort(particles, (Comparator<Particle>) (Particle p1, Particle p2) -> (int) (p1.getDepth() - p2.getDepth()));
		
		for (Particle particle : particles)
			if (particle != null)
				particle.tick();

	}

	public void render(Graphics g, float climate, float lighting) {
		for (Particle particle : particles) {
			if (particle != null)
				particle.render(g, climate, lighting);
		}
		postDraw();
	}

	public void postDraw() {
		particles.removeIf(new FilterDestroyed());
	}
	
	public Particle createParticle() {
		switch (particle_type) {
		case FLAME:
			float p_size = Utils.getRandInRange(min_size, max_size);
			Random r = new Random();
			float spread_x = r.nextFloat() * spread.getX() - spread.getX() / 2;
			float spread_y = r.nextFloat() * spread.getY() - spread.getY() / 2;
			float x = pos.getX() + spread_x;
			float y = pos.getY() + spread_y;
			Particle p = new Flame(this, x, y, direction, acceleration, duration, p_size);
			return p;
		default:
			return null;
		}
	}

	// Getters and Setters
	public Map getMap() {
		return map;
	}

	public Point getPos() {
		return pos;
	}

	public int getDepth() {
		return depth;
	}

	public ArrayList<Particle> getParticles() {
		return particles;
	}

	public ParticleType getParticle_type() {
		return particle_type;
	}

	public void setParticle_type(ParticleType particle_type) {
		this.particle_type = particle_type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getMin_size() {
		return min_size;
	}

	public void setMin_size(float min_size) {
		this.min_size = min_size;
	}

	public float getMax_size() {
		return max_size;
	}

	public void setMax_size(float max_size) {
		this.max_size = max_size;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public float getDirection() {
		return direction;
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}

	public float getInterval() {
		return interval;
	}

	public void setInterval(float interval) {
		this.interval = interval;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public Point getSpread() {
		return spread;
	}
}

class FilterDestroyed implements Predicate<Particle> {

	@Override
	public boolean test(Particle p) {
		return p == null;
	}
	
}
