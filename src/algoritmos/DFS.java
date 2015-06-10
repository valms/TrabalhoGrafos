package algoritmos;

import tadGrafo.Aresta;
import tadGrafo.Grafo;

public class DFS {

	private final int COR_BRANCA = 1;
	private final int COR_CINZA = 2;
	private final int COR_NIGGA = 3;
	private Grafo grafo;
	private int[] parenteAnterior;
	private int[] tempoDescorberta;
	private int[] tempoFinalizacao;
	private int matrizAdjacencia[][];

	public DFS(Grafo grafo) {
		this.grafo = grafo;
		this.parenteAnterior = new int[grafo.getQuantidadeDeVertices() + 1];
		this.tempoDescorberta = new int[grafo.getQuantidadeDeVertices() + 1];
		this.tempoFinalizacao = new int[grafo.getQuantidadeDeVertices() + 1];
		this.matrizAdjacencia = grafo.getMatrizAdjacencia();

	}

	/*
	 * public void busca(int origem, int fim) { boolean caminhoEncontrado =
	 * false; int destino = 0, elemento = 0;
	 * 
	 * for (int i = 0; i < grafo.getQuantidadeDeVertices(); i++) {
	 * this.parent[i] = -1; this.visitado[i] = false; }
	 * 
	 * this.filaResultado.add(origem); this.parent[origem] = -1;
	 * visitado[origem] = true;
	 * 
	 * while (!this.filaResultado.isEmpty()) { if
	 * (matrizAdjacencia[elemento][destino] > 0) {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */

	public void buscaDFS() {
		matrizAdjacencia = grafo.getMatrizAdjacencia();
		int tempo = 0;
		int cor[] = new int[grafo.getMatrizAdjacencia().length];

		for (int i = 0; i < grafo.getMatrizAdjacencia().length; i++) {
			cor[i] = COR_BRANCA;
			this.parenteAnterior[i] = -1;
		}

		for (int i = 0; i < matrizAdjacencia.length; i++) {
			if (cor[i] == COR_BRANCA) {
				tempo = this.visitaDFS(i, tempo, cor);
				System.out.println("Vertice " + (i + 1) + " -> "
						+ tempoDescorberta[i] + "/" + tempoFinalizacao[i]);
			}
		}

	}

	private int visitaDFS(int vertice, int tempo, int cor[]) {
		cor[vertice] = COR_CINZA;
		this.tempoDescorberta[vertice] = ++tempo;
		if (!this.grafo.listaAdjacenciaVazia(vertice)) {
			Aresta aresta = this.grafo.primeiroListaAdjacencia(vertice);

			while (aresta != null) {
				int ver = aresta.getVerticeDestino();
				if (cor[ver] == COR_BRANCA) {
					this.parenteAnterior[ver] = vertice;
					tempo = this.visitaDFS(ver, tempo, cor);
				}
				aresta = this.grafo.proximoAdjacente(vertice);
			}
		}

		cor[vertice] = COR_NIGGA;
		this.tempoFinalizacao[vertice] = ++tempo;

		return tempo;

	}
}
