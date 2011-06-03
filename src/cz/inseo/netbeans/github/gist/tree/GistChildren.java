package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.GithubAuth;
import cz.inseo.netbeans.github.options.GithubOptions;
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
	
	public void reload(){
		//fix bug when user change option when instance already exist
		String l_userName;
		if(userName.equals(ROOT_USER)){
			l_userName = GithubOptions.getInstance().getLogin();
		}else{
			l_userName = userName;
		}
		
		if(l_userName == null ? userName == null : l_userName.equals("")){
			InfoDialog.showError("Please setup github setting.");
			return;
		}

		/* Load gists from github */
		GistService gistService = GithubAuth.getGistService();

		
		try {
			List<Gist> gists = gistService.getGists(l_userName);
			
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
