    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import Entities.PerformanceEquipe;
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
 * @author Admin
 */
public class PerformanceEquipeService implements IServiceU<PerformanceEquipe> {
   
    private Connection conn;

    public PerformanceEquipeService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(PerformanceEquipe o) {
        String requete="INSERT INTO `performance_equipe`( `victoires`, `defaites`, `nuls`, `but_encaisse`, `but_marque`) values (?,?,?,?,?)";
        
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, o.getVictoires());
            pst.setInt(2, o.getDefaites());
            pst.setInt(3, o.getNuls());
            pst.setInt(4, o.getBut_encaisse());
            pst.setInt(5, o.getBut_marque());
            
            
            pst.executeUpdate();
            System.out.print(" performance equipe ajouté");
        
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceEquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    
    }

    @Override
    public void update(PerformanceEquipe o)  {
         String requete = "UPDATE performance_equipe SET victoires=?, defaites=?, nuls=?, but_encaisse=?, but_marque=? WHERE id=?";
    try {
        PreparedStatement pst ;
        pst = conn.prepareStatement(requete);
        pst.setInt(1, o.getVictoires());
        pst.setInt(2, o.getDefaites());
        pst.setInt(3, o.getNuls());
        pst.setInt(4, o.getBut_encaisse());
        pst.setInt(5, o.getBut_marque());
        pst.setInt(6,o.getId());
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(PerformanceEquipeService.class.getName()).log(Level.SEVERE, null, ex);
    }}

    @Override
    public void delete(int id) {
        String requete="DELETE FROM performance_equipe WHERE id ="+id;try {
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
            System.out.println("equipe supprimé avec succées ");
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceEquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public PerformanceEquipe readById(int id) {
        String requete = "SELECT * FROM performance_equipe WHERE id=?";
            PerformanceEquipe e = null;
            try{
            PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            e = new PerformanceEquipe(
                    
            rs.getInt(1),
            rs.getInt(2),
            rs.getInt(3),
            rs.getInt(4),
            rs.getInt(5),
            rs.getInt(6)
            );
            }
            }catch (SQLException ex) {
        Logger.getLogger(PerformanceEquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return e;
    }

    @Override
    public ArrayList<PerformanceEquipe> readAll() {
String requete = "SELECT * FROM performance_equipe";
ArrayList<PerformanceEquipe> list = new ArrayList<>();
try {
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery(requete);
while (rs.next()) {
PerformanceEquipe u = new PerformanceEquipe(
rs.getInt("id"),
rs.getInt("victoires"),
rs.getInt("defaites"),
rs.getInt("nuls"),
rs.getInt("but_encaisse"),
rs.getInt("but_marque")
);
list.add(u);
}
} catch (SQLException ex) {
Logger.getLogger(PerformanceEquipeService.class.getName()).log(Level.SEVERE, null, ex);
}
return list;
}
    
}
