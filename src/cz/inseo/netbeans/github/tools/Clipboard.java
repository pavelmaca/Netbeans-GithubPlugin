package cz.inseo.netbeans.github.tools;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 *
 * @author Pavel
 */
public class Clipboard implements ClipboardOwner {

	/**
	 * Place a String on the clipboard, and make this class the
	 * owner of the Clipboard's contents.
	 */
	public void setContents(String aString) {
		StringSelection stringSelection = new StringSelection(aString);
		java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, this);
	}

	/* * ******************* interface ClipboardOwner ******************* */
	@Override
	public void lostOwnership(java.awt.datatransfer.Clipboard clipboard, Transferable contents) {
		//do nothing
	}
}
