package GUI;


import com.goacademy.MainApp;
import Entities.Commande;
import Entities.Produit;
import GUI.MainWindowFrontController;
import Services.CommandeService;
import Services.ProduitService;
import Utils.AlertUtils;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class ManageCommFrontController implements Initializable {

    @FXML
    public TextField nombreProduitTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;
    @FXML
    public TextField emailTF;
    @FXML
    public Text produitText;

    Commande currentCommande;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        currentCommande = ShowAllCommBackController.currentCommande;

        if (currentCommande != null) {
            produitText.setText("Produit selectionné : " + currentCommande.getProduit());
            topText.setText("Modifier commande");
            btnAjout.setText("Modifier");

            try {
                nombreProduitTF.setText(String.valueOf(currentCommande.getNombreProduit()));
            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            produitText.setText("Produit selectionné : " + com.goacademy.gui.front.produit.ShowAllController.currentProduit);

            topText.setText("Ajouter commande");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent ignored) {

        if (controleDeSaisie()) {


            if (currentCommande == null) {
                Commande commande = new Commande(
                        com.goacademy.gui.front.produit.ShowAllController.currentProduit,
                        MainApp.session,
                        Integer.parseInt(nombreProduitTF.getText()),
                        Math.round(com.goacademy.gui.front.produit.ShowAllController.currentProduit.getPrix()) * Integer.parseInt(nombreProduitTF.getText())
                );

                if (com.goacademy.gui.front.produit.ShowAllController.currentProduit.getStock() < Integer.parseInt(nombreProduitTF.getText())) {
                    AlertUtils.makeErrorNotificationWithApi("Rupture de stock");
                } else {

                    Produit produit = commande.getProduit();
                    produit.setStock(produit.getStock() - Integer.parseInt(nombreProduitTF.getText()));

                    if (ProduitService.getInstance().edit(produit)) {
                        if (CommandeService.getInstance().add(commande)) {

                            if (!emailTF.getText().isEmpty()) {
                                try {
                                    sendMail(emailTF.getText());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            AlertUtils.makeSuccessNotification("Commande ajouté avec succés");
                            MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_COMMANDE);
                        } else {
                            AlertUtils.makeError("Error");
                        }
                    } else {
                        AlertUtils.makeError("Error");
                    }
                }
            } else {

                Commande commande = new Commande(
                        currentCommande.getProduit(),
                        MainApp.session,
                        Integer.parseInt(nombreProduitTF.getText()),
                        Math.round(currentCommande.getProduit().getPrix()) * Integer.parseInt(nombreProduitTF.getText())
                );

                commande.setId(currentCommande.getId());
                if (CommandeService.getInstance().edit(commande)) {
                    AlertUtils.makeSuccessNotification("Commande modifié avec succés");
                    ShowAllController.currentCommande = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_COMMANDE);
                } else {
                    AlertUtils.makeError("Error");
                }
            }

        }
    }

    public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "pidev.app.esprit@gmail.com";
        //Your gmail password
        String password = "jkemsuddgeptmcsb";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        if (message != null) {
            Transport.send(message);
            System.out.println("Mail sent successfully");
        } else {
            System.out.println("Error sending the mail");
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Notification");
            String htmlCode = "<h1>Notification</h1> <br/> <h2><b>Commande ajouté avec succés</b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private boolean controleDeSaisie() {

        if (nombreProduitTF.getText().isEmpty()) {
            AlertUtils.makeInformation("nombreProduit ne doit pas etre vide");
            return false;
        }

        try {
            Integer.parseInt(nombreProduitTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("nombreProduit doit etre un nombre");
            return false;
        }

        if (Integer.parseInt(nombreProduitTF.getText()) < 1) {
            AlertUtils.makeInformation("Nombre produit < 1");
            return false;
        }

        return true;
    }
}