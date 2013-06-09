package ch.bfh.red.app.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ch.bfh.red.app.util.RequestUtil;
import ch.bfh.red.app.util.RequestUtil.Callback;

import com.google.gwt.user.client.Window;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends Popover {
	
	public File file;
	
	public LoginView(){
		displayLogin();
	}
	
	public void displayLogin(){
	
		setClosable(true);
        setModal(true);

        setWidth("650px");
        setHeight("75%");
        
		VerticalLayout content = new VerticalLayout();
        content.setWidth("100%");
        content.setHeight("100%");
		// Create the upload with a caption and set receiver later
		Upload upload = new Upload("Upload Image Here", null);
		upload.setButtonCaption("Start Upload");
		        
		// Put the upload component in a panel
		content.addComponent(upload);
//		setLeftComponent(upload);
		        
		// Show uploaded file in this placeholder
		final Image image = new Image("Uploaded Image");
		image.setVisible(false);
		content.addComponent(image);
//		setRightComponent(image);

		// Implement both receiver that saves upload in a file and
		// listener for successful upload
		class ImageUploader implements Receiver, SucceededListener {
		    
		    
		    public OutputStream receiveUpload(String filename,
		                                      String mimeType) {
		        // Create upload stream
		        FileOutputStream fos = null; // Stream to write to
		        try {
		            // Open the file for writing.
		            file = new File("temp.jpg");
		            fos = new FileOutputStream(file, false);
		        } catch (final java.io.FileNotFoundException e) {
		            Notification.show(
		                    "Could not open file<br/>", e.getMessage(),
		                    Notification.TYPE_ERROR_MESSAGE);
		            return null;
		        }
		        return fos; // Return the output stream to write to
		    }

		    public void uploadSucceeded(SucceededEvent event) {
		        // Show the uploaded file in the image viewer
		        image.setVisible(true);
		        image.setSource(new FileResource(file));
		    }
		};
		final ImageUploader uploader = new ImageUploader(); 
		upload.setReceiver(uploader);
		upload.addListener(uploader);
		
		Button loginButton = new Button();
		loginButton.setCaption("Login");

		loginButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
//				recognizeImage();
//				detect(file);
//				train();
				recognizeImage();
			}
		});
		
		content.addComponent(loginButton);

		setContent(content);
	}
	
	
	private void detect(File file){
		RequestUtil.sendDetectRequest(file);
	}
	
	private void train(){

		RequestUtil.save();
	}
	
	private void recognizeImage(){
		RequestUtil.recognize(file);
	}

}
