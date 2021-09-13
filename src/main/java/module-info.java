module Main {
    requires javafx.controls;
    requires javafx.fxml;

    exports Scenes.Controllers;
    opens Scenes.Controllers to javafx.fxml;
    exports Scenes.Creators;
    opens Scenes.Creators to javafx.fxml;
}