import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Epic firstEpic = new Epic("Эпик 1 (с двумя подзадачами)", "Описание эпика 1");
        taskManager.createEpic(firstEpic);

        Subtask firstEpicFirstSubtask = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1 эпика 1", firstEpic);
        Subtask firstEpicSecondSubtask = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2 эпика 1", firstEpic);
        taskManager.createSubtask(firstEpicFirstSubtask);
        taskManager.createSubtask(firstEpicSecondSubtask);

        Epic secondEpic = new Epic("Эпик 2 (с одной подзадачей)", "Описание эпика 2");
        taskManager.createEpic(secondEpic);

        Subtask secondEpicFirstSubtask = new Subtask("Подзадача 1 эпика 2", "Описание подзадачи 1 эпика 2", secondEpic);
        taskManager.createSubtask(secondEpicFirstSubtask);

        Task firstTask = new Task("Задача 1", "Описание задачи 1");
        Task secondTask = new Task("Задача 2", "Описание задачи 2");
        taskManager.createTask(firstTask);
        taskManager.createTask(secondTask);

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

        System.out.println("Изначальный статус эпика: " + firstEpic.getStatus());

        firstEpicFirstSubtask.setStatus("IN_PROGRESS");
        System.out.println("Статус эпика после изменения статуса подзадачи: " + firstEpic.getStatus());

        firstEpicFirstSubtask.setStatus("DONE");
        firstEpicSecondSubtask.setStatus("DONE");
        System.out.println("Статус эпика после изменения статуса подзадач на \"DONE\": " + firstEpic.getStatus());

        taskManager.removeTaskById(6);
        taskManager.removeEpicById(4);

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
    }
}
