package Constants;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;



public class SignInSceneConstants {

    // Relative path (starting from project's root directory) to parent directory of signInScene.fxml.
    private static final String SIGN_IN_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR =
                    "src" + ProjectConstants.SEP + "main" + ProjectConstants.SEP + "resources" + ProjectConstants.SEP + "FXML";

    // FXML file containing code about look and behaviour of SignInScene.
    public static final String SIGN_IN_SCENE_FXML_NAME = "signInScene.fxml";

    // Absolute path to parent directory of signInScene.fxml file.
    public static final Path SIGN_IN_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR =
            Paths.get( ProjectConstants.PROJECT_STRING_PATH, SIGN_IN_SCENE_RELATIVE_STRING_PATH_TO_FXML_PARENT_DIR );


    public static final String SIGN_IN_SCENE_ABSOLUTE_STRING_PATH_TO_FXML = ProjectConstants.SEP +
            Paths.get( SIGN_IN_SCENE_ABSOLUTE_PATH_TO_FXML_PARENT_DIR.toString(), SIGN_IN_SCENE_FXML_NAME );

    // Height and width for Sign In Scene.
    public static final Double SIGN_IN_SCENE_HEIGHT = 405.00D;
    public static final Double SIGN_IN_SCENE_WIDTH = 360.00D;




    public static final String SIGN_IN_SCENE_CSS_NAME = "signInSceneStyle.css";

    private static final File SIGN_IN_SCENE_CSS_FILE = new File( ProjectConstants.PROJECT_STRING_PATH + ProjectConstants.SEP + "src" +
                                    ProjectConstants.SEP + "main" + ProjectConstants.SEP + "resources" +
                                    ProjectConstants.SEP + "CSS" + ProjectConstants.SEP + SIGN_IN_SCENE_CSS_NAME );

    public static String SIGN_IN_SCENE_CSS_ABSOLUTE_STRING_PATH = "file:///" + SIGN_IN_SCENE_CSS_FILE.getAbsolutePath().replace("\\", "/" );




}
