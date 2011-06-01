package cz.inseo.netbeans.github.gist.tree;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class GistsTreeWillExpandListener implements TreeWillExpandListener{

	@Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		TreePath path = event.getPath();
		Object component = path.getLastPathComponent();
		if(component instanceof GistNode){
			GistNode gNode = (GistNode) component;
			gNode.expand();
		}
	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
	}
	
}
