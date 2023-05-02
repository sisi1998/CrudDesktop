/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Equipe;
import Entities.PerformanceEquipe;
import Entities.User;
import GUI.AffichEquipeController;
import GUI.ModifEquipeController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.PerformanceEquipeService;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class PerformanceEquipeIndexController implements Initializable {
    private static PerformanceEquipeIndexController instance;
public static PerformanceEquipeIndexController getInstance() {
        return instance;
    }
    private User userc;
public void getSession(User userc){
         this.userc=userc;
    this.sess.setText(userc.getEmail());
    

}

PerformanceEquipeService us = new PerformanceEquipeService();
List<PerformanceEquipe> listEquipe = us.readAll();

    @FXML
    private ListView<PerformanceEquipe> ListeItemsEquipe;
    @FXML
    private ChoiceBox<String> operations;
    @FXML
    private Button rfch;
    @FXML
    private TextField search;
    @FXML
    private Text sess;

    /**
     * Initializes the controller class.
     */
    @Override
   
    public void initialize(URL url, ResourceBundle rb) {
         instance = this;
        
       
        //initialise operations     
        ObservableList<String> list = FXCollections.observableArrayList("Afficher", "Modifier","Supprimer");
        operations.setItems(list);
        
        //intialize List View
        List<PerformanceEquipe> listu = us.readAll();
        
        ObservableList<PerformanceEquipe> obs=
                FXCollections.observableArrayList(listu);
        ListeItemsEquipe.setItems(obs);
        
        // Ajout d'un gestionnaire d'événements pour le double-clic sur un élément de la liste
        ListeItemsEquipe.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 &&(operations.getValue()==null || operations.getValue()=="Afficher")) {
                PerformanceEquipe equipe = ListeItemsEquipe.getSelectionModel().getSelectedItem();
                if (equipe != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichPerfEquipe.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        AffichPerfEquipeController controller = loader.getController();
                        controller.setPerfEquipe(equipe);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichEquipeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if (event.getClickCount() == 2 &&( operations.getValue()=="Supprimer")) {
                PerformanceEquipe eq = ListeItemsEquipe.getSelectionModel().getSelectedItem();
                if (eq != null) {
                    us.delete(eq.getId());
                    
                   refreshList(new ActionEvent());
                
            
                    
                }
            }
            
            if (event.getClickCount() == 2 &&( operations.getValue()=="Modifier")) {
                PerformanceEquipe user = ListeItemsEquipe.getSelectionModel().getSelectedItem();
                if (user != null) {try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifPerfEquipe.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        
                        ModifPerfEquipeController controller = loader.getController();
                        controller.setUser(user);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ModifEquipeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
        
        // TODO
    }    
    
    public void refreshList(ActionEvent event) {
         List<PerformanceEquipe> newList = us.readAll();
        ObservableList<PerformanceEquipe> newObsList = FXCollections.observableArrayList(newList);
        ListeItemsEquipe.setItems(newObsList);
    }

    @FXML
    private void AddEquipe(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutPerformanceEquipe.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        stage.show();
    }
    
}