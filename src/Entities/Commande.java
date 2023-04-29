package Entities;


public class Commande {

    private int id;
    private Produit produit;
    private User user;

    private int nombreProduit;
    private int prixTotal;

    public Commande(int id, Produit produit, User user, int nombreProduit, int prixTotal) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.nombreProduit = nombreProduit;
        this.prixTotal = prixTotal;
    }

    public Commande(Produit produit, User user, int nombreProduit, int prixTotal) {
        this.produit = produit;
        this.user = user;
        this.nombreProduit = nombreProduit;
        this.prixTotal = prixTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNombreProduit() {
        return nombreProduit;
    }

    public void setNombreProduit(int nombreProduit) {
        this.nombreProduit = nombreProduit;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }
}