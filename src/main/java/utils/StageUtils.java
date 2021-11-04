package utils;

import constants.other.ProjectConstants;
import constants.other.ImageConstants;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;


public class StageUtils
{

    private static final Logger LOGGER = LogManager.getLogger( StageUtils.class );


    /**
     * Sets every Stage's bottom-right corner Label to display text about current project version.
     *
     * @param versionLabel Label containing text about current version of project in every Stage's bottom-right corner.
     */

    public static void setProjectVersionLabel ( Label versionLabel )
    {
        LOGGER.info( "Setting project version label" );
        versionLabel.setText( ProjectConstants.VERSION_INFO );
    }


    /**
     * Creates a new Stage which is modeled by user's parameters.
     *
     * @param title String value which is displayed in top-left corner of created Stage.
     * @param isResizable boolean value which allows/disallows resizing opened Stage.
     * @param root Parent parameter which has loaded .fxml file that has defined Stage's structure.
     * @param cssFile URL's value with path to css style which serves as a stylesheet for Stage.
     * @param width Double value, Stage's width.
     * @param height Double value, Stage's height.
     *
     * @return Stage instance created according to user's parameters.
     */

    public static Stage createStage ( String title, Boolean isResizable, Parent root,
                                     URL cssFile, Double width, Double height )
    {
        Stage newStage = new Stage();
        return createStage( newStage, title, isResizable, root, cssFile, width, height );
    }


    /**
     * Creates a new Stage which is modeled by user's parameters. The only difference from upwards method is that
     * the Stage instance is already created by default and has to be modeled.
     *
     * @see StageUtils#createStage(String, Boolean, Parent, URL, Double, Double)
     *
     * @param newStage Stage instance which will be modified and returned.
     * @param title String value which is displayed in top-left corner of created Stage.
     * @param isResizable boolean value which allows/disallows resizing opened Stage.
     * @param root Parent parameter which has loaded .fxml file that has defined Stage's structure.
     * @param cssFile URL's value with path to css style which serves as a stylesheet for Stage.
     * @param width Double value, Stage's width.
     * @param height Double value, Stage's height.
     *
     * @return Stage instance created according to user's parameters.
     */

    public static Stage createStage ( Stage newStage, String title, Boolean isResizable, Parent root,
                                     URL cssFile, Double width, Double height )
    {
        LOGGER.info( "Creating new stage." );
        newStage.getIcons().add( transformPathToImageToImageInstance( ImageConstants.ICON ) );
        newStage.setTitle( title );
        newStage.setResizable( isResizable );

        Scene newScene = new Scene( root, width, height );
        setStageStylesheets( newScene, cssFile );
        newStage.setScene( newScene );
        return newStage;
    }


    /**
     * A method which accepts String parameter which serves as path to image file and creates Image instance from it.
     *
     * @param stringPathToImage String value, path to image file.
     * @return
     *  - Image instance containing path to image file, if URL created from stringPathToImage is correct
     *  or
     *  - null value, if URL created from stringPathToImage is invalid and doesn't point to image file.
     */

    public static Image transformPathToImageToImageInstance( String stringPathToImage )
    {
        URL urlToImage = StageUtils.class.getResource( stringPathToImage );
        if( urlToImage == null )
        {
            LOGGER.warn( "Couldn't create URL from " + stringPathToImage, " there will be no image." );
            return null;
        }
        return new Image( urlToImage.toString() );
    }


    /**
     * A method which indicates whether Stage was shown on the screen. If childStage parameter wasn't null, it sets its
     * owner to parentStage, opens the childStage 25px to the bottom-right from parentStage and creates a listener to avoid
     * duplicated childStage on the screen.
     *
     * @see StageUtils#createListenerForAvoidingDuplicateStages(Stage, SimpleBooleanProperty)
     *
     * @param childStage Stage instance, which is a Stage that is supposed to be shown on the screen if it isn't null.
     * @param parentStage Stage instance, it has functionality to open childStage and therefore serves as its parent.
     * @param modality Modality value, describes relation between childStage and parentStage. @see javafx.stage.Modality
     * @param isStageOnScreenProperty SimpleBooleanProperty instance, this parameter is used to make sure that Stage
     *                                  can not show on the screen more than once.
     *
     * @return
     * - true, if childStage wasn't null and was shown on screen.
     * - false, if childStage was null.
     */

    public static boolean showStageAndCheckIfItsShown( Stage childStage, Stage parentStage, Modality modality,
                                                      SimpleBooleanProperty isStageOnScreenProperty, boolean moveStageFlag )
    {
        if( !checkIfStageIsNotNull( childStage ) )
        {
            return false;
        }

        if( moveStageFlag )
        {
            childStage.setX( parentStage.getX() + 25 );
            childStage.setY( parentStage.getY() + 25 );
        }

        childStage.initOwner( parentStage );
        childStage.initModality( modality );
        childStage.show();
        createListenerForAvoidingDuplicateStages( childStage, isStageOnScreenProperty );
        return true;
    }


    public static void showStageAndCheckIfItsShown(Stage childStage, Stage parentStage, boolean hideParentStageFlag,
                                                   boolean moveStageFlag, Modality modality,
                                                   SimpleBooleanProperty isStageOnScreenProperty )
    {
        if( !checkIfStageIsNotNull( childStage ) )
        {
            return;
        }

        if( moveStageFlag )
        {
            childStage.setX( parentStage.getX() + 25 );
            childStage.setY( parentStage.getY() + 25 );
        }

        childStage.initOwner( parentStage );
        if ( hideParentStageFlag )
        {
            parentStage.hide();
        }
        childStage.initModality( modality );
        childStage.show();
        createListenerForAvoidingDuplicateStages( childStage, isStageOnScreenProperty );
    }


    /**
     * A method which sets stage's SimpleBooleanProperty object to false when it's closed, so we can open the screen again
     * after we close it. It is created as we don't want to have duplicate stages of the same instance on the screen.
     *
     * @see StageUtils#showStageAndCheckIfItsShown(Stage, Stage, Modality, SimpleBooleanProperty, boolean) 
     *
     * @param stage Stage instance, which we don't want to be duplicated on the screen.
     * @param isStageOnScreen SimpleBooleanProperty instance, which tells us whether the Stage is displayed on the screen.
     */

    private static void createListenerForAvoidingDuplicateStages( Stage stage, SimpleBooleanProperty isStageOnScreen )
    {
        stage.setOnCloseRequest( wE ->
        {
            isStageOnScreen.set( false );
            LOGGER.info( "Closing " + stage.getTitle() + " Stage." );
        });
    }


    public static void closeStage( Stage stage, SimpleBooleanProperty isStageOnScreen )
    {
        isStageOnScreen.set( false );
        stage.close();
        LOGGER.info( "Closing " + stage.getTitle() + " Stage." );
    }


    public static void setImage( ImageView imageView, String pathToImage )
    {
        imageView.setImage( transformPathToImageToImageInstance( pathToImage ) );
    }


    public static void setImageTooltip( String message, ImageView imageView )
    {
        Tooltip tooltip = new Tooltip();
        tooltip.setText( message );
        imageView.setPickOnBounds( true );
        Tooltip.install( imageView, tooltip );
    }


    public static void setTooltipForCorrectInput( ImageView status, String propertyName )
    {
        setImageTooltip( propertyName + " is acceptable.", status );
    }


    private static boolean checkIfStageIsNotNull( Stage stage )
    {
        try
        {
            if ( stage == null )
            {
                LOGGER.error( "Failed to created stage" );
                throw new NullPointerException();
            }
        }
        catch ( NullPointerException e )
        {
            AlertUtils.popUpErrorAlert( e );
            return false;
        }
        return true;
    }


    private static URL getVersionLabelStylesheet()
    {
        return StageUtils.class.getResource( ProjectConstants.RELATIVE_PATH_TO_VERSION_CSS_FILE );
    }


    private static void setStageStylesheets( Scene scene, URL css )
    {
        scene.getStylesheets().clear();
        scene.getStylesheets().add( css.toExternalForm() );
        scene.getStylesheets().add( getVersionLabelStylesheet().toExternalForm() );
    }

    public static void loadCountriesToComboBox(ComboBox< String > countryComboBox )
    {
        LOGGER.info( "Parsing countries data from JSON." );
        JSONParser parser = new JSONParser();
        URL urlToJSONFile = StageUtils.class.getResource( ProjectConstants.RELATIVE_PATH_TO_COUNTRIES_JSON_FILE );

        try
        {
            if ( urlToJSONFile == null )
            {
                LOGGER.error( "No JSON file found." );
                throw new NullPointerException();
            }
        }
        catch ( Exception e )
        {
            AlertUtils.popUpErrorAlert( e );
            return;
        }


        ArrayList<String> countriesArrayList = new ArrayList<>();
        try( FileReader reader = new FileReader( urlToJSONFile.getPath() ) )
        {
            LOGGER.info( "Reading countries from JSON." );
            Object parsedJson = parser.parse( reader );
            JSONArray jsonDataArray = (JSONArray) parsedJson;

            jsonDataArray.forEach( country -> {
                String name = (String) ((JSONObject) country).get( "name" );
                countriesArrayList.add( name );
            } );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception thrown while parsing JSON." );
            AlertUtils.popUpErrorAlert( e );
            return;
        }

        LOGGER.info( "Setting countries for ComboBox" );
        countryComboBox.setItems( FXCollections.observableList( countriesArrayList ) );
    }


    public static void loadLeaguesToComboBox( ComboBox< String > leagueComboBox )
    {
        LOGGER.info( "Parsing countries data from PROPERTIES." );
        try( InputStream input = StageUtils.class.getResourceAsStream( ProjectConstants.RELATIVE_PATH_TO_LEAGUES_PROPERTIES_FILE ) )
        {
            Properties properties = new Properties();

            if( input == null )
            {
                throw new IOException( "No data read from " + ProjectConstants.LEAGUES_PROPERTIES_NAME + " file." );
            }

            properties.load( input );
            ObservableList< String > leagues = FXCollections.observableArrayList();
            properties.values()
                    .forEach( league -> leagues.add( (String)league ) );

            LOGGER.info( "Setting leagues for ComboBox" );
            leagueComboBox.setItems( leagues );

        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception thrown while parsing PROPERTIES." );
            AlertUtils.popUpErrorAlert( e );
        }

    }
}
