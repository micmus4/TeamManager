package start;

import constants.account.SignInConstants;
import constants.id.BeanIdConstants;
import constants.other.ProjectConstants;
import data.AccountManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ObjectFactory;
import stages.account.SignInStage;
import utils.AlertUtils;
import utils.StageUtils;

import java.io.IOException;
import java.net.URL;



public class Start extends Application {

    private static final Logger LOGGER = LogManager.getLogger( Start.class );

    private final ObjectFactory objectFactory = ObjectFactory.getFactory();

    private final AccountManager accountManager = (AccountManager)
            objectFactory.getBean( BeanIdConstants.ACCOUNT_MANAGER_SINGLETON );

    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void start( Stage signInStage )
    {
        accountManager.loadAccountsFromFile();
        signInStage = createSignInScene( signInStage );
        try
        {
            SignInStage.setSignInStage( signInStage );
            signInStage.show();
            LOGGER.info( "SignInStage shown on screen." );
        }
        catch ( NullPointerException e )
        {
            LOGGER.fatal( "SignInStage failed to show on screen." );
            AlertUtils.popUpErrorAlert( e );
        }
    }


    public Stage createSignInScene( Stage signInStage )
    {
        Parent root;
        URL urlToFXML;
        URL urlToCSS;

        try
        {
            urlToFXML = getClass().getResource( SignInConstants.SIGN_IN_STAGE_RESOURCES_FXML );
            urlToCSS = getClass().getResource( SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS );
            root = FXMLLoader.load(  urlToFXML );
            LOGGER.info( "SignInStage loaded." );
        }
        catch ( IOException | NullPointerException e )
        {
            LOGGER.fatal( e.getClass().getSimpleName() + " thrown while initializing SignInStage." );
            AlertUtils.popUpErrorAlert( e );
            System.exit( 1 );
            return null;
        }

        LOGGER.info( "Creating SignInStage." );
        return StageUtils.createStage( signInStage, "Sign in", false, root, urlToCSS,
                SignInConstants.SIGN_IN_STAGE_WIDTH,
                SignInConstants.SIGN_IN_STAGE_HEIGHT );
    }
}
