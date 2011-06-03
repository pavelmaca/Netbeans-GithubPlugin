package cz.inseo.netbeans.github.gist;

import javax.swing.Action;
import org.eclipse.egit.github.core.GistFile;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class FileNode extends AbstractNode {
	
	private GistFile file;
	
	/** Creates a new instance of FileNode */
    public FileNode(GistFile key) {
        super(Children.LEAF, Lookups.singleton(key));
        this.file = key;
        setDisplayName(key.getFilename());
        setIconBaseWithExtension("cz/inseo/netbeans/github/resources/images/paste.png");
		
    }
	
	public GistFile getFile(){
		return file;
	}
	
	public String getGistId(){
		return ((GistNode) getParentNode()).getGistId();
	}
    
	
	@Override
	 public boolean canCut() {
        
        return true;
    }
    
	@Override
    public boolean canDestroy() {
        return false;
    }

	@Override
	public Action getPreferredAction() {
		return new FilePreferredAction();
	}
		
}
