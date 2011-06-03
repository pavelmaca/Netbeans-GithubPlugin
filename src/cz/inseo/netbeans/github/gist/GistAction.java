package cz.inseo.netbeans.github.gist;

import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.NodeAction;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class GistAction extends NodeAction {
	
	 public GistAction() {   }

	@Override
	protected void performAction(Node[] activatedNodes) {
		/*
		GistNode lookup = activatedNodes[0].getLookup().lookup(GistNode.class);
		if(lookup != null){
			InfoDialog.showInfo("ok");
		}*/
	}

	@Override
	protected boolean enable(Node[] activatedNodes) {
		return false;
	}

	@Override
	public String getName() {
		return "Do Something";
	}

	@Override
	public HelpCtx getHelpCtx() {
		throw new UnsupportedOperationException("Not supported yet3.");
	}
	
}
