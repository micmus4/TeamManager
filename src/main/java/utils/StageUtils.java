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
     * @param aVersionLabel Label containing text about current version of project in every Stage's bottom-right corner.
     */

    public static void setProjectVersionLabel ( Label aVersionLabel )
    {
        LOGGER.info( "Setting project version label" );
        aVersionLabel.setText( ProjectConstants.VERSION_INFO );
    }


    /**
     * Creates a new Stage which is modeled by user's parameters.
     *
     * @param aTitle String value which is displayed in top-left corner of created Stage.
     * @param aIsResizable boolean value which allows/disallows resizing opened Stage.
     * @param aRoot Parent parameter which has loaded .fxml file that has defined Stage's structure.
     * @param aCssFile URL's value with path to css style which serves as a stylesheet for Stage.
     * @param aWidth Double value, Stage's width.
     * @param aHeight Double value, Stage's height.
     *
     * @return Stage instance created according to user's parameters.
     */

    public static Stage createStage ( String aTitle, Boolean aIsResizable, Parent aRoot,
                                      URL aCssFile, Double aWidth, Double aHeight )
    {
        Stage newStage = new Stage();
        return createStage( newStage, aTitle, aIsResizable, aRoot, aCssFile, aWidth, aHeight );
    }


    /**
     * Creates a new Stage which is modeled by user's parameters. The only difference from upwards method is that
     * the Stage instance is already created by default and has to be modeled.
     *
     * @see StageUtils#createStage(String, Boolean, Parent, URL, Double, Double)
     *
     * @param aNewStage Stage instance which will be modified and returned.
     * @param aTitle String value which is displayed in top-left corner of created Stage.
     * @param aIsResizable boolean value which allows/disallows resizing opened Stage.
     * @param aRoot Parent parameter which has loaded .fxml file that has defined Stage's structure.
     * @param aCssFile URL's value with path to css style which serves as a stylesheet for Stage.
     * @param aWidth Double value, Stage's width.
     * @param aHeight Double value, Stage's height.
     *
     * @return Stage instance created according to user's parameters.
     */

    public static Stage createStage ( Stage aNewStage, String aTitle, Boolean aIsResizable, Parent aRoot,
                                     URL aCssFile, Double aWidth, Double aHeight )
    {
        LOGGER.info( "Creating new stage." );
        aNewStage.getIcons().add( transformPathToImageToImageInstance( ImageConstants.ICON ) );
        aNewStage.setTitle( aTitle );
        aNewStage.setResizable( aIsResizable );

        Scene newScene = new Scene( aRoot, aWidth, aHeight );
        setStageStylesheets( newScene, aCssFile );
        aNewStage.setScene( newScene );
        return aNewStage;
    }


    /**
     * A method which accepts String parameter which serves as path to image file and creates Image instance from it.
     *
     * @param aStringPathToImage String value, path to image file.
     * @return
     *  - Image instance containing path to image file, if URL created from stringPathToImage is correct
     *  or
     *  - null value, if URL created from stringPathToImage is invalid and doesn't point to image file.
     */

    public static Image transformPathToImageToImageInstance( String aStringPathToImage )
    {
        URL urlToImage = StageUtils.class.getResource( aStringPathToImage );
        if( urlToImage == null )
        {
            LOGGER.warn( "Couldn't create URL from " + aStringPathToImage, " there will be no image." );
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
     * @param aChildStage Stage instance, which is a Stage that is supposed to be shown on the screen if it isn't null.
     * @param aParentStage Stage instance, it has functionality to open childStage and therefore serves as its parent.
     * @param aModality Modality value, describes relation between childStage and parentStage. @see javafx.stage.Modality
     * @param aIsStageOnScreenProperty SimpleBooleanProperty instance, this parameter is used to make sure that Stage
     *                                  can not show on the screen more than once.
     *
     * @return
     * - true, if childStage wasn't null and was shown on screen.
     * - false, if childStage was null.
     */

    public static boolean showStageAndCheckIfItsShown( Stage aChildStage, Stage aParentStage, Modality aModality,
                                                      SimpleBooleanProperty aIsStageOnScreenProperty, boolean aMoveStageFlag )
    {
        if( !checkIfStageIsNotNull( aChildStage ) )
        {
            return false;
        }

        if( aMoveStageFlag )
        {
            aChildStage.setX( aParentStage.getX() + 25 );
            aChildStage.setY( aParentStage.getY() + 25 );
        }

        aChildStage.initOwner( aParentStage );
        aChildStage.initModality( aModality );
        aChildStage.show();
        createListenerForAvoidingDuplicateStages( aChildStage, aIsStageOnScreenProperty );
        return true;
    }


    public static void showStageAndCheckIfItsShown( Stage aChildStage, Stage aParentStage, boolean aHideParentStageFlag,
                                                   boolean aMoveStageFlag, Modality aModality,
                                                   SimpleBooleanProperty aIsStageOnScreenProperty )
    {
        if( !checkIfStageIsNotNull( aChildStage ) )
        {
            return;
        }

        if( aMoveStageFlag )
        {
            aChildStage.setX( aParentStage.getX() + 25 );
            aChildStage.setY( aParentStage.getY() + 25 );
        }

        aChildStage.initOwner( aParentStage );
        if ( aHideParentStageFlag )
        {
            aParentStage.hide();
        }
        aChildStage.initModality( aModality );
        aChildStage.show();
        createListenerForAvoidingDuplicateStages( aChildStage, aIsStageOnScreenProperty );
    }


    /**
     * A method which sets stage's SimpleBooleanProperty object to false when it's closed, so we can open the screen again
     * after we close it. It is created as we don't want to have duplicate stages of the same instance on the screen.
     *
     * @see StageUtils#showStageAndCheckIfItsShown(Stage, Stage, Modality, SimpleBooleanProperty, boolean) 
     *
     * @param aStage Stage instance, which we don't want to be duplicated on the screen.
     * @param aIsStageOnScreen SimpleBooleanProperty instance, which tells us whether the Stage is displayed on the screen.
     */

    private static void createListenerForAvoidingDuplicateStages( Stage aStage, SimpleBooleanProperty aIsStageOnScreen )
    {
        aStage.setOnCloseRequest( wE ->
        {
            aIsStageOnScreen.set( false );
            LOGGER.info( "Closing " + aStage.getTitle() + " Stage." );
        });
    }


    public static void closeStage( Stage aStage, SimpleBooleanProperty aIsStageOnScreen )
    {
        aIsStageOnScreen.set( false );
        aStage.close();
        LOGGER.info( "Closing " + aStage.getTitle() + " Stage." );
    }


    public static void setImage( ImageView aImageView, String aPathToImage )
    {
        aImageView.setImage( transformPathToImageToImageInstance( aPathToImage ) );
    }


    public static void setImageTooltip( String aMessage, ImageView aImageView )
    {
        Tooltip tooltip = new Tooltip();
        tooltip.setText( aMessage );
        aImageView.setPickOnBounds( true );
        Tooltip.install( aImageView, tooltip );
    }


    public static void setTooltipForCorrectInput( ImageView aStatus, String aPropertyName )
    {
        setImageTooltip( aPropertyName + " is acceptable.", aStatus );
    }


    private static boolean checkIfStageIsNotNull( Stage aStage )
    {
        try
        {
            if ( aStage == null )
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


    private static void setStageStylesheets( Scene aScene, URL aCss )
    {
        aScene.getStylesheets().clear();
        aScene.getStylesheets().add( aCss.toExternalForm() );
        aScene.getStylesheets().add( getVersionLabelStylesheet().toExternalForm() );
    }

    public static void loadCountriesToComboBox(ComboBox< String > aCountryComboBox )
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
        aCountryComboBox.setItems( FXCollections.observableList( countriesArrayList ) );
    }


    public static void loadLeaguesToComboBox( ComboBox< String > aLeagueComboBox )
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
            aLeagueComboBox.setItems( leagues );

        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception thrown while parsing PROPERTIES." );
            AlertUtils.popUpErrorAlert( e );
        }

    }
}
