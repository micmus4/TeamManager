package account;

import java.io.Serializable;

public class CompleteAccount implements Serializable
{
    private static final long serialVersionUID = 8486742864L;

    private final UserAccount userAccount;

    private final FootballClubAccount footballClubAccount;


    public CompleteAccount( UserAccount aUserAccount, FootballClubAccount aFootballClubAccount )
    {
        userAccount = aUserAccount;
        footballClubAccount = aFootballClubAccount;
    }


    public UserAccount getUserAccount()
    {
        return userAccount;
    }
}
