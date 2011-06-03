package cz.inseo.netbeans.github.options;

import org.openide.util.NbPreferences;

/**
 *
 * @author Pavel
 */
public class GitHubOptions {

	private static GitHubOptions INSTANCE;
	private static final String GITHUB_LOGIN = "github-login";
	private static final String GITHUB_PASSWORD = "github-password";

	public static GitHubOptions getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GitHubOptions();
		}
		return INSTANCE;
	}

	private GitHubOptions() {
	}

	public void setLogin(String login) {
		NbPreferences.forModule(GitHubOptions.class).put(GITHUB_LOGIN, login);
	}

	public String getLogin() {
		return NbPreferences.forModule(GitHubOptions.class).get(GITHUB_LOGIN, "");
	}

	public void setPassword(String password) {
		NbPreferences.forModule(GitHubOptions.class).put(GITHUB_PASSWORD, password);
	}

	public String getPassword() {
		return NbPreferences.forModule(GitHubOptions.class).get(GITHUB_PASSWORD, "");
	}
}