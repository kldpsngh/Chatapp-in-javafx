package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class server_too_busy_controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML private Label server_too_busy_label;
	@FXML private Button ok_button;
	
	@FXML private void Exit_error(ActionEvent event)
	{
		if(event.getSource()==ok_button)
		{
			Stage s=(Stage)ok_button.getScene().getWindow();
			s.close();   //to close error pop up
			Platform.exit();  //to close main window
		}
	}
	

}
