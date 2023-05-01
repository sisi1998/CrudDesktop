/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


//import entities.Arena;
import Entities.Arena;
import Entities.Cours;
import Entities.Equipe;
import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
//import java.sql.Time;
import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.DataSource;

/**
 *
 * @author Mon Pc
 */
public class CService  {
    private final  Connection conn;

    public CService() {
        conn = DataSource.getInstance().getCnx();
    }

  //  @Override
    public void insert(Cours t) {
             String requete = "insert into Cours(nom,description,dateseance,dure,arenas_id,equipex_id)values"
                + "('" + t.getNom() + "','" + t.getDescription() + "'," + t.getDateseance() +"','"+ t.getDure()+ "',"+t.getArenas_id()+ t.getEquipex_id()+ ")";
      try {
            Statement ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void insertPst(Cours t) {
        String requete = "insert into Cours(nom,description,dateseance,dure,arenas_id,equipex_id) values(?,?,?,?,?,?)";
        try {
           // Date dns=Date.valueOf(p.getDns());
            
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setDate(3, t.getDateseance());
            pst.setTime(4, t.getDure());
            pst.setInt(5, t.getArenas_id());
            pst.setInt(6, t.getEquipex_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 //   @Override
    public void update(Cours t) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     /* public void supprimern(int id) {
            try {
            PreparedStatement res = conn.prepareStatement("DELETE FROM  cours WHERE id = "+id+"");//"DELETE FROM voyage WHERE id = "
            //res.setInt(1,t.getId());
            res.executeUpdate();
           
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
      
    }*/
      public void modifierxx(Cours t) throws SQLException {
        String re = "UPDATE  Cours SET nom = ?,description = ?,dateseance = ?,dure = ?,arenas_id = ?,equipex_id = ?  where id= ?";
       
           // Date dns=Date.valueOf(p.getDns());
            
            PreparedStatement psx = conn.prepareStatement(re);
            psx.setString(1,t.getNom());
            psx.setString(2,t.getDescription());
            psx.setDate(3,t.getDateseance());
            psx.setTime(4,t.getDure());
            psx.setInt(5,t.getArenas_id());
            psx.setInt(6,t.getEquipex_id());
            psx.setInt(7,t.getId());
            psx.executeUpdate();
        
    }

   // @Override
    public void delete(int id) {
        try {
            PreparedStatement res = conn.prepareStatement("DELETE FROM  cours WHERE id = "+id+"");//"DELETE FROM voyage WHERE id = "
            //res.setInt(1,t.getId());
            res.executeUpdate();
           System.out.println(" l'id = "+id+" a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
    }
    
  
  
  //  @Override
    public Cours readById(int id) {
           throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  
}
    
       /*public ArrayList findMeC() throws SQLException {
            ObservableList<String> listw = FXCollections.observableArrayList();
            String rq = "SELECT   Cours.id, Cours.nom, Cours.description, Cours.dateseance, Cours.dure,Cours.arenas_id,Cours.equipex_id,Arena.nom AS arena_nom, Equipe.nom AS equipe_nom "
                       + "FROM Cours  "
                       + "JOIN Arena  ON Cours.arenas_id = Arena.id "
                       + "JOIN Equipe  ON Cours.equipex_id = Equipe.id "
                       +"WHERE Equipe.Nom = ?";
            
            Statement sty = conn.createStatement();
            ResultSet resu = sty.executeQuery(rq);
            
  
}*/

   /* @Override
    public ArrayList<Cours> readAll() {
    ArrayList<Cours> list = new ArrayList<>();
    String requete = "SELECT co.id, co.nom, co.description, co.dateseance, co.dure, a.nom AS arena_nom, eq.nom AS nom_equipe"
                 + "FROM Cours co "
                 + "JOIN arena a ON co.arenas_id = a.id "
                 + "JOIN equipe eq ON co.equipe_id = ce.id ";
      
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
         
            while(rs.next()){
               
               int id =rs.getInt("id");
               String nom =rs.getString("nom");
               String description =rs.getString("description");
               Date dateseance =rs.getDate("dateseance");
               Time dure =rs.getTime("dure");
               String arenaNom = rs.getString("arena_nom");
               String equipeNom = rs.getString("nom_equipe");
               Cours a=new Cours(id,nom,description,dateseance,dure,new Arena(arenaNom), new Equipe(equipeNom));
               /*a.setId(rs.getInt("id"));
               a.setNom(rs.getString("nom"));
               a.setDescription(rs.getString("description"));
               a.setDateseance(rs.getDate("dateseance"));
               a.setDure(rs.getTime("dure"));
               //a.setArenas_id(rs.getInt("arenas_id"));
               //a.setEquipex_id(rs.getInt("equipex_id"));
               String arenaNom = rs.getString("arena_nom");
               String equipeNom = rs.getString("nom_equipe");
              //Cours a=new Cours(arenaNom,equipeNom);
               a.setId(rs.getInt("id"));
                list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }*/
  //   @Override
    public ArrayList<Cours> readAll() {
          ArrayList<Cours> list = new ArrayList<>();
       String requete = "SELECT C.id, C.nom, C.description, C.dateseance, C.dure,C.arenas_id,C.equipex_id, A.nom AS arena_nom, E.nom AS equipe_nom "
                       + "FROM Cours C "
                       + "JOIN Arena A ON C.arenas_id = A.id "
                       + "JOIN Equipe E ON C.equipex_id = E.id ";
                     

      
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
         
            while(rs.next()){
               
              int id = rs.getInt("id");
              String nom = rs.getString("nom");
              String description = rs.getString("description");
              Date dateseance = rs.getDate("dateseance");
              Time dure = rs.getTime("dure");
              int arenasid = rs.getInt("arenas_id");
              int equipexid = rs.getInt("equipex_id");
             // String arenaNom = rs.getString("arena_nom");
              //String equipeNom = rs.getString("equipe_nom");
              Cours a=new Cours(id,nom,description,dateseance,dure,arenasid,equipexid);
              list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
public ObservableList<Cours> chercherCours(String chaine){
          String sql="SELECT * FROM cours WHERE (nom LIKE ? )";
            
             //Connection cnx= MyDB.getInsatnce().getConnection();
            String ch=""+chaine+"%";
         System.out.println(sql);
            ObservableList<Cours> myList= FXCollections.observableArrayList();
        try {
           
            //Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
             Statement ste = conn.createStatement(); 
              PreparedStatement stee =conn.prepareStatement(sql);  
            stee.setString(1,ch);
           
         System.out.println(ste);
         
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
           
               Cours a=new Cours();
               a.setNom(rs.getString("nom"));
               a.setDescription(rs.getString("description"));
               a.setDateseance(rs.getDate("dateseance"));
               a.setDure(rs.getTime("dure"));
               a.setArenas_id(rs.getInt("arenas_id"));
               a.setEquipex_id(rs.getInt("equipex_id"));
               a.setId(rs.getInt("id"));
                myList.add(a);
                System.out.println("trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
 

   
}

