/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Siwar
 */
public class Equipe {
    private int id;
    private String nom,logo;
    String origine;
     int nb_joueurs;
     private List<Competition> competitions = new ArrayList<Competition>();
     private List<PerformanceEquipe> PerformanceEquipe = new ArrayList<PerformanceEquipe>();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String Nom) {
        this.nom = Nom;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public Equipe(int id, String Nom) {
        this.id = id;
        this.nom = Nom;
    }

    public Equipe(String Nom) {
        this.nom = Nom;
    }

    public Equipe(int id) {
        this.id = id;
    }

 
     
     public Equipe() {
        
    }
     
     
    public Equipe(String nom, String logo, int nb_joueurs, String origine) {
        this.nom = nom;
        this.logo = logo;
        this.nb_joueurs = nb_joueurs;
        this.origine = origine;
    }
//2
    public Equipe(int id, String nom, String logo, int nb_joueurs, String origine) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.nb_joueurs = nb_joueurs;
        this.origine = origine;
    }


     
      @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nom=" + nom + ", logo=" + logo + ", nb_joueurs=" + nb_joueurs + ", origine=" + origine + '}';
    }
      public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getNb_joueurs() {
        return nb_joueurs;
    }

    public void setNb_joueurs(int nb_joueurs) {
        this.nb_joueurs = nb_joueurs;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }
    
}

