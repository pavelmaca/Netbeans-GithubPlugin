package cz.inseo.netbeans.github.gist;

import javax.swing.Action;
import org.eclipse.egit.github.core.Gist;
import org.openide.nodes.AbstractNode;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class GistNode extends AbstractNode {

	public GistNode(Gist gist) {
		super(new FileChildren(gist), Lookups.singleton(gist));

		String name = gist.isPublic() ? gist.getId() : gist.getId().substring(0, 6);
		name += (gist.getDescription() != null ? ": " + gist.getDescription() : "");
		setDisplayName(name);

		String icon = gist.isPublic() ? "cz/inseo/netbeans/github/resources/images/public.png"
				: "cz/inseo/netbeans/github/resources/images/private.png";
		setIconBaseWithExtension(icon);
	}

	@Override
	public Action[] getActions(boolean context) {
		//return new Action[] { new GistAction() };
		return super.getActions(context);

	}
	
}
