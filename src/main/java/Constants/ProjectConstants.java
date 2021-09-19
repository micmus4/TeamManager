package Constants;

import java.io.File;

public class ProjectConstants {
    // Correct file separator for operating system flexibility.
    public static final String SEP = File.separator;

    public static final String PROTOCOL = "file";

    public static final String HOST = "";

    // String path to project's root directory.
    public static final String PROJECT_STRING_PATH = System.getProperty( "user.dir" );
}
