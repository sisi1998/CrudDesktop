
package Services;

import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataSource;

/**
 *
 * @author Ousse
 */
public class UserService implements IServiceU<User> {
    
    private Connection conn;

    public UserService() {
        conn = DataSource.getInstance().getCnx();
    }
    
    

     @Override
    public void insert(User o) {
        String requete="insert into user (nom,prenom,email,mdp,role,date_birth,image) values"
                + " (?,?,?,?,?,?,?)";
        
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete);
            pst.setString(1,o.getNom());
            pst.setString(2,o.getPrenom());
            pst.setString(3, o.getEmail());
            pst.setString(4, o.getMdp());
            pst.setString(5, o.getRole());
                pst.setDate(6, java.sql.Date.valueOf(o.getDate_birth()));
           pst.setString(7, o.getImage());
            
            pst.executeUpdate();
            System.out.print("personne ajouté");
        
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    

    @Override
    public void delete(int id) {
         String requete="DELETE FROM user WHERE id ="+id;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
            System.out.println("personne supprimé avec succées ");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }}

    

    @Override
   public ArrayList<User> readAll() {
String requete = "SELECT * FROM user";
ArrayList<User> list = new ArrayList<>();
try {
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery(requete);
while (rs.next()) {
User u = new User(
rs.getInt("id"),
rs.getString("nom"),
rs.getString("prenom"),
rs.getString("email"),
rs.getString("mdp"),
rs.getString("role"),
rs.getDate("date_birth").toLocalDate(),
rs.getBoolean("is_blocked"),
rs.getString("image")
);
list.add(u);
}
} catch (SQLException ex) {
Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
}
return list;
}

    @Override
    public void update(User o) {
         String requete = "UPDATE user SET nom=?, prenom=?, email=?, mdp=?, role=?, date_birth=?,  image=?, is_blocked=? WHERE id=?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst = conn.prepareStatement(requete);
            pst.setString(1,o.getNom());
            pst.setString(2,o.getPrenom());
            pst.setString(3, o.getEmail());
            pst.setString(4, o.getMdp());
            pst.setString(5, o.getRole());
            pst.setDate(6, java.sql.Date.valueOf(o.getDate_birth()));
         //   if(o.isIs_blocked()){pst.setInt(7,1);}else{pst.setInt(7,0);}
            pst.setString(7, o.getImage());
            pst.setBoolean(8,o.isIs_blocked());
            pst.setInt(9,o.getId());
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }}

    @Override
    public User readById(int id) {
       
            String requete = "SELECT * FROM user WHERE id=?";
            User u = null;
                try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
        u = new User(
        rs.getInt("id"),
        rs.getString("nom"),
        rs.getString("prenom"),
        rs.getString("email"),
        rs.getString("mdp"),
        rs.getString("role"),
        rs.getDate("date_birth").toLocalDate(),
        rs.getBoolean("is_blocked"),
        rs.getString("image")
        );
        }
        } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
        }
    
    public User readByMail(String email) {
       
            String requete = "SELECT * FROM user WHERE email=?";
            User u = null;
                try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
        u = new User(
        rs.getInt("id"),
        rs.getString("nom"),
        rs.getString("prenom"),
        rs.getString("email"),
        rs.getString("mdp"),
        rs.getString("role"),
        rs.getDate("date_birth").toLocalDate(),
        rs.getBoolean("is_blocked"),
        rs.getString("image")
                ,
                rs.getString("reset_token")
        );
        }
        } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
        }
    
    public ArrayList<User> SearchByMail(String searchText) {
    String requete = "SELECT * FROM user WHERE email LIKE ?";
    ArrayList<User> list = new ArrayList<>();
    try {
        PreparedStatement st = conn.prepareStatement(requete);
        st.setString(1, "%" + searchText + "%");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            User u = new User(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                rs.getString("mdp"),
                rs.getString("role"),
                rs.getDate("date_birth").toLocalDate(),
                rs.getBoolean("is_blocked"),
                rs.getString("image")
            );
            list.add(u);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}

    
//    public User readByMail(String email) {
//       
//            String requete = "SELECT * FROM user WHERE email=?";
//            User u = null;
//                try {
//        PreparedStatement pst = conn.prepareStatement(requete);
//        pst.setString(1, email);
//        ResultSet rs = pst.executeQuery();
//        if (rs.next()) {
//        u = new User(
//        rs.getInt("id"),
//        rs.getString("nom"),
//        rs.getString("prenom"),
//        rs.getString("email"),
//        rs.getString("mdp"),
//        rs.getString("role"),
//        rs.getDate("date_birth").toLocalDate(),
//        rs.getBoolean("is_blocked"),
//        rs.getString("image")
//
//        );
//        }
//        } catch (SQLException ex) {
//        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return u;
//        }
    
    public void Blocking(int id, boolean a){
    
        if(a){
         String requete = "UPDATE user SET is_blocked = 1";
        }
        else{
        
         String requete = "UPDATE user SET is_blocked = 1";
        
        }
    
    }
    
    public void rest_token(User u, String token){
        String requete = "UPDATE user set reset_token=? WHERE id=?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst = conn.prepareStatement(requete);
            pst.setString(1,token);
            pst.setInt(2,u.getId());
            pst.executeUpdate();
    System.out.println("Token rested successfully");
    }
    
    catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    public void rest_mdp(User u, String mdp){
        String requete = "UPDATE user set mdp=? WHERE id=?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst = conn.prepareStatement(requete);
            pst.setString(1,mdp);
            pst.setInt(2,u.getId());
            pst.executeUpdate();
    System.out.println("Token rested successfully");
    }
    
    catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
}
