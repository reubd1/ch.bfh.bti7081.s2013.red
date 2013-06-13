package ch.bfh.red.app.util;

import java.io.Serializable;
import java.util.ArrayList;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

/**
 * Javascript Component that is connected to a GoogleMap Javascript Class
 * 
 * @author reubd1
 */


@JavaScript({
	         "http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js",
	         "GMap.js", "GMap-connector.js" })
	 public class GMap extends AbstractJavaScriptComponent {
	     
	     public interface ValueChangeListener extends Serializable {
	         void valueChange();
	     }
	    
	    ArrayList<ValueChangeListener> listeners =
	            new ArrayList<ValueChangeListener>();
	    public void addListener(ValueChangeListener listener) {
	        listeners.add(listener);
	    }
	    
	    public void setValue(String value) {
	        getState().setValue(value);
	        markAsDirty();
	    }
	    
	    public String getValue() {
	        return getState().getValue();
	    }
	    
	    @Override
	    public GMapState getState() {
	        return (GMapState) super.getState();
	    }
	    
	    public GMap() {
	        addFunction("onClick", new JavaScriptFunction() {
				@Override
				public void call(org.json.JSONArray arguments)
						throws org.json.JSONException {
					 getState().setValue(arguments.getString(0));
		                for (ValueChangeListener listener: listeners)
		                    listener.valueChange();
					
				}
	        });
	    }
	}
