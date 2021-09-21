package constants;

import java.io.File;

public class ProjectConstants {

    // Correct file separator for operating system flexibility.
    public static final String SEP = File.separator;

    // String path to project's root directory.
    public static final String PROJECT_STRING_PATH = System.getProperty( "user.dir" );

    // Current version of Team Manager.
    private static final String VERSION = "1.0.0";

    // Text displayed in bottom right corner of every scene.
    public static final String VERSION_INFO = "Team Manager " + VERSION + ", by Michal Musialowicz";
}
