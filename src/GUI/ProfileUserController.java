/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Services.UserService;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class ProfileUserController implements Initializable {
private User userc;
   
    
    private Label email;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private ImageView usrImg;
    @FXML
    private Label birth;
    @FXML
    private Button back;
    @FXML
    private Text sess;
    
    

    /**
     * Initializes the controller class.
     */
    
    public void getSession(User userc){
         this.userc=userc;
    this.sess.setText(userc.getEmail());
}
       
    
    public void getProfile(User userProf){
        this.userc=userProf;
        emailLabel.setText(userProf.getEmail());
        nomLabel.setText(userProf.getNom());
        prenomLabel.setText(userProf.getPrenom());
        roleLabel.setText(userProf.getRole());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        birth.setText(formatter.format(userProf.getDate_birth()));
    
   Image image1 = new Image(getClass().getResourceAsStream("/image/"+userProf.getImage()));
        usrImg.setImage(image1);
//        
//        
        usrImg.setFitWidth(150);
usrImg.setFitHeight(200);
usrImg.setPreserveRatio(false);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
    } 

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("Acceuil.fxml"));
        Parent root=loader.load();
        
        AcceuilController cc = loader.getController();
        
        
        cc.getSession(userc);
        

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    
    
}
