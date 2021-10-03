package constants.account;

import constants.ProjectConstants;

public class RegisterAccountStageConstants
{

    /**
     * Height and width for RegisterAccountStage.
     */
    public static final Double REGISTER_ACCOUNT_STAGE_HEIGHT = 600.00D;
    public static final Double REGISTER_ACCOUNT_STAGE_WIDTH = 500.00D;


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


    /**
     * Tooltip messages for login, password and email tooltips.
     */
    public static final String REGISTER_ACCOUNT_STAGE_LOGIN_TOOLTIP_MESSAGE = "Login:\n\t- from 8 to 20 characters" +
            "\n\t- no special characters";
    public static final String REGISTER_ACCOUNT_STAGE_PASSWORD_TOOLTIP_MESSAGE = "Password:\n\t- must have from 8 to 20" +
            " characters\n\t- at least one capital letter\n\t- at least one small letter\n\t- at least one digit\n\t- at" +
            " least one special character.";
    public static final String REGISTER_ACCOUNT_STAGE_EMAIL_TOOLTIP_MESSAGE = "Email:\n\t- must have at least one '@'\n\t" +
            "- must have at least one '.'";
}

