package cz.inseo.netbeans.github;

import cz.inseo.netbeans.github.options.GithubOptions;
import org.eclipse.egit.github.core.client.GitHubClient;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class User {
	
	public static boolean tryLogin(){
		GitHubClient client = new GitHubClient();
		client.setCredentials(GithubOptions.getInstance().getLogin(), GithubOptions.getInstance().getPassword());
		String user = client.getUser();
		return user.equals(GithubOptions.getInstance().getLogin());
	}
}
