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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.EquipeService;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class AjoutEquipeController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private ImageView imgV;
    @FXML
    private TextField txtOrigin;
    @FXML
    private TextField nbJoueur;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    
    File selectedFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void AddPicture() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose an image file");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
       
        Image image = new Image(selectedFile.toURI().toString());
        imgV.setImage(image);
    }}
    
   


    @FXML
    private void ajouter(ActionEvent event) {
                EquipeService us= new EquipeService();
        
        if(txtNom.getText().length()<6){
        l1.setText("le nom trés court (min 6)");
        return;
        }else{l1.setText("");}
        
        if(Integer.parseInt(nbJoueur.getText())<11 ||Integer.parseInt(nbJoueur.getText())>40 ){
        l2.setText("nombre de joueurs doit être entre [11 .. 40]");
        return;
        }else{l2.setText("");}
        
        
        
        if(txtOrigin.getText().isEmpty()){l3.setText("veuillez insérer une valeur");
        return;
        }else{l3.setText("");}
        
        if (selectedFile != null) {
            Path sourcePath = selectedFile.toPath();
            
            System.out.println(sourcePath.toString());
            Path targetPath = Paths.get("./src/image/" + selectedFile.getName());
                    try {
                        Files.copy(sourcePath, targetPath);
                    } catch (IOException ex) {
                        Logger.getLogger(AjoutEquipeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
       Equipe E = new Equipe (txtNom.getText(),selectedFile.getName(),Integer.parseInt(nbJoueur.getText()),txtOrigin.getText());
      
        us.insert(E);
        
        EquipeIndexController userIndexController = EquipeIndexController.getInstance();

        // Call the refreshList method on the UserIndexController instance
        userIndexController.refreshList(event);
        
        Stage stage = (Stage) imgV.getScene().getWindow();
            stage.close();
        
    }
    }
    

