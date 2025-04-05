package model;

public class Task {
    protected int id;
    protected String title;
    protected String description;
    protected String status;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "NEW";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
