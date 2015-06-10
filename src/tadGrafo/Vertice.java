package tadGrafo;

public class Vertice {
	private int cor;
	private int adjacentes[];
	private int idVertice;

	public int getIdVertice() {
		return idVertice;
	}

	public void setIdVertice(int idVertice) {
		this.idVertice = idVertice;
	}

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
