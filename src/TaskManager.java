import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    void createTask(Task task);
    void createEpic(Epic epic);
    void createSubtask(Subtask subtask);
    void updateTask(int id, Task task);
    Task getTaskById(int id);
    Epic getEpicById(int id);
    Subtask getSubtaskById(int id);
    void removeTaskById(int id);
    void removeEpicById(int id);
    void removeSubtaskById(int id);
    ArrayList<Task> getTasks();
    ArrayList<Epic> getEpics();
    ArrayList<Subtask> getSubtasks();
    ArrayList<Subtask> getSubtasksByEpic(Epic epic);
    void removeAll();
    int getUniqueId();
    HistoryManager getHistoryManager();
}