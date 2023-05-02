/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Equipe;
import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Services.EquipeService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class AffichEquipeController implements Initializable {
    EquipeService es=new EquipeService();
    
private Equipe eq;
    @FXML
    private Label nom;
    @FXML
    private Label nb_joueurs;
    @FXML
    private ImageView image;
    @FXML
    private Label id;
    @FXML
    private Label origin;
    private User userc;
    @FXML
    private Button compC;
    @FXML
    private Button perfC1;
    @FXML
    private Text sess;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setEquipe(Equipe eq) {
        this.eq = eq;
        id.setText(Integer.toString(eq.getId()));
        nom.setText(eq.getNom());
        nb_joueurs.setText(String.valueOf(eq.getNb_joueurs()));
        origin.setText(eq.getOrigine());

        Image imagee = new Image(getClass().getResourceAsStream("/image/" + eq.getLogo()));
        
        if(imagee!=null){
        image.setImage(imagee);}
        }
     public void getSession(User userc){
         this.userc=userc;
    this.sess.setText(userc.getEmail());
    
        
        }

    @FXML
    private void espaceCompetition(ActionEvent event) {
    }

    @FXML
    private void espacePerformance(ActionEvent event) {
    }
    
    
}
