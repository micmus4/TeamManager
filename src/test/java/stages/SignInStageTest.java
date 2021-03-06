package stages;

import constants.help.HelpContactConstants;
import constants.help.HelpInformationConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import start.Start;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;



class SignInStageTest
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
        validURLToHelpContactSceneFXML = Start.class.getResource( HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_FXML);
        invalidURLToHelpContactSceneFXML = Start.class.getResource( "Some random invalid path." );
        helpContactFXMLFile = new File( validURLToHelpContactSceneFXML.getFile() );

        validURLToHelpContactSceneCSS = Start.class.getResource( HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_CSS);
        invalidURLToHelpContactSceneCSS = Start.class.getResource( "Some random invalid path." );
        helpContactCSSFile = new File( validURLToHelpContactSceneCSS.getFile() );

        validURLToHelpInformationSceneFXML = Start.class.getResource( HelpInformationConstants.HELP_INFORMATION_STAGE_RESOURCES_FXML);
        invalidURLToHelpInformationSceneFXML = Start.class.getResource( "Some random invalid path." );
        helpInformationFXMLFile = new File( validURLToHelpInformationSceneFXML.getFile() );

        validURLToHelpInformationSceneCSS = Start.class.getResource( HelpInformationConstants.HELP_INFORMATION_STAGE_RESOURCES_CSS);
        invalidURLToHelpInformationSceneCSS = Start.class.getResource( "Some random invalid path." );
        helpInformationCSSFile = new File( validURLToHelpInformationSceneCSS.getFile() );
    }


    @Test
    public void helpContactSceneFXMLFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpContactSceneFXML.getPath(),
                "Should throw NullPointerException, invalid path to " + HelpContactConstants.HELP_CONTACT_STAGE_FXML_NAME);

        assertDoesNotThrow( () -> validURLToHelpContactSceneFXML.getPath(),
                "Correct path to " + HelpContactConstants.HELP_CONTACT_STAGE_FXML_NAME + ", shouldn't throw any exceptions." );

        assertTrue( helpContactFXMLFile.exists(),
                HelpContactConstants.HELP_CONTACT_STAGE_FXML_NAME + " should exist" );

        assertFalse( helpContactFXMLFile.isDirectory(),
                HelpContactConstants.HELP_CONTACT_STAGE_FXML_NAME + " shouldn't be a directory.");

        assertTrue( helpContactFXMLFile.canRead(),
                HelpContactConstants.HELP_CONTACT_STAGE_FXML_NAME + " should be readable." );

        assertTrue( helpContactFXMLFile.canWrite(),
                HelpContactConstants.HELP_CONTACT_STAGE_FXML_NAME + " should be writeable." );

        assertTrue( helpContactFXMLFile.canExecute(),
                HelpContactConstants.HELP_CONTACT_STAGE_FXML_NAME + " should be executable." );
    }

    @Test
    public void helpContactSceneCSSFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpContactSceneCSS.getPath(),
                "Should throw NullPointerException, invalid path to " + HelpContactConstants.HELP_CONTACT_STAGE_CSS_NAME);

        assertDoesNotThrow( () -> validURLToHelpContactSceneCSS.getPath(),
                "Correct path to " + HelpContactConstants.HELP_CONTACT_STAGE_CSS_NAME + ", shouldn't throw any exceptions." );

        assertTrue( helpContactCSSFile.exists(),
                HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_CSS + " should exist" );

        assertFalse( helpContactCSSFile.isDirectory(),
                HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_CSS + " shouldn't be a directory.");

        assertTrue( helpContactCSSFile.canRead(),
                HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_CSS + " should be readable." );

        assertTrue( helpContactCSSFile.canWrite(),
                HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_CSS + " should be writeable." );

        assertTrue( helpContactCSSFile.canExecute(),
                HelpContactConstants.HELP_CONTACT_STAGE_RESOURCES_CSS + " should be executable." );
    }

    @Test
    public void helpInformationSceneFXMLFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpInformationSceneFXML.getPath(),
                "Should throw NullPointerException, invalid path to " +
                        HelpInformationConstants.HELP_INFORMATION_STAGE_FXML_NAME);

        assertDoesNotThrow( () -> validURLToHelpInformationSceneFXML.getPath(),
                "Correct path to " + HelpInformationConstants.HELP_INFORMATION_STAGE_FXML_NAME
                        + ", shouldn't throw any exceptions." );

        assertTrue( helpInformationFXMLFile.exists(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_FXML_NAME + " should exist" );

        assertFalse( helpInformationFXMLFile.isDirectory(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_FXML_NAME + " shouldn't be a directory.");

        assertTrue( helpInformationFXMLFile.canRead(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_FXML_NAME + " should be readable." );

        assertTrue( helpInformationFXMLFile.canWrite(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_FXML_NAME + " should be writeable." );

        assertTrue( helpInformationFXMLFile.canExecute(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_FXML_NAME + " should be executable." );
    }

    @Test
    public void helpInformationSceneCSSFileCorrectness()
    {
        assertThrows( NullPointerException.class, () -> invalidURLToHelpInformationSceneCSS.getPath(),
                "Should throw NullPointerException, invalid path to " +
                        HelpInformationConstants.HELP_INFORMATION_STAGE_CSS_NAME);

        assertDoesNotThrow( () -> validURLToHelpInformationSceneCSS.getPath(),
                "Correct path to " + HelpInformationConstants.HELP_INFORMATION_STAGE_CSS_NAME
                        + ", shouldn't throw any exceptions." );

        assertTrue( helpInformationCSSFile.exists(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_CSS_NAME + " should exist" );

        assertFalse( helpInformationCSSFile.isDirectory(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_CSS_NAME + " shouldn't be a directory.");

        assertTrue( helpInformationCSSFile.canRead(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_CSS_NAME + " should be readable." );

        assertTrue( helpInformationCSSFile.canWrite(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_CSS_NAME + " should be writeable." );

        assertTrue( helpInformationCSSFile.canExecute(),
                HelpInformationConstants.HELP_INFORMATION_STAGE_CSS_NAME + " should be executable." );
    }
    
}