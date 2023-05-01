/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Arena;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import Services.ArenaService;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mon Pc
 */
public class AddArenaFXMLController implements Initializable {
        @FXML
    private TextField txtn;
    @FXML
    private TextField txtp;
    @FXML
    private TextField txta;
    @FXML
    private Label label;
     private Button AddImage;
    @FXML
    private TextField URLImage;
      @FXML
    private Button btn;

   
       @FXML
    private Button btnArena;
 @FXML
    private ImageView Imagex;
    @FXML
    private Button btnCours;
     @FXML
    private Button btnx;
    ArenaService s=new ArenaService();

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
      @FXML  
  private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        //String DBPath = "C:\xampp\htdocs\FirstMine\public\images\arenes" + x + ".jpg";
                String DBPath = "" + x + ".jpg";

        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path = file.getAbsolutePath();
            String res;
            int len;
            len=path.length();

            res = path.substring(1,len);
            System.out.println(res);
            Image img = new Image(file.toURI().toString());
            Imagex.setImage(img);
            URLImage.setText(res);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();

        } else {
            System.out.println("error");

        }
    }
      @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Arena a1=new Arena(txtn.getText(),txtp.getText(),txta.getText(),URLImage.getText());
        ArenaService ps=new ArenaService();
        ps.insertPst(a1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ajout");
            alert.setHeaderText("succès.");
            alert.showAndWait();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AddArenaFXML.fxml"));
        Parent root=loader.load();
        txtn.getScene().setRoot(root);
//        RecuperationController recup=loader.getController();
       // recup.setLabel(txtn.getText());
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
