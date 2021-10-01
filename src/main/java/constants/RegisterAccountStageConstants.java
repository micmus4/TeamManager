package constants;

public class RegisterAccountStageConstants
{

    /**
     * Height and width for RegisterAccountStage.
     */
    public static final Double REGISTER_ACCOUNT_STAGE_HEIGHT = 600.00D;
    public static final Double REGISTER_ACCOUNT_STAGE_WIDTH = 400.00D;


    /**
     * Height and width for RegisterAccountStage.
     */

    public static final String REGISTER_ACCOUNT_STAGE_FXML_NAME = "registerAccountStage.fxml";
    public static final String REGISTER_ACCOUNT_STAGE_CSS_NAME = "registerAccountStageStyle.css";


    /**
     * Relative paths (from resources dir) to FXML/CSS files for SignInStage.
     */

    public static String REGISTER_ACCOUNT_STAGE_RESOURCES_FXML = ( ProjectConstants.SEP + "fxml" + ProjectConstants.SEP +
            REGISTER_ACCOUNT_STAGE_FXML_NAME).replace( "\\", "/" );
    public static String REGISTER_ACCOUNT_STAGE_RESOURCES_CSS = ( ProjectConstants.SEP + "css" + ProjectConstants.SEP +
            REGISTER_ACCOUNT_STAGE_CSS_NAME).replace( "\\", "/" );
}

