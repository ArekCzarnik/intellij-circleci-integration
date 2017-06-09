package com.bkv.intellij.circleci.ui;

import com.bkv.intellij.circleci.build.BuildInterface;
import com.bkv.intellij.circleci.client.CircleCiHttpClient;
import com.bkv.intellij.circleci.client.HttpClient;
import com.bkv.intellij.circleci.model.BuildListenerInterface;
import com.bkv.intellij.circleci.model.BuildsModel;
import com.bkv.intellij.circleci.ui.RecentBuildsTree.RecentBuildTreeCellRenderer;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class RecentBuildsToolWindowFactory implements ToolWindowFactory, BuildListenerInterface {

    private JTree tree1;
    private JPanel pnlMain;
    private DefaultMutableTreeNode rootNode;
    private BuildsModel builds;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(pnlMain, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(ToolWindow window) {
        builds = BuildsModel.getInstance();

        rootNode = new DefaultMutableTreeNode("CircleCI");

        TreeModel treeModel = new DefaultTreeModel(rootNode);
        tree1.setModel(treeModel);
        tree1.setCellRenderer(new RecentBuildTreeCellRenderer());

        builds.refresh();
    }

    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return false;
    }

    @Override
    public boolean isDoNotActivateOnStart() {
        return false;
    }

    @Override
    public void onBuildWasAdded(BuildInterface build) {
        rootNode.add(new DefaultMutableTreeNode(build));
        tree1.updateUI();
    }

    @Override
    public void onBuildStatusWasUpdated(BuildInterface build) {

    }
}
