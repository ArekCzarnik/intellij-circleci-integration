package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface Icons {
        Icon PLUGIN_ICON = new ImageIcon(Icons.class.getResource("/cci-logo.png").getPath());
        Icon BUILD_SUCCESS_ICON = new ImageIcon(Icons.class.getResource("/green_ball.png").getPath());
        Icon BUILD_FAILURE_ICON = new ImageIcon(Icons.class.getResource("/red_ball.png").getPath());
        Icon BUILD_NOTRUN_ICON = new ImageIcon(Icons.class.getResource("/grey_ball.png").getPath());
        Icon BUILD_RUNNING_ICON = new ImageIcon(Icons.class.getResource("/white_ball.png").getPath());
}
