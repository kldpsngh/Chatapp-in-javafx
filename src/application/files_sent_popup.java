package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class files_sent_popup {
	
	public void display(Stage files_sent_popup) throws Exception
	{
		 //files_sent_popup.initModality(Modality.APPLICATION_MODAL);
		 //server_too_busy_popup.setTitle("Error");
		 Parent root=FXMLLoader.load(getClass().getResource("files_sent_popup.fxml"));
		 Scene scene=new Scene(root,380,83);
		 scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 files_sent_popup.setScene(scene);
		 files_sent_popup.show();
	}

}
