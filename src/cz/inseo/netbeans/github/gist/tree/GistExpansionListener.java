
package cz.inseo.netbeans.github.gist.tree;

import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Pavel
 */
/*
public class GistExpansionListener implements TreeExpansionListener {
	
	// Make sure expansion is threaded and updating the tree model
// only occurs within the event dispatching thread.
	
	public void treeExpanded(TreeExpansionEvent event) {
		final DefaultMutableTreeNode node = getTreeNode(
				event.getPath());
		final FileNode fnode = getFileNode(node);

		Thread runner = new Thread() {

			public void run() {
				if (fnode != null && fnode.expand(node)) {
					Runnable runnable = new Runnable() {

						public void run() {
							m_model.reload(node);
						}
					};
					SwingUtilities.invokeLater(runnable);
				}
			}
		};
		runner.start();
	}

	public void treeCollapsed(TreeExpansionEvent event) {
	}
}*/
