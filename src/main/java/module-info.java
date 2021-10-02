module Main {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens start to javafx.fxml;
    exports start;

    opens stages.help to javafx.fxml;
    exports stages.help;

    opens stages.account to javafx.fxml;
    exports stages.account;


}