package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTests {
    Todos sut = new Todos();

    @Test
    public void AddToListTest(){
        sut.addTask("Task");
        Assertions.assertNotNull(sut.getAllTasks());

    }
    @Test
    public void RemoveFromListTest(){
        sut.removeTask(sut.getAllTasks());
        Assertions.assertFalse(sut.getAllTasks().contains("Task"));
    }


}
