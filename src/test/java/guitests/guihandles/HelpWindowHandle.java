package guitests.guihandles;

import java.util.logging.Logger;

import guitests.GuiRobot;
import seedu.address.commons.core.LogsCenter;

/**
 * Provides a handle to the help window of the app.
 */
public class HelpWindowHandle extends WindowHandle {

    public static final String HELP_WINDOW_TITLE = "Help";
    private static final String HELP_WINDOW_ROOT_FIELD_ID = "#helpWindowRoot";

    private Logger logger = LogsCenter.getLogger(HelpWindowHandle.class);

    public HelpWindowHandle() {
        super(new GuiRobot().window(HELP_WINDOW_TITLE));
        guiRobot.pauseForHuman(1000);
    }

    public void closeWindow() {
        logger.info("Closing window!");
        super.closeWindow();
        guiRobot.pauseForHuman(500);
    }

}
