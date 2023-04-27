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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.PasswordCrypt;
import Services.UserService;

/**
 * FXML Controller class
 *
 * @author Ousse
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dateB;
    @FXML
    private ChoiceBox<String> roles;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button register;
    @FXML
    private Button back;
    
   
    @FXML
    private Label er1;
    @FXML
    private Label er2;
    @FXML
    private Label er3;
    @FXML
    private Label er4;
    @FXML
    private Label er5;
    @FXML
    private Label er6;
    @FXML
    private Button btnImg;
    @FXML
    private ImageView ImgV;
    @FXML
    private ImageView imgg;
    
    private boolean validateEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1 = new Image(getClass().getResourceAsStream("/image/asset/broad-img.jpg"));
        imgg.setImage(image1);
        imgg.setPreserveRatio(true);
        
        //Choicebox roles init values
        ObservableList<String> list = FXCollections.observableArrayList("joueur", "coach");
        roles.setItems(list);
        
        //Datepicker dateB init
        
        dateB.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                LocalDate maxDate = LocalDate.now().minusYears(16); // Maximum date allowed is 18 years ago from now
                LocalDate minDate = LocalDate.now().minusYears(45); // Minimum date allowed is January 1, 1900

                if (date.isAfter(maxDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Set the background color to pink for disabled dates
                }
                if (date.isBefore(minDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Set the background color to pink for disabled dates
                }
            }
            
        });
            dateB.setValue(LocalDate.now().minusYears(30));
      
        // TODO
    }    
    

    private File selectedFile;

    @FXML
    void AddPicture() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose an image file");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
       
        Image image = new Image(selectedFile.toURI().toString());
        ImgV.setImage(image);
    }}

    @FXML
    private void Register(ActionEvent event) throws IOException {
        UserService us= new UserService();
        
        if(nom.getText().length()<6){
        er1.setText("le nom trés court (min 6)");
        return;
        }else{er1.setText("");}
        
        if(prenom.getText().length()<6){
        er2.setText("le nom trés court (min 6)");
        return;
        }else{er2.setText("");}
        
        if(!validateEmail(email.getText())){
        er3.setText("Email non valide");
        return;
        }else if (us.readByMail(email.getText())!=null){er3.setText("Email déjà utilisé !"); return;}
        else{er3.setText("");}
        
        if(pwd.getText().length()<6){er4.setText("mot de passe non valide");
        return;
        }else{er4.setText("");}
        
       // System.out.print(roles.getValue());
        if(roles.getValue()==null){er5.setText("insérez un role");
        return;
        }else{er5.setText("");}
        
        LocalDate selectedDate = dateB.getValue();
        
        if (selectedFile != null) {
            Path sourcePath = selectedFile.toPath();
            
            System.out.println(sourcePath.toString());
            Path targetPath = Paths.get("src/image/" + selectedFile.getName());
            Files.copy(sourcePath, targetPath);
    }
        PasswordCrypt pc = new PasswordCrypt();
        String hashedMDP = pc.cryptage(pwd.getText());
        User UI = new User(nom.getText(),prenom.getText(),email.getText(),hashedMDP,roles.getValue(),selectedDate,selectedFile.getName());
        us.insert(UI);
        
        // redirection 
         FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=loader.load();
        
        LoginController cc = loader.getController();
        cc.succesRegister(email.getText());

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
    }
        
        
        
        
        
    

    @FXML
    private void retourLogin(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass()
                .getResource("login.fxml"));
        
        
        Parent root=loader.load();

Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
