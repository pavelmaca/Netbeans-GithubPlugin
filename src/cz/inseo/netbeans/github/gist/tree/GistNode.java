package cz.inseo.netbeans.github.gist.tree;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;


/**
 *
 * @author Pavel
 */
public class GistNode {

	protected Gist m_gist;

	public GistNode(Gist gist) {
		m_gist = gist;
	}

	public Gist getGist() {
		return m_gist;
	}

	public boolean expand(DefaultMutableTreeNode parent) {
		DefaultMutableTreeNode flag =
				(DefaultMutableTreeNode) parent.getFirstChild();
		if (flag == null) // No flag
		{
			return false;
		}
		Object obj = flag.getUserObject();
		if (!(obj instanceof Boolean)) {
			return false;      // Already expanded
		}
		parent.removeAllChildren();  // Remove Flag
		
		Map<String, GistFile> files = m_gist.getFiles();
		if (files.isEmpty()) {
			return true;
		}

		Vector v = new Vector();

		Iterator iterator = files.keySet().iterator();// Iterate on keys
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			FileNode newNode = new FileNode(files.get(key));
			v.addElement(newNode);
		}

		for (int i = 0; i < v.size(); i++) {
			FileNode nd = (FileNode) v.elementAt(i);
			IconData idata = new IconData(GistTree.ICON_FILE, GistTree.ICON_PRIVATE, nd);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(idata);
			parent.add(node);
		}

		return true;
	}

	@Override
	public String toString() {
		return m_gist.getId()+( m_gist.getDescription() != null ? ": "+m_gist.getDescription() : "");
	}
}
