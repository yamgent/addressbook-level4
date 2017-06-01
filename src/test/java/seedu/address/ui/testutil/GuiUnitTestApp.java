package seedu.address.ui.testutil;

import static org.junit.Assert.fail;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.ui.CommandBoxTest;
import seedu.address.ui.UiPart;

/**
 * A dummy application for unit testing of a single GUI component.
 *
 * Instead of creating the entire GUI (like {@link seedu.address.TestApp}),
 * {@link GuiUnitTestApp} creates a blank window, and allows insertion of a
 * single UiPart. This allows the GUI part under test to be created without
 * creating the rest of the GUI.
 */
public class GuiUnitTestApp extends Application {

    private static final int DEFAULT_STAGE_WIDTH = 400;
    private static final int DEFAULT_STAGE_HEIGHT = 400;

    private static final String[] CSS_FILES = { "view/DarkTheme.css", "view/Extensions.css" };

    private Stage stage;

    @FXML
    private AnchorPane mainPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        // this.mainPane = new AnchorPane();

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/UiTestApp.fxml"));
        Scene scene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        //Scene scene = new Scene(mainPane, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        for (String cssFile : CSS_FILES) {
            scene.getStylesheets().add(cssFile);
        }
        stage.setScene(scene);
        stage.show();

        // mainPane.getStyleClass().add("anchor-pane-with-border");

        //TextField textField = new TextField();
        //textField.setId("commandTextField");
        //mainPane.getChildren().add(textField);
    }

    /**
     * Adds a new UI part that is being tested into the scene.
     *
     * This method blocks the main thread as it will have to wait
     * for the JavaFx's thread to add the UI part to the application UI,
     * otherwise the addition is not guaranteed to be completed when the
     * method exits.
     */
    public void addUiPart(UiPart<Region> part) throws InterruptedException {
        runAndWait(() -> mainPane.getChildren().add(part.getRoot()));
    }

    /**
     * Clears all UI parts added to the scene.
     *
     * This method blocks the main thread as it will have to wait
     * for the JavaFx's thread to clear the UI parts, otherwise the
     * clearing is not guaranteed to be completed when the method exits.
     */
    public void clearUiParts() throws InterruptedException {
        runAndWait(() -> mainPane.getChildren().clear());
    }

    /**
     * Runs the runnable on the JavaFx's thread, and wait for the
     * runnable to finish on that thread before we continue executing.
     */
    public void runAndWait(Runnable runnable) throws InterruptedException {
        synchronized (runnable) {
            Platform.runLater(() -> {
                synchronized (runnable) {
                    runnable.run();
                    runnable.notifyAll();
                }
            });

            runnable.wait();
        }
    }

    public void setStageWidth(int desiredWidth) {
        this.stage.setWidth(desiredWidth);
    }

    public void setStageHeight(int desiredHeight) {
        this.stage.setHeight(desiredHeight);
    }

    public Stage getStage() {
        return stage;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    /**
     * Creates a {@link GuiUnitTestApp} with the stage assigned to it.
     */
    public static GuiUnitTestApp spawnApp(Stage stage) {
        try {
            GuiUnitTestApp testApp = new GuiUnitTestApp();
            testApp.start(stage);
            return testApp;

        } catch (Exception e) {
            fail("Unable to launch application.");
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
