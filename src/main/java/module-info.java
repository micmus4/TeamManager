module Main {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;




    opens scenes to javafx.fxml;
    exports scenes;

    opens start to javafx.fxml;
    exports start;


}