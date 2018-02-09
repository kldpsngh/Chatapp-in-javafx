package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class disconnect_window {
	
	public void display() throws Exception
	{
		 Stage disconnect_window_popup=new Stage();
		 disconnect_window_popup.initModality(Modality.APPLICATION_MODAL);
		 //server_too_busy_popup.setTitle("Error");
		 Parent root=FXMLLoader.load(getClass().getResource("disconnect_window.fxml"));
		 Scene scene=new Scene(root,600,120);
		 scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 disconnect_window_popup.setScene(scene);
		 disconnect_window_popup.show();
	}

}
