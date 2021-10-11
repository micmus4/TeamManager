package constants.account;

import constants.ProjectConstants;

public class RegisterAccountConstants
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
     * Tooltip messages.
     */
    public static final String REGISTER_ACCOUNT_STAGE_LOGIN_TOOLTIP_MESSAGE = "Login:\n\t- from 8 to 20 characters" +
            "\n\t- no special characters";
    public static final String REGISTER_ACCOUNT_STAGE_PASSWORD_TOOLTIP_MESSAGE = "Password:\n\t- must have from 8 to 20" +
            " characters\n\t- at least one uppercase letter\n\t- at least one lowercase letter\n\t- at least one digit\n\t- at" +
            " least one special character.";
    public static final String REGISTER_ACCOUNT_STAGE_EMAIL_TOOLTIP_MESSAGE = "Email:\n\t- must have at least one '@'\n\t" +
            "- must have at least one '.'";
    public static final String WRONG_LENGTH_PASSWORD_MESSAGE = "Password must contain from 8 to 20 characters.";
    public static final String EMPTY_PASSWORD_MESSAGE = "Password can not be empty.";
    public static final String PASSWORD_NOT_MET_CHARACTERS_REQUIREMENTS_MESSAGE = "Password must have:\n\t- at least one uppercase " +
            "letter\n\t- at least one lowercase letter\n\t- at least one digit\n\t- at" + " least one special character.";
    public static final String EMPTY_NAME_MESSAGE = " Name can not be empty.";
    public static final String EMPTY_COUNTRY_MESSAGE = "Country can not be empty.";
    public static final String EMPTY_PHONE_NUMBER_MESSAGE = "Phone Number can not be empty.";

    // "-" and " " are exception to the rule when it comes to this constant.
    public static final String NON_DIGIT_CHARACTERS_IN_PHONE_NUMBER_MESSAGE = "Phone Number can not have non-digit characters.";

    public static final String WRONG_PHONE_NUMBER_PATTERN = "Phone Number must match one of the following patterns:\n\t- 123-456-789 " +
        "\n\t- 123 456 789\n\t- 123456789";
    public static final String EMPTY_LOGIN_MESSAGE = "Login can not be empty";
    public static final String WRONG_LENGTH_LOGIN_MESSAGE = "Login must contain from 8 to 20 characters.";
    public static final String SPECIAL_CHARACTERS_FOUND_IN_LOGIN_MESSAGE = "Login can not have any special characters";
    public static final String EMPTY_EMAIL_MESSAGE = "Email can not be empty";
    public static final String NECESSARY_CHARACTERS_IN_EMAIL_NOT_FOUND = "Email must have:\n\t- at least one '@'\n\t" +
            "- at least one '.'";


    /**
     * RegisterAccountValidation properties patterns.
     */
    public static final String PHONE_NUMBER_PATTERN = "^\\d{9}$";
    public static final String PHONE_NUMBER_PATTERN_WITH_SPACES = "^\\d{3} \\d{3} \\d{3}$";
    public static final String PHONE_NUMBER_PATTERN_WITH_DASHES = "^\\d{3}-\\d{3}-\\d{3}$";
    public static final String SPECIAL_CHARACTERS_PATTERN = "[^A-Za-z0-9]";
    public static final String UPPERCASE_LETTERS_PATTERN = "[^A-Z]";
    public static final String LOWERCASE_LETTERS_PATTERN = "[^a-z]";
    public static final String DIGITS_PATTERN = "[^0-9]";
    public static final String EVERYTHING_BUT_DIGITS_SPACES_AND_DASHES_PATTERN = "[^0-9\\-\\s]";

}

