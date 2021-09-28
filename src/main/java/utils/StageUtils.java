package utils;

import constants.ProjectConstants;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;

public class StageUtils
{

    public static void setProjectVersionLabel ( Label versionLabel )
    {
        versionLabel.setText( ProjectConstants.VERSION_INFO );
    }

    public static Stage createStage ( String title, Boolean isResizable, Parent root,
                                     URL cssFile, Double height, Double width )
    {
        Stage newStage = new Stage();
        newStage.getIcons().add( transformPathToImageToImageInstance( ProjectConstants.ICON_RESOURCES_FXML ) );
        newStage.setTitle( title );
        newStage.setResizable( isResizable );
        Scene newScene = new Scene( root, height, width );

        newScene.getStylesheets().clear();
        newScene.getStylesheets().add( cssFile.toExternalForm() );

        newStage.setScene( newScene );
        return newStage;
    }

    public static Stage createStage ( Stage newStage, String title, Boolean isResizable, Parent root,
                                     URL cssFile, Double height, Double width )
    {
        newStage.getIcons().add( transformPathToImageToImageInstance( ProjectConstants.ICON_RESOURCES_FXML ) );
        newStage.setTitle( title );
        newStage.setResizable( isResizable );
        Scene newScene = new Scene( root, height, width );

        newScene.getStylesheets().clear();
        newScene.getStylesheets().add( cssFile.toExternalForm() );

        newStage.setScene( newScene );
        return newStage;
    }

    private static Image transformPathToImageToImageInstance( String stringPathToImage )
    {
        URL urlToImage = StageUtils.class.getResource( stringPathToImage );
        return new Image( urlToImage.toString() );
    }
}
