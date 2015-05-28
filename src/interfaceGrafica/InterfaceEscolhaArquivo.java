package interfaceGrafica;

import io.ManipularTxt;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class InterfaceEscolhaArquivo {

	protected Shell janelaEntradaGrafo;
	private static final String[] FILTRO_NOMES = { "Arquivo de texto (*.txt)" };
	private static final String[] FILTRO_EXTENSOES = { "*.txt" };
	private Text diretorioTexto;
	private boolean radioSelecionado = false;
	private String radioButtonSelecionado;

	public static void main(String[] args) {
		try {
			InterfaceEscolhaArquivo window = new InterfaceEscolhaArquivo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		janelaEntradaGrafo.open();
		janelaEntradaGrafo.layout();
		while (!janelaEntradaGrafo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		janelaEntradaGrafo = new Shell(SWT.TITLE | SWT.MIN);
		janelaEntradaGrafo.setSize(634, 304);
		janelaEntradaGrafo.setText("Grafos 2015.2");
		final Text FILENAME = new Text(janelaEntradaGrafo, SWT.BORDER);

		SelectionListener listener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = ((Button) e.widget);

				if (button.getSelection()) {
					System.out.println(button.getText());
					radioButtonSelecionado = button.getText();
					radioSelecionado = true;

				}

				System.out.println();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		};

		Label lblSelecioneOArquivo = new Label(janelaEntradaGrafo, SWT.NONE);
		lblSelecioneOArquivo.setBounds(10, 6, 259, 15);
		lblSelecioneOArquivo.setText("Selecione o Arquivo:");

		Button btnAbrirTxt = new Button(janelaEntradaGrafo, SWT.PUSH);
		btnAbrirTxt.setBounds(533, 25, 75, 25);
		btnAbrirTxt.setText("Selecionar");

		diretorioTexto = new Text(janelaEntradaGrafo, SWT.BORDER);
		diretorioTexto.setBounds(10, 27, 517, 21);
		diretorioTexto.setEnabled(false);

		Button btnIniciar = new Button(janelaEntradaGrafo, SWT.NONE);
		btnIniciar.setBounds(256, 240, 75, 25);
		btnIniciar.setText("Iniciar");

		Button radios[] = new Button[3];

		radios[0] = new Button(janelaEntradaGrafo, SWT.RADIO);
		radios[0].setBounds(10, 81, 178, 16);
		radios[0].setText("Busca em Profundidade (DFS)");
		radios[0].addSelectionListener(listener);

		radios[1] = new Button(janelaEntradaGrafo, SWT.RADIO);
		radios[1].setBounds(194, 81, 207, 16);
		radios[1].setText("\u00C1rvore de Cobertura M\u00EDnima (MST)");
		radios[1].addSelectionListener(listener);

		radios[2] = new Button(janelaEntradaGrafo, SWT.RADIO);
		radios[2].setBounds(415, 81, 193, 16);
		radios[2].setText("Ordena\u00E7\u00E3o Topol\u00F3gica (DiGrafo)");
		radios[2].addSelectionListener(listener);

		btnAbrirTxt.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialog = new FileDialog(janelaEntradaGrafo, SWT.OPEN);

				dialog.setFilterNames(FILTRO_NOMES);
				dialog.setFilterExtensions(FILTRO_EXTENSOES);

				String fn = dialog.open();

				if (fn != null) {
					StringBuffer buf = new StringBuffer();

					String[] files = dialog.getFileNames();
					for (int i = 0, n = files.length; i < n; i++) {
						buf.append(dialog.getFilterPath());
						if (buf.charAt(buf.length() - 1) != File.separatorChar) {
							buf.append(File.separatorChar);
							diretorioTexto.setText(fn);
						}
						buf.append(files[i]);
						buf.append(" ");
					}
					FILENAME.setText(buf.toString());

				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		btnIniciar.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (diretorioTexto.getText().isEmpty()) {
					// System.out.println("Entrou");
					MessageBox alertMessageBox = new MessageBox(
							janelaEntradaGrafo, SWT.ICON_ERROR | SWT.OK);
					alertMessageBox.setText("ERRO!");
					alertMessageBox
							.setMessage("Nenhum arquivo foi apontado. Favor, selecione o arquivo .txt desejado.");
					alertMessageBox.open();

				} else if (!radioSelecionado) {
					MessageBox alertMessageBox = new MessageBox(
							janelaEntradaGrafo, SWT.ICON_ERROR | SWT.OK);
					alertMessageBox.setText("ERRO!");
					alertMessageBox
							.setMessage("Nenhum algorimo foi selecionado. Favor, selecione o algoritmo desejado.");
					alertMessageBox.open();

				} else {

					ManipularTxt manipularTxt = new ManipularTxt();
					File file = new File(diretorioTexto.getText());

					try {
						ExecucaoGrafo execucaoGrafo;

						switch (radioButtonSelecionado) {
						case "Busca em Profundidade (DFS)":
							manipularTxt.matrizAdjacencia(file);
							execucaoGrafo = new ExecucaoGrafo(
									janelaEntradaGrafo, 0);
							execucaoGrafo.open();
							break;

						case "Árvore de Cobertura Mínima (MST)":
							manipularTxt.matrizAdjacencia(file);
							execucaoGrafo = new ExecucaoGrafo(
									janelaEntradaGrafo, 0);
							execucaoGrafo.open();

							break;

						case "Ordenação Topológica (DiGrafo)":
							manipularTxt.matrizAdjacencia(file);
							execucaoGrafo = new ExecucaoGrafo(
									janelaEntradaGrafo, 0);
							execucaoGrafo.open();
							break;
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (NumberFormatException e2) {
						MessageBox alertMessageBox = new MessageBox(
								janelaEntradaGrafo, SWT.ICON_ERROR | SWT.OK);
						alertMessageBox.setText("ERRO!");
						alertMessageBox
								.setMessage("Entrada de Dados Inválidos. Favor, verifique se o arquivo esta correto.");
						alertMessageBox.open();
					}

				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}
}
