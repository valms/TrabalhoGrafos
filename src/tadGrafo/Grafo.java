package tadGrafo;

public class Grafo {
	private int matrizAdjacencia[][];
	private int quantidadeDeVertices;

	public Grafo(int quantidadeVertice) {
		this.matrizAdjacencia = new int[quantidadeVertice][quantidadeVertice];
		
	}

	public Grafo() {

	}

	public void setMatrizAdjacencia(int[][] matrizAdjacencia) {
		this.matrizAdjacencia = matrizAdjacencia;
		setQuantidadeDeVertices(matrizAdjacencia.length);
	}

	public int[][] getMatrizAdjacencia() {
		return matrizAdjacencia;
	}

	public void adicionarAresta(int origem, int destino, int vertice) {

		try {
			matrizAdjacencia[origem][destino] = vertice;

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}

	}

	public int retornarVertice(int origem, int destino) {

		try {
			return matrizAdjacencia[origem][destino];
		} catch (Exception e) {
			e.getMessage();
		}
		return -1;

	}

	public void imprimirGrafo() {
		System.out.print("   ");
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			System.out.print((i + 1) + " ");
		}
		System.out.println();

		for (int i = 0; i < matrizAdjacencia.length; i++) {
			System.out.print((i + 1) + "| ");
			for (int j = 0; j < matrizAdjacencia.length; j++) {
				System.out.print(retornarVertice(i, j) + " ");
			}
			System.out.println();
		}

	}

	public int getQuantidadeDeVertices() {
		return quantidadeDeVertices;
	}

	public void setQuantidadeDeVertices(int quantidadeDeVertices) {
		this.quantidadeDeVertices = quantidadeDeVertices;
	}

}
