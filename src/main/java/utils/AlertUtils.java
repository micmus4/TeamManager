package utils;

import javafx.scene.control.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stages.help.HelpInformationStage;

public class AlertUtils
{

    private static final Logger LOGGER = LogManager.getLogger( AlertUtils.class );


    public static void popUpErrorAlert( Throwable e )
    {
        LOGGER.warn( "Popping up alert." );
        e.printStackTrace();
        Alert alert = new Alert( Alert.AlertType.ERROR );

        alert.setTitle( "Error." );
        alert.setHeaderText( e.getClass().getCanonicalName() );
        alert.setContentText( e.getMessage() );
        alert.showAndWait();
    }
}
