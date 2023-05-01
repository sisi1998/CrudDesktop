/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Equipe;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.EquipeService;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class ModifEquipeController implements Initializable {
private File selectedFile;

private Equipe equipe;
    @FXML
    private ImageView image;
    @FXML
    private Label id;
    @FXML
    private Button chooseImg;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtemail;
    @FXML
    private Button btnSave;
    @FXML
    private HBox ratingPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Image emptyStarImage = new Image("/image/star-empty.png");
//        Image fullStarImage = new Image("/image/star-full.png");
//
//        for (int i = 1; i <= 5; i++) {
//            ImageView starImageView = new ImageView(emptyStarImage);
//            starImageView.setFitHeight(30);
//            starImageView.setFitWidth(30);
//
//            int finalI = i;
//            starImageView.setOnMouseEntered(event -> {
//                for (int j = 1; j <= finalI; j++) {
//                    ((ImageView) ratingPane.getChildren().get(j - 1)).setImage(fullStarImage);
//                }
//            });
//
//            starImageView.setOnMouseExited(event -> {
//                for (int j = 5; j > finalI; j--) {
//                    ((ImageView) ratingPane.getChildren().get(j - 1)).setImage(emptyStarImage);
//                }
//            });
//
//            starImageView.setOnMouseClicked(event -> {
//                for (int j = 1; j <= finalI; j++) {
//                    ((ImageView) ratingPane.getChildren().get(j - 1)).setImage(fullStarImage);
//                }
//                for (int j = 5; j > finalI; j--) {
//                    ((ImageView) ratingPane.getChildren().get(j - 1)).setImage(emptyStarImage);
//                }
//            });
//
//            Tooltip tooltip = new Tooltip("Rate " + i);
//            Tooltip.install(starImageView, tooltip);
//            ratingPane.getChildren().add(starImageView);
//        }
    }    
    private String photod;    
    public void setUser(Equipe equipe) {
        this.equipe = equipe;
        id.setText(Integer.toString(equipe.getId()));
        txtprenom.setText(String.valueOf(equipe.getNb_joueurs()));
        txtnom.setText(equipe.getNom());
        txtemail.setText(equipe.getOrigine());
        Image imagee = new Image(getClass().getResourceAsStream("/image/" + equipe.getLogo()));
        if(imagee!=null){
        image.setImage(imagee);}
        photod= this.equipe.getLogo();
        
    }
    
    @FXML
    private void chooseImage(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose an image file");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        
        Image images = new Image(selectedFile.toURI().toString());
        image.setImage(images);
        
    }
    }

    @FXML
    private void onClickSave(ActionEvent event) throws IOException {
        
        String adPhoto;
        if (selectedFile != null) {
            Path sourcePath = selectedFile.toPath();
            
            System.out.println(sourcePath.toString());
            Path targetPath = Paths.get("./src/image/" + selectedFile.getName());
            Files.copy(sourcePath, targetPath);
            adPhoto = selectedFile.getName() ;
            File fileToDelete = new File("./src/image/" + photod);
            fileToDelete.delete();
            
    }else{
        adPhoto=equipe.getLogo();
        }
            
        
        Equipe nn = new Equipe(equipe.getId(),txtnom.getText(),adPhoto,Integer.parseInt(txtprenom.getText()),txtemail.getText());
    EquipeService us = new EquipeService();
    us.update(nn);
 
    EquipeIndexController userIndexController = EquipeIndexController.getInstance();

        // Call the refreshList method on the UserIndexController instance
        userIndexController.refreshList(event);
    
    Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
            
            
    }
    
}
