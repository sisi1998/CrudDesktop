/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Services.PasswordCrypt;
import Services.UserService;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class LoginController implements Initializable {

    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField email;
    @FXML
    private TextField pwd;
    @FXML
    private Label error;
    @FXML
    private Label lblsucc;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView cr7view;
    @FXML
    private Text text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image image1 = new Image(getClass().getResourceAsStream("/image/asset/page-header-bg.jpg"));
        cr7view.setImage(image1);
        
        
        // TODO
    }    

    @FXML
    private void Connect(ActionEvent event) throws IOException {
        UserService us= new UserService();
        PasswordCrypt pc = new PasswordCrypt();
       User gg = us.readByMail(email.getText());
       
       if(gg==null){
       error.setText("User n'existe pas");
       return;
       //  gg.getMdp().equals(pwd.getText())
       }else if (pc.décryptage(pwd.getText(),gg.getMdp())){error.setText("mdp correct");
       error.setTextFill(Color.GREEN);
       //_____ passation session vers Acceuil
       if(!gg.isIs_blocked()){
           System.out.println("__xx___");
       FXMLLoader loader= new FXMLLoader(getClass().getResource("Acceuil.fxml"));
        Parent root=loader.load();
        
        AcceuilController cc = loader.getController();
        
        
        cc.getSession(gg);
        

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();}else{
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Accées refusé !");
                alert.setHeaderText("Blocked ACCESS");
                alert.setContentText("il semble que votre compte est bloqué, merci de contacter un Administrateur");
                alert.showAndWait();
                error.setText("");
       }
       
       //_____
       }else{
       error.setText("mdp incorrect!");
       }
       
      
    }

    @FXML
    private void goRegister(ActionEvent event) throws IOException {
        
        //roles initial
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Register.fxml"));
        
        //datepicker: dateb initial
        
        
        
        
        Parent root=loader.load();

Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    public void succesRegister(String mail){
    this.email.setText(mail);
    this.lblsucc.setTextFill(Color.GREEN);
    this.lblsucc.setText("compte crée avec succées !");
    }
    
    public void succesRset(String mail){
    this.email.setText(mail);
    this.lblsucc.setTextFill(Color.GREEN);
    this.lblsucc.setText("mot de passe reseted avec succées");
    }

    @FXML
    private void MDPOublie(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MotDePasseOublie1.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    
    // Get the stage and set the new scene
    Stage stage = (Stage) text.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    
    
}
