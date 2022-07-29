package sample;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView shieldImageView;

    @FXML
    private TextField usernameRegisterTextField;

    @FXML
    private TextField emailRegisterTextField;

    @FXML
    private PasswordField setRegisterPasswordField;

    @FXML
    private Button registerUserButton;

    @FXML
    private Button closeRegisterButton;

    @FXML
    private Label registerMessageLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/shield.jpg");
        javafx.scene.image.Image brandingImage = new Image(brandingFile.toURI().toString());
        shieldImageView.setImage(brandingImage);
    }

    public void registerButtonOnAction(ActionEvent event) {
        if(event.getSource() == registerUserButton){
            if (!usernameRegisterTextField.getText().isBlank() && !setRegisterPasswordField.getText().isBlank() && !emailRegisterTextField.getText().isBlank()) {
                insertUser();
                registerMessageLabel.setText("Berhasil");
            } else {
                registerMessageLabel.setText("Please enter username and password");
                registerMessageLabel.setText("Kosong");
            }
        }
    }

    private void executeQuery(String sql) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void insertUser() {
        String sql = "INSERT INTO user (namaUser, password, email) VALUES('"+usernameRegisterTextField.getText()+"','"+setRegisterPasswordField.getText()+"','"+emailRegisterTextField.getText()+"')";
        executeQuery(sql);
    }


    public void cancelButtonOnAction(ActionEvent event) {
        if (event.getSource()==closeRegisterButton) {
            Stage stage = (Stage) closeRegisterButton.getScene().getWindow();
            stage.close();
        }
    }

}
