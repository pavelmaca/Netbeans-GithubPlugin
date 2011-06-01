package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.GithubAuth;
import cz.inseo.netbeans.github.tools.InfoDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.service.GistService;
import org.openide.util.Exceptions;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class GistsTree extends JTree {

	private String userName;

	public GistsTree(String title, String userName) {
		super();
		this.userName = userName;

		RootNode top = new RootNode(title);
		setModel(new GistsTreeModel(top));

		IconCellRenderer m_cellRenderer = new IconCellRenderer();
		setCellRenderer(m_cellRenderer);

		addTreeWillExpandListener(new GistsTreeWillExpandListener());
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				doMouseClicked(e);
			}
			
		});
	}

	public void reload() {
		GistService gistService = GithubAuth.getGistService();

		try {
			List<Gist> gists = gistService.getGists(userName);
			DefaultMutableTreeNode gNode;

			RootNode root = (RootNode) getModel().getRoot();
			root.removeAllChildren();

			for (Iterator<Gist> it = gists.iterator(); it.hasNext();) {
				Gist gist = it.next();
				gNode = new GistNode(gist);
				root.add(gNode);
			}

			((GistsTreeModel) treeModel).reload(root);
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}
	}

	void doMouseClicked(MouseEvent me) {
		TreePath path = getPathForLocation(me.getX(), me.getY());
		if(path == null) return;
		
		Object component = path.getLastPathComponent();
		if(component instanceof FileNode && me.getClickCount() == 2){
			//open file in editor
			FileNode node = (FileNode) component;
			node.openInEditor();
			//InfoDialog.showInfo("Clicked: "+node.getFile().getFilename());
		}
	
	}
}
