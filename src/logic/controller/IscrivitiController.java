package controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.IscrizioneBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class IscrivitiController implements Initializable {
	public static IscrizioneBean iscrizioneBean = new IscrizioneBean();

    @FXML
    private Button indietroButton;

    @FXML
    private TextField NomeTF;
    @FXML
    private TextField CognomeTF;
    @FXML
    private DatePicker DataDP;
    @FXML
    private TextField TelefonoTF;
    @FXML
    private TextField MailTF;
    @FXML
    private TextField PWTF;
    @FXML
    private CheckBox RentCB;


    @FXML
    private void indietro(ActionEvent event) throws IOException {

        Stage stage = (Stage) indietroButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void confermaRegistrazione(ActionEvent event) throws SQLException, IOException {

		if (NomeTF.getText().trim().isEmpty() ||
                CognomeTF.getText().trim().isEmpty() || DataDP==null ||
                DataDP.getValue().toString().trim().isEmpty() ||
				MailTF.getText().trim().isEmpty() ||
				PWTF.getText().trim().isEmpty() ||
				TelefonoTF.getText().trim().isEmpty()) {
            emptyFieldError();
            return;
        }

		//controllo età
		if((LocalDateTime.now().getYear() - DataDP.getValue().getYear()) < 14)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRORE DATI UTENTE");
			alert.setContentText("La piattaforma è riservata ad utenti di età > 14, per favore riprova.");
			alert.showAndWait();
			return;
		}

		//controllo email
		if(!MailTF.getText().isEmpty())
		{
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher matcher = pattern.matcher(MailTF.getText().trim());
			if(!matcher.find()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("ERRORE DATI UTENTE");
				alert.setContentText("L'email inserita non è nel formato corretto, per favore riprova.");
				alert.showAndWait();
				return;
			}
		}

		//check utente già registrato
        int isRent = RentCB.isSelected() ? 1 : 0;
        if(iscrizioneBean.checkIfUserAlreadyExists(NomeTF.getText().trim().toUpperCase(), CognomeTF.getText().trim().toUpperCase())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRORE DATI UTENTE");
			alert.setContentText("C'è già un utente registrato con questo nome e cognome, per favore riprova.");
			alert.showAndWait();
		}

        //aggiunta utente nel database
		else if(iscrizioneBean.addUser(NomeTF.getText().trim().toUpperCase(), CognomeTF.getText().trim().toUpperCase(),
				MailTF.getText().trim(), DataDP.getValue().toString(), PWTF.getText().trim(),TelefonoTF.getText().trim(), isRent)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("INSERIMENTO AVVENUTO CON SUCCESSO");
			alert.setContentText("Complimenti, ti sei registrato con successo");
			alert.showAndWait();
			if(isRent == 1){
                Stage stage = (Stage) indietroButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageRenter.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
			}
			//modificare view e mettere quella dello sportsman
			else{
                Stage stage = (Stage) indietroButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageRenter.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

		}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //nothing to do
    }


    public void emptyFieldError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRORE INSERIMENTO DATI");
        alert.setContentText("Il sistema non accetta campi vuoti, per favore riprova.");
        alert.showAndWait();
    }
}
