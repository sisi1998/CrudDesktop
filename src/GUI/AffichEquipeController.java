/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Equipe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Services.EquipeService;

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
    
    
    
}
