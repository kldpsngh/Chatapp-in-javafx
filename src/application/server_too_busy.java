package application;



import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class server_too_busy {
	
	public void display() throws Exception
	{
		 Stage server_too_busy_popup=new Stage();
		 server_too_busy_popup.initModality(Modality.APPLICATION_MODAL);
		 //server_too_busy_popup.setTitle("Error");
		 Parent root=FXMLLoader.load(getClass().getResource("server_too_busy.fxml"));
		 Scene scene=new Scene(root,479,115);
		 scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 server_too_busy_popup.setScene(scene);
		 server_too_busy_popup.show();
	}
	
}
