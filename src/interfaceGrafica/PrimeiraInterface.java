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

public class PrimeiraInterface {

	protected Shell shell;
	private static final String[] FILTER_NAMES = {
			"OpenOffice.org Spreadsheet Files (*.sxc)",
			"Microsoft Excel Spreadsheet Files (*.xls)",
			"Comma Separated Values Files (*.csv)", "All Files (*.*)" };
	private static final String[] FILTER_EXTS = { "*.sxc", "*.xls", "*.csv",
			"*.*" };

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PrimeiraInterface window = new PrimeiraInterface();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(533, 356);
		shell.setText("SWT Application");
		final Text fileName = new Text(shell, SWT.BORDER);

		Label lblSelecioneOArquivo = new Label(shell, SWT.NONE);
		lblSelecioneOArquivo.setBounds(10, 37, 259, 15);
		lblSelecioneOArquivo.setText("Selecione o Arquivo:");

		Button btnNewButton = new Button(shell, SWT.PUSH);
		btnNewButton.setBounds(10, 75, 75, 25);
		btnNewButton.setText("New Button");
		btnNewButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterNames(FILTER_NAMES);
				dialog.setFilterExtensions(FILTER_EXTS);
				String fn = dialog.open();
				if (fn != null) {
					StringBuffer buf = new StringBuffer();
					String[] files = dialog.getFileNames();
					for (int i = 0, n = files.length; i < n; i++) {
						buf.append(dialog.getFilterPath());
						if (buf.charAt(buf.length() - 1) != File.separatorChar) {
							buf.append(File.separatorChar);
						}
						buf.append(files[i]);
						buf.append(" ");
					}
					fileName.setText(buf.toString());

				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}
}
