package domain.models;

public class ScanItem {
    private int id;
    private int[] image;
    private String title;
    private String dateStamp;

    public ScanItem(int id, int[] image, String title, String dateStamp) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.dateStamp = dateStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getImage() {
        return image;
    }

    public void setImage(int[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }
}
