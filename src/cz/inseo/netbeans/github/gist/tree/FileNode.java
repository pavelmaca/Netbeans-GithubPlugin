package cz.inseo.netbeans.github.gist.tree;

import com.github.api.v2.schema.File;



/**
 *
 * @author Pavel
 */
public class FileNode {
	
	private File m_file;
	
	public FileNode(File file) {
		m_file = file;
	}
	
	public File getFile(){
		return m_file;
	}
	
	@Override
	public String toString() {
		return m_file.getFilename();
	}
	
}
