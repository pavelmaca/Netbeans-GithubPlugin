package cz.inseo.netbeans.github.gist;


import org.eclipse.egit.github.core.Gist;
import org.openide.nodes.Index;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class FileChildren extends Index.ArrayChildren {
	
	private Gist gist;
	
	public FileChildren(Gist gist) {
        this.gist = gist;
    }
}
