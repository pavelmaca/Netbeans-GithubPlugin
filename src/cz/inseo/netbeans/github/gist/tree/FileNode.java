package cz.inseo.netbeans.github.gist.tree;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cz.inseo.netbeans.github.tools.InfoDialog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import org.eclipse.egit.github.core.GistFile;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class FileNode extends DefaultMutableTreeNode implements IIconNode {

	private static final ImageIcon ICON = new ImageIcon(FileNode.class.getResource("/cz/inseo/netbeans/github/resources/images/paste.png"));
	private GistFile file;

	FileNode(GistFile gistFile) {
		file = gistFile;
	}

	public GistFile getFile() {
		return file;
	}

	@Override
	public Icon getIcon() {
		return ICON;
	}

	@Override
	public String toString() {
		return file.getFilename();
	}

	public void openInEditor() {


		//create wirtual file
		try {
			FileSystem mfs = FileUtil.createMemoryFileSystem();
			FileObject fob = mfs.getRoot().createData("muj soubor", "php");


			String content = file.getRawUrl();

			try {
				InputStream openStream;
				openStream = new URL(content).openStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(openStream, "UTF-8"));

				String txt = "";

				String temp;
				while ((temp = reader.readLine()) != null) {
					txt += temp+"\n";
				}

				//write content

				OutputStream oStream = fob.getOutputStream();
				oStream.write(txt.getBytes());
				oStream.flush();
				oStream.close();


			} catch (IOException ex) {
				Exceptions.printStackTrace(ex);
			}

			//open file
			DataObject data = DataObject.find(fob);
			EditorCookie cookie = data.getCookie(EditorCookie.class);
			cookie.open();
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}



	}
}
