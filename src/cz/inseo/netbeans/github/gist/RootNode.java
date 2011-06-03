package cz.inseo.netbeans.github.gist;

import java.awt.Image;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class RootNode extends AbstractNode {
	
	 /** Creates a new instance of RootNode */
    public RootNode(Children children) {
        super(children);
    }
    
	@Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("cz/inseo/netbeans/github/resources/images/g-icon.png");
    }
    
	@Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }
	
	@Override
	public Action[] getActions(boolean context) {
		return new Action[] { new RootAction() };
	}
}
