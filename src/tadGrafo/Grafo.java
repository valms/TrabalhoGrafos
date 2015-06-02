package tadGrafo;

public class Grafo {
	private int matrizAdjacencia[][];

	public Grafo(int quantidadeVertice) {
		this.matrizAdjacencia = new int[quantidadeVertice][quantidadeVertice];
	}

	public void setMatrizAdjacencia(int[][] matrizAdjacencia) {
		this.matrizAdjacencia = matrizAdjacencia;
	}

	public int[][] getMatrizAdjacencia() {
		return matrizAdjacencia;
	}
	
	
	

}
