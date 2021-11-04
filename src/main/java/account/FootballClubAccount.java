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

    public FootballClubAccount( String fullName, String shortName, String stadiumName, int stadiumCapacity,
                               String country, String league, LocalDate dateOfCreation )
    {
        this.fullName = StringUtils.capitalize( fullName );
        this.shortName = StringUtils.capitalize( shortName );
        this.stadiumName = StringUtils.capitalize( shortName );
        this.stadiumCapacity = stadiumCapacity;
        this.country = country;
        this.league = league;
        this.dateOfCreation = dateOfCreation;
    }
}
