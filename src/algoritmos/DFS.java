package algoritmos;

import java.util.LinkedList;
import java.util.Queue;

import tadGrafo.Grafo;

public class DFS {

	private Grafo grafo;
	private int[] parent;
	private boolean[] visitado;
	private Queue<Integer> filaResultado;
	private int matrizAdjacencia[][];
	private final int COR_BRANCA = 1;
	private final int COR_CINZA = 2;
	private final int COR_NIGGA = 3;

	public DFS(Grafo grafo) {
		this.grafo = grafo;
		this.parent = new int[grafo.getQuantidadeDeVertices() + 1];
		this.visitado = new boolean[grafo.getQuantidadeDeVertices() + 1];
		this.filaResultado = new LinkedList<Integer>();
		this.matrizAdjacencia = grafo.getMatrizAdjacencia();

	}

	public void busca(int origem, int fim) {
		boolean caminhoEncontrado = false;
		int destino = 0, elemento = 0;

		for (int i = 0; i < grafo.getQuantidadeDeVertices(); i++) {
			this.parent[i] = -1;
			this.visitado[i] = false;
		}

		this.filaResultado.add(origem);
		this.parent[origem] = -1;
		visitado[origem] = true;

		while (!this.filaResultado.isEmpty()) {
			if (matrizAdjacencia[elemento][destino] > 0) {

			}

		}

	}
}
