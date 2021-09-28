package start;


import constants.SignInSceneConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class StartTest
{
    private static URL validURLToSignInSceneFXML;
    private static URL invalidURLToSignInSceneFXML;
    private static File fxmlFile;

    private static URL validURLToSignInSceneCSS;
    private static URL invalidURLToSignInSceneCSS;
    private static File cssFile;

    @BeforeAll
    public static void initialize()
    {
        validURLToSignInSceneFXML = Start.class.getResource( SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_FXML );
        invalidURLToSignInSceneFXML = Start.class.getResource( "Some random invalid path." );
        fxmlFile = new File( validURLToSignInSceneFXML.getFile() );

        validURLToSignInSceneCSS = Start.class.getResource( SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS );
        invalidURLToSignInSceneCSS = Start.class.getResource( "Some random invalid path." );
        cssFile = new File( validURLToSignInSceneCSS.getFile() );
    }



    @Test
    public void fxmlFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToSignInSceneFXML.getPath(),
                "Should throw NullPointerException, invalid path to " + SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME );

        assertDoesNotThrow( () -> validURLToSignInSceneFXML.getPath(),
                "Correct path to " + SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME + ", shouldn't throw any exceptions." );

        assertTrue( fxmlFile.exists(),
                SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME + " should exist" );

        assertFalse( fxmlFile.isDirectory(),
                SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME + " shouldn't be a directory.");

        assertTrue( fxmlFile.canRead(),
                SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME + " should be readable." );

        assertTrue( fxmlFile.canWrite(),
                SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME + " should be writeable." );

        assertTrue( fxmlFile.canExecute(),
                SignInSceneConstants.SIGN_IN_SCENE_FXML_NAME + " should be executable." );
    }

    @Test
    public void cssFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToSignInSceneCSS.getPath(),
                "Should throw NullPointerException, invalid path to " + SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS );

        assertDoesNotThrow( () -> validURLToSignInSceneCSS.getPath(),
                "Correct path to " + SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS + ", shouldn't throw any exceptions." );

        assertTrue( cssFile.exists(),
                SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS + " should exist" );

        assertFalse( cssFile.isDirectory(),
                SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS + " shouldn't be a directory.");

        assertTrue( cssFile.canRead(),
                SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS + " should be readable." );

        assertTrue( cssFile.canWrite(),
                SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS + " should be writeable." );

        assertTrue( cssFile.canExecute(),
                SignInSceneConstants.SIGN_IN_SCENE_RESOURCES_CSS + " should be executable." );
    }
}