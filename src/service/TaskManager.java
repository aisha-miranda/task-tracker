package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int uniqueId = 1;

    private int getUniqueId() {
        return uniqueId++;
    }

    public void createTask(Task task) {
        task.setId(getUniqueId());
        tasks.put(task.getId(), task);
    }

    public void createEpic(Epic epic) {
        epic.setId(getUniqueId());
        epics.put(epic.getId(), epic);
    }

    public void createSubtask(Subtask subtask) {
        Epic epic = subtask.getEpic();
        if (epics.containsKey(epic.getId())) {
            subtask.setId(getUniqueId());
            epic.addSubtask(subtask);
            epic.updateStatus();
            subtasks.put(epic.getId(), subtask);
        }
    }

    public void updateTask(int id, Task task) {
        tasks.put(id, task);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeEpicById(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            for (Subtask subtask : epic.getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }
    }

    public void removeSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            Subtask subtaskToRemove = subtasks.get(id);
            Epic epic = subtaskToRemove.getEpic();
            epic.removeSubtaskById(id);
            epic.updateStatus();
            subtasks.remove(id);
        }
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Subtask> getSubtasksByEpic(Epic epic) {
        return epic.getSubtasks();
    }

    public void removeAll() {
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }
}
