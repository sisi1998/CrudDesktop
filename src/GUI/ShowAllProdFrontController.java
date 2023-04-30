package GUI;

import Entities.Produit;

import Services.ProduitService;
import Utils.AlertUtils;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class ShowAllProdFrontController implements Initializable {

    public static Produit currentProduit;

    @FXML
    public Text topText;
    @FXML
    public VBox mainVBox;
    @FXML
    public TextField searchTF;
    @FXML
    public ComboBox<String> sortCB;

    List<Produit> listProduit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listProduit = ProduitService.getInstance().getAll();
        sortCB.getItems().addAll("Categorie", "Genre", "Description", "Reference", "Prix", "Stock");
        displayData("");
    }

    void displayData(String searchText) {
        mainVBox.getChildren().clear();

        Collections.reverse(listProduit);

        if (!listProduit.isEmpty()) {
            for (Produit produit : listProduit) {
                if (produit.getCategorie().getNom().toLowerCase().startsWith(searchText.toLowerCase())) {
                    mainVBox.getChildren().add(makeProduitModel(produit));
                }
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeProduitModel(
            Produit produit
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_PRODUIT)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#categorieText")).setText("Categorie : " + produit.getCategorie());
            ((Text) innerContainer.lookup("#marqueText")).setText("Marque : " + produit.getMarque());
            ((Text) innerContainer.lookup("#genreText")).setText("Genre : " + produit.getGenre());
            ((Text) innerContainer.lookup("#descriptionText")).setText("Description : " + produit.getDescription());
            ((Text) innerContainer.lookup("#referenceText")).setText("Reference : " + produit.getReference());

            ((Text) innerContainer.lookup("#prixText")).setText("Prix : " + produit.getPrix());
            ((Text) innerContainer.lookup("#stockText")).setText("Stock : " + produit.getStock());
            Path selectedImagePath = FileSystems.getDefault().getPath(produit.getImage());
            if (selectedImagePath.toFile().exists()) {
                ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
            }

            ((Button) innerContainer.lookup("#commanderBtn")).setOnAction(event -> commander(produit));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterProduit(ActionEvent ignored) {
        currentProduit = null;
        MainWindowFrontController.getInstance().loadInterface(Constants.FXML_FRONT_MANAGE_PRODUIT);
    }

    private void modifierProduit(Produit produit) {
        currentProduit = produit;
        MainWindowFrontController.getInstance().loadInterface(Constants.FXML_FRONT_MANAGE_PRODUIT);
    }

    private void supprimerProduit(Produit produit) {
        currentProduit = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer produit ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent()) {
            if (action.get() == ButtonType.OK) {
                if (ProduitService.getInstance().delete(produit.getId())) {
                    MainWindowFrontController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_PRODUIT);
                } else {
                    AlertUtils.makeError("Could not delete produit");
                }
            }
        }
    }


    private void commander(Produit produit) {
        currentProduit = produit;
        MainWindowFrontController.getInstance().loadInterface(Constants.FXML_FRONT_MANAGE_COMMANDE);
    }

    @FXML
    private void search(KeyEvent ignored) {
        displayData(searchTF.getText());
    }

    @FXML
    public void sort(ActionEvent ignored) {
        Constants.compareVar = sortCB.getValue();
        Collections.sort(listProduit);
        displayData("");
    }


}
