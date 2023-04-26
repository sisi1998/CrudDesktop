/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.UserService;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class UserModifyController implements Initializable {
    private File selectedFile;
    
    
private User user;
    @FXML
    private ImageView image;
    @FXML
    private Label id;
    @FXML
    private Label birth;
    @FXML
    private Button chooseImg;
    @FXML
    private ChoiceBox<String> role;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtemail;
    @FXML
    private Label crole;
    @FXML
    private Button btnSave;
    @FXML
    private CheckBox blocked;
   // private int Block;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ObservableList<String> list = FXCollections.observableArrayList("joueur", "coach","admin","agent_magasin");
        role.setItems(list);
        
        blocked.setOnAction(event -> {
            if (blocked.isSelected()) {
                // Affichage de l'alerte si la case est cochée
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("bloquage utilisateur");
                alert.setHeaderText("Attention !");
                alert.setContentText("En cochant cette case, vous venez de bloquer cet utilisateur !!");
                alert.showAndWait();
            }
        });
        // TODO
    }
private String photod;    
    public void setUser(User user) {
        this.user = user;
        id.setText(Integer.toString(user.getId()));
        crole.setText(user.getRole());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        birth.setText(formatter.format(user.getDate_birth()));
        txtprenom.setText(user.getPrenom());
        txtnom.setText(user.getNom());
        txtemail.setText(user.getEmail());
        //blockage
        if(user.isIs_blocked()){blocked.setSelected(true);}else{blocked.setSelected(false);}
        Image imagee = new Image(getClass().getResourceAsStream("/image/" + user.getImage()));
        if(imagee!=null){
        image.setImage(imagee);}
        photod= this.user.getImage();
        
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
            Path targetPath = Paths.get("src/image/" + selectedFile.getName());
            Files.copy(sourcePath, targetPath);
            adPhoto = selectedFile.getName() ;
            File fileToDelete = new File("src/image/" + photod);
            fileToDelete.delete();
            
    }else{
        adPhoto=user.getImage();
        }
            
        if(role.getValue()==null){role.setValue(user.getRole());}
        User nn = new User(user.getId(),txtnom.getText(),txtprenom.getText(),txtemail.getText(),user.getMdp(),role.getValue(),user.getDate_birth(),blocked.isSelected(),adPhoto);
    UserService us = new UserService();
    us.update(nn);
 
    UserIndexController userIndexController = UserIndexController.getInstance();

        // Call the refreshList method on the UserIndexController instance
        userIndexController.refreshList(event);
    
    Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
            
            
    }
    }
