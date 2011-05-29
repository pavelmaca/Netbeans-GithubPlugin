package cz.inseo.netbeans.github.gist.tree;

import org.eclipse.egit.github.core.GistFile;


/**
 *
 * @author Pavel
 */
public class FileNode {
	
	private GistFile m_file;
	
	public FileNode(GistFile file) {
		m_file = file;
	}
	
	public GistFile getFile(){
		return m_file;
	}
	
	@Override
	public String toString() {
		return m_file.getFilename();
	}
	
}
