/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Arena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataSource;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Mon Pc
 */

public class ArenaService {
private final  Connection conn;

    public ArenaService() {
        conn = DataSource.getInstance().getCnx();
    }

//    @Override
     public void insert(Arena t) {
          String requete = "insert into Arena(nom,surface,adresse) values"
                + "('" + t.getNom() + "','" + t.getSurface() + "," + t.getId() +"',"+ t.getAdresse()+ ")";
      try {
            Statement ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ArenaService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     public void insertPst(Arena t) {
        String requete = "insert into Arena(nom,surface,adresse,image) values(?,?,?,?)";
        try {
           // Date dns=Date.valueOf(p.getDns());
            
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getSurface());
            pst.setString(3, t.getAdresse());
            pst.setString(4,t.getImage());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArenaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  //  @Override
    public void update(Arena t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void supprimer (int id) throws SQLException {
            try {
            PreparedStatement req = conn.prepareStatement("delete from arena where id= "+id+"");
            //req.setInt(1, t.getId());
            req.executeUpdate();
            System.out.println(" l'id = "+id+" a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
    }

    
   // @Override
    public Arena readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modifier(Arena t) throws SQLException {
        
        String req = "update Arena set nom=?,surface=?,adresse=?,image=? where id= ? ";
        
        PreparedStatement pst = conn.prepareStatement(req);
        pst.setString(1, t.getNom());
        pst.setString(2, t.getSurface());
        pst.setString(3, t.getAdresse());
        pst.setString(4,t.getImage());
        pst.setInt(5, t.getId());
        pst.executeUpdate();
        
    }
  //  @Override
    public ArrayList<Arena> readAll() {
  String requete = "select * from Arena";
        ArrayList<Arena> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
               Arena a=new Arena(rs.getInt(1),rs.getString("nom"),rs.getString("surface"),rs.getString("adresse"),rs.getString("image"));
                list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArenaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
     
  }

  //  @Override
    public void delete(int id) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public ObservableList<Arena> chercherArena(String cha){
          String sqll="SELECT * FROM arena WHERE (nom LIKE ? )";
            
             //Connection cnx= MyDB.getInsatnce().getConnection();
            String ch=""+cha+"%";
         System.out.println(sqll);
            ObservableList<Arena> myListA= FXCollections.observableArrayList();
        try {
           
            //Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
             Statement st= conn.createStatement(); 
              PreparedStatement steee =conn.prepareStatement(sqll);  
            steee.setString(1,ch);
           
         System.out.println(st);
         
            ResultSet rs = steee.executeQuery();
            while (rs.next()){
           

               Arena a=new Arena(rs.getInt(1),rs.getString("nom"),rs.getString("surface"),rs.getString("adresse"),rs.getString("image"));
                myListA.add(a);
                System.out.println("trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myListA;
      }
 
 
}
