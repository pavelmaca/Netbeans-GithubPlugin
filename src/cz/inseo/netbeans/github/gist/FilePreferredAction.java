package cz.inseo.netbeans.github.gist;

import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.NodeAction;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class FilePreferredAction extends NodeAction {

	@Override
	protected void performAction(Node[] activatedNodes) {
		//open file in editor
		throw new UnsupportedOperationException("Not supported yet.1");
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
