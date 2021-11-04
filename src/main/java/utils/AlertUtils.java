package utils;

import javafx.scene.control.Alert;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stages.help.HelpInformationStage;

public class AlertUtils
{

    private static final Logger LOGGER = LogManager.getLogger( AlertUtils.class );


    public static void popUpErrorAlert( Throwable e )
    {
        LOGGER.error( "Popping up error alert." );
        e.printStackTrace();
        Alert alert = new Alert( Alert.AlertType.ERROR );

        alert.setTitle( "Error." );
        alert.setHeaderText( e.getClass().getCanonicalName() );
        alert.setContentText( e.getMessage() );
        alert.showAndWait();
    }


    public static void popUpInfoAlert( String title, String header, String content )
    {
        LOGGER.info( "Popping up info alert." );
        Alert alert = new Alert( Alert.AlertType.INFORMATION );

        alert.setTitle( title );
        alert.setHeaderText( header );
        alert.setContentText( content );
        alert.showAndWait();
    }


    public static void popUpInfoAlert( String title, String header )
    {
        popUpInfoAlert( title, header, StringUtils.EMPTY );
    }
}
