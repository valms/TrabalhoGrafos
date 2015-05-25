package interfaceGrafica;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;

public class InterfaceEscolhaArquivo {

	protected Shell janelaEntradaGrafo;
	private static final String[] FILTRO_NOMES = { "Arquivo de texto (*.txt)" };
	private static final String[] FILTRO_EXTENSOES = { "*.txt" };
	private Text diretorioTexto;

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
		janelaEntradaGrafo = new Shell();
		janelaEntradaGrafo.setSize(634, 195);
		janelaEntradaGrafo.setText("Grafos 2015.2");
		final Text fileName = new Text(janelaEntradaGrafo, SWT.BORDER);
		
		SelectionListener listener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = ((Button) e.widget);
				System.out.println(button.getText());
				System.out.println("Selecionado = " + button.getSelection());

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

		Button btnIniciar = new Button(janelaEntradaGrafo, SWT.NONE);
		btnIniciar.setBounds(263, 121, 75, 25);
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
					fileName.setText(buf.toString());

				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		btnIniciar.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}
}
