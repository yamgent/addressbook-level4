package guitests.guihandles;

import javafx.stage.Window;
import seedu.address.TestApp;

/**
 * Provides a handle for the main GUI.
 */
public class MainWindowHandle extends WindowHandle {

    private PersonListPanelHandle personListPanel;
    private ResultDisplayHandle resultDisplay;
    private CommandBoxHandle commandBox;
    private StatusBarFooterHandle statusBarFooter;
    private MainMenuHandle mainMenu;
    private BrowserPanelHandle browserPanel;

    public MainWindowHandle(Window window) {
        super(window);

        personListPanel = new PersonListPanelHandle();
        resultDisplay = new ResultDisplayHandle();
        commandBox = new CommandBoxHandle(TestApp.APP_TITLE);
        statusBarFooter = new StatusBarFooterHandle();
        mainMenu = new MainMenuHandle();
        browserPanel = new BrowserPanelHandle(this);
    }

    public PersonListPanelHandle getPersonListPanel() {
        return personListPanel;
    }

    public ResultDisplayHandle getResultDisplay() {
        return resultDisplay;
    }

    public CommandBoxHandle getCommandBox() {
        return commandBox;
    }

    public StatusBarFooterHandle getStatusBarFooter() {
        return statusBarFooter;
    }

    public MainMenuHandle getMainMenu() {
        return mainMenu;
    }

    public BrowserPanelHandle getBrowserPanel() {
        return browserPanel;
    }
}
