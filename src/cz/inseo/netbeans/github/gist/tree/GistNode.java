package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.GithubAuth;
import java.util.Iterator;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.service.GistService;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class GistNode extends DefaultMutableTreeNode implements IIconNode {

	private static final ImageIcon ICON_PRIVATE = new ImageIcon(GistNode.class.getResource("/cz/inseo/netbeans/github/resources/images/private.png"));
	private static final ImageIcon ICON_PUBLIC = new ImageIcon(GistNode.class.getResource("/cz/inseo/netbeans/github/resources/images/public.png"));
	private Gist gist;

	GistNode(Gist gist) {
		super();
		setAllowsChildren(true);

		this.gist = gist;
		add(new EmptyNode());

	}

	public Gist getGist() {
		return gist;
	}

	@Override
	public Icon getIcon() {
		return gist.isPublic() ? ICON_PUBLIC : ICON_PRIVATE;
	}

	@Override
	public String toString() {
		String id = gist.isPublic() ? gist.getId() : gist.getId().substring(0, 6);
		return id + (gist.getDescription() != null ? ": " + gist.getDescription() : "");
	}

	public void expand() {
		if(getFirstChild() instanceof EmptyNode){
			removeAllChildren();
		}
		
		Map<String, GistFile> files = gist.getFiles();
		if (!files.isEmpty()) {
			Iterator iterator = files.keySet().iterator();// Iterate on keys
			FileNode fileNode;
		
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				fileNode = new FileNode(files.get(key));
				add(fileNode);
			}
		}
	}
}
