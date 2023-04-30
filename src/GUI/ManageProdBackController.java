package GUI;


import com.goacademy.MainApp;
import Entities.Categorie;
import Entities.Produit;

import Services.CategorieService;
import Services.ProduitService;
import Utils.AlertUtils;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class ManageProdBackController implements Initializable {

    @FXML
    public ComboBox<Categorie> categorieCB;
    @FXML
    public TextField marqueTF;
    @FXML
    public TextField descriptionTF;
    @FXML
    public TextField referenceTF;
    @FXML
    public ImageView imageIV;
    @FXML
    public TextField prixTF;
    @FXML
    public TextField stockTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;
    @FXML
    public ComboBox<String> genreCB;

    Produit currentProduit;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        genreCB.getItems().add("Homme");
        genreCB.getItems().add("Femme");
        genreCB.getItems().add("Autre");

        for (Categorie categorie : CategorieService.getInstance().getAll()) {
            categorieCB.getItems().add(categorie);
        }

        currentProduit = ShowAllProdBackController.currentProduit;

        if (currentProduit != null) {
            topText.setText("Modifier produit");
            btnAjout.setText("Modifier");

            try {
                categorieCB.setValue(currentProduit.getCategorie());
                marqueTF.setText(currentProduit.getMarque());
                genreCB.setValue(currentProduit.getGenre());
                descriptionTF.setText(currentProduit.getDescription());
                referenceTF.setText(currentProduit.getReference());
                selectedImagePath = FileSystems.getDefault().getPath(currentProduit.getImage());
                if (selectedImagePath.toFile().exists()) {
                    imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
                }
                prixTF.setText(String.valueOf(currentProduit.getPrix()));
                stockTF.setText(String.valueOf(currentProduit.getStock()));

            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter produit");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent ignored) {

        if (controleDeSaisie()) {

            String imagePath;
            if (imageEdited) {
                imagePath = currentProduit.getImage();
            } else {
                createImageFile();
                imagePath = selectedImagePath.toString();
            }

            Produit produit = new Produit(
                    categorieCB.getValue(),
                    marqueTF.getText(),
                    genreCB.getValue(),
                    descriptionTF.getText(),
                    referenceTF.getText(),
                    imagePath,
                    Float.parseFloat(prixTF.getText()),
                    Integer.parseInt(stockTF.getText())
            );

            if (currentProduit == null) {
                if (ProduitService.getInstance().add(produit)) {
                    AlertUtils.makeSuccessNotification("Produit ajouté avec succés");
                    MainWindowBackController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_PRODUIT);
                } else {
                    AlertUtils.makeError("Reference existe deja");
                }
            } else {
                produit.setId(currentProduit.getId());
                if (ProduitService.getInstance().edit(produit)) {
                    AlertUtils.makeSuccessNotification("Produit modifié avec succés");
                    ShowAllProdBackController.currentProduit = null;
                    MainWindowBackController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_PRODUIT);
                } else {
                    AlertUtils.makeError("Error");
                }
            }

            if (selectedImagePath != null) {
                createImageFile();
            }
        }
    }

    @FXML
    public void chooseImage(ActionEvent ignored) {

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(MainApp.mainStage);
        if (file != null) {
            selectedImagePath = Paths.get(file.getPath());
            imageIV.setImage(new Image(file.toURI().toString()));
        }
    }

    public void createImageFile() {
        try {
            Path newPath = FileSystems.getDefault().getPath("src/com/goacademy/images/uploads/" + selectedImagePath.getFileName());
            Files.copy(selectedImagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
            selectedImagePath = newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean controleDeSaisie() {


        if (categorieCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir categorie");
            return false;
        }


        if (genreCB.getValue() == null) {
            AlertUtils.makeInformation("genre ne doit pas etre vide");
            return false;
        }

        if (marqueTF.getText().isEmpty()) {
            AlertUtils.makeInformation("marque ne doit pas etre vide");
            return false;
        }


        if (descriptionTF.getText().isEmpty()) {
            AlertUtils.makeInformation("description ne doit pas etre vide");
            return false;
        }


        if (referenceTF.getText().isEmpty()) {
            AlertUtils.makeInformation("reference ne doit pas etre vide");
            return false;
        }


        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }


        if (prixTF.getText().isEmpty()) {
            AlertUtils.makeInformation("prix ne doit pas etre vide");
            return false;
        }


        try {
            Float.parseFloat(prixTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("prix doit etre un réel");
            return false;
        }

        if (Float.parseFloat(prixTF.getText()) < 0) {
            AlertUtils.makeInformation("prix doit etre positif");
            return false;
        }


        if (stockTF.getText().isEmpty()) {
            AlertUtils.makeInformation("stock ne doit pas etre vide");
            return false;
        }


        try {
            Integer.parseInt(stockTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("stock doit etre un nombre");
            return false;
        }

        if (Integer.parseInt(stockTF.getText()) < 0) {
            AlertUtils.makeInformation("stock doit etre positif");
            return false;
        }


        return true;
    }
}