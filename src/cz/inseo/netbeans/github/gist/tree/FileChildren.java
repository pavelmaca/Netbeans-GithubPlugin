package cz.inseo.netbeans.github.gist.tree;

import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.openide.nodes.Index;
import org.openide.nodes.Node;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class FileChildren extends Index.ArrayChildren {

	private Gist gist;

	public FileChildren(Gist gist) {
		this.gist = gist;
	}

	@Override
	protected java.util.List<Node> initCollection() {
		java.util.Map<String, GistFile> files = gist.getFiles();
		
		ArrayList<Node> childrenNodes = new ArrayList<Node>(files.size());
		
		Iterator iterator = files.keySet().iterator();// Iterate on keys
		GistFile file;
		while (iterator.hasNext()) {
				String key = (String) iterator.next();
				file = files.get(key);
				childrenNodes.add(new FileNode(file));
		}
		
		return childrenNodes;
	}
}
