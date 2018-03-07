package seedu.address.logic.commands;

import seedu.address.model.person.HideAllPersonPredicate;


/**
 * Locks the app with a password
 * */
public class LockCommand extends Command {

    public static final String COMMAND_WORD = "lock";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lock the address book. ";

    public static final String MESSAGE_SUCCESS = "Address book has been locked!";

    private static String password;

    private final HideAllPersonPredicate predicate = new HideAllPersonPredicate();

    public LockCommand() {
        this.password = null;
    }

    public LockCommand(String keyword) {
        this.password = keyword;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static String getPassword() {
        return password;
    }
}