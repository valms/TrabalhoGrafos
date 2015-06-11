package interfaceGrafica;

import io.LerTxt;

import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tadGrafo.Grafo;
import algoritmos.DFS;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class ExecucaoGrafo {

	protected Shell janelaExibicaoGrafo;
	private Grafo grafo;
	private LerTxt lerTxt = new LerTxt();
	private static final String[] FILTRO_NOMES = { "Arquivo de texto (*.txt)" };
	private static final String[] FILTRO_EXTENSOES = { "*.txt" };
	private String caminhoDoArquivo;
	private Text FILENAME;
	private mxGraph mxGraph;
	private mxGraphComponent mxGraphComponent;
	private int matriz[][];

	/**
	 * Método para abrir a Janela
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		Image image = new Image(display, "resources\\logoUnifor.gif");
		janelaExibicaoGrafo.setImage(image);
		janelaExibicaoGrafo.open();
		janelaExibicaoGrafo.layout();
		while (!janelaExibicaoGrafo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Método que cria e manipula os componentes da janela.Create contents of
	 * the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		janelaExibicaoGrafo = new Shell();
		janelaExibicaoGrafo.setSize(893, 780);
		janelaExibicaoGrafo.setText("Grafos 2015.2");
		mxGraph = new mxGraph();
		mxGraphComponent = new mxGraphComponent(mxGraph);
		mxGraphComponent.setPreferredSize(new Dimension(400, 400));

		FILENAME = new Text(janelaExibicaoGrafo, SWT.BORDER);
		Menu menu = new Menu(janelaExibicaoGrafo, SWT.BAR);
		janelaExibicaoGrafo.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("Arquivo");

		Menu menuArquivo = new Menu(mntmFile);
		mntmFile.setMenu(menuArquivo);

		MenuItem mntmNovo = new MenuItem(menuArquivo, SWT.CASCADE);
		mntmNovo.setText("Novo");

		Menu subMenuArquivoNovo = new Menu(mntmNovo);
		mntmNovo.setMenu(subMenuArquivoNovo);

		MenuItem mntmGrafo = new MenuItem(subMenuArquivoNovo, SWT.NONE);
		mntmGrafo.setText("Grafo");
		mntmGrafo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				abrirJanelaSelecaoArquivo(e);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		MenuItem mntmImprimirMatriz = new MenuItem(menuArquivo, SWT.CASCADE);
		mntmImprimirMatriz.setText("Grafo");

		Menu subMenuGrafo = new Menu(mntmImprimirMatriz);
		mntmImprimirMatriz.setMenu(subMenuGrafo);

		MenuItem mntmExibirMatrizAdjacencia = new MenuItem(subMenuGrafo,
				SWT.NONE);
		mntmExibirMatrizAdjacencia.setText("Exibir Matriz Adjac\u00EAncia");
		mntmExibirMatrizAdjacencia
				.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						imprimirMatrizAdjacencia(e);

					}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {

					}
				});

		MenuItem mntmPlotarGrafo = new MenuItem(subMenuGrafo, SWT.NONE);
		mntmPlotarGrafo.setText("Plotar Grafo");
		mntmPlotarGrafo.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event e) {
				plotarGrafo(e);

			}
		});

		new MenuItem(menuArquivo, SWT.SEPARATOR);

		MenuItem mntmSair = new MenuItem(menuArquivo, SWT.NONE);
		mntmSair.setText("Sair");
		mntmSair.addListener(SWT.Selection, event -> {
			janelaExibicaoGrafo.getDisplay().dispose();
			System.exit(0);
		});

		MenuItem mntmAlgoritmos = new MenuItem(menu, SWT.CASCADE);
		mntmAlgoritmos.setText("Algoritmos");

		Menu menuAlgoritmo = new Menu(mntmAlgoritmos);
		mntmAlgoritmos.setMenu(menuAlgoritmo);

		MenuItem mntmBfs = new MenuItem(menuAlgoritmo, SWT.NONE);
		mntmBfs.setText("BFS");
		mntmBfs.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event e) {
				executarDFS(e);

			}
		});

		MenuItem mntmOrdenaoTopolgica = new MenuItem(menuAlgoritmo, SWT.NONE);
		mntmOrdenaoTopolgica.setText("Ordena\u00E7\u00E3o Topol\u00F3gica");
		mntmOrdenaoTopolgica.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event e) {
				ordenacaoTopologica(e);
				
			}
		});

		MenuItem mntmKruskal = new MenuItem(menuAlgoritmo, SWT.NONE);
		mntmKruskal.setText("Kruskal");
		mntmKruskal.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event e) {
				executarKruskal(e);

			}
		});

		MenuItem mntmSobre = new MenuItem(menu, SWT.NONE);
		mntmSobre.setText("Sobre");

		Composite composite = new Composite(janelaExibicaoGrafo, SWT.EMBEDDED
				| SWT.NO_BACKGROUND);
		composite.setBounds(10, 10, 857, 701);
		Frame frame = SWT_AWT.new_Frame(composite);
		frame.add(mxGraphComponent);

	}

	private void abrirJanelaSelecaoArquivo(SelectionEvent e) {

		try {
			FileDialog fileDialog = new FileDialog(janelaExibicaoGrafo,
					SWT.OPEN);
			fileDialog.setFilterNames(FILTRO_NOMES);
			fileDialog.setFilterExtensions(FILTRO_EXTENSOES);

			String nomeArquivo = fileDialog.open();
			if (nomeArquivo != null) {
				StringBuffer stringBuffer = new StringBuffer();
				String[] arquivos = fileDialog.getFileNames();

				for (int i = 0, n = arquivos.length; i < n; i++) {
					stringBuffer.append(fileDialog.getFilterPath());

					if (stringBuffer.charAt(stringBuffer.length() - 1) != File.separatorChar) {
						stringBuffer.append(File.separatorChar);
						caminhoDoArquivo = nomeArquivo;
					}
					stringBuffer.append(arquivos[i]);
					stringBuffer.append(" ");
				}
				FILENAME.setText(stringBuffer.toString());
			}

			File arquivoTxt = new File(caminhoDoArquivo);
			matriz = lerTxt.matrizAdjacencia(arquivoTxt);
			grafo = new Grafo();

			grafo.setQuantidadeDeVertices(matriz.length);
			grafo.setMatrizAdjacencia(matriz);
			grafo.setGrafoDirigido(lerTxt.isGrafoDirigido());

			MessageBox messageBox = new MessageBox(janelaExibicaoGrafo,
					SWT.ICON_INFORMATION | SWT.OK);
			messageBox.setText("Informação");
			messageBox.setMessage("Arquivo " + caminhoDoArquivo
					+ " carregado com êxito");
			messageBox.open();

			mxGraph.getModel().beginUpdate();
			Object parent = mxGraph.getDefaultParent();
			int posicao = 50;

			for (int i = 0; i < matriz.length; i++) {
				mxGraph.insertVertex(parent, null, i + 1, 150 + posicao,
						150 + posicao, 50, 50);
				posicao += 50;
			}

			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {

				}
			}
			mxGraph.getModel().endUpdate();

		} catch (NumberFormatException numberFormatException) {
			numberFormatException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	/**
	 * Bugado
	 * 
	 * @todo usar uma tabela
	 * @param event
	 */
	private void imprimirMatrizAdjacencia(SelectionEvent event) {
		int matrizResultate[][] = grafo.getMatrizAdjacencia();
		String string;

		MessageBox messageBox = new MessageBox(janelaExibicaoGrafo,
				SWT.ICON_INFORMATION | SWT.OK);
		messageBox.setText("Informação");

		for (int i = 0; i < matrizResultate.length; i++) {
			for (int j = 0; j < matrizResultate.length; j++) {
				string = String.valueOf(matrizResultate[i][j]);
				messageBox.setMessage(string);
			}
		}

		messageBox.open();

	}

	private void plotarGrafo(Event e) {
		grafo.imprimirGrafo();

	}

	private void executarDFS(Event e) {
		DFS dfs = new DFS(grafo);
		//dfs.buscaDFS();
		dfs.classificarArestasArcos();

	}

	private void executarKruskal(Event e) {

	}
	
	private void ordenacaoTopologica(Event e) {
		DFS dfs = new DFS(grafo);

		for (int i = 0; i < dfs.ordenacaoTopologica().size(); i++) {
			System.out.println(dfs.ordenacaoTopologica().peek() + 1 + "-");

		}
	}

	public static void main(String[] args) {
		try {
			for (int i = 0; i < args.length; i++) {
				System.out.println(args.length);
			}
			ExecucaoGrafo execucaoGrafo = new ExecucaoGrafo();
			execucaoGrafo.open();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
