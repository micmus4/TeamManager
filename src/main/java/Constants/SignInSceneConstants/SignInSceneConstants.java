package Constants.SignInSceneConstants;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SignInSceneConstants {

    // Correct file separator for operating system flexibility.
    private static final String separator = File.separator;



    // String path to project's root directory.
    public static final String SIGN_IN_SCENE_PROJECT_STRING_PATH = System.getProperty("user.dir");

    // Relative path (starting from project's root directory) to parent directory of signInScene.fxml.
    public static final String SIGN_IN_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR =
            "src" + separator + "main" + separator + "resources" + separator + "Scenes";

    // FXML file containing code about look and behaviour of SignInScene.
    public static final String SIGN_IN_SCENE_STRING_PATH = "signInScene.fxml";

    // Absolute path to parent directory of signInScene.fxml file.
    public static final Path SIGN_IN_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR =
            Paths.get(SIGN_IN_SCENE_PROJECT_STRING_PATH, SIGN_IN_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR);
}
