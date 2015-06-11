package interfaceGrafica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tadGrafo.Grafo;

public class Parser {

	private Grafo grafo;

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	public void grafoParaXML(Grafo grafo, String caminho) throws IOException {
		StringBuffer xml = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"ISO8859-1\"?>\n<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\">\n"
						+ "\t<graph edgedefault=\""
						+ ((grafo.isGrafoDirigido()) ? "directed"
								: "undirected")
						+ "\">\n"
						+ "\t<key id=\"name\" for=\"node\" attr.name=\"name\" attr.type=\"string\"/>\n"
						+ "\t<key id=\"type\" for=\"node\" attr.name=\"type\" attr.type=\"string\"/>\n"
						+ "\t<key id=\"waycolor\" for=\"edge\" attr.name=\"waycolor\" attr.type=\"string\"/>\n");

		int[][] matriz = grafo.getMatrizAdjacencia();

		for (int i = 0; i < matriz.length; i++) {
			xml.append("\n\t\t<node id=\"" + i + "\">"
					+ "\n\t\t\t<data key=\"name\">" + (i + 1) + "</data>"
					+ "\n\t\t\t<data key=\"type\">"
					+ (((i % 2) == 0) ? "normal" : "outro") + "</data>"
					+ "\n\t\t</node>");
		}

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if ((matriz[i][j] == 1) || (matriz[i][j] == 99)) {
					xml.append("\n\n\t\t<edge source=\"" + i + "\" target=\""
							+ j + "\"> "
							+ "\n\t\t\t<data key=\"waycolor\">color"
							+ ((matriz[i][j] == 1) ? 1 : 99)
							+ "</data>\n\t\t</edge>");
				}
			}
		}
		xml.append("\n\n\t</graph>\n</graphml>");
		BufferedWriter out = new BufferedWriter(new FileWriter(caminho));
		out.write(xml.toString());
		out.flush();
		out.close();

	}

}
