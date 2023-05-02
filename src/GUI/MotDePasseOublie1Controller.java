/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.UserService;
//mailer
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import Services.MailService;
import Services.PasswordCrypt;
import javafx.scene.text.Text;
//mailer end


/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class MotDePasseOublie1Controller implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button retour;
    @FXML
    private Button send;
    @FXML
    private Label error;
    @FXML
    private TextField tokentxt;
    @FXML
    private Button resetpage;
    @FXML
    private Button resetpage1;
    @FXML
    private PasswordField password;
    
    private String resetToken;
    @FXML
    private Text sess;
    private User userc;
    
    public void getSession(User userc){
         this.userc=userc;
    this.sess.setText(userc.getEmail());
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        resetpage.setVisible(false);
        tokentxt.setVisible(false);
        password.setVisible(false);
        resetpage1.setVisible(false);
        // TODO
    }    

    @FXML
    private void retourLogin(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=loader.load();

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
    }

    @FXML
    private void send(ActionEvent event) {
        
        UserService us = new UserService();
        User u = us.readByMail(email.getText());
        if (u == null)
        {
            error.setText("compte n'existe pas");
            return ;
        }
        error.setTextFill(Color.GREEN);
        
        
        resetToken = UUID.randomUUID().toString();
    
    // Enregistrer le token dans la base de données pour l'utilisateur correspondant
//    u.setResetToken(resetToken);
//    us.update(u);
us.rest_token(u, resetToken);
    
    // Envoyer l'email avec le jeton de réinitialisation
    String to = email.getText();
   // String to = "oussemaxbox360@gmail.com";
    String subject = "Réinitialisation de mot de passe";
    String body = "Bonjour " + u.getNom() + ",\n\n" +
                  "Vous avez demandé une réinitialisation de votre mot de passe.\n" +
                  "Veuillez utiliser le code suivant pour réinitialiser votre mot de passe :\n\n"
            +resetToken + "\n\n" 
            +
                  "Ce code est valable pendant 24 heures.\n\n" +
         //   "ou bien allez sur "+ 
           // " http://127.0.0.1:8000/oubli-pass/"+resetToken+"\n\n"+
                  "Cordialement,\n" +
                  "L'équipe de Mon Application";
    String from = "goacademy66@gmail.com";
    String appKey = "itqczwigkrkquytz";
    MailService.send(from, appKey, to, subject, body);
    error.setText("E-mail enovyé, veuillez saisir votre code ");
    error.setTextFill(Color.GREEN);
    resetpage.setVisible(true);
        tokentxt.setVisible(true);
       email.setEditable(false);
       send.setVisible(false);

        }

    @FXML
    private void GoToReset(ActionEvent event) {
        
        UserService us = new UserService();
        User u = us.readByMail(email.getText());
        
        if(!tokentxt.getText().equals(u.getReset_token())){
        error.setText("Code de reset incorrect");
        error.setTextFill(Color.RED);
        return;
        }else{
            tokentxt.setEditable(false);
            resetpage.setVisible(false);
            error.setText("Insérez votre nouveau mot de passe");
        error.setTextFill(Color.GREEN);
        // affichage
        password.setVisible(true);
        resetpage1.setVisible(true);
        
        }
      
    }

    @FXML
    private void ResetPassWord(ActionEvent event) throws IOException {
        
        UserService us = new UserService();
        User u = us.readByMail(email.getText());
        
        if(password.getText().length()<6){
        error.setText("mot de passe court (6 min)");
        error.setTextFill(Color.RED);
        return;
        }
        else{
            PasswordCrypt pc = new PasswordCrypt();
            String hashedMDP = pc.cryptage(password.getText());
         us.rest_mdp(u, hashedMDP);
         FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=loader.load();
        
        LoginController cc = loader.getController();
        
        
        cc.succesRset(email.getText());
        

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
        }
    }
    
    
    }
    

