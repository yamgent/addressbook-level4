package seedu.address.logic.commands;


import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class UnlockCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        UnlockCommand firstUnlockCommand = new UnlockCommand("nopassword");
        UnlockCommand secondUnlockCommand = new UnlockCommand("12345");

        // same object -> returns true
        assertTrue(firstUnlockCommand.equals(firstUnlockCommand));

        // same values -> returns true
        UnlockCommand secondUnlockCommandcopy = new UnlockCommand("12345");
        assertTrue(secondUnlockCommand.equals(secondUnlockCommandcopy));

        // different types -> returns false
        assertFalse(firstUnlockCommand.equals(1));

        // null -> returns false
        assertFalse(firstUnlockCommand.equals(null));

        // different value -> returns false
        assertFalse(firstUnlockCommand.equals(secondUnlockCommand));
    }

    @Test
    public void unlockSuccess() {

        UnlockCommand testUnlockCommand = new UnlockCommand("1234");
        testUnlockCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        String expectedMessage = UnlockCommand.MESSAGE_SUCCESS;
        CommandResult commandResult = testUnlockCommand.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
    }



}
