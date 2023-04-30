package Services;

import Entities.Commande;
import Entities.Produit;
import Entities.User;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeService {

    private static CommandeService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public CommandeService() {
        connection = DataSource.getInstance().getCnx();
    }

    public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }
        return instance;
    }

    public List<Commande> getAll() {
        List<Commande> listCommande = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM `commande` AS x " +
                            "RIGHT JOIN `produit` AS y ON x.produit_id = y.id " +
                            "RIGHT JOIN `user` AS z ON x.user_id = z.id " +
                            "WHERE x.produit_id = y.id " +
                            "AND x.user_id = z.id"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listCommande.add(new Commande(
                        resultSet.getInt("id"),
                        new Produit(
                                resultSet.getInt("y.id"),
                                null,
                                resultSet.getString("y.marque"),
                                resultSet.getString("y.genre"),
                                resultSet.getString("y.description"),
                                resultSet.getString("y.reference"),
                                resultSet.getString("y.image"),
                                resultSet.getFloat("y.prix"),
                                resultSet.getInt("y.stock")
                        ),
                        new User(
                                resultSet.getInt("z.id"),
                                resultSet.getString("z.nom"),
                                resultSet.getString("z.prenom"),
                                resultSet.getString("z.email"),
                                resultSet.getString("z.mdp"),
                                resultSet.getString("z.role"),
                                resultSet.getDate("z.date_birth").toLocalDate(),
                                resultSet.getString("z.image")
                                
                        ),
                        resultSet.getInt("nombre_produit"),
                        resultSet.getInt("prix_total")

                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) commande : " + exception.getMessage());
        }
        return listCommande;
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listUsers.add(new User( resultSet.getInt("z.id"),
                                resultSet.getString("z.nom"),
                                resultSet.getString("z.prenom"),
                                resultSet.getString("z.email"),
                                resultSet.getString("z.mdp"),
                                resultSet.getString("z.role"),
                                resultSet.getDate("z.date_birth").toLocalDate(),
                                resultSet.getString("z.image")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) users : " + exception.getMessage());
        }
        return listUsers;
    }

    public boolean add(Commande commande) {

        String request = "INSERT INTO `commande`( `produit_id`,`user_id`,  `nombre_produit`, `prix_total`) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, commande.getProduit().getId());
            preparedStatement.setInt(2, commande.getUser().getId());
            preparedStatement.setInt(3, commande.getNombreProduit());
            preparedStatement.setInt(4, commande.getPrixTotal());

            preparedStatement.executeUpdate();
            System.out.println("Commande added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) commande : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Commande commande) {

        String request = "UPDATE `commande` SET `produit_id` = ?, `user_id` = ?, `nombre_produit` = ?, `prix_total` = ? WHERE `id`=" + commande.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, commande.getProduit().getId());
            preparedStatement.setInt(2, commande.getUser().getId());
            preparedStatement.setInt(3, commande.getNombreProduit());
            preparedStatement.setInt(4, commande.getPrixTotal());

            preparedStatement.executeUpdate();
            System.out.println("Commande edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) commande : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `commande` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Commande deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) commande : " + exception.getMessage());
        }
        return false;
    }
}
