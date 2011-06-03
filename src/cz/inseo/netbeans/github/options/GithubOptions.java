package cz.inseo.netbeans.github.options;

import org.openide.util.NbPreferences;

/**
 *
 * @author Pavel
 */
public class GithubOptions {

	private static GithubOptions INSTANCE;
	private static final String GITHUB_LOGIN = "github-login";
	private static final String GITHUB_PASSWORD = "github-password";

	public static GithubOptions getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GithubOptions();
		}
		return INSTANCE;
	}

	private GithubOptions() {
	}

	public void setLogin(String login) {
		NbPreferences.forModule(GithubOptions.class).put(GITHUB_LOGIN, login);
	}

	public String getLogin() {
		return NbPreferences.forModule(GithubOptions.class).get(GITHUB_LOGIN, "");
	}

	public void setPassword(String password) {
		NbPreferences.forModule(GithubOptions.class).put(GITHUB_PASSWORD, password);
	}

	public String getPassword() {
		return NbPreferences.forModule(GithubOptions.class).get(GITHUB_PASSWORD, "");
	}
}