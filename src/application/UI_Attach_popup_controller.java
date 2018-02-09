package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UI_Attach_popup_controller implements Initializable {

@FXML private Button docs_send;
	
	@FXML private Button image_send;
	
	@FXML private Button pdf_send;
	
	@FXML private Button video_send;
	
	@FXML private Button mp3_send;
	
	@FXML private Label browse_field;
	
	@FXML private Button send_media;
	
	private List<File> selected;
	
	public ProgressBar progressbar;
	
	final MyNumber mynum=new MyNumber();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mynum.setNumber(0);
		//mynum.numberproperty().addListener(listener);
		//progressbar.progressProperty().bind(mynum.numberproperty());
	}
	
	@FXML
	private void open_MP3_dialog(ActionEvent event)
	{
		FileChooser fc=new FileChooser();
		fc.setInitialDirectory(new File("C:\\Users\\kuldeep singh\\Downloads"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("MP3 Files","*.mp3"));
		selected=fc.showOpenMultipleDialog(null);
		String path=selected.get(0).getAbsolutePath();
		for(int i=1;i<selected.size();i++)
		{
			path=path+" "+selected.get(i).getAbsolutePath();
		}
		browse_field.setText(path);
		
	}
	
	@FXML
	private void open_Docs_dialog(ActionEvent event)
	{
		FileChooser fc=new FileChooser();
		fc.setInitialDirectory(new File("C:\\Users\\kuldeep singh\\Downloads"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files","*.txt"),
				new ExtensionFilter("Doc Files","*.doc"));
		selected=fc.showOpenMultipleDialog(null);
		String path=selected.get(0).getAbsolutePath();
		for(int i=1;i<selected.size();i++)
		{
			path=path+" "+selected.get(i).getAbsolutePath();
		}
		browse_field.setText(path);
	}
	
	@FXML
	private void open_Image_dialog(ActionEvent event)
	{
		FileChooser fc=new FileChooser();
		fc.setInitialDirectory(new File("C:\\Users\\kuldeep singh\\Downloads"));
		fc.getExtensionFilters().addAll(
			    new ExtensionFilter("JPG", "*.jpg"),
			    new ExtensionFilter("GIF", "*.gif"),
			    new ExtensionFilter("BMP", "*.bmp"),
			    new ExtensionFilter("PNG", "*.png"));
		selected=fc.showOpenMultipleDialog(null);
		String path=selected.get(0).getAbsolutePath();
		for(int i=1;i<selected.size();i++)
		{
			path=path+" "+selected.get(i).getAbsolutePath();
		}
		browse_field.setText(path);
	}
	
	
	@FXML
	private void open_PDF_dialog(ActionEvent event)
	{
		FileChooser fc=new FileChooser();
		fc.setInitialDirectory(new File("C:\\Users\\kuldeep singh\\Downloads"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files","*.pdf"));
		selected=fc.showOpenMultipleDialog(null);
		String path=selected.get(0).getAbsolutePath();
		for(int i=1;i<selected.size();i++)
		{
			path=path+" "+selected.get(i).getAbsolutePath();
		}
		browse_field.setText(path);
	}
	
	
	@FXML
	private void open_Video_dialog(ActionEvent event)
	{
		FileChooser fc=new FileChooser();
		fc.setInitialDirectory(new File("C:\\Users\\kuldeep singh\\Downloads"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("video Files","*.mp4"),
				new ExtensionFilter("video Files","*.avi"),
				new ExtensionFilter("video Files","*.flv"),
				new ExtensionFilter("video Files","*.iso"));
		selected=fc.showOpenMultipleDialog(null);
		String path=selected.get(0).getAbsolutePath();
		for(int i=1;i<selected.size();i++)
		{
			path=path+" "+selected.get(i).getAbsolutePath();
		}
		browse_field.setText(path);
	}
	
	@FXML
	private void send_file_action(ActionEvent event)
	{
		if(event.getSource()==send_media)
		{
			File Dest_direc=new File("C:\\Users\\kuldeep singh\\Desktop\\FTP-Folder");
			
		       			progressbar=new ProgressBar();
		       			progressbar.progressProperty().bind(mynum.numberproperty());		
		       		//	send_media.getScene().getRoot().getChildrenUnmodifiable(progressbar)  
		       		
			  
			  double increase=(1/selected.size());
			
			for(int i=0;i<selected.size();i++)
			{
				try {
					File src_file=new File(selected.get(i).getAbsolutePath());
					FileUtils.copyFileToDirectory(src_file, Dest_direc);
					mynum.setNumber(mynum.getNumber()+increase);
					
					
					
				}catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if(selected.size()>1)
			{
				mediator.th.send("send" + selected.get(0).getName()+ " and "+(selected.size()-1)+" more...."+"\n");
			}
			else
			{
			mediator.th.send("has send "+ selected.get(0).getName());
			}
			files_sent_popup f=new files_sent_popup();
			try {
				f.display((Stage)send_media.getScene().getWindow());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
