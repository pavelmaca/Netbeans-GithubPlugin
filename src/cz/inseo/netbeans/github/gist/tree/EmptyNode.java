package cz.inseo.netbeans.github.gist.tree;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class EmptyNode extends DefaultMutableTreeNode {
	
	public EmptyNode(){
		super();
		setAllowsChildren(false);
	}
	
	@Override
	public String toString(){
		return "Loading data...";
	}
}
