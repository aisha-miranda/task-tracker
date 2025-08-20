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

    public Task fromString(String string) {
        String[] stringArray = string.split(", ");
        int id = Integer.parseInt(stringArray[0]);
        TaskType type = TaskType.valueOf(stringArray[1]);
        String title = stringArray[2];
        Statuses status = Statuses.valueOf(stringArray[3]);
        String description = stringArray[4];

        Task task;
        if (type == TaskType.TASK) {
            task = new Task(title, description, this);
            task.setStatus(status);
            createTask(task);
            task.setId(id);
        } else if (type == TaskType.EPIC) {
            task = new Epic(title, description, this);
            task.setStatus(status);
            createEpic((Epic) task);
            task.setId(id);
        } else {
            int epicId = Integer.parseInt(stringArray[5]);
            Epic epic = getEpicsMap().get(epicId);
            task = new Subtask(title, description, epic, this);
            task.setStatus(status);
            createSubtask((Subtask) task);
            task.setId(id);
        }
        return task;
    }

    public static List<Integer> historyFromString(String value) {
        List<Integer> taskIds = new ArrayList<>();
        if (value != null) {
            String[] string = value.split(", ");
            for (String str : string) {
                taskIds.add(Integer.parseInt(str));
            }
        }
        return taskIds;
    }

    public void loadFromFile(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line.isBlank()) {
                    break;
                }
                fromString(line);
            }
            String line = bufferedReader.readLine();
            if (!line.isBlank()) {
                List<Integer> history = historyFromString(line);
                for (int taskId: history){
                    if (getTasksMap().containsKey(taskId)) {
                        getHistoryManager().add(getTasksMap().get(taskId));
                    } else if (getEpicsMap().containsKey(taskId)){
                        getHistoryManager().add(getEpicsMap().get(taskId));
                    } else if (getSubtasksMap().containsKey(taskId)){
                        getHistoryManager().add(getSubtasksMap().get(taskId));
                    }
                }
            }
        } catch (IOException exception) {
            throw new ManagerSaveException("Невозможно прочесть файл.");
        }
    }

    public static void main(String[] args){
        FileBackedTaskManager manager = new FileBackedTaskManager(new InMemoryHistoryManager(), "C:\\Users\\Aisha\\OneDrive\\Документы\\Documents\\file_for_manager.rtf");

        Epic firstEpic = new Epic("Эпик 1 (с трёмя подзадачами)", "Описание эпика 1", manager);
        manager.createEpic(firstEpic);
        Epic secondEpic = new Epic("Эпик 2 (без подзадач)", "Описание эпика 1", manager);
        manager.createEpic(secondEpic);

        Task firstTask = new Task("Задача 1", "Описание задачи 1", manager);
        manager.createTask(firstTask);
        Task secondTask = new Task("Задача 2", "Описание задачи 2", manager);
        manager.createTask(secondTask);

        Subtask firstEpicFirstSubtask = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1 эпика 1", firstEpic, manager);
        Subtask firstEpicSecondSubtask = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2 эпика 1", firstEpic, manager);
        Subtask firstEpicThirdSubtask = new Subtask("Подзадача 3 эпика 1", "Описание подзадачи 3 эпика 1", firstEpic, manager);
        manager.createSubtask(firstEpicFirstSubtask);
        manager.createSubtask(firstEpicSecondSubtask);
        manager.createSubtask(firstEpicThirdSubtask);

        System.out.println(manager.getTaskById(3));
        System.out.println(manager.getEpicById(2));
        System.out.println(manager.getEpicById(1));
        System.out.println(manager.getSubtaskById(7));
        System.out.println(manager.getEpicById(1));
        System.out.println(manager.getTaskById(4));

        FileBackedTaskManager manager2 = new FileBackedTaskManager(new InMemoryHistoryManager(), "C:\\Users\\Aisha\\OneDrive\\Документы\\Documents\\file_for_manager.rtf");

        manager2.loadFromFile(manager2.file);
        System.out.println(manager2.getHistoryManager().getHistory());
    }
}
