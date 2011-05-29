package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.GithubAuth;
import cz.inseo.netbeans.github.options.GithubOptions;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.service.GistService;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author Pavel
 */
public class GistTree {

	public static final ImageIcon ICON_ROOT = new ImageIcon(GistTree.class.getResource("/cz/inseo/netbeans/github/resources/images/g-icon.png"));
	public static final ImageIcon ICON_FILE = new ImageIcon(GistTree.class.getResource("/cz/inseo/netbeans/github/resources/images/paste.png"));
	public static final ImageIcon ICON_PRIVATE = new ImageIcon(GistTree.class.getResource("/cz/inseo/netbeans/github/resources/images/private.png"));
	public static final ImageIcon ICON_PUBLIC = new ImageIcon(GistTree.class.getResource("/cz/inseo/netbeans/github/resources/images/public.png"));
	protected JTree m_tree;
	protected DefaultTreeModel m_model;

	public GistTree() {
		String title = NbBundle.getMessage(GistTree.class, "GistTree.title");
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(
				new IconData(ICON_ROOT, null, title));

		//create nodes
		DefaultMutableTreeNode node;



		GistService gistService = GithubAuth.getGistService();

		List<Gist> gists;
		try {
			gists = gistService.getGists(GithubOptions.getInstance().getLogin());
			for (Iterator<Gist> it = gists.iterator(); it.hasNext();) {
				Gist gist = it.next();

				ImageIcon icon;

				if (gist.isPublic() == true) {
					icon = ICON_PUBLIC;
				} else {
					icon = ICON_PRIVATE;
				}

				node = new DefaultMutableTreeNode(new IconData(icon, null, new GistNode(gist)));
				top.add(node);
				node.add(new DefaultMutableTreeNode(true));

			}
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}


		m_model = new DefaultTreeModel(top);
		m_tree = new JTree(m_model);

		m_tree.putClientProperty("JTree.lineStyle", "Angled");

		TreeCellRenderer renderer = new IconCellRenderer();
		m_tree.setCellRenderer(renderer);

		//m_tree.addTreeExpansionListener(new GistExpansionListener());

		//m_tree.addTreeSelectionListener(new DirSelectionListener());

		m_tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		m_tree.setShowsRootHandles(true);
		m_tree.setEditable(false);
	}

	public JTree getTree() {
		return m_tree;
	}

	DefaultMutableTreeNode getTreeNode(TreePath path) {
		return (DefaultMutableTreeNode) (path.getLastPathComponent());
	}

	GistNode getGistNode(DefaultMutableTreeNode node) {
		if (node == null) {
			return null;
		}
		Object obj = node.getUserObject();
		if (obj instanceof IconData) {
			obj = ((IconData) obj).getObject();
		}
		if (obj instanceof GistNode) {
			return (GistNode) obj;
		} else {
			return null;
		}
	}
}




/*
class DirSelectionListener
		implements TreeSelectionListener {

	public void valueChanged(TreeSelectionEvent event) {
		DefaultMutableTreeNode node = getTreeNode(
				event.getPath());
		FileNode fnode = getFileNode(node);
		if (fnode != null) {
			m_display.setText(fnode.getFile().
					getAbsolutePath());
		} else {
			m_display.setText("");
		}
	}
}
public static void main(String argv[]) 
  {
    new FileTree1();
  



}		*/