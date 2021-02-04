package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

import bean.LoginBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.Iscrivitiview;

public class MainController implements Initializable{
	public LoginBean loginBean = new LoginBean();


	@FXML
	 private Button iscrivitiBTN;
	
	@FXML
	 private TextField EmailTF;

	@FXML
	private TextField pwTF;

	@FXML
	private Button LoginBtn;

	@FXML
	private void iscriviti(ActionEvent event) throws Exception {
				
		Stage stageTheButtonBelongs = (Stage) iscrivitiBTN.getScene().getWindow();
	   	Iscrivitiview iscrizioneIscrivitiview = new Iscrivitiview();
		iscrizioneIscrivitiview.apriIscr(stageTheButtonBelongs);
	}

	@FXML
	private void login(ActionEvent event) throws Exception {

		if (EmailTF.getText().trim().isEmpty() || pwTF.getText().trim().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRORE INSERIMENTO DATI");
			alert.setContentText("Il sistema non accetta campi vuoti, per favore riprova.");
			alert.showAndWait();
			return;
		}

		TreeMap<String, String> user = loginBean.authenticate(EmailTF.getText().trim(), pwTF.getText().trim());
		if (user.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRORE LOGIN");
			alert.setContentText("Non esiste nessun utente associato a questa combinazione email/password.");
			alert.showAndWait();
			return;
		}

		String nome = user.get("NOME");
		String cognome = user.get("COGNOME");
		String datadinascita = user.get("DATADINASCITA");
		String email = user.get("EMAIL");
		String password = user.get("PASSWORD");
		String telefono = user.get("TELEFONO");
		String isRent = user.get("RENT");

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("ACCESSO ESEGUITO");
		String name = String.format("Bentornato, %s", nome);
		alert.setContentText(name);
		alert.showAndWait();

		if (isRent.equals("1")) {
			Stage stage = (Stage) LoginBtn.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageRenter.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			return;
		}

		//modificare view e mettere quella dello sportsman
		Stage stage = (Stage) LoginBtn.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageRenter.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		return;
	}
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing to do
	}	
	

}
