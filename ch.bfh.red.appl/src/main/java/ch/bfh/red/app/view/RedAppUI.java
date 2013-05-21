/**
 * 
 */
package ch.bfh.red.app.view;

/**
 * @author team #F00
 *
 */

import java.util.Calendar;

import ch.bfh.red.app.controller.DiaryEditor;
import ch.bfh.red.app.controller.DiaryEditor.EditorSavedEvent;
import ch.bfh.red.app.controller.DiaryEditor.EditorSavedListener;
import ch.bfh.red.app.model.assignment.Diary;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Push;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Push
public class RedAppUI extends UI {

   private JPAContainer<Diary> diaries;

   private static final long serialVersionUID = -3919212212063135503L;

   @Override
   protected void init(VaadinRequest request) {

      diaries = JPAContainerFactory.make(Diary.class, JpaAddressbookUI.PERSISTENCE_UNIT);

      // Set the window or tab title
      getPage().setTitle("Welcome RedApp");

      final VerticalLayout layout = new VerticalLayout();
      layout.setMargin(true);
      setContent(layout);

      final BeanItem<Diary> newDiaryItem = new BeanItem<Diary>(new Diary());

      DiaryEditor diaryEditor = new DiaryEditor(newDiaryItem);
      diaryEditor.setWidth(800, Unit.PIXELS);
      diaryEditor.setHeight(500, Unit.PIXELS);

      diaryEditor.addListener(new EditorSavedListener() {
         private static final long serialVersionUID = -4810596568407523252L;

         @Override
         public void editorSaved(EditorSavedEvent event) {
            // get and set current DateTime
            newDiaryItem.getBean().setDateTime(Calendar.getInstance());

            // write some "logs"
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("------------" + newDiaryItem.getBean().getEntry());
            System.out.println("------------" + newDiaryItem.getBean().getFeeling());

            diaries.addEntity(newDiaryItem.getBean());

         }
      });

      layout.addComponent(diaryEditor);

      DiarySummaryView diarySummaryView = new DiarySummaryView(newDiaryItem);
      diarySummaryView.setWidth(800, Unit.PIXELS);
      diarySummaryView.setHeight(500, Unit.PIXELS);
      layout.addComponent(diarySummaryView);

      new NotificationChecker().start();

   }

   class NotificationChecker extends Thread {
      @Override
      public void run() {
         
         // Loop forever
         while (true) {
            checkDiary();

            try {
               Thread.sleep(5000);
            } catch (InterruptedException e) { }
         }
      }

      private void checkDiary() {
         Calendar today = Calendar.getInstance();
         today.set(Calendar.HOUR_OF_DAY, 0);
         today.set(Calendar.MINUTE, 0);
         today.set(Calendar.SECOND, 0);
         today.set(Calendar.MILLISECOND, 0);

         diaries.addContainerFilter(new Compare.Greater("dateTime", today));
         diaries.applyFilters();

         // Init done, update the UI after doing locking
         access(new Runnable() {
            @Override
            public void run() {

               if (diaries.size() < 1) {
                  new Notification("Tagebuch führen", "", Type.ERROR_MESSAGE).show(getPage());
               }
            }
         });
         
         diaries.removeAllContainerFilters();
//         diaries.app§
      }
   }
}