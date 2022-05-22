package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final List<String> list;

    public Todos() {
        list = new ArrayList<>();
    }

    public void addTask(String task) {
        list.add(task);
    }

    public void removeTask(String task) {
        list.remove(task);
    }

    public String getAllTasks() {

        return list.toString();
    }

}
