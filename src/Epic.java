import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, Subtask> subtasks;

    public Epic(String title, String description) {
        super(title, description);
        this.subtasks = new HashMap<>();
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
}
