/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Equipe;
import Entities.PerformanceEquipe;
import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Services.PerformanceEquipeService;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class ModifPerfEquipeController implements Initializable {
    private PerformanceEquipe pee ;

    @FXML
    private TextField id_victoires;
    @FXML
    private TextField id_defaites;
    @FXML
    private TextField id_nuls;
    @FXML
    private TextField id_but_marque;
    @FXML
    private TextField id_but_encaisses;
    @FXML
    private Button btn_id;
    @FXML
    private Label e1;
    @FXML
    private Label e2;
    @FXML
    private Label e3;
    @FXML
    private Label e4;
    @FXML
    private Label e5;
    @FXML
    private Label id;
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
    
     public void setUser(PerformanceEquipe pee) {
        this.pee = pee;
        id.setText(Integer.toString(pee.getId()));
        
        
        
        id_victoires.setText(String.valueOf(pee.getVictoires()));
        id_defaites.setText(String.valueOf(pee.getDefaites()));
        id_nuls.setText(String.valueOf(pee.getNuls()));
        id_but_marque.setText(String.valueOf(pee.getBut_marque()));
        id_but_encaisses.setText(String.valueOf(pee.getBut_encaisse()));

    }

    @FXML
    private void ajouter(ActionEvent event) {
         PerformanceEquipeService us= new PerformanceEquipeService();
        
       
         if(Integer.parseInt(id_victoires.getText())<11 ||Integer.parseInt(id_victoires.getText())>40 ){
        e1.setText("nombre choisi doit être entre [0 .. 40]");
        return;
        }else{e1.setText("");}
         
        if(Integer.parseInt(id_defaites.getText())<0 ||Integer.parseInt(id_defaites.getText())>40 ){
        e2.setText("nombre choisi doit être entre [0 .. 40]");
        return;
        }else{e2.setText("");}
        
         if(Integer.parseInt(id_nuls.getText())<0 ||Integer.parseInt(id_nuls.getText())>40 ){
        e3.setText("nombre choisi doit être entre [0 .. 40]");
        return;
        }else{e3.setText("");}
         
         if(Integer.parseInt(id_but_marque.getText())<0 ||Integer.parseInt(id_but_marque.getText())>96 ){
        e4.setText("nombre choisi doit être entre [0 .. 96]");
        return;
        }else{e4.setText("");}
         
          if(Integer.parseInt(id_but_encaisses.getText())<0 ||Integer.parseInt(id_but_encaisses.getText())>96 ){
        e5.setText("nombre choisi doit être entre [0 .. 96]");
        return;
        }else{e5.setText("");}
        PerformanceEquipe pe = new PerformanceEquipe(Integer.parseInt(id_victoires.getText()),Integer.parseInt(id_defaites.getText()),Integer.parseInt(id_nuls.getText()),Integer.parseInt(id_but_marque.getText()),Integer.parseInt(id_but_encaisses.getText()));
          pee.setBut_encaisse(Integer.parseInt(id_but_encaisses.getText()));
          pee.setBut_marque(Integer.parseInt(id_but_marque.getText()));
          pee.setVictoires(Integer.parseInt(id_victoires.getText()));
          pee.setDefaites(Integer.parseInt(id_defaites.getText()));
          pee.setNuls(Integer.parseInt(id_nuls.getText()));
          us.update(pee);
          
          
          
          PerformanceEquipeIndexController xx = PerformanceEquipeIndexController.getInstance();

        // Call the refreshList method on the UserIndexController instance
        xx.refreshList(event);
        
        Stage stage = (Stage) id_but_encaisses.getScene().getWindow();
            stage.close();
    }
    
    public void getSession(User userc){
         this.userc=userc;
    this.sess.setText(userc.getEmail());
    

}
    
}
