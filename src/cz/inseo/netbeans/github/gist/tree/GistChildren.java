package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.GitHubAuth;
import cz.inseo.netbeans.github.options.GitHubOptions;
import cz.inseo.netbeans.github.tools.InfoDialog;
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
	
	public final static String ROOT_USER = "_root_user";
	
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
		//reload();		
    }
	
	@SuppressWarnings("unchecked")
	public void reload(){
		//fix bug when user change option when instance already exist
		String l_userName;
		if(userName.equals(ROOT_USER)){
			l_userName = GitHubOptions.getInstance().getLogin();
		}else{
			l_userName = userName;
		}
		
		if(l_userName == null ? userName == null : l_userName.equals("")){
			InfoDialog.showError("Please setup github setting.");
			return;
		}

		/* Load gists from github */
		GistService gistService = GitHubAuth.getGistService();

		
		try {
			List<Gist> gists = gistService.getGists(l_userName);
			
			setKeys(gists.toArray());			
		} catch (RequestException ex){
			GitHubAuth.processException(ex);
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}  
	}
	
}
