package scenes;

import constants.HelpContactSceneConstants;
import constants.HelpInformationSceneConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import start.Start;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;



class SignInSceneTest
{


    private static URL validURLToHelpContactSceneFXML;
    private static URL invalidURLToHelpContactSceneFXML;
    private static File helpContactFXMLFile;

    private static URL validURLToHelpContactSceneCSS;
    private static URL invalidURLToHelpContactSceneCSS;
    private static File helpContactCSSFile;

    private static URL validURLToHelpInformationSceneFXML;
    private static URL invalidURLToHelpInformationSceneFXML;
    private static File helpInformationFXMLFile;

    private static URL validURLToHelpInformationSceneCSS;
    private static URL invalidURLToHelpInformationSceneCSS;
    private static File helpInformationCSSFile;
    
    

    @BeforeAll
    public static void initialize()
    {
        validURLToHelpContactSceneFXML = Start.class.getResource( HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_FXML );
        invalidURLToHelpContactSceneFXML = Start.class.getResource( "Some random invalid path." );
        helpContactFXMLFile = new File( validURLToHelpContactSceneFXML.getFile() );

        validURLToHelpContactSceneCSS = Start.class.getResource( HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS );
        invalidURLToHelpContactSceneCSS = Start.class.getResource( "Some random invalid path." );
        helpContactCSSFile = new File( validURLToHelpContactSceneCSS.getFile() );

        validURLToHelpInformationSceneFXML = Start.class.getResource( HelpInformationSceneConstants.HELP_INFORMATION_SCENE_RESOURCES_FXML );
        invalidURLToHelpInformationSceneFXML = Start.class.getResource( "Some random invalid path." );
        helpInformationFXMLFile = new File( validURLToHelpInformationSceneFXML.getFile() );

        validURLToHelpInformationSceneCSS = Start.class.getResource( HelpInformationSceneConstants.HELP_INFORMATION_SCENE_RESOURCES_CSS );
        invalidURLToHelpInformationSceneCSS = Start.class.getResource( "Some random invalid path." );
        helpInformationCSSFile = new File( validURLToHelpInformationSceneCSS.getFile() );
    }


    @Test
    public void helpContactSceneFXMLFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpContactSceneFXML.getPath(),
                "Should throw NullPointerException, invalid path to " + HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME );

        assertDoesNotThrow( () -> validURLToHelpContactSceneFXML.getPath(),
                "Correct path to " + HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + ", shouldn't throw any exceptions." );

        assertTrue( helpContactFXMLFile.exists(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " should exist" );

        assertFalse( helpContactFXMLFile.isDirectory(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " shouldn't be a directory.");

        assertTrue( helpContactFXMLFile.canRead(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " should be readable." );

        assertTrue( helpContactFXMLFile.canWrite(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " should be writeable." );

        assertTrue( helpContactFXMLFile.canExecute(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_FXML_NAME + " should be executable." );
    }

    @Test
    public void helpContactSceneCSSFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpContactSceneCSS.getPath(),
                "Should throw NullPointerException, invalid path to " + HelpContactSceneConstants.HELP_CONTACT_SCENE_CSS_NAME );

        assertDoesNotThrow( () -> validURLToHelpContactSceneCSS.getPath(),
                "Correct path to " + HelpContactSceneConstants.HELP_CONTACT_SCENE_CSS_NAME + ", shouldn't throw any exceptions." );

        assertTrue( helpContactCSSFile.exists(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " should exist" );

        assertFalse( helpContactCSSFile.isDirectory(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " shouldn't be a directory.");

        assertTrue( helpContactCSSFile.canRead(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " should be readable." );

        assertTrue( helpContactCSSFile.canWrite(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " should be writeable." );

        assertTrue( helpContactCSSFile.canExecute(),
                HelpContactSceneConstants.HELP_CONTACT_SCENE_RESOURCES_CSS + " should be executable." );
    }

    @Test
    public void helpInformationSceneFXMLFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpInformationSceneFXML.getPath(),
                "Should throw NullPointerException, invalid path to " +
                        HelpInformationSceneConstants.HELP_INFORMATION_SCENE_FXML_NAME );

        assertDoesNotThrow( () -> validURLToHelpInformationSceneFXML.getPath(),
                "Correct path to " + HelpInformationSceneConstants.HELP_INFORMATION_SCENE_FXML_NAME
                        + ", shouldn't throw any exceptions." );

        assertTrue( helpInformationFXMLFile.exists(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_FXML_NAME + " should exist" );

        assertFalse( helpInformationFXMLFile.isDirectory(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_FXML_NAME + " shouldn't be a directory.");

        assertTrue( helpInformationFXMLFile.canRead(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_FXML_NAME + " should be readable." );

        assertTrue( helpInformationFXMLFile.canWrite(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_FXML_NAME + " should be writeable." );

        assertTrue( helpInformationFXMLFile.canExecute(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_FXML_NAME + " should be executable." );
    }

    @Test
    public void helpInformationSceneCSSFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpInformationSceneCSS.getPath(),
                "Should throw NullPointerException, invalid path to " +
                        HelpInformationSceneConstants.HELP_INFORMATION_SCENE_CSS_NAME );

        assertDoesNotThrow( () -> validURLToHelpInformationSceneCSS.getPath(),
                "Correct path to " + HelpInformationSceneConstants.HELP_INFORMATION_SCENE_CSS_NAME
                        + ", shouldn't throw any exceptions." );

        assertTrue( helpInformationCSSFile.exists(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_CSS_NAME + " should exist" );

        assertFalse( helpInformationCSSFile.isDirectory(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_CSS_NAME + " shouldn't be a directory.");

        assertTrue( helpInformationCSSFile.canRead(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_CSS_NAME + " should be readable." );

        assertTrue( helpInformationCSSFile.canWrite(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_CSS_NAME + " should be writeable." );

        assertTrue( helpInformationCSSFile.canExecute(),
                HelpInformationSceneConstants.HELP_INFORMATION_SCENE_CSS_NAME + " should be executable." );
    }
    
}