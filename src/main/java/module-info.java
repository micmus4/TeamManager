module Main {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires json.simple;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.commons.lang3;


    opens start to javafx.fxml, json.simple;
    exports start;

    opens stages.help to javafx.fxml, json.simple;

    opens stages.account to javafx.fxml, json.simple;


}