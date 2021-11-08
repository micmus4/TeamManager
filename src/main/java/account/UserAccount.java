package account;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class UserAccount implements Serializable
{
    private static final long serialVersionUID = 6874242L;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String password;

    private String phoneNumber;

    private String country;


    public UserAccount( String aFirstName, String aLastName, String aLogin, String aPassword,
                        String aEmail, String aPhoneNumber, String aCountry )
    {
        firstName = StringUtils.capitalize( aFirstName );
        lastName = StringUtils.capitalize( aLastName );
        login = aLogin;
        email = aEmail;
        password = aPassword;
        phoneNumber = aPhoneNumber;
        country = aCountry;
    }


    public String getLogin()
    {
        return login;
    }


    public String getEmail()
    {
        return email;
    }


    public String getPassword()
    {
        return password;
    }

}
