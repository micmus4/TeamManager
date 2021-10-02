package stages.account;

import constants.ProjectConstants;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.AlertUtils;
import utils.StageUtils;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

public class RegisterAccountStage
{
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ComboBox< String > countryComboBox;

    @FXML
    private Label versionLabel;

    private StringProperty firstNameTextProperty;

    private StringProperty lastNameTextProperty;

    private StringProperty loginTextProperty;

    private StringProperty emailTextProperty;

    private StringProperty phoneNumberTextProperty;

    private StringProperty passwordTextProperty;

    private StringProperty confirmPasswordTextProperty;

    private ObjectProperty< String > countryValueProperty;

    @FXML
    private ImageView countryPickStatus;


    public void initialize()
    {
        StageUtils.setProjectVersionLabel( versionLabel );
        setPropertiesForControls();
        loadCountriesToChoiceBox();
    }

    private void setPropertiesForControls()
    {
        firstNameTextProperty = firstNameField.textProperty();
        lastNameTextProperty = lastNameField.textProperty();
        loginTextProperty = loginField.textProperty();
        emailTextProperty = emailField.textProperty();
        phoneNumberTextProperty = phoneNumberField.textProperty();
        passwordTextProperty = passwordField.textProperty();
        confirmPasswordTextProperty = confirmPasswordField.textProperty();
        countryValueProperty = countryComboBox.valueProperty();

//        countryValueProperty.addListener((observableValue, s, t1) -> {
//            if( countryValueProperty.getValue().equals("") || countryValueProperty.getValue() == null )
//            {
//                countryPickStatus.setImage( StageUtils.transformPathToImageToImageInstance( "/images/redX.png" ) );
//            } else {
//                countryPickStatus.setImage( StageUtils.transformPathToImageToImageInstance( "/images/greenV.png" ) );
//            }
//        });
    }


    private void loadCountriesToChoiceBox()
    {
        JSONParser parser = new JSONParser();
        URL urlToJSONFile = getClass().getResource( ProjectConstants.COUNTRIES_RESOURCES_JSON );

        try
        {
            if ( urlToJSONFile == null )
            {
                throw new NullPointerException();
            }
        }
        catch ( Exception e )
        {
            AlertUtils.popUpErrorAlert( e );
            return;
        }


        ArrayList<String> countriesArrayList = new ArrayList<>();
        try( FileReader reader = new FileReader( urlToJSONFile.getPath() ) )
        {
            Object parsedJson = parser.parse( reader );
            JSONArray jsonDataArray = (JSONArray) parsedJson;

            jsonDataArray.forEach( country -> {
                String name = (String) ((JSONObject) country).get( "name" );
                countriesArrayList.add( name );
            } );
        }
        catch ( Exception e )
        {
            AlertUtils.popUpErrorAlert( e );
            return;
        }

        countryComboBox.setItems( FXCollections.observableList( countriesArrayList ) );
    }

}
