package model;

import service.TaskManager;

public class Task {
    protected int id;
    protected String title;
    protected String description;
    protected Statuses status;
    protected TaskType type;
    protected TaskManager manager;

    public Task(String title, String description, TaskManager manager) {
        this.title = title;
        this.description = description;
        this.status = Statuses.NEW;
        this.type = TaskType.TASK;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (getTaskManager().getTasks().contains(this)){
            getTaskManager().getTasksMap().remove(this.getId());
            getTaskManager().getTasksMap().put(id, this);
        }
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

    public TaskManager getTaskManager(){
        return manager;
    }

    public void setTaskManager(TaskManager manager){
        this.manager = manager;
    }

}
