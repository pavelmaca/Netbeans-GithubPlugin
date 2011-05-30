package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.tools.InfoDialog;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;

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


		m_model = new DefaultTreeModel(top);
		m_tree = new JTree(m_model);

		//m_tree.putClientProperty("JTree.lineStyle", "Angled");

		TreeCellRenderer renderer = new IconCellRenderer();
		m_tree.setCellRenderer(renderer);

		m_tree.addTreeExpansionListener(new GistExpansionListener());

		//m_tree.addTreeSelectionListener(new GistSelectionListener());

		m_tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		m_tree.setShowsRootHandles(true);
		m_tree.setEditable(false);
	}

	public JTree getTree() {
		return m_tree;
	}

	public DefaultMutableTreeNode getTreeNode(TreePath path) {
		return (DefaultMutableTreeNode) (path.getLastPathComponent());
	}

	public GistNode getGistNode(DefaultMutableTreeNode node) {
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
	/*
	public class GistSelectionListener implements TreeSelectionListener {
	
	@Override
	public void valueChanged(TreeSelectionEvent event) {
	//	InfoDialog.showInfo(event.toString());
	DefaultMutableTreeNode node = GistTree.getTreeNode(
	event.getPath());
	GistNode gnode = GistTree.getGistNode(node); 
	if (gnode != null) {
	//	InfoDialog.showInfo(gnode.getGist().getId());
	}
	}
	}*/

	public class GistExpansionListener implements TreeExpansionListener {

		@Override
		public void treeExpanded(TreeExpansionEvent event) {
			final DefaultMutableTreeNode node = getTreeNode(event.getPath());
			final GistNode gnode = getGistNode(node);

			RequestProcessor.getDefault().execute(new Runnable() {

				@Override
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							if (gnode != null && gnode.expand(node)) {
								Runnable runnable = new Runnable() {

									@Override
									public void run() {
										m_model.reload(node);
									}
								};
								SwingUtilities.invokeLater(runnable);
							}
						}
					});
				}
			});


		}

		@Override
		public void treeCollapsed(TreeExpansionEvent event) {
		}
	}
}
