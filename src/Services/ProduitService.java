package Services;

import Entities.Categorie;
import Entities.Produit;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {

    private static ProduitService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ProduitService() {
        connection = DataSource.getInstance().getCnx();
    }

    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }

    public List<Produit> getAll() {
        List<Produit> listProduit = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `produit` AS x RIGHT JOIN `categorie` AS y ON x.categorie_id = y.id WHERE x.categorie_id = y.id");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listProduit.add(new Produit(
                        resultSet.getInt("id"),
                        new Categorie(
                                resultSet.getInt("y.id"),
                                resultSet.getString("y.nom")
                        ),
                        resultSet.getString("marque"),
                        resultSet.getString("genre"),
                        resultSet.getString("description"),
                        resultSet.getString("reference"),
                        resultSet.getString("image"),
                        resultSet.getFloat("prix"),
                        resultSet.getInt("stock")
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) produit : " + exception.getMessage());
        }
        return listProduit;
    }

    public boolean checkExist(Produit produit) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `produit` WHERE `reference` = ?");

            preparedStatement.setString(1, produit.getReference());

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException exception) {
            System.out.println("Error checkExist " + exception.getMessage());
        }
        return false;
    }

    public boolean add(Produit produit) {
        if (checkExist(produit)) {
            return false;
        }

        String request = "INSERT INTO `produit`(`categorie_id`,`marque`, `genre`, `description`, `reference`, `image`, `prix`, `stock`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, produit.getCategorie().getId());
            preparedStatement.setString(2, produit.getMarque());
            preparedStatement.setString(3, produit.getGenre());
            preparedStatement.setString(4, produit.getDescription());
            preparedStatement.setString(5, produit.getReference());
            preparedStatement.setString(6, produit.getImage());
            preparedStatement.setFloat(7, produit.getPrix());
            preparedStatement.setInt(8, produit.getStock());

            preparedStatement.executeUpdate();
            System.out.println("Produit added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) produit : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Produit produit) {

        String request = "UPDATE `produit` SET `categorie_id` = ?,`marque` = ?, `genre` = ?, `description` = ?, `reference` = ?, `image` = ?, `prix` = ?, `stock` = ? WHERE `id`=" + produit.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, produit.getCategorie().getId());
            preparedStatement.setString(2, produit.getMarque());
            preparedStatement.setString(3, produit.getGenre());
            preparedStatement.setString(4, produit.getDescription());
            preparedStatement.setString(5, produit.getReference());
            preparedStatement.setString(6, produit.getImage());
            preparedStatement.setFloat(7, produit.getPrix());
            preparedStatement.setInt(8, produit.getStock());

            preparedStatement.executeUpdate();
            System.out.println("Produit edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) produit : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `produit` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Produit deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) produit : " + exception.getMessage());
        }
        return false;
    }
}
