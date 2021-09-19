package Utils;

import javafx.scene.control.Alert;

public class AlertUtils {

    public static void popUpErrorAlert( String headerText, String contentText ){
        Alert alert = new Alert( Alert.AlertType.ERROR );

        alert.setTitle( "Error." );
        alert.setHeaderText( headerText );
        alert.setContentText( contentText );
        alert.show();
    }
}
