module Main {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires json.simple;


    opens start to javafx.fxml, json.simple;
    exports start;

    opens stages.help to javafx.fxml, json.simple;
    exports stages.help;

    opens stages.account to javafx.fxml, json.simple;
    exports stages.account;


}