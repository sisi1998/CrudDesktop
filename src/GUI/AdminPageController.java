/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class AdminPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gocomp(ActionEvent event) throws IOException {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListCompetition.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        AdminListCompetitionController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void coperJ(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPerformance.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        ListPerformanceController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void goeq(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("CompetitionFront.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        CompetitionFrontController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void goperfE(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("CompetitionFront.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        CompetitionFrontController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void gocours(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCoursFXML.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        ShowCoursFXMLController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void goMag(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAllProdBack.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        ShowAllProdBackController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        AcceuilController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void gocommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAllCommBack.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        ShowAllCommBackController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void gocat(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAllCategoBack.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                       // ShowAllCommBackController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void gouser(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("UserIndex.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                       // ShowAllCommBackController controller = loader.getController();
                       
                        stage.show();
    }
    
}
