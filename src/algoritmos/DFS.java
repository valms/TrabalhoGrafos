package algoritmos;

import java.util.LinkedList;
import java.util.Queue;

import tadGrafo.Grafo;

public class DFS {

	private Queue<Integer> fila = new LinkedList<Integer>();
	private Grafo grafo;

	public DFS(Grafo grafo) {
		this.grafo = grafo;
	}

	public void busca(int origem) {
		fila.clear();
		boolean[] ordemVisita = new boolean[grafo.getQuantidadeDeVertices()];

		// Adiciono o origem como inicial
		fila.add(origem);

		while (!fila.isEmpty()) {
			int temporario = fila.poll();

			if (ordemVisita[temporario] = true) {
				continue;
			}

			for (int i = 0; i < grafo.getMatrizAdjacencia().length; i++) {
				if (condition) {
					
				}
				

			}

		}

	}

}
