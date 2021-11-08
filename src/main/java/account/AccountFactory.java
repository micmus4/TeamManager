package account;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class AccountFactory
{
    private AccountFactory() {}


    public UserAccount createUserAccount( StringProperty aFirstNameProperty, StringProperty aLastNameProperty,
                                         StringProperty aLoginProperty, StringProperty aPasswordProperty,
                                         StringProperty aEmailProperty, StringProperty aPhoneNumberProperty,
                                         ObjectProperty< String > aCountryProperty )
    {
        return new UserAccount( aFirstNameProperty.getValue(), aLastNameProperty.getValue(), aLoginProperty.getValue(),
                aPasswordProperty.getValue(), aEmailProperty.getValue(),
                aPhoneNumberProperty.getValue(), aCountryProperty.getValue() );
    }


    public FootballClubAccount createFootballClubAccount( StringProperty aFullNameTextProperty, StringProperty aShortNameTextProperty,
                                                          StringProperty aStadiumNameTextProperty, StringProperty aStadiumCapacityTextProperty,
                                                          ObjectProperty< String > aCountryValueProperty, ObjectProperty< String > aLeagueValueProperty,
                                                          ObjectProperty< LocalDate > aDateOfCreationValueProperty )
    {
        int stadiumCapacityConvertedToInteger = Integer.parseInt( aStadiumCapacityTextProperty.getValue() );

        return new FootballClubAccount( aFullNameTextProperty.getValue(), aShortNameTextProperty.getValue(),
                aStadiumNameTextProperty.getValue(), stadiumCapacityConvertedToInteger, aCountryValueProperty.getValue(),
                aLeagueValueProperty.getValue(), aDateOfCreationValueProperty.getValue() );
    }


    public CompleteAccount createCompleteAccount( UserAccount aUserAccount, FootballClubAccount aFootballClubAccount )
    {
        return new CompleteAccount( aUserAccount, aFootballClubAccount );
    }
}
