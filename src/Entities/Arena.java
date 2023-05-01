/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Mon Pc
 */
public class Arena {
    private int id;
    private String nom,surface,image,adresse;
    private LocalDate dns;
    public static String pathfile; 
 public static String filename="";
    public Arena() {
        
    }

   /* public Arena(String arenaNom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

   public Arena(String nom) {
        this.nom = nom;
    }
 


    public LocalDate getDns() {
        return dns;
    }
    
     public Arena(String nom , String surface,String adresse) {
        this.nom = nom;
        this.surface = surface;
        this.adresse = adresse;
    }
       public Arena(int id,String nom ) {
        this.id = id;
        this.nom = nom;}
    public Arena(int id,String nom , String surface,String adresse,String image) {
        this.id = id;
        this.nom = nom;
        this.surface = surface;
        this.image = image;
        this.adresse = adresse;
    }
    public Arena(int id,String nom , String surface,String adresse) {
        this.id = id;
        this.nom = nom;
        this.surface = surface;
        this.adresse = adresse;
    }

    public Arena(String nom , String surface,String adresse,String image) {
        this.nom = nom;
        this.surface = surface;
        this.image = image;
        this.adresse = adresse;
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
       
        if(!"".equals(nom))
            this.nom= nom;
        else {
            System.out.println("nom est null");
                }
    }
     public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        
        if(!"".equals(adresse))
            this.adresse= adresse;
        else {
            System.out.println("adresse est null");
                }
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image= image;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        
        if(!"".equals(surface))
            this.surface= surface;
        else {
            System.out.println("surface est null");
                }
    }

    @Override
    public String toString() {
        return "Arene{" + "id=" + id + ", Nom=" + nom + ", surface=" + surface +", image=" + image + "}";
    }

}






















