package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Iscrivitiview {
	
	public Iscrivitiview() {
		//nothing to do
	}
	

	public void apriIscr(Stage stage) throws IOException{
		 
		 Parent root = FXMLLoader.load(getClass().getResource("iscrizione.fxml"));
	     Scene scene = new Scene(root);
	     stage.setScene(scene);
	     stage.setTitle("Sport Challenge Online");
	     stage.show();
	}


}
