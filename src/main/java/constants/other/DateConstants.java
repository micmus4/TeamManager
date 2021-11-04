package constants.other;

import java.time.LocalDate;
import java.time.Month;

public class DateConstants
{
    private DateConstants(){}

    public static final LocalDate CURRENT_DATE = LocalDate.now();

    // Sheffield United, founded in 24.10.1857
    public static final LocalDate DATE_OF_FIRST_FOOTBALL_CLUB_CREATION = LocalDate.of( 1857, Month.OCTOBER, 24 );
}
