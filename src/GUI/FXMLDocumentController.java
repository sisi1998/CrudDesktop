/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Arena;
import Entities.Cours;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import Services.ArenaService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Mon Pc
 */
public class FXMLDocumentController implements Initializable {
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
    private TableView<Arena> ArenaTable;
    @FXML
    private TableColumn<Arena, String> nameCol;
    @FXML
    private TableColumn<Arena, String> surfCol;
    @FXML
    private TableColumn<Arena, String> adressCol;
     @FXML
    private TableColumn<Arena, String> imgCol;
      @FXML
    private TextField Nom;
    @FXML
    private TextField Recherchew;
    @FXML
    private TextField Surface;
    @FXML
    private TextField Adresse;
       @FXML
    private Button btnArena;
 @FXML
    private ImageView Imagex;
    @FXML
    private Button btnCours;
     @FXML
    private Button btnx;
            @FXML
    private Button btnl;
       @FXML
    private Button btnf;
    ArenaService s=new ArenaService();
    
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
        FXMLLoader loader= new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root=loader.load();
        txtn.getScene().setRoot(root);
//        RecuperationController recup=loader.getController();
       // recup.setLabel(txtn.getText());
        
    }
    
       @FXML
    private void RechercheY(KeyEvent event) {
        String cha = Recherchew.getText();
        System.out.println(" = "+cha+" a été ");
        populateTable(s.chercherArena(cha));
    }
 private void populateTable(ObservableList<Arena> branlist2) {
        ArenaTable.setItems(branlist2);

    }
       @FXML
    private void Supprimerx() throws SQLException{
        
        //Arena a2 = ArenaTable.getSelectionModel().getSelectedItem().getId();
        s.supprimer(ArenaTable.getSelectionModel().getSelectedItem().getId());
    }
         @FXML
    private void SupprimerA(ActionEvent event) throws SQLException  {
      Supprimerx() ;
      ArenaTable.getItems().removeAll(ArenaTable.getSelectionModel().getSelectedItem());
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("suppression");
            alert.setHeaderText("succès.");
            alert.showAndWait();
    }
      @FXML
    private void Modify(ActionEvent event) throws SQLException {
        
        Arena a2 =new Arena();
        a2 = ArenaTable.getSelectionModel().getSelectedItem();
        //int ida = a2.getId();
        
     
        a2.setId(ArenaTable.getSelectionModel().getSelectedItem().getId());
        a2.setNom(txtn.getText());
        a2.setSurface(txtp.getText());
        a2.setAdresse(txta.getText());
        a2.setImage(URLImage.getText());
       try {
            s.modifier(a2);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification");
            alert.setHeaderText("succès.");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
      
      
        
    }
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
    private void getData(MouseEvent event){
        
       
      Arena ser;
        ser= ArenaTable.getSelectionModel().getSelectedItem();
        txtn.setText(ser.getNom());
        txtp.setText(ser.getSurface());
        txta.setText(ser.getAdresse());
        String path = "C:\\\\\\\\\\\\\\\\xampp\\\\\\\\\\\\\\\\htdocs\\\\\\\\\\\\\\\\FirstMine\\\\\\\\\\\\\\\\public\\\\\\\\\\\\\\\\images\\\\\\\\\\\\\\\\arenes\\\\\\\\\\\\\\\\"+ser.getImage();
            File file = new File(path);
            URLImage.setText(path);
            Image img = new Image(file.toURI().toString());
            Imagex.setImage(img);
        
    }
     @FXML
    private void RefreshA(ActionEvent event) {
       List<Arena> ser = s.readAll();
            ObservableList<Arena> olp = FXCollections.observableArrayList(ser);
            nameCol.setCellValueFactory(new PropertyValueFactory<> ("nom"));
            surfCol.setCellValueFactory(new PropertyValueFactory<> ("surface"));
            adressCol.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
            imgCol.setCellValueFactory(new PropertyValueFactory<> ("image"));
            ArenaTable.setItems(olp);
           
        
    }
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            List<Arena> ser = s.readAll();
            ObservableList<Arena> olp = FXCollections.observableArrayList(ser);
            nameCol.setCellValueFactory(new PropertyValueFactory<> ("nom"));
            surfCol.setCellValueFactory(new PropertyValueFactory<> ("surface"));
            adressCol.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
            imgCol.setCellValueFactory(new PropertyValueFactory<> ("image"));
            ArenaTable.setItems(olp);
           
        
    }    
    
}
