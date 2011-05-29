package cz.inseo.netbeans.github.options;

import com.github.api.v2.services.GitHubServiceFactory;
import com.github.api.v2.services.OAuthService;
import com.github.api.v2.services.auth.LoginTokenAuthentication;
import cz.inseo.netbeans.github.tools.Browser;
import cz.inseo.netbeans.github.tools.InfoDialog;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import org.openide.DialogDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 *
 * @author Pavel
 */
public class GithubOptions {

	private static GithubOptions INSTANCE;
	private static final String GITHUB_LOGIN = "github-login";
	private static final String GITHUB_OAUTH_KEY = "clientID";
	private static final String GITHUB_OAUTH_SECRET_KEY = "clientSecret";
	private static GitHubServiceFactory serviceFactory;
	private static LoginTokenAuthentication loginTokenAuthentication;
	private static String clientID;
	private static String clientSecret;

	public static GithubOptions getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GithubOptions();
		}
		return INSTANCE;
	}

	private GithubOptions() {
		try {
			Properties p = new Properties();

			getClass().getResource("/cz/inseo/netbeans/github/oauthKeys.xml");

			InputStream xml = getClass().getResourceAsStream("/cz/inseo/netbeans/github/oauthKeys.xml");
			if (xml == null) {
				InfoDialog.showInfo("file not found");
			} else {
				InfoDialog.showInfo("loaded");
			}
			p.loadFromXML(xml);

			clientID = p.getProperty(GITHUB_OAUTH_KEY);
			InfoDialog.showInfo("read: " + clientID);
			clientSecret = p.getProperty(GITHUB_OAUTH_SECRET_KEY);
		} catch (InvalidPropertiesFormatException ex) {
			Exceptions.printStackTrace(ex);
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}
	}

	/* * ******************* NB setting variables ******************* */
	public void setLogin(String login) {
		NbPreferences.forModule(GithubOptions.class).put(GITHUB_LOGIN, login);
	}

	public String getLogin() {
		return NbPreferences.forModule(GithubOptions.class).get(GITHUB_LOGIN, "");
	}

	/* * ******************* local settings && tools ******************* */
	public GitHubServiceFactory getServiseFactory() {
		if (serviceFactory == null) {
			serviceFactory = GitHubServiceFactory.newInstance();
		}
		return serviceFactory;
	}

	public LoginTokenAuthentication getLoginTokenAuthentication() {
		if (loginTokenAuthentication == null) {
			loginTokenAuthentication = new LoginTokenAuthentication(getLogin(), getOAuthToken());
		}
		return loginTokenAuthentication;
	}

	public boolean tryAuthenttcate() {
		if (getLogin() == null) {
			return false;
		}

		if (getOAuthToken() == null) {
			OAuthService authService = getServiseFactory().createOAuthService(clientID, clientSecret);
			String authorizationUrl = authService.getAuthorizationUrl(null);
			InfoDialog.showInfo("Please config access on github.com (plugin will open request url)");
			try {
				Browser.openUrl(authorizationUrl);
			} catch (URISyntaxException ex) {
				Exceptions.printStackTrace(ex);
			}
		}

		return false;
	}

	public String getOAuthToken() {
		OAuthService OAuthService = getServiseFactory().createOAuthService("", "");
		return OAuthService.getAccessToken(null, null);
	}
}
