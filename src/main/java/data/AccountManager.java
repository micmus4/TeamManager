package data;

import account.CompleteAccount;
import constants.other.ProjectConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.AlertUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class AccountManager
{
    private final Logger LOGGER;

    private final ArrayList< CompleteAccount > listOfAccounts;

    private final URL urlToAccountsDatabase;

    private final File accountsDatabaseFile;


    private AccountManager()
    {
        LOGGER = LogManager.getLogger( AccountManager.class );
        listOfAccounts = new ArrayList<>();
        urlToAccountsDatabase = getClass().getResource( ProjectConstants.RELATIVE_PATH_TO_ACCOUNTS_DAT_FILE );
        accountsDatabaseFile = new File( urlToAccountsDatabase.getFile() );
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


    public void addAccount( CompleteAccount aAccount )
    {
        listOfAccounts.add( aAccount );
    }


    public CompleteAccount logIn( String aLogin, String aPassword )
    {
        if( aLogin.isEmpty() || aPassword.isEmpty() )
        {
            AlertUtils.popUpInfoAlert( "Log In failed", "You couldn't log into account",
                    "You have to provide login and password." );
            return null;
        }

        CompleteAccount account = null;

        for( CompleteAccount completeAccount : listOfAccounts )
        {
            if( completeAccount.getUserAccount().getLogin().equals( aLogin ) )
            {
                account = completeAccount;
                break;
            }
        }

        if( account == null )
        {
            LOGGER.warn( "Log in failed (no account with login: " + aLogin + " )." );
            AlertUtils.popUpInfoAlert( "Log In failed", "You couldn't log into account",
                    "There is no account with login " + aLogin + " in the database." );
            return null;
        }

        if( !account.getUserAccount().getPassword().equals( aPassword ) )
        {
            LOGGER.warn( "Log in failed (wrong password for account: " + aLogin + " )." );
            AlertUtils.popUpInfoAlert( "Log In failed", "You couldn't log into account",
                    "You have provided wrong password, please try again." );
            return null;
        }

        LOGGER.info( "User logged into " + aLogin + " account." );
        AlertUtils.popUpInfoAlert( "Log In successful", "You have successfully logged into your account" );
        return account;
    }


    public boolean lookForTheSameLoginAndEmailInDatabase( String aLogin, String aEmail )
    {
        for( CompleteAccount completeAccount : listOfAccounts )
        {
            if( completeAccount.getUserAccount().getLogin().equals( aLogin ) )
            {
                AlertUtils.popUpInfoAlert( "Error while registration",
                        "Can't proceed further",  "There is already account in database with login " + aLogin + "." );
                return true;
            }
            if( completeAccount.getUserAccount().getEmail().equals( aEmail ) )
            {
                AlertUtils.popUpInfoAlert( "Error while registration",
                        "Can't proceed further",  "There is already account in database with email " + aEmail + "." );
                return true;
            }
        }
        return false;
    }
}
