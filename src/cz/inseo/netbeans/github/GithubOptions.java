package cz.inseo.netbeans.github;

import com.github.api.v2.services.GitHubServiceFactory;
import org.openide.util.NbPreferences;

/**
 *
 * @author Pavel
 */
public class GithubOptions {

	private static GithubOptions INSTANCE;
	
	private static final String GITHUB_LOGIN = "github-login";

	private static GitHubServiceFactory serviceFactory;
		
	public static GithubOptions getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GithubOptions();
		}
		return INSTANCE;
	}
	
	private GithubOptions() { }
	
	public void setLogin(String login){
		NbPreferences.forModule(GithubOptions.class).put(GITHUB_LOGIN, login);
	}
	
	public String getLogin(){
		return NbPreferences.forModule(GithubOptions.class).get(GITHUB_LOGIN, "");
	}
	
	public boolean varifyLogin(){
		
		
		return false;
	}
	
	public String getOAuthToken(){
		GitHubServiceFactory factory = GitHubServiceFactory.newInstance();
		OAuthService OAuthService = factory.createOAuthService("", "");
		String authorizationUrl = OAuthService.getAuthorizationUrl("PavelMacaDev");
		try {
			Browser.openUrl(authorizationUrl);
		} catch (URISyntaxException ex) {
			Exceptions.printStackTrace(ex);
		}
	
	
		GistService gistService = factory.createGistService();

		return "";
	}
}
