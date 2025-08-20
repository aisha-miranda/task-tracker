package model;

public class Task {
    protected int id;
    protected String title;
    protected String description;
    protected Statuses status;
    protected TaskType type;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Statuses.NEW;
        this.type = TaskType.TASK;
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

    @Override
    public String toString() {
        return "Task{" +
                "title= " + title +
                ", description=" + description +
                ", id= " + getId() +
                ", status= " + status + "}";
    }

    public TaskType getType() {
        return type;
    }


}
