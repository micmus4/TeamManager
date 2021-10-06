package start;


import constants.account.SignInConstants;
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
        validURLToSignInSceneFXML = Start.class.getResource( SignInConstants.SIGN_IN_STAGE_RESOURCES_FXML);
        invalidURLToSignInSceneFXML = Start.class.getResource( "Some random invalid path." );
        fxmlFile = new File( validURLToSignInSceneFXML.getFile() );

        validURLToSignInSceneCSS = Start.class.getResource( SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS);
        invalidURLToSignInSceneCSS = Start.class.getResource( "Some random invalid path." );
        cssFile = new File( validURLToSignInSceneCSS.getFile() );
    }



    @Test
    public void fxmlFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToSignInSceneFXML.getPath(),
                "Should throw NullPointerException, invalid path to " + SignInConstants.SIGN_IN_STAGE_FXML_NAME);

        assertDoesNotThrow( () -> validURLToSignInSceneFXML.getPath(),
                "Correct path to " + SignInConstants.SIGN_IN_STAGE_FXML_NAME + ", shouldn't throw any exceptions." );

        assertTrue( fxmlFile.exists(),
                SignInConstants.SIGN_IN_STAGE_FXML_NAME + " should exist" );

        assertFalse( fxmlFile.isDirectory(),
                SignInConstants.SIGN_IN_STAGE_FXML_NAME + " shouldn't be a directory.");

        assertTrue( fxmlFile.canRead(),
                SignInConstants.SIGN_IN_STAGE_FXML_NAME + " should be readable." );

        assertTrue( fxmlFile.canWrite(),
                SignInConstants.SIGN_IN_STAGE_FXML_NAME + " should be writeable." );

        assertTrue( fxmlFile.canExecute(),
                SignInConstants.SIGN_IN_STAGE_FXML_NAME + " should be executable." );
    }

    @Test
    public void cssFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToSignInSceneCSS.getPath(),
                "Should throw NullPointerException, invalid path to " + SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS);

        assertDoesNotThrow( () -> validURLToSignInSceneCSS.getPath(),
                "Correct path to " + SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS + ", shouldn't throw any exceptions." );

        assertTrue( cssFile.exists(),
                SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS + " should exist" );

        assertFalse( cssFile.isDirectory(),
                SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS + " shouldn't be a directory.");

        assertTrue( cssFile.canRead(),
                SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS + " should be readable." );

        assertTrue( cssFile.canWrite(),
                SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS + " should be writeable." );

        assertTrue( cssFile.canExecute(),
                SignInConstants.SIGN_IN_STAGE_RESOURCES_CSS + " should be executable." );
    }
}