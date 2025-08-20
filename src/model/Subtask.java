package model;
import service.TaskManager;

public class Subtask extends Task {
    private Epic epic;

    public Subtask(String title, String description, Epic epic, TaskManager manager) {
        super(title, description, manager);
        this.epic = epic;
        this.type = TaskType.SUBTASK;
        this.manager = manager;
    }

    @Override
    public void setStatus(Statuses status) {
        super.setStatus(status);
        epic.updateStatus();
    }

    public Epic getEpic() {
        return epic;
    }

    public int getEpicId() {
        return epic.id;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "title= " + title +
                ", description=" + description +
                ", id= " + getId() +
                ", status= " + status + "}";
    }

    @Override
    public void setId(int id) {
        if (getTaskManager().getSubtasks().contains(this)) {
            getTaskManager().getSubtasksMap().remove(this.getId());
            getTaskManager().getSubtasksMap().put(id, this);
        }
        this.id = id;
    }

}
