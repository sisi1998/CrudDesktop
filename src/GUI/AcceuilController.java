/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.User;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Services.UserService;


/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class AcceuilController implements Initializable {
private User userc;

    @FXML
    private Label sess;
    @FXML
    private ImageView usrImg;
    @FXML
    private AnchorPane sidePane;
    @FXML
    private Button backlog;
    @FXML
    private ImageView competition;
    @FXML
    private Button gocomp;
    @FXML
    private Button gocours;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backlog.setVisible(false);
        
        // TODO
    }    
    
     public void getSession(User userc){
         this.userc=userc;
    this.sess.setText(userc.getEmail());
    Image image1 = new Image(getClass().getResourceAsStream("/image/"+userc.getImage()));
        usrImg.setImage(image1);
        if(userc.getRole().toUpperCase().equals("ADMIN")){
        backlog.setVisible(true);
        }
        
        usrImg.setFitWidth(150);
usrImg.setFitHeight(200);
usrImg.setPreserveRatio(false);
    
    }

    @FXML
    private void GoBacklog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                      // AdminPageController controller = loader.getController();
                       
                        stage.show();
    }

    @FXML
    private void MyProfile(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("ProfileUser.fxml"));
        Parent root=loader.load();
        
        ProfileUserController cc = loader.getController();
        
        
        cc.getProfile(userc);
        

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void gotoarena(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("homeFXML.fxml"));
        Parent root=loader.load();
        
       AddArenaFXMLController cc = loader.getController();
        
        
        cc.getSession(userc);
         Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
    }

    @FXML
    private void gomag(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("ShowAllProdFront.fxml"));
        Parent root=loader.load();
        
       AddArenaFXMLController cc = loader.getController();
        
        
        cc.getSession(userc);
         Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void goequipe(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("EquipeIndex.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        EquipeIndexController controller = loader.getController();
                        controller.getSession(userc);
                        stage.show();
    }

    @FXML
    private void goperfE(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PerformanceEquipeIndex.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        PerformanceEquipeIndexController controller = loader.getController();
                        controller.getSession(userc);
                        stage.show();
    }

    @FXML
    private void gotoComp(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("CompetitionFront.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        CompetitionFrontController controller = loader.getController();
                        controller.getSession(userc);
                        stage.show();
    }

    @FXML
    private void gopeerj(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("PerformanceFront.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        PerformanceFrontController controller = loader.getController();
                        controller.getSession(userc);
                        stage.show();
    }

    @FXML
    private void goprod(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAllProdFront.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                       // PerformanceFrontController controller = loader.getController();
                       // controller.getSession(userc);
                        stage.show();
    }
    }
 

     

