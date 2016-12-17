package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.ItemEvent;

/**
 * Created by Sandro on 17.12.2016.
 */
public class Tasks {

    private ObservableList<ItemEvent> events = FXCollections.observableArrayList();
    public Tasks(){
        events = FXCollections.observableArrayList();
        ItemEvent item;
        item = new ItemEvent(1, "Mathematik Test: Bruchrechnen", "12.12.2016", "15:00", "Lernziele 1-4 \n" +
                "Einfaches Rechnen 1: S12 - S24 \n" + "Zeit: 45min", "Test");
        events.add(item);
        item = new ItemEvent(2, "Bla bla bla", "06.05.2017", "11:00", "afsa fs fsj kfls jlfjjf ", "News");
        events.add(item);
        item = new ItemEvent(3, "Btestst tst", "06.05.2017", "11:00", "afsa fs fsj kfls jlfjjf ", "asfd");
        events.add(item);

    }

    public ObservableList<ItemEvent> getTasks(){
        return events;
    }
}
