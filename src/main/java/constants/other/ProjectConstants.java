package constants.other;

import java.io.File;

public class ProjectConstants
{
    private ProjectConstants(){}

    /**
     * Correct file separator for operating system flexibility.
     */

    public static final String SEP = File.separator;


    /**
     * Current version of Team Manager.
     */

    private static final String VERSION = "1.0.0";


    /**
     * Text displayed in bottom right corner of every scene.
     */

    public static final String VERSION_INFO = "Team Manager " + VERSION + ", by Michal Musialowicz";


    /**
     * Name of .css file containing stylesheet for Version Label.
     */

    public static final String VERSION_CSS_NAME = "versionStyle.css";


    /**
     * Name of .json file containing list of all countries (their code + their name).
     */

    public static final String COUNTRIES_JSON_NAME = "countries.json";


    /**
     * Name of .properties file containing list of all leagues (key=league).
     */

    public static final String LEAGUES_PROPERTIES_NAME = "leagues.properties";


    /**
     * Name of .dat file containing all data about created accounts.
     */

    public static final String ACCOUNTS_DAT_NAME = "accounts.dat";


    /**
     * Name of Spring Configuration file.
     */

    public static final String SPRING_CONFIG_FILE_NAME = "applicationContext.xml";


    /**
     * Relative path (from resources dir) to .json file containing data about world's countries.
     */

    public static final String RELATIVE_PATH_TO_COUNTRIES_JSON_FILE = ( ProjectConstants.SEP + "json" + ProjectConstants.SEP +
            COUNTRIES_JSON_NAME ).replace( "\\", "/" );


    /**
     * Relative path (from resources dir) to .css file containing style for Version Label.
     */

    public static final String RELATIVE_PATH_TO_VERSION_CSS_FILE = ( ProjectConstants.SEP + "css" + ProjectConstants.SEP +
            VERSION_CSS_NAME ).replace( "\\", "/" );


    /**
     *  Relative path (from resources dir) to .properties file containing list of leagues
     */

    public static final String RELATIVE_PATH_TO_LEAGUES_PROPERTIES_FILE = ( ProjectConstants.SEP + "properties" + ProjectConstants.SEP +
            LEAGUES_PROPERTIES_NAME ).replace( "\\", "/" );


    /**
     *  Relative path (from resources dir) to .dat file containing all data about created accounts.
     */

    public static final String RELATIVE_PATH_TO_ACCOUNTS_DAT_FILE = ( ProjectConstants.SEP + "database" + ProjectConstants.SEP +
            ACCOUNTS_DAT_NAME ).replace( "\\", "/" );



    public static final String RELATIVE_PATH_TO_STRING_CONFIG_FILE =  ( ProjectConstants.SEP + "config" + ProjectConstants.SEP +
            SPRING_CONFIG_FILE_NAME ).replace( "\\", "/" );

}
