package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main_application extends Application {

	
	public static void main(String[] args)
	{
		launch(args);
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root_login_window=FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene_1=new Scene(root_login_window,700,200);
		scene_1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
		/*Parent root_main_window=FXMLLoader.load(getClass().getResource("UI.fxml"));
		scene_2=new Scene(root_main_window,700,500);
		scene_2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
		Parent root_file_sending=FXMLLoader.load(getClass().getResource("UI_send_file.fxml"));
		scene_3=new Scene(root_file_sending,400,400);
		scene_3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());*/
		
		
		primaryStage.setScene(scene_1);
		primaryStage.show();
	}
	
	
}
