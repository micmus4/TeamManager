package start;

import constants.account.SignInStageConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import stages.account.SignInStage;
import utils.AlertUtils;
import utils.StageUtils;

import java.io.IOException;
import java.net.URL;

public class Start extends Application {

    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void start( Stage signInStage )
    {
        signInStage = createSignInScene( signInStage );

        try
        {
            SignInStage.setSignInStage( signInStage );
            signInStage.show();
        }
        catch ( NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
        }
    }


    public Stage createSignInScene( Stage signInStage )  {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( SignInStageConstants.SIGN_IN_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( SignInStageConstants.SIGN_IN_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load(  urlToFXML );
        }
        catch ( IOException | NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            System.exit( 1 );
            return null;
        }

        return StageUtils.createStage( signInStage, "Sign in", false, root, urlToCSS,
                SignInStageConstants.SIGN_IN_STAGE_WIDTH,
                SignInStageConstants.SIGN_IN_STAGE_HEIGHT );
    }
}
