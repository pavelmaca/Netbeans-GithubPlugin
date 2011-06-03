package cz.inseo.netbeans.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import org.eclipse.egit.github.core.GistFile;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class VirtualFileSystem {

	private static VirtualFileSystem INSTANCE;
	private FileSystem fileSystem;

	public static VirtualFileSystem getIstance() {
		if (INSTANCE == null) {
			INSTANCE = new VirtualFileSystem();
		}
		return INSTANCE;
	}

	private VirtualFileSystem() {
		fileSystem = FileUtil.createMemoryFileSystem();
	}

	private FileObject createGistFile(FileObject gistDir, GistFile file) throws VirtualFileSystemException {
		String ext = FileUtil.getExtension(file.getFilename());

		String name = "";

		int index = file.getFilename().lastIndexOf('.');
		if (index > 0 && index <= file.getFilename().length() - 2) {
			name = file.getFilename().substring(0, index);
		}
		FileObject gistFile = null;
		try {
			gistFile = gistDir.createData(name, ext);

			String rawFile = readRawFile(file.getRawUrl());

			OutputStream oStream = gistFile.getOutputStream();
			oStream.write(rawFile.getBytes());
			oStream.flush();
			oStream.close();

		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}

		if (gistFile == null) {
			throw new VirtualFileSystemException("Can't create file: " + file.getFilename());
		}
		return gistFile;
	}

	private String readRawFile(String url) {
		String txt = new String();
		try {
			InputStream openStream;
			openStream = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(openStream, "UTF-8"));

			String temp;
			while ((temp = reader.readLine()) != null) {
				txt += temp + "\n";
			}

		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}

		return txt;
	}

	private FileObject getGistFile(GistFile file, String gistId) throws VirtualFileSystemException {
		//try open gist folder
		FileObject gistDir = fileSystem.getRoot().getFileObject(gistId);
		if (gistDir == null) {
			try {
				//create gist folder
				gistDir = fileSystem.getRoot().createFolder(gistId);
			} catch (IOException ex) {
				Exceptions.printStackTrace(ex);
			}
		}

		if (gistDir == null) {
			throw new VirtualFileSystemException("Cant create folder: " + gistId);
		}

		FileObject gistFile = gistDir.getFileObject(file.getFilename());
		if (gistFile == null) {
			gistFile = createGistFile(gistDir, file);
		}

		return gistFile;

	}

	public void openGistFile(GistFile file, String gistId) {
		FileObject gistFile;
		try {
			gistFile = getGistFile(file, gistId);
			DataObject data;

			data = DataObject.find(gistFile);

			EditorCookie cookie = data.getCookie(EditorCookie.class);
			cookie.open();
		} catch (VirtualFileSystemException ex) {
			Exceptions.printStackTrace(ex);
		} catch (DataObjectNotFoundException ex) {
			Exceptions.printStackTrace(ex);
		}




	}
	/*create wirtual file
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
	}*/
}

class VirtualFileSystemException extends Exception {

	VirtualFileSystemException(String string) {
		super(string);
	}
}