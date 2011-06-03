package cz.inseo.netbeans.github.gist;

import cz.inseo.netbeans.github.GithubAuth;
import java.io.IOException;
import java.util.List;
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
	
	private String userName;

	public GistChildren(String userName) {
		this.userName = userName;
	}

	@Override
	protected Node[] createNodes(Object key) {
		Gist obj = (Gist) key;
        return new Node[] { new GistNode( obj ) };
	}
	
	@Override
	protected void addNotify() {
        super.addNotify();
		reload();		
    }
	
	public void reload(){
		/* Load gists from github */
		GistService gistService = GithubAuth.getGistService();

		
		try {
			List<Gist> gists = gistService.getGists(userName);
			
			Gist[] objs = new Gist[gists.size()];
			
			for(int i = 0; i < gists.size(); i++){
				objs[i] = gists.get(i);
			}
			
			setKeys(objs);
		} catch (RequestException ex){
			GithubAuth.processException(ex);
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}  
	}
	
}
