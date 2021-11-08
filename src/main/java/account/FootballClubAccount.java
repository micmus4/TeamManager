package account;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;

public class FootballClubAccount implements Serializable
{
    private static final long serialVersionUID = 314541L;

    private String fullName;

    private String shortName;

    private String stadiumName;

    private int stadiumCapacity;

    private String country;

    private String league;

    private LocalDate dateOfCreation;


    public FootballClubAccount( String aFullName, String aShortName, String aStadiumName, int aStadiumCapacity,
                               String aCountry, String aLeague, LocalDate aDateOfCreation )
    {
        fullName = StringUtils.capitalize( aFullName );
        shortName = StringUtils.capitalize( aShortName );
        stadiumName = StringUtils.capitalize( aStadiumName );
        stadiumCapacity = aStadiumCapacity;
        country = aCountry;
        league = aLeague;
        dateOfCreation = aDateOfCreation;
    }
}
