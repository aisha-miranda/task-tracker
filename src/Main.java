public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = taskManager.getHistoryManager();

        Epic epic1 = new Epic("epic1", "");
        Subtask subtask1 = new Subtask("subtask1", "epic1", epic1);
        Subtask subtask2 = new Subtask("subtask2", "epic1", epic1);
        Subtask subtask3 = new Subtask("subtask3", "epic1", epic1);

        Epic epic2 = new Epic("epic2", "");
        Subtask subtask4 = new Subtask("subtask4", "epic2", epic2);
        Subtask subtask5 = new Subtask("subtask5", "epic2", epic2);
        Subtask subtask6 = new Subtask("subtask6", "epic2", epic2);

        taskManager.createEpic(epic1);
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        taskManager.createSubtask(subtask3);

        taskManager.createEpic(epic2);
        taskManager.createSubtask(subtask4);
        taskManager.createSubtask(subtask5);
        taskManager.createSubtask(subtask6);

        // 1, 5 - epic
        // 2 3 4 6 7 8 - subtask
        taskManager.getEpicById(1);
        taskManager.getSubtaskById(2);
        taskManager.getSubtaskById(3);
        taskManager.getSubtaskById(4);
        taskManager.getEpicById(5);
        taskManager.getSubtaskById(6);
        taskManager.getSubtaskById(7);
        taskManager.getSubtaskById(8);
        taskManager.getEpicById(5);
        taskManager.getSubtaskById(8);

        System.out.println(historyManager.getHistory());

    }

}
