/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Arena;
import Entities.Cours;
import Entities.Equipe;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Services.ArenaService;
import Services.CService;
import Services.EService;

/**
 * FXML Controller class
 *
 * @author Mon Pc
 */
public class CoursfronFXMLController implements Initializable {
     @FXML
    private TableView<Cours> CTable;
 @FXML
    private TableColumn<Cours, String> nameC;
    @FXML
    private TableColumn<Cours, String> descC;
    @FXML
    private TableColumn<Cours, String> arC;
@FXML
    private TableColumn<Cours, String> equiC;
    @FXML
    private TableColumn<Cours, Time> duC;
    @FXML
    private TableColumn<Cours, Date> datesC;
     @FXML
    ChoiceBox<String> ChoiceEqui= new ChoiceBox<>();
     @FXML
    ListView<Cours> ListCours;
      @FXML
     private TextField findar;
       @FXML
     private TextField findeq; 
   EService k =new EService();
   CService op =new CService();
   ArenaService Arx =new ArenaService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
         
         List<Equipe> Equipem;
         Equipem= k.readAll(); 
         ObservableList<String> items5 = FXCollections.observableArrayList();
         for (Equipe k1 :Equipem) {
             items5.add(k1.getNom());
         }
        ChoiceEqui.setItems(items5);
        
        //String strE = ChoiceEqui.getValue();  
         List<Cours> Crs;
        Crs= op.readAll();
       // ObservableList<Cours> itemx = FXCollections.observableArrayList(Crs);
      //ListCours.setItems(itemx);
       
    }    
  @FXML
    private void FindMeCours(MouseEvent event) throws SQLException {
        // String chaineC="";
       String mo = ChoiceEqui.getSelectionModel().getSelectedItem(); 
       List<Cours> Crs = op.readAll(); 
       List<Equipe> Equipem= k.readAll(); 
       System.out.println(mo);
       List<Cours> ser2= new ArrayList();
      
         for(Equipe k1 :Equipem){    
         if(k1.getNom().equals(mo)){
               int idk=k1.getId();
               System.out.println(idk);
            
          for( Cours op1 :Crs){
            if(op1.getEquipex_id() == k1.getId()){
              ser2.add(op1);
             //String chx=op1.getNom();
            // String chy=op1.getDescription();
             //String chg=chx+chy;
             //mefind.setText(chg);
             //System.out.println("yup"+chx+chy);  
           
       
      

         }}
         }
                
   }
     ObservableList<Cours> coup = FXCollections.observableArrayList(ser2);
       nameC.setCellValueFactory(new PropertyValueFactory<> ("nom"));
       descC.setCellValueFactory(new PropertyValueFactory<> ("description"));
       datesC.setCellValueFactory(new PropertyValueFactory<> ("dateseance"));
       duC.setCellValueFactory(new PropertyValueFactory<> ("dure"));
       arC.setCellValueFactory(new PropertyValueFactory<> ("arenas_id"));
       //equiC.setCellValueFactory(new PropertyValueFactory<> ("equipex_id"));
       CTable.setItems(coup);
    
   }
      @FXML
    private void getDataK(MouseEvent event){
        
       //Cours somp;
       //somp= CTable.getSelectionModel().getSelectedItem();
       
        Cours  boomx =CTable.getSelectionModel().getSelectedItem();
        int idd = boomx.getId();
        System.out.println(" l'id = "+idd);   
int cvvk=boomx.getArenas_id();
System.out.println(" l'id = "+ cvvk);   
 int cvxk= boomx.getEquipex_id()  ;
System.out.println(" l'id = "+ cvxk); 
List<Arena> Arenas= Arx.readAll();
       
        List<Equipe> Equipes= k.readAll();
        for (Arena Arx1 : Arenas){







             if(Arx1.getId() == cvvk){ 
                 String namearena=Arx1.getNom();    
        /*for(Equipe k1 : Equipes){    
            if(k1.getId() == cvxk)
        {
           String nameequipe=k1.getNom();*/

      
       findar.setText(namearena); 
       //findeq.setText(nameequipe); 
       findar.setEditable(false);
       //findeq.setEditable(false);
        }
        }
    }



}
   
  
