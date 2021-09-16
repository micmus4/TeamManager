package Constants;

import java.nio.file.Path;
import java.nio.file.Paths;

import static Constants.ProjectsConstants.PROJECT_STRING_PATH;
import static Constants.ProjectsConstants.SEP;

public class HelpContactSceneConstants {

    public static String HELP_CONTACT_SCENE_FXML_NAME = "helpContactScene.fxml";

    // Relative path (starting from project's root directory) to parent directory of signInScene.fxml.
    private static final String HELP_CONTACT_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR =
            "src" + SEP + "main" + SEP + "resources" + SEP + "Scenes" + SEP + "Creators";

    // Absolute path to parent directory of signInScene.fxml file.
    public static final Path HELP_CONTACT_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR =
            Paths.get(PROJECT_STRING_PATH, HELP_CONTACT_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR);

    public static final Double HELP_CONTACT_SCENE_HEIGHT = 405.00D;
    public static final Double HELP_CONTACT_SCENE_WIDTH = 360.00D;



}
