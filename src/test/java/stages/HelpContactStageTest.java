package stages;

import constants.help.SocialMediaConstants;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


class HelpContactStageTest
{
    @Test
    public void areURIsToSocialMediaCorrect()
    {
        assertThrows( URISyntaxException.class, () -> new URI( "Invalid URI" ),
                "Invalid URI, should throw URISyntaxException." );

        assertDoesNotThrow( () -> new URI( SocialMediaConstants.FACEBOOK.getLink() ),
                "Valid URI to Facebook site, shouldn't throw any exceptions." );

        assertDoesNotThrow( () -> new URI( SocialMediaConstants.INSTAGRAM.getLink() ),
                "Valid URI to Instagram site, shouldn't throw any exceptions." );

        assertDoesNotThrow( () -> new URI( SocialMediaConstants.GITHUB.getLink() ),
                "Valid URI to Github site, shouldn't throw any exceptions." );

        assertDoesNotThrow( () -> new URI( SocialMediaConstants.LINKEDIN.getLink() ),
                "Valid URI to Linkedin site, shouldn't throw any exceptions." );
    }
}