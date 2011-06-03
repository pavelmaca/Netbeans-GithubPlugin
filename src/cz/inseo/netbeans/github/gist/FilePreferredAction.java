package cz.inseo.netbeans.github.gist;

import cz.inseo.netbeans.github.VirtualFileSystem;
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
		FileNode node;
		
		for (int i= 0; i < activatedNodes.length; i++) {
			if(activatedNodes[i] instanceof FileNode){
				node = (FileNode) activatedNodes[i];
				//open file in editor
				VirtualFileSystem.getIstance().openGistFile(node.getFile(), node.getGistId());
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
