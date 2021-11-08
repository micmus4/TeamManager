package account;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AccountFactory
{
    private AccountFactory() {}

    public UserAccount createUserAccount( StringProperty firstNameProperty, StringProperty lastNameProperty,
                                         StringProperty loginProperty, StringProperty passwordProperty,
                                         StringProperty emailProperty, StringProperty phoneNumberProperty,
                                         ObjectProperty< String > countryProperty )
    {
        return new UserAccount( firstNameProperty.getValue(), lastNameProperty.getValue(), loginProperty.getValue(),
                passwordProperty.getValue(), emailProperty.getValue(), phoneNumberProperty.getValue(), countryProperty.getValue() );
    }


    public FootballClubAccount createFootballClubAccount( StringProperty fullNameTextProperty, StringProperty shortNameTextProperty,
                                                          StringProperty stadiumNameTextProperty, StringProperty stadiumCapacityTextProperty,
                                                          ObjectProperty< String > countryValueProperty, ObjectProperty< String > leagueValueProperty,
                                                          ObjectProperty< LocalDate > dateOfCreationValueProperty )
    {
        int stadiumCapacityConvertedToInteger = Integer.parseInt( stadiumCapacityTextProperty.getValue() );

        return new FootballClubAccount( fullNameTextProperty.getValue(), shortNameTextProperty.getValue(), stadiumNameTextProperty.getValue(),
                stadiumCapacityConvertedToInteger, countryValueProperty.getValue(), leagueValueProperty.getValue(), dateOfCreationValueProperty.getValue() );
    }


    public CompleteAccount createCompleteAccount( UserAccount userAccount, FootballClubAccount footballClubAccount )
    {
        return new CompleteAccount( userAccount, footballClubAccount );
    }
}
