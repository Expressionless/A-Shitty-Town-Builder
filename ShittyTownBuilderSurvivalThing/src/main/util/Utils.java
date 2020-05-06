package main.util;

public class Utils {
	
	public static float lerp(float current_value, float final_value, float lerp_factor) {

		float result = ((current_value * (1.0f - lerp_factor)) + (final_value * lerp_factor));

		if (Math.abs(current_value - final_value) < 0.001f)
			return final_value;
		else
			return result;

	}
}
