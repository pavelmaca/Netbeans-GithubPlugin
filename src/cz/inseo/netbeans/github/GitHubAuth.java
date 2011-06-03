package cz.inseo.netbeans.github;

import cz.inseo.netbeans.github.options.GitHubOptions;
import cz.inseo.netbeans.github.tools.InfoDialog;
import java.io.IOException;
import java.util.List;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.GistService;
import org.openide.util.Exceptions;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class GitHubAuth {

	public static void processException(RequestException ex) {
		InfoDialog.showError("GitHub plugin: "+ex.getError().getMessage());
	}

	public static boolean tryLogin() {
		GitHubClient client = new GitHubClient();
		client.setCredentials(GitHubOptions.getInstance().getLogin(), GitHubOptions.getInstance().getPassword());
		GistService gistService = new GistService(client);
		try {
			List<Gist> gists = gistService.getGists(GitHubOptions.getInstance().getLogin());
			return true;
		} catch (RequestException ex) {
			processException(ex);
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}
		return false;
	}

	public static GistService getGistService() {
		GitHubClient client = new GitHubClient();
		client.setCredentials(GitHubOptions.getInstance().getLogin(), GitHubOptions.getInstance().getPassword());
		return new GistService(client);
	}
}
