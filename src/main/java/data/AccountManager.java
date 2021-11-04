package data;

import account.CompleteAccount;
import constants.other.ProjectConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.AlertUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class AccountManager
{
    private final Logger LOGGER = LogManager.getLogger( AccountManager.class );

    private AccountManager(){}

    private static final AccountManager accountManagerInstance = new AccountManager();

    private final ArrayList< CompleteAccount > listOfAccounts = new ArrayList<>();

    private final URL urlToAccountsDatabase = getClass().getResource( ProjectConstants.RELATIVE_PATH_TO_ACCOUNTS_DAT_FILE );

    private final File accountsDatabaseFile = new File( urlToAccountsDatabase.getFile() );


    public static AccountManager getAccountManagerInstance()
    {
        return accountManagerInstance;
    }


    private boolean isFileForStoringAccountsCreated()
    {
        return accountsDatabaseFile.exists();
    }


    private void createFileIfItDoesntExist()
    {
        if( !isFileForStoringAccountsCreated() )
        {
            LOGGER.warn( ProjectConstants.ACCOUNTS_DAT_NAME + " does not exist." );
            try
            {
                accountsDatabaseFile.createNewFile();
                LOGGER.info( ProjectConstants.ACCOUNTS_DAT_NAME + " created." );
            }
            catch ( Exception e )
            {
                LOGGER.error( "Failed to create " + ProjectConstants.ACCOUNTS_DAT_NAME + " file." );
                AlertUtils.popUpErrorAlert( e );
                return;
            }
        }
    }


    public void writeAccountsToFile()
    {
        createFileIfItDoesntExist();
        LOGGER.info( "Writing accounts to " + ProjectConstants.ACCOUNTS_DAT_NAME + "." );
        try( ObjectOutputStream outputStream =
                     new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream( urlToAccountsDatabase.getFile() ) ) ) )
        {
            listOfAccounts.forEach( completeAccount -> {
                try
                {
                    outputStream.writeObject( completeAccount );
                }
                catch ( Exception e )
                {
                    LOGGER.error( "Error while serializing account." );
                    AlertUtils.popUpErrorAlert( e );
                }
            });
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error while writing accounts to " + ProjectConstants.ACCOUNTS_DAT_NAME + "." );
            AlertUtils.popUpErrorAlert( e );
        }
    }


    public void loadAccountsFromFile()
    {
        createFileIfItDoesntExist();
        LOGGER.info( "Loading accounts from " + ProjectConstants.ACCOUNTS_DAT_NAME + "." );
        try( ObjectInputStream inputStream =
                     new ObjectInputStream( new BufferedInputStream( new FileInputStream( urlToAccountsDatabase.getFile() ) ) ) )
        {
            boolean eof = false;
            while( !eof )
            {
                try
                {
                    CompleteAccount completeAccount = (CompleteAccount) inputStream.readObject();
                    addAccount( completeAccount );
                }
                catch ( EOFException e )
                {
                    eof = true;
                }
            }
        }
        catch ( EOFException e )
        {
            LOGGER.warn( "No accounts found in database." );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error while loading accounts from " + ProjectConstants.ACCOUNTS_DAT_NAME + "." );
            AlertUtils.popUpErrorAlert( e );
        }
    }


    public void addAccount( CompleteAccount account )
    {
        listOfAccounts.add( account );
    }


    public CompleteAccount logIn( String login, String password )
    {
        if( login.isEmpty() || password.isEmpty() )
        {
            AlertUtils.popUpInfoAlert( "Log In failed", "You couldn't log into account",
                    "You have to provide login and password." );
            return null;
        }

        CompleteAccount account = null;

        for( CompleteAccount completeAccount : listOfAccounts )
        {
            if( completeAccount.getUserAccount().getLogin().equals( login ) )
            {
                account = completeAccount;
                break;
            }
        }

        if( account == null )
        {
            LOGGER.warn( "Log in failed (no account with login: " + login + " )." );
            AlertUtils.popUpInfoAlert( "Log In failed", "You couldn't log into account",
                    "There is no account with login " + login + " in the database." );
            return null;
        }

        if( !account.getUserAccount().getPassword().equals( password ) )
        {
            LOGGER.warn( "Log in failed (wrong password for account: " + login + " )." );
            AlertUtils.popUpInfoAlert( "Log In failed", "You couldn't log into account",
                    "You have provided wrong password, please try again." );
            return null;
        }

        LOGGER.info( "User logged into " + login + " account." );
        AlertUtils.popUpInfoAlert( "Log In successful", "You have successfully logged into your account" );
        return account;
    }


    public boolean lookForTheSameLoginAndEmailInDatabase( String login, String email )
    {
        for( CompleteAccount completeAccount : listOfAccounts )
        {
            if( completeAccount.getUserAccount().getLogin().equals( login ) )
            {
                AlertUtils.popUpInfoAlert( "Error while registration",
                        "Can't proceed further",  "There is already account in database with login " + login + "." );
                return true;
            }
            if( completeAccount.getUserAccount().getEmail().equals( email ) )
            {
                AlertUtils.popUpInfoAlert( "Error while registration",
                        "Can't proceed further",  "There is already account in database with email " + email + "." );
                return true;
            }
        }
        return false;
    }

}
