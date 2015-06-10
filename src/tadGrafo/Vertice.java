package tadGrafo;

public class Vertice {
	private int cor;
	private int adjacentes[];

	public int getCor() {
		return cor;
	}

	public int[] getAdjacentes() {
		return adjacentes;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public void setAdjacentes(int[] adjacentes) {
		this.adjacentes = adjacentes;
	}

}
