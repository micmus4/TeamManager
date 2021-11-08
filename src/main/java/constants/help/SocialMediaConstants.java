package constants.help;

public enum SocialMediaConstants
{

    /**
     * Links to my social medias.
     */

    INSTAGRAM ( "https://www.instagram.com/muuusial_/" ),

    GITHUB  ("https://github.com/micmus4" ),

    FACEBOOK ( "https://www.facebook.com/michalmusialowicz01" ),

    LINKEDIN ( "https://www.linkedin.com/in/micha%C5%82-musia%C5%82owicz-553851216/" );


    private final String link;


    SocialMediaConstants( String aLink )
    {
        link = aLink;
    }


    public String getLink()
    {
        return link;
    }


}
