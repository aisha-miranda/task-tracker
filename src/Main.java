import model.Epic;
import model.Statuses;
import model.Subtask;
import model.Task;
import service.HistoryManager;
import service.TaskManager;
import utils.Managers;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = taskManager.getHistoryManager();

        Epic firstEpic = new Epic("Эпик 1 (с трёмя подзадачами)", "Описание эпика 1");
        taskManager.createEpic(firstEpic);

        Epic secondEpic = new Epic("Эпик 1 (без подзадач)", "Описание эпика 1");
        taskManager.createEpic(secondEpic);

        Task firstTask = new Task("Задача 1", "Описание задачи 1");
        taskManager.createTask(firstTask);
        Task secondTask = new Task("Задача 2", "Описание задачи 2");
        taskManager.createTask(secondTask);

        Subtask firstEpicFirstSubtask = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1 эпика 1", firstEpic);
        Subtask firstEpicSecondSubtask = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2 эпика 1", firstEpic);
        Subtask firstEpicThirdSubtask = new Subtask("Подзадача 3 эпика 1", "Описание подзадачи 3 эпика 1", firstEpic);
        taskManager.createSubtask(firstEpicFirstSubtask);
        taskManager.createSubtask(firstEpicSecondSubtask);
        taskManager.createSubtask(firstEpicThirdSubtask);

        taskManager.getTaskById(3);
        System.out.println(historyManager.getHistory());
        taskManager.getSubtaskById(7);
        System.out.println(historyManager.getHistory());
        taskManager.getEpicById(2);
        System.out.println(historyManager.getHistory());
        taskManager.getEpicById(1);
        System.out.println(historyManager.getHistory());
        taskManager.getSubtaskById(5);
        System.out.println(historyManager.getHistory());
        taskManager.getSubtaskById(6);
        System.out.println(historyManager.getHistory());
        taskManager.getEpicById(1);
        System.out.println(historyManager.getHistory());
        taskManager.getSubtaskById(7);
        System.out.println(historyManager.getHistory());
        taskManager.getTaskById(4);
        System.out.println(historyManager.getHistory());

        taskManager.removeEpicById(1);
        System.out.println(historyManager.getHistory());
    }
}
