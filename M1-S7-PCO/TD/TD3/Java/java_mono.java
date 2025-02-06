package TD3;

public class java_mono {

	static int x_max, y_max = 1;
	static double max = 0;
	static int N = 40000;

	static double f(int x, int y) {
		return (x * x * x) * Math.cos((double) x) - (y * y * y) * Math.sin((double) y) - (x * x * x) / (y * y);

	}
	public static void main(String[] args) throws InterruptedException {
		double max_loc = 0;
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				max_loc = f( x, y);
				if (max_loc < 0) {
					max_loc = -max_loc;
				}
				if (max_loc > max) {
					max = max_loc;
					x_max = x;
					y_max = y;
				}
			}
		}
		System.out.println("x_max=" + x_max + " y_max=" + y_max + " max=" + max);
	}
}
