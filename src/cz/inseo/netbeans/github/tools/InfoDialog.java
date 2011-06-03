package cz.inseo.netbeans.github.tools;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author Pavel
 */
public class InfoDialog {

	public static void showInfo(String message) {
		notify(message, NotifyDescriptor.INFORMATION_MESSAGE);
	}
	
	public static void showError(String message){
		notify(message, NotifyDescriptor.ERROR_MESSAGE);
	}
	
	private static void notify(String message, int flag){
		NotifyDescriptor d = new NotifyDescriptor.Message( message, flag);
		DialogDisplayer.getDefault().notify(d);
	}
}
