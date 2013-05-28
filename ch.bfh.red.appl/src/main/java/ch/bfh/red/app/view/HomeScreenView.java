package ch.bfh.red.app.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;

public class HomeScreenView extends NavigationView {
	
	 @Override
	    public void attach() {
	        super.attach();
	        
	            buildView();
	    }

	private void buildView() {
		
		VerticalComponentGroup group = new VerticalComponentGroup();
		
		DiarySummaryView diarySummary = new DiarySummaryView();
		NavigationButton btnDiary = new NavigationButton("Diary", diarySummary);
		group.addComponents(btnDiary);
		
		this.setContent(group);
		
		
	}

}
