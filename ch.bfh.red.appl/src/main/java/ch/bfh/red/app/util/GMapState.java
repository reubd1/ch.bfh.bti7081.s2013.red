package ch.bfh.red.app.util;

import com.vaadin.shared.ui.JavaScriptComponentState;

/**
 * Javascript Component State to access different states of the Javascript on server side
 * 
 * @author reubd1
 */
public class GMapState extends JavaScriptComponentState {
	     private String value;
	     
	     public String getValue() {
	         return value;
	     }
	     
	     public void setValue(String value) {
	        this.value = value;
	    }
	}