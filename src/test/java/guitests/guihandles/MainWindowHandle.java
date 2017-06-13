package guitests.guihandles;

import javafx.stage.Window;

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

        personListPanel = new PersonListPanelHandle(this);
        resultDisplay = new ResultDisplayHandle(this);
        commandBox = new CommandBoxHandle(this);
        statusBarFooter = new StatusBarFooterHandle(this);
        mainMenu = new MainMenuHandle(this);
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
