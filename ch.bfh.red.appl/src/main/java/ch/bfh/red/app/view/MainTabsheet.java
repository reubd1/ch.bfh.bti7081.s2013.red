package ch.bfh.red.app.view;

import ch.bfh.red.app.model.assignment.Diary;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.TabSheet.Tab;
//import com.vornitologist.VornitologistApplication;
//import com.vornitologist.util.Translations;

/**
 * This is the main view for Vornitologist application. It displays a tabbar via
 * one can choose one of the sub views.
 */
public class MainTabsheet extends TabBarView {

    private DiarySummaryView diaryView;

    final BeanItem<Diary> newDiaryItem = new BeanItem<Diary>(new Diary());

	public static NavigationManager navigationManager;
	
//	public Na

    public MainTabsheet() {
    	
    	navigationManager = new NavigationManager();
    	
    	
//    	navigationManager.r
        /*
         * Populate main views
         */
        diaryView = new DiarySummaryView();
//        diaryView.getna
        
        Tab addTab = addTab(diaryView);
        addTab.setIcon( new ThemeResource("linegraphics/bird.png"));

        addTab = addTab(diaryView);
        addTab.setIcon((Resource) new ThemeResource("linegraphics/binocular.png"));

        addTab = addTab(diaryView);
        addTab.setIcon((Resource) new ThemeResource("linegraphics/world.png"));

        addTab = addTab(diaryView);
        addTab.setIcon((Resource) new ThemeResource("linegraphics/tools.png"));

        setSelectedTab(diaryView);

    }

    /**
     * Latest observation view needs to do some cleanup to let garbage collector
     * to do its job. This is due to our simple in memory "service layer"
     * 
     * @see com.vaadin.ui.AbstractComponentContainer#detach()
     */
    @Override
    public void detach() {
        super.detach();
    }

}
