package ch.bfh.red.app.view;
/**
 * Start screen with menu items
 * 
 * @author barta3
 */
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
		
		this.setCaption("Home");

		VerticalComponentGroup group = new VerticalComponentGroup();

		NavigationButton btnDiary = new NavigationButton("Tagebuch", new DiarySummaryView());
		group.addComponents(btnDiary);

		this.setContent(group);
	}

}
