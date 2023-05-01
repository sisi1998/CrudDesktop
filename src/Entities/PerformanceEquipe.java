/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class PerformanceEquipe {
    private Integer id,victoires,defaites,nuls,but_encaisse,but_marque;
     private List<Equipe> equipe = new ArrayList<Equipe>();
   
  public PerformanceEquipe() {
      
    }
    public PerformanceEquipe(Integer id,Integer victoires, Integer defaites, Integer nuls, Integer but_encaisse, Integer but_marque ) {
        this.id = id;
        this.victoires = victoires;
        this.defaites = defaites;
        this.nuls = nuls;
         this.but_encaisse = but_encaisse;
        this.but_marque = but_marque;
      
    }

    public PerformanceEquipe(Integer victoires, Integer defaites, Integer nuls, Integer but_encaisse, Integer but_marque) {
        this.victoires = victoires;
        this.defaites = defaites;
        this.nuls = nuls;
        this.but_encaisse = but_encaisse;
        this.but_marque = but_marque;
    }
    
     public Integer getId() {
        return id;
    }

    public Integer getVictoires() {
        return victoires;
    }

    public Integer getDefaites() {
        return defaites;
    }

    public Integer getNuls() {
        return nuls;
    }

    public Integer getBut_encaisse() {
        return but_encaisse;
    }

    public Integer getBut_marque() {
        return but_marque;
    }
    public void setid(Integer id) {
        this.id = id;
    }

    public void setVictoires(Integer victoires) {
        this.victoires = victoires;
    }

    public void setDefaites(Integer defaites) {
        this.defaites = defaites;
    }

    public void setNuls(Integer nuls) {
        this.nuls = nuls;
    }

    public void setBut_encaisse(Integer but_encaisse) {
        this.but_encaisse = but_encaisse;
    }

    public void setBut_marque(Integer but_marque) {
        this.but_marque = but_marque;
    }

    @Override
    public String toString() {
        return "PerformanceEquipe{" + "id=" + id + ", victoires=" + victoires + ", defaites=" + defaites + ", nuls=" + nuls + ", but_encaisse=" + but_encaisse + ", but_marque=" + but_marque + ", equipe=" + equipe + '}';
    }

   
   
    
}
