package seedu.address.ui;

import guitests.GuiRobot;
import guitests.guihandles.CommandBoxHandle;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.XmlAddressBookStorage;
import seedu.address.ui.testutil.GuiUnitTestApp;
import seedu.address.ui.testutil.GuiUnitTestApplicationRule;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandBoxTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Rule
    public GuiUnitTestApplicationRule applicationRule = new GuiUnitTestApplicationRule();

    private CommandBox commandBoxUI;
    private CommandBoxHandle commandBox;

    private ArrayList<String> defaultStyleOfCommandBox;
    private ArrayList<String> errorStyleOfCommandBox;

    private String getTempFilePath(String fileName) {
        return testFolder.getRoot().getPath() + fileName;
    }

    private static final String COMMAND_THAT_SUCCEEDS = "list";
    private static final String COMMAND_THAT_FAILS = "invalid command";

    @Before
    public void setUp() throws Exception {
        GuiUnitTestApp testApp = applicationRule.getTestApp();

        XmlAddressBookStorage addressBookStorage = new XmlAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));

        Model model = new ModelManager();
        Storage storage = new StorageManager(addressBookStorage, userPrefsStorage);
        Logic logic = new LogicManager(model, storage);

        testApp.setStageWidth(500);
        testApp.setStageHeight(50);

        testApp.runAndWait(() -> {
            commandBoxUI = new CommandBox(testApp.getMainPane(), logic);
        });
        testApp.addUiPart(commandBoxUI);
        commandBox = new CommandBoxHandle(new GuiRobot(), testApp.getStage(), testApp.getStage().getTitle());

        defaultStyleOfCommandBox = new ArrayList<>(commandBox.getStyleClass());

        // build style class for error
        errorStyleOfCommandBox = new ArrayList<>(defaultStyleOfCommandBox);
        errorStyleOfCommandBox.add(CommandBox.ERROR_STYLE_CLASS);
    }

    @Test
    public void commandBox_shouldNotStartWithError() {
        assertFalse("CommandBox default style classes should not contain error style class.",
                defaultStyleOfCommandBox.contains(CommandBox.ERROR_STYLE_CLASS));
    }


    @Test
    public void commandBox_startingWithSuccessfulCommand() {
        assertBehaviorForSuccessfulCommand();
        assertBehaviorForFailedCommand();
    }

    @Test
    public void commandBox_startingWithFailedCommand() {
        assertBehaviorForFailedCommand();
        assertBehaviorForSuccessfulCommand();

        // verify that style is changed correctly even after multiple consecutive successful/failed commands
        assertBehaviorForSuccessfulCommand();
        assertBehaviorForFailedCommand();
        assertBehaviorForFailedCommand();
        assertBehaviorForSuccessfulCommand();
    }

    /**
     * Runs a command that fails, then verifies that
     * - the return value of runCommand(...) is false,
     * - the text remains,
     * - the command box has only one ERROR_STYLE_CLASS, with other style classes untouched.
     */
    private void assertBehaviorForFailedCommand() {
        assertFalse(commandBox.runCommand(COMMAND_THAT_FAILS));
        assertEquals(COMMAND_THAT_FAILS, commandBox.getCommandInput());
        assertEquals(errorStyleOfCommandBox, commandBox.getStyleClass());
    }

    /**
     * Runs a command that succeeds, then verifies that
     * - the return value of runCommand(...) is true,
     * - the text is cleared,
     * - the command box does not have any ERROR_STYLE_CLASS, with style classes the same as default.
     */
    private void assertBehaviorForSuccessfulCommand() {
        assertTrue(commandBox.runCommand(COMMAND_THAT_SUCCEEDS));
        assertEquals("", commandBox.getCommandInput());
        assertEquals(defaultStyleOfCommandBox, commandBox.getStyleClass());
    }
}
