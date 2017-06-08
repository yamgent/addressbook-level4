package guitests;

import static guitests.guihandles.HelpWindowHandle.HELP_WINDOW_TITLE;
import static org.junit.Assert.assertFalse;

import java.util.logging.Logger;

import org.junit.Test;

import guitests.guihandles.HelpWindowHandle;
import seedu.address.commons.core.LogsCenter;

public class HelpWindowTest extends AddressBookGuiTest {

    private Logger logger = LogsCenter.getLogger(HelpWindowTest.class);

    @Test
    public void openHelpWindow() {
        //use accelerator
        /*
        mainWindowHandle.getCommandBox().click();
        mainWindowHandle.getMainMenu().openHelpWindowUsingAccelerator();
        assertHelpWindowOpen();
        */

        mainWindowHandle.getResultDisplay().click();
        mainWindowHandle.getMainMenu().openHelpWindowUsingAccelerator();
        assertHelpWindowOpen();

        mainWindowHandle.getPersonListPanel().click();
        mainWindowHandle.getMainMenu().openHelpWindowUsingAccelerator();
        assertHelpWindowOpen();

        mainWindowHandle.getBrowserPanel().click();
        mainWindowHandle.getMainMenu().openHelpWindowUsingAccelerator();
        assertHelpWindowNotOpen();

        //use menu button
        mainWindowHandle.getMainMenu().openHelpWindowUsingMenu();
        assertHelpWindowOpen();

        //use command
        mainWindowHandle.getCommandBox().runHelpCommand();
        assertHelpWindowOpen();
    }

    private void assertHelpWindowOpen() {
        GuiRobot guiRobot = new GuiRobot();

        int eventWaitTimeout = 5000;
        logger.info("Wait for opening");
        guiRobot.waitForEvent(() -> guiRobot.lookup("#helpWindowRoot").tryQuery().isPresent(), eventWaitTimeout);
        new HelpWindowHandle().closeWindow();
        logger.info("Wait for closing");
        guiRobot.waitForEvent(() -> !(guiRobot.lookup("#helpWindowRoot").tryQuery().isPresent()), eventWaitTimeout);
    }

    private void assertHelpWindowNotOpen() {
        GuiRobot guiRobot = new GuiRobot();
        assertFalse(guiRobot.isWindowActive(HELP_WINDOW_TITLE));
    }

}
