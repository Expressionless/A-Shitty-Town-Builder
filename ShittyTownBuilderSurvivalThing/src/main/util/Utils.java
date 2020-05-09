package main.util;

import java.util.Random;

import org.newdawn.slick.geom.Point;

public class Utils {

	public static float lerp(float current_value, float final_value, float lerp_factor) {

		float result = ((current_value * (1.0f - lerp_factor)) + (final_value * lerp_factor));

		if (Math.abs(current_value - final_value) < 0.001f)
			return final_value;
		else
			return result;

	}

	public static float getDistTo(Point from, Point to) {
		float dX = (float) Math.pow(from.getX() - to.getX(), 2);
		float dY = (float) Math.pow(from.getY() - to.getY(), 2);

		return (float) Math.pow(dX + dY, 0.5);
	}

	public static float getPointDirection(Point from, Point to) {
		float deltaX = to.getX() - from.getX();
		float deltaY = to.getY() - from.getY();
		float disTo = getDistTo(from, to);
		float angle = (float) Math.toDegrees(Math.acos(deltaX / disTo));
		if (deltaY > 0) {
			return angle;
		} else
			return 360 - angle;
	}
	
	/**
	 * 
	 * @param min
	 * @param max
	 * @return A random int between the min and max values
	 */
	
	public static int getRandInRange(int min, int max) {
		int variation = max - min;
		if(variation > 0)
			return min + new Random().nextInt(variation);
		else return min;
	}
}
