package Constants;

import java.nio.file.Path;
import java.nio.file.Paths;

import static Constants.ProjectsConstants.PROJECT_STRING_PATH;
import static Constants.ProjectsConstants.SEP;

public class SignInSceneConstants {

    // Relative path (starting from project's root directory) to parent directory of signInScene.fxml.
    private static final String SIGN_IN_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR =
            "src" + SEP + "main" + SEP + "resources" + SEP + "Scenes" + SEP + "Controllers";

    // FXML file containing code about look and behaviour of SignInScene.
    public static final String SIGN_IN_SCENE_FXML_NAME = "signInScene.fxml";

    // Absolute path to parent directory of signInScene.fxml file.
    public static final Path SIGN_IN_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR =
            Paths.get(PROJECT_STRING_PATH, SIGN_IN_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR);


    // Height and width for Sign In Scene.
    public static final Double SIGN_IN_SCENE_HEIGHT = 405.00D;
    public static final Double SIGN_IN_SCENE_WIDTH = 360.00D;
}
