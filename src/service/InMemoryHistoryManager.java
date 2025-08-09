package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_HISTORY_SIZE = 10;
    public Node head;
    public Node tail;
    private int size = 0;
    HashMap<Integer, Node> map = new HashMap<>();

    class Node {
        Task task;
        Node next;
        Node prev;

        public Node(Node prev, Task task, Node next) {
            this.task = task;
            this.next = next;
            this.prev = prev;
        }
    }

    public void linkLast(Task task) {
        Node newNode = new Node(tail, task, null);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        map.put(task.getId(), newNode);
    }

    public void removeNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.prev == null) {
            head = node.next;
            head.prev = null;
        } else if (node.next == null) {
            tail = node.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;
        map.remove(node.task.getId());
    }

    public List<Task> getTasks() {
        List<Task> taskList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            taskList.add(current.task);
            current = current.next;
        }
        return taskList;
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            if (map.size() >= MAX_HISTORY_SIZE) {
                map.remove(0);
            }
            Node node = map.get(task.getId());
            if (node != null) {
                removeNode(node);
            }
            linkLast(task);
        }
    }

    public void remove(Task task) {
        if (task != null) {
            Node node = map.get(task.getId());
            removeNode(node);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}
