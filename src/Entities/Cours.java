/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.io.Serializable;
import java.time.LocalDate;
import Entities.Arena;
import Entities.Equipe;


import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Mon Pc
 */


 

public class Cours{
private Equipe equipe ;
   private Arena arena ;
   private int id,arenas_id,equipex_id;
   private String nom,description,equipeNom,arenaNom;
   private Date dateseance;
    private Time dure;
    private LocalDate dns;

    public Cours() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Cours(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
        public Cours(int id,String nom, String description) {
         this.id = id;     
        this.nom = nom;
        this.description = description;
    }

    public Cours(int id, String nom, String description, Date dateseance, Time dure, String arenaNom, String equipeNom) {
         this.id = id;   
        this.nom = nom;
        this.description = description;
        this.dateseance =dateseance;
         this.dure = dure;
       this.arenaNom = arenaNom;
        this.equipeNom = equipeNom;
    }
       public Cours(int id, String nom, String description, Date dateseance, Time dure,int arenas_id,int equipex_id) {
         this.id = id;   
        this.nom = nom;
        this.description = description;
        this.dateseance =dateseance;
         this.dure = dure;
         this.arenas_id = arenas_id;
        this.equipex_id = equipex_id;
    }
    public Cours(int id, String nom, String description, Date dateseance, Time dure, Arena arena, Equipe equipe) {
         this.id = id;   
        this.nom = nom;
        this.description = description;
        this.dateseance =dateseance;
         this.dure = dure;
       this.arena = arena;
        this.equipe = equipe;
    }

   
    
    public LocalDate getDns() {
        return dns;
    }
     public Cours (String nom , String description,Time dure ) {
        this.nom = nom;
        this.description = description;
        this.dure = dure;
      
    }

    /**
     *
     * @param id
     * @param nom
     * @param description
     * @param dateseance
     */
    public Cours (int id,String nom , String description,Date dateseance ) {
         this.id = id;   
        this.nom = nom;
        this.description = description;
        this.dateseance =dateseance;
      
    }
 
   public Cours (int id,String nom , String description,Date dateseance,Time dure ) {
         this.id = id;   
        this.nom = nom;
        this.description = description;
        this.dateseance =dateseance;
       this.dure = dure;
    }
   
    public Cours (String nom , String description,Time dure,Date dateseance ) {
        this.nom = nom;
        this.description = description;
        this.dure = dure;
        this.dateseance = dateseance;
    }
   
   public Cours (int id,String nom , String description,Time dure,Date dateseance) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dure = dure;
        this.dateseance = dateseance;
    } 
   
     public Cours (int id,String nom , String description,Time dure,Date dateseance,int arenas_id,int equipex_id ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dure = dure;
        this.dateseance = dateseance;
        this.arenas_id = arenas_id;
        this.equipex_id = equipex_id;
    } 
   
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom= nom;
    }
      public String getarenaNom() {
        return arenaNom;
    }

    public void setarenaNom(String arenaNom) {
        this.arenaNom= arenaNom;
    }
      public String getequipeNom() {
        return equipeNom;
    }

    public void setequipeNom(String equipeNom) {
        this.equipeNom = equipeNom;
    }
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description= description;
    }
        public Time getDure() {
        return dure;
    }

    public void setDure(Time dure) {
        this.dure= dure;
    }
          public Date getDateseance() {
        return dateseance;
    }

    /**
     *
     * @param dateseance
     */
    public void setDateseance(Date dateseance) {
        this.dateseance= dateseance;
    }
   public int getEquipex_id() {
        return equipex_id;
    }

    public void setEquipex_id(int equipex_id) {
      this.equipex_id= equipex_id;  
    } 
             public int getArenas_id() {
        return arenas_id;
    }
    public void setArenas_id(int arenas_id) {
        this.arenas_id = arenas_id;
    }

     public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena= arena;
    }
        public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe (Equipe equipe) {
        this.equipe= equipe;
    }
    @Override
    public String toString() {
        return "cours{" + "id=" + id + ", Nom=" + nom + ", Description=" + description + ", date de seance ="+ dateseance +",dure ="+ dure + "}";
    }
}
