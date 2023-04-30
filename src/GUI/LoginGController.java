package GUI;


import com.goacademy.MainApp;
import Entities.User;
import Services.CommandeService;
import Services.UserService;
import Utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginGController implements Initializable {


    @FXML
    public ComboBox<User> userCB;
    UserService us = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (User user : us.readAll()) {
            userCB.getItems().add(user);
        }
    }

    @FXML
    public void frontend(ActionEvent ignored) {
        if (userCB.getValue() == null) {
            AlertUtils.makeError("Choose user");
        } else {
            MainApp.session = userCB.getValue();
            MainApp.getInstance().loadFront();
        }
    }

    @FXML
    public void backend(ActionEvent ignored) {
        MainApp.getInstance().loadBack();
    }
}
