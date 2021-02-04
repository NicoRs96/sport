
import bean.IscrizioneBean;
import view.MainView;

import java.sql.Date;
import java.sql.SQLException;

public class Main {	   
	    
	public static void main(String[] args) throws SQLException {

		//System.out.println(new IscrizioneBean().addUser("Davide","Rossi", "dasd@das.it", "2000-07-13","paassword",0));
		MainView.launch(MainView.class, args);
	}
	
 }
  





