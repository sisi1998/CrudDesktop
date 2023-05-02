/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.PerformanceEquipe;
import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class AffichPerfEquipeController implements Initializable {
private PerformanceEquipe pee ;
    @FXML
    private Label id;
    @FXML
    private Label id_victoires;
    @FXML
    private Label id_defaites;
    @FXML
    private Label id_nuls;
    @FXML
    private Label id_but_marque;
    @FXML
    private Label id_but_encaisses;
    @FXML
    private Text sess;
    private User userc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setPerfEquipe(PerformanceEquipe pee) {
        this.pee = pee;
        id.setText(Integer.toString(pee.getId()));
        id_victoires.setText(String.valueOf(pee.getVictoires()));
        id_defaites.setText(String.valueOf(pee.getDefaites()));
        id_nuls.setText(String.valueOf(pee.getNuls()));
        id_but_marque.setText(String.valueOf(pee.getBut_marque()));
        id_but_encaisses.setText(String.valueOf(pee.getBut_encaisse()));

    }

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) id_but_encaisses.getScene().getWindow();
            stage.close();
    }
    
                  public void getSession(User userc){
         this.userc=userc;
    this.sess.setText(userc.getEmail());
    
        
    
                  }}
