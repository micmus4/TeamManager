package Constants;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;



public class HelpContactSceneConstants {

    public static String HELP_CONTACT_SCENE_FXML_NAME = "helpContactScene.fxml";

    // Relative path (starting from project's root directory) to parent directory of signInScene.fxml.
    private static final String HELP_CONTACT_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR =
            "src" + ProjectConstants.SEP + "main" + ProjectConstants.SEP +
                    "resources" + ProjectConstants.SEP + "FXML";

    // Absolute path to parent directory of signInScene.fxml file.
    public static final Path HELP_CONTACT_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR =
            Paths.get( ProjectConstants.PROJECT_STRING_PATH, HELP_CONTACT_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR );

    public static final String HELP_CONTACT_SCENE_ABSOLUTE_STRING_PATH_TO_FXML = ProjectConstants.SEP +
            Paths.get( HELP_CONTACT_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR.toString(), HELP_CONTACT_SCENE_FXML_NAME );

    public static final Double HELP_CONTACT_SCENE_HEIGHT = 405.00D;
    public static final Double HELP_CONTACT_SCENE_WIDTH = 360.00D;




    public static final String HELP_CONTACT_SCENE_CSS_NAME = "helpContactSceneStyle.css";

    private static final File HELP_CONTACT_SCENE_CSS_FILE = new File(ProjectConstants.PROJECT_STRING_PATH + ProjectConstants.SEP + "src" +
                             ProjectConstants.SEP + "main" + ProjectConstants.SEP + "resources" +
                             ProjectConstants.SEP + "CSS" + ProjectConstants.SEP + HELP_CONTACT_SCENE_CSS_NAME );

    public static String HELP_CONTACT_SCENE_CSS_ABSOLUTE_STRING_PATH = "file:///" + HELP_CONTACT_SCENE_CSS_FILE.getAbsolutePath().replace( "\\", "/" );




}
