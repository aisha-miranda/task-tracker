package utils;

import service.HistoryManager;
import service.TaskManager;
import service.InMemoryHistoryManager;
import service.InMemoryTaskManager;

public class Managers {
    private Managers() { }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    }

    private static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}

