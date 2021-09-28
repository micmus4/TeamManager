module Main {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;




    opens stages to javafx.fxml;
    exports stages;

    opens start to javafx.fxml;
    exports start;


}