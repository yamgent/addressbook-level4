package guitests;

import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.logging.Logger;

import org.testfx.api.FxRobot;

import javafx.stage.Stage;
import javafx.stage.Window;
import seedu.address.commons.core.LogsCenter;

/**
 * Robot used to simulate user actions on the GUI.
 * Extends {@link FxRobot} by adding some customized functionality and workarounds.
 */
public class GuiRobot extends FxRobot {

    public static final String PROPERTY_GUITESTS_HEADLESS = "guitests.headless";

    private boolean isHeadlessMode = false;

    private Logger logger = LogsCenter.getLogger(GuiRobot.class);

    public GuiRobot() {
        String headlessPropertyValue = System.getProperty(PROPERTY_GUITESTS_HEADLESS);
        isHeadlessMode = (headlessPropertyValue != null && headlessPropertyValue.equals("true"));
    }

    /**
     * Allows GUI tests to pause its execution for a human to examine the effects of the test.
     * This method should be disabled when a non-human is executing the GUI test to avoid
     * unnecessary delay.
     */
    public void pauseForHuman(int duration) {
        if (!isHeadlessMode) {
            sleep(duration);
        }
    }

    /**
     * Wait for event to be true.
     */
    public void waitForEvent(BooleanSupplier event, int timeout) {
        int timePassed = 0;
        int retryInterval = 10;

        while (event.getAsBoolean() != true) {
            logger.info("Sleep for " + retryInterval + "ms");
            sleep(retryInterval);
            timePassed += retryInterval;

            if (timePassed > timeout) {
                throw new AssertionError("Event timeout.");
            }

            logger.info("Failed, retrying...");
            retryInterval += retryInterval;
        }
    }

    /**
     * Checks that the window with {@code stageTitle} is currently open.
     */
    public boolean isWindowActive(String stageTitle) {
        Optional<Window> window = listTargetWindows()
                .stream()
                .filter(w -> w instanceof Stage && ((Stage) w).getTitle().equals(stageTitle)).findAny();

        return window.isPresent();
    }
}
