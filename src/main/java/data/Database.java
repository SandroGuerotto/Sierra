package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.ItemEvent;


public class Database {

    ObservableList<Request> requests = FXCollections.observableArrayList();
    ObservableList<ItemEvent> events = FXCollections.observableArrayList();

    public Database(){
        initRequest();

        initEvent();

    }

    private void initRequest() {
        Request request = new Request();
        request.setID(1);
        request.setDate("25.12.2016");
        request.setReason("Aufgrund von Gründen");
        request.setStatus("OK");
        requests.addAll(request);
    }

    private void initEvent() {
        ItemEvent item;
        item = new ItemEvent(1, "Mathematik Test: Bruchrechnen", "12.12.2016", "15:00", "Lernziele 1-4 \n" +
                "Einfaches Rechnen 1: S12 - S24 \n" + "Zeit: 45min", "Test");
        events.add(item);
        item = new ItemEvent(2, "Bla bla bla", "06.05.2017", "11:00", "afsa fs fsj kfls jlfjjf ", "News");
        events.add(item);
        item = new ItemEvent(3, "Btestst tst", "06.05.2017", "11:00", "afsa fs fsj kfls jlfjjf ", "asfd");
        events.add(item);
    }

    public ObservableList<Request> getRequest(){
        return  requests;
    }
    public ObservableList<ItemEvent> getTasks(){
        return events;
    }

    public void addRequest(Request request){
        requests.add(request);
    }
}
