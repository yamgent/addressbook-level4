package guitests;

import static guitests.GuiRobotUtil.EVENT_TIMEOUT;
import static guitests.GuiRobotUtil.MEDIUM_WAIT;
import static guitests.guihandles.HelpWindowHandle.HELP_WINDOW_TITLE;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import guitests.guihandles.HelpWindowHandle;
import javafx.scene.input.KeyCode;
import seedu.address.logic.commands.HelpCommand;

public class HelpWindowTest extends AddressBookGuiTest {

    @Test
    public void openHelpWindow() {
        // use accelerator
        getCommandBox().clickOnSelf();
        guiRobot.push(KeyCode.F1);
        assertHelpWindowOpen();

        mainWindowHandle.focusOnWindow();   // needed in headless mode, otherwise main window loses focus
        getResultDisplay().clickOnTextArea();
        guiRobot.push(KeyCode.F1);
        assertHelpWindowOpen();

        mainWindowHandle.focusOnWindow();
        getPersonListPanel().clickOnListView();
        guiRobot.push(KeyCode.F1);
        assertHelpWindowOpen();

        mainWindowHandle.focusOnWindow();
        getBrowserPanel().clickOnSelf();
        guiRobot.push(KeyCode.F1);
        assertHelpWindowNotOpen();

        // use menu button
        mainWindowHandle.focusOnWindow();
        getMainMenu().openHelpWindowUsingMenu();
        assertHelpWindowOpen();

        // use command box
        mainWindowHandle.focusOnWindow();
        getCommandBox().enterCommand(HelpCommand.COMMAND_WORD);
        assertHelpWindowOpen();
    }

    /**
     * Asserts that the help window is open, and closes it after we are done checking.
     */
    private void assertHelpWindowOpen() {
        guiRobot.waitForEvent(() -> guiRobot.isWindowActive(HELP_WINDOW_TITLE), EVENT_TIMEOUT);
        guiRobot.pauseForHuman(MEDIUM_WAIT);

        new HelpWindowHandle().closeWindow();
    }

    /**
     * Asserts that the help window isn't open at all.
     */
    private void assertHelpWindowNotOpen() {
        guiRobot.pauseForHuman(MEDIUM_WAIT);
        assertFalse(guiRobot.isWindowActive(HELP_WINDOW_TITLE));
    }

}
