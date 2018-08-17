package labels.model;

import java.util.ArrayList;
import java.util.List;

public class LabelViewModel {
    private int id;
    private String itemName;
    private List<String> labelList;

    public LabelViewModel(int id, String itemName, List<String> labelList) {
        this.id = id;
        this.itemName = itemName;
        this.labelList = labelList;
    }

    public LabelViewModel() {
        labelList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int size() {
        if(labelList != null) {
            return labelList.size();
        } else {
            return 0;
        }
    }

    public String getLabel(int position) {
        return this.labelList.get(position);
    }

    public void setLabel(int position, String newLabel) {
        this.labelList.set(position, newLabel);
    }

    public void addLabel(String labelName) {
        this.labelList.add(labelName);
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
