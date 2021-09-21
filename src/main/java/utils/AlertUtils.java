package utils;

import javafx.scene.control.Alert;

public class AlertUtils {

    public static void popUpErrorAlert( Exception e )
    {
        e.printStackTrace();
        Alert alert = new Alert( Alert.AlertType.ERROR );

        alert.setTitle( "Error." );
        alert.setHeaderText( e.getClass().getCanonicalName() );
        alert.setContentText( e.getMessage() );
        alert.showAndWait();
    }
}
