package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.tools.InfoDialog;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.NodeAction;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class RootAction extends NodeAction{
	
	public RootAction() {
       putValue (NAME, "Refresh");
    }

	@Override
	protected void performAction(Node[] activatedNodes) {
		if(activatedNodes[0] instanceof RootNode){
			RootNode node = (RootNode) activatedNodes[0];
			GistChildren child = (GistChildren) node.getChildren();
			child.reload();
		}
	}

	@Override
	protected boolean enable(Node[] activatedNodes) {
		return true;
	}

	@Override
	public String getName() {
		throw new UnsupportedOperationException("Not supported yet.2");
	}

	@Override
	public HelpCtx getHelpCtx() {
		throw new UnsupportedOperationException("Not supported yet.3");
	}
}
