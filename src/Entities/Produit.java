package Entities;


import Utils.Constants;

public class Produit implements Comparable<Produit> {

    private int id;
    private Categorie categorie;
    private String marque;
    private String genre;
    private String description;
    private String reference;
    private String image;
    private float prix;
    private int stock;

    public Produit(int id, Categorie categorie, String marque, String genre, String description, String reference, String image, float prix, int stock) {
        this.id = id;
        this.categorie = categorie;
        this.marque = marque;
        this.genre = genre;
        this.description = description;
        this.reference = reference;
        this.image = image;
        this.prix = prix;
        this.stock = stock;
    }

    public Produit(Categorie categorie, String marque, String genre, String description, String reference, String image, float prix, int stock) {
        this.categorie = categorie;
        this.marque = marque;
        this.genre = genre;
        this.description = description;
        this.reference = reference;
        this.image = image;
        this.prix = prix;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int compareTo(Produit produit) {
        switch (Constants.compareVar) {
            case "Categorie":
                return Integer.compare(produit.getCategorie().getId(), this.getCategorie().getId());
            case "Genre":
                return produit.getGenre().compareTo(this.getGenre());
            case "Description":
                return produit.getDescription().compareTo(this.getDescription());
            case "Reference":
                return produit.getReference().compareTo(this.getReference());
            case "Image":
                return produit.getImage().compareTo(this.getImage());
            case "Prix":
                return Float.compare(produit.getPrix(), this.getPrix());
            case "Stock":
                return Integer.compare(produit.getStock(), this.getStock());

            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "Ref : " + reference;
    }
}