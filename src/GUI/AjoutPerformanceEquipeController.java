/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.PerformanceEquipe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.PerformanceEquipeService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutPerformanceEquipeController implements Initializable {

    @FXML
    private TextField id_victoires;
    @FXML
    private TextField id_defaites;
    @FXML
    private TextField id_nuls;
    @FXML
    private TextField id_but_marque;
    private Label id_but_encaisse;
    @FXML
    private TextField id_but_encaisses;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
          
          us.insert(pe);
          
          
          
          PerformanceEquipeIndexController xx = PerformanceEquipeIndexController.getInstance();

        // Call the refreshList method on the UserIndexController instance
        xx.refreshList(event);
        
        Stage stage = (Stage) id_but_encaisses.getScene().getWindow();
            stage.close();
       
    }
    
}
