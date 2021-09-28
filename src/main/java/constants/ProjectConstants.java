package constants;

import java.io.File;

public class ProjectConstants
{
    /**
     * Correct file separator for operating system flexibility.
     */

    public static final String SEP = File.separator;


    /**
     * String path to project's root directory.
     */

    public static final String PROJECT_STRING_PATH = System.getProperty( "user.dir" );


    /**
     * Current version of Team Manager.
     */

    private static final String VERSION = "1.0.0";


    /**
     * Text displayed in bottom right corner of every scene.
     */

    public static final String VERSION_INFO = "Team Manager " + VERSION + ", by Michal Musialowicz";


    /**
     * Icon of all windows name.
     */

    public static final String ICON_NAME = "footballIcon.png";


    /**
     * Relative paths (from resources dir) to icon.
     */

    public static String ICON_RESOURCES_FXML = ( ProjectConstants.SEP + "images" + ProjectConstants.SEP +
            ICON_NAME ).replace( "\\", "/" );

}