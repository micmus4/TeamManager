package account;

import java.io.Serializable;

public class CompleteAccount implements Serializable
{
    private static final long serialVersionUID = 8486742864L;

    private final UserAccount userAccount;

    private final FootballClubAccount footballClubAccount;

    public CompleteAccount( UserAccount userAccount, FootballClubAccount footballClubAccount )
    {
        this.userAccount = userAccount;
        this.footballClubAccount = footballClubAccount;
    }

    public UserAccount getUserAccount()
    {
        return userAccount;
    }

    public FootballClubAccount getFootballClubAccount()
    {
        return footballClubAccount;
    }
}
