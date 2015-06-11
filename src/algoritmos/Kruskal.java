package algoritmos;

import tadGrafo.Grafo;

public class Kruskal {
	private Grafo grafo;
	public Kruskal(Grafo grafo) {
		this.grafo = grafo;
	}
	
	
	public Grafo temCiclo(){
		DFS dfs = new DFS(grafo);
		
		dfs.criaListaCiclo(true);
		dfs.buscaDFS();
		
		if (dfs.getListaCiclo() != null) {
			
		}
		
		
		
		
		
		return null;
	}

}
