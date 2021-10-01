package utils;

import constants.ProjectConstants;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class StageUtils
{

    public static void setProjectVersionLabel ( Label versionLabel )
    {
        versionLabel.setText( ProjectConstants.VERSION_INFO );
    }


    public static Stage createStage ( String title, Boolean isResizable, Parent root,
                                     URL cssFile, Double width, Double height )
    {
        Stage newStage = new Stage();
        newStage.getIcons().add( transformPathToImageToImageInstance( ProjectConstants.ICON_RESOURCES_FXML ) );
        newStage.setTitle( title );
        newStage.setResizable( isResizable );
        Scene newScene = new Scene( root, width, height );

        newScene.getStylesheets().clear();
        newScene.getStylesheets().add( cssFile.toExternalForm() );

        newStage.setScene( newScene );
        return newStage;
    }


    public static Stage createStage ( Stage newStage, String title, Boolean isResizable, Parent root,
                                     URL cssFile, Double width, Double height )
    {
        newStage.getIcons().add( transformPathToImageToImageInstance( ProjectConstants.ICON_RESOURCES_FXML ) );
        newStage.setTitle( title );
        newStage.setResizable( isResizable );
        Scene newScene = new Scene( root, width, height );

        newScene.getStylesheets().clear();
        newScene.getStylesheets().add( cssFile.toExternalForm() );

        newStage.setScene( newScene );
        return newStage;
    }


    private static Image transformPathToImageToImageInstance( String stringPathToImage )
    {
        URL urlToImage = StageUtils.class.getResource( stringPathToImage );
        if( urlToImage == null )
        {
            return null;
        }
        return new Image( urlToImage.toString() );
    }


    public static boolean isStageShown( Stage childStage, Stage parentStage, Modality modality )
    {
        try
        {
            if ( childStage == null )
            {
                throw new NullPointerException();
            }
        }
        catch ( NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return false;
        }

        childStage.setX( parentStage.getX() + 25 );
        childStage.setY( parentStage.getY() + 25 );

        childStage.initOwner( parentStage );
        childStage.initModality( modality );
        childStage.show();
        return true;
    }


}
