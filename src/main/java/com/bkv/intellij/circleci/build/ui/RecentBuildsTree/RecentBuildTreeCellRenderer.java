package com.bkv.intellij.circleci.build.ui.RecentBuildsTree;

import com.bkv.intellij.circleci.build.BuildInterface;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class RecentBuildTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if (o instanceof BuildInterface) {
            BuildInterface build = (BuildInterface) o;
            setText("[" + build.getStatus() + "] #" + build.getBuildNumber() + ' ' + build.getSubject());
            setToolTipText("By " + build.getCommitterName() + " on " + build.getCommitterDate() + "\nVCS Revision was " + build.getVcsRevision());
            setIcon(getIcon(build));
        }
        return c;
    }

    private ImageIcon getIcon(BuildInterface build) {
        ImageIcon icon;
        switch (build.getStatus()) {
            default:
                icon = new ImageIcon(getClass().getResource("/circleci/008-error.png").getPath());
        }
        return icon;
    }
}
