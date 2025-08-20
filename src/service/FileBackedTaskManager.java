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
}
