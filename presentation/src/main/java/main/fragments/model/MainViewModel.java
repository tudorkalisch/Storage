package main.fragments.model;

import java.util.List;

public class MainViewModel {
    private List<MainViewModelItem> items;

    public MainViewModel(List<MainViewModelItem> items) {
        this.items = items;
    }

    public MainViewModel() {

    }

    public List<MainViewModelItem> getItems() {
        return items;
    }

    public void setItems(List<MainViewModelItem> items) {
        this.items = items;
    }

    public String getTitle(int position) {
        return this.items.get(position).getTitle();
    }

    public MainViewModelItem getItem(int position) {return this.items.get(position); }

    public int size() {
        if(items != null) {
            return this.items.size();
        }
        return 0;
    }
}
