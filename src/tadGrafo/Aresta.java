package tadGrafo;

public class Aresta {
	private int verticeOrigem;
	private int verticeDestino;
	private int peso;

	public Aresta(int verticeOrigem, int verticeDestino, int peso) {
		this.peso = peso;
		this.verticeOrigem = verticeOrigem;
		this.verticeDestino = verticeDestino;
	}

	public int getVerticeOrigem() {
		return verticeOrigem;
	}

	public void setVerticeOrigem(int verticeOrigem) {
		this.verticeOrigem = verticeOrigem;
	}

	public int getVerticeDestino() {
		return verticeDestino;
	}

	public void setVerticeDestino(int verticeDestino) {
		this.verticeDestino = verticeDestino;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

}
