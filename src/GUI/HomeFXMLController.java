/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mon Pc
 */
public class HomeFXMLController implements Initializable {
     @FXML
    private Button btnArena;

    @FXML
    private Button btnCours;
     @FXML
    private Button btnx;
       @FXML
    private Button btnl;
       @FXML
    private Button btnf;
         @FXML
    private void handleButtonClicks(ActionEvent mouseEvent) {
        if (mouseEvent.getSource() ==btnCours) {
            loadStage("GestionCours.fxml");
        } else if (mouseEvent.getSource() ==btnArena) {
            loadStage("FXMLDocument.fxml");
        } 
        else if (mouseEvent.getSource() ==btnf) {
            loadStage("AddArenaFXML.fxml");
        }else if (mouseEvent.getSource() ==btnx) {
            loadStage("homeFXML.fxml");
        }else if (mouseEvent.getSource() ==btnl) {
            loadStage("ShowCoursFXML.fxml");
        }
        
        
    }  
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
           
            
            stage.show();
        } catch (IOException e) {
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goback(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
    Parent root = loader.load();
   CompetitionFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
}
