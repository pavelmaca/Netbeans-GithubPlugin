package cz.inseo.netbeans.github.gist.tree;

import cz.inseo.netbeans.github.tools.InfoDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author Pavel Máca <maca.pavel@gmail.com>
 */
public class IconCellRenderer extends JLabel implements TreeCellRenderer {

	protected Color m_textSelectionColor;
	protected Color m_textNonSelectionColor;
	protected Color m_bkSelectionColor;
	protected Color m_bkNonSelectionColor;
	protected Color m_borderSelectionColor;
	protected boolean m_selected;

	public IconCellRenderer() {
		super();
		m_textSelectionColor = UIManager.getColor(
				"Tree.selectionForeground");
		m_textNonSelectionColor = UIManager.getColor(
				"Tree.textForeground");
		m_bkSelectionColor = UIManager.getColor(
				"Tree.selectionBackground");
		m_bkNonSelectionColor = UIManager.getColor(
				"Tree.textBackground");
		m_borderSelectionColor = UIManager.getColor(
				"Tree.selectionBorderColor");
		setOpaque(false);
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		setFont(tree.getFont());
		setForeground(selected ? m_textSelectionColor
				: m_textNonSelectionColor);
		setBackground(selected ? m_bkSelectionColor
				: m_bkNonSelectionColor);
		m_selected = selected;

		if(value instanceof IIconNode){
			Icon icon;
			/*
			if (value instanceof RootNode) {
				RootNode node = (RootNode) value;
				icon = node.getIcon();
			} else if (value instanceof GistNode) {
				GistNode node = (GistNode) value;
				icon = node.getIcon();
			    addMouseListener(new GistsMouseListener());
			} else if (value instanceof FileNode) {
				FileNode node = (FileNode) value;
				icon = node.getIcon();
			}*/

			IIconNode node = (IIconNode) value;
			icon = node.getIcon();
			setIcon(icon);
			setIconTextGap(7);
		}
		
		setText(value.toString());
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		Color bColor = getBackground();
		Icon icon = getIcon();

		g.setColor(bColor);
		int offset = 0;
		if (icon != null && getText() != null) {
			offset = (icon.getIconWidth() + getIconTextGap());
		}
		g.fillRect(offset, 0, getWidth() - 1 - offset,
				getHeight() - 1);

		if (m_selected) {
			g.setColor(m_borderSelectionColor);
			g.drawRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);
		}
		super.paintComponent(g);
	}
}
