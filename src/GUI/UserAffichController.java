/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.User;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class UserAffichController implements Initializable {

    
private User user;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label role;
    @FXML
    private Label birth;
    @FXML
    private ImageView image;
    @FXML
    private Label block;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        }
        
    
    
    
    
        public void setUser(User user) {
        this.user = user;
        id.setText(Integer.toString(user.getId()));
        nom.setText(user.getNom());
        prenom.setText(user.getPrenom());
        email.setText(user.getEmail());
        role.setText(user.getRole());
        
        if(user.isIs_blocked()){
        block.setText("oui");
        block.setTextFill(Color.RED);
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        birth.setText(formatter.format(user.getDate_birth()));
        
        Image imagee = new Image(getClass().getResourceAsStream("/image/" + user.getImage()));
        if(user.getImage()!=null){
        image.setImage(imagee);}
        }
}
