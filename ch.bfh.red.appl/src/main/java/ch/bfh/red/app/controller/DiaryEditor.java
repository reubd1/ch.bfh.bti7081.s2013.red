package ch.bfh.red.app.controller;

/**
 * Edit form for new Diary entries
 * 
 * @author Team red
 */
import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.red.app.controller.notification.NotificationChecker;
import ch.bfh.red.app.model.assignment.DiaryEntry;
import ch.bfh.red.app.model.assignment.DiaryEntry.FeelingEnum;
import ch.bfh.red.app.model.profile.Patient;
import ch.bfh.red.app.view.DiarySummaryView;
import ch.bfh.red.app.view.LoginView;
import ch.bfh.red.app.view.RedAppUI;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;

public class DiaryEditor extends GeneralEditor implements ClickListener {

	private Button cancel;
	private Button submit;
	private Button uploadBt;

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Label lbFoto;
	@AutoGenerated
	private Upload upload;
	@AutoGenerated
	private TextArea taEntry;
	@AutoGenerated
	private Label lbEntry;
	@AutoGenerated
	private OptionGroup ogFeel;
	@AutoGenerated
	private Label lbFeel;
	private Item diaryItem;
	FieldGroup binder;

	private JPAContainer<DiaryEntry> diaryEntries;

	// for test usage only
	// @Inject
	// private PatientDAO patientDAO;

	// private DiaryAddedCallback diaryAddedCallback;

	/**
	 * An interface for users of this class, so that they can react on if new DiaryEntry was added.
	 */
	// interface DiaryAddedCallback {
	//
	// /**
	// * Called when diary has been added.
	// *
	// * @param diary
	// * the added diary
	// */
	// void diaryAdded(DiaryEntry diary);
	//
	// }

	public DiaryEditor(final Item diaryItem) {

		this.diaryItem = diaryItem;
	}

	@Override
	public void attach() {
		super.attach();
		buildView();
	}

	private void buildView() {
		cancel = new Button(null, this);
		cancel.setCaption("Cancel");

		uploadBt = new Button(null, this);
		uploadBt.setCaption("Upload");

		getNavigationBar().setLeftComponent(cancel);

		submit = new Button(null, this);
		submit.setCaption("Save");
		getNavigationBar().setRightComponent(submit);

		setPreviousComponent(new DiarySummaryView());
		this.diaryEntries = JPAContainerFactory.make(DiaryEntry.class, RedAppUI.PERSISTENCE_UNIT);

		this.addListener(new EditorSavedListener() {
			private static final long serialVersionUID = -4810596568407523252L;

			@Override
			public void editorSaved(EditorSavedEvent event) {
				// get and set current DateTime
				BeanItem<DiaryEntry> newDiaryItem = (BeanItem<DiaryEntry>) diaryItem;
				// newDiaryItem.getBean().setDateTime(Calendar.getInstance());

				// TODO use not only default patient
				EntityManager em = Persistence.createEntityManagerFactory("redapp").createEntityManager();

				newDiaryItem.getBean().setPatient(em.find(Patient.class, new Long(1)));

				diaryEntries.addEntity(newDiaryItem.getBean());

			}
		});

		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();

		// ogFeel
		ogFeel = new OptionGroup("Wie fühlst du dich heute?");
		ogFeel.setImmediate(false);
		ogFeel.setWidth("100.0%");
		ogFeel.setHeight("80px");
		ogFeel.setRequired(true);

		for (FeelingEnum fe : DiaryEntry.FeelingEnum.values()) {
			ogFeel.addItem(fe);
		}

		mainLayout.addComponent(ogFeel, "top:80.0px;left:80.0px;");

		// taEntry
		taEntry = new TextArea("Tagebucheintrag:");
		taEntry.setImmediate(false);
		taEntry.setWidth("340px");
		taEntry.setHeight("156px");
		taEntry.setNullRepresentation("Liebes Tagebuch");

		taEntry.setRequired(true);

		taEntry.setInvalidAllowed(false);
		taEntry.addValidator(new StringLengthValidator("Not long enough (min 10) or null", 10, 100, true));

		mainLayout.addComponent(taEntry, "top:200.0px;left:80.0px;");

		// submit
		// mainLayout.addComponent(submit, "top:480.0px;left:357.0px;");

		// cancel
		// mainLayout.addComponent(cancel, "top:480.0px;left:120.0px;");

		binder = new FieldGroup(diaryItem);

		binder.bind(taEntry, "entry");
		binder.bind(ogFeel, "feeling");

		mainLayout.addComponent(uploadBt, "top:400.0px;left:80.0px;");

		setContent(mainLayout);

	}

	public void buttonClick(ClickEvent event) {

		// to validate
		if (!taEntry.isValid() || !ogFeel.isValid()) {
			return;
		}

		if (event.getButton() == submit) {
			try {
				binder.commit();
			} catch (CommitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fireEvent(new EditorSavedEvent(this, diaryItem));
		} else if (event.getButton() == cancel) {
			binder.discard();
		} else if (event.getButton() == uploadBt) {
			UI.getCurrent().addWindow(new LoginView());
		}
		NotificationChecker.getInstance().setActive(true);
		getNavigationManager().navigateBack();

	}

	public void addListener(EditorSavedListener listener) {
		try {
			Method method = EditorSavedListener.class.getDeclaredMethod("editorSaved", new Class[] { EditorSavedEvent.class });
			addListener(EditorSavedEvent.class, listener, method);
		} catch (final java.lang.NoSuchMethodException e) {
			// This should never happen
			throw new java.lang.RuntimeException("Internal error, editor saved method not found");
		}
	}

	public void removeListener(EditorSavedListener listener) {
		removeListener(EditorSavedEvent.class, listener);
	}

	public static class EditorSavedEvent extends Component.Event {

		private Item savedItem;

		public EditorSavedEvent(Component source, Item savedItem) {
			super(source);
			this.savedItem = savedItem;
		}

		public Item getSavedItem() {
			return savedItem;
		}
	}

	public interface EditorSavedListener extends Serializable {
		public void editorSaved(EditorSavedEvent event);
	}
}