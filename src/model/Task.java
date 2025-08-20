package model;

public class Task {
    protected int id;
    protected String title;
    protected String description;
    protected Statuses status;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Statuses.NEW;
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

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public String toString() {
        return Integer.toString(id);
    }
}
