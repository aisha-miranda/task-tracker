//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Epic firstEpic = new Epic("Эпик 1 (с двумя подзадачами)", "Описание эпика 1");
        manager.createEpic(firstEpic);

        Subtask firstEpicFirstSubtask = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1 эпика 1", firstEpic);
        Subtask firstEpicSecondSubtask = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2 эпика 1", firstEpic);
        manager.createSubtask(firstEpicFirstSubtask);
        manager.createSubtask(firstEpicSecondSubtask);

        Epic secondEpic = new Epic("Эпик 2 (с одной подзадачей)", "Описание эпика 2");
        manager.createEpic(secondEpic);

        Subtask secondEpicFirstSubtask = new Subtask("Подзадача 1 эпика 2", "Описание подзадачи 1 эпика 2", secondEpic);
        manager.createSubtask(secondEpicFirstSubtask);

        Task firstTask = new Task("Задача 1", "Описание задачи 1");
        Task secondTask = new Task("Задача 2", "Описание задачи 2");
        manager.createTask(firstTask);
        manager.createTask(secondTask);

        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());

        System.out.println("Изначальный статус эпика: " + firstEpic.getStatus());

        firstEpicFirstSubtask.setStatus("IN_PROGRESS");
        System.out.println("Статус эпика после изменения статуса подзадачи: " + firstEpic.getStatus());

        firstEpicFirstSubtask.setStatus("DONE");
        firstEpicSecondSubtask.setStatus("DONE");
        System.out.println("Статус эпика после изменения статуса подзадач на \"DONE\": " + firstEpic.getStatus());

        manager.removeTaskById(6);
        manager.removeEpicById(4);

        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());
    }
}