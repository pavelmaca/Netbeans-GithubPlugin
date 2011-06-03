package cz.inseo.netbeans.github.tools;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.openide.util.Exceptions;

/**
 *
 * @author Pavel
 */
public class Browser {
	
	public static void openUrl(String url) throws URISyntaxException{
		if (Desktop.isDesktopSupported()) {
           try {
               try {
                   Desktop.getDesktop().browse(new URI(url));
               } catch (URISyntaxException ex) {
                   Exceptions.printStackTrace(ex);
               }
           } catch (IOException ex) {
               Exceptions.printStackTrace(ex);
           }
       }
	}
	
}
