package cz.inseo.netbeans.github.gist.tree;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class RootNode extends DefaultMutableTreeNode implements IIconNode{
	
	private static final ImageIcon ICON = new ImageIcon(RootNode.class.getResource("/cz/inseo/netbeans/github/resources/images/g-icon.png"));
	
	private String title;
	
	public RootNode(String title){
		super();
		this.title = title;
	}
	
	@Override
	public Icon getIcon(){
		return ICON;
	}
	
	@Override
	public String toString(){
		return title;
	}
}
