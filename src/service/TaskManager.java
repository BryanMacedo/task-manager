package service;

import dominio.Task;

import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void listTasks(){
        for (Task listTask : tasks) {
            System.out.println("\n"+ listTask);
        }
        System.out.println();
    }
}
