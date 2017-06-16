package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface Icons {
        Icon PLUGIN_ICON = IconLoader.getIcon("/cci-logo.ico");
        Icon BUILD_SUCCESS_ICON = IconLoader.getIcon("/green_ball.ico");
        Icon BUILD_FAILURE_ICON = IconLoader.getIcon("/red_ball.ico");
        Icon BUILD_NOTRUN_ICON = IconLoader.getIcon("/grey_ball.ico");
        Icon BUILD_RUNNING_ICON = IconLoader.getIcon("/white_ball.ico");
}
