package cz.inseo.netbeans.github.gist;

import java.awt.Frame;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.actions.EditAction;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.cookies.EditorCookie;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.windows.WindowManager;

@ActionID(category = "Edit",
id = "cz.inseo.netbeans.github.gist.CreateGistAction")
@ActionRegistration(iconBase = "cz/inseo/netbeans/github/resources/images/g-icon.png",
displayName = "#CreateGistDialog.title")
@ActionReferences({
	@ActionReference(path = "Editors/Popup", position = 4040, separatorBefore = 4035, separatorAfter = 4045),
	@ActionReference(path = "Menu/Edit", position = 1470, separatorBefore = 1455),
	@ActionReference(path = "Toolbars/Clipboard", position = 400)
	/*@ActionReference(path = "Loaders/Languages/Actions", position = 1150, separatorAfter = 1175),*/
	
})
public final class CreateGistAction extends EditAction {

	@Override
	protected void initialize() {
		super.initialize();
	}

	@Override
	protected int mode() {
		return MODE_EXACTLY_ONE;
	}

	@Override
	protected Class<?>[] cookieClasses() {
		return new Class[]{EditorCookie.class};
	}

	@Override
	protected void performAction(Node[] activatedNodes) {
	
		EditorCookie cookie = activatedNodes[0].getLookup().lookup(EditorCookie.class);

		JTextComponent component = cookie.getOpenedPanes()[0];

		final String mimeType = NbEditorUtilities.getMimeType(component);
		final String displayName = activatedNodes[0].getDisplayName();

		final String text = component.getSelectedText();
		if (text != null && !text.isEmpty()) {
			RequestProcessor.getDefault().execute(new Runnable() {

				@Override
				public void run() {

					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {

							Frame f = WindowManager.getDefault().getMainWindow();
							int x = f.getX() + f.getWidth() / 2;
							int y = f.getY() + f.getHeight() / 2;
							CreateGistDialog dialog = new CreateGistDialog(f, true);
							dialog.setLocation(x, y);

							dialog.setFile(displayName, text, mimeType);


							dialog.setVisible(true);
						}
					});
				}
			});
		}
	}

	@Override
	public String getName() {
		return NbBundle.getMessage(CreateGistAction.class, "CreateGistDialog.title");
	}

	@Override
	public HelpCtx getHelpCtx() {
		return HelpCtx.DEFAULT_HELP;
	}
}
