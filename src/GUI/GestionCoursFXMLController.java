/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.Alert;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import Entities.Arena;
import Entities.Equipe;
import Entities.Cours;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
//import javafx.scene.text.Text;
import Services.ArenaService;
import java.time.YearMonth;
import Services.CService;
import Services.EService;



/**
 * FXML Controller class
 *
 * @author Mon Pc
 */
public class GestionCoursFXMLController implements Initializable {
     @FXML
    private TableView<Cours> CoursTable;
 @FXML
    private TableColumn<Cours, String> nameColl;
    @FXML
    private TableColumn<Cours, String> descCol;
    @FXML
    private TableColumn<Cours, String> arCol;
@FXML
    private TableColumn<Cours, String> equiCol;
    @FXML
    private TableColumn<Cours, Time> duCol;
    @FXML
    private TableColumn<Cours, Date> datesCol;
     @FXML
    private TextField Recherche;
    @FXML
    private TextField txtnom;
    @FXML
    private TextArea txtdescr;
    @FXML
    private JFXDatePicker DateF;
    @FXML
    private JFXTimePicker Duree;
      @FXML
    ChoiceBox<String> choiceE= new ChoiceBox<>();
    @FXML
  ChoiceBox<String> choice= new ChoiceBox<>();
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
     ArenaService c =new ArenaService();
     CService cs =new CService();
    EService v =new EService();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         List<Arena> Arenas;
         Arenas = c.readAll(); 
         ObservableList<String> items1 = FXCollections.observableArrayList();
         for (Arena c1 :Arenas) {
             items1.add(c1.getNom());
         }
         choice.setItems(items1);
         
         
         List<Equipe> Equipes;
         Equipes = v.readAll(); 
         ObservableList<String> items2 = FXCollections.observableArrayList();
         for (Equipe v1 :Equipes) {
             items2.add(v1.getNom());
         }
         choiceE.setItems(items2);
         
       List<Cours> sen;
       sen = cs.readAll();
       System.out.println(sen);
       ObservableList<Cours> cou = FXCollections.observableArrayList(sen);
       
       nameColl.setCellValueFactory(new PropertyValueFactory<> ("nom"));
       descCol.setCellValueFactory(new PropertyValueFactory<> ("description"));
       datesCol.setCellValueFactory(new PropertyValueFactory<> ("dateseance"));
       duCol.setCellValueFactory(new PropertyValueFactory<> ("dure"));
       arCol.setCellValueFactory(new PropertyValueFactory<> ("arenas_id"));
       equiCol.setCellValueFactory(new PropertyValueFactory<> ("equipex_id"));
       CoursTable.setItems(cou);
          
         
         
         
}
    
   @FXML
    private void RefreshC(ActionEvent event) {
       
           List<Cours> sen;
       sen = cs.readAll();
    
       ObservableList<Cours> cou = FXCollections.observableArrayList(sen);
       nameColl.setCellValueFactory(new PropertyValueFactory<> ("nom"));
       descCol.setCellValueFactory(new PropertyValueFactory<> ("description"));
       datesCol.setCellValueFactory(new PropertyValueFactory<> ("dateseance"));
       duCol.setCellValueFactory(new PropertyValueFactory<> ("dure"));
       arCol.setCellValueFactory(new PropertyValueFactory<> ("arenas_id"));
       equiCol.setCellValueFactory(new PropertyValueFactory<> ("equipex_id"));
       CoursTable.setItems(cou);
          
        
    }   
   
    
    @FXML
    private void AjouterC(ActionEvent event) throws SQLException {
        
        String cc = choice.getSelectionModel().getSelectedItem();
        List<Arena> Arenas= c.readAll();
        String xx = choiceE.getSelectionModel().getSelectedItem();
        List<Equipe> Equipes= v.readAll();
        for (Arena c1 : Arenas){
             if(c1.getNom().equals(cc)){ 
                 int idA=c1.getId();    
        for(Equipe v1 : Equipes){    
            if(v1.getNom().equals(xx))
        {
       
           int idC=v1.getId();      
        
        Cours  pm = new Cours();
        pm.setNom(txtnom.getText());
        pm.setDescription(txtdescr.getText());
        pm.setDateseance(Date.valueOf(DateF.getValue()));
        pm.setDure(Time.valueOf(Duree.getValue()));
        pm.setArenas_id(idA);
        pm.setEquipex_id(idC);
        cs.insertPst(pm);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ajout");
            alert.setHeaderText("succès.");
            alert.showAndWait();
       
        }
    }}}}
   
    /*@FXML
    private void Supprimery() throws SQLException{
        
        //Arena a2 = ArenaTable.getSelectionModel().getSelectedItem().getId();
        int sa =CoursTable.getSelectionModel().getSelectedItem().getId();
        cs.supprimern(sa);
    }*/
    @FXML
    private void SupprimerC(ActionEvent event) throws SQLException  {
       int sa=CoursTable.getSelectionModel().getSelectedItem().getId();
       System.out.println("l'id = "+sa+" a été supprimer avec succès...");
       cs.delete(sa);
       CoursTable.getItems().removeAll(CoursTable.getSelectionModel().getSelectedItem());
    
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("suppression");
            alert.setHeaderText("succès.");
            alert.showAndWait();
    }
       @FXML
    private void RechercheX(KeyEvent event) {
        String chaine = Recherche.getText();
        System.out.println(" = "+chaine+" a été ");
        populateTable(cs.chercherCours(chaine));
    }
 private void populateTable(ObservableList<Cours> branlist) {
        CoursTable.setItems(branlist);

    }
    /* @FXML
    private void SupprimerC(ActionEvent event) throws SQLException {
       // cs.supprimerc(CoursTable.getSelectionModel().getSelectedItem().getId())
        //CoursTable.getItems().removeAll(CoursTable.getSelectionModel().getSelectedItem());
       //Cours sen;
       //sen= CoursTable.getSelectionModel().getSelectedItem();
       //cs.supprimerc(sen);
    }*/
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
    private void getDataC(MouseEvent event){
        
       
       Cours sen;
       sen= CoursTable.getSelectionModel().getSelectedItem();
       txtnom.setText(sen.getNom());
       txtdescr.setText(sen.getDescription());
       DateF.setValue(LocalDate.from(sen.getDateseance().toLocalDate()));
       Duree.setValue(LocalTime.from(sen.getDure().toLocalTime()));
        Cours  pmx =CoursTable.getSelectionModel().getSelectedItem();
        int idm = pmx.getId();
        System.out.println(" l'id = "+idm);   
int cvv=pmx.getArenas_id();
System.out.println(" l'id = "+ cvv);   
 int cvx= pmx.getEquipex_id()  ;
System.out.println(" l'id = "+ cvx); 
List<Arena> Arenas= c.readAll();
       
        List<Equipe> Equipes= v.readAll();
        for (Arena c1 : Arenas){







             if(c1.getId() == cvv){ 
                 String arenaname=c1.getNom();    
        for(Equipe v1 : Equipes){    
            if(v1.getId() == cvx)
        {
           String equipename=v1.getNom();

      
        choice.setValue(arenaname); 
       choiceE.setValue(equipename); 
        }}}}
    }
   
  
    
         @FXML
    private void ModifyC(ActionEvent event) throws SQLException {
        
     
        Cours  pxm =CoursTable.getSelectionModel().getSelectedItem();
        int idm = pxm.getId();
        System.out.println(" l'id = "+idm);
        
        
        Cours pxn = new Cours();
        pxn.setNom(txtnom.getText());
        pxn.setDescription(txtdescr.getText());
        pxn.setDateseance(Date.valueOf(DateF.getValue()));
        pxn.setDure(Time.valueOf(Duree.getValue()));
        
        String cc = choice.getSelectionModel().getSelectedItem();
        List<Arena> Arenas= c.readAll();
        String xx = choiceE.getSelectionModel().getSelectedItem();
        List<Equipe> Equipes= v.readAll();
        for (Arena c1 : Arenas){
             if(c1.getNom().equals(cc)){ 
                 int idA=c1.getId();    
        for(Equipe v1 : Equipes){    
            if(v1.getNom().equals(xx))
        {
       
           int idC=v1.getId(); 
           
        
        pxn.setArenas_id(idA);
        pxn.setEquipex_id(idC);
        pxn.setId(idm);
          try {
            cs.modifierxx(pxn);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification");
            alert.setHeaderText("succès.");
            alert.showAndWait();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
      
      
        }}}}}  
        }     
  
    
    


        
     /*@FXML
    private TextField txtx;
    @FXML
    private TextField txtd;
/*
    @FXML
    private TextField txtdu;
        @FXML
    private JFXDatePicker DateF;
          @FXML
    private JFXTimePicker Duree;
*//*
    @FXML
    private Label label;
      @FXML
    private Button btn;
    @FXML
    private TableView<Cours> CoursTable;
    @FXML
    private TableColumn<Cours, String> nameColL;
    @FXML
    private TableColumn<Cours, String> descCol;*/
/*
    @FXML
    private TableColumn<Cours, String> duCol;
    @FXML
    private TableColumn<Cours, String> datesCol;*/
   /* CService ss= new CService();
      @FXML
    private void getDataC(MouseEvent event){
        
        Cours u = CoursTable.getSelectionModel().getSelectedItem();
       
        txtx.setText(u.getNom());
       txtd.setText(u.getDescription());
       
    }
    /* @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
       // Cours a1=new Cours(txtx.getText(),txtd.getText(),txtdu.getText());Duree
       
        Cours ps=new Cours();
        ps.setNom(txtx.getText());
        ps.setSeance(Date.valueOf(DateF.getValue()));
        ps.setDure(Time.valueOf(Duree.getValue()));
         ps.setDescription(txtd.getText());
         ss.insertPst(ps);
        
    }*/
      /* @FXML
    private void Refresh(ActionEvent event) {
       
             List<Coursf> ser = ss.readAll();
            ObservableList<Coursf> olp = FXCollections.observableArrayList(ser);
            nameColL.setCellValueFactory(new PropertyValueFactory<> ("nom"));
            descCol.setCellValueFactory(new PropertyValueFactory<> ("surface"));
            duCol.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
            datesCol.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
            CoursfTable.setItems(olp);
    }*/
  /*  @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
   /* List<Cours> ser = ss.readAll();
            ObservableList<Cours> olp = FXCollections.observableArrayList(ser);
            nameColL.setCellValueFactory(new PropertyValueFactory<> ("nom"));
            descCol.setCellValueFactory(new PropertyValueFactory<> ("surface"));
            duCol.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
            datesCol.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
            CoursTable.setItems(olp);
           
    }
    

   

   

    }*/
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    

