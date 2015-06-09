package tadGrafo;

public class Grafo {
	private int matrizAdjacencia[][];
	private int quantidadeDeVertices;
	private int posicaoAdjacente[];
	private boolean grafoDirigido;

	public Grafo(int quantidadeVertice) {
		this.matrizAdjacencia = new int[quantidadeVertice][quantidadeVertice];
		this.posicaoAdjacente = new int[quantidadeVertice];
		this.quantidadeDeVertices = quantidadeVertice;

		for (int i = 0; i < this.quantidadeDeVertices; i++) {
			this.posicaoAdjacente[i] = -1;
			for (int j = 0; j < this.quantidadeDeVertices; j++) {
				if (i == j) {
					this.matrizAdjacencia[i][j] = 0;
				} else {
					this.matrizAdjacencia[i][j] = Integer.MAX_VALUE;
				}
			}

		}
	}

	public Grafo() {

	}

	public int[] getPosicaoAdjacente() {
		return posicaoAdjacente;
	}

	public boolean isGrafoDirigido() {
		return grafoDirigido;
	}

	public void setPosicaoAdjacente(int[] posicaoAdjacente) {
		this.posicaoAdjacente = posicaoAdjacente;
	}

	public void setGrafoDirigido(boolean grafoDirigido) {
		this.grafoDirigido = grafoDirigido;
	}

	public void setMatrizAdjacencia(int[][] matrizAdjacencia) {
		this.matrizAdjacencia = matrizAdjacencia;
	}

	public int[][] getMatrizAdjacencia() {
		return matrizAdjacencia;
	}

	public int getQuantidadeDeVertices() {
		return quantidadeDeVertices;
	}

	public void setQuantidadeDeVertices(int quantidadeDeVertices) {
		this.quantidadeDeVertices = quantidadeDeVertices;
		this.posicaoAdjacente = new int[this.quantidadeDeVertices];
	}

	public void adicionarAresta(int verticeOrigem, int verticeDestino, int peso) {

		try {
			matrizAdjacencia[verticeOrigem][verticeDestino] = peso;

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}

	}

	public void adicionarAresta(int verticeOrigem, int verticeDestino)
			throws Exception {
		if (grafoDirigido) {
			throw new Exception();
		}
		matrizAdjacencia[verticeOrigem][verticeDestino] += 1;

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
			System.out.print((i + 1) + "(" + i + ")| ");
			for (int j = 0; j < matrizAdjacencia.length; j++) {
				System.out.print(retornarVertice(i, j) + " ");
			}
			System.out.println();
		}

		int aux[] = verificaConjutoVizinhanca((8 - 1));
		for (int i = 0; i < verificaConjutoVizinhanca(0).length; i++) {
			System.out.print(" " + aux[i]);
		}
		System.out.println();
		
		removeAresta((8 - 1), (4 - 1)); // Arrumar
		

	}

	public boolean verificaAresta(int verticeOrigem, int verticeDestino) {
		if (verticeOrigem == verticeDestino) {
			return (this.matrizAdjacencia[verticeOrigem][verticeDestino] != 0);
		} else {
			return (this.matrizAdjacencia[verticeOrigem][verticeDestino] != 0);
		}
	}

	public boolean verificaArco(int verticeOrigem, int verticeDestino) {
		if (verticeOrigem == verticeDestino) {
			return (this.matrizAdjacencia[verticeOrigem][verticeDestino] != 0);
		} else {
			return (this.matrizAdjacencia[verticeOrigem][verticeDestino] != Integer.MAX_VALUE);
		}
	}

	/**
	 * 
	 * @param vertice
	 * @return um vetor com o conjuto vizinhança do vértice
	 */
	public int[] verificaConjutoVizinhanca(int vertice) {
		int[] conjuntoVizinhanca = null;
		if (!this.listaAdjacenciaVazia(vertice)) {
			conjuntoVizinhanca = new int[this.quantidadeDeVertices];
			int indice = 0;
			Aresta auxiliar = this.primeiroListaAdjacencia(vertice);
			while (auxiliar != null) {
				conjuntoVizinhanca[indice++] = auxiliar.getVerticeDestino() + 1;
				auxiliar = this.proximoAdjacente(vertice);
			}
		}
		return conjuntoVizinhanca;
	}

	public boolean listaAdjacenciaVazia(int vertice) {

		for (int i = 0; i < this.quantidadeDeVertices; i++) {
			if (verificaAresta(vertice, i)) {
				return false;
			}
		}
		return true;
	}

	public Aresta primeiroListaAdjacencia(int vertice) {
		this.posicaoAdjacente[vertice] = -1;
		return this.proximoAdjacente(vertice);

	}

	public Aresta proximoAdjacente(int vertice) {
		this.posicaoAdjacente[vertice]++;
		while ((this.posicaoAdjacente[vertice] < this.quantidadeDeVertices)
				&& (!verificaAresta(vertice, this.posicaoAdjacente[vertice]))) {
			this.posicaoAdjacente[vertice]++;
		}
		if (this.posicaoAdjacente[vertice] == this.quantidadeDeVertices) {
			return null;
		} else {
			return new Aresta(
					vertice,
					this.posicaoAdjacente[vertice],
					this.matrizAdjacencia[vertice][this.posicaoAdjacente[vertice]]);
		}

	}

	/*
	 * TODO
	 */
	public Aresta removeAresta(int verticeOrigem, int verticeDestino) {
		if (verificaAresta(verticeOrigem, verticeDestino)) {
			return null;
		} else {
			Aresta aresta = new Aresta(verticeDestino, verticeDestino,
					this.matrizAdjacencia[verticeOrigem][verticeDestino]);
			if (verticeOrigem == verticeDestino) {
				this.matrizAdjacencia[verticeOrigem][verticeDestino] = 0;
			} else {
				this.matrizAdjacencia[verticeOrigem][verticeDestino] = 0;
			}
			return aresta;

		}
	}

	public boolean estaVazio() {
		if (this.quantidadeDeVertices == 0) {
			return true;
		}
		return false;
	}

}
