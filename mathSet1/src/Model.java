public class Model {
	public static final int defaultWidth = 300, defaultHeight = 300;
	public int width = defaultWidth, height = defaultHeight;
	public double x1 = -2, x2 = 2, y1 = -2, y2 = 2;
	public double a = 0.4, b = 0.4;
	public boolean[][] f = new boolean[height][width];

	public int type = 1;

	public boolean isInRegion(double x, double y) {
		// if (x >= x1 && x <= x2 && y >= y1 && y <= y2) return true;
		if (x * x + y * y <= 4)
			return true;
		else
			return false;
	}

	public double jToX(int j) {
		return (double) j * ((double) (x2 - x1) / width) + x1;
	}

	public double iToY(int i) {
		return (double) i * ((double) (y2 - y1) / height) + y1;
	}

	public int XtoJ(double x) {
		return (int) ((x - x1) * width / (x2 - x1));
	}

	public int YtoI(double y) {
		return (int) ((y - y1) * height / (y2 - y1));
	}

	public double getNextX(double x, double y) {
		if (type == 1)
			return x * x - y * y + a;
		else if (type == 0)
			return 1 + y - a * x * x;
		else if (type == 2)
			return y;
		return 0;
	}

	public double getNextY(double x, double y) {
		if (type == 1)
			return 2 * x * y + b;
		else if (type == 0)
			return b * x;
		else if (type == 2)
			return 2.27 * y * (1 - x);
		return 0;
	}

	public int getNextI(int i, int j) {
		return YtoI(getNextY(jToX(j), iToY(i)));
	}

	public int getNextJ(int i, int j) {
		return XtoJ(getNextX(jToX(j), iToY(i)));
	}

	public Model() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (isInRegion(jToX(j), iToY(i)))
					f[i][j] = true;
			}
		}
	}

	public Model(String stringType) {
		this();
		if (stringType == "complexNumbers") {
			a = 0.4;
			b = 0.4;
			type = 1;
		} else if (stringType == "henon") {
			a = 2;
			b = 0.3;
			type = 0;
		} else if (stringType == "someMap") {
			type = 2;
		}
	}
	
	public Model(String stringType, double newA, double newB, int newWidth, int newHeight) {
		a = newA;
		b = newB;
		width = newWidth;
		height = newHeight;
		f = new boolean[height][width];
		if (stringType == "complexNumbers") {
			type = 1;
		} else if (stringType == "henon") {
			type = 0;
		} else if (stringType == "someMap") {
			type = 2;
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (isInRegion(jToX(j), iToY(i)))
					f[i][j] = true;
			}
		}
		System.out.println("Successful!" + a + " " + b);
	}

	public void nextStep() {
		boolean[][] new_f = new boolean[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (getNextI(i, j) >= 0 && getNextI(i, j) < height
						&& getNextJ(i, j) >= 0 && getNextJ(i, j) < width
						&& f[getNextI(i, j)][getNextJ(i, j)] && f[i][j])
					new_f[i][j] = true;
			}
		}
		f = new_f;
	}
}
