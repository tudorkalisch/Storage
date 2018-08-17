package domain.models;

import java.util.ArrayList;
import java.util.List;

public class Label {
    private int id;
    private String itemName;
    private List<String> labels;

    public Label() {
        this.id = -1;
        this.itemName = "";
        labels = new ArrayList<>();
    }

    public Label(int id, String itemName, List<String> labels) {
        this.id = id;
        this.itemName = itemName;
        this.labels = labels;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
