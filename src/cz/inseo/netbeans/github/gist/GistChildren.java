package cz.inseo.netbeans.github.gist;

import cz.inseo.netbeans.github.GithubAuth;
import cz.inseo.netbeans.github.options.GithubOptions;
import cz.inseo.netbeans.github.tools.InfoDialog;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.GistService;
import org.openide.nodes.Children;

import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class GistChildren extends Children.Keys {

	public GistChildren() {
	}

	@Override
	protected Node[] createNodes(Object key) {
		Gist obj = (Gist) key;
        return new Node[] { new GistNode( obj ) };
	}
	
	@Override
	protected void addNotify() {
        super.addNotify();
		
		/* Load gists from github */
		GistService gistService = GithubAuth.getGistService();

		
		try {
			List<Gist> gists = gistService.getGists(GithubOptions.getInstance().getLogin());
			
			Gist[] objs = new Gist[gists.size()];
			
			for(int i = 0; i < gists.size(); i++){
				objs[i] = gists.get(i);
			}
			
			setKeys(objs);
		} catch (RequestException ex){
			InfoDialog.showError(ex.getMessage());
			
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}        
    }
}
