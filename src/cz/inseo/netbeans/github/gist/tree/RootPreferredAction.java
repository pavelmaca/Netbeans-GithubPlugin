package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.VirtualFileSystem;
import cz.inseo.netbeans.github.tools.InfoDialog;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.NodeAction;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class RootPreferredAction extends NodeAction {
	
	private static boolean init = true;
	
	@Override
	protected void performAction(Node[] activatedNodes) {
		RootNode node;
		
		for (int i= 0; i < activatedNodes.length; i++) {
			if(init == true && activatedNodes[i] instanceof RootNode){
				node = (RootNode) activatedNodes[i];
		
				((GistChildren) node.getChildren()).reload();
				init = false;
			}
		}
	}

	@Override
	protected boolean enable(Node[] activatedNodes) {
		return true;
	}

	@Override
	public String getName() {
		throw new UnsupportedOperationException("Not supported yet.3");
	}

	@Override
	public HelpCtx getHelpCtx() {
		throw new UnsupportedOperationException("Not supported yet.4");
	}
	
}
