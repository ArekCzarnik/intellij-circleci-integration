package com.bkv.intellij.circleci.ui;

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
        return new ImageIcon(getClass().getResource("/circleci/circleci.png"));
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
        propertiesComponent.setValue("com.gheevel.circleci.api_key", txtApiKey.getText());
        propertiesComponent.setValue("com.gheevel.circleci.refresh_interval", txtRefreshInterval.getText());
        changed = false;
    }

    public void reset() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        String apiKey = propertiesComponent.getValue("com.gheevel.circleci.api_key", "Your circleci api key found under account settings");
        String refreshInterval = propertiesComponent.getValue("com.gheevel.circleci.refresh_interval", "30");

        txtApiKey.setText(apiKey);
        txtRefreshInterval.setText(refreshInterval);
        changed = false;
    }

    public void disposeUIResources() {

    }
}
