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

    public UserAccount( String firstName, String lastName, String login, String password,
                        String email, String phoneNumber, String country )
    {
        this.firstName = StringUtils.capitalize( firstName );
        this.lastName = StringUtils.capitalize( lastName );
        this.login = login;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.country = country;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
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

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getCountry()
    {
        return country;
    }
}
