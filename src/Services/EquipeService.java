/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Equipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataSource;

/**
 *
 * @author Ousse
 */
public class EquipeService implements IServiceU<Equipe>{
    private Connection conn;

    public EquipeService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Equipe o) {
        String requete="INSERT INTO `equipe`( `nom`, `logo`, `nb_joueurs`, `origine`) values (?,?,?,?)";
        
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete);
            pst.setString(1,o.getNom());
            pst.setString(2,o.getLogo());
            pst.setInt(3, o.getNb_joueurs());
            pst.setString(4, o.getOrigine());
            
            
            pst.executeUpdate();
            System.out.print("equipe ajouté");
        
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    
    }

    @Override
    public void update(Equipe o)  {
         String requete = "UPDATE equipe SET nom=?, logo=?, nb_joueurs=?, origine=? WHERE id=?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst = conn.prepareStatement(requete);
            pst.setString(1,o.getNom());
            pst.setString(2,o.getLogo());
            pst.setInt(3, o.getNb_joueurs());
            pst.setString(4, o.getOrigine());
            pst.setInt(5,o.getId());
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
    }}

    @Override
    public void delete(int id) {
        String requete="DELETE FROM equipe WHERE id ="+id;try {
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
            System.out.println("equipe supprimé avec succées ");
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public Equipe readById(int id) {
        String requete = "SELECT * FROM equipe WHERE id=?";
            Equipe e = null;
            try{
            PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            e = new Equipe(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
             rs.getString(5)
            );
            }
            }catch (SQLException ex) {
        Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return e;
    }

    @Override
    public ArrayList<Equipe> readAll() {
String requete = "SELECT * FROM equipe";
ArrayList<Equipe> list = new ArrayList<>();
try {
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery(requete);
while (rs.next()) {
Equipe u = new Equipe(
rs.getInt("id"),
rs.getString("nom"),
rs.getString("logo"),
rs.getInt("nb_joueurs"),
rs.getString("origine")
);
list.add(u);
}
} catch (SQLException ex) {
Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
}
return list;
}
 
 public ArrayList<Equipe> SearchByNom(String searchText) {
    String requete = "SELECT * FROM equipe WHERE nom LIKE ?";
    ArrayList<Equipe> list = new ArrayList<>();
    try {
        PreparedStatement st = conn.prepareStatement(requete);
        st.setString(1, "%" + searchText + "%");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Equipe u = new Equipe(
rs.getInt("id"),
rs.getString("nom"),
rs.getString("logo"),
rs.getInt("nb_joueurs"),
rs.getString("origine")
);
            list.add(u);
        }
    } catch (SQLException ex) {
        Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;   
 }
}
