package cz.inseo.netbeans.github.gist.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class GistsTreeModel extends DefaultTreeModel{

	GistsTreeModel(DefaultMutableTreeNode node) {
		super(node);
	}
}
