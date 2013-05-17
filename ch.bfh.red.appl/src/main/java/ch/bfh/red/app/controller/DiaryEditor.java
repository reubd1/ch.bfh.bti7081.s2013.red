package ch.bfh.red.app.controller;

import java.io.Serializable;
import java.lang.reflect.Method;

import ch.bfh.red.app.model.assignment.Diary;
import ch.bfh.red.app.model.assignment.Diary.FeelingEnum;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload;

        public class DiaryEditor extends CustomComponent implements Button.ClickListener{

        /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

        @AutoGenerated
        private AbsoluteLayout mainLayout;
        @AutoGenerated
        private Button cancel;
        @AutoGenerated
        private Button submit;
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
   private final Item diaryItem;
   FieldGroup binder;

        /**
         * The constructor should first build the main layout, set the
         * composition root and then do any custom initialization.
         *
         * The constructor will not be automatically regenerated by the
         * visual editor.
         */
        public DiaryEditor(Item diaryItem) {

       this.diaryItem = diaryItem;
                buildMainLayout();
                setCompositionRoot(mainLayout);

                binder = new FieldGroup(diaryItem);

                binder.bind(taEntry, "entry");
                binder.bind(ogFeel, "feeling");

                // TODO add user code here
        }

        @AutoGenerated
        private AbsoluteLayout buildMainLayout() {
                // common part: create layout
                mainLayout = new AbsoluteLayout();
                mainLayout.setImmediate(false);
                mainLayout.setWidth("100%");
                mainLayout.setHeight("100%");

                // top-level component properties
                setWidth("100.0%");
                setHeight("100.0%");

                // lbFeel
                lbFeel = new Label();
                lbFeel.setImmediate(false);
                lbFeel.setWidth("-1px");
                lbFeel.setHeight("-1px");
                lbFeel.setValue("Wie fühlst du dich heute?");
                mainLayout.addComponent(lbFeel, "top:60.0px;left:80.0px;");

                // ogFeel
                ogFeel = new OptionGroup();
                ogFeel.setImmediate(false);
                ogFeel.setWidth("100.0%");
                ogFeel.setHeight("80px");
                
                
                for(FeelingEnum fe : Diary.FeelingEnum.values()){
                	ogFeel.addItem(fe);
                }
                
                mainLayout.addComponent(ogFeel, "top:80.0px;left:80.0px;");

                // lbEntry
                lbEntry = new Label();
                lbEntry.setImmediate(false);
                lbEntry.setWidth("-1px");
                lbEntry.setHeight("-1px");
                lbEntry.setValue("Tagebucheintrag:");
                mainLayout.addComponent(lbEntry, "top:180.0px;left:80.0px;");

                // taEntry
                taEntry = new TextArea();
                taEntry.setImmediate(false);
                taEntry.setWidth("340px");
                taEntry.setHeight("156px");

                taEntry.setValue("Liebes Tagebuch.... ");
                
                mainLayout.addComponent(taEntry, "top:200.0px;left:80.0px;");


                // submit
                submit = new Button("Senden", this);
                submit.setImmediate(true);
                submit.setWidth("-1px");
                submit.setHeight("-1px");
                mainLayout.addComponent(submit, "top:480.0px;left:357.0px;");

                // cancel
                cancel = new Button("Cancel", this);
                cancel.setImmediate(false);
                cancel.setWidth("-1px");
                cancel.setHeight("-1px");
                mainLayout.addComponent(cancel, "top:480.0px;left:120.0px;");

                return mainLayout;
        }
        /*
    * (non-Javadoc)
    *
    * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.
    * ClickEvent)
    */
   public void buttonClick(ClickEvent event) {
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
       }
//        close();
   }

   public void addListener(EditorSavedListener listener) {
       try {
           Method method = EditorSavedListener.class.getDeclaredMethod(
                   "editorSaved", new Class[] { EditorSavedEvent.class });
           addListener(EditorSavedEvent.class, listener, method);
       } catch (final java.lang.NoSuchMethodException e) {
           // This should never happen
           throw new java.lang.RuntimeException(
                   "Internal error, editor saved method not found");
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