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

public class disconnect_window_controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML private Label disconnect_label;
	@FXML private Button ok_button_2;
	
	@FXML private void ok_button_2_action(ActionEvent event)
	{
		if(event.getSource()==ok_button_2)
		{
			Stage s=(Stage)ok_button_2.getScene().getWindow();
			s.close();
			Platform.exit();
		}
	}

}
