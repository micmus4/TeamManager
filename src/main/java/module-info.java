module Main {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires json.simple;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;


    opens start to javafx.fxml, json.simple;
    exports start;

    opens stages.help to javafx.fxml, json.simple;
    exports stages.help;

    opens stages.account to javafx.fxml, json.simple;
    exports stages.account;


}