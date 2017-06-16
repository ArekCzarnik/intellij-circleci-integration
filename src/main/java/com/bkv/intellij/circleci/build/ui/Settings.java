package com.bkv.intellij.circleci.build.ui;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Settings implements Configurable {
    public static final String DISPLAY_NAME = "CircleCI";
    private boolean changed = false;
    private JPanel pnlMain;
    private JLabel lblApiKey;
    private JTextField txtApiKey;
    private JLabel lblRefreshInterval;
    private JTextField txtRefreshInterval;

    @Nls
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    public Icon getIcon() {
        return new ImageIcon(getClass().getResource("/icons/cci-logo.png"));
    }

    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        KeyListener changeTracker = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                changed = true;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                changed = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        txtApiKey.addKeyListener(changeTracker);
        txtRefreshInterval.addKeyListener(changeTracker);
        return pnlMain;
    }

    public boolean isModified() {
        return changed;
    }

    public void apply() throws ConfigurationException {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue("com.bkv.intellij.icons.api_key", txtApiKey.getText());
        propertiesComponent.setValue("com.bkv.intellij.icons.refresh_interval", txtRefreshInterval.getText());
        changed = false;

        BuildsModel.resetInstance();
    }

    public void reset() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        String apiKey = propertiesComponent.getValue("com.bkv.intellij.icons.api_key", "Your icons api key found under account settings");
        String refreshInterval = propertiesComponent.getValue("com.bkv.intellij.icons.refresh_interval", "30");

        txtApiKey.setText(apiKey);
        txtRefreshInterval.setText(refreshInterval);
        changed = false;
    }

    public void disposeUIResources() {

    }
}
