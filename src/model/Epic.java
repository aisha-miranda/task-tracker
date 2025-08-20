package model;

import java.util.ArrayList;
import java.util.HashMap;
import service.TaskManager;

public class Epic extends Task {
    private HashMap<Integer, Subtask> subtasks;

    public Epic(String title, String description, TaskManager manager) {
        super(title, description, manager);
        this.subtasks = new HashMap<>();
        this.type = TaskType.EPIC;
    }

    public void updateStatus() {
        boolean allNew = true;
        boolean allDone = true;

        for (Subtask subtask : subtasks.values()) {
            if (!subtask.getStatus().equals(Statuses.NEW)) {
                allNew = false;
            }
            if (!subtask.getStatus().equals(Statuses.DONE)) {
                allDone = false;
            }

            if (!allNew && !allDone) {
                break;
            }
        }

        if (subtasks.isEmpty() || allNew) {
            setStatus(Statuses.NEW);
        } else if (allDone) {
            setStatus(Statuses.DONE);
        } else {
            setStatus(Statuses.IN_PROGRESS);
        }
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    public void removeSubtaskById(int id) {
        subtasks.remove(id);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title= " + title +
                ", description=" + description +
                ", id= " + getId() +
                ", status= " + status + "}";
    }

    @Override
    public void setId(int id) {
        if (getTaskManager().getEpics().contains(this)){
            getTaskManager().getEpicsMap().remove(this.getId());
            getTaskManager().getEpicsMap().put(id, this);
        }
        this.id = id;
    }
}
