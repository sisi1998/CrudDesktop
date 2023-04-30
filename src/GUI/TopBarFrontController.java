package GUI;

import com.goacademy.MainApp;
import Utils.Animations;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TopBarFrontController implements Initializable {

    private final Color COLOR_GRAY = new Color(0.9, 0.9, 0.9, 1);
    private final Color COLOR_PRIMARY = Color.web("#F9690E");
    private final Color COLOR_DARK = new Color(1, 1, 1, 0.65);
    private Button[] liens;

    @FXML
    private Button btnProduits;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnCommandes;

    @FXML
    private AnchorPane mainComponent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        liens = new Button[]{
                btnProduits,
                btnCategories,
                btnCommandes,
        };

        mainComponent.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            lien.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));
            Animations.animateButton(lien, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
        }

        btnProduits.setTextFill(COLOR_DARK);

        btnCategories.setTextFill(COLOR_DARK);

        btnCommandes.setTextFill(COLOR_DARK);

    }

    @FXML
    private void afficherProduits(ActionEvent event) {
        goToLink(Constants.FXML_FRONT_DISPLAY_ALL_PRODUIT);

        btnProduits.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnProduits, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    @FXML
    private void afficherCategories(ActionEvent event) {
        goToLink(Constants.FXML_FRONT_DISPLAY_ALL_CATEGORIE);

        btnCategories.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnCategories, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    @FXML
    private void afficherCommandes(ActionEvent event) {
        goToLink(Constants.FXML_FRONT_DISPLAY_ALL_COMMANDE);

        btnCommandes.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnCommandes, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    private void goToLink(String link) {
        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            Animations.animateButton(lien, COLOR_GRAY, COLOR_DARK, COLOR_PRIMARY, 0, false);
        }
        MainWindowFrontController.getInstance().loadInterface(link);
    }

    @FXML
    public void logout(ActionEvent ignored) {
        MainApp.getInstance().logout();
    }
}
