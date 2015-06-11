package algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
	private int[][] matrizAdjacencia;

	private boolean criaListaCiclo;
	private boolean criaListaTopologica;
	private boolean criaListaComponentes;

	private ArrayList<Integer> listaCiclo;
	private ArrayList<ArrayList<Integer>> componentes;
	private Queue<Integer> listaOrdenada;

	public DFS(Grafo grafo) {
		this.grafo = grafo;
		this.parenteAnterior = new int[grafo.getQuantidadeDeVertices() + 1];
		this.tempoDescorberta = new int[grafo.getQuantidadeDeVertices() + 1];
		this.tempoFinalizacao = new int[grafo.getQuantidadeDeVertices() + 1];
		this.matrizAdjacencia = grafo.getMatrizAdjacencia();

	}

	public void buscaDFS() {
		if (criaListaComponentes) {
			this.componentes = new ArrayList<ArrayList<Integer>>();
		}

		matrizAdjacencia = grafo.getMatrizAdjacencia();
		int tempo = 0;
		int cor[] = new int[grafo.getMatrizAdjacencia().length];

		for (int i = 0; i < grafo.getMatrizAdjacencia().length; i++) {
			cor[i] = COR_BRANCA;
			this.parenteAnterior[i] = -1;
		}

		for (int i = 0; i < matrizAdjacencia.length; i++) {
			if (cor[i] == COR_BRANCA) {
				if (criaListaComponentes) {
					this.componentes.add(0, new ArrayList<Integer>());
				}
				tempo = this.visitaDFS(i, tempo, cor);
			}

		}

	}

	private int visitaDFS(int vertice, int tempo, int cor[]) {
		cor[vertice] = COR_CINZA;
		this.tempoDescorberta[vertice] = ++tempo;

		if (criaListaComponentes) {
			this.componentes.get(0).add(vertice);
		}

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
		System.out.println("Vertice " + (vertice + 1) + " -> "
				+ tempoDescorberta[vertice] + "/" + tempoFinalizacao[vertice]);
		System.out.println();
		if (this.criaListaTopologica) {
			this.listaOrdenada.add(vertice);
		}

		return tempo;

	}

	public Grafo classificarArestasArcos() {
		try {
			setCriaListaComponentes(true);
			buscaDFS();

			int quantidadeComponentesConexos = getComponentes().size();
			int quantidadeVertices = grafo.getQuantidadeDeVertices();
			Grafo grafoAuxiliar = this.grafo;
			int contadorTemp = 0;

			int[] vetorControle = new int[quantidadeVertices];

			for (int i = 0; i < quantidadeVertices; i++) {
				for (int j = 0; j < quantidadeVertices; j++) {
					vetorControle[j] = grafoAuxiliar.getElemento(i, j);
					grafo.setElement(i, j, 0);
				}

				DFS dfs = new DFS(grafoAuxiliar);
				dfs.setCriaListaComponentes(true);
				dfs.buscaDFS();

				for (int j = 0; j < quantidadeVertices; j++) {
					grafoAuxiliar.setElement(i, j, vetorControle[j]);
					vetorControle[j] = 0;
				}

				if (dfs.getComponentes().size() > quantidadeComponentesConexos) {
					for (int j = 0; j < quantidadeVertices; j++) {
						if (grafoAuxiliar.getElemento(i, j) > 0) {
							grafoAuxiliar.setElement(i, j, 99);
							grafoAuxiliar.setElement(j, i, 99);
						}
					}
				}

			}
			
			grafoAuxiliar.imprimirGrafo();
			
			System.out.println(contadorTemp);

			return grafoAuxiliar;

		} catch (NullPointerException e) {
			System.out.println(e.getCause());
		}

		return null;
	}

	public Queue<Integer> ordenacaoTopologica() {
		criaListaTopologica(true);

		buscaDFS();

		Queue<Integer> resultado = getListaOrdenada();

		criaListaTopologica(false);
		return resultado;
	}

	public ArrayList<ArrayList<Integer>> getComponentes() {
		return componentes;
	}

	public void setCriaListaComponentes(boolean criaListaComponentes) {
		this.criaListaComponentes = criaListaComponentes;
	}

	public int[] getParenteAnterior() {
		return parenteAnterior;
	}

	public int[] getTempoDescorberta() {
		return tempoDescorberta;
	}

	public int[] getTempoFinalizacao() {
		return tempoFinalizacao;
	}

	public Queue<Integer> getListaOrdenada() {
		return listaOrdenada;
	}

	public void criaListaCiclo(boolean criaLista) {
		this.criaListaCiclo = criaLista;

		if (this.criaListaCiclo) {
			this.listaCiclo = new ArrayList<Integer>();
		} else {
			this.listaCiclo = null;
		}

	}

	public void criaListaTopologica(boolean criaLista) {
		this.criaListaTopologica = criaLista;

		if (criaListaTopologica) {
			this.listaOrdenada = new LinkedList<Integer>();
		} else {
			this.listaOrdenada.clear();
			this.listaOrdenada = null;

		}
	}

	public ArrayList<Integer> getListaCiclo() {
		return listaCiclo;
	}

}
