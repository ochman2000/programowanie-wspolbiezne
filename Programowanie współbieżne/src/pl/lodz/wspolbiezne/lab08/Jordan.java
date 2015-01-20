package pl.lodz.wspolbiezne.lab08;

public class Jordan implements Runnable {
	private int beginIndex;
	private int endIndex;
	private int currentRow;
	private int columns;
	private double[][] matrix;

	public Jordan(int beginIndex, int endIndex, double[][] matrix,
			int currentRow, int columns) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		this.matrix = matrix;
		this.currentRow = currentRow;
		this.columns = columns;
	}

	@Override
	public void run() {
		for (int r = beginIndex; r < endIndex; r++) {
			if (r == currentRow) {
				continue;
			}
			double w = matrix[r][currentRow];
			for (int c = 0; c < columns + 1; c++) {
				matrix[r][c] -= matrix[currentRow][c] * w;
			}
		}
	}
}