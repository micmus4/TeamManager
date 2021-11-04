package constants.other;

import javax.swing.text.html.ImageView;

public class ImageConstants
{
    private ImageConstants(){}

    /**
     * Names of files containing images.
     */

    public static final String ICON_NAME = "footballIcon.png";

    public static final String QUESTION_MARK_NAME = "questionMark.png";

    public static final String CORRECT_NAME = "correct.png";

    public static final String WRONG_NAME = "wrong.png";


    /**
     * Relative paths (from resources dir) to images.
     */

    public static final String ICON = ( ProjectConstants.SEP + "images" + ProjectConstants.SEP +
            ICON_NAME ).replace( "\\", "/" );

    public static final String QUESTION_MARK = ( ProjectConstants.SEP + "images" + ProjectConstants.SEP +
            QUESTION_MARK_NAME ).replace( "\\", "/" );

    public static final String CORRECT = ( ProjectConstants.SEP + "images" + ProjectConstants.SEP +
            CORRECT_NAME ).replace( "\\", "/" );

    public static final String WRONG = ( ProjectConstants.SEP + "images" + ProjectConstants.SEP +
            WRONG_NAME ).replace( "\\", "/" );

}
