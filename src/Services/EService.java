/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Equipe;
import java.sql.Connection;
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
 * @author Mon Pc
 */
public class EService  {

    private  Connection conn;

    public EService() {
        conn = DataSource.getInstance().getCnx();
    }

    
    
    
    
   
    public ArrayList<Equipe> readAll() {
          String requete = "select * from Equipe";
        ArrayList<Equipe> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
               Equipe a=new Equipe(rs.getInt(1),rs.getString("nom"));
                list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

   
    
}
