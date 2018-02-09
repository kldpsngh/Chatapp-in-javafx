package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UI_Attach_popup {

	public void display_pop_up() throws Exception
	{
		Stage popup_window=new Stage();
		popup_window.initModality(Modality.APPLICATION_MODAL);
		popup_window.setTitle("Send File");
		Parent root=FXMLLoader.load(getClass().getResource("UI_Attach_file.fxml"));
		Scene scene=new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		popup_window.setScene(scene);
		popup_window.show();
	}
}
