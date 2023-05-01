package Services;

import Entities.Categorie;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieService {

    private static CategorieService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public CategorieService() {
        connection = DataSource.getInstance().getCnx();
    }

    public static CategorieService getInstance() {
        if (instance == null) {
            instance = new CategorieService();
        }
        return instance;
    }

    public List<Categorie> getAll() {
        List<Categorie> listCategorie = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `categorie`");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listCategorie.add(new Categorie(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) categorie : " + exception.getMessage());
        }
        return listCategorie;
    }


    public boolean add(Categorie categorie) {


        String request = "INSERT INTO `categorie`(`nom`) VALUES(?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, categorie.getNom());

            preparedStatement.executeUpdate();
            System.out.println("Categorie added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) categorie : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Categorie categorie) {

        String request = "UPDATE `categorie` SET `nom` = ? WHERE `id`=" + categorie.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, categorie.getNom());

            preparedStatement.executeUpdate();
            System.out.println("Categorie edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) categorie : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `categorie` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Categorie deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) categorie : " + exception.getMessage());
        }
        return false;
    }
}
