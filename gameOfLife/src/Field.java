import java.io.*;

public class Field {
	private int sizeH = 50, sizeW = 50;
	private boolean[][] f = new boolean[sizeH][sizeW];

	public Field() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("initial_field"));
			System.out.println("Congratulations!");
			String line = null;
			int lineNum = 0;
			while ((line = reader.readLine()) != null) {
				lineNum++;
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) != ' ') {
						f[lineNum][i] = true;
					}
				}
				System.out.println("Congratulations!");
			}
			reader.close();
		} catch (IOException i) {
			System.out.println("Sorry!");
		}
	}

	int getSizeH() {
		return sizeH;
	}

	int getSizeW() {
		return sizeW;
	}

	void setSizeH(int newSizeH) {
		sizeH = newSizeH;
	}

	void setSizeW(int newSizeW) {
		sizeH = newSizeW;
	}

	boolean getPoint(int h, int w) {
		return f[h][w];
	}

	void setPoint(int h, int w) {
		f[h][w] = true;
	}

	void unsetPoint(int h, int w) {
		f[h][w] = false;
	}

	void xorPoint(int h, int w) {
		f[h][w] = f[h][w] ^ true;
	}

	public void step() {
		boolean[][] newF = new boolean[sizeH][sizeW];
		for (int i = 0; i < sizeH; i++) {
			for (int j = 0; j < sizeW; j++) {
				int neighbours = 0;
				for (int di = -1; di <= 1; di++) {
					for (int dj = -1; dj <= 1; dj++) {
						neighbours += f[(i + di + sizeH) % sizeH][(j + dj + sizeW)
								% sizeW] ? 1 : 0;
					}
				}
				neighbours -= f[i][j] ? 1 : 0;
				if (neighbours == 3 || (neighbours == 2 && f[i][j])) {
					newF[i][j] = true;
				}
			}
		}
		// for (int i=0; i<sizeW; i++) new_f[sizeH-1][i]=false;
		f = newF;
	}
}