package application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class web_view {
	
	
	public void display() throws Exception{
		
		Stage stage=new Stage();
		 stage.initModality(Modality.APPLICATION_MODAL);
		WebView w=new WebView();
		WebEngine engine=w.getEngine();
		//engine.load("https://www.facebook.com/");
		engine.load("ftp://192.168.43.176:21/");
		
		
		VBox root=new VBox();
		root.getChildren().addAll(w);
		
		stage.setTitle("Mera Browser");
		Scene scene=new Scene(root,900,600);
		stage.setScene(scene);
		stage.show();
	}

}
