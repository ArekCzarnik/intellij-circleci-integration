package com.bkv.intellij.circleci.build.ui;

import com.bkv.intellij.circleci.build.BuildInterface;
import com.bkv.intellij.circleci.build.model.BuildsModel;
import com.bkv.intellij.circleci.build.ui.RecentBuildsTree.RecentBuildTreeCellRenderer;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Timer;

public class RecentBuildsToolWindowFactory implements ToolWindowFactory {

    private final String FIELD_NONE = "none";
    private final String FIELD_COMMITTER = "getCommitterName";
    private final String FIELD_STATUS = "getStatus";

    private String groupField = FIELD_NONE;
    private JTree tree1;
    private JPanel pnlMain;
    private JButton refreshButton;
    private DefaultMutableTreeNode rootNode;
    private BuildsModel builds;
    private Timer refreshTimer;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(pnlMain, "", false);
        toolWindow.getContentManager().addContent(content);
        toolWindow.setIcon(new ImageIcon(getClass().getResource("/circleci/circleci.png").getPath()));
        refreshButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refresh();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void init(ToolWindow window) {
        builds = BuildsModel.getInstance();
        rootNode = new DefaultMutableTreeNode("CircleCI");
        TreeModel treeModel = new DefaultTreeModel(rootNode);
        tree1.setModel(treeModel);
        tree1.setShowsRootHandles(true);
        tree1.setCellRenderer(new RecentBuildTreeCellRenderer());
        PropertiesComponent component = PropertiesComponent.getInstance();
        Integer refreshInterval;
        try {
            refreshInterval = new Integer(component.getValue("com.bkv.intellij.circleci.refresh_interval"));
        } catch (Exception e) {
            e.printStackTrace();
            refreshInterval = new Integer(99999999);
        }
        enableAutoRefresh(refreshInterval);
        refresh();
    }

    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return false;
    }

    @Override
    public boolean isDoNotActivateOnStart() {
        return false;
    }

    private DefaultMutableTreeNode getRootTreeNodeByText(TreeNode node, String text) {
        Enumeration<DefaultMutableTreeNode> children = node.children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode childNode = children.nextElement();
            if (childNode.getUserObject().equals(text)) {
                return childNode;
            }
        }
        return null;
    }

    public void enableAutoRefresh(int seconds)
    {
        refreshTimer = new Timer("Builds refresh", true);
        refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        }, seconds * 1000, seconds * 1000);
    }

    public void disableAutoRefresh()
    {
        refreshTimer.cancel();
    }

    public void refresh() {
        Set<Integer> expanded = new HashSet<>(tree1.getRowCount());
        for (int i = 0; i < tree1.getRowCount(); i++) {
            if (tree1.isExpanded(i)) {
                expanded.add(i);
            }
        }
        this.builds.refresh();
        List<BuildInterface> recentBuilds = this.builds.getRecentBuilds();
        rootNode.removeAllChildren();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) tree1.getModel().getRoot();

        if (groupField != FIELD_NONE) {
            Set<String> uniqueValues = getUniqueFieldValues(recentBuilds, groupField);
            for (String value : uniqueValues) {
                DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(value);
                rootNode.add(groupNode);
            }
        }
        for (BuildInterface build: recentBuilds) {
            if (groupField != FIELD_NONE) {
                try {
                    String value = (String) build.getClass().getMethod(groupField).invoke(build);
                    DefaultMutableTreeNode node = getRootTreeNodeByText(rootNode, value);
                    node.add(new DefaultMutableTreeNode(build));
                } catch (IllegalAccessException e) {

                } catch (InvocationTargetException e) {

                } catch (NoSuchMethodException e) {

                }
            } else {
                rootNode.add(new DefaultMutableTreeNode(build));
            }
        }
        tree1.updateUI();
        for (int i = 0; i < tree1.getRowCount(); i++) {
            if (expanded.contains(i)) {
                tree1.expandRow(i);
            }
        }
    }

    private Set<String> getUniqueFieldValues(List<BuildInterface> builds, String field) {
        Set<String> result = new HashSet<String>(64);
        for (BuildInterface build: builds) {
            try {
                String value = (String) build.getClass().getMethod(field).invoke(build);
                result.add(value);
            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            } catch (NoSuchMethodException e) {

            }
        }
        return result;
    }
}
