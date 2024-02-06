package games.enchanted.fallingBlockParticles.util;

public class NumberUtil {
  public static double lerp(double a, double b, double f) {
    return (a * (1.0 - f) + (b * f));
  }
  public static float lerp(float a, float b, float f) {
    return (float) (a * (1.0 - f) + (b * f));
  }
}
