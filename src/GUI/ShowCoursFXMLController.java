/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Arena;
import Entities.Cours;
import Entities.Equipe;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Services.ArenaService;
import Services.CService;
import Services.EService;

/**
 * FXML Controller class
 *
 * @author Mon Pc
 */
public class ShowCoursFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
  
     @FXML
    private TableView<Cours> CsTable;
    @FXML
    private TableColumn<Cours, String> name;
    @FXML
    private TableColumn<Cours, String> desc;
    @FXML
    private TableColumn<Cours, String> ar;
   @FXML
    private TableColumn<Cours, String> equi;
    @FXML
    private TableColumn<Cours, Time> du;
    @FXML
    private TableColumn<Cours, Date> dates;

       @FXML
    ChoiceBox<String> choicem= new ChoiceBox<>();
    @FXML
  ChoiceBox<String> choicex= new ChoiceBox<>();
      @FXML
     private TextField finda;
       @FXML
     private TextField finde; 
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
     
     
     
     
     ArenaService ck =new ArenaService();
     CService csxx =new CService();
    EService vk =new EService();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         List<Arena> Aas;
         Aas = ck.readAll(); 
         ObservableList<String> items1 = FXCollections.observableArrayList();
         for (Arena c1 :Aas) {
             items1.add(c1.getNom());
         }
         choicem.setItems(items1);
         
         
         List<Equipe> Equis;
         Equis = vk.readAll(); 
         ObservableList<String> items2 = FXCollections.observableArrayList();
         for (Equipe v1 :Equis) {
             items2.add(v1.getNom());
         }
         choicex.setItems(items2);
         
       List<Cours> senl;
       senl = csxx.readAll();
       System.out.println(senl);
       ObservableList<Cours> couxx = FXCollections.observableArrayList(senl);
       
       name.setCellValueFactory(new PropertyValueFactory<> ("nom"));
       desc.setCellValueFactory(new PropertyValueFactory<> ("description"));
       dates.setCellValueFactory(new PropertyValueFactory<> ("dateseance"));
       du.setCellValueFactory(new PropertyValueFactory<> ("dure"));
       ar.setCellValueFactory(new PropertyValueFactory<> ("arenas_id"));
       equi.setCellValueFactory(new PropertyValueFactory<> ("equipex_id"));
       CsTable.setItems(couxx);
          
         
         
         


    }  
          @FXML
    private void getDataP(MouseEvent event){
        
       //Cours somp;
       //somp= CTable.getSelectionModel().getSelectedItem();
       
        Cours  boomxx =CsTable.getSelectionModel().getSelectedItem();
        int idl = boomxx.getId();
        System.out.println(" l'id = "+idl);   
int cvvkk=boomxx.getArenas_id();
System.out.println(" l'id = "+ cvvkk);   
 int cvxkk= boomxx.getEquipex_id()  ;
System.out.println(" l'id = "+ cvxkk); 
List<Arena> Arenas= ck.readAll();
       
        List<Equipe> Equipes= vk.readAll();
        for (Arena ck1 : Arenas){







             if(ck1.getId() == cvvkk){ 
                 String narena=ck1.getNom();    
        for(Equipe vk1 : Equipes){    
            if(vk1.getId() == cvxkk)
        {
           String nequipe=vk1.getNom();

      
       finda.setText(narena); 
       finde.setText(nequipe); 
       
       finda.setEditable(false);
       finde.setEditable(false);
        }
        }
             }
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
    
    
    
}
