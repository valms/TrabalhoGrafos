package interfaceGrafica;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;

public class ExecucaoGrafo extends Dialog {

	protected Object result;
	protected Shell shlTeoriaDosGrafos;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ExecucaoGrafo(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlTeoriaDosGrafos.open();
		shlTeoriaDosGrafos.layout();
		Display display = getParent().getDisplay();
		while (!shlTeoriaDosGrafos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlTeoriaDosGrafos = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL);
		shlTeoriaDosGrafos.setSize(830, 586);
		shlTeoriaDosGrafos.setText("Teoria dos Grafos 2015.1");

	}

}
