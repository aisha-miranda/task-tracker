public class Managers {
    private Managers() { }

    public static TaskManager getDefault() {
        return new inMemoryTaskManager(getDefaultHistory());
    }

    private static HistoryManager getDefaultHistory() {
        return new inMemoryHistoryManager();
    }
}

