package scenes;

import constants.HelpContactSceneConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import start.Start;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;



class SignInSceneTest {

    private static URL validURLToHelpContactSceneFXML;
    private static URL invalidURLToHelpContactSceneFXML;
    private static File fxmlFile;

    private static URL validURLToHelpContactSceneCSS;
    private static URL invalidURLToHelpContactSceneCSS;
    private static File cssFile;

    @BeforeAll
    public static void initialize()
    {
        validURLToHelpContactSceneFXML = Start.class.getResource( HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_FXML );
        invalidURLToHelpContactSceneFXML = Start.class.getResource( "Some random invalid path." );
        fxmlFile = new File( validURLToHelpContactSceneFXML.getFile() );

        validURLToHelpContactSceneCSS = Start.class.getResource( HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS );
        invalidURLToHelpContactSceneCSS = Start.class.getResource( "Some random invalid path." );
        cssFile = new File( validURLToHelpContactSceneCSS.getFile() );
    }


    @Test
    public void isPathToHelpContactSceneFXMLFileCorrect()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpContactSceneFXML.getPath(),
                "Should throw NullPointerException, invalid path to " + HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME );

        assertDoesNotThrow( () -> validURLToHelpContactSceneFXML.getPath(),
                "Correct path to " + HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + ", shouldn't throw any exceptions." );

        assertTrue( fxmlFile.exists(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " should exist" );

        assertFalse( fxmlFile.isDirectory(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " shouldn't be a directory.");

        assertTrue( fxmlFile.canRead(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " should be readable." );

        assertTrue( fxmlFile.canWrite(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " should be writeable." );

        assertTrue( fxmlFile.canExecute(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " should be executable." );
    }

    @Test
    public void isPathToHelpContactSceneCSSFileCorrect()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpContactSceneCSS.getPath(),
                "Should throw NullPointerException, invalid path to " + HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS );

        assertDoesNotThrow( () -> validURLToHelpContactSceneCSS.getPath(),
                "Correct path to " + HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + ", shouldn't throw any exceptions." );

        assertTrue( cssFile.exists(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " should exist" );

        assertFalse( cssFile.isDirectory(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " shouldn't be a directory.");

        assertTrue( cssFile.canRead(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " should be readable." );

        assertTrue( cssFile.canWrite(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " should be writeable." );

        assertTrue( cssFile.canExecute(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " should be executable." );
    }
    
}