package constants;



public class SignInStageConstants
{


    /**
     * Height and width for SignInScene.
     */

    public static final String SIGN_IN_SCENE_FXML_NAME = "signInStage.fxml";
    public static final String SIGN_IN_SCENE_CSS_NAME = "signInSceneStage.css";



    /**
     * Names of FXML/CSS files for SignInScene.
     */

    public static final Double SIGN_IN_SCENE_HEIGHT = 405.00D;
    public static final Double SIGN_IN_SCENE_WIDTH = 360.00D;



    /**
     * Relative paths (from resources dir) to FXML/CSS files for SignInScene.
     */

    public static String SIGN_IN_SCENE_RESOURCES_FXML = ( ProjectConstants.SEP + "fxml" + ProjectConstants.SEP +
            SIGN_IN_SCENE_FXML_NAME ).replace( "\\", "/" );
    public static String SIGN_IN_SCENE_RESOURCES_CSS = ( ProjectConstants.SEP + "css" + ProjectConstants.SEP +
            SIGN_IN_SCENE_CSS_NAME ).replace( "\\", "/" );




}
