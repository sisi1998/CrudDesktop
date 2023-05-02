/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Equipe;
import Entities.User;
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
import Services.EquipeService;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class EquipeIndexController implements Initializable {
    
private static EquipeIndexController instance;
public static EquipeIndexController getInstance() {
        return instance;
    }

    @FXML
    private ListView<Equipe> ListeItemsEquipe;
    @FXML
    private ChoiceBox<String> operations;
    @FXML
    private Button rfch;
    EquipeService us = new EquipeService();
    List<Equipe> listEquipe = us.readAll();
    @FXML
    private TextField search;
    @FXML
    private Text sess;
    private User userc;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         instance = this;
        
        search.textProperty().addListener((observable, oldValue, newValue) -> {
    updateListEquipe(newValue);
});
        //initialise operations     
        ObservableList<String> list = FXCollections.observableArrayList("Afficher", "Modifier","Supprimer");
        operations.setItems(list);
        
        //intialize List View
        List<Equipe> listu = us.readAll();
        
        ObservableList<Equipe> obs=
                FXCollections.observableArrayList(listu);
        ListeItemsEquipe.setItems(obs);
        
        // Ajout d'un gestionnaire d'événements pour le double-clic sur un élément de la liste
        ListeItemsEquipe.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 &&(operations.getValue()==null || operations.getValue()=="Afficher")) {
                Equipe equipe = ListeItemsEquipe.getSelectionModel().getSelectedItem();
                if (equipe != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichEquipe.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        AffichEquipeController controller = loader.getController();
                        controller.setEquipe(equipe);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichEquipeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if (event.getClickCount() == 2 &&( operations.getValue()=="Supprimer")) {
                Equipe eq = ListeItemsEquipe.getSelectionModel().getSelectedItem();
                if (eq != null) {
                    us.delete(eq.getId());
                    
                   refreshList(new ActionEvent());
                  File fileToDelete = new File("src/image/"+eq.getLogo());
            fileToDelete.delete();
                    
                }
            }
            
            if (event.getClickCount() == 2 &&( operations.getValue()=="Modifier")) {
                Equipe user = ListeItemsEquipe.getSelectionModel().getSelectedItem();
                if (user != null) {try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifEquipe.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        
                        ModifEquipeController controller = loader.getController();
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
         List<Equipe> newList = us.readAll();
        ObservableList<Equipe> newObsList = FXCollections.observableArrayList(newList);
        ListeItemsEquipe.setItems(newObsList);
    }

    @FXML
    private void AddEquipe(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutEquipe.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        stage.show();
    }
    private void updateListEquipe(String searchText) {
    ListeItemsEquipe.getItems().clear();
    ArrayList<Equipe> users = us.SearchByNom(searchText);
    ListeItemsEquipe.getItems().addAll(users);
}
    public void getSession(User userc){
         this.userc=userc;
    this.sess.setText(userc.getEmail());
    

}
}
