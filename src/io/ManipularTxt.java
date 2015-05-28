package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ManipularTxt {

	public ManipularTxt() {
	}

	public void ler(File caminhoArquivo) throws IOException {
		int x = 0, y = 0;

		FileReader fr = new FileReader(caminhoArquivo);
		BufferedReader br = new BufferedReader(fr);
		String line;
		int tamanhoMatriz = Integer.parseInt(br.readLine());
		// System.out.println(tamanhoMatriz);
		int[][] matriz = new int[tamanhoMatriz][tamanhoMatriz];

		while ((line = br.readLine()) != null) {
			String[] values = line.split(" ");
			for (String string : values) {
				int stringInt = Integer.parseInt(string);
				matriz[x][y] = stringInt;
				y = y + 1;
				// matrizAdjacencia[x][y] = stringInt;
			}
			x = x + 1;
			y = 0;
		}
		br.close();
		fr.close();

		/*
		 * for (int i = 0; i < matriz.length; i++) { for (int j = 0; j <
		 * matriz.length; j++) { System.out.print(" " + matriz[i][j]); }
		 * System.out.println(); }
		 */
	}

}
