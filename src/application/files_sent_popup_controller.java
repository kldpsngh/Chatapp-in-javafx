package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class files_sent_popup_controller implements Initializable {
	
	
	
	@FXML private Button file_sent_ok_button;
	
	@FXML private Label file_sent_message;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML private void file_sent_ok_button_action(ActionEvent event)
	{
		if(event.getSource()==file_sent_ok_button)
		{
		     Stage s=(Stage)file_sent_ok_button.getScene().getWindow();
		     s.close();
		}
	}

}
