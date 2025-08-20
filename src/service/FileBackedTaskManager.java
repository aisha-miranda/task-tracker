package service;

import exceptions.ManagerSaveException;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final File file;

    public FileBackedTaskManager(HistoryManager historyManager, String path) {
        super(historyManager);
        this.file = new File(path);
    }

    public File getFile(){
        return file;
    }

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
    }

    @Override
    public void updateTask(int id, Task task) {
        super.updateTask(id, task);
        save();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }

    @Override
    public void removeTaskById(int id) {
        super.removeTaskById(id);
        save();
    }

    @Override
    public void removeEpicById(int id) {
        super.removeEpicById(id);
        save();
    }

    @Override
    public void removeSubtaskById(int id) {
        super.removeSubtaskById(id);
        save();
    }

    @Override
    public void removeAll() {
        super.removeAll();
        save();
    }

    public String toString(Task task) {
        if (task.getType() == TaskType.SUBTASK) {
            return String.format("%s, %s, %s, %s, %s, %s", task.getId(), task.getType(), task.getTitle(), task.getStatus(), task.getDescription(), ((Subtask) task).getEpicId());
        }
        return String.format("%s, %s, %s, %s, %s", task.getId(), task.getType(), task.getTitle(), task.getStatus(), task.getDescription());
    }

    public static String historyToString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();
        for (Task task : manager.getHistory()) {
            sb.append(task.getId()).append(", ");
        }
        return sb.toString();
    }

    public void save() {
        try (Writer writer = new FileWriter(file)) {
            List<Task> tasks = getTasks();
            if (!tasks.isEmpty()) {
                for (Task task : tasks) {
                    writer.write(String.format("%s\n", toString(task)));
                }
            }
            List<Epic> epics = getEpics();
            if (!epics.isEmpty()) {
                for (Epic epic : epics) {
                    writer.write(String.format("%s\n", toString(epic)));
                }

            }
            List<Subtask> subtasks = getSubtasks();
            if (!subtasks.isEmpty()) {
                for (Subtask subtask : subtasks) {
                    writer.write(String.format("%s\n", toString(subtask)));
                }
            }
            writer.write("\n");
            writer.write(historyToString(getHistoryManager()));
        } catch (IOException exception) {
            throw new ManagerSaveException("Невозможно записать данные в файл.");
        }
    }
}
