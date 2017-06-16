package com.bkv.intellij.circleci.build.ui.RecentBuildsTree;

import com.bkv.intellij.circleci.build.BuildInterface;
import icons.Icons;

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
            setText("#" + build.getBuildNumber() + ' ' + build.getSubject());
            setToolTipText("By " + build.getCommitterName() + " on " + build.getCommitterDate() + "\nVCS Revision was " + build.getVcsRevision());
            try {
                setIcon(getIcon(build));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    private Icon getIcon(BuildInterface build) {
        Icon icon;
        switch (build.getStatus()) {
            case "fixed":
            case "success":
                icon = Icons.BUILD_SUCCESS_ICON;
            break;
            case "running":
                icon = Icons.BUILD_RUNNING_ICON;
            break;
            case "failed":
                icon = Icons.BUILD_FAILURE_ICON;
            break;
            default:
                icon = Icons.BUILD_NOTRUN_ICON;
            break;
        }
        return icon;
    }
}
